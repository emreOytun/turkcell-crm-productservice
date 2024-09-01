package com.turkcell.pair3.productservice.services.mappers;

import com.turkcell.pair3.productservice.entities.ProductSpecDetails;
import com.turkcell.pair3.productservice.services.dto.responses.AddProductSpecificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductSpecDetailsMapper {
    ProductSpecDetailsMapper INSTANCE = Mappers.getMapper(ProductSpecDetailsMapper.class);

    @Mapping(source = "specification.specName", target = "specName")
    AddProductSpecificationResponse addProductSpecificationResponse(ProductSpecDetails productSpecDetails);
}
