package com.medixpress.user_service.dto;

import com.medixpress.user_service.entity.UserType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class UserDTO {

    private String id;
    private String name;
    private String email;
    private String password;
    private String contactNumber;
    private String address;
    private double latitude;
    private double longitude;
    private UserType userType;
}
