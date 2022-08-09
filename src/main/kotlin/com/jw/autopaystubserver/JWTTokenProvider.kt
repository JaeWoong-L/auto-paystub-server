package com.jw.autopaystubserver

import com.jw.autopaystubserver.domain.user.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JWTTokenProvider(private val userService: UserService) {

    val EXPIRATION_MS = 30 * 24 * 3600 * 1000L
    var secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun createToken(account: String): String {

        val now = Date()
        val expirationDate = Date(now.time + EXPIRATION_MS)

        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .signWith(secretKey)
            .setIssuedAt(now)
            .setExpiration(expirationDate)
            .setSubject(account)
            .compact()
    }

    fun getTokenFromRequest(request: HttpServletRequest): String? {
        return request.getHeader("Authorization")
    }

    fun getAuthentication(token: String): Authentication {
        val userAccount = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).body.subject
//        val userDetails = userDetailsService.loadUserByUsername(userAccount)
        val userDetails = userService.findUserByEmail(userAccount)

        return UsernamePasswordAuthenticationToken(userDetails, null, null)
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
            return true
        } catch (e: Exception) {
            println("token validation error")
        }

        return false
    }
}