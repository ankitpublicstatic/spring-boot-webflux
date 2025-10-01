package com.ankit.webflux.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ankit.webflux.dao.CustomerDAO;
import com.ankit.webflux.dto.Customer;
import reactor.core.publisher.Flux;

@Service
public class CustomerService {
  @Autowired
  private CustomerDAO customerDAO;

  public List<Customer> getAllCustomer() {
    long start = System.currentTimeMillis();
    List<Customer> customers = customerDAO.getCustomer();
    long end = System.currentTimeMillis();
    System.out.println("Execution time taken : " + (end - start));
    return customers;
  }

  public Flux<Customer> getAllCustomerStream() {
    long start = System.currentTimeMillis();
    Flux<Customer> customers = customerDAO.getCustomerStream();
    long end = System.currentTimeMillis();
    System.out.println("Execution time taken stream : " + (end - start));
    return customers;
  }
}
