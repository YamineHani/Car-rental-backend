package com.carrental.carrental.repo;

import com.carrental.carrental.model.Car;
import com.carrental.carrental.model.Office;
import com.carrental.carrental.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationRepo extends JpaRepository<Reservation, Integer> {

    Optional<Reservation> findReservationByReservationId(Integer id);

    Optional<List<Reservation>> findReservationsByStartDate(Date startDate);

    void deleteReservationByReservationId(Integer reservationId);

    Optional<List<Reservation>> findReservationsByCar(Car car);

    Optional<List<Reservation>> findReservationsByOffice(Office office);
}
