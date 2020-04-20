package com.pink.unicorn.services.interfaces;

import com.pink.unicorn.domain.PlainObjects.PlainProduct;
import com.pink.unicorn.domain.Product;
import com.pink.unicorn.exceptions.EmptyDataException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author Andrii Hubarenko
 * <p>This interface explains the main methods, that must be implemented by any class of Product services, to get a completely workable application.</p>
 */
public interface IProductService {

    /**
     * <p>Method for creating product.</p>
     * @param product
     * @return PlainProduct
     */
    @Transactional
    PlainProduct createProduct(Product product);

    /**
     * <p>Method for updating product.</p>
     * @param updatedProduct
     * @param productId
     * @return PlainProduct
     */
    @Transactional
    PlainProduct updateProduct(Product updatedProduct, Long productId);

    /**
     * <p>Method for getting product.</p>
     * @param productId
     * @return PlainProduct
     */
    @Transactional
    PlainProduct getProduct(Long productId);

    /**
     * <p>Method for getting list of products.</p>
     * @return List<PlainProduct>
     */
    @Transactional
    List<PlainProduct> getProductList();

    /**
     * <p>Method for getting list of products filtered by tag.</p>
     * @param filters
     * @return List<PlainProduct>
     */
    @Transactional
    List<PlainProduct> getFilteredProductList(List<String> filters);

    /**
     * <p>Method for deleting product.</p>
     * @param id
     * @return String
     */
    @Transactional
    String deleteProduct(Long id);
}
