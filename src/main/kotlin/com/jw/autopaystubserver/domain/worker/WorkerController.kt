package com.jw.autopaystubserver.domain.worker

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/workers")
class WorkerController(
    private val workerService: WorkerService
) {
    @PostMapping("")
    fun create(@Valid @RequestBody workerDto: WorkerDto.Request): ResponseEntity<*> {
        val worker = workerService.register(workerDto)

        val response = mapOf(
            "data" to mapOf(
                "worker" to worker
            )
        )

        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") id: Long): ResponseEntity<Unit> {
        workerService.deleteWorker(id)

        return ResponseEntity.noContent().build()
    }
}