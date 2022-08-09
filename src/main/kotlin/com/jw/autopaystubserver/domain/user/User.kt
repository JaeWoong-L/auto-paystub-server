package com.jw.autopaystubserver.domain.user

import com.jw.autopaystubserver.domain.agency.Agency
import com.jw.autopaystubserver.model.PersonEntity
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*

@Entity
@Table(name = "user")
@SQLDelete(sql = "UPDATE user SET activation = false WHERE id = ?")
@Where(clause = "activation = true")
class User(
    @Column(nullable = false)
    private var password: String?,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agency_id")
    var agency: Agency?,

    @Column
    var activation: Boolean = true,

    name: String?,
    email: String?,
    mobilePhone: String?

) : PersonEntity(name, email, mobilePhone) {

    fun isValidPassword(passwordEncoder: BCryptPasswordEncoder, password: String): Boolean {
        return passwordEncoder.matches(password, this.password)
    }

//    override fun toString(): String {
//        return "User(id=$id, email=$email, name=$name, mobile_phone=$mobilePhone, agency_id=${agency.id})"
//    }
//
//    override fun equals(other: Any?): Boolean {
//        return id == (other as User).id
//    }

}