package encora.challenger.api.msproducts.application.mapper;

import encora.challenger.api.msproducts.domain.Product;
import encora.challenger.api.msproducts.domain.model.dto.ProductCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {
  @Mapping(source = "name", target = "name")
  Product toDomain(ProductCreateRequest request);

}
