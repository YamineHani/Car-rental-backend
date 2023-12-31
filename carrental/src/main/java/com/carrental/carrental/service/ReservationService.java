package com.carrental.carrental.service;

import com.carrental.carrental.model.Car;
import com.carrental.carrental.model.Reservation;
import com.carrental.carrental.model.User;
import com.carrental.carrental.model.enums.CarStatus;
import com.carrental.carrental.repo.CarRepo;
import com.carrental.carrental.repo.ReservationRepo;
import com.carrental.carrental.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepo reservationRepo;
    private final CarRepo carRepo;
    private final UserRepo userRepo;

    @Autowired
    public ReservationService(ReservationRepo reservationRepo, CarRepo carRepo,
                              UserRepo userRepo){
        this.reservationRepo = reservationRepo;
        this.carRepo = carRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public ResponseEntity<String> addReservation(Reservation reservation) {
        Optional<Car> carOptional = carRepo.findCarByPlateId(reservation.getCar().getPlateId());
        Optional<User> userOptional = userRepo.findById(reservation.getUser().getId());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(carOptional.isPresent()){
                Car car = carOptional.get();
                if(car.getStatus() == CarStatus.ACTIVE){
                    car.setStatus(CarStatus.RENTED);
                    Reservation newReservation = new Reservation(reservation.getStartDate(),
                            reservation.getEndDate(), car, user, reservation.getMethod());
                    newReservation.calculateBill(car);
                    reservationRepo.save(newReservation);
                    return new ResponseEntity<>("Successfully reserved", HttpStatus.OK);
                }
                return new ResponseEntity<>("Car selected is unavailable for now",
                        HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>("No car with plate id " + reservation.getCar().getPlateId()
                    + " was found", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("No user with id " + reservation.getUser().getId()
                + " was found", HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> findAllReservations() {
        List<Reservation> reservations = reservationRepo.findAll();
        if(reservations.isEmpty())
        {
            return new ResponseEntity<>("No reservations currently exist", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

//    public ResponseEntity<String> updateReservation(Reservation reservation) { //not done
//        Optional<Reservation> optionalReservation = reservationRepo.
//                findReservationByReservationId(reservation.getReservationId());
//        if(optionalReservation.isPresent()){
//            Reservation newReservation = optionalReservation.get();
//            Optional<Car> optionalCar = carRepo.findCarByPlateId(reservation.getCar().getPlateId());
//            if(optionalCar.isPresent()){
//                Car car = optionalCar.get();
//            }
//        }
//        reservationRepo.save(reservation);
//        return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
//    }

    @Transactional //will recheck this after deciding criteria for delete
    public ResponseEntity<String> deleteReservation(Integer reservationId) {
        if(reservationRepo.findReservationByReservationId(reservationId).isPresent()){
            reservationRepo.deleteReservationByReservationId(reservationId);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Reservation with id "+reservationId+" is not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> findReservationByReservationId(Integer reservationId) {
        Optional<Reservation> optionalReservation = reservationRepo.findReservationByReservationId(reservationId);
        if(optionalReservation.isPresent())
        {
            Reservation reservation = optionalReservation.get();
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        }
        return new ResponseEntity<>("No reservation with id " + reservationId + " was found", HttpStatus.UNAUTHORIZED);
    }

//    public ResponseEntity<?> findReservationsByStartDate(Date startDate) {
//        Optional<List<Reservation>> optionalReservations = reservationRepo.findReservationsByStartDate(startDate);
//        if(optionalReservations.isEmpty())
//        {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .contentType(MediaType.TEXT_PLAIN)
//                    .body("No reservation starting on " + startDate + " was found");
//        }
//        List<Reservation> reservations = optionalReservations.get();
//        return new ResponseEntity<>(reservations, HttpStatus.OK);
//    }

    public ResponseEntity<?> findReservationsByCar(Long plateId) {
        Optional<Car> optionalCar = carRepo.findCarByPlateId(plateId);
        if (optionalCar.isPresent()){
            Car newCar = optionalCar.get();
            List<Reservation> reservations = reservationRepo.findReservationsByCar(newCar);
            if(!reservations.isEmpty())
            {
                return new ResponseEntity<>(reservations, HttpStatus.OK);
            }
            return new ResponseEntity<>("No reservation for car with plate id " +
                    newCar.getPlateId() + " was found", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("No car with plate id " + plateId +
                " was found", HttpStatus.UNAUTHORIZED);
    }
}
