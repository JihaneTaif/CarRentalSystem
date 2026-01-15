package org.example.tp4daoentitymanager;

import org.example.tp4daoentitymanager.entity.Vehicle;
import org.example.tp4daoentitymanager.service.VehiculeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tp4DaoEntityManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tp4DaoEntityManagerApplication.class, args);
    }

    @Bean
    CommandLineRunner init(VehiculeService vehiculeService) {
        return args -> {
            Vehicle v1 = new Vehicle();
            v1.setBrand("Toyota");
            v1.setModel("Corolla");
            v1.setDailyRentalRate(50F);
            v1. setAvailability(true);

            vehiculeService.saveVehicule(v1);
        };
    }


}
