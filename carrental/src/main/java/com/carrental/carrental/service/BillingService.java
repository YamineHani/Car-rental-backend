package com.carrental.carrental.service;

import com.carrental.carrental.model.Billing;
import com.carrental.carrental.model.Reservation;
import com.carrental.carrental.model.enums.BillingStatus;
import com.carrental.carrental.model.enums.Method;
import com.carrental.carrental.repo.BillingRepo;
import com.carrental.carrental.repo.CarRepo;
import com.carrental.carrental.repo.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BillingService {
    private final BillingRepo billingRepo;
    private final ReservationRepo reservationRepo;
    private final CarRepo carRepo;

    @Autowired
    public BillingService(BillingRepo billingRepo, ReservationRepo reservationRepo, CarRepo carRepo) {
        this.billingRepo = billingRepo;
        this.reservationRepo = reservationRepo;
        this.carRepo = carRepo;
    }

    public ResponseEntity<?> addBilling(Billing billing) {
        billingRepo.save(billing);
        return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
        //return new ResponseEntity<>("Can't create this billing\nCheck your inputs", HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> findAllBillings() {
        List<Billing> billings = billingRepo.findAll();
        if(billings.isEmpty())
        {
            return new ResponseEntity<>("No billings currently existing", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(billings, HttpStatus.OK);
    }

    //todo handling updating description
    public ResponseEntity<?> updateBilling(Billing billing) {
        /*if(reservationRepo.findReservationByReservationId(billing.getReservationId()).get().getEndDate() != null)
        {
            System.out.println("here");
            Long carId = billing.getReservation().getPlateId();
            Integer officeId = carRepo.findCarByPlateId(billing.getReservation().getPlateId()).get().getOfficeId();
            Float price = carRepo.findCarByPlateId(billing.getReservation().getPlateId()).get().getRate();
            Integer daysReserved = billing.getReservation().getDays();
            Long daysTotal = ((billing.getReservation().getEndDate().getTime() - billing.getReservation().getStartDate().getTime()) / (1000*60*60*24)) % 365;
            Long daysLate = (daysTotal - daysReserved) > 0? (daysTotal - daysReserved): 0;
            Double penalty = price * 0.12;
            Double payment = (daysReserved * price) + (daysLate * penalty);
            String description = "For car of plate id = " + carId + "\nOffice No. " + officeId + "\nRate/Day = " + price + " $\nTotal number of reserved days = " + daysReserved +
                    " days\nNumber of late days = " + daysLate +
                    " days\nLatency penalty/Day = " + penalty + "$\nTotal amount to be paid = " + payment + "$\nPayment method: " + billing.getMethod().getDisplayName();
            billing.setDescription(description);
        }*/
        billingRepo.save(billing);
        return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
    }

    @Transactional
    public void deleteBilling(Integer billingId) {
        billingRepo.deleteBillingByBillingId(billingId);
    }

    public ResponseEntity<?> findBillingByBillingId(Integer billingId) {
        Optional<Billing> billing = billingRepo.findBillingByBillingId(billingId);
        if(billing.isPresent())
        {
            return new ResponseEntity<>(billing, HttpStatus.OK);
        }
        return new ResponseEntity<>("No billing with id " + billingId, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findBillingByReservation(Reservation reservation) {
        Optional<Billing> billing = billingRepo.findBillingByReservation(reservation);
        if(billing.isPresent())
        {
            return new ResponseEntity<>(billing, HttpStatus.OK);
        }
        return new ResponseEntity<>("No billing for reservation of id " + reservation.getReservationId(), HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findBillingsByMethod(Method method) {
        Optional<List<Billing>> billings = billingRepo.findBillingsByMethod(method);
        if(billings.isPresent())
        {
            return new ResponseEntity<>(billings, HttpStatus.OK);
        }
        return new ResponseEntity<>("No billing with " + method.getDisplayName() + " payment method was found", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> findBillingsByStatus(BillingStatus status) {
        Optional<List<Billing>> billings = billingRepo.findBillingsByStatus(status);
        if(billings.isPresent())
        {
            return new ResponseEntity<>(billings, HttpStatus.OK);
        }
        return new ResponseEntity<>("No billing with " + status.getDisplayName() + " status was found", HttpStatus.NO_CONTENT);
    }
}
