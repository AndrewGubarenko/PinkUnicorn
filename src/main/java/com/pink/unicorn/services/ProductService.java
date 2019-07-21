package com.pink.unicorn.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Transactional
    public String create(String product) {
        return null;
    }

    @Transactional
    public String update(String product, Long id) {
        return null;
    }

    @Transactional
    public String getProduct(Long id) {
        return null;
    }

    @Transactional
    public String getProductList() {
        return null;
    }

    @Transactional
    public String getProductListByFilters(String...filters) {
        return null;
    }

    @Transactional
    public String delete(Long id) {
        return null;
    }
}
