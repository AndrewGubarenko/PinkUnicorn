package com.pink.unicorn.services.interfaces;

import com.pink.unicorn.domain.Article;
import com.pink.unicorn.domain.PlainObjects.PlainArticle;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Andrii Hubarenko
 * <p>This interface explains the main methods, that must be implemented by any class of Article services, to get a completely workable application.</p>
 */
public interface IArticleService {

    /**
     * <p>Method for creating article</p>
     * @param article
     * @return PlainArticle
     */
    @Transactional
    PlainArticle createArticle(Article article) ;

    /**
     * <p>Method for updating article</p>
     * @param article
     * @param id
     * @return PlainArticle
     */
    @Transactional
    PlainArticle updateArticle(Article article, Long id);

    /**
     *<p>Method for getting single article</p>
     * @param id
     * @return PlainArticle
     */
    @Transactional
    PlainArticle getArticle(Long id);

    /**
     *<p>Method for getting the list of articles</p>
     * @return List<PlainArticle>
     */
    @Transactional
    List<PlainArticle> getListOfArticles();

    /**
     * <p>Method for deleting article</p>
     * @param id
     * @return String
     */
    @Transactional
    String deleteArticle(Long id);
}

