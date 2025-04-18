package com.medixpress.user_service.repository;

import com.medixpress.user_service.entity.User;
import com.medixpress.user_service.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByContactNumber(String contactNumber);
    List<User> findByUserType(UserType userType);
}
