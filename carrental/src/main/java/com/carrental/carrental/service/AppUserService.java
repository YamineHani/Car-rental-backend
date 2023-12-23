package com.carrental.carrental.service;

import com.carrental.carrental.exception.UserNotFoundException;
import com.carrental.carrental.model.AppUser;
import com.carrental.carrental.repo.AppUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepo appUserRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepo.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(String.format("user with email %s not found",email)));
    }

    public ResponseEntity<String> signUpUser(AppUser appUser) throws UserNotFoundException {
        boolean userExists = appUserRepo.findByEmail(appUser.getEmail()).isPresent();
        if(userExists){
            return new ResponseEntity<>("Email Already taken", HttpStatus.FORBIDDEN);
            //System.out.println("email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepo.save(appUser);//now we have user in database
        // TODO: SEND CONFIRMATION TOKEN
        return new ResponseEntity<>("it works", HttpStatus.CREATED); //change this
    }

}
