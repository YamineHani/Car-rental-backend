package com.carrental.carrental.repo;


import com.carrental.carrental.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true) //MIGHT REMOVE THIS
public interface AppUserRepo extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByEmail(String email);

}