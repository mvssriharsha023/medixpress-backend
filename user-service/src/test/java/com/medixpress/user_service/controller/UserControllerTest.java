package com.medixpress.user_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegisterApi() throws Exception {
        // Create user request
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Harhsa shanmukh");
        userRequest.setEmail("Venkata.Shanmukha.Maddila@ness.com");
        userRequest.setPassword("shanmukh@123");
        userRequest.setContactNumber("9019102265");
        userRequest.setAddress("Kphb,hyderabad,Telangana,500084");
        userRequest.setUserType("CUSTOMER");
        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{...}"))
                .andExpect(status().isCreated());
    }
}

