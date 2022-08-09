package com.jw.autopaystubserver.domain.payroll

import com.jw.autopaystubserver.domain.company.CompanyRepository
import com.jw.autopaystubserver.util.exception.BaseException
import com.jw.autopaystubserver.util.exception.ExceptionResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PayrollService(
    private val payrollRepository: PayrollRepository,
    private val companyRepository: CompanyRepository,
    private val payrollFormatRepository: PayrollFormatRepository
) {
    fun register(payrollDto: PayrollDto.Request): Payroll {
        val company = companyRepository.findByIdOrNull(payrollDto.companyId)
            ?: throw BaseException(ExceptionResponse.COMPANY_NOT_FOUND)

        val format = payrollFormatRepository.findByIdOrNull(payrollDto.formatId)
            ?: throw BaseException(ExceptionResponse.FORMAT_NOT_FOUND)

        val payroll = payrollDto.toEntity(company, format)

        return payrollRepository.save(payroll)
    }

    fun findPayrolls(): List<Payroll> {
        return payrollRepository.findAll()
    }
}