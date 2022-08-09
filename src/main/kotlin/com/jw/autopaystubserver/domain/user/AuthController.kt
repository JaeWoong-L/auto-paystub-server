package com.jw.autopaystubserver.domain.user

import com.jw.autopaystubserver.JWTTokenProvider
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val jwtTokenProvider: JWTTokenProvider
) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody @Valid userDto: UserDto.SignupRequest): ResponseEntity<*> {

        val user: User = authService.register(userDto)

        val response = mapOf(
            "data" to mapOf(
                "user" to user
            )
        )

        return ResponseEntity.ok(response)

    }

    @PostMapping("/sign-in")
    fun signIn(@Valid @RequestBody userDto: UserDto.SignInRequest): ResponseEntity<*> {

        val user = authService.login(userDto)

        val token = jwtTokenProvider.createToken(userDto.email)

        val response = mapOf(
            "data" to mapOf(
                "token" to token,
                "user" to user
            )
        )

        return ResponseEntity.ok(response)

    }
}