package encora.challenger.api.msproducts.infraestructure.rest.advice;

import encora.challenger.api.msproducts.infraestructure.adapters.exception.ErrorResponse;
import encora.challenger.api.msproducts.infraestructure.adapters.exception.ProductException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductControllerAdvice {
  @ExceptionHandler(ProductException.class)
  public ResponseEntity<ErrorResponse> handleAccountTransactionException(ProductException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().toString(), ex.getErrorMessage());
    return new ResponseEntity<>(errorResponse, ex.getErrorCode());
  }
}
