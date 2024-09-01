package com.turkcell.pair3.productservice.repositories;

import com.turkcell.pair3.productservice.entities.CartProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CartProducts, Integer> {
    Optional<List<CartProducts>> findAllByCartId(Integer cartId);

    @Query("SELECT cp.productId FROM CartProducts cp WHERE cp.cartId = :cartId")
    Optional<List<Integer>> findProductIdsByCartId(@Param("cartId") Integer cartId);
}
