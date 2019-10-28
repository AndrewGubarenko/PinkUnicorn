package com.pink.unicorn.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Andrii Hubarenko
 * <p>The entity of tag. Used for dividing the products into categories.</p>
 */

@Entity
@Table(name = "TAG")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Product> productList = new ArrayList<>();

    public Tag() {}
    public Tag(String name) {
        this.name = name;
    }

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

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.removeAllProducts();
        productList.forEach(this::addProduct);
        this.productList = productList;
    }

    public void addProduct(Product product) {
        addProduct(product, false);
    }

    public void addProduct(Product product, boolean otherSideWasAffected) {
        getProductList().add(product);
        if (otherSideWasAffected) {
            return;
        }
        product.addTag(this, true);
    }

    public void removeProduct(Product product) {
        removeProduct(product, false);
    }

    public void removeProduct(Product product, boolean otherSideWasAffected) {
        this.getProductList().remove(product);
        if (otherSideWasAffected) {
            return;
        }
        product.removeTag(this, true);
    }

    public void removeAllProducts() {
        getProductList().stream().collect(Collectors.toList()).forEach(this::removeProduct);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return id.equals(tag.id) &&
                name.equals(tag.name) &&
                Objects.equals(productList, tag.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productList);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productList=" + productList +
                '}';
    }
}
