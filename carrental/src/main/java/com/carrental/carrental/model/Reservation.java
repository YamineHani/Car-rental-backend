package com.carrental.carrental.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reservationId;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Integer days;

    private Date endDate;

    @Column(nullable = false)
    private Boolean driver;

    @ManyToOne
    @JoinColumn(name = "plateId", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "officeId", nullable = false)
    private Office office;


    @OneToOne(mappedBy = "reservation")
    private Billing billing;

    private String license;

    public Reservation() {
    }

    public Reservation(Integer reservationId, Date startDate, Integer days, Date endDate, Boolean driver, Car car, Office office, Billing billing, String license) {
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.days = days;
        this.endDate = endDate;
        this.driver = driver;
        this.car = car;
        this.office = office;
        this.billing = billing;
        this.license = license;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getDriver() {
        return driver;
    }

    public void setDriver(Boolean driver) {
        this.driver = driver;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    @Override
    public String toString() {
        return "Reservation{"+
                "id="+reservationId+
                ", startDate="+startDate+
                ", endDate="+endDate+
                ", driver="+driver+
                ", license="+license+
                ", car_plate_id="+car.getPlateId()+
                "office_id="+office.getOfficeId()+
                "}";
    }
}
