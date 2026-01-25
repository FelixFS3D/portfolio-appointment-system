package dev.romero.portfolio_api.repository;

import dev.romero.portfolio_api.DTO.AppointmentRequest;
import dev.romero.portfolio_api.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
