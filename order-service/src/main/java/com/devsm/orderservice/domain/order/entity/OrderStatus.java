package com.devsm.orderservice.domain.order.entity;

import lombok.Getter;


public enum OrderStatus {
    ORDER_PENDING("주문 중"),
    ORDER_COMPLETED("주문 완료"),
    DELIVERY_PROCESSING("배송 중"),
    DELIVERY_COMPLETED("배송 완료"),
    ORDER_CANCELLED("주문 취소");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }
}
