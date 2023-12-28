package com.carrental.carrental.model;

import com.carrental.carrental.model.enums.BillingStatus;
import com.carrental.carrental.model.enums.Method;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Billing implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer billingId;
    @OneToOne()
    @JoinColumn(name = "reservationId", nullable = false)
    private Reservation reservation;
    @Column(nullable = false)
    private Method method;
    @Column(nullable = false)
    private BillingStatus status;
    //@Column(nullable = false)
    private String description;

    public Billing() {
    }

    public Billing(Integer billingId, Reservation reservation, Method method, BillingStatus status, String description) {
        this.billingId = billingId;
        this.reservation = reservation;
        this.method = method;
        this.status = status;
        this.description = description;
    }

    public Integer getBillingId() {
        return billingId;
    }

    public void setBillingId(Integer billingId) {
        this.billingId = billingId;
    }

    public Integer getReservationId() {
        return this.reservation.getReservationId();
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    /*public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }*/

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public BillingStatus getStatus() {
        return status;
    }

    public void setStatus(BillingStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public void setDescription() {
        Long carId = reservation.getCar().getPlateId();
        Integer officeId = reservation.getOffice().getOfficeId();
        Float price = reservation.getCar().getRate();
        Integer daysReserved = reservation.getDays();
        Long daysTotal = ((reservation.getEndDate().getTime() - reservation.getStartDate().getTime()) / (1000*60*60*24)) % 365;
        Long daysLate = (daysTotal - daysReserved) > 0? (daysTotal - daysReserved): 0;
        Double penalty = price * 0.12;
        Double payment = (daysReserved * price) + (daysLate * penalty);
        this.description = "For car of plate id = " + carId + "\nOffice No. " + officeId + "\nRate/Day = " + price + " $\nTotal number of reserved days = " + daysReserved +
        " days\nNumber of late days = " + daysLate +
        " days\nLatency penalty/Day = " + penalty + "$\nTotal amount to be paid = " + payment + "$\nPayment method: " + method.getDisplayName();
    }*/

    @Override
    public String toString() {
        return "Billing{" +
                "id=" + billingId +
                ", reservation id=" + reservation.getReservationId() +
                ", method=" + method.getDisplayName() +
                ", status=" + status.getDisplayName() +
                "}";
    }
}
