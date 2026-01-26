package dev.romero.portfolio_api.controller;

import dev.romero.portfolio_api.DTO.AppointmentRequest;
import dev.romero.portfolio_api.DTO.SlotDTO;
import dev.romero.portfolio_api.model.AvailabilitySlot;
import dev.romero.portfolio_api.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/slots")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Obtener la lista de todos los slots transformados a DTO
    @GetMapping
    public List<SlotDTO> getSlots() {
        return bookingService.getAllSlots().stream().map(slot -> {
            SlotDTO dto = new SlotDTO();
            dto.setId(slot.getId());
            dto.setStartTime(slot.getStartTime());
            dto.setEndTime(slot.getEndTime());
            dto.setIsBooked(slot.getIsBooked());
            return dto;
        }).collect(Collectors.toList());
    }

    // Reservar un slot específico
    @PostMapping("/{id}/book")
    public ResponseEntity<String> bookSlot(@PathVariable Long id, @Valid @RequestBody AppointmentRequest request) {
        try {
            bookingService.createAppointment(id, request);
            return ResponseEntity.ok("Reserva exitosa. Se ha enviado un correo de confirmación.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}