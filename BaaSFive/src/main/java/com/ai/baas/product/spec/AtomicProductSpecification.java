package com.ai.baas.product.spec;

import java.util.ArrayList;
import java.util.Map;

import com.ai.baas.basetype.*;
import com.ai.baas.common.util.CommonUtils;

/**
 * A type of ProductSpecification that does not have any subordinate ProductSpecifications, that is, an AtomicProductSpecification is a leaf-level ProductSpecification.
 */
public class AtomicProductSpecification extends ProductSpecification {

    /**
     * @param productNumber
     * @param name
     * @param brand
     * @param description
     * @param validFor
     */
    public AtomicProductSpecification(String productNumber, String name, String brand, String description, TimePeriod validFor) {
        super(productNumber, name, brand, validFor, description);
    }

    /**
     * @param productNumber
     * @param name
     * @param brand
     */
    public AtomicProductSpecification(String productNumber, String name, String brand) {
        super(productNumber, name, brand);
    }

    @Override
    public String toString() {
        Map<String, Object> result = this.getProductInfoToMap();
        return CommonUtils.format(result.toString());
    }
}