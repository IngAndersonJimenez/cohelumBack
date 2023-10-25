package com.backend.security;

import com.backend.domain.entity.User;
import com.backend.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String emailUser) throws UsernameNotFoundException {
        User user = this.userRepository
                .findOneUserByEmail(emailUser).orElseThrow(() ->
                        new UsernameNotFoundException("The user with email" + emailUser + "not found."));

        return new UserDetailsImpl(user);
    }
}
