package com.carrental.carrental.service;

import com.carrental.carrental.exception.UserNotFoundException;
import com.carrental.carrental.model.Car;
import com.carrental.carrental.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarService {
    private final CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public Car addCar(Car car){
        if(carRepo.existsById(car.getPlateId()) == false)
        {
            return carRepo.save(car);
        }
        else
        {
            return findCarByPlateId(car.getPlateId());
        }
    }

    public List<Car> findAllCars(){
        return carRepo.findAll();
    }

    public Car updateCar(Car car){
        if(carRepo.existsById(car.getPlateId()) == true)
        {
            return carRepo.save(car);
        }
        else
        {
            return findCarByPlateId(car.getPlateId());
        }
    }

    @Transactional
    public void deleteCar(Long plateId){
        carRepo.deleteCarByPlateId(plateId);
    }

    public Car findCarByPlateId(Long plateId){
        return carRepo.findCarByPlateId(plateId)
                .orElseThrow(() -> new UserNotFoundException("Car by id "+ plateId + " was not found"));
    }
    public List<Car> findCarsByBrand(String brand) {
        return carRepo.findCarsByBrand(brand)
                .orElseThrow(() -> new UserNotFoundException("Cars of brand "+ brand + " was not found"));
    }
    public List<Car> findCarsByColor(String color) {
        return carRepo.findCarsByColor(color)
                .orElseThrow(() -> new UserNotFoundException("Cars of "+ color + " color was not found"));
    }
    public List<Car> findCarsByYear(Integer year) {
        return carRepo.findCarsByYear(year)
                .orElseThrow(() -> new UserNotFoundException("Cars of year "+ year + " was not found"));
    }
}
