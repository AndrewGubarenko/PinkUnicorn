package com.pink.unicorn.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Andrii Hubarenko
 * <p>The entity of user. The ordinal user, who can register and uses the privileges of personal account, like wish list, order history etc.</p>
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false, updatable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone")
    private String phone;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @ElementCollection
    @CollectionTable(name="wish_list")
    private List<Long> wishList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

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

    public List<Long> getWishList() {
        return wishList;
    }

    public void setWishList(List<Long> wishList) {
        this.wishList.clear();
        wishList.forEach(this::accept);
    }

    /*------------------------------------------------------------------*/

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.removeAllOrders();
        orders.forEach(this::addOrder);
    }

    public void addOrder(Order order) {
        addOrder(order, false);
    }

    public void addOrder(Order order, boolean otherSideWasAffected) {
        getOrders().add(order);
        if (otherSideWasAffected) {
            return;
        }
        order.addUser(this, true);
    }

    public void removeAllOrders() {
        getOrders().stream().collect(Collectors.toList()).forEach(this::removeOrder);
    }

    public void removeOrder(Order order) {
        removeOrder(order, false);
    }

    public void removeOrder(Order order, boolean otherSideWasAffected) {
        this.getOrders().remove(order);
        if (otherSideWasAffected) {
            return;
        }
        order.removeUser(this, true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                phone.equals(user.phone) &&
                roles.equals(user.roles) &&
                Objects.equals(wishList, user.wishList) &&
                Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, phone, roles, wishList, orders);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", roles=" + roles +
                ", wishList=" + wishList +
                ", orders=" + orders +
                '}';
    }

    private void accept(Long id) {
        this.wishList.add(id);
    }
}
