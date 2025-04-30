package com.medixpress.order_service.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    @Test
    void testUserDTOBuilder() {
        UserDTO userDTO = UserDTO.builder()
                .id(123L)
                .name("John Doe")
                .email("john@example.com")
                .password("password123")
                .contactNumber("9876543210")
                .address("123 Main St")
                .latitude(12.34)
                .longitude(56.78)
                .userType(UserType.CUSTOMER)
                .build();

        assertNotNull(userDTO);
        assertEquals(123L, userDTO.getId());
        assertEquals("John Doe", userDTO.getName());
        assertEquals("john@example.com", userDTO.getEmail());
        assertEquals("password123", userDTO.getPassword());
        assertEquals("9876543210", userDTO.getContactNumber());
        assertEquals("123 Main St", userDTO.getAddress());
        assertEquals(12.34, userDTO.getLatitude());
        assertEquals(56.78, userDTO.getLongitude());
        assertEquals(UserType.CUSTOMER, userDTO.getUserType());
    }
}
