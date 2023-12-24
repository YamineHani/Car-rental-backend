package com.carrental.carrental.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Car implements Serializable {
    @Id
    private long plateId;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private Integer year;
    @Column(nullable = false)
    private String status;
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
    private List<Reservation> reservations;

    public Car(){}

    public Car(long plateId, String brand, String type, Integer year, String status, Float rate, String transmissionType, String fuelType, String bodyStyle, String color, Integer capacity, String imageUrl, List<Reservation> reservations) {
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
        this.reservations = reservations;
    }

    public long getPlateId() {
        return plateId;
    }

    public void setPlateId(long plateId) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public List<Reservation> getReservation() {
        return reservations;
    }

    public void setReservation(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString(){
        return "Car{"+
                "plate id=" + plateId +
                ", brand=" + brand +
                ", status=" + status +
                ", imageUrl=" + imageUrl+
                "}";
    }

}
