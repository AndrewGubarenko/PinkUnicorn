package com.pink.unicorn.services;

import com.pink.unicorn.domain.Category;
import com.pink.unicorn.domain.Product;
import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.repositories.CategoryRepository;
import com.pink.unicorn.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {


    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService (CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public List<Category> findOrCreate(Collection<Category> categories, Product product) {
        return categories.stream().map(category -> {

            Optional<Category> foundCategory = categoryRepository.findByName(category.getName());

            if (foundCategory.isPresent()) {
                Category cat = foundCategory.get();
                cat.getSubCategories().addAll(product.getSubCategories());
                cat.addProduct(product);
                return cat;
            } else {
                Category cat = new Category(category.getName());
                cat.setSubCategories(product.getSubCategories());
                categoryRepository.save(cat);
                cat.addProduct(product);
                return cat;
            }
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void removeCategory(String categoryName) throws EmptyDataException {
        Optional<Category> categoryForRemoveOpt = categoryRepository.findByName(categoryName);
        if(!categoryForRemoveOpt.isPresent()) {
            throw new EmptyDataException("Category doesn't exist!");
        }
        Category categoryForRemove = categoryForRemoveOpt.get();
        categoryForRemove.removeAllProducts();
        categoryRepository.delete(categoryForRemove);
    }
}
