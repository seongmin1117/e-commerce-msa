package com.devsm.orderservice.domain.wishlist.entity;

import com.devsm.commonserver.global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Wishlist extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;
    private Long productId;
    private Long quantity;

    public void increaseQuantity(Long quantity) {
        this.quantity += quantity;
    }
    public void decreaseQuantity(Long quantity) {
        this.quantity -= quantity;
    }
}
