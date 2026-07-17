package com.example.resilience.repository;

import com.example.resilience.entities.Properties;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertiesRepository extends JpaRepository<Properties, Long> {

  Optional<Properties> findByPropName(String propName);
}
