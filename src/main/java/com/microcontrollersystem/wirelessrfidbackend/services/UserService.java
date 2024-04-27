package com.microcontrollersystem.wirelessrfidbackend.services;

import com.microcontrollersystem.wirelessrfidbackend.models.dto.LoginRequest;
import com.microcontrollersystem.wirelessrfidbackend.models.dto.UserData;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.User;
import com.microcontrollersystem.wirelessrfidbackend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
     private final UserRepository userRepository;

     public User validateUser(LoginRequest loginRequest) {
         Optional<User> userOptional = userRepository.findByEmailAndPassword(loginRequest.getEmailUser(),
                 loginRequest.getPassword());
         return userOptional.orElse(null);

     }

     public List<User> getUserList() {
         return userRepository.findAll();
     }

     public String addUser(UserData userData, Integer idUser) {
         User user = userData.asDTO();
         user.setCreatorUser(idUser);
         User userSaved = userRepository.save(user);
         return Objects.nonNull(userSaved.getId()) ? "Usuario creado con exito"
                 : "Ha ocurrido un error al intentar crear el usuario";
     }
}
