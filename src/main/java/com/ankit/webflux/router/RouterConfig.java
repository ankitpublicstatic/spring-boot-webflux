package com.ankit.webflux.router;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.ankit.webflux.handler.CustomerHandler;
import com.ankit.webflux.handler.CustomerStreamHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Configuration
public class RouterConfig {

  @Autowired
  private CustomerHandler customerHandler;

  @Autowired
  private CustomerStreamHandler customerStreamHandler;

  @Bean
  public WebProperties.Resources resources() {
    return new WebProperties.Resources();
  }

  @RouterOperations({

      @RouterOperation(path = "/router/customers", produces = {MediaType.APPLICATION_JSON_VALUE},
          method = RequestMethod.GET, beanClass = CustomerHandler.class,
          beanMethod = "getAllCustomers",
          operation = @Operation(operationId = "getAllCustomers",
              responses = {
                  @ApiResponse(responseCode = "200", description = "Success Loaded customers data",
                      content = @Content(schema = @Schema(
                          implementation = CustomerHandler.class)))})),
      @RouterOperation(
          path = "/router/customer/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
          method = RequestMethod.GET, beanClass = CustomerHandler.class,
          beanMethod = "findCustomerById", operation = @Operation(
              operationId = "findCustomerById", responses = {

                  @ApiResponse(responseCode = "200",
                      description = "Success Loaded customers data by id",
                      content = @Content(schema = @Schema(implementation = CustomerHandler.class))),
                  @ApiResponse(responseCode = "404",
                      description = "Customer not found with given id",
                      content = @Content(
                          schema = @Schema(implementation = CustomerHandler.class)))},
              parameters = {@Parameter(in = ParameterIn.PATH, name = "id")})),

      @RouterOperation(path = "/router/customer/save",
          produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST,
          beanClass = CustomerHandler.class, beanMethod = "saveCustomer", operation = @Operation(
              operationId = "saveCustomer", responses = {

                  @ApiResponse(responseCode = "200", description = "Successfully saved customer",
                      content = @Content(schema = @Schema(implementation = String.class)))},
              requestBody = @RequestBody(
                  content = @Content(schema = @Schema(implementation = CustomerHandler.class)))

          ))

  })
  @Bean
  public RouterFunction<ServerResponse> routerFunction() {
    return RouterFunctions.route().GET("/router/customers", customerHandler::getAllCustomers)
        .GET("/router/customers/stream", customerStreamHandler::getCustomersStream)
        .GET("/router/customer/{id}", customerHandler::findCustomerById)
        .POST("/router/customer/save", customerHandler::saveCustomer).build();
  }
}
