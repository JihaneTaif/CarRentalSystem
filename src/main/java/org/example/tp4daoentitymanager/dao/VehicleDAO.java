package org.example.tp4daoentitymanager.dao;

import org.example.tp4daoentitymanager.entity.Vehicle;

import java.util.List;

public interface VehicleDAO {

    /*
    * o	Find all vehicles.
o	Find a specific vehicle by its ID.
o	Save a vehicle (Insert or Update).
o	Delete a vehicle.
o	Filter and return only vehicles where available = true.
*/
    List<Vehicle> findAllVehicules();
    Vehicle findVehiculeById(Long id);
    void saveVehicule(Vehicle vehicule);
    void deleteVehicule(Long id);
    List<Vehicle> findAvailableVehicules();




    Vehicle findMostExpensiveVehicle();



}
