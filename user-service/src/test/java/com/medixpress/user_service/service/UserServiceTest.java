package com.medixpress.user_service.service;

import com.medixpress.user_service.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testRegisterUser() {
        User user = new User(...);
        User savedUser = userService.registerUser(user);
        assertNotNull(savedUser.getId());
    }
}

