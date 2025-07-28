package com.example.resilience;

import com.example.resilience.config.CircuitBreakerConfiguration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ CircuitBreakerConfiguration.class})
@OpenAPIDefinition(info =@Info(title = "Resilience test application",version = "v1",
		contact = @Contact( name = "venu", email = "venuchalla1993@gmail.com",url = "https://google.com"))
)
@EnableFeignClients
public class ResilienceExampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(ResilienceExampleApplication.class, args);
	}


}
