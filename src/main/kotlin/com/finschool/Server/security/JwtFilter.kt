package com.finschool.Server.security

import com.finschool.Server.security.JwtUtil
import com.finschool.Server.security.UserDetailsServiceImpl
import com.finschool.Server.repo.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
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
        private val userDetailsService: UserDetailsServiceImpl,
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val jwt = authHeader.substring(7)
            if (jwt.isBlank()) {
                response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid JWT Token in Bearer Header")
                return
            }

            try {
                val login = jwtUtil.validateAccessTokenAndRetrieveClaim(jwt)
                val userDetails = userDetailsService.loadUserByUsername(login)
                val authToken = UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.authorities
                )

                if (SecurityContextHolder.getContext().authentication == null) {
                    SecurityContextHolder.getContext().authentication = authToken
                }
            } catch (e: JWTVerificationException) {
                response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid JWT Token")
                return
            }
        }

        filterChain.doFilter(request, response)
    }
}