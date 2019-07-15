package com.pink.unicorn.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Set<Product> productList = new HashSet<>();

    public Tag() {

    }

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

    public Set<Product> getProductList() {
        return productList;
    }

    public void addTodo(Product product) {
        addTodo(product, false);
    }

    public void addTodo(Product product, boolean otherSideWasAffected) {
        getProductList().add(product);
        if (otherSideWasAffected) {
            return;
        }

        product.addTag(this, true);
    }

    public void removeTodo(Product product) {
        removeTodo(product, false);
    }

    public void removeTodo(Product product, boolean otherSideWasAffected) {
        this.getProductList().remove(product);
        if (otherSideWasAffected) {
            return;
        }
        product.removeTag(this, true);
    }

    public int hash() {
        return Objects.hash(this.getId(), this.getName());
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof Tag)) {
            return false;
        }

        Tag that = (Tag) object;

        if (!Objects.equals(this.getId(), that.getId())) {
            return false;
        }

        if (!Objects.equals(this.getName(), this.getName())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productList=" + productList.stream().map(Product::toString).collect(Collectors.joining(",\'")) +
                '}';
    }
}
