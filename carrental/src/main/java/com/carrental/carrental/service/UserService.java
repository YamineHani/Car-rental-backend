package com.carrental.carrental.service;

import com.carrental.carrental.model.*;
import com.carrental.carrental.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailValidatorService emailValidatorService;
    private final EmailService emailService;

    public ResponseEntity<String> register(RegistrationRequest request) {
        boolean isValidEmail = emailValidatorService.test(request.getEmail()); //validate request email
        if(!isValidEmail) {
            return new ResponseEntity<>("email not valid", HttpStatus.UNAUTHORIZED);
        }
        ResponseEntity<String> responseEntity  = createUser(new User(request.getFirstName(),
                request.getLastName(), request.getEmail(), request.getPassword(), UserRole.USER));
        if(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.CREATED)){
            String token = responseEntity.getBody();
            String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
            emailService.send(request.getEmail(), emailService.buildEmail(request.getFirstName(), link));
            return new ResponseEntity<>(token,HttpStatus.OK);
        }
        return new ResponseEntity<>("user already exists",HttpStatus. UNAUTHORIZED);
    }

    @Transactional
    public ResponseEntity<String> confirmToken(String token) {

        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token).orElse(null);
        if(confirmationToken == null){
            return new ResponseEntity<>("token not found", HttpStatus.UNAUTHORIZED);
        }

        if (confirmationToken.getConfirmedAt() != null) {
            return new ResponseEntity<>("email already confirmed", HttpStatus.UNAUTHORIZED);
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            return new ResponseEntity<>("token expired", HttpStatus.UNAUTHORIZED);
        }

        confirmationTokenService.setConfirmedAt(token);
        enableAppUser(confirmationToken.getUser().getEmail());

        return new ResponseEntity<>("confirmed", HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(String.format("user with email %s not found",email)));
    }

    private ResponseEntity<String> createUser(User user) {
        boolean userExists = userRepo.findByEmail(user.getEmail()).isPresent();
        if(userExists){
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            return new ResponseEntity<>("Email Already taken", HttpStatus.UNAUTHORIZED);
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);//now we have user in database
        // TODO: SEND CONFIRMATION TOKEN
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15), user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        // TODO:SEND EMAIL
        return new ResponseEntity<>(token, HttpStatus.CREATED); //change this
    }

    public int enableAppUser(String email) {
        return userRepo.enableUser(email);
    }
}
