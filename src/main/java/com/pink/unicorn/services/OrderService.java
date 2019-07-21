package com.pink.unicorn.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Transactional
    public String create(String order) {
        return null;
    }

    @Transactional
    public String update(String order, Long id) {
        return null;
    }

    @Transactional
    public String get(Long id) {
        return null;
    }

    @Transactional
    public String getListOfOrders() {
        return null;
    }

    @Transactional
    public String closeOrder(Long id) {
        return null;
    }

}