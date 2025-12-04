package com.homepulse.dto;

import com.homepulse.model.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link com.homepulse.model.Image}
 */
public class ImageDto implements Serializable {
    private  Long id;
    @NotNull
    private  Product product;
    @NotNull
    @Size(max = 255)
    private  String image;
    private  Boolean isMain;
    private  Integer position;
    private  Instant createdAt;
    private  Instant updatedAt;

    public ImageDto() {
    }

    public ImageDto(Long id, Product product, String image, Boolean isMain, Integer position, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.product = product;
        this.image = image;
        this.isMain = isMain;
        this.position = position;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {}

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {}

    public String getImage() {
        return image;
    }
    public void setImage(String image) {}

    public Boolean getIsMain() {
        return isMain;
    }
    public void setIsMain(Boolean isMain) {}

    public Integer getPosition() {
        return position;
    }
    public void setPosition(Integer position) {}

    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {}

    public Instant getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Instant updatedAt) {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDto entity = (ImageDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.product, entity.product) &&
                Objects.equals(this.image, entity.image) &&
                Objects.equals(this.isMain, entity.isMain) &&
                Objects.equals(this.position, entity.position) &&
                Objects.equals(this.createdAt, entity.createdAt) &&
                Objects.equals(this.updatedAt, entity.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, image, isMain, position, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "product = " + product + ", " +
                "image = " + image + ", " +
                "isMain = " + isMain + ", " +
                "position = " + position + ", " +
                "createdAt = " + createdAt + ", " +
                "updatedAt = " + updatedAt + ")";
    }
}