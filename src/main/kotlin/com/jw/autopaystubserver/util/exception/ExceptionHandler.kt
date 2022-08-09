package com.jw.autopaystubserver.util.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.xml.ws.Response

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(e: BaseException): ResponseEntity<*> {
        println("**************")
        println(e)
        println("**************")

        return ResponseEntity
            .status(e.exceptionResponse.status)
            .body(e.exceptionResponse.body)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleNotReadableException(): ResponseEntity<*> {
        val e = ExceptionResponse.INVALID_BODY_FORMAT

        return ResponseEntity
            .status(e.status)
            .body(e.body)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleNotValidException(e: MethodArgumentNotValidException): ResponseEntity<*> {
        val body = mapOf(
            "error" to mapOf(
                "code" to 100,
                "message" to e.bindingResult.fieldError?.defaultMessage
            )
        )

        return ResponseEntity
            .badRequest()
            .body(body)
    }
}