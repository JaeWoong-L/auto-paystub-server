package com.jw.autopaystubserver.domain.agency

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AgencyRepository: JpaRepository<Agency, Long> {
    fun findByBusinessNumber(businessNumber: String?): Agency?
    fun findByName(name: String?): Agency?
}