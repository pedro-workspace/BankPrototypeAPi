package com.bankapi.bankapiprototype.service

import com.bankapi.bankapiprototype.dto.requests.CustomerDto
import com.bankapi.bankapiprototype.dto.requests.CustomerUpdateDto
import com.bankapi.bankapiprototype.entity.Customer
import com.bankapi.bankapiprototype.exception.BussinessException
import com.bankapi.bankapiprototype.repository.CustomerRepository
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {

    fun save(customer: Customer):Customer{
        return this.customerRepository.save(customer) ?: throw BussinessException("Could not save customer")
    }

    fun findById(customerId:Long):Customer{
        val customer:Customer = this.customerRepository.findById(customerId).get() ?: throw BussinessException("Error, could not find customer")
        return customer
    }

    fun findByName(name:String):List<Customer> {
        val customers:List<Customer> = this.customerRepository.findAll()
        var customerWithNameEquals:MutableList<Customer> = mutableListOf()
        customers.forEach{
            c -> if(name in c.nome){
                customerWithNameEquals.add(c)
        }
        }
        return customerWithNameEquals
    }

    fun findBySurname(surname:String):List<Customer> {
        val customers:List<Customer> = this.customerRepository.findAll()
        var customerWithNameEquals:MutableList<Customer> = mutableListOf()
        customers.forEach{
                c -> if(surname in c.sobrenome){
            customerWithNameEquals.add(c)
        }
        }
        return customerWithNameEquals
    }

    fun update(customerId: Long, customerUpdateDto: CustomerDto):Customer{
        val pastCustomer = this.findById(customerId!!)
        pastCustomer.nome = customerUpdateDto.nome
        pastCustomer.sobrenome = customerUpdateDto.sobrenome
        pastCustomer.cpf = customerUpdateDto.cpf
        pastCustomer.income = customerUpdateDto.income
        pastCustomer.email = customerUpdateDto.email
        pastCustomer.password = customerUpdateDto.password
        pastCustomer.address.zipCode = customerUpdateDto.zipCode
        pastCustomer.address.street = customerUpdateDto.street
        this.save(pastCustomer) ?: throw BussinessException("Customer could not be saved")
        return pastCustomer
    }

    fun deleteById(customerId:Long){
        val customer = findById(customerId)
        this.customerRepository.delete(customer)
    }

//    fun delete(customer:Customer){
//        this.findById(customer.customerId!!) //chamando a função para testar se o customer realmente existe
//        this.customerRepository.delete(customer)
//    }
}