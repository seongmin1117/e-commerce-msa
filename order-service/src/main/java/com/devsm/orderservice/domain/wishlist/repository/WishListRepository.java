package com.devsm.orderservice.domain.wishlist.repository;

import com.devsm.orderservice.domain.wishlist.entity.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<Wishlist,Long> {

    Page<Wishlist> findAllByUuid(String uuid, Pageable pageable);

    Wishlist findByUuidAndProductId(String uuid, Long productId);

    void deleteByUuidAndProductId(String uuid, Long productId);
}
