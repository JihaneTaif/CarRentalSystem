package org.example.tp4daoentitymanager.controller;


import org.example.tp4daoentitymanager.entity.Booking;
import org.example.tp4daoentitymanager.entity.Vehicle;
import org.example.tp4daoentitymanager.service.BookingService;
import org.example.tp4daoentitymanager.service.VehiculeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehiculeController {

    private final  VehiculeService vehiculeService;
    private final  BookingService bookingService;

    public VehiculeController(VehiculeService vehiculeService,BookingService bookingService) {

        this.vehiculeService = vehiculeService;
        this.bookingService= bookingService;
    }


    /*
    * o	GET /api/vehicles: Returns the list of all vehicles.
o	GET /api/vehicles/available: Returns only the vehicles ready for rent.
o	POST /api/vehicles: Accepts a JSON object to add a new vehicle.
o	DELETE /api/vehicles/{id}: Removes a vehicle from the fleet.

List<Vehicule> findAllVehicules();
    Vehicule findVehiculeById(Long id);
    void saveVehicule(Vehicule vehicule);
    void deleteVehicule(Long id);
    List<Vehicule> findAvailableVehicules(Boolean availability);

*/

    @GetMapping
    public List<Vehicle> findAllVehicules(){
        return vehiculeService.findAllVehicules();
    }
    @GetMapping("/{id}")
    public Vehicle findVehiculeById(@PathVariable Long id){
        return vehiculeService.findVehiculeById(id);
    }
    @PostMapping
    public void saveVehicule(@RequestBody Vehicle vehicule){
        vehiculeService.saveVehicule(vehicule);
    }
    @DeleteMapping("/{id}")
    public void deleteVehicule(@PathVariable  Long id){
        vehiculeService.deleteVehicule(id);
    }
    @GetMapping("/available")
    public  List<Vehicle> findAvailableVehicules(){
        return vehiculeService.findAvailableVehicules();
    }




    // EXAM ENDPOINT
    @GetMapping("/{id}/bookings")
    public List<Booking> getBookings(@PathVariable Long id) {
        return bookingService.getBookingsByVehicle(id);
    }



}
