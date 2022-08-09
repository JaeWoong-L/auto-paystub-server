package com.jw.autopaystubserver.domain.worker

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jw.autopaystubserver.domain.company.Company
import com.jw.autopaystubserver.model.PersonEntity
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "worker")
@SQLDelete(sql = "UPDATE worker SET activation = false WHERE id = ?")
@Where(clause = "activation = true")
class Worker(

    @Column
    var gender: String?,

    @Column
    var birth: String?, // 어떤 식으로 넘겨줄지 물어보기

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    var company: Company,

    @Column
    @JsonIgnore
    var activation: Boolean = true,

    name: String?,
    email: String?,
    mobilePhone: String?

): PersonEntity(name, email, mobilePhone) {}