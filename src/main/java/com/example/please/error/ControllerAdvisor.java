package com.example.please.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.RequestDispatcher;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{



    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Object> handlerExceptionClientNotFound(ClientNotFoundException exception,WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", HttpStatus.NOT_FOUND.value());
        body.put("message", "Client not found");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handlerExceptionAccNotFound(AccountNotFoundException exception, WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", HttpStatus.NOT_FOUND.value());
        body.put("message", "Account not found");
        return handleExceptionInternal(exception, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<Object> handlerExceptionPaymentNotFound(PaymentNotFoundException exception,WebRequest request){
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.NOT_FOUND, RequestAttributes.SCOPE_REQUEST);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", HttpStatus.NOT_FOUND.value());
        body.put("message", "Payment not found");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoDateFoundException.class)
    public ResponseEntity<Object> handleNodataFoundException(
            NoDateFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", HttpStatus.NOT_FOUND.value());
        body.put("message", "No persons found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<Object> handlerException(UnprocessableEntityException exception){

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", HttpStatus.UNPROCESSABLE_ENTITY.value());
        body.put("message", "Person not found");

        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerException(Exception exception){

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", HttpStatus.BAD_REQUEST.value());
        body.put("message", "Person not found");


        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
