package com.ai.baas.product.spec;

import com.ai.baas.basetype.*;

/**
 * A use of the ProdSpecCharacteristicValue by an ProductSpecification to which additional properties (attributes) apply or override the properties of similar properties contained in ProdSpecCharacteristicValue.
 */
public class ProdSpecCharValueUse {

    public ProductSpecCharacteristicValue prodSpecCharValue;
    public ProductSpecCharacteristicValue getProdSpecCharValue() {
		return prodSpecCharValue;
	}

	/**
     * Indicates if the value is the default value for a characteristic.
     */
    private boolean isDefault;
    /**
     * The period of time for which the use of the CharacteristicSpecificationValue is applicable.
     */
    private TimePeriod validFor;

    public boolean isIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param charVal
     * @param isDefault
     * @param validFor
     */
    public ProdSpecCharValueUse(ProductSpecCharacteristicValue charVal, boolean isDefault, TimePeriod validFor) {
        this.prodSpecCharValue = charVal;
        this.isDefault = isDefault;
        this.validFor = validFor;
    }

    /**
     * 
     * @param charValId
     * @param isDefault
     * @param validFor
     */
    public ProdSpecCharValueUse(String charValId, boolean isDefault, TimePeriod validFor) {
    	this.isDefault =isDefault;
    	this.validFor =validFor;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((prodSpecCharValue == null) ? 0 : prodSpecCharValue
						.hashCode());
		result = prime * result
				+ ((validFor == null) ? 0 : validFor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdSpecCharValueUse other = (ProdSpecCharValueUse) obj;
		if (prodSpecCharValue == null) {
			if (other.prodSpecCharValue != null)
				return false;
		} else if (!prodSpecCharValue.equals(other.prodSpecCharValue))
			return false;
		if (validFor == null) {
			if (other.validFor != null)
				return false;
		} else if (!validFor.equals(other.validFor))
			return false;
		return true;
	}

    
}