package com.ai.baas.product.spec;

import com.ai.baas.basetype.TimePeriod;

/**
 * A migration, substitution, dependency, or exclusivity relationship between/among ProductSpecifications.
 */
public class ProductSpecificationRelationship {

    ProductSpecification targetProdSpec;
    ProductSpecification sourceSpec;
    /**
     * A categorization of the relationship, such as migration, substitution, dependency, exclusivity.
     */
    private String type;
    /**
     * The period for which the relationship is applicable.
     */
    private TimePeriod validFor;

    /**
     * 
     * @param sourceSpec
     * @param targetSpec
     * @param type
     * @param validFor
     */
    public ProductSpecificationRelationship(ProductSpecification sourceSpec, ProductSpecification targetSpec, String type, TimePeriod validFor) {
        this.sourceSpec = sourceSpec;
        this.targetProdSpec = targetSpec;
    	this.type = type;
    	this.validFor = validFor;
    }


    public ProductSpecification getTargetProdSpec() {
		return targetProdSpec;
	}

	public void setTargetProdSpec(ProductSpecification targetProdSpec) {
		this.targetProdSpec = targetProdSpec;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TimePeriod getValidFor() {
		return validFor;
	}

	public void setValidFor(TimePeriod validFor) {
		this.validFor = validFor;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((targetProdSpec == null) ? 0 : targetProdSpec.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ProductSpecificationRelationship other = (ProductSpecificationRelationship) obj;
		if (targetProdSpec == null) {
			if (other.targetProdSpec != null){
				return false;
			}
		} else if (!targetProdSpec.equals(other.targetProdSpec)){
			return false;
		}
		if (type == null) {
			if (other.type != null){
				return false;
			}
		} else if (!type.equals(other.type)){
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