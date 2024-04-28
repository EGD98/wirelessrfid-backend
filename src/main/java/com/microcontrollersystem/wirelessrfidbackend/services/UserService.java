package com.microcontrollersystem.wirelessrfidbackend.services;

import com.microcontrollersystem.wirelessrfidbackend.models.dto.LoginRequest;
import com.microcontrollersystem.wirelessrfidbackend.models.dto.UserData;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.User;
import com.microcontrollersystem.wirelessrfidbackend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
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
         return userRepository.findAllByStatusTrue();
     }

     public String addUser(UserData userData, Integer idUser) {
         User user;
         if(!userData.getId().isEmpty()){
             user = userRepository.findById(Integer.parseInt(userData.getId())).orElseThrow(() -> new RuntimeException("Usuario no existe"));
             user = userData.asDTO(user);
         } else {
            user = userData.asDTO();
         }
         if (user.getId() != null) {
             user.setEditDate(new Date());
             user.setEditorUser(idUser);
         } else {
             user.setCreatorUser(idUser);
         }
         User userSaved = userRepository.save(user);
         String respuesta = user.getId() != null ? "Usuario actualizado con exito" : "Usuario creado con exito";
         return Objects.nonNull(userSaved.getId()) ? respuesta
                 : "Ha ocurrido un error al intentar crear el usuario";
     }

    public String deleteUser(Integer idUserEditor, Integer idUser) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("Usuario no existe"));
        user.setStatus(Boolean.FALSE);
        user.setEditorUser(idUserEditor);
        user.setEditDate(new Date());
        User userSaved = userRepository.save(user);
        String respuesta = "Usuario eliminado con exito" ;
        return Objects.nonNull(userSaved.getId()) ? respuesta
                : "Ha ocurrido un error al intentar eliminar el usuario";
    }
}
