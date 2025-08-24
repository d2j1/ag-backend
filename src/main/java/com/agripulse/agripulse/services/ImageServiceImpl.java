package com.agripulse.agripulse.services;

import com.agripulse.agripulse.dto.ImageResponseDto;
import com.agripulse.agripulse.dto.PaginatedResponse;
import com.agripulse.agripulse.exceptions.FileNotFoundException;
import com.agripulse.agripulse.exceptions.FileNotUploadedException;
import com.agripulse.agripulse.exceptions.ImageNotFoundException;
import com.agripulse.agripulse.exceptions.ImageNotSavedException;
import com.agripulse.agripulse.models.Image;
import com.agripulse.agripulse.repositories.ImageRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImageServiceImpl implements ImageService{

    private final ImageRepository imageRepository;
    private final S3StorageService s3StorageService;

    public ImageServiceImpl(ImageRepository imageRepository, S3StorageService s3StorageService){
        this.imageRepository = imageRepository;
        this.s3StorageService = s3StorageService;
    }

    @Override
    public Image save(Image image) throws ImageNotSavedException {
        try{
            return imageRepository.save(image);
        }catch (DataIntegrityViolationException e){
            // invalid data in post like nulls
            throw new ImageNotSavedException("Could not save the image. Please try again!");
        }catch (DataAccessException e){
            // not able to access the database
            throw new ImageNotSavedException("Could not save the image due to system unavailability. Please try after some time!");
        }catch (Exception e){
            throw new ImageNotSavedException("Could not save the image. Please try again after sometime!");
        }
    }

    @Override
    public Image findById(UUID id) throws ImageNotFoundException {

        Optional<Image> imageOptional = imageRepository.findById(id);

        if(imageOptional.isEmpty()){
            throw new ImageNotFoundException("Image not found for "+id);
        }
        return imageOptional.get();
    }


    @Override
    public PaginatedResponse<ImageResponseDto> getAllImages(int pageNumber, int pageSize) {

        Page<Image> imagePage = imageRepository.findAll(PageRequest.of(pageNumber, pageSize));

        HashMap<UUID, List<String>> postIdAndImages = new HashMap<>();

        for(Image image : imagePage.getContent()){
            List<String> urls = postIdAndImages.getOrDefault(image.getPostId(), new ArrayList<String>());

            urls.add(image.getImageUrl());

            postIdAndImages.put(image.getPostId(), urls);
        }

        List<ImageResponseDto> imageResponseDtos = new ArrayList<>();

        for(UUID postId : postIdAndImages.keySet()){
            ImageResponseDto dto = new ImageResponseDto();
            dto.setPostId(postId);
            dto.setImageUrls(postIdAndImages.get(postId));

            imageResponseDtos.add(dto);
        }


        return new PaginatedResponse<>(imageResponseDtos,
                imagePage.getNumber(),
                imagePage.getTotalPages(),
                imagePage.getTotalElements()
                );
    }

    @Override
    public List<Image> findByPostId(UUID postId) throws ImageNotFoundException {
        List<Image> images = imageRepository.findByPostId(postId);

        if(images.isEmpty()){
            throw new ImageNotFoundException("Images not found for the provided post id: "+postId);
        }
        return images;
    }

    @Override
    public void deleteByImageId(UUID id) throws ImageNotFoundException, FileNotFoundException {

        Optional<Image> imageOptional = imageRepository.findById(id);
        if(imageOptional.isEmpty()){
            throw new ImageNotFoundException("Image not found for "+id);
        }

        Image image = imageOptional.get();
        s3StorageService.deleteFileByUrl(image.getImageUrl());
        imageRepository.delete(image);
    }

    @Override
    public void deleteByPostId(UUID postId) throws ImageNotFoundException, FileNotFoundException {

        List<Image> images = findByPostId(postId);

        for(Image image: images){
            s3StorageService.deleteFileByUrl(image.getImageUrl());
        }
        imageRepository.deleteAll(images);
    }
}
