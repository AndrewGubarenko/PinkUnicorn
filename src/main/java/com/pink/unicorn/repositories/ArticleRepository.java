package com.pink.unicorn.repositories;

import com.pink.unicorn.domain.Article;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author Andrii Hubarenko
 * Spring JPA Repository for access to article's entitites in DB
 */
@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {

    @Query(value = "SELECT * FROM articles ORDER BY ID DESC LIMIT 10", nativeQuery = true)
    Set<Article> getFirst10Articles();
}
