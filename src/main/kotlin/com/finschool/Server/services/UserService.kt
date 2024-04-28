package com.finschool.Server.services

import com.finschool.Server.dto.UserEditDto
import com.finschool.Server.models.User
import com.finschool.Server.repo.AchievementRepository
import com.finschool.Server.repo.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository,
                  private val passwordEncoder: PasswordEncoder,
    private val achievementRepository: AchievementRepository) {
    fun editUser(login: String, userEditDto: UserEditDto):User{
        val user = userRepository.findByLogin(login)
            ?: throw EntityNotFoundException("User not found with login: $login")
        userEditDto.lvl?.let { user.lvl = it }
        userEditDto.mail?.let { user.mail = it }
        userEditDto.name?.let { user.name = it }
        userEditDto.password?.let { user.password = passwordEncoder.encode(it) }
        return userRepository.save(user)
    }
    fun addAchievementToUser(login: String, achievementId: Int) {
        val user = userRepository.findByLogin(login) ?: return
        val achievementOptional = achievementRepository.findById(achievementId)

        if (achievementOptional.isPresent) {
            val achievement = achievementOptional.get()
            user.achievementsReceived.add(achievement)
            userRepository.save(user)
        }
    }

    fun removeAchievementFromUser(login: String, achievementId: Int){
        val user = userRepository.findByLogin(login) ?: return
        val achievementOptional = achievementRepository.findById(achievementId)
        if(achievementOptional.isPresent){
            user.achievementsReceived.remove(achievementOptional.get())
            userRepository.save(user)
        }
    }
}