package com.bankapi.bankapiprototype.service

import com.bankapi.bankapiprototype.entity.Credit
import com.bankapi.bankapiprototype.entity.Customer
import com.bankapi.bankapiprototype.exceptions.BussinessException
import com.bankapi.bankapiprototype.repositorio.CreditRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class CreditService(
    private val creditRepository:CreditRepository,
    private val customerService: CustomerService) {
    fun validateDayFirstDayOfInstallment(dayOfInstallment: LocalDate):Boolean{
        return if(dayOfInstallment.isBefore(LocalDate.now().plusMonths(3))) true
                else throw BussinessException("Invalid date of installment")
    }
    fun save(credit:Credit):Credit{
        this.validateDayFirstDayOfInstallment(credit.dayFirstInstallment)
        credit.customer = customerService.findById(credit.customer?.id!!)
        return creditRepository.save(credit)
    }
    fun findAllByCustomer(customerId: Long):List<Credit>{
        return this.creditRepository.findAllByCustomerId(customerId)
    }
    fun findById(creditId:Long): Optional<Credit> {
        return this.creditRepository.findById(creditId)
    }
    fun delete(creditId: Long){

        this.creditRepository.deleteById(creditId)
    }
}