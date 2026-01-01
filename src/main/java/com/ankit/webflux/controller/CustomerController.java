package com.ankit.webflux.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ankit.webflux.dto.Customer;
import com.ankit.webflux.service.CustomerService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customers")
public class CustomerController {


  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public List<Customer> getAllcustomers() {
    return customerService.getAllCustomer();
  }

  @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Customer> getAllcustomersStream() {
    return customerService.getAllCustomerStream();
  }

}
