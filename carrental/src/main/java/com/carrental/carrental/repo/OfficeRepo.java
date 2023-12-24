package com.carrental.carrental.repo;

import com.carrental.carrental.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OfficeRepo extends JpaRepository<Office,Integer> {
    Optional<List<Office>> findOfficesByCountry(String country);

    void deleteOfficeByOfficeId(Integer officeId);

    Optional<Office> findOfficeByOfficeId(Integer id);

    Optional<List<Office>> findOfficesByCity(String city);
}
