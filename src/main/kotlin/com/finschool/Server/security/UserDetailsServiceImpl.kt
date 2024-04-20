package com.finschool.Server.security

import com.example.articlewithalexandr.security.UserDetailsImpl
import com.finschool.Server.repo.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired

@Service
@Transactional(readOnly = true)
class UserDetailsServiceImpl @Autowired constructor(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        val user = userRepository.findByLogin(login)
        if (user != null) {
            return UserDetailsImpl(user)
        } else {
            throw UsernameNotFoundException("User not found for login: $login")
        }
    }

    fun loadUserByMail(mail: String): UserDetails {
        val user = userRepository.findByMail(mail)
        if (user != null) {
            return UserDetailsImpl(user)
        } else {
            throw UsernameNotFoundException("User not found for email: $mail")
        }
    }
}