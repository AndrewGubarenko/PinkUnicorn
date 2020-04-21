package com.pink.unicorn.repositories;

import com.pink.unicorn.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * @author Andrii Hubarenko
 * Spring JPA Repository for access to Category's entitites in DB
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByName(String name);
    Set<Category> findAll();
}
