package com.devsm.orderservice.domain.order.controller;

import com.devsm.orderservice.domain.order.dto.request.CreateOrderRequestDto;
import com.devsm.orderservice.domain.order.service.OrderService;
import com.devsm.commonserver.global.response.ResponseDto;
import com.devsm.commonserver.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("health-check")
    public String healthCheck() {
        return "order health check";
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto<Long>> create(@RequestBody CreateOrderRequestDto dto) {
        ResponseDto<Long> response = orderService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("")
    public void  check(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(userDetails.getUsername());
    }
}
