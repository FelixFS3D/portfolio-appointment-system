package dev.romero.portfolio_api.repository;

import dev.romero.portfolio_api.model.AvailabilitySlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface SlotRepository extends JpaRepository<AvailabilitySlot, Long> {
    List<AvailabilitySlot> findByIsBookedFalse();
    List<AvailabilitySlot> findByStartTimeBetweenOrderByStartTimeAsc(ZonedDateTime start, ZonedDateTime end);
    boolean existsByStartTime(ZonedDateTime startTime);
}
