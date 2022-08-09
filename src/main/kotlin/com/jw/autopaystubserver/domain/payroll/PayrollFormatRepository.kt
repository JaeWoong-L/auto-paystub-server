package com.jw.autopaystubserver.domain.payroll

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PayrollFormatRepository: JpaRepository<PayrollFormat, Long> {
    fun findByName(name: String?): PayrollFormat?
}