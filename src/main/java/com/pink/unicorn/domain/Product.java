package com.pink.unicorn.domain;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Andrii Hubarenko
 * <p>The entity of product.</p>
 */

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    //todo: check, should it be to be obligatory
    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "in_sale", nullable = false)
    private boolean inSale;

    @Column(name = "price")
    private double price;

    @Column(name = "sale_price")
    private double salePrice;

    @Column(name = "count")
    private int count;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Image> images = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="sub_cats")
    private Set<String> subCategories = new HashSet<>();

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

    public Set<String> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<String> subCategories) {
        this.subCategories.clear();
        subCategories.forEach(subCat -> this.subCategories.add(subCat));
    }

    /*------------------------------------------------------------------*/

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.removeAllImages();
        images.forEach(this::addImage);
    }

    public void addImage(Image image) {
        addImage(image, false);
    }

    public void addImage(Image image, boolean otherSideWasAffected) {
        getImages().add(image);
        if (otherSideWasAffected) {
            return;
        }
        image.addProduct(this, true);
    }

    public void removeAllImages() {
        getImages().stream().collect(Collectors.toList()).forEach(this::removeImage);
    }

    public void removeImage(Image image) {
        removeImage(image, false);
    }

    public void removeImage(Image image, boolean otherSideWasAffected) {
        this.getImages().remove(image);
        if (otherSideWasAffected) {
            return;
        }
        image.removeProduct(this, true);
    }

    /*------------------------------------------------------------------*/

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.removeAllCategories();
        categories.forEach(this::addCategory);
    }

    public void addCategory(Category category) {
        addCategory(category, false);
    }

    public void addCategory(Category category, boolean otherSideWasAffected) {
        getCategories().add(category);
        if (otherSideWasAffected) {
            return;
        }
        category.addProduct(this, true);
    }

    public void removeAllCategories() {
        getCategories().stream().collect(Collectors.toList()).forEach(this::removeCategory);
    }

    public void removeCategory(Category category) {
        removeCategory(category, false);
    }

    public void removeCategory(Category category, boolean otherSideWasAffected) {
        this.getCategories().remove(category);
        if (otherSideWasAffected) {
            return;
        }
        category.removeProduct(this, true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                name.equals(product.name) &&
                brand.equals(product.brand) &&
                description.equals(product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, description, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", inSale=" + inSale + '\'' +
                ", price=" + price + '\'' +
                ", salePrice=" + salePrice + '\'' +
                ", count=" + count + '\'' +
                ", images=" + images + '\'' +
                ", categories=" + categories +
                '}';
    }
}
