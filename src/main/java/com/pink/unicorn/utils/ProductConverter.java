package com.pink.unicorn.utils;

import com.pink.unicorn.domain.PlainObjects.PlainProduct;
import com.pink.unicorn.domain.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 *
 */
@Component
public class ProductConverter {

    public static PlainProduct ProductToPlain(Product product) {
        PlainProduct result = new PlainProduct();

        result.setId(product.getId());
        result.setName(product.getName());
        result.setCategory(product.getCategory());
        result.setBrand(product.getBrand());
        result.setDescription(product.getDescription());
        result.setInSale(product.isInSale());
        result.setPrice(product.getPrice());
        result.setSalePrice(product.getSalePrice());
        result.setCount(product.getCount());
        result.setImages(product.getImages().stream().map(image -> ImageConverter.ImageToPlain(image)).collect(Collectors.toSet()));
        result.setTags(product.getTags().stream().map(tag -> TagConverter.TagToPlain(tag)).collect(Collectors.toSet()));

        return result;
    }

    public static Product PlainToProduct(PlainProduct plainProduct) {
        Product result = new Product();

        result.setId(plainProduct.getId());
        result.setName(plainProduct.getName());
        result.setCategory(plainProduct.getCategory());
        result.setBrand(plainProduct.getBrand());
        result.setDescription(plainProduct.getDescription());
        result.setInSale(plainProduct.isInSale());
        result.setPrice(plainProduct.getPrice());
        result.setSalePrice(plainProduct.getSalePrice());
        result.setCount(plainProduct.getCount());
        result.setImages(plainProduct.getImages().stream().map(plainImage -> ImageConverter.PlainToImage(plainImage)).collect(Collectors.toList()));
        result.setTags(plainProduct.getTags().stream().map(plainTag -> TagConverter.PlainToTag(plainTag)).collect(Collectors.toSet()));

        return result;
    }
}
