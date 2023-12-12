package com.eazybytes.cards.exception;

import com.eazybytes.cards.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto>handleGlobalException(Exception ex ,
                                                                 WebRequest webRequest){
        ErrorResponseDto errorResponseDto =new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return
             new ResponseEntity<>(errorResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>handleResourceNotFoundException(ResourceNotFoundException ex ,
                                                                           WebRequest webRequest){
        ErrorResponseDto errorResponseDto=new ErrorResponseDto(
          webRequest.getDescription(false),
          HttpStatus.NOT_FOUND,
          ex.getMessage(),
          LocalDateTime.now()
        );
        return  new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CardAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto>handleCardAlreadyExistException(CardAlreadyExistsException exception,
                                                                           WebRequest webRequest){
        ErrorResponseDto errorResponseDto=new ErrorResponseDto(
          webRequest.getDescription(false),
          HttpStatus.BAD_REQUEST,
          exception.getMessage(),
          LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }
}
