package com.devsm.orderservice.domain.wishlist.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddWishListRequestDto {
    private Long productId;
    private Long quantity;
}
