package com.devsm.orderservice.domain.order.dto.response;

import com.devsm.orderservice.domain.order.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetOrderResponse {
    private Long id;

    private String uuid;
    private Long orderProductId;
    private Long orderPrice;

    private OrderStatus status;

    private String receiver;
    private String address;
    private String phoneNumber;
}
