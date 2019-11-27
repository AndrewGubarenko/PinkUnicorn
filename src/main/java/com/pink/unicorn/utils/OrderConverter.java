package com.pink.unicorn.utils;

import com.pink.unicorn.domain.Order;
import com.pink.unicorn.domain.PlainObjects.PlainOrder;
import com.pink.unicorn.domain.User;
import com.pink.unicorn.repositories.ProductRepository;
import com.pink.unicorn.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderConverter {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    @Autowired
    public OrderConverter (UserRepository userRepository,
                           ProductRepository productRepository,
                           ProductConverter productConverter) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    public PlainOrder OrderToPlain (Order order) {
        PlainOrder result = new PlainOrder();

        result.setId(order.getId());
        result.setEmail(order.getEmail());
        result.setFirstName(order.getFirstName());
        result.setLastName(order.getLastName());
        result.setAddress(order.getAddress());
        result.setDeliveryType(order.getDeliveryType());
        result.setPhone(order.getPhone());
        result.setPaymentType(order.getPaymentType());
        result.setIsCompleted(order.getIsCompleted());
        result.setListOfProducts(order.getListOfProductIds().stream().map(productId -> productConverter.ProductToPlain(productRepository.findById(productId).get())).collect(Collectors.toList()));
        result.setUserId(order.getUser().getId());

        return result;
    }
}
