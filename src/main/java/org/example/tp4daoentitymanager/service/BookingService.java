package org.example.tp4daoentitymanager.service;

import org.example.tp4daoentitymanager.dao.BookingDAO;
import org.example.tp4daoentitymanager.dao.VehicleDAO;
import org.example.tp4daoentitymanager.entity.Booking;
import org.example.tp4daoentitymanager.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingDAO bookingDAO;
    private final VehicleDAO vehicleDAO;

    public BookingService(BookingDAO bookingDAO, VehicleDAO vehicleDAO) {
        this.bookingDAO = bookingDAO;
        this.vehicleDAO = vehicleDAO;
    }

    // Availability Guard
    public void createBooking(Long vehicleId, Booking booking) {
        Vehicle vehicle = vehicleDAO.findVehiculeById(vehicleId);

        if (!vehicle.getAvailability()) {
            throw new RuntimeException("Vehicle not available");
        }

        booking.setVehicle(vehicle);
        // persist booking (add persist method if needed)
    }

    // Total Price Logic
    public double calculateTotalPrice(Booking booking) {
        return booking.getNumberOfDays() * booking.getVehicle().getDailyRentalRate();
    }

    public List<Booking> getBookingsByVehicle(Long id) {
        return bookingDAO.findBookingsByVehicle(id);
    }
}