package com.pink.unicorn.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {

    @Transactional
    public String create(String article) {
        return null;
    }

    @Transactional
    public String update(String article, Long id) {
        return null;
    }

    @Transactional
    public String get(Long id) {
        return null;
    }

    @Transactional
    public String getListOfArticles() {
        return null;
    }

    @Transactional
    public String delete(Long id) {
        return null;
    }

}
