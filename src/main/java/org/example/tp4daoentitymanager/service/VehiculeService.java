package org.example.tp4daoentitymanager.service;


import jakarta.transaction.Transactional;
import org.example.tp4daoentitymanager.dao.VehicleDAO;
import org.example.tp4daoentitymanager.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VehiculeService  {

    private VehicleDAO vehicleDAO;


    public VehiculeService(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    public List<Vehicle> findAllVehicules(){
        return vehicleDAO.findAllVehicules();
    }
    public Vehicle findVehiculeById(Long id){
        return vehicleDAO.findVehiculeById(id);
    }
    public void saveVehicule(Vehicle vehicule){
        vehicleDAO.saveVehicule(vehicule);
    }
    public void deleteVehicule(Long id){
        vehicleDAO.deleteVehicule(id);
    }
    public List<Vehicle> findAvailableVehicules(){
        return vehicleDAO.findAvailableVehicules();
    }



}
