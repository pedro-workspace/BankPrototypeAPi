package com.bankapi.bankapiprototype.controllers

import com.bankapi.bankapiprototype.dto.requests.CustomerDto
import com.bankapi.bankapiprototype.dto.responses.CustomerView
import com.bankapi.bankapiprototype.entity.Customer
import com.bankapi.bankapiprototype.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/customers")
class CustomerController(private val customerService: CustomerService) {
    @PostMapping()
    fun saveCustomer(@RequestParam(value = "customer") customer:CustomerDto){
        this.customerService.save(customer.toEntity())
    }

    @GetMapping("/{customerId}")
    fun findById(customerId:Long):ResponseEntity<CustomerView>{
        val customer = this.customerService.findById(customerId)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer))
    }

    @GetMapping("/{nome}")
    fun findByFirstName(@RequestParam(value = "nome") nome:String):ResponseEntity<List<CustomerView>>{
        val customersView = this.customerService.findManyByName(nome).stream().map{
            customer:Customer -> CustomerView(customer)
        }.collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(customersView)
    }
}