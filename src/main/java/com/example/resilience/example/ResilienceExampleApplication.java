package com.example.resilience.example;

import com.example.resilience.example.config.CircuitBreakerConfiguration;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import jakarta.persistence.EntityManager;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.time.Duration;
import java.util.Set;
import java.util.Stack;

@SpringBootApplication
@Import({ CircuitBreakerConfiguration.class})
@EnableFeignClients
public class ResilienceExampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(ResilienceExampleApplication.class, args);
	}


}
