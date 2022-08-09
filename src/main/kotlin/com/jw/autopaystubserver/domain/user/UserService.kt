package com.jw.autopaystubserver.domain.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class UserService(private val userRepository: UserRepository) {

    fun findUsers(): List<User> {
        return userRepository.findAll()
    }

    fun findUser(userId: Long): Optional<User> {
        return userRepository.findById(userId)
    }

    fun findUserByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

}