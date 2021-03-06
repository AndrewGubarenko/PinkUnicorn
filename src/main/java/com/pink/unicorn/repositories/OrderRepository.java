package com.pink.unicorn.repositories;

import com.pink.unicorn.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Andrii Hubarenko
 * Spring JPA Repository for access to order's entitites in DB
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
}
