package com.carrental.carrental.controller;

import com.carrental.carrental.model.Car;
import com.carrental.carrental.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarResource { // This is a mirroring of whatever we have in the service
    private final CarService carService;

    public CarResource(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars(){
        List<Car> cars = carService.findAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    //api/car/find/500
    @GetMapping("/find/{plateId}")
    public ResponseEntity<Car> getCarByPlateId(@PathVariable("plateId") Long plateId){
        Car car = carService.findCarByPlateId(plateId);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        Car newCar = carService.addCar(car);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Car> updateCar(@RequestBody Car car){
        Car updateCar = carService.updateCar(car);
        return new ResponseEntity<>(updateCar, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{plateId}")
    public ResponseEntity<?> deleteCar(@PathVariable("plateId") Long plateId){
        carService.deleteCar(plateId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
