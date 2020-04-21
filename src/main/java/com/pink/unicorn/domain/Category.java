package com.pink.unicorn.domain;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Andrii Hubarenko
 * <p>The entity of tag. Used for dividing the products into categories.</p>
 */

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="sub_categories")
    private Set<String> subCategories = new HashSet<>();

    public Category() {}
    public Category(String name) {
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
    }

    public Set<String> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<String> subCategories) {
        this.subCategories.clear();
        subCategories.forEach(subCat -> this.subCategories.add(subCat));
    }

    public void addProduct(Product product) {
        addProduct(product, false);
    }

    public void addProduct(Product product, boolean otherSideWasAffected) {
        getProductList().add(product);
        if (otherSideWasAffected) {
            return;
        }
        product.addCategory(this, true);
    }

    public void removeProduct(Product product) {
        removeProduct(product, false);
    }

    public void removeProduct(Product product, boolean otherSideWasAffected) {
        this.getProductList().remove(product);
        if (otherSideWasAffected) {
            return;
        }
        product.removeCategory(this, true);
    }

    public void removeAllProducts() {
        getProductList().stream().collect(Collectors.toList()).forEach(this::removeProduct);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id.equals(category.id) &&
                name.equals(category.name) &&
                Objects.equals(productList, category.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productList);
    }

    @Override
    public String toString() {
        String prodList = "";
        for(int i = 0; i < productList.size(); i++) {
            prodList += " " + productList.get(i);
        }
        return "Category{" + '\'' +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", productList=" + "[ " + prodList + " ]" + '\'' +
                '}';
    }
}
