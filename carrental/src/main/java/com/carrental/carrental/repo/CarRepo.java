package com.carrental.carrental.repo;

import com.carrental.carrental.model.Car;
import com.carrental.carrental.model.Office;
import com.carrental.carrental.model.enums.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepo extends JpaRepository<Car,Long> {
    void deleteCarByPlateId(Long plateId);

    Optional<Car> findCarByPlateId(Long plateId);

    Optional<List<Car>> findCarsByBrand(String brand);

    Optional<List<Car>> findCarsByColor(String color);

    Optional<List<Car>> findCarsByYear(Integer year);

    Optional<List<Car>> findCarsByType(String type);

    Optional<List<Car>> findCarsByTransmissionType(String transmissionType);

    Optional<List<Car>> findCarsByFuelType(String fuelType);

    Optional<List<Car>> findCarsByBodyStyle(String bodyStyle);

    Optional<List<Car>> findCarsByCapacity(Integer capacity);

    Optional<List<Car>> findCarsByRate(Float rate);

    Optional<List<Car>> findCarsByStatus(CarStatus status);

    Optional<List<Car>> findCarsByOffice(Office office);

    @Query(value = "SELECT DISTINCT brand FROM car", nativeQuery = true)
    Optional<List<String>> findAllBrands();

    @Query(value = "SELECT DISTINCT type FROM car", nativeQuery = true)
    Optional<List<String>> findAllCarTypes();

    @Query(value = "SELECT DISTINCT year FROM car", nativeQuery = true)
    Optional<List<String>> findAllYears();

    @Query(value = "SELECT DISTINCT transmission_type FROM car", nativeQuery = true)
    Optional<List<String>> findAllTransmissionTypes();

    @Query(value = "SELECT DISTINCT fuel_type FROM car", nativeQuery = true)
    Optional<List<String>> findAllFuelTypes();

    @Query(value = "SELECT DISTINCT body_style FROM car", nativeQuery = true)
    Optional<List<String>> findAllBodyStyles();

    @Query(value = "SELECT DISTINCT color FROM car", nativeQuery = true)
    Optional<List<String>> findAllColors();

    @Query(value = "SELECT MAX(rate) color FROM car", nativeQuery = true)
    Optional<Float> findMaxRate();

    @Query(value = "SELECT * FROM car WHERE rate <= ?1", nativeQuery = true)
    Optional<List<Car>> findCarsBellowRate(Float rate);
}
