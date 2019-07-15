package com.pink.unicorn.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ARTICLES")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "THEME", nullable = false)
    private String theme;

    @Column(name = "TEXT_OF_ARTICLE", columnDefinition = "TEXT", nullable = false)
    private String textOfArticle;

    @Column(name = "SHORT_PREVIEW", columnDefinition = "TEXT", nullable = false)
    private String shortPreview;

    @Column(name = "PICTURE")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return id.equals(article.id) &&
                theme.equals(article.theme) &&
                textOfArticle.equals(article.textOfArticle) &&
                shortPreview.equals(article.shortPreview) &&
                Objects.equals(picture, article.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, theme, textOfArticle, shortPreview, picture);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", theme='" + theme + '\'' +
                ", textOfArticle='" + textOfArticle + '\'' +
                ", shortPreview='" + shortPreview + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
