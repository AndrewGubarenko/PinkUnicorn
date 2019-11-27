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
    public PlainArticle createArticle(PlainArticle plainArticle) throws EmptyDataException {
        this.checkForEmptyFields(plainArticle);

        Article newArticle = this.setArticleData(plainArticle, new Article());
        articleRepository.save(newArticle);

        return articleConverter.ArticleToPlain(newArticle);
    }

    @Override
    @Transactional
    public PlainArticle getArticle(Long id) throws EmptyDataException {
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
    public PlainArticle updateArticle(PlainArticle updatedArticle, Long id) throws EmptyDataException {
        this.checkForEmptyFields(updatedArticle);

        Optional<Article> articleForUpdateOpt = articleRepository.findById(id);
        if(!articleForUpdateOpt.isPresent()) {
            throw new EmptyDataException("No Article with id " + id + " exist!");
        }
        Article articleForUpdate = this.setArticleData(updatedArticle, articleForUpdateOpt.get());
        articleRepository.save(articleForUpdate);

        return articleConverter.ArticleToPlain(articleForUpdate);
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

    private void checkForEmptyFields (PlainArticle plainArticle) throws EmptyDataException{
        if(plainArticle.getTheme().equals("") || plainArticle.getTheme() == null) {
            throw new EmptyDataException("Theme field is empty!");
        }
        if(plainArticle.getShortPreview().equals("") || plainArticle.getShortPreview() == null) {
            throw new EmptyDataException("Short preview field is empty!");
        }
        if(plainArticle.getTextOfArticle().equals("") || plainArticle.getTextOfArticle() == null) {
            throw new EmptyDataException("Text of plainArticle field is empty!");
        }
    }

    private Article setArticleData (PlainArticle source, Article target) {
        target.setTheme(source.getTheme());
        target.setShortPreview(source.getShortPreview());
        target.setTextOfArticle(source.getTextOfArticle());
        target.setPicture(Base64.getDecoder().decode(source.getPicture()));
        return target;
    }

}
