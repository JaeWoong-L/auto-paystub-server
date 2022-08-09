package com.jw.autopaystubserver.domain.company

class CompanyDto {

    data class Request(
        var name: String?,
        var businessNumber: String?,
        var representative: String?,
        var logoImageUrl: String?,
        var message: String?
    ) {
        fun toEntity() = Company(
            name = name,
            businessNumber = businessNumber,
            representative =  representative,
            logoImageUrl =  logoImageUrl,
            message =  message
        )
    }
}