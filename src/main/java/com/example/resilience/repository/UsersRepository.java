package com.example.resilience.repository;

import com.example.resilience.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
  Optional<User> findByUserName(String userName);
}
