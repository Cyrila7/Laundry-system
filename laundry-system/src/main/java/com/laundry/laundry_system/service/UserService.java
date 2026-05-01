package com.laundry.laundry_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.laundry.laundry_system.dto.RegisterRequest;
import com.laundry.laundry_system.model.Role;
import com.laundry.laundry_system.model.User;
import com.laundry.laundry_system.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
   
    public void registerUser(RegisterRequest request) {

    
    if(userRepository.findByEmail(request.getEmail()).isPresent()){
        throw new RuntimeException("Email already exists");
    }
    
    String hashedPassword = passwordEncoder.encode(request.getPassword());
    
    
    User user = new User();
    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());
    user.setPassword(hashedPassword);
    user.setEmail(request.getEmail());
    user.setRole(Role.USER);
   
    userRepository.save(user);
}
   public User loginUser(String email, String password) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));
    
    if (!passwordEncoder.matches(password, user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }
    
    return user;
}
}
