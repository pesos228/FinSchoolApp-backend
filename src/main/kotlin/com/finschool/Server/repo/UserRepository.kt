package com.finschool.Server.repo

import com.finschool.Server.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int> {
    fun findByLogin(login: String): User?
    fun findByMail(email: String): User?
}