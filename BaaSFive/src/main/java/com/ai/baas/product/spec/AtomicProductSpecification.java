package com.ai.baas.product.spec;

import com.ai.baas.basetype.*;

/**
 * A type of ProductSpecification that does not have any subordinate ProductSpecifications, that is, an AtomicProductSpecification is a leaf-level ProductSpecification.
 */
public class AtomicProductSpecification extends ProductSpecification {

    /**
     * 
     * @param productNumber
     * @param name
     * @param brand
     * @param description
     * @param validFor
     */
    public AtomicProductSpecification(String productNumber, String name, String brand, String description, TimePeriod validFor) {
        // TODO - implement AtomicProductSpecification.AtomicProductSpecification
    	super(name, productNumber, brand, validFor, description);
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param productNumber
     * @param name
     * @param brand
     */
    public AtomicProductSpecification(String productNumber, String name, String brand) {
        // TODO - implement AtomicProductSpecification.AtomicProductSpecification
    	super(name, productNumber, brand);
        throw new UnsupportedOperationException();
    }

	@Override
	public void addCharacteristic(ProductSpecCharacteristic specChar,
			boolean canBeOveridden, boolean isPackage, TimePeriod validFor,
			String name, String unique, int minCardinality, int maxCardinality,
			boolean extensible, String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCharacteristic(String specCharId, boolean canBeOveridden,
			boolean isPackage, TimePeriod validFor, String name, String unique,
			int minCardinality, int maxCardinality, boolean extensible,
			String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCharacteristic(ProductSpecCharacteristic specChar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCharacteristic(String specCharId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detachCharacteristicValue(ProductSpecCharacteristic specChar,
			ProductSpecCharacteristicValue charValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detachCharacteristicValue(String specCharId, String charValueId) {
		// TODO Auto-generated method stub
		
	}

}