package com.devsm.orderservice.domain.order.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CreateOrderRequestDto {
    @NotBlank
    private String uuid;
    @NotBlank
    private Long orderProductId;
    @NotBlank
    private Long orderPrice;
    @NotBlank
    private String receiver;
    @NotBlank
    private String address;
    private String phoneNumber;
}
