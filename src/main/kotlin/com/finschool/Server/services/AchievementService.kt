package com.finschool.Server.services

import com.finschool.Server.errors.AchievementAlreadyExistsException
import com.finschool.Server.errors.AchievementNotFoundException
import com.finschool.Server.models.Achievement
import com.finschool.Server.repo.AchievementRepository
import org.springframework.stereotype.Service

@Service
class AchievementService(private val achievementRepository: AchievementRepository) {

    fun addAchievement(name: String): Achievement{
        if (achievementRepository.findByName(name) != null){
            throw AchievementAlreadyExistsException("Achievement with name '$name' already exists")
        }
        val newAchievement = Achievement(name = name)
        return achievementRepository.save(newAchievement)
    }
    fun removeAchievement(achievementId: Int) {
        val achievement = achievementRepository.findById(achievementId)
        if (achievement.isPresent) {
            achievementRepository.delete(achievement.get())
        } else {
            throw AchievementNotFoundException("Achievement with id '$achievementId' not found")
        }
    }

    fun getAllAchievements(): List<Achievement>{
        return achievementRepository.findAll()
    }
}