package com.devsm.productservice.domain.product.dto.response;

import com.devsm.productservice.domain.product.entity.Product;
import lombok.Getter;


@Getter
public class GetProductResponse{

    private final Long id;

    private final String productName;

    private final Long price;

    private final String content;

    public GetProductResponse(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.content = product.getContent();
    }

}
