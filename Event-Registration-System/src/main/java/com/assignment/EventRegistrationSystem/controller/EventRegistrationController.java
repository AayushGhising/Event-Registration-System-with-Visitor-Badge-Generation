package com.assignment.EventRegistrationSystem.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.EventRegistrationSystem.entity.EventRegistration;
import com.assignment.EventRegistrationSystem.repository.EventRegistrationRepository;
import com.assignment.EventRegistrationSystem.service.EventRegistrationService;
import com.itextpdf.text.DocumentException;

@RestController // This means that this class is a Controller and will be handling HTTP Requests
@RequestMapping("/api/event-registration") // This means URL's start with /api/event-registration (after Application path) which will be handled by this class
public class EventRegistrationController {

    @Autowired // This helps to inject the dependency of EventRegistrationService class into this class
    private EventRegistrationService service;

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    @GetMapping("/")
    public String showRegistrationForm(Model model) {
        model.addAttribute("Event Registration", new EventRegistration());
        return "registration";
    }

    @PostMapping("/registerUser")
    // public ResponseEntity<String> registerUser(
    //     @RequestParam String full_name,
    //     @RequestParam String email,
    //     @RequestParam String phone,
    //     @RequestParam MultipartFile photo

    // ){
    //     try {
    //         EventRegistration registration = service.registerUser(full_name, email, phone, photo);
    //         return ResponseEntity.ok("Registration successful! Your ID: " + registration.getId());
    //     }catch (Exception e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }
    public String registerUser(EventRegistration eventRegistration, @RequestParam("photo") MultipartFile photo, Model model){
        try {
            service.registerUser(eventRegistration, photo);
            model.addAttribute("Event Registration", eventRegistration);
            return "badge";
        }catch (Exception e) {
            model.addAttribute("error", "Registration failed. Please try again");
            return "registration";
        }
    }

    @GetMapping("/downloadBadge")
    public void downloadBadge(@PathVariable Integer id, HttpServletResponse response) throws IOException, DocumentException {
        EventRegistration eventRegistration = eventRegistrationRepository.findById(id).orElseThrow(() -> new RuntimeException("Event Registration not found"));

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=badge.pdf");

        service.generateBadge(eventRegistration, response.getOutputStream());
        
    }

}
