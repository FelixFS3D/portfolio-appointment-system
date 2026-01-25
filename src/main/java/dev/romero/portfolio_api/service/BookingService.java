package dev.romero.portfolio_api.service;


import dev.romero.portfolio_api.DTO.AppointmentRequest;
import dev.romero.portfolio_api.model.Appointment;
import dev.romero.portfolio_api.model.AvailabilitySlot;
import dev.romero.portfolio_api.repository.AppointmentRepository;
import dev.romero.portfolio_api.repository.SlotRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private SlotRepository slotRepo;
    @Autowired
    private AppointmentRepository apptRepo;
    @Autowired
    private EmailService emailService;

    private final String MY_ZOOM_LINK = "https://us05web.zoom.us/j/9048312950?pwd=cN3aYlTFaa41gF6QN3jd3GuYJo7e9P.1";

    @Transactional
    public void createAppointment(Long slotId, AppointmentRequest request) {
        AvailabilitySlot slot = slotRepo.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        if (slot.getIsBooked()) {
            throw new RuntimeException("Este horario ya esta reservado");
        }

        slot.setIsBooked(true);
        slotRepo.save(slot);

        Appointment appt = new Appointment();
        appt.setInterviewerName(request.getName());
        appt.setInterviewerEmail(request.getName());
        appt.setSlot(slot);
        appt.setZoomLink(MY_ZOOM_LINK);
        apptRepo.save(appt);

        emailService.sendConfirmation(
                request.getEmail(),
                request.getName(),
                slot.getStartTime().toString(),
                MY_ZOOM_LINK
        );
    }

    public void generateDailySlots(LocalDate date) {
        createSlotsInRange(date, 7, 13);
        createSlotsInRange(date, 15, 18);
    }

    private void createSlotsInRange(LocalDate date, int startHour, int endHour) {
        LocalDateTime current = date.atTime(startHour, 0);
        LocalDateTime limit = date.atTime(endHour, 0);

        while (current.isBefore(limit)) {
            AvailabilitySlot slot = new AvailabilitySlot();
            slot.setStartTime(current);
            slot.setEndTime(current.plusMinutes(60));
            slot.setIsBooked(false);
            slotRepo.save(slot);

            current = current.plusMinutes(60);
        }
    }

    public List<AvailabilitySlot> getAllSlots() {
        return slotRepo.findAll();
    }
}

