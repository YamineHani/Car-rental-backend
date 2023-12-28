package com.carrental.carrental.repo;

import com.carrental.carrental.model.Car;
import com.carrental.carrental.model.Office;
import com.carrental.carrental.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationRepo extends JpaRepository<Reservation, Integer> {

    Optional<Reservation> findReservationByReservationId(Integer id);

    Optional<List<Reservation>> findReservationsByStartDate(Date startDate);

    void deleteReservationByReservationId(Integer reservationId);

    Optional<List<Reservation>> findReservationsByCar(Car car);

    /*@Query(value = "SELECT * FROM reservation WHERE plate_id IN (SELECT plate_id FROM car WHERE office_id = :officeId", nativeQuery = true)
    Optional<List<Reservation>> findReservationsByOfficeId(@Param("officeId") Integer officeId);*/
}
