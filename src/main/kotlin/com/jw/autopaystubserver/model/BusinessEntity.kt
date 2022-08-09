package com.jw.autopaystubserver.model

import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
class BusinessEntity(
    @Column
    var name: String? = null,

    @Column(name="business_number")
    var businessNumber: String? = null,

    @Column
    var representative: String? = null
) : BaseEntity()