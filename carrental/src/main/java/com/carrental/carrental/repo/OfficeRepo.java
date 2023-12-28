package com.carrental.carrental.repo;

import com.carrental.carrental.model.Car;
import com.carrental.carrental.model.Office;
import com.carrental.carrental.model.enums.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface OfficeRepo extends JpaRepository<Office,Integer> {
    Optional<List<Office>> findOfficesByCountry(String country);

    void deleteOfficeByOfficeId(Integer officeId);

    Optional<Office> findOfficeByOfficeId(Integer id);

    Optional<List<Office>> findOfficesByCity(String city);

    Optional<List<Office>> findOfficesByBranch(Branch branch);

    /*@Query(value = "SELECT plate_id FROM car WHERE office_id = :officeId", nativeQuery = true)
    Optional<List<Long>> findOfficePlateIds(@Param("officeId") Integer officeId);*/
}