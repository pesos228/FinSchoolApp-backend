package com.finschool.Server.repo

import com.finschool.Server.models.Achievement
import org.springframework.data.jpa.repository.JpaRepository

interface AchievementRepository: JpaRepository<Achievement, Int> {
    fun findByName(name: String): Achievement?
}