package com.finschool.Server.dto

data class JwtTokenResponse(
        val accessToken: String,
        val refreshToken: String
)