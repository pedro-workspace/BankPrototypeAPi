package com.bankapi.bankapiprototype.repository

import com.bankapi.bankapiprototype.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface CustomerRepository: JpaRepository<Customer, Long>{
}
