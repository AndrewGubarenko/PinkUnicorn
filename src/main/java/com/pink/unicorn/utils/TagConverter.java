package com.pink.unicorn.utils;

import com.pink.unicorn.domain.PlainObjects.PlainTag;
import com.pink.unicorn.domain.Tag;
import com.pink.unicorn.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TagConverter {

    private final ProductRepository productRepository;

    @Autowired
    public TagConverter (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public PlainTag TagToPlain(Tag tag) {
        PlainTag result = new PlainTag();

        result.setId(tag.getId());
        result.setName(tag.getName());
        result.setPlainProductIdList(tag.getProductList().stream().map(product -> product.getId()).collect(Collectors.toSet()));

        return result;
    }

/*    public Tag PlainToTag(PlainTag plainTag) {
        Tag result = new Tag();

        result.setId(plainTag.getId());
        result.setName(plainTag.getName());
        result.setProductList(plainTag.getPlainProductIdList().stream().map(productId -> productRepository.findById(productId).get()).collect(Collectors.toList()));

        return result;
    }*/
}
