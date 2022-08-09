package com.jw.autopaystubserver.util.excel

import com.jw.autopaystubserver.domain.payroll.PayrollFormatDirective
import com.jw.autopaystubserver.domain.payroll.PayrollFormatDirectiveRepository
import org.apache.poi.ss.util.CellReference
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.File
import java.io.FileInputStream

@Service
@Transactional
class ExcelService(
    val payrollFormatDirectiveRepository: PayrollFormatDirectiveRepository
) {
    fun parseExcel(filePath: String, formatId: Long) {
        val file = File(filePath)
        val fis = FileInputStream(file)
        val workbook = XSSFWorkbook(fis)
        val sheet = workbook.getSheetAt(0)
        val formatInfoList: List<PayrollFormatDirective>? = payrollFormatDirectiveRepository.findAllByPayrollFormatId(formatId)

        formatInfoList?.let {
            for(formatInfo in it) {
                val cell = CellReference(formatInfo.value)
                val row = sheet.getRow(cell.row)
//                val data = row.getCell(cell.col.toInt()).stringCellValue
            }
        }
    }
}