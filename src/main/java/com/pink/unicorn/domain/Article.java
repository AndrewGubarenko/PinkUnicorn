package com.pink.unicorn.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Andrii Hubarenko
 * <p>The entity of article.</p>
 */
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "theme", nullable = false)
    private String theme;

    @Column(name = "text_of_article", columnDefinition = "text", nullable = false)
    private String textOfArticle;

    @Column(name = "short_preview", columnDefinition = "text", nullable = false)
    private String shortPreview;

    @Column(name = "photo_base64")
    @Lob
    private byte[] picture;

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

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
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
                ", picture='" + Arrays.toString(picture) + '\'' +
                '}';
    }
}
