package com.pink.unicorn.services.interfaces;

import com.pink.unicorn.domain.PlainObjects.PlainProduct;
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
     * @param plainProduct
     * @return PlainProduct
     */
    @Transactional
    PlainProduct createProduct(PlainProduct plainProduct);

    /**
     * <p>Method for updating product.</p>
     * @param updatedPlainProduct
     * @param productId
     * @return PlainProduct
     * @throws EmptyDataException
     */
    @Transactional
    PlainProduct updateProduct(PlainProduct updatedPlainProduct, Long productId) throws EmptyDataException;

    /**
     * <p>Method for getting product.</p>
     * @param productId
     * @return PlainProduct
     * @throws EmptyDataException
     */
    @Transactional
    PlainProduct getProduct(Long productId) throws EmptyDataException;

    /**
     * <p>Method for getting list of products.</p>
     * @return PlainProduct
     * @throws EmptyDataException
     */
    @Transactional
    List<PlainProduct> getProductList();

    /**
     * <p>Method for getting list of products filtered by tag.</p>
     * @param filters
     * @return PlainProduct
     */
    @Transactional
    List<PlainProduct> getFilteredProductList(List<String> filters);

    /**
     * <p>Method for deleting product.</p>
     * @param id
     * @return String
     * @throws EmptyDataException
     */
    @Transactional
    String deleteProduct(Long id) throws EmptyDataException;
}
