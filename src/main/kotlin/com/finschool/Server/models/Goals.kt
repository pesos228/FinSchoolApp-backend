package com.finschool.Server.models


import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "GoalsUsers")
class Goals (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, name = "GoalId")
    val id: Int,

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "UserId")
    val user: User,

    @Column(nullable = true, name = "Name")
    var name: String,

    @Column(nullable = true, precision = 11, scale = 2, name = "TargetAmount")
    var targetAmount: BigDecimal,

    @Column(nullable = true, precision = 11, scale = 2, name = "CurrentAmount")
    var currentAmount: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = true, unique = true, name = "PhotoUrl")
    var photoUrl: String
)