package dev.romero.portfolio_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmation(String to, String name, String date, String zoomLink){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("felix.fs3d@gmail.com");
        message.setTo(to);
        message.setSubject("Entrevista confirmada");
        message.setText("Hola " + name + ",\n\n"+
                        "Tu entrevista ha sido programda con exito.\n"+
                        "Fecha y Hora: " + date + "\n\n" +
                        "Enlace de Zoom: " + zoomLink + "\n\n" +
                        "¡Nos vemos pronto!");

        mailSender.send(message);
    }
}
