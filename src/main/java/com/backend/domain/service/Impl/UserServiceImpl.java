package com.backend.domain.service.Impl;

import com.backend.domain.entity.User;
import com.backend.domain.repository.UserRepository;
import com.backend.web.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        ResponseEntity<Object> response;

        if (user == null) {
            String errorMessage = "Usuario no encontrado para el correo electrónico: " + email;
            response = new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<>(user, HttpStatus.OK);
        }

        return response;
    }


    public ResponseEntity<String> authenticateUser(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail());
        ResponseEntity<String> response;

        if (user != null && user.getPassword().equals(loginDTO.getPassword())) {
            response =  ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
        return response;
    }

}


