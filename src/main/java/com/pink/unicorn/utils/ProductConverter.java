package com.pink.unicorn.utils;

import com.pink.unicorn.domain.PlainObjects.PlainProduct;
import com.pink.unicorn.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 *
 */
@Component
public class ProductConverter {

    private final CategoryConverter categoryConverter;
    private final ImageConverter imageConverter;

    @Autowired
    public ProductConverter (CategoryConverter categoryConverter,
                             ImageConverter imageConverter) {
        this.categoryConverter = categoryConverter;
        this.imageConverter = imageConverter;
    }

    public PlainProduct ProductToPlain(Product product) {
        PlainProduct result = new PlainProduct();

        result.setId(product.getId());
        result.setName(product.getName());
        result.setBrand(product.getBrand());
        result.setDescription(product.getDescription());
        result.setInSale(product.isInSale());
        result.setPrice(product.getPrice());
        result.setSalePrice(product.getSalePrice());
        result.setCount(product.getCount());
        result.setImages(product.getImages().stream().map(image -> imageConverter.ImageToPlain(image)).collect(Collectors.toList()));
        result.setCategories(product.getCategories().stream().map(category -> categoryConverter.CategoryToPlain(category)).collect(Collectors.toSet()));
        result.setSubCategories(product.getSubCategories());

        return result;
    }
}
