package com.jw.autopaystubserver.domain.payroll

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PayrollRepository : JpaRepository<Payroll, Long> {
    fun findByName(name: String?): Payroll?
}