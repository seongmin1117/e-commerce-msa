package com.devsm.productservice.domain.product.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateProductRequestDto{

    private String productName;

    private Long price;

    private String content;

    private Long stock;

}
