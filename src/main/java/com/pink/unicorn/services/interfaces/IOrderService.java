package com.pink.unicorn.services.interfaces;

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
     * @param plainOrder
     * @return PlainOrder
     */
    @Transactional
    PlainOrder createOrder(PlainOrder plainOrder);

    /**
     * <p>Method for updating order.</p>
     * @param plainOrder
     * @param id
     * @return PlainOrder
     * @throws EmptyDataException
     */
    @Transactional
    PlainOrder updateOrder(PlainOrder plainOrder, Long id) throws EmptyDataException;

    /**
     * <p>Method for getting order.</p>
     * @param id
     * @return PlainOrder
     * @throws EmptyDataException
     */
    @Transactional
    PlainOrder getOrder(Long id) throws EmptyDataException;

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
     * @throws EmptyDataException
     */
    @Transactional
    String deleteOrder(Long orderId, Long userId) throws EmptyDataException;
}
