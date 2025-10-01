package com.ankit.webflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.ankit.webflux.dao.CustomerDAO;
import com.ankit.webflux.dto.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

  @Autowired
  private CustomerDAO customerDAO;

  public Mono<ServerResponse> getCustomersStream(ServerRequest request) {
    Flux<Customer> customerStream = customerDAO.getCustomerStream();

    return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(customerStream,
        Customer.class);
  }
}
