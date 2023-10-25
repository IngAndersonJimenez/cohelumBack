package com.backend.domain.service;

import com.backend.domain.entity.User;

public interface UserService {

    User loadUserByEmail(String emailUser);

}
