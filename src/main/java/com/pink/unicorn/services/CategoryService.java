package com.pink.unicorn.services;

import com.pink.unicorn.domain.Category;
import com.pink.unicorn.domain.PlainObjects.PlainCategory;
import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.repositories.CategoryRepository;
import com.pink.unicorn.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    public List<Category> findOrCreate(Collection<PlainCategory> plainCategories, Set<String> subCategories) {
        return plainCategories.stream().map(plainCategory -> {

            Optional<Category> foundCategory = categoryRepository.findByName(plainCategory.getName());

            if (foundCategory.isPresent()) {
                Category cat = foundCategory.get();
                cat.getSubCategories().addAll(subCategories);
                categoryRepository.save(cat);
                return cat;
            } else {
                Category cat = new Category(plainCategory.getName());
                cat.getSubCategories().addAll(subCategories);
                categoryRepository.save(cat);
                return cat;
            }
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void removeCategory(String categoryName) throws EmptyDataException {
        Optional<Category> categoryForRemoveOpt = categoryRepository.findByName(categoryName);
        if(!categoryForRemoveOpt.isPresent()) {
            throw new EmptyDataException("Category not exist!");
        }
        Category categoryForRemove = categoryForRemoveOpt.get();
        categoryForRemove.removeAllProducts();
        categoryRepository.delete(categoryForRemove);
    }
}
