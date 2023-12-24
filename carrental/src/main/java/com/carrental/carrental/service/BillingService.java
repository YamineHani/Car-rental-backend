package com.carrental.carrental.service;

import com.carrental.carrental.exception.UserNotFoundException;
import com.carrental.carrental.model.Billing;
import com.carrental.carrental.model.Office;
import com.carrental.carrental.model.Reservation;
import com.carrental.carrental.repo.BillingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BillingService {
    private final BillingRepo billingRepo;

    @Autowired
    public BillingService(BillingRepo billingRepo) {
        this.billingRepo = billingRepo;
    }

    public Billing addBilling(Billing billing) {
        if(billingRepo.existsById(billing.getBillingId()) == false)
        {
            return billingRepo.save(billing);
        }
        else
        {
            return findBillingByReservation(billing.getReservation());
        }
    }

    public Billing updateBilling(Billing billing) {
        if(billingRepo.existsById(billing.getBillingId()) == true)
        {
            return billingRepo.save(billing);
        }
        else
        {
            return findBillingByReservation(billing.getReservation());
        }
    }

    public List<Billing> findAllBillings() {
        return billingRepo.findAll();
    }

    public Billing findBillingByBillingId(Integer billingId) {
        return billingRepo.findBillingByBillingId(billingId)
                .orElseThrow(() -> new UserNotFoundException("Billing by id "+ billingId + "was not found"));
    }

    public Billing findBillingByReservation(Reservation reservation) {
        return billingRepo.findBillingByReservation(reservation)
                .orElseThrow(() -> new UserNotFoundException("Billing by reservation of id "+ reservation.getReservationId() + "was not found"));
    }

    @Transactional
    public void deleteBilling(Integer billingId) {
        billingRepo.deleteBillingByBillingId(billingId);
    }
}
