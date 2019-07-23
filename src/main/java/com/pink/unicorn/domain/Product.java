package com.pink.unicorn.domain;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Andrii Hubarenko
 * <p>The entity of product.</p>
 */
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "IS_IN_SALE", nullable = false)
    private Boolean isInSale;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "SALE_PRICE")
    private Double salePrice;

    @Column(name = "COUNT")
    private Integer count;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "PRODUCT_PHOTO", joinColumns = @JoinColumn(name = "PRODUCT_ID"))
    @Column(name="PRODUCT_PHOTOS")
    private List<String> photos;

    @ManyToMany
    @JoinTable(name = "PRODUCT_TAG", joinColumns = @JoinColumn(name = "PRODUCT_TAG"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private Set<Tag> tags = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getInSale() {
        return isInSale;
    }

    public void setInSale(Boolean inSale) {
        isInSale = inSale;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        addTag(tag, false);
    }

    public void addTag(Tag tag, boolean otherSideWasAffected) {
        getTags().add(tag);
        if (otherSideWasAffected) {
            return;
        }

        tag.addTodo(this, true);
    }

    public void setTags(Collection<Tag> tags) {
        this.removeAllTags();
        tags.forEach(this::addTag);
    }

    public void removeAllTags() {
        getTags().stream().collect(Collectors.toList()).forEach(this::removeTag);
    }

    public void removeTag(Tag tag) {
        removeTag(tag, false);
    }

    public void removeTag(Tag tag, boolean otherSideWasAffected) {
        this.getTags().remove(tag);
        if (otherSideWasAffected) {
            return;
        }
        tag.removeTodo(this, true);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isInSale=" + isInSale +
                ", price=" + price +
                ", salePrice=" + salePrice +
                ", count=" + count +
                ", photos=" + photos.stream().collect(Collectors.joining(",")) +
                ", tags=" + tags.stream().map(Tag::toString).collect(Collectors.joining(",")) +
                '}';
    }
}
