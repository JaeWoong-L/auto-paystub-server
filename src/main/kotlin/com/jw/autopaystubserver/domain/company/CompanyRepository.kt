package com.jw.autopaystubserver.domain.company

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository: JpaRepository<Company, Long> {
    fun findByBusinessNumber(businessNumber: String?): Company?
    fun findByName(name: String?): Company?
}