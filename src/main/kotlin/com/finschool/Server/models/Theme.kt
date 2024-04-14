package com.finschool.Server.models

import jakarta.persistence.*

@Entity
@Table(name = "Themes")
class Theme(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, name = "ThemeId")
    val id: Int,

    @Column(nullable = false, unique = true, name = "Name")
    var name: String,

    @ManyToMany(mappedBy = "savedThemes")
    val users: MutableList<User> = mutableListOf()
)