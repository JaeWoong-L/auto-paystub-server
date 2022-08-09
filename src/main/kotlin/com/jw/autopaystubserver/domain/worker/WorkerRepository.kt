package com.jw.autopaystubserver.domain.worker

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkerRepository : JpaRepository<Worker, Long> {
    fun findByNameAndBirth(name: String?, birth: String?): Worker?
}