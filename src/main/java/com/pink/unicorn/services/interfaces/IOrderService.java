package com.pink.unicorn.services.interfaces;

import com.pink.unicorn.domain.Order;
import com.pink.unicorn.domain.PlainObjects.PlainOrder;
import com.pink.unicorn.exceptions.EmptyDataException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Andrii Hubarenko
 * <p>This interface explains the main methods, that must be implemented by any class of Order services, to get a completely workable application.</p>
 */
public interface IOrderService {

    /**
     * <p>Method for creating order.</p>
     * @param order
     * @param userId
     * @return PlainOrder
     */
    @Transactional
    PlainOrder createOrder(Order order, Long userId);

    /**
     * <p>Method for updating order.</p>
     * @param order
     * @param id
     * @return PlainOrder
     */
    @Transactional
    PlainOrder updateOrder(Order order, Long id);

    /**
     * <p>Method for getting order.</p>
     * @param id
     * @return PlainOrder
     */
    @Transactional
    PlainOrder getOrder(Long id);

    /**
     * <p>Method for getting list of orders.</p>
     * @param userId
     * @return List<PlainOrder>
     */
    @Transactional
    List<PlainOrder> getListOfOrders(Long userId);

    /**
     * <p>Method for deleting order.</p>
     * @param orderId
     * @param userId
     * @return String
     */
    @Transactional
    String deleteOrder(Long orderId, Long userId);
}
