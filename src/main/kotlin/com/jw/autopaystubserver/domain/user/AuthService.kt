package com.jw.autopaystubserver.domain.user

import com.jw.autopaystubserver.util.exception.BaseException
import com.jw.autopaystubserver.util.exception.ExceptionResponse
import com.jw.autopaystubserver.domain.agency.AgencyRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AuthService(
    private val userRepository: UserRepository,
    private val agencyRepository: AgencyRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    fun register(userDto: UserDto.SignupRequest): User {
        val existingUser = userRepository.findByEmail(userDto.email)
        if(existingUser != null) {
            throw BaseException(ExceptionResponse.DUPLICATED_EMAIL)
        }

        val agency = agencyRepository.findByIdOrNull(userDto.agencyId)
            ?: throw BaseException(ExceptionResponse.AGENCY_NOT_FOUND)

        userDto.encodePassword(passwordEncoder)
        val user = userDto.toEntity(agency)

        return userRepository.save(user)
    }

    fun login(userDto: UserDto.SignInRequest): User {
        val user = userRepository.findByEmail(userDto.email) ?: throw BaseException(ExceptionResponse.USER_NOT_FOUND)

        if(!user.isValidPassword(passwordEncoder, userDto.password))
            throw BaseException(ExceptionResponse.PASSWORD_MISMATCH)

        return user
    }

}