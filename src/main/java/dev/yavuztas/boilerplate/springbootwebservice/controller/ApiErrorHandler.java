package dev.yavuztas.boilerplate.springbootwebservice.controller;

import dev.yavuztas.boilerplate.springbootwebservice.exception.UsernameNotFoundException;
import dev.yavuztas.boilerplate.springbootwebservice.view.ApiErrorView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Error handler for our api business logic errors.
 *
 * @author Yavuz Tas
 */
@ControllerAdvice
@RestController
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<ApiErrorView> handleNotFoundException(UsernameNotFoundException exception) {
        ApiErrorView apiError = new ApiErrorView(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}