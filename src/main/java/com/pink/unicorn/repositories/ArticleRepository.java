package com.pink.unicorn.repositories;

import com.pink.unicorn.domain.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Andrii Hubarenko
 * Spring JPA Repository for access to article's entitites in DB
 */
@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
