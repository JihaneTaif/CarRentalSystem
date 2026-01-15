package org.example.tp4daoentitymanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private LocalDate bookingDate;
    private Integer numberOfDays;

    // MANY bookings → ONE vehicle
    //❓ EXAM QUESTION ANSWER
    //How do you ensure the Foreign Key is named car_id? -> @JoinColumn(name = "car_id")
    @JsonIgnore   //Prevent JSON Infinite Loop ->Why? Vehicle → Booking → Vehicle → Booking → 💥 infinite loop
    @ManyToOne
    @JoinColumn(name = "car_id") // EXAM QUESTION
    private Vehicle vehicle;


    public Booking(Long id, String clientName, LocalDate bookingDate, Integer numberOfDays, Vehicle vehicle) {
        this.id = id;
        this.clientName = clientName;
        this.bookingDate = bookingDate;
        this.numberOfDays = numberOfDays;
        this.vehicle = vehicle;
    }

    public Booking() {

    }

    public Long getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}