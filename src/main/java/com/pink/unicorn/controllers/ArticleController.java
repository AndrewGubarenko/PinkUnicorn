/*
package com.pink.unicorn.controllers;

import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.services.ArticleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

*/
/**
 *@author Andrii Hubarenko
 * <p>Rest Controller for processing articles's requests</p>
 *//*

@RestController
public class ArticleController {

    private static final Logger LOGGER = Logger.getLogger(ArticleController.class.getSimpleName());
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(path = "/admin/articles/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> createArticle(@RequestBody String article) throws IOException, EmptyDataException {
        String response = articleService.create(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<String>> getListOfArticles() {
        List<String> response = articleService.getListOfArticles();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/articles/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getArticle(@PathVariable Long id) {
        String response = articleService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/articles/{id}/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> updateArticle(@RequestBody String article, @PathVariable Long id) throws IOException {
        String response = articleService.update(article, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/articles/{id}/delete")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id) {
        String response = articleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    */
/**
     * Exceptions handling
     *//*

    @ExceptionHandler
    public ResponseEntity<String> onEmptyData(EmptyDataException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(e.getClass())
                + ": One or more data`s fields are empty."
                + "/ "
                + e.getLocalizedMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingArticle(EmptyResultDataAccessException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(e.getClass())
                + ": No such element was found"
                + "/ ");
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingArticleId(NoSuchElementException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(e.getClass())
                + ": No such article was found"
                + "/ ");
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIOException(IOException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(e.getClass())
                + ": Check the arguments!"
                + "/ "
                + e.getLocalizedMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleSQLException(SQLException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(e.getClass())
                + ": SQL statement problem"
                + "/ "
                + e.getLocalizedMessage());
    }
}
*/
