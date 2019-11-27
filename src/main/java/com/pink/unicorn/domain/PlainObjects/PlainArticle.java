package com.pink.unicorn.domain.PlainObjects;

public class PlainArticle {
    private Long id;
    private String theme;
    private String textOfArticle;
    private String shortPreview;
    private String picture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTextOfArticle() {
        return textOfArticle;
    }

    public void setTextOfArticle(String textOfArticle) {
        this.textOfArticle = textOfArticle;
    }

    public String getShortPreview() {
        return shortPreview;
    }

    public void setShortPreview(String shortPreview) {
        this.shortPreview = shortPreview;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
