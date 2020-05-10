package com.pink.unicorn.controllers;

import com.pink.unicorn.domain.Article;
import com.pink.unicorn.domain.PlainObjects.PlainArticle;
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

/**
 *@author Andrii Hubarenko
 * <p>Rest Controller for processing article's requests for users and admin</p>
 */
@RestController
public class ArticleController {

    private static final Logger LOGGER = Logger.getLogger(ArticleController.class.getSimpleName());
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(path = "/admin/articles")
    public ResponseEntity<PlainArticle> createArticle(@RequestBody Article article) {
        PlainArticle response = articleService.createArticle(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/articles")
    public ResponseEntity<List<PlainArticle>> getListOfArticles() {
        List<PlainArticle> response = articleService.getListOfArticles();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/articles/{id}")
    public ResponseEntity<PlainArticle> getArticle(@PathVariable Long id) {
        PlainArticle response = articleService.getArticle(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/articles/{id}")
    public ResponseEntity<PlainArticle> updateArticle(@RequestBody Article article, @PathVariable Long id)  {
        PlainArticle response = articleService.updateArticle(article, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/articles/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id) {
        String response = articleService.deleteArticle(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Exceptions handling
     */
    @ExceptionHandler
    public ResponseEntity<String> onEmptyData(EmptyDataException e) {
        LOGGER.error(new StringBuilder(ClassUtils.getShortName(e.getClass())).append(": ").append(e.getLocalizedMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getLocalizedMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingArticle(EmptyResultDataAccessException e) {
        LOGGER.error(new StringBuilder(ClassUtils.getShortName(e.getClass())).append(": ").append(e.getLocalizedMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StringBuilder(ClassUtils.getShortName(e.getClass()))
                                                                        .append(": No such element was found")
                                                                        .append("/ ")
                                                                        .toString());
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingArticleId(NoSuchElementException e) {
        LOGGER.error(new StringBuilder(ClassUtils.getShortName(e.getClass())).append(": ").append(e.getLocalizedMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StringBuilder(ClassUtils.getShortName(e.getClass()))
                                                                        .append(": No such article was found")
                                                                        .append("/ ")
                                                                        .toString());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIOException(IOException e) {
        LOGGER.error(new StringBuilder(ClassUtils.getShortName(e.getClass())).append(": ").append(e.getLocalizedMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new StringBuilder(ClassUtils.getShortName(e.getClass()))
                                                                    .append(": Check the arguments!")
                                                                    .append("/ ")
                                                                    .append(e.getLocalizedMessage())
                                                                    .toString());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleSQLException(SQLException e) {
        LOGGER.error(new StringBuilder(ClassUtils.getShortName(e.getClass())).append(": ").append(e.getLocalizedMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new StringBuilder(ClassUtils.getShortName(e.getClass()))
                                                                    .append(": SQL statement problem")
                                                                    .append("/ ")
                                                                    .append(e.getLocalizedMessage())
                                                                    .toString());
    }
}
