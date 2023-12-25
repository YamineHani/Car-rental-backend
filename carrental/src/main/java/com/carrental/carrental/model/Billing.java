package com.carrental.carrental.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Billing implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer billingId;
    @OneToOne
    @JoinColumn(name = "reservationId", nullable = false)
    private Reservation reservation;
    @Column(nullable = false)
    private Integer method;     //0 for cash, 1 for credit
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String description;     //paid, pending, etc.

    public Billing() {
    }

    public Billing(Integer billingId, Reservation reservation, Integer method, String status, String description) {
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

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription() {
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
        " days\nLatency penalty/Day = " + penalty + "$\nTotal amount to be paid = " + payment + "$";
    }

    @Override
    public String toString() {
        return "Billing{" +
                "id=" + billingId +
                ", reservation id=" + reservation.getReservationId() +
                ", method=" + method +
                ", status=" + status +
                "}";
    }
}
