package com.pink.unicorn.services;

import com.pink.unicorn.domain.Article;
import com.pink.unicorn.domain.PlainObjects.PlainArticle;
import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.repositories.ArticleRepository;
import com.pink.unicorn.services.interfaces.IArticleService;
import com.pink.unicorn.utils.ArticleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService implements IArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;

    @Autowired
    public ArticleService(ArticleRepository articleRepository,
                          ArticleConverter articleConverter) {
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
    }

    @Override
    @Transactional
    public PlainArticle createArticle(Article article) {
        this.checkForEmptyFields(article);

        articleRepository.save(article);

        return articleConverter.ArticleToPlain(article);
    }

    @Override
    @Transactional
    public PlainArticle getArticle(Long id) {
        Optional<Article> articleOpt = articleRepository.findById(id);
        if(!articleOpt.isPresent()) {
            throw new EmptyDataException("No Article with id " + id + " exist!");
        }
        return articleConverter.ArticleToPlain(articleOpt.get());
    }

    @Override
    @Transactional
    public List<PlainArticle> getListOfArticles() {
        List<PlainArticle> resultList = new ArrayList<>();
        articleRepository.findAll().forEach(article -> resultList.add(articleConverter.ArticleToPlain(article)));
        return  resultList;
    }

    @Override
    @Transactional
    public PlainArticle updateArticle(Article source, Long id) throws EmptyDataException {
        this.checkForEmptyFields(source);

        Optional<Article> articleForUpdateOpt = articleRepository.findById(id);
        if(!articleForUpdateOpt.isPresent()) {
            throw new EmptyDataException("No Article with id " + id + " exist!");
        }
        Article target = articleForUpdateOpt.get();

        target.setTheme(source.getTheme());
        target.setShortPreview(source.getShortPreview());
        target.setTextOfArticle(source.getTextOfArticle());
        target.setPicture(Base64.getDecoder().decode(source.getPicture()));

        articleRepository.save(target);

        return articleConverter.ArticleToPlain(target);
    }

    @Override
    @Transactional
    public String deleteArticle(Long id) throws EmptyDataException {
        Optional<Article> articleForDeleteOpt = articleRepository.findById(id);
        if(!articleForDeleteOpt.isPresent()) {
            throw new EmptyDataException("No Article with id " + id + " exists");
        }
        String theme = articleForDeleteOpt.get().getTheme();
        articleRepository.delete(articleForDeleteOpt.get());
        return "Article with theme: \"" + theme + "\" was completely removed";
    }

    private void checkForEmptyFields (Article article) {
        if(article.getTheme().equals("") || article.getTheme() == null) {
            throw new EmptyDataException("Theme field is empty!");
        }
        if(article.getShortPreview().equals("") || article.getShortPreview() == null) {
            throw new EmptyDataException("Short preview field is empty!");
        }
        if(article.getTextOfArticle().equals("") || article.getTextOfArticle() == null) {
            throw new EmptyDataException("Text of plainArticle field is empty!");
        }
    }
}
