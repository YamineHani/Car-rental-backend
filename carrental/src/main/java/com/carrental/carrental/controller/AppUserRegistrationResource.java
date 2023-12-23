package com.carrental.carrental.controller;

import com.carrental.carrental.exception.UserNotFoundException;
import com.carrental.carrental.model.AppUserRegistrationRequest;
import com.carrental.carrental.service.AppUserRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class AppUserRegistrationResource {

    private AppUserRegistrationService appUserRegistrationService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody AppUserRegistrationRequest request) throws UserNotFoundException {
        return appUserRegistrationService.register(request);
    }
}
