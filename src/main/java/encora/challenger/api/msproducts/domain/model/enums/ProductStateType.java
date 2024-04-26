package encora.challenger.api.msproducts.domain.model.enums;

import encora.challenger.api.msproducts.domain.model.constants.ProductConstant;
import lombok.Getter;

@Getter
public enum ProductStateType {
  ACTIVE( "ACTIVE"),
  INACTIVE("INACTIVE");

  private final String code;

  ProductStateType(String code) {
    this.code = code;
  }

  public static ProductStateType fromCode(String code) {
    for (ProductStateType type : ProductStateType.values()) {
      if (type.getCode().equals(code)) {
        return type;
      }
    }
    throw new IllegalArgumentException(
        String.format(ProductConstant.PRODUCT_STATE_NOT_FOUND, code));
  }
}
