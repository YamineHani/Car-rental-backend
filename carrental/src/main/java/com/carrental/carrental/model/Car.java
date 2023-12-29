package com.carrental.carrental.model;

import com.carrental.carrental.model.enums.CarStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Car implements Serializable {
    @Id
    private Long plateId;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private Integer year;
    @Column(nullable = false)
    private CarStatus status;
    @Column(nullable = false)
    private Float rate;
    @Column(nullable = false)
    private String transmissionType;
    @Column(nullable = false)
    private String fuelType;
    @Column(nullable = false)
    private String bodyStyle;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private Integer capacity;
    private String imageUrl;

    @OneToMany(mappedBy = "car")
    private List<Reservation> reservations = new ArrayList<Reservation>();
    @ManyToOne()
    @JoinColumn(name = "officeId", nullable = false)
    private Office office;

    public Car(long plateId, String brand, String type, Integer year, CarStatus status, Float rate, String transmissionType, String fuelType, String bodyStyle, String color, Integer capacity, String imageUrl, Office office) {
        this.plateId = plateId;
        this.brand = brand;
        this.type = type;
        this.year = year;
        this.status = status;
        this.rate = rate;
        this.transmissionType = transmissionType;
        this.fuelType = fuelType;
        this.bodyStyle = bodyStyle;
        this.color = color;
        this.capacity = capacity;
        this.imageUrl = imageUrl;
        this.office = office;
    }

    @Override
    public String toString(){
        return "Car{"+
                "plate id=" + plateId +
                ", brand=" + brand +
                ", status=" + status.getDisplayName() +
                ", imageUrl=" + imageUrl+
                ", office id= " + office.getOfficeId() +
                "}";
    }

}
