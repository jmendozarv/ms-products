package encora.challenger.api.msproducts.infraestructure.adapters.repository;

import encora.challenger.api.msproducts.infraestructure.adapters.entities.ProductEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {
  Flux<ProductEntity> findByDocumentNumberAndState(String documentNumber,String state);
}
