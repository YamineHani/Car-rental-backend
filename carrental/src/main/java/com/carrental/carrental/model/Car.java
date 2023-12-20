package com.carrental.carrental.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long plateId;
    private String brand;
    private String status;
    private String imageUrl;

    public Car(){}

    public Car(long plateId, String brand, String status, String imageUrl) {
        this.plateId = plateId;
        this.brand = brand;
        this.status = status;
        this.imageUrl = imageUrl;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
