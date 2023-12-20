package com.carrental.carrental.repo;

import com.carrental.carrental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepo extends JpaRepository<Car,Long> {
    void deleteCarByPlateId(Long plateId);

    Optional<Car> findCarByPlateId(Long plateId);
}
