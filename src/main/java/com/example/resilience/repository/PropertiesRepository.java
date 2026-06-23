package com.example.resilience.repository;

import com.example.resilience.entities.Properties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertiesRepository extends JpaRepository<Properties,Long> {

    Optional<Properties> findByPropName(String propName);
}
