package com.carrental.carrental.service;

import com.carrental.carrental.model.Car;
import com.carrental.carrental.model.Office;
import com.carrental.carrental.model.enums.CarStatus;
import com.carrental.carrental.repo.CarRepo;
import com.carrental.carrental.repo.OfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepo carRepo;
    private final OfficeRepo officeRepo;

    @Autowired
    public CarService(CarRepo carRepo, OfficeRepo officeRepo) {
        this.carRepo = carRepo;
        this.officeRepo = officeRepo;
    }

    public ResponseEntity<String> addCar(Car car){ //edit save part
        if(!carRepo.existsById(car.getPlateId()))
        {
            if(officeRepo.existsById(car.getOffice().getOfficeId()))
            {
                carRepo.save(car);
                return new ResponseEntity<>("Successfully added", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("No office with id " + car.getOffice().getOfficeId(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Car with plate id " + car.getPlateId() + " already exists", HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> findAllCars(){
        List<Car> cars = carRepo.findAll();
        if(cars.isEmpty())
        {
            return new ResponseEntity<>("No cars currently existing", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    public ResponseEntity<?> updateCar(Car car){
        if(carRepo.existsById(car.getPlateId()))
        {
            if(officeRepo.existsById(car.getOffice().getOfficeId()))
            {
                carRepo.save(car);
                return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
            }
            return new ResponseEntity<>("No office with id " + car.getOffice().getOfficeId(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("No car with plate id " + car.getPlateId(), HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findBrands() {
        Optional<List<String>> brands = carRepo.findAllBrands();
        if(brands.isPresent())
        {
            return new ResponseEntity<>(brands, HttpStatus.OK);
        }
        return new ResponseEntity<>("No brands found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findTypes() {
        Optional<List<String>> types = carRepo.findAllCarTypes();
        if(types.isPresent())
        {
            return new ResponseEntity<>(types, HttpStatus.OK);
        }
        return new ResponseEntity<>("No types found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findYears() {
        Optional<List<String>> years = carRepo.findAllYears();
        if(years.isPresent())
        {
            return new ResponseEntity<>(years, HttpStatus.OK);
        }
        return new ResponseEntity<>("No years found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findTransmissions() {
        Optional<List<String>> transmissions = carRepo.findAllTransmissionTypes();
        if(transmissions.isPresent())
        {
            return new ResponseEntity<>(transmissions, HttpStatus.OK);
        }
        return new ResponseEntity<>("No transmission types found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findFuels() {
        Optional<List<String>> fuels = carRepo.findAllFuelTypes();
        if(fuels.isPresent())
        {
            return new ResponseEntity<>(fuels, HttpStatus.OK);
        }
        return new ResponseEntity<>("No fuel types found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findBodyStyles() {
        Optional<List<String>> styles = carRepo.findAllBodyStyles();
        if(styles.isPresent())
        {
            return new ResponseEntity<>(styles, HttpStatus.OK);
        }
        return new ResponseEntity<>("No body styles found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findColors() {
        Optional<List<String>> colors = carRepo.findAllColors();
        if(colors.isPresent())
        {
            return new ResponseEntity<>(colors, HttpStatus.OK);
        }
        return new ResponseEntity<>("No colors found", HttpStatus.NO_CONTENT);
    }

    @Transactional
    public void deleteCar(Long plateId){
        carRepo.deleteCarByPlateId(plateId);
    }

    public ResponseEntity<?> findCarByPlateId(Long plateId){
        Optional<Car> car = carRepo.findCarByPlateId(plateId);
        if(car.isPresent())
        {
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        return new ResponseEntity<>("No car with plate id " + plateId, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findCarsByBrand(String brand) {
        Optional<List<Car>> cars = carRepo.findCarsByBrand(brand);
        if(cars.isPresent())
        {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>("No car with " + brand + " brand was found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findCarsByType(String type) {
        Optional<List<Car>> cars = carRepo.findCarsByType(type);
        if(cars.isPresent())
        {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>("No car with " + type + " type was found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findCarsByYear(Integer year) {
        Optional<List<Car>> cars = carRepo.findCarsByYear(year);
        if(cars.isPresent())
        {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>("No car with " + year + " year was found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findCarsByTransmissionType(String transmissionType) {
        Optional<List<Car>> cars = carRepo.findCarsByTransmissionType(transmissionType);
        if(cars.isPresent())
        {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>("No car with " + transmissionType + " transmission type was found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findCarsByFuelType(String fuelType) {
        Optional<List<Car>> cars = carRepo.findCarsByFuelType(fuelType);
        if(cars.isPresent())
        {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>("No car with " + fuelType + " fuel type was found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findCarsByBodyStyle(String bodyStyle) {
        Optional<List<Car>> cars = carRepo.findCarsByBodyStyle(bodyStyle);
        if(cars.isPresent())
        {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>("No car with " + bodyStyle + " body style was found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findCarsByColor(String color) {
        Optional<List<Car>> cars = carRepo.findCarsByColor(color);
        if(cars.isPresent())
        {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>("No car with " + color + " color was found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findCarsByCapacity(Integer capacity) {
        Optional<List<Car>> cars = carRepo.findCarsByCapacity(capacity);
        if(cars.isPresent())
        {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>("No car with " + capacity + " seats capacity was found", HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> findCarsByRate(Float rate) {
        Optional<List<Car>> cars = carRepo.findCarsByRate(rate);
        if(cars.isPresent())
        {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>("No car with " + rate + " $ rate was found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findCarsBellowRate(Float rate) {
        Optional<List<Car>> cars = carRepo.findCarsBellowRate(rate);
        if(cars.isPresent())
        {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>("No car with " + rate + " $ rate was found", HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> findCarsByStatus(CarStatus status) {
        Optional<List<Car>> cars = carRepo.findCarsByStatus(status);
        if(cars.isPresent())
        {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>("No " + status.getDisplayName() + " car was found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findCarsByOffice(Office office) {
        Optional<List<Car>> cars = carRepo.findCarsByOffice(office);
        if(cars.isPresent())
        {
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>("No car registered for office with id " + office.getOfficeId() + " was found", HttpStatus.NO_CONTENT);
    }
}
