package com.bankapi.bankapiprototype.service

import com.bankapi.bankapiprototype.dto.requests.CreditDto
import com.bankapi.bankapiprototype.dto.requests.CreditUpdateDto
import com.bankapi.bankapiprototype.entity.Credit
import com.bankapi.bankapiprototype.exception.BussinessException
import com.bankapi.bankapiprototype.repository.CreditRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CreditService(
    private val creditRepository:CreditRepository,
    private val customerService: CustomerService) {

    fun validateDayFirstDayOfInstallment(dayOfInstallment: LocalDate):Boolean{
        return if(dayOfInstallment.isBefore(LocalDate.now())) true
                else throw BussinessException("Invalid date of installment")
    }

    fun save(creditDto:CreditDto):Credit{
        val credit:Credit = Credit(creditValue = creditDto.creditValue,
            numberOfInstallments = creditDto.numberOfInstallment)
        this.validateDayFirstDayOfInstallment(credit.dayFirstInstallment)
        credit.customer = customerService.findById(creditDto.customerId)
        return creditRepository.save(credit)
    }

    fun findCreditsByCustomerId(customerId: Long):List<Credit>{
        val credits:List<Credit> = this.creditRepository.findAll() ?: throw BussinessException("Could not find credits")
        var wantedCredits:MutableList<Credit> = mutableListOf()
        credits.forEach{
            c -> if(customerId.equals(c.customer!!.customerId)){
                wantedCredits.add(c)
            }
        }
        return wantedCredits
    }

    fun findById(creditId:Long): Credit? {
        val credit:Credit? =  this.creditRepository.findById(creditId).get() ?: throw BussinessException("Credit not found")
        return credit
    }

    fun delete(creditId: Long){
        this.creditRepository.deleteById(creditId)
    }

    fun update(creditId:Long, creditUpdateDto: CreditDto):Credit{
        val credit = this.findById(creditId)
        credit!!.creditValue = creditUpdateDto.creditValue
        credit.customer = this.customerService.findById(creditUpdateDto.customerId)
        credit.status = creditUpdateDto.status
        credit.numberOfInstallments = creditUpdateDto.numberOfInstallment
        this.creditRepository.save(credit)
        return credit
    }
}