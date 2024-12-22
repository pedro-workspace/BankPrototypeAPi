package com.bankapi.bankapiprototype.controller

import com.bankapi.bankapiprototype.dto.requests.CreditDto
import com.bankapi.bankapiprototype.dto.requests.CreditUpdateDto
import com.bankapi.bankapiprototype.dto.responses.CreditView
import com.bankapi.bankapiprototype.entity.Credit
import com.bankapi.bankapiprototype.exception.BussinessException
import com.bankapi.bankapiprototype.service.CreditService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/credits")
class CreditController(private val creditService:CreditService) {
    @PostMapping
    fun saveCredit(@RequestBody creditDto:CreditDto):ResponseEntity<String>{
        val credit: Credit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(
            "Credit ${credit.creditId} from Customer ${credit.customer?.email} saved."
        )
    }

    @GetMapping("/{customerId}")
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId:Long):ResponseEntity<List<CreditView>>{
        val creditsView = this.creditService.findAllByCustomerId(customerId).
                stream().map{
                    credit:Credit -> CreditView(credit)
        }.collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(creditsView)
    }

    @GetMapping("/{creditId}")
    fun findByCreditId(@RequestParam(value = "creditId") creditId: Long,
                       @PathVariable customerId:Long):ResponseEntity<CreditView>?{
        val credit:Credit? = this.creditService.findById(creditId, customerId)
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit!!))?: throw BussinessException("Credit not found")
    }

    @PatchMapping("/update/{creditId}")
    fun updateCredit(@RequestParam(value = "creditId") creditId:Long,
                     @RequestBody @Valid creditUpdateDto: CreditUpdateDto):ResponseEntity<CreditView>{
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(this.creditService.update(creditId, creditUpdateDto)))
    }

    @DeleteMapping("/delete/{creditId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCredit(@RequestParam(value = "creditId") creditId:Long){
        this.creditService.delete(creditId)
    }
}