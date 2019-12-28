package com.noetic.codetest.util.exception;

import com.noetic.codetest.util.CodetestResponse;
import com.noetic.codetest.util.CodetestResponseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Kasun Thilina
 * Date    : 12/28/2019
 * Project : codetest
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity toResponse(ConstraintViolationException ex) {
        CodetestResponse<Object, Map<String, String>> response = new CodetestResponse<>();
        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().forEach((error) -> {
            String fieldName = error.getPropertyPath().toString();
            String errorMessage = error.getMessage();
            errors.put(fieldName, errorMessage);
        });

        CodetestResponseMapper.build(response, 406, null, errors);
        log.error(errors.toString(), ex);
        return new ResponseEntity(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<?> recordNotFoundException(Throwable ex) {
        CodetestResponse<Object, String> response = new CodetestResponse<>();
        CodetestResponseMapper.build(response, 404, null, ex.getMessage());
        log.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RecordAlreadyExistsException.class)
    public ResponseEntity<?> recordAlreadyExistsException(Throwable ex) {
        CodetestResponse<Object, String> response = new CodetestResponse<>();
        CodetestResponseMapper.build(response, 409, null, ex.getMessage());
        log.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
