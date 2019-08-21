package com.pink.unicorn.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Transactional
    public String createProduct(String product) {
        return null;
    }

    @Transactional
    public String updateProduct(String product, Long id) {
        return null;
    }

    @Transactional
    public String getProduct(Long id) {
        return null;
    }

    @Transactional
    public String getProductList(List<String> tags) {
        return null;
    }

    @Transactional
    public String getProductListByTags(String...filters) {
        return null;
    }

    @Transactional
    public String deleteProduct(Long id) {
        return null;
    }
}
