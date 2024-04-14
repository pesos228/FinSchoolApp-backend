package com.finschool.Server.services

import com.finschool.Server.dto.JwtTokenResponse
import com.finschool.Server.dto.UserLoginDto
import com.finschool.Server.models.User
import com.finschool.Server.repo.UserRepository
import com.finschool.Server.security.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder



@Service
class AuthorizationService(@Autowired private val userRepository: UserRepository,
                           private val authenticationManager: AuthenticationManager, private val jwtUtil: JwtUtil,
                           private val passwordEncoder: PasswordEncoder) {

    fun saveUserAndReturnJwtResponse(user: User): JwtTokenResponse{
        user.password = passwordEncoder.encode(user.password)
        userRepository.save(user)
        return jwtUtil.generateJWTResponse(user)
    }

    fun login(loginDto: UserLoginDto): JwtTokenResponse{
        val auth = UsernamePasswordAuthenticationToken(loginDto.login, loginDto.password)
        authenticationManager.authenticate(auth)
        val user = userRepository.findByLogin(loginDto.login) ?: throw IllegalArgumentException("User not found")

        return jwtUtil.generateJWTResponse(user)
    }

}