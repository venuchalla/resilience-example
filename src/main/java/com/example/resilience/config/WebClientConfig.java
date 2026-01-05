package com.example.resilience.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    private final String baseUrl = "https://dummy.restapiexample.com/api/v1/";
    //BasicErrorController
   private static final Logger logger = LoggerFactory.getLogger(WebClientConfig.class);


    @Bean
    DefaultUriBuilderFactory createDefaultUriBuilderFactory(){
        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory(baseUrl);
        defaultUriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);
        return  defaultUriBuilderFactory;
    }
    @Bean
    public WebClient createWebClient() {
        return WebClient.builder()
                .uriBuilderFactory(createDefaultUriBuilderFactory())
                //.baseUrl(baseUrl)
                .defaultCookie("cookie-name", "cookie-value")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(logRequest())
                .filter(logResponse())
                .build();
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            logger.info("Client response {}",clientResponse.releaseBody());
            return Mono.just(clientResponse);
        });
    }

    private ExchangeFilterFunction logRequest(){
       return ExchangeFilterFunction.ofRequestProcessor( clientRequest -> {
           logger.info("RequestMethod : {} RequestURL:{}",clientRequest.method(),clientRequest.url());
           return Mono.just(clientRequest);
       });
    }
}
