package com.carrental.carrental.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Reference;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
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

    @ManyToOne()
    @JoinColumn(name = "plateId", nullable = false)
    private Car car;
    private String license;

    @OneToOne(mappedBy = "reservation")
    private Billing billing;

    //TRY TO REMOVE ID
    public Reservation(Integer reservationId, Date startDate, Integer days,
                       Date endDate, Boolean driver, Car car, String license) {
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.days = days;
        this.endDate = endDate;
        this.driver = driver;
        this.car = car;
        this.license = license;

    }

    @Override
    public String toString() {
        return "Reservation{"+
                "id="+reservationId+
                ", startDate="+startDate+
                ", endDate="+endDate+
                ", driver="+driver+
                ", license="+license+
                ", car_plate_id="+this.car.getPlateId()+
                "}";
    }
}
