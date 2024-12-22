package com.bankapi.bankapiprototype.entity

import com.bankapi.bankapiprototype.entity.nodes.Address
import jakarta.persistence.Entity
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.UUID

@Entity
data class Customer(
    @Id @Column var customerId:Long? = null,
    @Column(nullable=false) var nome:String = "",
    @Column(nullable=false) var sobrenome:String = "",
    @Column(nullable=false, unique = true) var cpf:String = "",
    @Column(nullable=false, unique = true) var email:String = "",
    @Column(nullable=false) var income: BigDecimal = BigDecimal.ZERO,
    @Column(nullable=false) var password:String = "",
    @Column(nullable=false) @Embedded var address: Address,
    @Column(nullable=false) @OneToMany(fetch = FetchType.LAZY,
        cascade = arrayOf(CascadeType.REMOVE, CascadeType.PERSIST), mappedBy = "customer")
        var credits:MutableList<Credit> = mutableListOf()
    ){
    init {
        customerId = hashCode().toLong()
    }
}