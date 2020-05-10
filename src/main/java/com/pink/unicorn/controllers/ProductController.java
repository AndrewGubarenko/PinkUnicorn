package com.pink.unicorn.controllers;

import com.pink.unicorn.domain.PlainObjects.PlainProduct;
import com.pink.unicorn.domain.Product;
import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.services.interfaces.IProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *@author Andrii Hubarenko
 * <p>Rest Controller for processing product's requests</p>
 */
@RestController
public class ProductController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getSimpleName());

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/admin/product")
    public ResponseEntity<PlainProduct> createProduct(@RequestBody Product product){
        PlainProduct response = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/product/{id}")
    public ResponseEntity<PlainProduct> getProduct(@PathVariable Long id) throws EmptyDataException {
        PlainProduct response = productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/product/list")
    public ResponseEntity<List<PlainProduct>> getProductList() {
        List<PlainProduct> response = productService.getProductList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/product/filtered_list")
    public ResponseEntity<List<PlainProduct>> getProductListByTags(@RequestBody List<String> filters) {
        List<PlainProduct> response = productService.getFilteredProductList(filters);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/admin/product/{id}")
    public ResponseEntity<PlainProduct> updateProduct(@RequestBody Product product, @PathVariable Long id) throws EmptyDataException {
        PlainProduct response = productService.updateProduct(product, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/admin/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws EmptyDataException {
        String response = productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Exceptions handling
     */
    @ExceptionHandler
    public ResponseEntity<String> onEmptyData(EmptyDataException e) {
        LOGGER.error(new StringBuilder(ClassUtils.getShortName(e.getClass())).append(": ").append(e.getLocalizedMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getLocalizedMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingProduct(EmptyResultDataAccessException e) {
        LOGGER.error(new StringBuilder(ClassUtils.getShortName(e.getClass())).append(": ").append(e.getLocalizedMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StringBuilder(ClassUtils.getShortName(e.getClass()))
                                                                        .append(": No such product was found")
                                                                        .toString());
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingProductId(NoSuchElementException e) {
        LOGGER.error(new StringBuilder(ClassUtils.getShortName(e.getClass())).append(": ").append(e.getLocalizedMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StringBuilder(ClassUtils.getShortName(e.getClass()))
                                                                        .append(": No such product was found")
                                                                        .toString());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIOException(IOException e) {
        LOGGER.error(new StringBuilder(ClassUtils.getShortName(e.getClass())).append(": ").append(e.getLocalizedMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new StringBuilder(ClassUtils.getShortName(e.getClass()))
                                                                    .append(": Check the arguments!")
                                                                    .toString());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleSQLException(SQLException e) {
        LOGGER.error(new StringBuilder(ClassUtils.getShortName(e.getClass())).append(": ").append(e.getLocalizedMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new StringBuilder(ClassUtils.getShortName(e.getClass()))
                                                                    .append(": SQL statement problem")
                                                                    .toString());
    }
}
