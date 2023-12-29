package com.carrental.carrental.controller;

import com.carrental.carrental.model.Car;
import com.carrental.carrental.model.Office;
import com.carrental.carrental.model.enums.CarStatus;
import com.carrental.carrental.service.CarService;
import com.carrental.carrental.service.OfficeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/car")
@AllArgsConstructor
@CrossOrigin("*")
public class CarResource { // This is a mirroring of whatever we have in the service
    private final CarService carService;
    private final OfficeService officeService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCars(){
        return carService.findAllCars();
    }

    //api/car/find/500
    @GetMapping("/find/plate/{plateId}")
    public ResponseEntity<?> getCarByPlateId(@PathVariable("plateId") Long plateId){
        return carService.findCarByPlateId(plateId);
    }

    @GetMapping("/find/brand/{brand}")
    public ResponseEntity<?> getCarsByBrand(@PathVariable("brand") String brand){
        return carService.findCarsByBrand(brand);
    }

    @GetMapping("/find/type/{type}")
    public ResponseEntity<?> getCarsByType(@PathVariable("type") String type){
        return carService.findCarsByType(type);
    }

    @GetMapping("/find/year/{year}")
    public ResponseEntity<?> getCarByYear(@PathVariable("year") Integer year){
        return carService.findCarsByYear(year);
    }

    @GetMapping("/find/transmission/{transmissionType}")
    public ResponseEntity<?> getCarsByTransmissionType(@PathVariable("transmissionType") String transmissionType){
        return carService.findCarsByTransmissionType(transmissionType);
    }

    @GetMapping("/find/fuel/{fuelType}")
    public ResponseEntity<?> getCarsByFuelType(@PathVariable("fuelType") String fuelType){
        return carService.findCarsByFuelType(fuelType);
    }

    @GetMapping("/find/body/{bodyStyle}")
    public ResponseEntity<?> getCarsByBodyStyle(@PathVariable("bodyStyle") String bodyStyle){
        return carService.findCarsByBodyStyle(bodyStyle);
    }

    @GetMapping("/find/color/{color}")
    public ResponseEntity<?> getCarsByColor(@PathVariable("color") String color){
        return carService.findCarsByColor(color);
    }

    @GetMapping("/find/capacity/{capacity}")
    public ResponseEntity<?> getCarsByCapacity(@PathVariable("capacity") Integer capacity){
        return carService.findCarsByCapacity(capacity);
    }

    @GetMapping("/find/rate/{rate}")
    public ResponseEntity<?> getCarsByRate(@PathVariable("rate") Float rate){
        return carService.findCarsByRate(rate);
    }

    @GetMapping("/find/status/{status}")
    public ResponseEntity<?> getCarsByStatus(@PathVariable("status") CarStatus status){
        return carService.findCarsByStatus(status);
    }

    @GetMapping("/find/office/{officeId}")
    public ResponseEntity<?> getCarsByOfficeId(@PathVariable("officeId") Integer officeId){
        if(officeService.findOfficeByOfficeId(officeId).getStatusCode() == HttpStatus.OK)
        {
            Optional<Office> office = (Optional<Office>)officeService.findOfficeByOfficeId(officeId).getBody();
            return carService.findCarsByOffice(office.get());
        }
        return new ResponseEntity<>("No office with id " + officeId, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCar(@RequestBody Car car){
        return carService.updateCar(car);
    }

    @DeleteMapping("/delete/{plateId}")
    public ResponseEntity<?> deleteCar(@PathVariable("plateId") Long plateId){
        carService.deleteCar(plateId);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }


}
