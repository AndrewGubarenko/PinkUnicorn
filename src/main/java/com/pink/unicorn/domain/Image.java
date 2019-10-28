package com.pink.unicorn.domain;

import javax.persistence.*;

/**
 * @author Andrii Hubarenko
 * <p>The entity of Image. Storing image as a BLOB in DB.</p>
 */

@Entity
@Table(name = "IMAGES")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PHOTO")
    @Lob
    private byte[] photo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Product getProduct() {
        return product;
    }

    /*public void setProduct(Product product) {
        this.product = product;
    }*/

    public void addProduct(Product product) {
        addProduct(product, false);
    }

    public void addProduct(Product product, boolean otherSideWasAffected) {
        this.product = product;
        if (otherSideWasAffected) {
            return;
        }
        product.addImage(this, true);
    }

    public void removeProduct(Product product) {
        removeProduct(product, false);
    }

    public void removeProduct(Product product, boolean otherSideWasAffected) {
        this.product = null;
        if (otherSideWasAffected) {
            return;
        }
        product.removeImage(this, true);
    }
}
