package com.bankapi.bankapiprototype.entity

import com.bankapi.bankapiprototype.entity.nodes.Address
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "Cliente")
data class Customer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id:Long? = null,
    @Column(nullable=false) var nome:String = "",
    @Column(nullable=false) var sobrenome:String = "",
    @Column(nullable=false, unique = true) var cpf:String = "",
    @Column(nullable=false, unique = true) var email:String = "",
    @Column(nullable=false) var income: BigDecimal = BigDecimal.ZERO,
    @Column(nullable=false) var password:String = "",
    @Column(nullable=false) @Embedded var address: Address,
    @Column(nullable=false) @OneToMany(fetch = FetchType.LAZY,
        cascade = arrayOf(CascadeType.REMOVE, CascadeType.PERSIST), mappedBy = "customer")
        var credits:List<Credit> = mutableListOf()
    )