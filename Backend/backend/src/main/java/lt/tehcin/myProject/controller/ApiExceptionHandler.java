package lt.tehcin.myProject.controller;

import lt.tehcin.myProject.dto.ErrorDto;
import lt.tehcin.myProject.dto.ErrorFieldDto;
import lt.tehcin.myProject.exception.ClientValidationException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.servlet.http.HttpServletRequest;
//import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(ClientValidationException.class)
    public ResponseEntity<ErrorDto> handleClientValidationException(HttpServletRequest request, ClientValidationException clientValidationException) {
        logger.error("ClientValidationException: {}, for field: {}", clientValidationException.getMessage(), clientValidationException.getField());

        var errorStatus = HttpStatus.BAD_REQUEST;

        var errorFields = List.of(
                new ErrorFieldDto(clientValidationException.getField(), clientValidationException.getError(), clientValidationException.getRejectedValue())
        );

        var errorDto = new ErrorDto(request.getRequestURL().toString(),
                errorFields,
                clientValidationException.getMessage(),
                errorStatus.value(),
                errorStatus.getReasonPhrase(),
                request.getRequestURL().toString(),
                LocalDateTime.now());
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleClientValidationException(ConstraintViolationException exception) {
        logger.error("ClientValidationException: {}, for field: {}", exception.getMessage(), exception.getCause());

        var errorStatus = HttpStatus.BAD_REQUEST;

        var errorDto = new ErrorDto(
                errorStatus.value(),
                exception.getMessage());
        return ResponseEntity.badRequest().body(errorDto);
    }

}
