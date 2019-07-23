package com.pink.unicorn.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pink.unicorn.domain.Article;
import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.repositories.ArticleRepository;
import com.pink.unicorn.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ArticleService implements IArticleService {

    private final ArticleRepository articleRepository;
    private final ObjectMapper objectMapper;
    private final Converter converter;

    @Autowired
    public ArticleService(ArticleRepository articleRepository,
                       ObjectMapper objectMapper,
                       Converter converter) {
        this.articleRepository = articleRepository;
        this.objectMapper = objectMapper;
        this.converter = converter;
    }

    @Override
    @Transactional
    public String create(String article) throws EmptyDataException, IOException {
        JsonNode rootNode = objectMapper.readTree(article);
        if (rootNode.path("theme").asText().equals("")) {
            throw new EmptyDataException("Empty THEME field");
        } else if (rootNode.path("shortPreview").asText().equals("")) {
            throw new EmptyDataException("Empty SHORT PREVIEW field");
        } else if (rootNode.path("textOfArticle").asText().equals("")) {
            throw new EmptyDataException("Empty TEXT OF ARTICLE field");
        }

        return converter.ObjectToJSON(saveArticleData(article, new Article()));
    }

    @Override
    @Transactional
    public String get(Long id) {
        Optional<Article> foundArticleOpt = articleRepository.findById(id);
        if (!foundArticleOpt.isPresent()) {
            throw new NoSuchElementException();
        }
        Article foundArticle = foundArticleOpt.get();
        return converter.ObjectToJSON(foundArticle);
    }

    @Override
    @Transactional
    public List<String> getListOfArticles() {
        Iterable<Article> listOfArticles = articleRepository.findAll();
        List<String> resultList = new ArrayList<>();
        listOfArticles.forEach(article -> resultList.add(converter.ObjectToJSON(article)));
        return resultList;
    }

    @Override
    @Transactional
    public String update(String updatedArticle, Long id) throws IOException{
        Optional<Article> articleForUpdateOpt = articleRepository.findById(id);
        if (!articleForUpdateOpt.isPresent()) {
            throw new NoSuchElementException();
        }
        return converter.ObjectToJSON(saveArticleData(updatedArticle, articleForUpdateOpt.get()));
    }

    @Override
    @Transactional
    public String delete(Long id) {
        Optional<Article> articleForDeleteOpt = articleRepository.findById(id);
        if (!articleForDeleteOpt.isPresent()) {
            throw new NoSuchElementException();
        }
        String articleTheme = articleForDeleteOpt.get().getTheme();
        articleRepository.delete(articleForDeleteOpt.get());
        return "Article by theme " + articleTheme + " was completely removed";
    }

    private Article saveArticleData(String data, Article article) throws IOException{
        JsonNode rootNode = objectMapper.readTree(data);
        article.setTheme(rootNode.path("theme").asText());
        article.setShortPreview(rootNode.path("shortPreview").asText());
        article.setTextOfArticle(rootNode.path("textOfArticle").asText());
        article.setPicture(rootNode.path("picture").asText());
        articleRepository.save(article);

        return article;
    }

}
