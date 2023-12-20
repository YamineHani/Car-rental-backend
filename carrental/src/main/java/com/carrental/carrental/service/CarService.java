package com.carrental.carrental.service;

import com.carrental.carrental.exception.UserNotFoundException;
import com.carrental.carrental.model.Car;
import com.carrental.carrental.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class CarService {
    private final CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public Car addCar(Car car){
        car.setBrand(UUID.randomUUID().toString());
        return carRepo.save(car);
    }

    public List<Car> findAllCars(){
        return carRepo.findAll();
    }

    public Car updateCar(Car car){
        return carRepo.save(car);
    }

    public void deleteCar(Long plateId){
        carRepo.deleteCarByPlateId(plateId);
    }

    public Car findCarByPlateId(Long plateId){
        return carRepo.findCarByPlateId(plateId)
                .orElseThrow(() -> new UserNotFoundException("car by id "+ plateId + "was not found"));
    }
}
