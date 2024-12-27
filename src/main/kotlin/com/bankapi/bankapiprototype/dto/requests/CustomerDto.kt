package com.bankapi.bankapiprototype.dto.requests

import com.bankapi.bankapiprototype.entity.Customer
import com.bankapi.bankapiprototype.entity.nodes.Address
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
    @field:NotEmpty(message = "First name not found") val nome:String,
    @field:NotEmpty(message = "Last name not found") val sobrenome:String,
    @field:NotEmpty(message = "CPF found empty")
    @field:CPF(message = "Not a valid CPF") val cpf:String,
    @field:Email(message = "Invalid e-mail")
    @field:NotEmpty(message = "E-mail not found") val email:String,
    @field:NotNull(message = "Income not found") val income:BigDecimal = BigDecimal.ZERO,
    @field:NotEmpty(message = "Password not found") val password:String,
    @field:NotEmpty(message = "Zipcode not found") val zipCode:String,
    @field:NotEmpty(message = "Steet not found") val street:String
){
    fun toEntity():Customer = Customer(
        customerId = hashCode().toLong(),
        nome = this.nome,
        sobrenome =  this.sobrenome,
        cpf = this.cpf,
        email = this.email,
        income = this.income,
        password = this.password,
        zipCode = this.zipCode,
        street = this.street
    )
}
