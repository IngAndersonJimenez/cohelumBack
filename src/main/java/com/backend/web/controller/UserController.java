package com.backend.web.controller;

import com.backend.domain.entity.User;
import com.backend.domain.service.Impl.UserServiceImpl;
import com.backend.web.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<Object> getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        Map<String, String> response = new HashMap<>();
        if (user == null){
            response.put("message", "Usuario no encontrado para el correo electrónico: " + email);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.getUserByEmail(loginDTO.getEmail());

        if (user != null && user.getPassword().equals(loginDTO.getPassword())) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

}
