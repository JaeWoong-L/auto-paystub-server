package com.jw.autopaystubserver.domain.agency

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/agencies")
class AgencyController(var agencyService: AgencyService) {
    @GetMapping()
    fun findList(): ResponseEntity<*> {
        val agencies = agencyService.findAgencies()

        val response = mapOf(
            "data" to mapOf(
                "agencies" to agencies
            )
        )

        return ResponseEntity.ok(response)
    }

    @PostMapping()
    fun create(@Valid @RequestBody agencyDto: AgencyDto.Request): ResponseEntity<*> {

        val agency = agencyService.register(agencyDto)

        val response = mapOf(
            "data" to mapOf(
                "agency" to agency
            )
        )

        return ResponseEntity.ok(response)
    }
}