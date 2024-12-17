package com.bankapi.bankapiprototype.dto.responses

import com.bankapi.bankapiprototype.entity.Credit
import com.bankapi.bankapiprototype.enum.Status
import java.math.BigDecimal

data class CreditView(
    val creditCode: Long?,
    val creditValue: BigDecimal,
    val status:Status,
    val numberOfInstallments:Int,
    val emailCustomer:String?,
    val customerIncome:BigDecimal?
){
    constructor(credit: Credit):this(
        creditCode = credit.creditId,
        creditValue = credit.creditValue,
        status = credit.status,
        numberOfInstallments = credit.numberOfInstallments,
        emailCustomer = credit.customer?.email,
        customerIncome = credit.customer?.income
    )
}
