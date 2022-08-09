package com.jw.autopaystubserver.domain.paystub

import com.jw.autopaystubserver.domain.payroll.Payroll
import com.jw.autopaystubserver.model.BaseEntity
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "paystub")
@SQLDelete(sql = "UPDATE paystub SET activation = false WHERE id = ?")
@Where(clause = "activation = true")
class Paystub(

    @Column(name = "paystub_image_url")
    var paystubImageUrl: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payroll_id")
    var payroll: Payroll

): BaseEntity()