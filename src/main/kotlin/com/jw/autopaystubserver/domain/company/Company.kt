package com.jw.autopaystubserver.domain.company

import com.jw.autopaystubserver.model.BusinessEntity
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "company")
class Company(

    @Column(name = "logo_image_url")
    var logoImageUrl: String?,// 급여명세서 마지막에 넣는 메시지

    @Column
    var message: String?,

    name: String?,
    businessNumber: String?,
    representative: String?

    ) : BusinessEntity(name, businessNumber, representative) {

    override fun toString(): String {
        return "Company(name=$name, businessNumber=$businessNumber, representative=$representative, logoImageUrl=$logoImageUrl, message=$message)"
    }

    override fun equals(other: Any?): Boolean {
        return businessNumber == (other as Company).businessNumber
    }

    override fun hashCode(): Int {
        return businessNumber.hashCode()
    }
}