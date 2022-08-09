package com.jw.autopaystubserver.domain.company

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/companies")
class CompanyController(private var companyService: CompanyService) {

    @PostMapping("")
    fun create(@Valid @RequestBody companyDto: CompanyDto.Request): ResponseEntity<*> {

        val company = companyService.register(companyDto)

        val response = mapOf(
            "data" to mapOf(
                "company" to company
            )
        )

        return ResponseEntity.ok(response)
    }

    @GetMapping("")
    fun findList(): ResponseEntity<*> {
        val companies = companyService.findCompanies()

        val response = mapOf(
            "data" to mapOf(
                "companies" to companies
            )
        )

        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") id: Long): ResponseEntity<Unit> {
        companyService.deleteCompany(id)

        return ResponseEntity.noContent().build()
    }
}