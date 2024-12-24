package com.bankapi.bankapiprototype.dto.requests

import com.bankapi.bankapiprototype.entity.Credit
import com.bankapi.bankapiprototype.entity.Customer
import com.bankapi.bankapiprototype.enum.Status
import jakarta.validation.constraints.*
import java.math.BigDecimal

//in case there is any kind of renegotiation
data class CreditUpdateDto(
    @field:NotNull(message = "Credit value not found") val creditValue:BigDecimal,
    @field:Min(value = 1) @field:Max(value = 48) val numberOfInstallment:Int,
    @field:NotNull(message = "Customer Id not found") val customerId:Long,
    @field:NotNull(message = "Status not found") val status: Status
    )