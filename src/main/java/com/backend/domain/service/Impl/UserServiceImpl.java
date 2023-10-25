package com.backend.domain.service.Impl;

import com.backend.domain.entity.User;
import com.backend.domain.repository.UserRepository;
import com.backend.domain.service.UserService;
import com.backend.web.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> getUserByEmail(String email) {


        return null;
    }


    public ResponseEntity<String> authenticateUser(LoginDTO loginDTO) {

        return null;
    }

    @Override
    public User loadUserByEmail(String emailUser) {
        return null;
    }
}


