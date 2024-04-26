package encora.challenger.api.msproducts.infraestructure.adapters.mapper;

import encora.challenger.api.msproducts.domain.Product;
import encora.challenger.api.msproducts.infraestructure.adapters.entities.ProductEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductDboMapper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "description", target = "description")
  @Mapping(source = "documentNumber", target = "documentNumber")
  @Mapping(source = "state", target = "state")
  ProductEntity toDbo(Product product);

  @InheritConfiguration
  Product toDomain(ProductEntity productEntity);
}
