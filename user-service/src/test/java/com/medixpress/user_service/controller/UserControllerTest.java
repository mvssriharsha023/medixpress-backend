package com.medixpress.user_service.controller;

import com.medixpress.user_service.entity.User;
import com.medixpress.user_service.entity.UserType;
import com.medixpress.user_service.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;  // MOCK UserService

    @Test
    void testRegisterApi() throws Exception {
        // Mock the behavior of userService
        User mockUser = new User(
                1L,
                "John Doe",
                "john@example.com",
                "password123",
                "123 Main Street",
                "9876543210",
                12.9716,
                77.5946,
                UserType.CUSTOMER
        );

        when(userService.saveUser(Mockito.any(User.class))).thenReturn(mockUser);

        String userJson = """
        {
            "name": "John Doe",
            "email": "john@example.com",
            "password": "password123",
            "contactNumber": "9876543210",
            "address": "123 Main Street",
            "latitude": 12.9716,
            "longitude": 77.5946,
            "userType": "CUSTOMER"
        }
        """;

        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk());
    }
}
