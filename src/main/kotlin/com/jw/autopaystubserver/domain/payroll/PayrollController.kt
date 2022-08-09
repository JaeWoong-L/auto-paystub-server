package com.jw.autopaystubserver.domain.payroll

import com.jw.autopaystubserver.util.excel.ExcelService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/payrolls")
class PayrollController(
    private var payrollService: PayrollService,
    private var excelService: ExcelService
) {
    @GetMapping()
    fun findList(): ResponseEntity<*> {
        val payrolls = payrollService.findPayrolls()

        val response = mapOf(
            "data" to payrolls
        )

        return ResponseEntity.ok(response)
    }

    @PostMapping()
    fun create(@Valid @RequestBody payrollDto: PayrollDto.Request): ResponseEntity<*> {
        val payroll = payrollService.register(payrollDto)

        excelService.parseExcel(payroll.excelFileUrl, payroll.payrollFormat.id)

        val response = mapOf(
            "data" to mapOf(
                "payroll" to payroll
            )
        )

        return ResponseEntity.ok(response)
    }
}