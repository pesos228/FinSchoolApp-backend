package com.finschool.Server.controllers

import com.finschool.Server.dto.AchievementDto
import com.finschool.Server.errors.AchievementAlreadyExistsException
import com.finschool.Server.errors.AchievementNotFoundException
import com.finschool.Server.security.JwtUtil
import com.finschool.Server.services.AchievementService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/achievements")
class AchievementController(private val jwtUtil: JwtUtil,
                            @Value("\${app.secret-password}") private val password: String,
                            private val achievementService: AchievementService) {

    @GetMapping("/list")
    fun achievementList(@RequestHeader("Authorization") token: String?): ResponseEntity<Any>{
        val accessToken = token?.substringAfter("Bearer ")
        return if (jwtUtil.validateAccessToken(accessToken)){
            ResponseEntity.ok().body(achievementService.getAllAchievements())
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid access token")
        }
    }

    @PostMapping("/add")
    fun addAchievement(
        @RequestHeader("Authorization") token: String?,
        @RequestBody achievementDto: AchievementDto
    ): ResponseEntity<Any> {
        val accessToken = token?.substringAfter("Bearer ")
        return if (jwtUtil.validateAccessToken(accessToken)) {
            if (achievementDto.password == password && achievementDto.name != null) {
                try {
                    achievementService.addAchievement(achievementDto.name)
                    ResponseEntity.ok().body("Achievement added successfully")
                } catch (e: AchievementAlreadyExistsException) {
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
                }
            } else {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid secret password or achievement is null")
            }
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid access token")
        }
    }

    @DeleteMapping("/remove")
    fun removeAchievement(@RequestHeader("Authorization") token: String?,
                          @RequestBody achievementDto: AchievementDto): ResponseEntity<Any>{
        val accessToken = token?.substringAfter("Bearer ")
        return if (jwtUtil.validateAccessToken(accessToken)){
            if(achievementDto.password == password && achievementDto.id != null){
                try {
                    achievementService.removeAchievement(achievementId = achievementDto.id)
                    ResponseEntity.ok().body("Achievement removed successfully")
                }catch (e: AchievementNotFoundException){
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
                }
            }
            else{
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid secret password or achievementId is null")
            }
        }
            else{
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid access token")
            }
    }
}