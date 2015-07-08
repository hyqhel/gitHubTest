package com.ai.baas.product.spec;

import com.ai.baas.basetype.*;

public class ProdSpecCharValueRelationship {

    private ProductSpecCharacteristicValue sourceCharValue;
    private ProductSpecCharacteristicValue productSpecCharacteristicValue;
    /**
     * A categorization of the relationship between values, such as aggregation, migration, substitution, dependency, exclusivity.
     */
    private String charValueRelationshipType;
    /**
     * The period for which the relationship is applicable.
     */
    private TimePeriod validFor;

    public String getCharValueRelationshipType() {
        return this.charValueRelationshipType;
    }

    public void setCharValueRelationshipType(String charValueRelationshipType) {
        this.charValueRelationshipType = charValueRelationshipType;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param srourceCharValue
     * @param targetCharValue
     * @param relationType
     * @param validFor
     */
    public ProdSpecCharValueRelationship(ProductSpecCharacteristicValue srourceCharValue, ProductSpecCharacteristicValue targetCharValue, String relationType, TimePeriod validFor) {
       this.sourceCharValue = sourceCharValue;
       this.productSpecCharacteristicValue = targetCharValue;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((charValueRelationshipType == null) ? 0
						: charValueRelationshipType.hashCode());
		result = prime
				* result
				+ ((productSpecCharacteristicValue == null) ? 0
						: productSpecCharacteristicValue.hashCode());
		result = prime * result
				+ ((validFor == null) ? 0 : validFor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		ProdSpecCharValueRelationship other = (ProdSpecCharValueRelationship) obj;
		if (charValueRelationshipType == null) {
			if (other.charValueRelationshipType != null){
				return false;
			}
		} else if (!charValueRelationshipType
				.equals(other.charValueRelationshipType)){
			return false;
		}
		if (productSpecCharacteristicValue == null) {
			if (other.productSpecCharacteristicValue != null){
				return false;
			}
		} else if (!productSpecCharacteristicValue
				.equals(other.productSpecCharacteristicValue)){
			return false;
		}
		if (validFor == null) {
			if (other.validFor != null){
				return false;
			}
		} else if (!validFor.equals(other.validFor)){
			return false;
		}
		return true;
	}
    
}