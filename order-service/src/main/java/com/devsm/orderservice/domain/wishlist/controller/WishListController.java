package com.devsm.orderservice.domain.wishlist.controller;

import com.devsm.orderservice.domain.wishlist.dto.request.AddWishListRequestDto;
import com.devsm.orderservice.domain.wishlist.entity.Wishlist;
import com.devsm.commonserver.global.response.ResponseDto;
import com.devsm.commonserver.global.security.UserDetailsImpl;
import com.devsm.orderservice.domain.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishListController {
    private final WishListService wishListService;

    @GetMapping("health-check")
    public String healthCheck() {
        return "wishlist health-check";
    }

    // 내 장바구니 조회
    @GetMapping("")
    public ResponseEntity<ResponseDto<Page<Wishlist>>> getWishList(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                   @RequestParam Pageable pageable) {
        String uuid = userDetails.getUsername();
        ResponseDto<Page<Wishlist>> response = wishListService.getWishList(uuid, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    // 장바구니에 추가
    @PostMapping("")
    public ResponseEntity<ResponseDto<Long>> addWishList(@AuthenticationPrincipal UserDetailsImpl userDetails, AddWishListRequestDto dto){
        String uuid = userDetails.getUsername();
        ResponseDto<Long> response = wishListService.addWishList(uuid, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
