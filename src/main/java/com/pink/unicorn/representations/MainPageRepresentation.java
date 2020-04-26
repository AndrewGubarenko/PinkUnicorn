package com.pink.unicorn.representations;

import com.pink.unicorn.domain.PlainObjects.PlainArticle;
import com.pink.unicorn.domain.PlainObjects.PlainCategory;
import com.pink.unicorn.domain.PlainObjects.PlainProduct;

import java.util.Set;

public class MainPageRepresentation {

    private Set<PlainCategory> setOfCategories;
    private Set<PlainProduct> productCarousel;
    private Set<PlainProduct> saleCarousel;
    private Set<PlainArticle> articleCarousel;

    public Set<PlainCategory> getSetOfCategories() {
        return setOfCategories;
    }

    public void setSetOfCategories(Set<PlainCategory> setOfCategories) {
        this.setOfCategories = setOfCategories;
    }

    public Set<PlainProduct> getProductCarousel() {
        return productCarousel;
    }

    public void setProductCarousel(Set<PlainProduct> productCarousel) {
        this.productCarousel = productCarousel;
    }

    public Set<PlainProduct> getSaleCarousel() {
        return saleCarousel;
    }

    public void setSaleCarousel(Set<PlainProduct> saleCarousel) {
        this.saleCarousel = saleCarousel;
    }

    public Set<PlainArticle> getArticleCarousel() {
        return articleCarousel;
    }

    public void setArticleCarousel(Set<PlainArticle> articleCarousel) {
        this.articleCarousel = articleCarousel;
    }
}
