package com.pink.unicorn.utils;

import com.pink.unicorn.domain.Image;
import com.pink.unicorn.domain.PlainObjects.PlainImage;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ImageConverter {

    public PlainImage ImageToPlain(Image image) {
        PlainImage result = new PlainImage();

        result.setId(image.getId());
        result.setPhoto(Base64.getEncoder().encodeToString(image.getPhoto()));
        result.setProductId(image.getProduct().getId());

        return result;
    }
}
