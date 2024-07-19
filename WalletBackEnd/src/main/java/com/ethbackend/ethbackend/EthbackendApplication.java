package com.ethbackend.ethbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class EthbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EthbackendApplication.class, args);
    }

}
