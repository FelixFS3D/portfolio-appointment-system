package dev.romero.portfolio_api.controller;

import dev.romero.portfolio_api.DTO.AppointmentRequest;
import dev.romero.portfolio_api.DTO.SlotDTO;
import dev.romero.portfolio_api.model.AvailabilitySlot;
import dev.romero.portfolio_api.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/slots")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @GetMapping
    public List<SlotDTO> getSlots(
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to
    ) {
        // Si no mandan fechas, usamos "hoy" y "dentro de 7 días" por defecto
        if (from == null) from = LocalDate.now();
        if (to == null) to = LocalDate.now().plusDays(7);

        return bookingService.getSlotsInDateRange(from, to).stream().map(slot -> {
            // ... tu mapeo a DTO igual que antes ...
            SlotDTO dto = new SlotDTO();
            // ...
            return dto;
        }).collect(Collectors.toList());
    }

    @PostMapping("/{id}/book")
    public ResponseEntity<?> bookSlot(@PathVariable Long id, @Valid @RequestBody AppointmentRequest request) {
        try {
            bookingService.createAppointment(id, request);
            return ResponseEntity.ok(Map.of("message", "Reserva exitosa.Se ha enviado un correo de conrfirmacion."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}