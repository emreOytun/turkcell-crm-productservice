package com.turkcell.pair3.productservice.services.abstracts;

import com.turkcell.pair3.productservice.entities.Product;
import com.turkcell.pair3.productservice.services.dto.requests.AddProductRequest;

public interface ProductService {

    double findProductPriceById(int id);
    void create(AddProductRequest addProductRequest);
    Product save(Product product);
    Product searchProductByIdOrThrowExceptionIfNotFound(int id);
}
