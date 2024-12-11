package com.bankapi.bankapiprototype.service

import com.bankapi.bankapiprototype.dto.requests.CustomerUpdateDto
import com.bankapi.bankapiprototype.entity.Customer
import com.bankapi.bankapiprototype.exceptions.BussinessException
import com.bankapi.bankapiprototype.repositorio.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {
    fun save(customer: Customer): Customer{
        return this.customerRepository.save(customer)
    }
    fun findById(customerId: Long):Customer{
        return this.customerRepository.findById(customerId).orElse(throw BussinessException("Id $customerId not found"))
    }
    fun findManyByName(customerName:String):List<Customer>{
        val customerList:List<Customer> = this.customerRepository.findManyByName(customerName)
        return customerList
    }
    fun findManyBySurname(customerSurname:String):List<Customer>{
        return this.customerRepository.findManyBySurname(customerSurname)
    }
    fun delete(customerId: Long){
        val customer:Customer = this.findById(customerId)
        this.customerRepository.delete(customer)
    }
    fun update(customerId:Long, customerNewData:CustomerUpdateDto){
        this.customerRepository.update(customerId, customerNewData.firstName,
            customerNewData.lastName, customerNewData.income, customerNewData.zipCode, customerNewData.street)
    }
}