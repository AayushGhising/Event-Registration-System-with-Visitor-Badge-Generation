package com.assignment.EventRegistrationSystem.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.EventRegistrationSystem.entity.EventRegistration;
import com.assignment.EventRegistrationSystem.repository.EventRegistrationRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class EventRegistrationService {

    @Value("${file.upload-dir}")
    private final String UPLOAD_DIR = "uploads/";

    @Autowired
    private EventRegistrationRepository repository;

    public void registerUser(EventRegistration eventRegistration, MultipartFile photo) throws IOException {
        // if (repository.existsByEmail(email) || repository.existsByPhone(phone)) {
        //     throw new RuntimeException("Email or Phone already exists in the system");
        // }

        // // Save Photo
        // String photo_path = UPLOAD_DIR + photo.getOriginalFilename();
        // File photo_File = new File(photo_path);
        // photo.transferTo(photo_File);
        

        // // Save registration details
        // EventRegistration registration = new EventRegistration();
        // registration.setFull_name(full_name);
        // registration.setEmail(email);
        // registration.setPhone(phone);
        // registration.setPhoto_path(photo_path);
        // return repository.save(registration)
        
        // Save the photo to the uploads directory
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String photoPath = UPLOAD_DIR + photo.getOriginalFilename(); 
        Files.write(Paths.get(photoPath), photo.getBytes());
        eventRegistration.setPhoto_path(photoPath);
        repository.save(eventRegistration);  
        
    }

    public void generateBadge(EventRegistration eventRegistration, OutputStream outputStream) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Add visitor photo
        Image photo = Image.getInstance(eventRegistration.getPhoto_path());
        photo.scaleToFit(100, 100);
        document.add(photo);

        // Add visitor name
        document.add(new Paragraph(eventRegistration.getFull_name()));

        // Add visitor label
        document.add(new Paragraph("Event Registration"));
        
        document.close();
    }

}
