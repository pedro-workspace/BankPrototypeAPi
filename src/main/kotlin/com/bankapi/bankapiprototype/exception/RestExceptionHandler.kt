package com.bankapi.bankapiprototype.exception

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerValidException(exception:MethodArgumentNotValidException): ResponseEntity<ExceptionDetail>{
        val errors:MutableMap<String, String?> = HashMap()
        exception.bindingResult.allErrors.forEach{
            erro: ObjectError ->
            val fieldName:String = (erro as FieldError).field
            val messageError: String? = erro.defaultMessage
            errors[fieldName] = messageError
        }
        return ResponseEntity(
            ExceptionDetail(
                title = "Bad request, consult documentation",
                status = HttpStatus.BAD_REQUEST.value(),
                exceptionName = exception.javaClass.toString(),
                details = errors
            ), HttpStatus.BAD_REQUEST
        )
    }
    @ExceptionHandler(DataAccessException::class)
    fun handlerValidException(exception: DataAccessException):ResponseEntity<ExceptionDetail>{
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionDetail(
            title = "Conflict found, consult documentation",
            status = HttpStatus.CONFLICT.value(),
            exceptionName = exception.javaClass.toString(),
            details = mutableMapOf(exception.cause.toString() to exception.message)
        ))
    }
    @ExceptionHandler(BussinessException::class)
    fun handlerValidException(exception:BussinessException):ResponseEntity<ExceptionDetail>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ExceptionDetail(
                title = "BusinessException Occurred, consult documentation",
                status = HttpStatus.BAD_REQUEST.value(),
                exceptionName = exception.javaClass.toString(),
                details = mutableMapOf(exception.cause.toString() to exception.message)
            )
        )
    }
    @ExceptionHandler(IllegalArgumentException::class)
    fun handlerValidException(exception:IllegalArgumentException):ResponseEntity<ExceptionDetail>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ExceptionDetail(
                title = "Illegal argument found, consult documentation",
                status = HttpStatus.BAD_REQUEST.value(),
                exceptionName = exception.javaClass.toString(),
                details = mutableMapOf(exception.cause.toString() to exception.message)
            )
        )
    }
}