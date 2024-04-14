package com.finschool.Server.models

import jakarta.persistence.*

@Entity
@Table(name="Users")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, name = "UserId")
    val id: Int,

    @Column(nullable = false, unique = true, name = "Login")
    var login: String,

    @Column(nullable = false, length = 12, name = "Name")
    var name: String,

    @Column(nullable = false, name = "Password")
    var password: String,

    @Column(nullable = false, unique = true, name = "Mail")
    var mail: String,

    @Column(nullable = false, name = "Lvl")
    var lvl: Int = 1,

    @ManyToMany
    @JoinTable(
            name = "SavedThemes",
            joinColumns = [JoinColumn(name = "UserId", referencedColumnName = "UserId")],
            inverseJoinColumns = [JoinColumn(name = "ThemeId", referencedColumnName = "ThemeId")]
    )
    val savedThemes: MutableList<Theme> = mutableListOf(),

    @ManyToMany
    @JoinTable(
            name = "AchievementUsers",
            joinColumns = [JoinColumn(name = "UserId", referencedColumnName = "UserId")],
            inverseJoinColumns = [JoinColumn(name = "AchId", referencedColumnName = "AchId")]
    )
    val achievementsReceived: MutableList<Achievement> = mutableListOf()
)