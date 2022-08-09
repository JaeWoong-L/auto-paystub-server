package com.jw.autopaystubserver.domain.payroll

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PayrollFormatDirectiveRepository: JpaRepository<PayrollFormatDirective, Long> {
    fun findAllByPayrollFormatId(id: Long?): List<PayrollFormatDirective>?
}