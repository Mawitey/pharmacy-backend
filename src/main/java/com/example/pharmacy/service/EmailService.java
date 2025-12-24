//package com.example.pharmacy.service;
//
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//
//    private final JavaMailSender mailSender;
//
//    public EmailService(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    public void sendConfirmationEmail(String to, String name, Long requestId) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject("Pharmacy Refill Confirmation");
//        message.setText("Hello " + name + ",\n\n"
//                + "Your prescription refill request has been submitted successfully.\n"
//                + "Your confirmation ID is: " + requestId + "\n\n"
//                + "Thank you for choosing HealthCare Pharmacy!");
//        mailSender.send(message);
//    }
//}
