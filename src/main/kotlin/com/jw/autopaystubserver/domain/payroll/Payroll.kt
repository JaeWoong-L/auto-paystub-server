package com.jw.autopaystubserver.domain.payroll

import com.jw.autopaystubserver.domain.company.Company
import com.jw.autopaystubserver.model.BaseEntity
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "payroll")
@SQLDelete(sql = "UPDATE payroll SET activation = false WHERE id = ?")
@Where(clause = "activation = true")
class Payroll(
    @Column(nullable = false)
    var excelFileUrl: String,

    @Column
    var name: String?,

    @Column
    var period: Int? = 0,

    @Column
    var year: Int?,

    @Column
    var month: Int?,

    /**
     * period가 month면 order 0.
     * 나머지 경우 order가 해당 월에 지급한 횟수만큼 1부터 차례로 증가.
     */
    @Column(name = "sequence")
    var sequence: Int? = 0,

    @Column
    var activation: Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    var company: Company,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "format_id")
    var payrollFormat: PayrollFormat

) : BaseEntity() {
    // 필요할 때 수정
    //    override fun toString(): String {
//        return "Payroll()"
//    }
//
//    override fun equals(other: Any?): Boolean {
//        return id == (other as Payroll).id
//    }
//
//    override fun hashCode(): Int {
//        return super.hashCode()
//    }

}