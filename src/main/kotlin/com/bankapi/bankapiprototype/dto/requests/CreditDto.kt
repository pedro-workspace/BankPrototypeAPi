package com.bankapi.bankapiprototype.dto.requests

import com.bankapi.bankapiprototype.entity.Credit
import com.bankapi.bankapiprototype.entity.Customer
import com.bankapi.bankapiprototype.enum.Status
import jakarta.validation.constraints.*
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "Invalid credit value") val creditValue:BigDecimal,
    @field:Min(value = 1) @field:Max(value = 48) val numberOfInstallment: Int,
    @field:NotEmpty(message = "Status not found") val status: Status = Status.IN_PROGRESS,
    @field:NotNull(message = "Id not identified") val customerId:Long
)
