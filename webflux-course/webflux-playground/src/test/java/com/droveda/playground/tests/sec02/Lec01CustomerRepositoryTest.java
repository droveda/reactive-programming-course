package com.droveda.playground.tests.sec02;

import com.droveda.playground.sec02.entity.Customer;
import com.droveda.playground.sec02.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.util.function.Function;

public class Lec01CustomerRepositoryTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(Lec01CustomerRepositoryTest.class);

    @Autowired
    private CustomerRepository repository;

    @Test
    void findAll() {
        repository.findAll()
                .doOnNext(c -> log.info("{}", c))
                .as(StepVerifier::create)
                .expectNextCount(10)
                .expectComplete()
                .verify();
    }

    @Test
    void findById() {
        repository.findById(2)
                .doOnNext(c -> log.info("{}", c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("mike", c.getName()))
                .expectComplete()
                .verify();
    }

    @Test
    void findByName() {
        repository.findByName("jake")
                .doOnNext(c -> log.info("{}", c))
                .as(StepVerifier::create)
                .assertNext(c -> {
                            Assertions.assertEquals("jake", c.getName());
                            Assertions.assertEquals("jake@gmail.com", c.getEmail());
                        }
                )
                .expectComplete()
                .verify();
    }

    @Test
    void findByEmailEndingWith() {
        repository.findByEmailEndingWith("ke@gmail.com")
                .doOnNext(c -> log.info("{}", c))
                .as(StepVerifier::create)
                .assertNext(c -> {
                            Assertions.assertEquals("mike", c.getName());
                            Assertions.assertEquals("mike@gmail.com", c.getEmail());
                        }
                )
                .assertNext(c -> {
                            Assertions.assertEquals("jake", c.getName());
                            Assertions.assertEquals("jake@gmail.com", c.getEmail());
                        }
                )
                .expectComplete()
                .verify();
    }

    @Test
    void insertAndDeleteCustomer() {
        //insert
        var customer = new Customer();
        customer.setName("marshal");
        customer.setEmail("marshal@gmail.com");

        var saved = repository.save(customer);

        saved
                .doOnNext(c -> log.info("{}", c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertNotNull(c.getId()))
                .expectComplete()
                .verify();

        // count
        this.repository.count()
                .as(StepVerifier::create)
                .expectNext(11L)
                .expectComplete()
                .verify();

        // delete
        repository.deleteById(11)
                .then(repository.count())
                .as(StepVerifier::create)
                .expectNext(10L)
                .expectComplete()
                .verify();

    }

    @Test
    void updateCustomer() {

        repository.findByName("ethan")
                .doOnNext(c -> c.setName("noel"))
//                .flatMap(c -> this.repository.save(c))
                .flatMap(new Function<Customer, Publisher<Customer>>() {
                    @Override
                    public Publisher<Customer> apply(Customer customer) {
//                        customer.setName("noel 2");
                        return repository.save(customer);
                    }
                })
                .doOnNext(c -> log.info("{}", c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("noel", c.getName()))
                .expectComplete()
                .verify();

    }

}
