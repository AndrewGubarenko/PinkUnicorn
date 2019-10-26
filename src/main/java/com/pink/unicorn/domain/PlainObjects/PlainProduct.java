package com.pink.unicorn.domain.PlainObjects;

import java.util.HashSet;
import java.util.Set;

public class PlainProduct {

    private long id;
    private String name;
    private String category;
    private String brand;
    private String description;
    private boolean inSale;
    private double price;
    private double salePrice;
    private int count;
    private Set<PlainImage> images = new HashSet<>();
    private Set<PlainTag> tags = new HashSet<>();

    public PlainProduct(){}

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

    public Set<PlainImage> getImages() {
        return images;
    }

    public void setImages(Set<PlainImage> images) {
        this.images = images;
    }

    public Set<PlainTag> getTags() {
        return tags;
    }

    public void setTags(Set<PlainTag> tags) {
        this.tags = tags;
    }
}
