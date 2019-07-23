package com.pink.unicorn.repositories;

import com.pink.unicorn.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Andrii Hubarenko
 * Spring JPA Repository for access to product's entitites in DB
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
