package encora.challenger.api.msproducts.domain;

import encora.challenger.api.msproducts.domain.model.enums.ProductStateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Product {

  private String id;
  private String name;
  private String description;
  private String documentNumber;
  private ProductStateType state;

}
