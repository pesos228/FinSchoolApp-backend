package com.finschool.Server.models


import jakarta.persistence.*

@Entity
@Table(name = "Achievement")
class Achievement (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, name = "AchId")
    val id: Int,

    @Column(nullable = false, unique = true, length = 250, name = "AchName")
    var name: String,

    @ManyToMany(mappedBy = "achievementsReceived")
    val users: MutableList<User> = mutableListOf()
)