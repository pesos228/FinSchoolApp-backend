package com.finschool.Server.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.auth0.jwt.interfaces.DecodedJWT
import com.finschool.Server.dto.JwtTokenResponse
import com.finschool.Server.models.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.*

@Service
class JwtUtil(@Value("\${jwt.secret.access}")
              private val accessSecret: String,

              @Value("\${jwt.secret.refresh}")
              private val refreshSecret: String) {
    private companion object {
        const val USER_DETAILS = "User details"
        const val LOGIN = "login"
        const val ID = "id"
        const val ISSUER = "Finschool"
    }

    private fun generateAccessToken(user: User):String{
        val issuedDate = Date()
        val expirationDate = Date.from(ZonedDateTime.now().plusHours(1).toInstant())

        return JWT.create()
                .withSubject(USER_DETAILS)
                .withClaim(ID, user.id)
                .withClaim(LOGIN, user.login)
                .withIssuedAt(issuedDate)
                .withIssuer(ISSUER)
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(accessSecret))
    }

    private fun generateRefreshToken(user: User): String {
        val issuedDate = Date()
        val expirationDate = Date.from(ZonedDateTime.now().plusHours(48).toInstant())

        return JWT.create()
                .withSubject(USER_DETAILS)
                .withClaim(ID, user.id)
                .withClaim(LOGIN, user.login)
                .withIssuedAt(issuedDate)
                .withIssuer(ISSUER)
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(refreshSecret))
    }
    fun generateJWTResponse(user: User): JwtTokenResponse {
        return JwtTokenResponse(generateAccessToken(user), generateRefreshToken(user))
    }

    fun validateAccessTokenAndRetrieveClaim(token: String): String {
        val verifier: JWTVerifier = JWT.require(Algorithm.HMAC256(accessSecret))
                .withSubject(USER_DETAILS)
                .withIssuer(ISSUER)
                .build()

        return try {
            val jwt: DecodedJWT = verifier.verify(token)
            jwt.getClaim(LOGIN).asString()
        } catch (e: JWTVerificationException) {
            throw IllegalArgumentException("Invalid access token!")
        }
    }

    fun validateAccessToken(token: String): Boolean {
        return try {
            val verifier: JWTVerifier = JWT.require(Algorithm.HMAC256(accessSecret))
                .withSubject(USER_DETAILS)
                .withIssuer(ISSUER)
                .build()
            val jwt: DecodedJWT = verifier.verify(token)
            !jwt.expiresAt.before(Date())
        } catch (e: JWTVerificationException) {
            false
        } catch (e: TokenExpiredException) {
            false
        }
    }

    fun validateRefreshToken(token: String): Boolean {
        return try {
            val verifier: JWTVerifier = JWT.require(Algorithm.HMAC256(refreshSecret))
                .withSubject(USER_DETAILS)
                .withIssuer(ISSUER)
                .build()
            val jwt: DecodedJWT = verifier.verify(token)
            !jwt.expiresAt.before(Date())
        } catch (e: JWTVerificationException) {
            false
        } catch (e: TokenExpiredException) {
            false
        }
    }

    fun getLogin(token: String): String {
        return try {
            val decodedJWT: DecodedJWT = JWT.decode(token)
            decodedJWT.getClaim(LOGIN).asString()
        } catch (e: JWTDecodeException){
            throw IllegalArgumentException("Invalid access token!")
        }
    }

}