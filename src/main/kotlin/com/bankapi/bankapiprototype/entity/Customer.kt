package com.bankapi.bankapiprototype.entity

import com.bankapi.bankapiprototype.entity.nodes.Address
import jakarta.persistence.Entity
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.UUID

@Entity
data class Customer(
    @Id @Column(name = "CUSTOMER_ID") var customerId:Long? = null,
    @Column(nullable=false, name = "NOME") var nome:String = "",
    @Column(nullable=false, name = "SOBRENOME") var sobrenome:String = "",
    @Column(nullable=false, unique = true, name = "CPF") var cpf:String = "",
    @Column(nullable=false, unique = true, name = "EMAIL") var email:String = "",
    @Column(nullable=false, name = "INCOME") var income: BigDecimal = BigDecimal.ZERO,
    @Column(nullable=false, name = "PASSWORD") var password:String = "",
    @Column(nullable=false, name = "ZIP_CODE") var zipCode:String = "",
    @Column(nullable=false, name = "STREET") var street:String = "",
    @OneToMany(fetch = FetchType.LAZY,
        cascade = arrayOf(CascadeType.REMOVE, CascadeType.PERSIST), mappedBy = "customer")
        var credits:MutableList<Credit> = mutableListOf()
    ){
    init {
        customerId = Math.abs(hashCode().toLong())
    }
}