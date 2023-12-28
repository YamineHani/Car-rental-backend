package com.carrental.carrental.model;

import com.carrental.carrental.model.enums.Branch;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.ArrayList;
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
    private Branch branch;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "office")
    private List<Car> cars = new ArrayList<Car>();

    public Office() {
    }

    public Office(Integer officeId, String country, String city, Branch branch, String password) {
        this.officeId = officeId;
        this.country = country;
        this.city = city;
        this.branch = branch;
        this.password = password;
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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
