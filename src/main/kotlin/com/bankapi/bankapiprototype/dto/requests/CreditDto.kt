package com.bankapi.bankapiprototype.dto.requests

import com.bankapi.bankapiprototype.entity.Credit
import com.bankapi.bankapiprototype.entity.Customer
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import java.math.BigDecimal
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Future
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "Invalid credit value") val creditValue:BigDecimal,
    @field:Future val dayOneOfInstallment: LocalDate,
    @field:Min(value = 1) @field:Max(value = 48) val numberOfInstallment: Int,
    @field:NotNull(message = "Id não identificado") val customerId:Long
){
    fun toEntity():Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayOneOfInstallment,
        numberOfInstallments = this.numberOfInstallment,
        //customer = Customer from customerId
        //  fetch it from the database
    )
}
