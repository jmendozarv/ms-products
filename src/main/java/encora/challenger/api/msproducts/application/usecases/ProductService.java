package encora.challenger.api.msproducts.application.usecases;

import encora.challenger.api.msproducts.domain.model.dto.ProductCreateRequest;
import encora.challenger.api.msproducts.domain.model.dto.ProductPatchRequest;
import encora.challenger.api.msproducts.domain.model.dto.ProductResponse;
import encora.challenger.api.msproducts.domain.model.dto.ProductUpdateRequest;
import java.util.Optional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

  Mono<ProductResponse> createProduct(ProductCreateRequest productCreate);

  Mono<Optional<ProductResponse>> getProductById(String productId);

  Mono<ProductResponse> updateProduct(String productId, ProductUpdateRequest productUpdate);

  Mono<ProductResponse> patchProduct(String productId, ProductPatchRequest productPatchRequest);

  Mono<Void> deleteProduct(String productId);

  Flux<ProductResponse> getActiveProductsByCustomer(String customerId,String state);

  Flux<ProductResponse> getAllProducts();

}
