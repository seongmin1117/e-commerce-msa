package com.devsm.productservice.domain.product.dto.response;

import com.devsm.productservice.domain.product.entity.Product;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class GetProductListResponse{
    private final Page<Product> productList;

    public GetProductListResponse(Page<Product> productList) {
        this.productList = productList;
    }

}
