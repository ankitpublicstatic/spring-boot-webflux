package com.ankit.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(title = "Spring Webflux demo", version = "1.0", description = "Sample documents"))
public class SpringBootWebfluxApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootWebfluxApplication.class, args);
  }

}
