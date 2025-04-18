package com.medixpress.user_service.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import org.springframework.data.mongodb.core.index.Indexed;
//import org.springframework.data.mongodb.core.mapping.Document;

@Entity//(collection = "user")
//@Table(name = "users", uniqueConstraints = {
//        @UniqueConstraint(columnNames = "email"),
//        @UniqueConstraint(columnNames = "contactNumber")
//})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotNull
//    @Indexed(unique = true)
    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @NotNull
//    @Indexed(unique = true)
    @Column(nullable = false, unique = true)
    private String contactNumber;

    private String address;

    private double latitude;
    private double longitude;

    private UserType userType;
}
