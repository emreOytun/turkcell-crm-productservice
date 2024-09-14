package com.turkcell.pair3.productservice.entities.factories;

import com.turkcell.pair3.productservice.entities.Specification;

public class SpecificationFactory {
    private SpecificationFactory() {

    }

    public static Specification create(String specName) {
        Specification specification = new Specification();
        specification.setSpecName(specName);
        return specification;
    }
}
