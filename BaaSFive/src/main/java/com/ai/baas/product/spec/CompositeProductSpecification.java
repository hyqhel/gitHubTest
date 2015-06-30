package com.ai.baas.product.spec;

import java.util.*;
import com.ai.baas.basetype.*;

/**
 * A type of ProductSpecification that is formed by aggregating other ProductSpecifications, which may be Composite or Atomic ProductSpecifications.
 */
public class CompositeProductSpecification extends ProductSpecification {

    public List<ProductSpecification> prodSpec;

    /**
     * 
     * @param productNumber
     * @param name
     * @param brand
     */
    public CompositeProductSpecification(String productNumber, String name, String brand) {
        // TODO - implement CompositeProductSpecification.CompositeProductSpecification
    	super(name, productNumber, brand);
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param productNumber
     * @param name
     * @param brand
     * @param description
     * @param validFor
     */
    public CompositeProductSpecification(String productNumber, String name, String brand, String description, TimePeriod validFor) {
        // TODO - implement CompositeProductSpecification.CompositeProductSpecification
    	super(name, productNumber, brand, validFor, description);
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param prodSpec
     */
    public void addSubProdSpec(ProductSpecification prodSpec) {
        // TODO - implement CompositeProductSpecification.addSubProdSpec
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param prodSpecId
     */
    public void addSubProdSpec(String prodSpecId) {
        // TODO - implement CompositeProductSpecification.addSubProdSpec
        throw new UnsupportedOperationException();
    }

	/*@Override
	public void addCharacteristic(ProductSpecCharacteristic specChar,
			boolean canBeOveridden, boolean isPackage, TimePeriod validFor,
			String name, String unique, int minCardinality, int maxCardinality,
			boolean extensible, String description) {
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public void addCharacteristic(String specCharId, boolean canBeOveridden,
			boolean isPackage, TimePeriod validFor, String name, String unique,
			int minCardinality, int maxCardinality, boolean extensible,
			String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCharacteristic(String specCharId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detachCharacteristicValue(String specCharId, String charValueId) {
		// TODO Auto-generated method stub
		
	}

}