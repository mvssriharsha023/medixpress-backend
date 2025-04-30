package com.medixpress.order_service.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String contactNumber;
    private String address;
    private double latitude;
    private double longitude;
    private UserType userType;

    public UserDTO(long l, String customerA, String mail) {
    }
}
