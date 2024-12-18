package com.bankapi.bankapiprototype.controllers

import com.bankapi.bankapiprototype.dto.requests.CustomerDto
import com.bankapi.bankapiprototype.dto.requests.CustomerUpdateDto
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
    @PostMapping
    fun saveCustomer(@RequestParam(value = "customerDto") customerDto:CustomerDto){
        this.customerService.save(customerDto.toEntity())
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

    @GetMapping("/{sobrenome}")
    fun findByLastName(@RequestParam(value = "sobrenome") sobrenome:String):ResponseEntity<List<CustomerView>>{
        val customerView = this.customerService.findManyBySurname(sobrenome).stream().map{
            customer:Customer -> CustomerView(customer)
        }.collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(customerView)
    }

    @PatchMapping()
    fun updateCustomer(@RequestParam(value = "customerId") customerId:Long,
                       @RequestParam(value = "customerNewData") customerNewData: CustomerUpdateDto){
        this.customerService.update(customerId, customerNewData)
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@RequestParam(value = "customerId") customerId:Long){
        this.customerService.delete(customerId)
    }

}