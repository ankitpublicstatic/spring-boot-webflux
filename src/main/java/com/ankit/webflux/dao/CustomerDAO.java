package com.ankit.webflux.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Component;
import com.ankit.webflux.dto.Customer;
import reactor.core.publisher.Flux;

@Component
public class CustomerDAO {

  public static void executionSleep(int i) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public List<Customer> getCustomer() {
    return IntStream.rangeClosed(1, 10).peek(CustomerDAO::executionSleep)

        .peek(j -> System.out.println("Processing count : " + j))
        .mapToObj(i -> new Customer(i, "Customer" + i))

        .collect(Collectors.toList());

  }

  public Flux<Customer> getCustomers() {
    return Flux.range(1, 10).doOnNext(j -> System.out.println("Processing count : " + j))
        .map(i -> new Customer(i, "Customer" + i));

  }

  public Flux<Customer> getCustomerStream() {
    return Flux.range(1, 10).delayElements(Duration.ofSeconds(1))
        .doOnNext(j -> System.out.println("Processing count in stream flow: " + j))
        .map(i -> new Customer(i, "Customer" + i));

  }
}
