package com.example.please.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ClientErrorResponse> handlerException(ClientNotFoundException exception){
        ClientErrorResponse error = new ClientErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ClientErrorResponse> handlerException(Exception exception){


        ClientErrorResponse error = new ClientErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ClientErrorResponse> handlerException(UnprocessableEntityException exception){


        ClientErrorResponse error = new ClientErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                exception.getMessage());

        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
