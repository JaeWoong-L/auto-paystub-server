package com.jw.autopaystubserver.domain.payroll

import com.jw.autopaystubserver.domain.company.Company
import javax.validation.constraints.NotNull

class PayrollDto {
    data class Request(
        var excelFileUrl: String,

        var name: String?,

        var period: Int?,

        var year: Int?,

        var month: Int?,

        var sequence: Int?,

        @field:NotNull(message = "companyId는 필수 입력사항입니다.")
        var companyId: Long,

        @field:NotNull(message = "formatId는 필수 입력사항입니다.")
        var formatId: Long

    ) {
        fun toEntity(company: Company, payrollFormat: PayrollFormat) = Payroll(
            excelFileUrl = excelFileUrl,
            name = name,
            period = period,
            year = year,
            month = month,
            sequence = sequence,
            company = company,
            payrollFormat = payrollFormat
        )
    }
}