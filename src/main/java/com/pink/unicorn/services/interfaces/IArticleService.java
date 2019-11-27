package com.pink.unicorn.services.interfaces;

import com.pink.unicorn.domain.PlainObjects.PlainArticle;
import com.pink.unicorn.exceptions.EmptyDataException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Andrii Hubarenko
 * <p>This interface explains the main methods, that must be implemented by any class of Article services, to get a completely workable application.</p>
 */
public interface IArticleService {

    /**
     * <p>Method for creating article</p>
     * @param plainArticle
     * @return PlainArticle
     * @throws EmptyDataException
     */
    @Transactional
    PlainArticle createArticle(PlainArticle plainArticle) throws EmptyDataException;

    /**
     * <p>Method for updating article</p>
     * @param plainArticle
     * @param id
     * @return PlainArticle
     * @throws EmptyDataException
     */
    @Transactional
    PlainArticle updateArticle(PlainArticle plainArticle, Long id) throws EmptyDataException;

    /**
     *<p>Method for getting single article</p>
     * @param id
     * @return PlainArticle
     * @throws EmptyDataException
     */
    @Transactional
    PlainArticle getArticle(Long id) throws EmptyDataException;

    /**
     *<p>Method for getting the list of articles</p>
     * @return List<PlainArticle>
     * @throws EmptyDataException
     */
    @Transactional
    List<PlainArticle> getListOfArticles() throws EmptyDataException;

    /**
     * <p>Method for deleting article</p>
     * @param id
     * @return Confirmation message about successful deleting
     * @throws EmptyDataException
     */
    @Transactional
    String deleteArticle(Long id) throws EmptyDataException;
}

