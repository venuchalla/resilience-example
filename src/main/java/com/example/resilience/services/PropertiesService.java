package com.example.resilience.services;

import com.example.resilience.dto.PropertiesDTO;
import com.example.resilience.entities.Properties;
import com.example.resilience.repository.PropertiesRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PropertiesService {

  @Autowired PropertiesRepository propertiesRepository;

  public PropertiesDTO saveProp(PropertiesDTO propertiesDTO) {

    Properties prop = new Properties();
    prop.setPropName(propertiesDTO.getPropName());
    prop.setPropValue(propertiesDTO.getPropValue());
    Properties p = propertiesRepository.save(prop);
    log.info("p : {}", p);
    return propertiesDTO;
  }

  public Optional<Properties> getProp(String propName) {
    Optional<Properties> p = propertiesRepository.findByPropName(propName);
    return p;
  }
}
