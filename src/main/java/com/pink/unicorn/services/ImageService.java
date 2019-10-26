package com.pink.unicorn.services;

import com.pink.unicorn.domain.Image;
import com.pink.unicorn.domain.Product;
import com.pink.unicorn.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    public Image createImage(Image image, Product product) {
        image.setProduct(product);
        return imageRepository.save(image);
    }
}
