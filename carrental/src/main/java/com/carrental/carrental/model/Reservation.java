package com.carrental.carrental.model;

import com.carrental.carrental.model.enums.BillingStatus;
import com.carrental.carrental.model.enums.Method;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Reference;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Reservation implements Serializable {
    @Id
    @SequenceGenerator( // used to generate unique identifiers
            name = "reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reservation_sequence"
    )
    private Integer reservationId;

    @Column(nullable = false)
    private Date startDate;

//    @Transient //derived attribute
//    private Integer lateDays;

    @Column(nullable = false)
    private Date endDate; //user has to enter endDate

    //might remove
    //private Date returnDate; //return date is the date in which the user actually return the car

    @ManyToOne()
    @JoinColumn(name = "plateId", nullable = false)
    private Car car;

    @ManyToOne()  //--------------------
    @JoinColumn(name = "id", nullable = false)
    private User user;
    //-------------------------------

    //instead of table billing
    @Column(nullable = false)
    private Method method;
    @Column(nullable = false)
    private BillingStatus status;

    @Transient
    private float bill;

    public Reservation( Date startDate, Date endDate, Car car, User user, Method method) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.car = car;
        this.user = user;
        this.method = method;
        this.status = BillingStatus.PENDING; //initially pending might remove this
    }



    @Override
    public String toString() {
        return "Reservation{"+
                "id="+reservationId+
                ", startDate="+startDate+
                ", endDate="+endDate+
                ", car_plate_id="+this.car.getPlateId()+
                ", method=" + method +
                ", status=" + status +
                "}";
    }

    public void calculateBill(Car car){
        LocalDateTime startDateTime = this.startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime endDateTime = this.endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        Duration duration = Duration.between(startDateTime, endDateTime);
        long days = duration.toDays();
        float bill = days*car.getRate();
        this.setBill(bill);
    }
}
