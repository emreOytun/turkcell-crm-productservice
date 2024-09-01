package com.turkcell.pair3.productservice.services.concretes;

import com.turkcell.pair3.core.exception.types.BusinessExceptionFactory;
import com.turkcell.pair3.core.messages.Messages;
import com.turkcell.pair3.core.services.abstracts.MessageService;
import com.turkcell.pair3.productservice.entities.Product;
import com.turkcell.pair3.productservice.entities.ProductSpecDetails;
import com.turkcell.pair3.productservice.entities.Specification;
import com.turkcell.pair3.productservice.entities.factories.ProductSpecDetailsFactory;
import com.turkcell.pair3.productservice.entities.factories.SpecificationFactory;
import com.turkcell.pair3.productservice.repositories.ProductSpecDetailsRepository;
import com.turkcell.pair3.productservice.repositories.SpecificationRepository;
import com.turkcell.pair3.productservice.services.abstracts.ProductService;
import com.turkcell.pair3.productservice.services.abstracts.SpecificationService;
import com.turkcell.pair3.productservice.services.dto.requests.AddProductSpecificationRequest;
import com.turkcell.pair3.productservice.services.dto.requests.AddSpecificationRequest;
import com.turkcell.pair3.productservice.services.dto.requests.AssignProductSpecificationRequest;
import com.turkcell.pair3.productservice.services.dto.responses.AddProductSpecificationResponse;
import com.turkcell.pair3.productservice.services.dto.responses.AddSpecificationResponse;
import com.turkcell.pair3.productservice.services.mappers.ProductSpecDetailsMapper;
import com.turkcell.pair3.productservice.services.mappers.SpecificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecificationServiceImpl implements SpecificationService {
    private final SpecificationRepository specificationRepository;
    private final ProductSpecDetailsRepository productSpecDetailsRepository;
    private final MessageService messageService;
    private final ProductService productService;

    @Override
    public AddSpecificationResponse createSpecification(AddSpecificationRequest request) {
        Specification specification = SpecificationFactory.create(request.getSpecName());
        specificationRepository.save(specification);
        return SpecificationMapper.INSTANCE.addSpecificationResponse(specification);
    }

    @Override
    public AddProductSpecificationResponse createProductSpec(AddProductSpecificationRequest request) {
        Specification specification = searchSpecificationByIdOrThrowExceptionIfNotFound(request.getSpecificationId());
        ProductSpecDetails productSpecDetails = ProductSpecDetailsFactory.create(specification, request.getValue());
        productSpecDetailsRepository.save(productSpecDetails);
        return ProductSpecDetailsMapper.INSTANCE.addProductSpecificationResponse(productSpecDetails);
    }

    private Specification searchSpecificationByIdOrThrowExceptionIfNotFound(Integer specificationId) {
        return specificationRepository.findById(specificationId).orElseThrow(
                () -> BusinessExceptionFactory.createWithMessage(messageService.getMessage(Messages.BusinessErrors.SPECIFICATION_NOT_FOUND)));
    }

    @Override
    public Integer assignProductSpec(AssignProductSpecificationRequest request) {
        Specification specification = searchSpecificationByIdOrThrowExceptionIfNotFound(request.getSpecificationId());
        Product product = productService.searchProductByIdOrThrowExceptionIfNotFound(request.getProductId());
        product.getSpecifications().add(specification);
        return productService.save(product).getId();
    }
}
