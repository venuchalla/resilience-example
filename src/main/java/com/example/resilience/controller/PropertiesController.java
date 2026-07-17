package com.example.resilience.controller;

import com.example.resilience.dto.Option;
import com.example.resilience.dto.Options;
import com.example.resilience.dto.PropertiesDTO;
import com.example.resilience.entities.Properties;
import com.example.resilience.services.PropertiesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/props")
@Slf4j
public class PropertiesController {

  @Autowired PropertiesService propertiesService;

  @GetMapping(path = "/getResidencyType", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Option>> getResidencyType(@RequestParam("name") String name) {

    Option firstOption = new Option("Rent", "rent");
    Option secondOption = new Option("Own", "own");
    Option thirdOption = new Option("Other", "other");
    List<Option> lOptions = new ArrayList<>();

    Optional<Properties> p = propertiesService.getProp(name);
    if (p.isPresent()) {
      Properties p2 = p.get();
      String value = p2.getPropValue();
      ObjectMapper objectMapper = new ObjectMapper();
      Options o = new Options();
      try {
        log.info("value :{}", value);
        lOptions = objectMapper.readValue(value, new TypeReference<List<Option>>() {});
      } catch (JsonProcessingException e) {
        log.error("exception :" + e.getMessage());
      }
      return new ResponseEntity<>(lOptions, HttpStatus.OK);
    }
    lOptions.add(firstOption);
    lOptions.add(secondOption);
    lOptions.add(thirdOption);
    Options options = new Options(lOptions);
    return new ResponseEntity<>(lOptions, HttpStatusCode.valueOf(200));
  }

  @PostMapping(
      value = "/createProp",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PropertiesDTO> createProp(@RequestBody PropertiesDTO propertiesDTO) {
    Optional<Properties> p = propertiesService.getProp(propertiesDTO.getPropName());
    if (p.isPresent()) {

      Properties pe = p.get();
      log.info("prop is already present : {}", pe);
      PropertiesDTO existingDto = new PropertiesDTO(propertiesDTO.getPropValue(), pe.getPropName());
      propertiesService.saveProp(existingDto);
      return new ResponseEntity<>(propertiesDTO, HttpStatus.OK);
    }
    propertiesService.saveProp(propertiesDTO);
    return new ResponseEntity<>(propertiesDTO, HttpStatus.CREATED);
  }
}
