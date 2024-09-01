package com.turkcell.pair3.productservice.services.mappers;

import com.turkcell.pair3.productservice.entities.Specification;
import com.turkcell.pair3.productservice.services.dto.responses.AddSpecificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpecificationMapper {
    SpecificationMapper INSTANCE = Mappers.getMapper(SpecificationMapper.class);

    @Mapping(source = "specName", target = "name")
    AddSpecificationResponse addSpecificationResponse(Specification specification);
}
