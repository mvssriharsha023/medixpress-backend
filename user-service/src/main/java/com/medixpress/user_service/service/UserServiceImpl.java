package com.medixpress.user_service.service;

import com.medixpress.user_service.entity.UserType;
import com.medixpress.user_service.exception.InvalidCredentialsException;
import com.medixpress.user_service.exception.UserAlreadyExistsException;
import com.medixpress.user_service.exception.UserNotExistException;
import com.medixpress.user_service.exception.InvalidAddressException;
import com.medixpress.user_service.dto.LoginRequest;
import com.medixpress.user_service.dto.LoginResponse;
import com.medixpress.user_service.dto.UserDTO;
import com.medixpress.user_service.entity.User;
import com.medixpress.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GeoLocationService geoLocationService;

    @Autowired
    private RestTemplate restTemplate;

    public String generateTokenFromApiGateway(Long id) {
        String url = "http://localhost:8080/auth/generate-token?id=" + id;
        return restTemplate.postForObject(url, null, String.class);
    }

    @Override
    public User registerUser(UserDTO userDTO) {

        Optional<User> opUserByEmail = userRepository.findByEmail(userDTO.getEmail());
        Optional<User> opUserByContactNumber = userRepository.findByContactNumber(userDTO.getContactNumber());

        if (opUserByEmail.isPresent() || opUserByContactNumber.isPresent()) {
            throw new UserAlreadyExistsException("Pharmacy or Customer with this email id or phone number already exists");
        }

        // Split by comma
        String[] parts = userDTO.getAddress().split(",");

        // Get the last 4 parts
        int len = parts.length;
        StringBuilder tempAddress = new StringBuilder();
        for (int i = len - 4; i < len; i++) {
            tempAddress.append(parts[i].trim());
            if (i != len - 1) {
                tempAddress.append(", ");
            }
        }

        double[] latLong = geoLocationService
                .getLatLongFromAddress(String.valueOf(tempAddress))
                .orElseThrow(() -> new InvalidAddressException("Invalid address"));

        System.out.println(userDTO.getUserType());


        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .contactNumber(userDTO.getContactNumber())
                .address(userDTO.getAddress())
                .latitude(latLong[0])
                .longitude(latLong[1])
                .userType(userDTO.getUserType())
                .build();

        return userRepository.save(user);
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!Objects.equals(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = generateTokenFromApiGateway(user.getId());

        if (user.getUserType().toString().equals("CUSTOMER")) {
            return new LoginResponse(
                    token,
                    "Login successful",
                    "CUSTOMER"
            );
        }

        return new LoginResponse(
                token,
                "Login successful",
                "PHARMACY"
        );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotExistException("User not found"));
    }

    @Override
    public User updateUser(Long id, UserDTO dto) {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotExistException("User not found"));

        Optional<User> opUserByEmail = userRepository.findByEmail(dto.getEmail());
        Optional<User> opUserByContactNumber = userRepository.findByContactNumber(dto.getContactNumber());

        if (opUserByEmail.isPresent() && !Objects.equals(user.getEmail(), dto.getEmail())) {
            throw new UserAlreadyExistsException("Customer or Pharmacy with this email id already exists");
        }

        if (opUserByContactNumber.isPresent() && !Objects.equals(user.getContactNumber(), dto.getContactNumber())) {
            throw new UserAlreadyExistsException("Customer or Pharmacy with this phone number already exists");
        }

        // Split by comma
        String[] parts = dto.getAddress().split(",");

        // Get the last 4 parts
        int len = parts.length;
        StringBuilder tempAddress = new StringBuilder();
        for (int i = len - 4; i < len; i++) {
            tempAddress.append(parts[i].trim());
            if (i != len - 1) {
                tempAddress.append(", ");
            }
        }

        double[] latLong = geoLocationService
                .getLatLongFromAddress(String.valueOf(tempAddress))
                .orElseThrow(() -> new InvalidAddressException("Invalid address"));


        User existing = getUserById(id);
        existing.setName(dto.getName());
        existing.setAddress(dto.getAddress());
        existing.setPassword(dto.getPassword());
        existing.setContactNumber(dto.getContactNumber());
        existing.setEmail(dto.getEmail());
        existing.setLatitude(latLong[0]);
        existing.setLongitude(latLong[1]);
        existing.setUserType(dto.getUserType());
        return userRepository.save(existing);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllCustomers() {
        return userRepository.findByUserType(UserType.CUSTOMER);
    }

    @Override
    public List<User> getAllPharmacy() {
        return userRepository.findByUserType(UserType.PHARMACY);
    }


    // Adding for testing
    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
