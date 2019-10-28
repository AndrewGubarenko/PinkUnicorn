package com.pink.unicorn.services;

import com.pink.unicorn.domain.Image;
import com.pink.unicorn.domain.PlainObjects.PlainImage;
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

    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    public List<Image> createImage(Collection<PlainImage> plainImages) {
        return plainImages.stream().map(plainImage -> {
            Image image = new Image();
            image.setPhoto(Base64.getDecoder().decode(plainImage.getPhoto()));
            imageRepository.save(image);
            return image;
        }).collect(Collectors.toList());
    }
}
