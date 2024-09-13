package com.testing.demo.repository;

import com.testing.demo.model.entity.Roles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class RoleRepositoryTest {

    @Mock
    private RoleRepository roleRepository;

    @Test
    void testFindByName() {
        Roles role1 = new Roles();
        Roles role2 = new Roles();

        role1.setId(1); role1.setName("admin");
        role2.setId(2); role2.setName("user");

        when(roleRepository.findByName("admin")).thenReturn(Optional.of(role1));
        when(roleRepository.findByName("user")).thenReturn(Optional.of(role2));

        Optional<Roles> roleTest1 = roleRepository.findByName("admin");
        Optional<Roles> roleTest2 = roleRepository.findByName("user");

        verify(roleRepository, times(1)).findByName("admin");
        verify(roleRepository, times(1)).findByName("user");

        //check ispresent
        Assertions.assertTrue(roleTest1.isPresent());
        Assertions.assertTrue(roleTest2.isPresent());

        assertEquals("admin", roleTest1.get().getName());
        assertEquals("user", roleTest2.get().getName());
    }
}
