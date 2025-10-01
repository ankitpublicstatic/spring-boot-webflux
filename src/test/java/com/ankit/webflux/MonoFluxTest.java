package com.ankit.webflux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

	public void testMono() {
		Mono<?> monoString = Mono.just("Java").then(Mono.error(new Throwable("Exception Occured"))).log();
		// .subscribe(System.out::println);
		monoString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
	}

//	@Test
	public void testFlux() {
		Flux<?> flux = Flux.just("Spring", "Hibernate", "JPA", "Database", "Microservice").concatWithValues("AWS")
				.concatWith(Flux.error(new Throwable("Exception occured"))).log();
		flux.subscribe(System.out::println);
	}
}
