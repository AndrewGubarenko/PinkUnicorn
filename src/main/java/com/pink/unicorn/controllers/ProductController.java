package com.pink.unicorn.controllers;

import com.pink.unicorn.services.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *@author Andrii Hubarenko
 * <p>Rest Controller for processing product's requests</p>
 */
@RestController
public class ProductController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getSimpleName());

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/admin/product/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> createProduct(@RequestBody String product) {
        String response = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/product/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getProduct(@PathVariable Long id) {
        String response = productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/admin/product/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getProductList(@RequestBody List<String> tags) {
        String response = productService.getProductList(tags);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/admin/product/update/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> updateUser(@RequestBody String product, @PathVariable Long id) {
        String response = productService.updateProduct(product, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/admin/product/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String response = productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
