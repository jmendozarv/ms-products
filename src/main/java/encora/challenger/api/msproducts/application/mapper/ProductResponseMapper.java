package encora.challenger.api.msproducts.application.mapper;

import encora.challenger.api.msproducts.domain.Product;
import encora.challenger.api.msproducts.domain.model.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(target = "documentNumber", source = "documentNumber")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "state", source = "state")
  @Mapping(target = "description", source = "description")
  ProductResponse toDto(Product product);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "documentNumber", source = "documentNumber")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "state", source = "state")
  @Mapping(target = "description", source = "description")
  ProductResponse toProductResponse(Product product);
}
