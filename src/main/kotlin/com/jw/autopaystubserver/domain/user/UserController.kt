package com.jw.autopaystubserver.domain.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(var userService: UserService) {

    @GetMapping()
    fun findList(): ResponseEntity<*> {
        val users = userService.findUsers()

        val response = mapOf(
            "data" to mapOf(
                "users" to users
            )
        )

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable(value = "id") id: Long): ResponseEntity<*> {
        val user = userService.findUser(id)

        val response = mapOf(
            "data" to mapOf(
                "user" to user
            )
        )

        return ResponseEntity.ok(response)
    }
}