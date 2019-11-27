package com.pink.unicorn.utils;

import com.pink.unicorn.domain.Article;
import com.pink.unicorn.domain.PlainObjects.PlainArticle;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ArticleConverter {

    public PlainArticle ArticleToPlain (Article article) {
        PlainArticle plainArticle = new PlainArticle();

        plainArticle.setId(article.getId());
        plainArticle.setTheme(article.getTheme());
        plainArticle.setShortPreview(article.getShortPreview());
        plainArticle.setTextOfArticle(article.getTextOfArticle());
        plainArticle.setPicture(Base64.getEncoder().encodeToString(article.getPicture()));

        return plainArticle;
    }
}
