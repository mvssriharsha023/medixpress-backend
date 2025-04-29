package com.medixpress.user_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.medixpress.user_service.entity.User;

import com.medixpress.user_service.entity.UserType;
import com.medixpress.user_service.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindUserById() {

        User user = new User(
                1L,
                "John Doe",
                "john@example.com",
                "password123",         // password
                "123 Main Street",      // address
                "9876543210",           // phone number
                12.9716,                // latitude
                77.5946,                // longitude
                UserType.CUSTOMER       // Assuming you have an enum UserType
        );

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.findUserById(1L);

        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    void testSaveUser() {
        User user = new User(
                null,
                "Jane Doe",
                "jane@example.com",
                "password456",
                "456 Another Street",
                "9123456789",
                12.2958,
                76.6394,
                UserType.CUSTOMER
        );
        User savedUser = new User(
                2L,
                "Jane Doe",
                "jane@example.com",
                "password456",
                "456 Another Street",
                "9123456789",
                12.2958,
                76.6394,
                UserType.CUSTOMER
        );

        when(userRepository.save(user)).thenReturn(savedUser);

        User result = userService.saveUser(user);

        assertEquals(2L, result.getId());
        assertEquals("Jane Doe", result.getName());
    }
}
