package com.turkcell.pair3.productservice.repositories;

import com.turkcell.pair3.productservice.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer>{


}
