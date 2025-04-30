package com.medixpress.order_service.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTypeTest {

    @Test
    void testEnumValues() {
        assertEquals(UserType.CUSTOMER, UserType.valueOf("CUSTOMER"));
        assertEquals(UserType.PHARMACY, UserType.valueOf("PHARMACY"));
    }
}
