package com.bankapi.bankapiprototype.entity

import com.bankapi.bankapiprototype.enum.Status
import jakarta.persistence.*
import java.lang.Long.hashCode
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
data class Credit(
    @Column(nullable = false) var creditValue:BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) var dayFirstInstallment:LocalDate = LocalDate.now(),
    @Column(nullable = false) var numberOfInstallments:Int = 0,
    @Enumerated var status: Status = Status.IN_PROGRESS,
    @ManyToOne var customer:Customer? = null,
    @Id @Column(name = "CREDIT_ID") var creditId:Long? = null
    ){
    init {
        creditId = Math.abs(hashCode().toLong())
    }
}