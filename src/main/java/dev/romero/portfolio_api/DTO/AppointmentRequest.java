package dev.romero.portfolio_api.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AppointmentRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @NotBlank(message = "El mail es obligatorio")
    @Email(message = "EL formato de mail es inválido")
    private String email;
}
