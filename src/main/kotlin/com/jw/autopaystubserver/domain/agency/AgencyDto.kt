package com.jw.autopaystubserver.domain.agency

class AgencyDto {

    data class Request(
        var name: String?,
        var businessNumber: String?,
        var representative: String?,
        var address: String?,
        var telephone: String?,

        ) {
        fun toEntity() = Agency(
            address = address,
            telephone = telephone,
            name = name,
            businessNumber = businessNumber,
            representative = representative
        )
    }
}