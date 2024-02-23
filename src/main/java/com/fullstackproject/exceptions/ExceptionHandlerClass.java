package com.fullstackproject.exceptions;

import com.fullstackproject.payload.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerClass {
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorInfo> handle404(EmployeeNotFoundException exception){
        ErrorInfo build = ErrorInfo.builder().errorMessage(exception.getMessage()).toContact("vikas@gmail").timeStamp(LocalDateTime.now()).build();
        return  new ResponseEntity<>(build, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleGlobalException(Exception exception){
        ErrorInfo build = ErrorInfo.builder().errorMessage(exception.getMessage()).toContact("vikas@gmail").timeStamp(LocalDateTime.now()).build();
        return  new ResponseEntity<>(build, HttpStatus.BAD_REQUEST);
    }
     @ExceptionHandler(EmployeeAlreadyExistException.class)
    public ResponseEntity<ErrorInfo> handleEmployeeAlreadyExist(EmployeeAlreadyExistException exception){
        ErrorInfo build = ErrorInfo.builder().errorMessage(exception.getMessage()).toContact("vikas@gmail").timeStamp(LocalDateTime.now()).build();
        return new ResponseEntity<>(build,HttpStatus.BAD_REQUEST);}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> handleValidationException(MethodArgumentNotValidException exception){
       String errormessage= exception.getBindingResult().getAllErrors().stream().map(ex->ex.getDefaultMessage()).collect(Collectors.joining(","));
        ErrorInfo build = ErrorInfo.builder().errorMessage(errormessage).toContact("vikas@gmail").timeStamp(LocalDateTime.now()).build();
        return new ResponseEntity<>(build,HttpStatus.BAD_REQUEST);}
}
