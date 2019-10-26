package com.pink.unicorn.utils;

import com.pink.unicorn.domain.Image;
import com.pink.unicorn.domain.PlainObjects.PlainImage;
import com.pink.unicorn.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageConverter {

    @Autowired
    private static ProductRepository productRepository;

    public static PlainImage ImageToPlain(Image image) {
        PlainImage result = new PlainImage();

        result.setId(image.getId());
        result.setPhoto(image.getPhoto());
        result.setProductId(image.getProduct().getId());

        return result;
    }

    public static Image PlainToImage(PlainImage plainImage) {
        Image result = new Image();

        result.setId(plainImage.getId());
        result.setPhoto(plainImage.getPhoto());
        result.setProduct(productRepository.findById(plainImage.getProductId()).get());

        return result;
    }
}
