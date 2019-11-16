package com.pink.unicorn.domain.PlainObjects;

import com.pink.unicorn.domain.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlainUser {

    private Long id;
    private String email;
    private String password;
    private String phone;
    private Set<Role> roles;
    private List<PlainProduct> wishList;
    private List<PlainOrder> orders = new ArrayList<>();

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

    public List<PlainProduct> getWishList() {
        return wishList;
    }

    public void setWishList(List<PlainProduct> wishList) {
        this.wishList = wishList;
    }

    public List<PlainOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<PlainOrder> orders) {
        this.orders = orders;
    }
}
