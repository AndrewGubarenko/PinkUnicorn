package com.pink.unicorn.services;

import com.pink.unicorn.domain.Order;
import com.pink.unicorn.domain.PlainObjects.PlainOrder;
import com.pink.unicorn.domain.User;
import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.repositories.OrderRepository;
import com.pink.unicorn.repositories.UserRepository;
import com.pink.unicorn.services.interfaces.IOrderService;
import com.pink.unicorn.utils.OrderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

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

    @Override
    @Transactional
    public PlainOrder createOrder(PlainOrder plainOrder) {
        Order order = setOrderData(plainOrder, new Order());
        orderRepository.save(order);
        User userForAdd = userRepository.findById(plainOrder.getUserId()).get();
        order.addUser(userForAdd);
        return orderConverter.OrderToPlain(order);
    }

    @Override
    @Transactional
    public PlainOrder updateOrder(PlainOrder plainOrder, Long id) throws EmptyDataException{
        Optional<Order> orderForUpdateOpt = orderRepository.findById(id);
        if (!orderForUpdateOpt.isPresent()) {
            throw new EmptyDataException("No order with id " + id + " exists!" );
        }
        Order orderForUpdate = setOrderData(plainOrder, orderForUpdateOpt.get());
        orderRepository.save(orderForUpdate);

        return orderConverter.OrderToPlain(orderForUpdate);
    }

    @Override
    @Transactional
    public PlainOrder getOrder(Long id) throws EmptyDataException {
        Optional<Order> orderOpt = orderRepository.findById(id);
        if (!orderOpt.isPresent()) {
            throw new EmptyDataException("No order with id " + id + " exists!" );
        }
        return orderConverter.OrderToPlain(orderOpt.get());
    }

    @Override
    @Transactional
    public List<PlainOrder> getListOfOrders(Long userId) {
        List<PlainOrder> result = new ArrayList<>();
        orderRepository.findByUserId(userId).forEach(order -> result.add(orderConverter.OrderToPlain(order)));
        return result;
    }

    @Override
    @Transactional
    public String deleteOrder(Long orderId, Long userId) throws EmptyDataException {
        String response;
        Optional<Order> orderForDeleteOpt = orderRepository.findById(orderId);
        if(orderForDeleteOpt.isPresent()) {
            Order orderForDelete = orderForDeleteOpt.get();
            orderForDelete.getListOfProductIds().clear();
            orderForDelete.removeUser(userRepository.findById(userId).get());
            orderRepository.delete(orderForDelete);
            response = "Order was removed";
        } else {
            response = "Order not found";
        }
        return response;
    }

    private Order setOrderData(PlainOrder resource, Order target) {
        target.setEmail(resource.getEmail());
        target.setFirstName(resource.getFirstName());
        target.setLastName(resource.getLastName());
        target.setAddress(resource.getAddress());
        target.setDeliveryType(resource.getDeliveryType());
        target.setPhone(resource.getPhone());
        target.setPaymentType(resource.getPaymentType());
        target.setIsCompleted(resource.getIsCompleted());
        target.setListOfProductIds(resource.getListOfProducts().stream().map(plainProduct -> plainProduct.getId()).collect(Collectors.toList()));

        return target;
    }

}