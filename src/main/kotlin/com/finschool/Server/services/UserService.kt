package com.finschool.Server.services

import com.finschool.Server.dto.UserEditDto
import com.finschool.Server.models.User
import com.finschool.Server.repo.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {
    fun editUser(login: String, userEditDto: UserEditDto):User{
        val user = userRepository.findByLogin(login)
            ?: throw EntityNotFoundException("User not found with login: $login")
        userEditDto.lvl?.let { user.lvl = it }
        userEditDto.mail?.let { user.mail = it }
        userEditDto.name?.let { user.name = it }
        userEditDto.password?.let { user.password = passwordEncoder.encode(it) }
        return userRepository.save(user)
    }
}