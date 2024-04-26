package encora.challenger.api.msproducts.application.services;

import encora.challenger.api.msproducts.application.mapper.ProductRequestMapper;
import encora.challenger.api.msproducts.application.mapper.ProductResponseMapper;
import encora.challenger.api.msproducts.application.usecases.ProductService;
import encora.challenger.api.msproducts.domain.model.constants.ProductConstant;
import encora.challenger.api.msproducts.domain.model.dto.ProductCreateRequest;
import encora.challenger.api.msproducts.domain.model.dto.ProductPatchRequest;
import encora.challenger.api.msproducts.domain.model.dto.ProductResponse;
import encora.challenger.api.msproducts.domain.model.dto.ProductUpdateRequest;
import encora.challenger.api.msproducts.domain.model.enums.ProductStateType;
import encora.challenger.api.msproducts.domain.port.ProductPersistencePort;
import encora.challenger.api.msproducts.infraestructure.adapters.exception.ProductException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductManagementService implements ProductService {

  private final ProductPersistencePort productPersistencePort;
  private final ProductResponseMapper productResponseMapper;
  private final ProductRequestMapper productRequestMapper;


  @Override
  public Mono<ProductResponse> createProduct(ProductCreateRequest productCreateRequest) {
    return Mono.just(productCreateRequest)
        .map(productRequestMapper::toDomain)
        .flatMap(productPersistencePort::create)
        .map(productResponseMapper::toProductResponse);
  }

  @Override
  public Mono<Optional<ProductResponse>> getProductById(String productId) {
    return productPersistencePort.getById(productId)
        .map(productResponseMapper::toProductResponse)
        .map(Optional::of)
        .defaultIfEmpty(Optional.empty());
  }

  @Override
  public Mono<ProductResponse> updateProduct(String productId,
      ProductUpdateRequest productUpdate) {
    return productPersistencePort.getById(productId)  // Obtenemos el producto por ID
        .flatMap(existingProduct -> {
          // Actualizamos el producto con los valores del ProductUpdateRequest
          existingProduct.setName(productUpdate.getName());
          existingProduct.setDescription(productUpdate.getDescription());
          existingProduct.setDocumentNumber(productUpdate.getDocumentNumber());
          existingProduct.setState(ProductStateType.fromCode(productUpdate.getState()));

          return productPersistencePort.update(
              existingProduct);  // Guardamos el producto actualizado
        })
        .map(productResponseMapper::toProductResponse)  // Convertimos a ProductResponse
        .switchIfEmpty(Mono.error(new ProductException(
            String.format(ProductConstant.CURRENT_PRODUCT_NOT_FOUND,
                productId))));  // Error si no se encuentra el producto
  }

  @Override
  public Mono<ProductResponse> patchProduct(String productId,
      ProductPatchRequest productPatchRequest) {
    return productPersistencePort.getById(productId)  // Obtiene el producto por ID
        .flatMap(existingProduct -> {
          // Aplicar actualizaciones parciales
          if (productPatchRequest.getName() != null) {
            existingProduct.setName(productPatchRequest.getName());
          }
          if (productPatchRequest.getDescription() != null) {
            existingProduct.setDescription(productPatchRequest.getDescription());
          }
          if (productPatchRequest.getDocumentNumber() != null) {
            existingProduct.setDocumentNumber(productPatchRequest.getDocumentNumber());
          }
          if (productPatchRequest.getState() != null) {
            existingProduct.setState(ProductStateType.fromCode(productPatchRequest.getState()));
          }

          return productPersistencePort.update(existingProduct);  // Guardar producto actualizado
        })
        .map(productResponseMapper::toProductResponse)  // Mapear a ProductResponse
        .switchIfEmpty(Mono.error(new ProductException(
            String.format(ProductConstant.CURRENT_PRODUCT_NOT_FOUND,
                productId))));  // Error si el producto no existe
  }

  @Override
  public Flux<ProductResponse> getActiveProductsByCustomer(String customerId,String state) {
    return productPersistencePort.getActiveProductsByCustomerId(customerId,state)
        .map(productResponseMapper::toDto);
  }

  @Override
  public Flux<ProductResponse> getAllProducts() {
    return productPersistencePort.getAll().map(productResponseMapper::toDto);
  }

  @Override
  public Mono<Void> deleteProduct(String productId) {
    return productPersistencePort.getById(productId)
        .flatMap(product -> productPersistencePort.deleteById(product.getId()));
  }
}
