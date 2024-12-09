package com.bankapi.bankapiprototype.repositorio

import com.bankapi.bankapiprototype.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, Long>{
    @Query("SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ?1")
    fun findCustomerById(customerId: Long)
}
