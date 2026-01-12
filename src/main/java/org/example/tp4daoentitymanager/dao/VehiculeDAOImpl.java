package org.example.tp4daoentitymanager.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.tp4daoentitymanager.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class VehiculeDAOImpl implements VehicleDAO{


    @PersistenceContext
     private EntityManager entityManager;



    @Override
    public List<Vehicle> findAllVehicules() {
        return entityManager.createQuery("FROM Vehicle", Vehicle.class)
                .getResultList();
    }


    @Override
    public Vehicle findVehiculeById(Long id) {
        return entityManager.find(Vehicle.class, id);
    }


    @Override
    public void saveVehicule(Vehicle vehicle) {
        if (vehicle.getId() == null) {
            entityManager.persist(vehicle);
        } else {
            entityManager.merge(vehicle);
        }
    }
    @Override
    public void deleteVehicule(Long id) {
        Vehicle vehicle = findVehiculeById(id);
        if (vehicle != null) {
            entityManager.remove(vehicle);
        }
    }


    @Override
    public List<Vehicle> findAvailableVehicules() {
        return entityManager
                .createQuery("FROM Vehicle v WHERE v.availability = true", Vehicle.class)
                .getResultList();
    }


}
