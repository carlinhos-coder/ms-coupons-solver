package co.com.bancolombia.api.exceptions;

import co.com.bancolombia.api.dto.ErrorDTO;
import co.com.bancolombia.exceptions.CouponsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptions {

    @ExceptionHandler(value = CouponsException.class)
    public ResponseEntity<ErrorDTO> exceptions(CouponsException exception){
        ErrorDTO error = ErrorDTO.builder().message(exception.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
