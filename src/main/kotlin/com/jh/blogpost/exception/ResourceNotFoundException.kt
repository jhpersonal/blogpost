package com.jh.blogpost.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
class APIExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(BaseAPIException::class)])
    fun handleConflict(exception: BaseAPIException, request: WebRequest): ResponseEntity<Any> {
        println("Handle")
        return handleExceptionInternal(exception, "Response Body", HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }
}

class BaseAPIException(override val message: String?) : Exception(message)



// OR - another approach
@ControllerAdvice
class ControllerAdviceRequestError : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [(UserAlreadyExistsException::class)])
    fun handleUserAlreadyExists(ex: UserAlreadyExistsException,request: WebRequest): ResponseEntity<ErrorsDetails> {
        val errorDetails = ErrorsDetails(Date(),
            "Validation Failed",
            ex.message!!
        )
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }
}
class UserAlreadyExistsException(override val message: String?) : Exception(message)

data class ErrorsDetails(val time: Date, val message: String, val details: String)