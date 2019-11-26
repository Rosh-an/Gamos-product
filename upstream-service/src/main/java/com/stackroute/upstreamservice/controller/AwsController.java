package com.stackroute.upstreamservice.controller;

import com.stackroute.upstreamservice.service.AmazonClient;
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
/**Represents a controller for AWS */
@RestController
@CrossOrigin("*")
@RequestMapping("storage")
public class AwsController {
    private AmazonClient amazonClient;
    private static final Logger logger = LoggerFactory.getLogger(AwsController.class);

    @Autowired
    AwsController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }
    /**for Posting file data or uploading file */
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile multipartFile) throws IOException {
        File file = convertMultiPartToFile(multipartFile);
        return this.amazonClient.uploadFile(file);
    }
/**for deletion of file*/
    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }
/**for posting video */
    @PostMapping("/video")
    public ResponseEntity<File> getVideo(@RequestPart(name = "blob", required = false) MultipartFile file) throws IOException {
        logger.info("Before upload:{}",file);
        amazonClient.uploadFile(convertMultiPartToFile(file));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());

        try (FileOutputStream fos = new FileOutputStream(convFile)){
            fos.write(file.getBytes());
        }
        catch (Exception e)
        {
            logger.info(e.getMessage());
        }
        return convFile;
    }
}
