package com.carrental.carrental.model;

import com.carrental.carrental.model.enums.Branch;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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
    @JsonBackReference
    private List<Car> cars = new ArrayList<Car>();

    public Office(Integer officeId, String country, String city, Branch branch, String password) {
        this.officeId = officeId;
        this.country = country;
        this.city = city;
        this.branch = branch;
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
