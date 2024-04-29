package com.devsm.productservice.domain.product.service;

import com.devsm.productservice.domain.product.entity.Product;
import com.devsm.productservice.domain.product.dto.request.CreateProductRequestDto;
import com.devsm.productservice.domain.product.dto.response.GetProductListResponse;
import com.devsm.productservice.domain.product.dto.response.GetProductResponse;
import com.devsm.productservice.domain.product.repository.ProductRepository;
import com.devsm.commonserver.global.response.ResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    // 상품 등록
    public ResponseDto<Long> createProduct(CreateProductRequestDto dto) {
        Product save = productRepository.save(Product.builder()
                .productName(dto.getProductName())
                .price(dto.getPrice())
                .content(dto.getContent())
                .stock(dto.getStock())
                .build());

        return ResponseDto.success(save.getId());
    }

    // 특정 상품 조회
    public ResponseDto<com.devsm.productservice.domain.product.dto.response.GetProductResponse> getProduct(Long id){
        Product product = productRepository.findById(id)
                    .orElseThrow(()-> new EntityNotFoundException("해당 상품을 찾을 수 없습니다."));

        return ResponseDto.success(new GetProductResponse(product));
    }
    // 상품 리스트 조회
    public ResponseDto<GetProductListResponse> getProductList(Pageable pageable){
        Page<Product> productlist = productRepository.findAll(pageable);
        return ResponseDto.success(new GetProductListResponse(productlist));
    }


}
