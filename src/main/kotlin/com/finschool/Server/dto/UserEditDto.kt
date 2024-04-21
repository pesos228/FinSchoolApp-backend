package com.finschool.Server.dto


data class UserEditDto(
    val name: String?,
    val mail: String?,
    val password: String?,
    val lvl: Int?
)