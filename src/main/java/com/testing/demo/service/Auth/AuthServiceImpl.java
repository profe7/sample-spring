package com.testing.demo.service.Auth;

import com.testing.demo.model.dto.RegisterDto;
import com.testing.demo.model.entity.Roles;
import com.testing.demo.model.entity.UserEntity;
import com.testing.demo.repository.RoleRepository;
import com.testing.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserEntity registerUser(RegisterDto registerDto) {
        if (isUsernameTaken(registerDto.getUsername())) {
            throw new RuntimeException("Error: Username is already taken!");
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Roles roles = roleRepository.findByName("ADMIN").get();
        user.setRoles(Collections.singletonList(roles));

        return userRepository.save(user);
    }

    private boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }
}
