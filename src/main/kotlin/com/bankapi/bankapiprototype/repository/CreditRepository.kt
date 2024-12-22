package com.bankapi.bankapiprototype.repository

import com.bankapi.bankapiprototype.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface CreditRepository: JpaRepository<Credit, Long> {
    @Query("SELECT * FROM CREDIT WHERE CUSTOMER_ID = ?1", nativeQuery = true)
    fun findByCreditId(creditId: Long):Credit?
    @Query(value = "SELECT * FROM CREDIT WHERE CUSTOMER_ID = ?1", nativeQuery = true)
    fun findAllByCustomerId(customerId:Long):List<Credit>
    @Query("UPDATE credit" +
            "SET CREDIT_VALUE = ?2, NUMBER_OF_INSTALLMENTS = ?3" +
            "WHERE CREDIT_ID = ?1", nativeQuery = true)
    fun update(creditId: Long, creditValue:BigDecimal, numberOfInstallments:Int):Credit
}