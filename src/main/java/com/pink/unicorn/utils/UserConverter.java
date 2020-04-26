package com.pink.unicorn.utils;

import com.pink.unicorn.domain.PlainObjects.PlainUser;
import com.pink.unicorn.domain.User;
import com.pink.unicorn.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final OrderConverter orderConverter;

    @Autowired
    public UserConverter (ProductRepository productRepository,
                          ProductConverter productConverter,
                          OrderConverter orderConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.orderConverter = orderConverter;
    }

    public PlainUser UserToPlain (User user) {
        PlainUser result = new PlainUser();

        result.setId(user.getId());
        result.setEmail(user.getEmail());
        result.setPassword("");
        result.setPhone(user.getPhone());
        result.setRoles(user.getRoles());
        if(!(user.getWishList() == null)) {
            result.setWishList(user.getWishList().stream().map(productId -> productConverter.ProductToPlain(productRepository.findById(productId).get())).collect(Collectors.toList()));
        } else {
            result.setWishList(new ArrayList<>());
        }

        if(!(user.getOrders() == null)) {
            result.setOrders(user.getOrders().stream().map(order -> orderConverter.OrderToPlain(order)).collect(Collectors.toList()));
        } else {
            result.setOrders(new ArrayList<>());
        }
        return result;
    }
}
