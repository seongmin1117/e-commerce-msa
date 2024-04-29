package com.devsm.productservice.domain.product.controller;

import com.devsm.productservice.domain.product.dto.request.CreateProductRequestDto;
import com.devsm.productservice.domain.product.dto.response.GetProductListResponse;
import com.devsm.productservice.domain.product.dto.response.GetProductResponse;
import com.devsm.productservice.domain.product.service.ProductService;
import com.devsm.commonserver.global.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("health-check")
    public String healthCheck() {
        return "product-health-check";
    }
    @PostMapping("")
    public ResponseEntity<ResponseDto<Long>> createProduct(@RequestBody CreateProductRequestDto dto) {
        ResponseDto<Long> response = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto<GetProductResponse>> getProduct(@PathVariable("id") Long id) {
        ResponseDto<GetProductResponse> response = productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto<GetProductListResponse>> getProductList(@RequestParam Pageable pageable){
        ResponseDto<GetProductListResponse> response = productService.getProductList(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
