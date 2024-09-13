package com.testing.demo.service;

import com.testing.demo.model.dto.RegisterDto;
import com.testing.demo.model.entity.Roles;
import com.testing.demo.model.entity.UserEntity;
import com.testing.demo.repository.RoleRepository;
import com.testing.demo.repository.UserRepository;
import com.testing.demo.service.Auth.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        UserEntity user1 = new UserEntity();
        UserEntity user2 = new UserEntity();

        user1.setId(1); user1.setUsername("admin"); user1.setPassword("admin");
        user2.setId(2); user2.setUsername("user"); user2.setPassword("user");

        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user1));
        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user2));
        when(userRepository.existsByUsername("admin")).thenReturn(true);
        when(userRepository.existsByUsername("user")).thenReturn(true);
    }

    @Test
    void testRegisterUser() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("admin");
        registerDto.setPassword("admin");

        Roles roles = new Roles();
        roles.setId(1); roles.setName("ADMIN");

        when(roleRepository.findByName("ADMIN")).thenReturn(Optional.of(roles));
        when(userRepository.save(any(UserEntity.class))).thenReturn(new UserEntity());

        try {
            authService.registerUser(registerDto);
        } catch (RuntimeException e) {
            assertEquals("Error: Username is already taken!", e.getMessage());
        }
    }
}
