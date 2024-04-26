package encora.challenger.api.msproducts.infraestructure.adapters.repository;

import encora.challenger.api.msproducts.domain.Product;
import encora.challenger.api.msproducts.domain.model.constants.ProductConstant;
import encora.challenger.api.msproducts.domain.port.ProductPersistencePort;
import encora.challenger.api.msproducts.infraestructure.adapters.entities.ProductEntity;
import encora.challenger.api.msproducts.infraestructure.adapters.exception.ProductException;
import encora.challenger.api.msproducts.infraestructure.adapters.mapper.ProductDboMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
public class ProductRepositoryAdapter implements ProductPersistencePort {

  private final ProductRepository productRepository;
  private final ProductDboMapper productDboMapper;
  private final ReactiveMongoOperations reactiveMongoOperations;

  @Override
  public Mono<Product> create(Product model) {
    ProductEntity productEntity = productDboMapper.toDbo(model);
    return productRepository.save(productEntity).map(productDboMapper::toDomain);
  }

  @Override
  public Mono<Product> getById(String id) {
    return productRepository.findById(id).map(productDboMapper::toDomain);
  }

  @Override
  public Mono<Product> update(Product model) {
    ProductEntity productEntity = productDboMapper.toDbo(model);
    return productRepository.save(productEntity).map(productDboMapper::toDomain);
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return productRepository.deleteById(id);
  }

  @Override
  public Flux<Product> getAll() {
    return productRepository.findAll().map(productDboMapper::toDomain)
        .onErrorResume(ex -> Flux.error(
            new ProductException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error")));
  }

  @Override
  public Flux<Product> getActiveProductsByCustomerId(String documentNumber,String state) {
    return productRepository.findByDocumentNumberAndState(documentNumber,state).map(productDboMapper::toDomain)
        .onErrorResume(ex -> Flux.error(
            new ProductException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error")));
  }
}
