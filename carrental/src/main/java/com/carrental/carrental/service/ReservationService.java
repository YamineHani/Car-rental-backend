package com.carrental.carrental.service;

import com.carrental.carrental.exception.UserNotFoundException;
import com.carrental.carrental.model.Car;
import com.carrental.carrental.model.Office;
import com.carrental.carrental.model.Reservation;
import com.carrental.carrental.repo.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepo reservationRepo;

    @Autowired
    public ReservationService(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    public Reservation addReservation(Reservation reservation) {
        return reservationRepo.save(reservation);
    }

    public Reservation updateReservation(Reservation reservation) {
        if(reservationRepo.existsById(reservation.getReservationId()) == false)
        {
            return reservationRepo.save(reservation);
        }
        else
        {
            return findReservationByReservationId(reservation.getReservationId());
        }
    }

    public List<Reservation> findAllReservations() {
        return reservationRepo.findAll();
    }

    public Reservation findReservationByReservationId(Integer reservationId) {
        return reservationRepo.findReservationByReservationId(reservationId)
                .orElseThrow(() -> new UserNotFoundException("Reservation by id "+ reservationId + "was not found"));
    }

    public List<Reservation> findReservationsByStartDate(Date startDate) {
        return reservationRepo.findReservationsByStartDate(startDate)
                .orElseThrow(() -> new UserNotFoundException("Reservation by start date "+ startDate + "was not found"));
    }

    public List<Reservation> findReservationsByCar(Car car) {
        return reservationRepo.findReservationsByCar(car)
                .orElseThrow(() -> new UserNotFoundException("Reservation by car of id "+ car.getPlateId() + "was not found"));
    }

    public List<Reservation> findReservationsByOffice(Office office) {
        return reservationRepo.findReservationsByOffice(office)
                .orElseThrow(() -> new UserNotFoundException("Reservation by office of id "+ office.getOfficeId() + "was not found"));
    }

    @Transactional
    public void deleteReservation(Integer reservationId) {
        reservationRepo.deleteReservationByReservationId(reservationId);
    }
}
