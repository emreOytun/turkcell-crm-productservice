package com.turkcell.pair3.productservice.services.concretes;

import com.turkcell.pair3.core.events.CartProductEvent;
import com.turkcell.pair3.productservice.entities.CartProducts;
import com.turkcell.pair3.productservice.entities.factories.CartFactory;
import com.turkcell.pair3.productservice.repositories.CartProductRepository;
import com.turkcell.pair3.productservice.repositories.CartRepository;
import com.turkcell.pair3.productservice.services.abstracts.CartService;
import com.turkcell.pair3.productservice.services.dto.requests.AddProductToCartRequest;
import com.turkcell.pair3.productservice.services.mappers.CartProductsMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartProductRepository cartProductRepository;
    private final CartRepository cartRepository;

    @Override
    public void addProductToCart(AddProductToCartRequest addProductToCartRequest) {
        cartProductRepository.save(CartProductsMapper.INSTANCE.cartProductFromRequest(addProductToCartRequest));
    }

    @Override
    public Integer createCartforCustomer(Integer customerId) {
        return cartRepository.save(CartFactory.create(customerId)).getId();
    }

    @Override
    public List<Integer> getCartProducts(Integer cartId) {
        return cartProductRepository.findProductIdsByCartId(cartId).orElse(null);
    }

    @Override
    public List<CartProductEvent> getProductsByCartId(Integer cartId) {
        List<CartProducts> cartProductList = cartProductRepository.findAllByCartId(cartId).orElse(null);
        if (CollectionUtils.isEmpty(cartProductList)) return new ArrayList<>();
        return cartProductList.stream().map(CartProductsMapper.INSTANCE::cartProductEvent).toList();
    }
}
