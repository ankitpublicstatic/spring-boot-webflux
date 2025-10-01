package com.ankit.webflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.ankit.webflux.dao.CustomerDAO;
import com.ankit.webflux.dto.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

  @Autowired
  private CustomerDAO customerDAO;

  public Mono<ServerResponse> getAllCustomers(ServerRequest request) {
    Flux<Customer> customer = customerDAO.getCustomers();
    return ServerResponse.ok().body(customer, Customer.class);
  }

  public Mono<ServerResponse> findCustomerById(ServerRequest request) {
    Integer id = Integer.parseInt(request.pathVariable("id"));
    // customerDAO.getCustomers().filter(c -> c.getId() == id).take(1).single();
    Mono<Customer> customer = customerDAO.getCustomers().filter(c -> c.getId() == id).next();
    return ServerResponse.ok().body(customer, Customer.class);
  }

  public Mono<ServerResponse> saveCustomer(ServerRequest request) {
    Mono<Customer> customerMono = request.bodyToMono(Customer.class);
    Mono<String> saveResponse =
        customerMono.map(customer -> customer.getId() + " , " + customer.getName());
    return ServerResponse.ok().body(saveResponse, String.class);
  }
}
