package com.finschool.Server.errors

import com.finschool.Server.models.User
import com.finschool.Server.security.UserDetailsServiceImpl
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import org.springframework.security.core.userdetails.UsernameNotFoundException
import lombok.RequiredArgsConstructor

@Component
@RequiredArgsConstructor
class UserNotExistsValidator(private val userDetailsService: UserDetailsServiceImpl) : Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return User::class.java.isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        val user = target as User

        try {
            userDetailsService.loadUserByUsername(user.login)
        } catch (ex: UsernameNotFoundException) {
            // пользователь не найден, валидация успешно пройдена
            return
        }

        errors.rejectValue("email", "", "Client with this email already exists!")
    }
}