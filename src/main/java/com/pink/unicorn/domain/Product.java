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
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    //todo: check, should it be to be obligatory
    @Column(name = "BRAND", nullable = false)
    private String brand;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "IS_IN_SALE", nullable = false)
    private boolean inSale;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "SALE_PRICE")
    private double salePrice;

    @Column(name = "COUNT")
    private int count;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column
    private List<Image> images = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "PRODUCT_TAG", joinColumns = @JoinColumn(name = "PRODUCT_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private List<Tag> tags = new ArrayList<>();

    public Product() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isInSale() {
        return inSale;
    }

    public void setInSale(boolean inSale) {
        this.inSale = inSale;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.removeAllTags();
        tags.forEach(this::addTag);
    }

    public void addTag(Tag tag) {
        addTag(tag, false);
    }

    public void addTag(Tag tag, boolean otherSideWasAffected) {
        getTags().add(tag);
        if (otherSideWasAffected) {
            return;
        }
        tag.addProduct(this, true);
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
        tag.removeProduct(this, true);
    }

}
