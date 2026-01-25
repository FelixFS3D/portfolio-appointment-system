package dev.romero.portfolio_api.service;

import dev.romero.portfolio_api.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BookingService bookingService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>> Iniciando la generación de horarios...");

        // Generamos slots para hoy y para mañana para tener variedad
        bookingService.generateDailySlots(LocalDate.now());
        bookingService.generateDailySlots(LocalDate.now().plusDays(1));

        System.out.println(">>> ¡Éxito! Horarios (7-13h y 15-18h) creados en la base de datos.");
    }
}
