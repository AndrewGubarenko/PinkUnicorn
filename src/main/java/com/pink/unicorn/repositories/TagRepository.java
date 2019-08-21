package com.pink.unicorn.repositories;

import com.pink.unicorn.domain.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Andrii Hubarenko
 * Spring JPA Repository for access to tag's entitites in DB
 */
@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
}