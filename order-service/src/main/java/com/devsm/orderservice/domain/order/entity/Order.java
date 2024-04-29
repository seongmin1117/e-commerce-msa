package com.devsm.orderservice.domain.order.entity;

import com.devsm.commonserver.global.BaseEntity;
import com.devsm.commonserver.global.converter.CryptoConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;
    private Long orderProductId;
    private Long orderPrice;

    @Enumerated(EnumType.STRING)
    private com.devsm.orderservice.domain.order.entity.OrderStatus status;

    @Convert(converter = CryptoConverter.class)
    private String receiver;
    @Convert(converter = CryptoConverter.class)
    private String address;
    @Convert(converter = CryptoConverter.class)
    private String phoneNumber;

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

}
