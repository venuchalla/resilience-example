package com.example.resilience.services;

import com.example.resilience.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserDetails userDetails = usersRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException(username));
        log.info("Role : {}", userDetails.getAuthorities());
        return  userDetails;
    }


}
