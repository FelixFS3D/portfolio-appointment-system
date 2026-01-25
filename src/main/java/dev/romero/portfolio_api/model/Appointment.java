package dev.romero.portfolio_api.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String interviewerName;
    private String interviewerEmail;
    private String zoomLink;

    @OneToOne
    @JoinColumn(name = "slot_id")
    private AvailabiltySlot slot;
}
