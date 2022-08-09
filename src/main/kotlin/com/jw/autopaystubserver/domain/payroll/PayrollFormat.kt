package com.jw.autopaystubserver.domain.payroll

import com.jw.autopaystubserver.model.BaseEntity
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "payroll_format")
@SQLDelete(sql = "UPDATE payroll_format SET activation = false WHERE id = ?")
@Where(clause = "activation = true")
class PayrollFormat(
    @Column
    var name: String,

    @Column
    var activation: Boolean = true,

) : BaseEntity()