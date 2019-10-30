package com.stackoverflow.signup.exceptionhandling;

import com.stackoverflow.signup.exceptions.UserExistException;
import com.stackoverflow.signup.exceptions.UnauthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.lang.reflect.UndeclaredThrowableException;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value
            = {UndeclaredThrowableException.class,IllegalArgumentException.class, IllegalStateException.class , UserExistException.class, NullPointerException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
//       String bodyOfResponse = "Illegal exception";
//        System.out.println(ex.getClass());
//        ex.printStackTrace();
        if(ex.getClass().equals(UserExistException.class))
            return handleExceptionInternal(ex,ex.getMessage(),
                    new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        else if(ex.getClass().equals(ValidationException.class))
            return handleExceptionInternal(ex,ex.getMessage(),
                    new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        else if(ex.getClass().equals(NullPointerException.class))
            return handleExceptionInternal(ex,"Null Pointer Exception",
                    new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value
            = {Throwable.class})
    protected ResponseEntity<Object> validationException(
            Exception ex, WebRequest request) throws Exception {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
