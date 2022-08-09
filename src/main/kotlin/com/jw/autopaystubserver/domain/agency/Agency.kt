package com.jw.autopaystubserver.domain.agency

import com.jw.autopaystubserver.model.BusinessEntity
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "agency")
@SQLDelete(sql = "UPDATE agency SET activation = false WHERE id = ?")
@Where(clause = "activation = true")
class Agency(
    @Column
    var address: String?,

    @Column
    var telephone: String?,

    @Column
    var activation: Boolean = true,

    name: String?,
    businessNumber: String?,
    representative: String?

) : BusinessEntity(name, businessNumber, representative) {

    //    override fun toString(): String {
//        return "Agency(id=$id, name=$name, business_number=$businessNumber, representative=$representative, address=$address, telephone=$telephone)"
//    }
//
//    override fun equals(other: Any?): Boolean {
//        return id == (other as Agency).id
//    }
}