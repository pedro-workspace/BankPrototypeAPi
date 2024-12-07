package com.bankapi.bankapiprototype.dto.responses

import com.bankapi.bankapiprototype.entity.Customer
import java.math.BigDecimal

data class CustomerView(
    val fistName:String,
    val lastName:String,
    val cpf:String,
    val income:BigDecimal,
    val email:String,
    val zipCode:String,
    val street:String,
    val id:Long?
){
    constructor(customer:Customer):this(
        fistName = customer.nome,
        lastName = customer.sobrenome,
        cpf = customer.cpf,
        income = customer.income,
        email = customer.email,
        zipCode = customer.address.zipCode,
        street = customer.address.street,
        id = customer.id
    )
}
