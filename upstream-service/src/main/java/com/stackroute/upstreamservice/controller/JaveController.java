package com.stackroute.upstreamservice.controller;

import com.stackroute.upstreamservice.service.JaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**controller for video conversion */
@RestController
@CrossOrigin("*")
public class JaveController {

    private JaveService javeService;
    @Autowired
    public JaveController(JaveService javeService) {
        this.javeService = javeService;
    }

    private static final Logger logger = LoggerFactory.getLogger(JaveController.class);

    @PostMapping("/video-bot")
    public ResponseEntity<String> getVideo(@RequestPart(name = "blob", required = false) MultipartFile file) throws Exception {
       logger.info("File:{}",file);
       logger.info("Before convert");
       return new ResponseEntity<>(javeService.videoEncoder(convertMultiPartToFile(file)), HttpStatus.OK);
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        try(FileOutputStream fos = new FileOutputStream(convFile))
        {
            fos.write(file.getBytes());
        }
        catch (Exception e)
        {
            logger.info(e.getMessage());
        }
        return convFile;
    }
}
