package com.testing.demo.repository;

import com.testing.demo.model.entity.Processor;
import com.testing.demo.model.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

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

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testFindByUsername() {
        Optional<UserEntity> user = userRepository.findByUsername("admin");
        Optional<UserEntity> user2 = userRepository.findByUsername("user");
        Assertions.assertTrue(user.isPresent());
        Assertions.assertTrue(user2.isPresent());
        assertEquals("admin", user.get().getUsername());
        assertEquals("user", user2.get().getUsername());
        verify(userRepository, times(1)).findByUsername("admin");
        verify(userRepository, times(1)).findByUsername("user");
    }

    @Test
    void testExistsByUsername() {
        boolean exists = userRepository.existsByUsername("admin");
        boolean exists2 = userRepository.existsByUsername("user");
        Assertions.assertTrue(exists);
        Assertions.assertTrue(exists2);
        verify(userRepository, times(1)).existsByUsername("admin");
        verify(userRepository, times(1)).existsByUsername("user");
    }
}
