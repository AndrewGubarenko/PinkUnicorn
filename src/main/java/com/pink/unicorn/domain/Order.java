package com.pink.unicorn.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Andrii Hubarenko
 * <p>The entity of order. Contains the list of products, user is going to buy, contact and delivery information.</p>
 */
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ElementCollection(targetClass = Product.class, fetch = FetchType.EAGER)
    @CollectionTable(name="PRODUCT", joinColumns=@JoinColumn(name="PRODUCT_ID"))
    @Column(name="LIST_OF_PRODUCTS", nullable = false)
    private List<Product> listOfProducts;

    @Column(name = "DELIVERY_TYPE", nullable = false)
    private String deliveryType;

    @Column(name = "PAYMENT_TYPE", nullable = false)
    private String paymentType;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "IS_COMPLITED", nullable = false)
    private Boolean isComplited;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsComplited() {
        return isComplited;
    }

    public void setIsComplited(Boolean isComplited) {
        this.isComplited = isComplited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id.equals(order.id) &&
                listOfProducts.equals(order.listOfProducts) &&
                deliveryType.equals(order.deliveryType) &&
                paymentType.equals(order.paymentType) &&
                firstName.equals(order.firstName) &&
                lastName.equals(order.lastName) &&
                phone.equals(order.phone) &&
                Objects.equals(email, order.email) &&
                address.equals(order.address) &&
                isComplited.equals(order.isComplited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, listOfProducts, deliveryType, paymentType, firstName, lastName, phone, email, address, isComplited);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", listOfProducts=" + listOfProducts.stream().map(Product::toString).collect(Collectors.joining(",\'")) +
                ", deliveryType='" + deliveryType + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", isComplited=" + isComplited +
                '}';
    }
}
