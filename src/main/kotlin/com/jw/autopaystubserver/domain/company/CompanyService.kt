package com.jw.autopaystubserver.domain.company

import com.jw.autopaystubserver.util.exception.BaseException
import com.jw.autopaystubserver.util.exception.ExceptionResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CompanyService(private var companyRepository: CompanyRepository) {
    fun register(companyDto: CompanyDto.Request): Company {
        val existingCompany = companyRepository.findByBusinessNumber(companyDto.businessNumber)
        if(existingCompany != null) {
            throw BaseException(ExceptionResponse.DUPLICATED_COMPANY)
        }

        val company = companyDto.toEntity()

        return companyRepository.save(company)
    }

    fun findCompanies(): List<Company> {
        return companyRepository.findAll()
    }

    fun deleteCompany(id: Long) {
        val company = companyRepository.findByIdOrNull(id)

        if(company == null) {
            throw BaseException(ExceptionResponse.COMPANY_NOT_FOUND)
        } else {
            return companyRepository.deleteById(id)
        }
    }
}