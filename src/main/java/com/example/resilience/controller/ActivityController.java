package com.example.resilience.controller;

import com.example.resilience.dto.ApiError;
import com.example.resilience.services.BoredApiService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.servlet.FilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/activity")
@Tags(value = {
        @Tag(name = "Activity Controller")
})
public class ActivityController {
    private final Logger logger = LoggerFactory.getLogger(ActivityController.class);
    @Autowired
    BoredApiService boredApiService;
    @GetMapping(path = "")
    @Operation(summary = "get activity blocks")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "500", content = @Content(
                    schema = @Schema(implementation = ApiError.class)
            ), useReturnTypeSchema = true)})
    public ResponseEntity<String> getActivity() {
        String result = boredApiService.getActivity().block();
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(200));

    }

    @GetMapping(path = "/{type}")
    @CircuitBreaker(name = "BoredApiCircuitBreaker", fallbackMethod = "BoredApiFallBackMethod")
    @Operation(summary = "get activity block using block type", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "500", content = @Content(
                    schema = @Schema(implementation = ApiError.class)
            ), useReturnTypeSchema = true)
    })
    public ResponseEntity<String> getActivityByType(@PathVariable String type) {
        logger.info("path variable : {}", type);
        String result = boredApiService.getActivityByType(type).block();
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(200));

    }

    @GetMapping(path = "/fallback/{status}")
    public Mono<String> getActivityFallBack(@PathVariable String status) {
        logger.info("path variable : {}", status);
        return boredApiService.getActivityFallBack(status);
        // return new ResponseEntity<>(result, HttpStatusCode.valueOf(200));

    }

}
