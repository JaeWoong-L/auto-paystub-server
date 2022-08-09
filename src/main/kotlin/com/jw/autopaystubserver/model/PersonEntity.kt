package com.jw.autopaystubserver.model

import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
class PersonEntity(
    @Column
    var name: String? = null,

    @Column
    var email: String? = null,

    @Column(name = "mobile_phone")
    var mobilePhone: String? = null
) : BaseEntity()