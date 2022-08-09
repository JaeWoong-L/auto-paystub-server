package com.jw.autopaystubserver.domain.agency

import com.jw.autopaystubserver.util.exception.BaseException
import com.jw.autopaystubserver.util.exception.ExceptionResponse
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class AgencyService(
    private val agencyRepository: AgencyRepository
) {
    fun register(agencyDto: AgencyDto.Request): Agency {
        val existingAgency = agencyRepository.findByBusinessNumber(agencyDto.businessNumber)
        if(existingAgency != null) {
            throw BaseException(ExceptionResponse.DUPLICATED_AGENCY)
        }

        val agency = agencyDto.toEntity()

        return agencyRepository.save(agency)
    }

    fun findAgencies(): List<Agency> {
        return agencyRepository.findAll()
    }
}