package org.example.tp4daoentitymanager.dao;

import org.example.tp4daoentitymanager.entity.Booking;

import java.util.List;

public interface BookingDAO {





    List<Booking> findBookingsByVehicle(Long vehicleId);
}
