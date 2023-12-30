package com.carrental.carrental.controller;

import com.carrental.carrental.model.Billing;
import com.carrental.carrental.model.Reservation;
import com.carrental.carrental.model.enums.BillingStatus;
import com.carrental.carrental.model.enums.Method;
import com.carrental.carrental.service.BillingService;
import com.carrental.carrental.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/billing")
@CrossOrigin("*")
public class BillingResource {
    private final BillingService billingService;
    private final ReservationService reservationService;

    public BillingResource(BillingService billingService, ReservationService reservationService) {
        this.billingService = billingService;
        this.reservationService = reservationService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBillings() {
        return billingService.findAllBillings();
    }

    @GetMapping("/find/id/{billingId}")
    public ResponseEntity<?> getBillingByBillingId(@PathVariable("billingId") Integer billingId) {
        return billingService.findBillingByBillingId(billingId);
    }

    @GetMapping("/find/reservation/{reservationId}")
    public ResponseEntity<?> getBillingByReservationId(@PathVariable("reservationId") Integer reservationId) {
        if(reservationService.findReservationByReservationId(reservationId).getStatusCode() == HttpStatus.OK)
        {
            Reservation reservation = (Reservation)reservationService.findReservationByReservationId(reservationId).getBody();
            return billingService.findBillingByReservation(reservation);
        }
        return new ResponseEntity<>("No reservation with id " + reservationId, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/method/{method}")
    public ResponseEntity<?> getBillingsByMethod(@PathVariable("method") Method method) {
        return billingService.findBillingsByMethod(method);
    }

    @GetMapping("/find/status/{status}")
    public ResponseEntity<?> getBillingsByStatus(@PathVariable("status") BillingStatus status) {
        return billingService.findBillingsByStatus(status);
    }

    //todo messaging repeated reservation ids
    @PostMapping("/add")
    public ResponseEntity<?> addBilling(@RequestBody Billing billing){
        if(reservationService.findReservationByReservationId(billing.getReservation().getReservationId()).getStatusCode() == HttpStatus.OK)
        {
            /*Optional<Reservation> reservationFound = (Optional<Reservation>)reservationService.findReservationByReservationId(billing.getReservationId()).getBody();
            Optional<Billing> billingFound = (Optional<Billing>)billingService.findBillingByReservation(reservationFound.get()).getBody();
            if(billingFound.isPresent())
            {
                return new ResponseEntity<>("A billing of id " + billingFound.get().getBillingId() + " already exists for reservation of id " + billing.getReservationId(), HttpStatus.CONFLICT);
            }*/
            return billingService.addBilling(billing);
        }
        return new ResponseEntity<>("No reservation with id " + billing.getReservation().getReservationId() + " was found", HttpStatus.CONFLICT);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBilling(@RequestBody Billing billing){
        if(billingService.findBillingByBillingId(billing.getBillingId()).getStatusCode() == HttpStatus.OK)
        {
            return billingService.updateBilling(billing);
        }
        return new ResponseEntity<>("No billing of id " + billing.getBillingId() + " was found", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{billingId}")
    public ResponseEntity<?> deleteBilling(@PathVariable("billingId") Integer billingId){
        billingService.deleteBilling(billingId);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
}
