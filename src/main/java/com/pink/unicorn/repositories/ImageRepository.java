package com.pink.unicorn.repositories;

import com.pink.unicorn.domain.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Andrii Hubarenko
 * Spring JPA Repository for access to image's entitites in DB
 */
@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
}
