package com.carrental.carrental.repo;

import com.carrental.carrental.model.Billing;
import com.carrental.carrental.model.Reservation;
import com.carrental.carrental.model.enums.BillingStatus;
import com.carrental.carrental.model.enums.Method;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BillingRepo extends JpaRepository<Billing, Integer> {

    /*@Override
    List<Billing> findAll();*/
    Optional<Billing> findBillingByReservation(Reservation reservation);

    Optional<Billing> findBillingByBillingId(Integer billingId);

    void deleteBillingByBillingId(Integer billingId);

    Optional<List<Billing>> findBillingsByMethod(Method method);

    Optional<List<Billing>> findBillingsByStatus(BillingStatus status);
}
