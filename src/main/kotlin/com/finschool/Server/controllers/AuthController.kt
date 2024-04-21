package com.finschool.Server.controllers

import com.finschool.Server.dto.JwtTokenResponse
import com.finschool.Server.dto.UserLoginDto
import com.finschool.Server.models.User
import com.finschool.Server.services.AuthorizationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.finschool.Server.errors.UserNotExistsValidator

@RestController
@RequestMapping("/auth")
class AuthController(private val authorizationService: AuthorizationService, private val userNotExistsValidator: UserNotExistsValidator) {


    @PostMapping("/register")
    fun register(@RequestBody user: User, bindingResult: BindingResult): ResponseEntity<Any> {
        userNotExistsValidator.validate(user, bindingResult)
        if (bindingResult.hasErrors()) {
            val errorMessages = bindingResult.allErrors.map { it.defaultMessage ?: "Validation error" }
            return ResponseEntity.badRequest().body(errorMessages)
        }
        return ResponseEntity(authorizationService.saveUserAndReturnJwtResponse(user), HttpStatus.CREATED)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginDto: UserLoginDto): ResponseEntity<JwtTokenResponse> {
        return ResponseEntity.ok(authorizationService.login(loginDto))
    }

}