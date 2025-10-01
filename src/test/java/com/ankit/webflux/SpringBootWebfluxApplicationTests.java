package com.ankit.webflux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.ankit.webflux.controller.ProductController;
import com.ankit.webflux.dto.ProductDto;
import com.ankit.webflux.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

// @SpringBootTest
@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
class SpringBootWebfluxApplicationTests {

  // @Test
  // void contextLoads() {
  // }

  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private ProductService productService;

  @org.junit.jupiter.api.Test
  public void addProductTest() {
    Mono<ProductDto> productDtoMono = Mono.just(new ProductDto("102", "mobile", 80, 900.0));
    when(productService.saveProduct(productDtoMono)).thenReturn(productDtoMono);

    webTestClient.post().uri("/products").body(Mono.just(productDtoMono), ProductDto.class)
        .exchange().expectStatus().isOk();
  }

  @Test
  public void getProductsTest() {
    Flux<ProductDto> productDtoFlux = Flux.just(new ProductDto("102", "mobile", 80, 900.0),
        new ProductDto("103", "tab", 80, 900.0), new ProductDto("104", "iphone", 80, 900.0),
        new ProductDto("105", "ipad", 80, 900.0));
    when(productService.getProducts()).thenReturn(productDtoFlux);

    Flux<ProductDto> responseBody = webTestClient.get().uri("/products").exchange().expectStatus()
        .isOk().returnResult(ProductDto.class).getResponseBody();

    StepVerifier.create(responseBody).expectSubscription()
        .expectNext(new ProductDto("102", "mobile", 80, 900.0))
        .expectNext(new ProductDto("103", "tab", 80, 900.0))
        .expectNext(new ProductDto("104", "iphone", 80, 900.0))
        .expectNext(new ProductDto("105", "ipad", 80, 900.0)).verifyComplete();
  }

  @Test
  public void getProductByIdTest() {
    Mono<ProductDto> productDtoMono = Mono.just(new ProductDto("102", "mobile", 80, 900.0));
    when(productService.getProduct(any())).thenReturn(productDtoMono);

    Flux<ProductDto> responseBody = webTestClient.get().uri("/products/102").exchange()
        .expectStatus().isOk().returnResult(ProductDto.class).getResponseBody();

    StepVerifier.create(responseBody).expectSubscription()
        .expectNextMatches(p -> "mobile".equals(p.getName()))
        // .expectNext(new ProductDto("102", "mobile", 80, 900.0))
        .verifyComplete();
  }

  @Test
  public void updateProductTest() {
    Mono<ProductDto> productDtoMono = Mono.just(new ProductDto("102", "mobile", 80, 900.0));
    when(productService.updateProduct(productDtoMono, "102")).thenReturn(productDtoMono);

    webTestClient.put().uri("/products/update/102")
        .body(Mono.just(productDtoMono), ProductDto.class).exchange().expectStatus().isOk();
  }

  @Test
  public void deleteProductByIdTest() {
    given(productService.deleteProduct(any())).willReturn(Mono.empty());

    webTestClient.delete().uri("/products/delete/102").exchange().expectStatus().isOk();

  }
}
