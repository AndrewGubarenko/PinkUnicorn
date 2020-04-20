package com.pink.unicorn.services.interfaces;

import com.pink.unicorn.domain.Category;
import com.pink.unicorn.domain.PlainObjects.PlainCategory;
import com.pink.unicorn.domain.Product;
import com.pink.unicorn.exceptions.EmptyDataException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Andrii Hubarenko
 * <p>This interface explains the main methods, that must be implemented by any class of Category services, to get a completely workable application.</p>
 */
public interface ICategoryService {
    /**
     * <p>Method for creating Category if it is not exist in DB.</p>
     * @param category
     * @param product
     * @return List<Category>
     */
    @Transactional
    List<Category> findOrCreate(Collection<Category> category, Product product);

    /**
     * <p>Method for deleting Category.</p>
     * @param categoryName
     * @return void
     */
    @Transactional
    void removeCategory(String categoryName);
}
