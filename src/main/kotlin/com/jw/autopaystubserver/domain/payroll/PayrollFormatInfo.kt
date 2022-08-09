package com.jw.autopaystubserver.domain.payroll

import com.jw.autopaystubserver.domain.payroll.PayrollFormat
import com.jw.autopaystubserver.model.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "payroll_format_info")
class PayrollFormatInfo(
    @Column
    var code: String,

    @Column
    var value: String,

    @Column
    var sequence: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payroll_format_id")
    var payrollFormat: PayrollFormat

) : BaseEntity()