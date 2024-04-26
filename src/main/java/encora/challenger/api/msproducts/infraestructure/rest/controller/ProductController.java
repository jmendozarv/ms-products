package encora.challenger.api.msproducts.infraestructure.rest.controller;

import encora.challenger.api.msproducts.application.services.ProductManagementService;
import encora.challenger.api.msproducts.domain.model.dto.ProductCreateRequest;
import encora.challenger.api.msproducts.domain.model.dto.ProductPatchRequest;
import encora.challenger.api.msproducts.domain.model.dto.ProductResponse;
import encora.challenger.api.msproducts.domain.model.dto.ProductUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openapitools.api.ProductsApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@Log4j2
public class ProductController implements ProductsApi {

  private final ProductManagementService productManagementService;

  @Override
  public Mono<ResponseEntity<Flux<ProductResponse>>> productsActiveGet(
      ServerWebExchange exchange) {
    // Obtenemos todos los productos activos usando el servicio
    Flux<ProductResponse> activeProducts = productManagementService.getAllProducts();

    // Devolvemos un ResponseEntity con el estado HTTP 200 y la lista de productos
    return Mono.just(ResponseEntity.ok(activeProducts));
  }


  @Override
  public Mono<ResponseEntity<ProductResponse>> productsPost(
      Mono<ProductCreateRequest> productCreateRequest, ServerWebExchange exchange) {
    return productCreateRequest
        .flatMap(productManagementService::createProduct)  // Creamos el producto
        .map(createdProduct -> ResponseEntity
            .status(201)  // Devolvemos HTTP 201 Created
            .body(createdProduct));  // Devolvemos el producto creado

  }

  @Override
  public Mono<ResponseEntity<Void>> productsProductIdDelete(String productId,
      ServerWebExchange exchange) {
    return productManagementService.deleteProduct(productId)  // Eliminamos el producto
        .then(Mono.just(ResponseEntity.ok().build()));
  }

  @Override
  public Mono<ResponseEntity<ProductResponse>> productsProductIdGet(String productId,
      ServerWebExchange exchange) {
    return null;
  }

  @Override
  public Mono<ResponseEntity<ProductResponse>> productsProductIdPut(String productId,
      Mono<ProductUpdateRequest> productUpdateRequest, ServerWebExchange exchange) {
    return productUpdateRequest
        .flatMap(request -> productManagementService.updateProduct(productId,
            request))  // Actualizamos el producto
        .map(ResponseEntity::ok)  // Devolvemos el producto actualizado
        .switchIfEmpty(
            Mono.just(ResponseEntity.notFound().build()));  // HTTP 404 si no se encuentra
  }

  @Override
  public Mono<ResponseEntity<Flux<ProductResponse>>> productsCustomerCustomerIdGet(
      String customerId, String state, ServerWebExchange exchange) {
    Flux<ProductResponse> activeProducts =
        productManagementService.getActiveProductsByCustomer(customerId, state);
    // Devolvemos un ResponseEntity con el estado HTTP 200 y la lista de productos
    return Mono.just(ResponseEntity.ok(activeProducts));
  }

  @Override
  public Mono<ResponseEntity<ProductResponse>> productsProductIdPatch(String productId,
      Mono<ProductPatchRequest> productPatchRequest, ServerWebExchange exchange) {
    return productPatchRequest
        .flatMap(request -> productManagementService.patchProduct(productId,
            request))  // Actualizamos el producto
        .map(ResponseEntity::ok)  // Devolvemos el producto actualizado
        .switchIfEmpty(
            Mono.just(ResponseEntity.notFound().build()));  // HTTP 404 si no se encuentra
  }
}
