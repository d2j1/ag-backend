package com.agripulse.agripulse.controllers;

import com.agripulse.agripulse.exceptions.FileNotUploadedException;
import com.agripulse.agripulse.exceptions.InvalidFileFormatException;
import com.agripulse.agripulse.models.Image;
import com.agripulse.agripulse.services.S3StorageServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final S3StorageServiceImpl s3StorageServiceImpl;

    public ImageController(S3StorageServiceImpl s3StorageService){
        this.s3StorageServiceImpl = s3StorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImages(@RequestParam("images") List<MultipartFile> files, @RequestParam("postId") UUID postId) throws InvalidFileFormatException, FileNotUploadedException, IOException {

        if(files.size() >5){
            return new ResponseEntity<>("Only 5 images are allowed per post!", HttpStatus.BAD_REQUEST);
        }

        //iterate
        short i=0;

        for( MultipartFile file : files){

            if(file.isEmpty()) continue;

            String contentType = file.getContentType();

            if (contentType == null ||
                    !(contentType.equals("image/jpeg") || contentType.equals("image/png"))) {
                return new ResponseEntity<>("Only PNG and JPEG images are allowed", HttpStatus.BAD_REQUEST);
            }


            String url = s3StorageServiceImpl.uploadFile(file);

            Image image = new Image();
            image.setImageUrl(url);
            image.setImageIndex(i);

            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());

            image.setImageHeight(bufferedImage.getHeight());
            image.setImageWidth(bufferedImage.getWidth());
            image.setImageSize(file.getSize());
            image.setPost_id(postId);

            i++;
        }

    }


}
