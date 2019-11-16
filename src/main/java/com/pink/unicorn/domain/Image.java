package com.pink.unicorn.domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Andrii Hubarenko
 * <p>The entity of Image. Storing image as a BLOB in DB.</p>
 */

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "photo_base64")
    @Lob
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "product_id")
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

    public void addProduct(Product product) {
        addProduct(product, false);
    }

    void addProduct(Product product, boolean otherSideWasAffected) {
        this.product = product;
        if (otherSideWasAffected) {
            return;
        }
        product.addImage(this, true);
    }

    public void removeProduct(Product product) {
        removeProduct(product, false);
    }

    void removeProduct(Product product, boolean otherSideWasAffected) {
        this.product = null;
        if (otherSideWasAffected) {
            return;
        }
        product.removeImage(this, true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return id.equals(image.id) &&
                Arrays.equals(photo, image.photo) &&
                Objects.equals(product, image.product);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, product);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", photo=" + Arrays.toString(photo) +
                ", product=" + product +
                '}';
    }
}
