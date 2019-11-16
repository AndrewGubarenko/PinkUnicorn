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

/*    public Order PlainToOrder (PlainOrder plainOrder) {
        Order result = new Order();

        result.setId(plainOrder.getId());
        result.setEmail(plainOrder.getEmail());
        result.setFirstName(plainOrder.getFirstName());
        result.setLastName(plainOrder.getLastName());
        result.setAddress(plainOrder.getAddress());
        result.setDeliveryType(plainOrder.getDeliveryType());
        result.setPhone(plainOrder.getPhone());
        result.setPaymentType(plainOrder.getPaymentType());
        result.setIsCompleted(plainOrder.getIsCompleted());
        result.setListOfProductIds(plainOrder.getListOfProductIds());

        User userForAdd = userRepository.findById(plainOrder.getUserId()).get();

        result.addUser(userForAdd);

        return result;
    }*/

}
