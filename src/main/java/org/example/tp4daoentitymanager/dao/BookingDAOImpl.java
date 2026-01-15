package org.example.tp4daoentitymanager.dao;



import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.tp4daoentitymanager.entity.Booking;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookingDAOImpl implements BookingDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    // REQUIRED METHOD
    public List<Booking> findBookingsByVehicle(Long vehicleId) {
        return em.createQuery(
                        "SELECT b FROM Booking b WHERE b.vehicle.id = :id",
                        Booking.class
                )
                .setParameter("id", vehicleId)
                .getResultList();
    }
}