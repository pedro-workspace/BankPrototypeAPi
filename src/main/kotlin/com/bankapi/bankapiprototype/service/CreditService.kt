package com.bankapi.bankapiprototype.service

import com.bankapi.bankapiprototype.dto.requests.CreditUpdateDto
import com.bankapi.bankapiprototype.entity.Credit
import com.bankapi.bankapiprototype.exceptions.BussinessException
import com.bankapi.bankapiprototype.repositorio.CreditRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

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
    fun findAllByCustomerId(customerId: Long):List<Credit>{
        return this.creditRepository.findAllByCustomerId(customerId)
    }
    fun findById(creditId:Long, customerId:Long): Credit? {
        val credit:Credit? =  this.creditRepository.findByCreditId(creditId)?:throw BussinessException("Credit not found")
        if(credit?.customer?.id == null){
            throw IllegalArgumentException("Customer from credit of credit_id ${credit?.creditId} not found")
        }
        return credit
    }
    fun delete(creditId: Long){

        this.creditRepository.deleteById(creditId.toString().toLong())
    }
    fun update(creditId:Long, creditUpdateDto: CreditUpdateDto):Credit{
        return this.creditRepository.update(creditId, creditUpdateDto.creditValue, creditUpdateDto.numberOfInstallment)
    }
}