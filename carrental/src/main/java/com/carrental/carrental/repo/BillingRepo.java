package com.carrental.carrental.repo;

import com.carrental.carrental.model.Billing;
import com.carrental.carrental.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillingRepo extends JpaRepository<Billing, Integer> {
    Optional<Billing> findBillingByReservation(Reservation reservation);

    Optional<Billing> findBillingByBillingId(Integer billingId);

    void deleteBillingByBillingId(Integer billingId);
}
