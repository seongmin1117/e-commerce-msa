package com.devsm.orderservice.domain.order.service;

import com.devsm.orderservice.domain.order.entity.Order;
import com.devsm.orderservice.domain.order.entity.OrderStatus;
import com.devsm.orderservice.domain.order.dto.request.CreateOrderRequestDto;
import com.devsm.orderservice.domain.order.repository.OrderRepository;
import com.devsm.commonserver.global.response.ResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;



@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    /*
     주문시 확인할 것
     1. 유저가 존재하는지
     2. 상품이 존재하는지
     3. 재고가 있는지

     */

    // 주문 생성
    public ResponseDto<Long> create(CreateOrderRequestDto dto) {
        // 존재하는 유저인지 feign
        String uuid = dto.getUuid();

        // 존재하는 주문의상품인지 feign , 재고가 있는지 feign
        Long orderProductId = dto.getOrderProductId();


        Order save = orderRepository.save(Order.builder()
                .uuid(dto.getUuid())
                .orderProductId(orderProductId)
                .orderPrice(dto.getOrderPrice())
                .receiver(dto.getReceiver())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .status(OrderStatus.ORDER_COMPLETED)
                .build());

        return ResponseDto.success(save.getId());
    }

    // 주문 조회
    public ResponseDto<OrderStatus> getOrder(Long id){
        // 존재하는 주문인지 확인
        Order order = orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("주문이 존재하지 않습니다."));
        // 주문 상태 확인, 현재 시간과 주문 생성 시간 비교 후 상태 설정
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime created = order.getCreatedAt();

        long hoursDiff = ChronoUnit.HOURS.between(now, created);

        if(hoursDiff>=24 && hoursDiff<48){
            order.updateStatus(OrderStatus.DELIVERY_PROCESSING);
        }
        if (hoursDiff>=48){
            order.updateStatus(OrderStatus.DELIVERY_COMPLETED);
        }
        OrderStatus status = order.getStatus();
        return ResponseDto.success(status);
    }

    // 주문 취소 (상태 변경)
    public void cancel(Long id){
        // 존재하는 주문인지 확인
        Order order = orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("주문이 존재하지 않습니다."));
        // 주문 상태 확인, 현재 시간과 주문 생성 시간 비교 후 상태 설정
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime created = order.getCreatedAt();

        long hoursDiff = ChronoUnit.HOURS.between(now, created);


        //주문 상태 확인,
    }

}
