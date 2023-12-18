package se.implementer.newsservice.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.logging.Logger

@ControllerAdvice
class GlobalExceptionHandler {

    private val logger = Logger.getLogger(GlobalExceptionHandler::class.java.name)
    @ExceptionHandler
    fun handleGlobalException(throwable: Throwable): ResponseEntity<ResponseError> {
        logger.severe("error: ${throwable.message}")
        return ResponseEntity(
            ResponseError(
                errorMessage = throwable.message,
                cause = throwable.cause
            ),null, HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}

data class ResponseError(
    val errorMessage: String?,
    val cause: Throwable?,
)