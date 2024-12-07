package com.bankapi.bankapiprototype.dto.requests

import com.bankapi.bankapiprototype.entity.Credit
import jakarta.validation.constraints.*
import java.math.BigDecimal
import java.time.LocalDate

//in case there is any kind of renegotiation
data class CreditUpdateDto(
    @field:NotNull(message = "Credit value not found") val creditValue:BigDecimal,
    @field:Future val dayOneOfInstallment: LocalDate,
    @field:Min(value = 1) @field:Max(value = 48) val numberOfInstallment:Int,
    @field:NotNull(message = "Customer Id not found") val customerId:Long
    ){
    fun toEntity(credit:Credit):Credit{
        credit.creditValue = this.creditValue
        credit.dayFirstInstallment = this.dayOneOfInstallment
        credit.numberOfInstallments = this.numberOfInstallment
        //credit.customer = getCustomerById(this.customerId)
        return credit
    }
}