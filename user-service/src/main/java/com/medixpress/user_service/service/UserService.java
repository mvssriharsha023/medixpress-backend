package com.medixpress.user_service.service;

import com.medixpress.user_service.dto.LoginRequest;
import com.medixpress.user_service.dto.LoginResponse;
import com.medixpress.user_service.dto.UserDTO;
import com.medixpress.user_service.entity.User;

import java.util.List;

public interface UserService {
    User registerUser(UserDTO userDTO);
    LoginResponse loginUser(LoginRequest loginRequest);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    List<User> getAllCustomers();
    List<User> getAllPharmacy();

    // adding for testing
    User findUserById(Long id);
    User saveUser(User user);
}