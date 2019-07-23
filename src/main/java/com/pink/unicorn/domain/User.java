package com.pink.unicorn.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Andrii Hubarenko
 * <p>The entity of user. The ordinal user, who can register and uses the privileges of personal account, like wish list, order history etc.</p>
 */
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @JsonProperty("id")
    private Long id;

    @Column(name = "EMAIL", nullable = false, unique = true)
    @JsonProperty("email")
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    @JsonProperty("password")
    private String password;

    @Column(name = "PHONE")
    @JsonProperty("phone")
    private String phone;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @ElementCollection(targetClass = Product.class, fetch = FetchType.EAGER)
    @CollectionTable(name="PRODUCT", joinColumns=@JoinColumn(name="PRODUCT_ID"))
    @Column(name="WISH_LIST")
    @JsonProperty("wishlist")
    private List<Product> wishList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Product> getWishList() {
        return wishList;
    }

    public void setWishList(List<Product> wishList) {
        this.wishList = wishList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                Objects.equals(phone, user.phone) &&
                roles.equals(user.roles) &&
                Objects.equals(wishList, user.wishList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, phone, roles, wishList);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", roles=" + roles.stream().map(Role::name).collect(Collectors.joining(",")) +
                ", wishList=" + wishList.stream().map(Product::toString).collect(Collectors.joining(",\'")) +
                '}';
    }
}
