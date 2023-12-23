package com.carrental.carrental.service;

import com.carrental.carrental.model.AppUser;
import com.carrental.carrental.model.AppUserRegistrationRequest;
import com.carrental.carrental.model.AppUserRole;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserRegistrationService {

    private final EmailValidatorService emailValidatorService;
    private final AppUserService appUserService;
    public ResponseEntity<String> register(AppUserRegistrationRequest request) {
        boolean isValidEmail = emailValidatorService.test(request.getEmail()); //validate request email
        if(!isValidEmail) {
            return new ResponseEntity<>("email not valid", HttpStatus.FORBIDDEN);
        }
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}
