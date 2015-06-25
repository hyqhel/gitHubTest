package com.ai.baas.product.offering;

import com.ai.baas.basetype.*;
import com.ai.baas.product.spec.*;

/**
 * A type of ProductOffering that does not have any subordinate ProductOfferings, that is, an SimpleProductOffering is a leaf-level ProductOffering.
 */
public class SimpleProductOffering extends ProductOffering {

    /**
     * 
     * @param id
     * @param name
     * @param description
     * @param validFor
     * @param prodSpec
     */
    public SimpleProductOffering(String id, String name, String description, TimePeriod validFor, ProductSpecification prodSpec) {
        // TODO - implement SimpleProductOffering.SimpleProductOffering
    	super(id, name, description, validFor);
        throw new UnsupportedOperationException();
    }

}