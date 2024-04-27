package com.finschool.Server.controllers

import com.finschool.Server.dto.UserEditDto
import com.finschool.Server.repo.UserRepository
import com.finschool.Server.security.JwtUtil
import com.finschool.Server.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil) {

    @GetMapping("/get-info")
    fun infoGet(@RequestHeader("Authorization") token: String?): ResponseEntity<Any> {
        val accessToken = token?.substringAfter("Bearer ")
        return if (jwtUtil.validateAccessToken(accessToken)) {
            val login = jwtUtil.validateAccessTokenAndRetrieveClaim(accessToken)
            ResponseEntity.ok().body(userRepository.findByLogin(login))
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid access token")
        }
    }

    @PatchMapping("/edit")
    fun userEdit(@RequestHeader("Authorization") token: String?, @RequestBody userEditDto: UserEditDto): ResponseEntity<Any> {
        val accessToken = token?.substringAfter("Bearer ")
        return if (jwtUtil.validateAccessToken(accessToken)) {
            val login = jwtUtil.validateAccessTokenAndRetrieveClaim(accessToken)
            userService.editUser(login, userEditDto)
            ResponseEntity.ok().body("User edited successfully")
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid access token")
        }
    }
}