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

    @Column(name = "BRAND", nullable = false)
    private String brand;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "IS_IN_SALE", nullable = false)
    private boolean isInSale;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "SALE_PRICE")
    private double salePrice;

    @Column(name = "COUNT")
    private int count;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "PRODUCT_PHOTO", joinColumns = @JoinColumn(name = "PRODUCT_ID"))
    @Column(name="PRODUCT_PHOTOS")
    private List<String> photos;

    @ManyToMany
    @JoinTable(name = "PRODUCT_TAG", joinColumns = @JoinColumn(name = "PRODUCT_TAG"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private Set<Tag> tags = new HashSet<>();

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
        return isInSale;
    }

    public void setInSale(boolean inSale) {
        isInSale = inSale;
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

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public void setTags(Collection<Tag> tags) {
        this.removeAllTags();
        tags.forEach(this::addTag);
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
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", isInSale=" + isInSale +
                ", price=" + price +
                ", salePrice=" + salePrice +
                ", count=" + count +
                ", photos=" + photos +
                ", tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                isInSale == product.isInSale &&
                Double.compare(product.price, price) == 0 &&
                Double.compare(product.salePrice, salePrice) == 0 &&
                count == product.count &&
                name.equals(product.name) &&
                category.equals(product.category) &&
                brand.equals(product.brand) &&
                description.equals(product.description) &&
                Objects.equals(photos, product.photos) &&
                Objects.equals(tags, product.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, brand, description, isInSale, price, salePrice, count, photos, tags);
    }
}
