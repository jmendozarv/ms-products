package encora.challenger.api.msproducts.domain.port;

import encora.challenger.api.msproducts.domain.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductPersistencePort {

  Mono<Product> create(Product product);

  Mono<Product> getById(String productId);

  Mono<Product> update(Product productUpdate);

  Mono<Void> deleteById(String productId);

  Flux<Product> getAll();

  Flux<Product> getActiveProductsByCustomerId(String customerId,String state);

}
