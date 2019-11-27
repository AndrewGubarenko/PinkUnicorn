package com.pink.unicorn.utils;

import com.pink.unicorn.domain.Category;
import com.pink.unicorn.domain.PlainObjects.PlainCategory;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryConverter {

    public PlainCategory CategoryToPlain(Category category) {
        PlainCategory result = new PlainCategory();

        result.setId(category.getId());
        result.setName(category.getName());
        result.setSubCategories(category.getSubCategories());
        result.setPlainProductIdList(category.getProductList().stream().map(product -> product.getId()).collect(Collectors.toSet()));

        return result;
    }
}
