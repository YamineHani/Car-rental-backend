package com.carrental.carrental.service;

import com.carrental.carrental.exception.UserNotFoundException;
import com.carrental.carrental.model.AppUser;
import com.carrental.carrental.repo.AppUserRepo;
import com.carrental.carrental.model.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepo appUserRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepo.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(String.format("user with email %s not found",email)));
    }

    public ResponseEntity<String> signUpUser(AppUser appUser) {
        boolean userExists = appUserRepo.findByEmail(appUser.getEmail()).isPresent();
        if(userExists){
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            return new ResponseEntity<>("Email Already taken", HttpStatus.UNAUTHORIZED);
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepo.save(appUser);//now we have user in database
        // TODO: SEND CONFIRMATION TOKEN
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        // TODO:SEND EMAIL
        return new ResponseEntity<>(token, HttpStatus.CREATED); //change this
    }

    public int enableAppUser(String email) {
        return appUserRepo.enableAppUser(email);
    }

}
