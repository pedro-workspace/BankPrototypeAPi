package com.bankapi.bankapiprototype.entity

import com.bankapi.bankapiprototype.enum.Status
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
data class Credit(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(nullable = false, unique = true) val creditId:Long? = null,
    @Column(nullable = false) var creditValue:BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) var dayFirstInstallment:LocalDate,
    @Column(nullable = false) var numberOfInstallments:Int = 0,
    @Enumerated var status: Status = Status.IN_PROGRESS,
    @ManyToOne var customer:Customer? = null,
    //@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id:Long? = null
    )