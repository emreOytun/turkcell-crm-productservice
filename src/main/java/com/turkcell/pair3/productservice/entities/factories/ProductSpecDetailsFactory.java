package com.turkcell.pair3.productservice.entities.factories;

import com.turkcell.pair3.productservice.entities.ProductSpecDetails;
import com.turkcell.pair3.productservice.entities.Specification;

public class ProductSpecDetailsFactory {
    private ProductSpecDetailsFactory() {

    }

    public static ProductSpecDetails create(Specification specification, String specValue) {
        ProductSpecDetails productSpecDetails = new ProductSpecDetails();
        productSpecDetails.setSpecification(specification);
        productSpecDetails.setSpecValue(specValue);
        return productSpecDetails;
    }
}
