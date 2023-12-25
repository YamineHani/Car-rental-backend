package com.carrental.carrental.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"city", "country", "branch"})})
public class Office implements Serializable {
    @Id
    private Integer officeId;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private Integer branch;     //0 for airport, 1 for downtown
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "office")
    private List<Reservation> reservations;

    public Office() {
    }

    public Office(Integer officeId, String country, String city, Integer branch, String password, List<Reservation> reservations) {
        this.officeId = officeId;
        this.country = country;
        this.city = city;
        this.branch = branch;
        this.password = password;
        this.reservations = reservations;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getBranch() {
        return branch;
    }

    public void setBranch(Integer branch) {
        this.branch = branch;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Reservation> getReservation() {
        return reservations;
    }

    public void setReservation(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Office{"+
                "id="+ officeId +
                ", country="+country+
                ", city="+city+
                ", branch="+branch+
                "}";
    }
}
