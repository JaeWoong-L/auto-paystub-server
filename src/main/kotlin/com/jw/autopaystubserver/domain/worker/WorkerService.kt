package com.jw.autopaystubserver.domain.worker

import com.jw.autopaystubserver.domain.company.CompanyRepository
import com.jw.autopaystubserver.util.exception.BaseException
import com.jw.autopaystubserver.util.exception.ExceptionResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class WorkerService(
    private val workerRepository: WorkerRepository,
    private val companyRepository: CompanyRepository
    ) {

    fun register(workerDto: WorkerDto.Request): Worker {
        val existingWorker = workerRepository.findByNameAndBirth(workerDto.name, workerDto.birth)
        if(existingWorker != null) {
            throw BaseException(ExceptionResponse.DUPLICATED_WORKER)
        }

        val company = companyRepository.findByIdOrNull(workerDto.companyId)

        if(company == null) throw BaseException(ExceptionResponse.COMPANY_NOT_FOUND)


        val worker = workerDto.toEntity(company)

        return workerRepository.save(worker)
    }

    fun deleteWorker(id: Long) {
        val worker = workerRepository.findByIdOrNull(id)

        if(worker == null) {
            throw BaseException(ExceptionResponse.WORKER_NOT_FOUND)
        } else {
            return workerRepository.deleteById(id)
        }
    }
}