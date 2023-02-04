package com.example.FintechApplication.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler
  public ResponseEntity<ErrorInfo> handleErrors(HttpServletRequest request, Exception exception){
    String errorMessage = exception.getMessage();
    ErrorInfo error = new ErrorInfo(errorMessage, request.getRequestURL().toString());
    System.out.println(errorMessage + exception);

    if(exception instanceof CustomerNotFoundException){
      return ResponseEntity.status(CustomerNotFoundException.class.cast(exception).getErrorStatus()).body(error);
    } else if(exception instanceof WrongDateFormatException){
      return ResponseEntity.status(WrongDateFormatException.class.cast(exception).getErrorStatus()).body(error);
    } else if(exception instanceof BankAccountNotFoundException){
      return ResponseEntity.status(BankAccountNotFoundException.class.cast(exception).getErrorStatus()).body(error);
    } else if(exception instanceof TransactionNotFoundException){
      return ResponseEntity.status(TransactionNotFoundException.class.cast(exception).getErrorStatus()).body(error);
    } else if(exception instanceof CreditNotFoundException){
      return ResponseEntity.status(CreditNotFoundException.class.cast(exception).getErrorStatus()).body(error);
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
  }
@AllArgsConstructor
@Getter
  public static class ErrorInfo{
    String error;
    String path;
  }
}
