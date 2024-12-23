package com.bankapi.bankapiprototype.dto.requests

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import com.bankapi.bankapiprototype.entity.Customer
import com.bankapi.bankapiprototype.entity.nodes.Address


data class CustomerUpdateDto(
    @field:NotEmpty(message = "First name is empty") val firstName:String,
    @field:NotEmpty(message = "Last name is empty") val lastName:String,
    @field:NotEmpty(message = "E-mail not found") val email:String,
    @field:NotNull(message = "Income value not valid") val income: BigDecimal,
    @field:NotEmpty(message = "Not a valid zip code") val zipCode:String,
    @field:NotEmpty(message = "Not a valid street value") val street:String
){
    fun toEntity():Customer = Customer(
        nome = this.firstName,
        sobrenome = this.lastName,
        email = this.email,
        income = this.income,
        address = Address(this.zipCode, this.street)
    )
}
