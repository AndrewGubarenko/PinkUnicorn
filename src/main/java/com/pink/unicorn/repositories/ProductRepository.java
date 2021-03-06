package com.pink.unicorn.repositories;

import com.pink.unicorn.domain.Product;
import com.pink.unicorn.domain.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author Andrii Hubarenko
 * Spring JPA Repository for access to product's entitites in DB
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByCategories(Category category);

    @Query(value = "SELECT * FROM products ORDER BY ID DESC LIMIT 10", nativeQuery = true)
    Set<Product> getFirst10();


    Set<Product> getFirs10ByInSaleTrue();
}
