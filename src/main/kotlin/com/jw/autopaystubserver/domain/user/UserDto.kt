package com.jw.autopaystubserver.domain.user

import com.jw.autopaystubserver.domain.agency.Agency
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.validation.constraints.NotNull

class UserDto {

    data class SignupRequest(
        @field:NotNull(message = "이메일은 필수 입력사항입니다.")
        var email: String?,

        @field:NotNull(message = "비밀번호는 필수 입력사항입니다.")
        private var password: String?,

//        @field:NotBlank(message = "이름은 필수 입력사항입니다.")
        var name: String?,

//        @field:NotBlank(message = "전화번호는 필수 입력사항입니다.")
        var mobilePhone: String?,

        @field:NotNull(message = "agencyId는 필수 입력사항입니다.")
        var agencyId: Long?,

        ) {
        fun toEntity(agency: Agency) = User(
            name = name,
            email = email,
            mobilePhone = mobilePhone,
            password = password,
            agency = agency
        )

        fun encodePassword(passwordEncoder: BCryptPasswordEncoder) {
            this.password = passwordEncoder.encode(this.password)
        }
    }

    data class SignInRequest(
        val email: String,
        val password: String,
    )
}