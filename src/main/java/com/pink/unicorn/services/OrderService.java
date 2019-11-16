package com.pink.unicorn.services;

import com.pink.unicorn.domain.Order;
import com.pink.unicorn.domain.PlainObjects.PlainOrder;
import com.pink.unicorn.domain.User;
import com.pink.unicorn.repositories.OrderRepository;
import com.pink.unicorn.repositories.UserRepository;
import com.pink.unicorn.utils.OrderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderConverter orderConverter;

    @Autowired
    public OrderService (OrderRepository orderRepository,
                         UserRepository userRepository,
                         OrderConverter orderConverter) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderConverter = orderConverter;
    }

    @Transactional
    public PlainOrder createOrder(PlainOrder plainOrder) {

        Order order = new Order();

        order.setEmail(plainOrder.getEmail());
        order.setFirstName(plainOrder.getFirstName());
        order.setLastName(plainOrder.getLastName());
        order.setAddress(plainOrder.getAddress());
        order.setDeliveryType(plainOrder.getDeliveryType());
        order.setPhone(plainOrder.getPhone());
        order.setPaymentType(plainOrder.getPaymentType());
        order.setIsCompleted(plainOrder.getIsCompleted());
        order.setListOfProductIds(plainOrder.getListOfProducts().stream().map(plainProduct -> plainProduct.getId()).collect(Collectors.toList()));

        orderRepository.save(order);

        User userForAdd = userRepository.findById(plainOrder.getUserId()).get();

        order.addUser(userForAdd);

        return orderConverter.OrderToPlain(order);
    }

    @Transactional
    public PlainOrder updateOrder(PlainOrder plainOrder, Long id) {
        Order orderForUpdate = orderRepository.findById(id).get();

        orderForUpdate.setEmail(plainOrder.getEmail());
        orderForUpdate.setFirstName(plainOrder.getFirstName());
        orderForUpdate.setLastName(plainOrder.getLastName());
        orderForUpdate.setAddress(plainOrder.getAddress());
        orderForUpdate.setDeliveryType(plainOrder.getDeliveryType());
        orderForUpdate.setPhone(plainOrder.getPhone());
        orderForUpdate.setPaymentType(plainOrder.getPaymentType());
        orderForUpdate.setIsCompleted(plainOrder.getIsCompleted());
        orderForUpdate.setListOfProductIds(plainOrder.getListOfProducts().stream().map(plainProduct -> plainProduct.getId()).collect(Collectors.toList()));

        orderRepository.save(orderForUpdate);

        return orderConverter.OrderToPlain(orderForUpdate);
    }

    @Transactional
    public PlainOrder getOrder(Long id) {
        return orderConverter.OrderToPlain(orderRepository.findById(id).get());
    }

    @Transactional
    public List<PlainOrder> getListOfOrders(Long userId) {
        List<PlainOrder> result = new ArrayList<>();
        orderRepository.findByUserId(userId).forEach(order -> result.add(orderConverter.OrderToPlain(order)));
        return result;
    }

    @Transactional
    public PlainOrder closeOrder(Long id) {
        Order orderForClose = orderRepository.findById(id).get();
        orderForClose.setIsCompleted(true);
        orderRepository.save(orderForClose);
        return orderConverter.OrderToPlain(orderForClose);
    }

    @Transactional
    public String deleteOrder(Long orderId, Long userId) {
        String response;
        Optional<Order> orderForDeleteOpt = orderRepository.findById(orderId);
        if(orderForDeleteOpt.isPresent()) {
            Order orderForDelete = orderForDeleteOpt.get();
            orderForDelete.removeUser(userRepository.findById(userId).get());
            orderRepository.delete(orderForDelete);
            response = "Order was removed";
        } else {
            response = "Order not found";
        }

        return response;
    }

}