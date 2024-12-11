package com.bankapi.bankapiprototype.repositorio

import com.bankapi.bankapiprototype.dto.requests.CustomerUpdateDto
import com.bankapi.bankapiprototype.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface CustomerRepository: JpaRepository<Customer, Long>{
    @Query("SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ?1")
    fun findCustomerById(customerId: Long):Customer
    @Query("UPDATE CUSTOMER" +
            "SET NOME = ?2, SOBRENOME = ?3, INCOME = ?4, ZIPCODE = ?5, STREET = ?6" +
            "WHERE CUSTOMER_ID = ?1")
    fun update(customerId: Long, nome:String, sobrenome:String, income: BigDecimal, zipCode:String, street:String)
    @Query("SELECT * FROM CUSTOMER WHERE NOME like '%?1%'")
    fun findManyByName(customerName:String):List<Customer>
    @Query("SELECT * FROM CUSTOMER WHERE SOBRENOME like '%?1%'")
    fun findManyBySurname(customerSurname:String):List<Customer>
}
