package com.pink.unicorn.services;

import java.util.List;

/**
 * @author Andrii Hubarenko
 * <p>This interface explains the main methods, that must be implemented by any class of Article services, to get a completely workable application.</p>
 */
public interface IArticleService {

    /**
     * <p>Method for creating article</p>
     * @param article
     * @return the created article in JSON format
     * @throws Exception
     */
    String create(String article) throws Exception;

    /**
     * <p>Method for updating article</p>
     * @param article
     * @param id
     * @return the updated article in JSON format
     * @throws Exception
     */
    String update(String article, Long id) throws Exception;

    /**
     *<p>Method for getting single article</p>
     * @param id
     * @return the article in JSON format
     * @throws Exception
     */
    String get(Long id) throws Exception;

    /**
     *<p>Method for getting the list of articles</p>
     * @return List of all articles
     * @throws Exception
     */
    List<String> getListOfArticles() throws Exception;

    /**
     * <p>Method for deleting article</p>
     * @param id
     * @return Confirmation message about successful deleting
     * @throws Exception
     */
    String delete(Long id) throws Exception;
}
