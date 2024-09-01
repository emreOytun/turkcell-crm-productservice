package com.turkcell.pair3.productservice.services.concretes;

import com.turkcell.pair3.core.exception.types.BusinessExceptionFactory;
import com.turkcell.pair3.core.messages.Messages;
import com.turkcell.pair3.core.services.abstracts.MessageService;
import com.turkcell.pair3.productservice.entities.Category;
import com.turkcell.pair3.productservice.entities.Product;
import com.turkcell.pair3.productservice.repositories.ProductRepository;
import com.turkcell.pair3.productservice.services.abstracts.ProductService;
import com.turkcell.pair3.productservice.services.dto.requests.AddProductRequest;
import com.turkcell.pair3.productservice.services.mappers.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final MessageService messageService;
    private final CategoryServiceImpl categoryService;

    @Override
    public double findProductPriceById(int id) {
        return searchProductByIdOrThrowExceptionIfNotFound(id).getPrice();
    }

    @Override
    public void create(AddProductRequest addProductRequest) {
        Category category = categoryService.searchCategoryByIdOrThrowExceptionIfNotFound(addProductRequest.getCategoryId());
        Product product = ProductMapper.INSTANCE.productFromAddRequest(addProductRequest);
        product.setCategory(category);
        save(product);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product searchProductByIdOrThrowExceptionIfNotFound(int id) {
        return productRepository.findProductById(id)
                .orElseThrow(() -> BusinessExceptionFactory.createWithMessage(messageService.getMessage(Messages.BusinessErrors.NO_PRODUCT_FOUND_GIVEN_ID)));
    }
}
