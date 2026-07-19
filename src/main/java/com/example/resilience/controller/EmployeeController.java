package com.example.resilience.controller;

import com.example.resilience.dto.EmployeeDTO;
import com.example.resilience.dto.EmployeesResponse;
import com.example.resilience.dto.PageResponse;
import com.example.resilience.services.EmployeeApiService;
import com.example.springutil.annotations.LogRequest;
import com.example.springutil.dto.RequestWrapper;
import com.example.springutil.dto.ResponseWrapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Objects;
import lombok.CustomLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
@Tags(value = {@Tag(name = "Employee Controller")})
@CustomLog
public class EmployeeController {
  private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
  @Autowired EmployeeApiService employeeApiService;

  @GetMapping("/all")
  ResponseEntity<List<EmployeeDTO>> getEmployees() {
    logger.info("getEmployees method");
    Mono<EmployeesResponse> employees = employeeApiService.getEmployees();
    // employees.s
    List<EmployeeDTO> result = Objects.requireNonNull(employees.block()).getData();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/getEmployees")
  ResponseEntity<List<EmployeeDTO>> getEmployeesUsingRepo() {
    logger.info("get all employees");
    EmployeesResponse result = employeeApiService.getAllEmployees();
    return new ResponseEntity<>(result.getData(), HttpStatus.OK);
  }

  @GetMapping(value = " ", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<PageResponse> getPagebleResponse(
      @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
      @RequestParam(name = "size", defaultValue = "5") int size,
      @RequestParam(name = "sort", defaultValue = "firstName") String sortBy,
      @RequestParam(name = "order", defaultValue = "asc") String order) {

    Sort sortObject =
        Sort.by("asc".equals(order) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy.split(","));
    Pageable pageable = PageRequest.of(pageNumber, size, sortObject);
    PageResponse<EmployeeDTO> response = employeeApiService.getAllEmployeesUsingPageable(pageable);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(
      value = "/createEmployee",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @LogRequest
  ResponseEntity<ResponseWrapper<EmployeeDTO>> createEmployee(
      @Valid @RequestBody RequestWrapper<EmployeeDTO, Void> requestWrapper) {
    EmployeeDTO e = employeeApiService.saveEmployee(requestWrapper.request());
    ResponseWrapper<EmployeeDTO> response = new ResponseWrapper<>(null, "abc", "1.0.0", 200, e);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
