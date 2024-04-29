package com.devsm.orderservice.domain.wishlist.service;

import com.devsm.orderservice.domain.wishlist.dto.request.AddWishListRequestDto;
import com.devsm.orderservice.domain.wishlist.entity.Wishlist;
import com.devsm.orderservice.domain.wishlist.repository.WishListRepository;
import com.devsm.commonserver.global.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WishListService {
    private final WishListRepository wishListRepository;

    // uuid로 장바구니에 담긴 상품들 조회
    public ResponseDto<Page<Wishlist>> getWishList(String uuid, Pageable pageable) {
        Page<Wishlist> wishlists = wishListRepository.findAllByUuid(uuid, pageable);
        return ResponseDto.success(wishlists);
    }

    // 장바구니에 상품 추가
    public ResponseDto<Long> addWishList(String uuid, AddWishListRequestDto dto) {

        Long productId = dto.getProductId();
        Wishlist wishlist = wishListRepository.findByUuidAndProductId(uuid, productId);
        // 이미 존재하면 수량 ++
        if (wishlist != null) {
            wishlist.increaseQuantity(dto.getQuantity());
        }
        // 없으면 새로 생성
        if (wishlist == null) {
            wishlist = wishListRepository.save(Wishlist.builder()
                    .uuid(uuid)
                    .productId(productId)
                    .quantity(dto.getQuantity())
                    .build());
        }
        return ResponseDto.success(wishlist.getId());
    }
    // 장바구니 상품 수량 증가 (한번 누를 때)
    public ResponseDto<Long> increaseQuantity(String uuid, Long productId) {
        Wishlist wishlist = wishListRepository.findByUuidAndProductId(uuid, productId);
        wishlist.increaseQuantity(1L);
        return ResponseDto.success(wishlist.getId());
    }

    // 장바구니 상품 수량 감소 (한번 누를 때)
    public ResponseDto<Long> decreaseQuantity(String uuid, Long productId) {
        Wishlist wishlist = wishListRepository.findByUuidAndProductId(uuid, productId);
        if (wishlist.getQuantity()>0){
            wishlist.decreaseQuantity(1L);
        }
        return ResponseDto.success(wishlist.getId());
    }

    // 장바구니 상품리스트 삭제
    public ResponseDto<?> deletedWishlist(String uuid, List<Long> productIdList) {
        for (Long productId : productIdList) {
            wishListRepository.deleteByUuidAndProductId(uuid, productId);
        }
        return ResponseDto.success();
    }

    // 장바구니 상품 주문


}
