package com.finschool.Server.controllers

import com.finschool.Server.dto.UserEditDto
import com.finschool.Server.security.JwtUtil
import com.finschool.Server.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
    private val jwtUtil: JwtUtil) {

    @PatchMapping("/edit")
    fun userEdit(@RequestHeader("Authorization") token: String?, @RequestBody userEditDto: UserEditDto): ResponseEntity<Any> {
        return try {
            if (token != null && token.startsWith("Bearer ")) {
                val accessToken = token.substring(7)
                val login = jwtUtil.validateAccessTokenAndRetrieveClaim(accessToken)
                userService.editUser(login, userEditDto)
                ResponseEntity.ok().body("User edited successfully")
            } else {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid access token")
            }
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred")
        }
    }
}