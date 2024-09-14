package com.turkcell.pair3.productservice.entities.factories;

import com.turkcell.pair3.productservice.entities.Cart;

public class CartFactory {
    private CartFactory() {

    }

    public static Cart create(Integer customerId) {
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        return cart;
    }
}
