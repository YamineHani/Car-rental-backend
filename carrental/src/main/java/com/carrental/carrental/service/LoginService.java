package com.carrental.carrental.service;

import com.carrental.carrental.model.AppUser;
import com.carrental.carrental.model.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder passwordEncoder;
//
//    public ResponseEntity<String> login(LoginRequest request) {
//        // Validate credentials
//        boolean isValidCredentials = validateCredentials(request);
//
//        if (!isValidCredentials) {
//            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
//        }
//
//        // TODO: Generate authentication token or perform other login-related tasks
//
//        return new ResponseEntity<>("Login successful", HttpStatus.OK);
//    }

//    private boolean validateCredentials(LoginRequest request) {
//        // Fetch user by username/email
//       // AppUser user = appUserService.loadUserByUsername(request.getEmail());
//        // Check if user exists and password matches
//        return user != null && passwordEncoder.matches(request.getPassword(), user.getPassword());
//    }

}
