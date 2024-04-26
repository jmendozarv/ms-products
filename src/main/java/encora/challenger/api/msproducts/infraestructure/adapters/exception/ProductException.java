package encora.challenger.api.msproducts.infraestructure.adapters.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductException extends RuntimeException {
  private HttpStatus errorCode;
  private String errorMessage;

  public ProductException(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
