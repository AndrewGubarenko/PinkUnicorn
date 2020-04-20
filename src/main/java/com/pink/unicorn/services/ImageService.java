package com.pink.unicorn.services;

import com.pink.unicorn.domain.Image;
import com.pink.unicorn.domain.PlainObjects.PlainImage;
import com.pink.unicorn.domain.Product;
import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService (ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Transactional
    public List<Image> createImage(Collection<Image> plainImages) {
        return plainImages.stream().map(image -> {
            imageRepository.save(image);
            return image;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void deleteImage(Long imageId, Product product) throws EmptyDataException{
        if(!imageRepository.existsById(imageId)) {
            throw new EmptyDataException("No such image exists!");
        }
        Image imageForDelete = imageRepository.findById(imageId).get();
        imageForDelete.removeProduct(product);
        imageRepository.delete(imageForDelete);
    }
}
