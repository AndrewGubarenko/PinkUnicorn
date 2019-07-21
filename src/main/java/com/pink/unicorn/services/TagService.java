package com.pink.unicorn.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagService {

    @Transactional
    public String create(String tag) {
        return null;
    }

    @Transactional
    public String update(String tag, Long id) {
        return null;
    }

    @Transactional
    public String get(Long id) {
        return null;
    }

    @Transactional
    public String getListOfTags() {
        return null;
    }

    @Transactional
    public String delete(Long id) {
        return null;
    }

}
