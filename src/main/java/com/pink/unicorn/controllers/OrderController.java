package com.pink.unicorn.controllers;

import com.pink.unicorn.domain.PlainObjects.PlainOrder;
import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *@author Andrii Hubarenko
 * <p>Rest Controller for processing order's requests for users</p>
 */
@RestController
public class OrderController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getSimpleName());

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService productService) {
        this.orderService = productService;
    }

    @PostMapping(path = "/user/{userId}/order/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PlainOrder> createProduct(@RequestBody PlainOrder plainOrder) {
        PlainOrder response = orderService.createOrder(plainOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/user/{userId}/order/{orderId}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PlainOrder> getProduct(@PathVariable Long orderId) {
        PlainOrder response = orderService.getOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/user/{userId}/order/list", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<PlainOrder>> getProductList(@PathVariable Long userId) {
        List<PlainOrder> response = orderService.getListOfOrders(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/user/{userId}/order/{orderId}/update", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PlainOrder> updateProduct(@RequestBody PlainOrder plainOrder, @PathVariable Long orderId) {
        PlainOrder response = orderService.updateOrder(plainOrder, orderId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/user/{userId}/order/{orderId}/delete")
    public ResponseEntity<String> deleteProduct(@PathVariable Long orderId, @PathVariable Long userId) {
        String response = orderService.deleteOrder(orderId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Exceptions handling
     */
    @ExceptionHandler
    public ResponseEntity<String> onEmptyData(EmptyDataException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(e.getClass()) + ": One or more data`s fields are empty.");
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingProduct(EmptyResultDataAccessException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(e.getClass()) + ": No such order was found");
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingProductId(NoSuchElementException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(e.getClass()) + ": No such order was found");
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIOException(IOException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(e.getClass()) + ": Check the arguments!");
    }

    @ExceptionHandler
    public ResponseEntity<String> handleSQLException(SQLException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(e.getClass()) + ": SQL statement problem");
    }
}
