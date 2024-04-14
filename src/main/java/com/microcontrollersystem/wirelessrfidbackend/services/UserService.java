package com.microcontrollersystem.wirelessrfidbackend.services;

import com.microcontrollersystem.wirelessrfidbackend.models.dto.LoginRequest;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.User;
import com.microcontrollersystem.wirelessrfidbackend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
     private final UserRepository userRepository;

     public User validateUser(LoginRequest loginRequest) {
         Optional<User> userOptional = userRepository.findByEmailAndPassword(loginRequest.getEmailUser(),
                 loginRequest.getPassword());
         if (userOptional.isPresent())
             return userOptional.get();

         return null;
     }

     public List<User> getUserList() {
         return userRepository.findAll();
     }
}
