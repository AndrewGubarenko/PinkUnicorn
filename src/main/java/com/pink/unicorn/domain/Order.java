package com.pink.unicorn.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Andrii Hubarenko
 * <p>The entity of order. Contains the list of products, user is going to buy, contact and delivery information.</p>
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ElementCollection(targetClass = Long.class)
    @CollectionTable(name ="list_of_product_ids" , joinColumns=@JoinColumn(name="product_id"))
    private List<Long> listOfProductIds = new ArrayList<>();

    @Column(name = "delivery_type", nullable = false)
    private String deliveryType;

    @Column(name = "payment_type", nullable = false)
    private String paymentType;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getListOfProductIds() {
        return listOfProductIds;
    }

    public void setListOfProductIds(List<Long> listOfProductIds) {
        this.listOfProductIds.clear();
        this.listOfProductIds.addAll(listOfProductIds);
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

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public User getUser() {
        return user;
    }

    /*-------------------------------------------------------*/

    public void addUser(User user) {
        addUser(user, false);
    }

    void addUser(User user, boolean otherSideWasAffected) {
        this.user = user;
        if (otherSideWasAffected) {
            return;
        }
        user.addOrder(this, true);
    }

    public void removeUser(User user) {
        removeUser(user, false);
    }

    void removeUser(User user, boolean otherSideWasAffected) {
        this.user = null;
        if (otherSideWasAffected) {
            return;
        }
        user.removeOrder(this, true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) &&
                listOfProductIds.equals(order.listOfProductIds) &&
                deliveryType.equals(order.deliveryType) &&
                paymentType.equals(order.paymentType) &&
                firstName.equals(order.firstName) &&
                lastName.equals(order.lastName) &&
                phone.equals(order.phone) &&
                email.equals(order.email) &&
                address.equals(order.address) &&
                isCompleted.equals(order.isCompleted) &&
                Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, listOfProductIds, deliveryType, paymentType, firstName, lastName, phone, email, address, isCompleted, user);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", listOfProductIds=" + listOfProductIds.stream().map(id -> id.toString()) +
                ", deliveryType='" + deliveryType + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", isCompleted=" + isCompleted +
                ", user=" + user +
                '}';
    }
}
