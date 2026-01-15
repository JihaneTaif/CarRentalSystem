package org.example.tp4daoentitymanager.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "vehicules")
public class Vehicle {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String brand;
        private String model;
        private Float dailyRentalRate;
        private Boolean availability;
        private LocalDate lastServiceDate;

    // ONE vehicle → MANY bookings
    //@OneToMany(mappedBy="vehicle")
    //means Vehicle is NOT owner
    //When a vehicle is deleted → its bookings are deleted(EXAME REQ)
    @OneToMany(
            mappedBy = "vehicle",
            cascade = CascadeType.REMOVE
    )
    private List<Booking> bookings;


    public Vehicle(Long id, String brand, String model, Float dailyRentalRate, Boolean availability, LocalDate lastServiceDate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.dailyRentalRate = dailyRentalRate;
        this.availability = availability;
        this.lastServiceDate = lastServiceDate;
    }

    public Vehicle() {

    }


    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Float getDailyRentalRate() {
        return dailyRentalRate;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public LocalDate getLastServiceDate() {
        return lastServiceDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDailyRentalRate(Float dailyRentalRate) {
        this.dailyRentalRate = dailyRentalRate;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public void setLastServiceDate(LocalDate lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }
}
