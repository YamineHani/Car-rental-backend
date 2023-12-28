package com.carrental.carrental.model;

import com.carrental.carrental.model.enums.CarStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Car(){}

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

    public Long getPlateId() {
        return plateId;
    }

    public void setPlateId(Long plateId) {
        this.plateId = plateId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getOfficeId() {
        return this.office.getOfficeId();
    }

    /*public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }*/

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
        //this.office = this.office.getOfficeId();
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
