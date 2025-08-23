package com.agripulse.agripulse.controllers;

import com.agripulse.agripulse.dto.ImageResponseDto;
import com.agripulse.agripulse.exceptions.FileNotUploadedException;
import com.agripulse.agripulse.exceptions.ImageNotSavedException;
import com.agripulse.agripulse.exceptions.ImagesLimitException;
import com.agripulse.agripulse.exceptions.InvalidFileFormatException;
import com.agripulse.agripulse.models.Image;
import com.agripulse.agripulse.services.ImageService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final S3StorageServiceImpl s3StorageServiceImpl;
    private final ImageService imageService;



    public ImageController(S3StorageServiceImpl s3StorageService, ImageService imageService){
        this.s3StorageServiceImpl = s3StorageService;
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ImageResponseDto> uploadImages(@RequestParam("images") List<MultipartFile> files, @RequestParam("postId") UUID postId) throws InvalidFileFormatException, FileNotUploadedException, IOException, ImageNotSavedException, ImagesLimitException {

        if(files.size() >5){
            throw new ImagesLimitException("Only 5 images are allowed per post!");
        }

        //iterate
        short i=0;
        List<String> imageUrls = new ArrayList<>();

        for( MultipartFile file : files){

            if(file.isEmpty()) continue;

            String contentType = file.getContentType();

            if (contentType == null ||
                    !(contentType.equals("image/jpeg") || contentType.equals("image/png"))) {
                throw new InvalidFileFormatException("Only PNG and JPEG images are allowed");
            }


            String url = s3StorageServiceImpl.uploadFile(file);

            Image image = new Image();
            image.setImageUrl(url);
            image.setImageIndex(i);

            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());

            image.setImageHeight(bufferedImage.getHeight());
            image.setImageWidth(bufferedImage.getWidth());
            image.setImageSize(file.getSize());
            image.setPostId(postId);

            imageService.save(image);
            imageUrls.add(url);

            i++;
        }

        ImageResponseDto responseDto = new ImageResponseDto();
        responseDto.setImageUrls(imageUrls);
        responseDto.setPostId(postId);

        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }


}
