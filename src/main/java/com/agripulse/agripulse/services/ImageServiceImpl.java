package com.agripulse.agripulse.services;

import com.agripulse.agripulse.exceptions.ImageNotFoundException;
import com.agripulse.agripulse.exceptions.ImageNotSavedException;
import com.agripulse.agripulse.exceptions.PostNotCreatedException;
import com.agripulse.agripulse.models.Image;
import com.agripulse.agripulse.repositories.ImageRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService{

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
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
    public List<Image> findAll() {
        return List.of();
    }

    @Override
    public List<Image> findByPostId(UUID postId) {
        return List.of();
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Image update(UUID id, Image image) {
        return null;
    }
}
