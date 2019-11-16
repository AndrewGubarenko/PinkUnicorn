package com.pink.unicorn.repositories;

import com.pink.unicorn.domain.Product;
import com.pink.unicorn.domain.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Andrii Hubarenko
 * Spring JPA Repository for access to product's entitites in DB
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByTags(Tag tag);
}
