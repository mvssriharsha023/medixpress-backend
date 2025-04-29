package com.medixpress.apigateway.controller;

import com.medixpress.apigateway.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AuthControllerTest {

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateToken() {
        Long id = 1L;
        String mockToken = "mock-jwt-token";

        when(jwtUtil.generateToken(id)).thenReturn(mockToken);

        ResponseEntity<String> response = authController.generateToken(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockToken, response.getBody());
    }
}
