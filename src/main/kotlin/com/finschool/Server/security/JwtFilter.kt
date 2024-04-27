package com.finschool.Server.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.ServletException
import com.auth0.jwt.exceptions.JWTVerificationException
import java.io.IOException
import org.springframework.http.HttpStatus

@Component
class JwtFilter(
    private val jwtUtil: JwtUtil,
    private val userDetailsService: UserDetailsServiceImpl
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val accessToken = authHeader.substring(7)

            try {
                if (jwtUtil.validateAccessToken(accessToken)) {
                    val login = jwtUtil.getLogin(accessToken)
                    val userDetails = userDetailsService.loadUserByUsername(login)
                    val authToken = UsernamePasswordAuthenticationToken(
                        userDetails, userDetails.password, userDetails.authorities
                    )

                    if (SecurityContextHolder.getContext().authentication == null) {
                        SecurityContextHolder.getContext().authentication = authToken
                    }
                } else {
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid Access Token")
                    return
                }
            } catch (e: JWTVerificationException) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid Access Token")
                return
            }
        }

        filterChain.doFilter(request, response)
    }
}