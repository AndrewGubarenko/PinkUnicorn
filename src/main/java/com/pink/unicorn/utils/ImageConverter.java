package com.pink.unicorn.utils;

import com.pink.unicorn.domain.Image;
import com.pink.unicorn.domain.PlainObjects.PlainImage;
import com.pink.unicorn.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ImageConverter {

    private final ProductRepository productRepository;

    @Autowired
    public ImageConverter (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public PlainImage ImageToPlain(Image image) {
        PlainImage result = new PlainImage();

        result.setId(image.getId());
        result.setPhoto(Base64.getEncoder().encodeToString(image.getPhoto()));
        result.setProductId(image.getProduct().getId());

        return result;
    }

/*    public Image PlainToImage(PlainImage plainImage) {
        Image result = new Image();

        result.setId(plainImage.getId());
        result.setPhoto(Base64.getDecoder().decode(plainImage.getPhoto()));
        result.addProduct(productRepository.findById(plainImage.getProductId()).get());

        return result;
    }*/
}
