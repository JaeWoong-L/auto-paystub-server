package com.jw.autopaystubserver.domain.worker

import com.jw.autopaystubserver.domain.company.Company
import javax.validation.constraints.NotNull

class WorkerDto {
    data class Request(
        var gender: String?,

        @field:NotNull(message = "생년월일은 필수 입력사항입니다.")
        var birth: String?,

        @field:NotNull(message = "이름은 필수 입력사항입니다.")
        var name: String?,

        var email: String?,

        @field:NotNull(message = "전화번호는 필수 입력사항입니다.")
        var mobilePhone: String?,

        var companyId: Long?
    ) {
        fun toEntity(company: Company) = Worker(
            gender = gender,
            birth = birth,
            name = name,
            email = email,
            mobilePhone = mobilePhone,
            company = company
        )
    }
}