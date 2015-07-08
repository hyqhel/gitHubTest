package com.ai.baas.product.spec;

import com.ai.baas.basetype.*;
import com.ai.baas.common.enums.ProdSpecEnum;
import org.apache.log4j.Logger;

/**
 * A aggregation, migration, substitution, dependency, or exclusivity relationship between/among ProductSpecCharacteristics.
 */
public class ProductSpecCharRelationship {
	private static final Logger logger = Logger.getLogger(ProductSpecCharRelationship.class);
    private ProductSpecCharacteristic targetProdSpecChar;
    private ProductSpecCharacteristic sourceProdSpecChar;
	public ProductSpecCharacteristic getTargetProdSpecChar() {
		return targetProdSpecChar;
	}

	public void setTargetProdSpecChar(ProductSpecCharacteristic targetProdSpecChar) {
		this.targetProdSpecChar = targetProdSpecChar;
	}

	public ProductSpecCharacteristic getSourceProdSpecChar() {
		return sourceProdSpecChar;
	}

	public void setSourceProdSpecChar(ProductSpecCharacteristic sourceProdSpecChar) {
		this.sourceProdSpecChar = sourceProdSpecChar;
	}

	/**
     * A categorization of the relationship, such as aggregation, migration, substitution, dependency, exclusivity.
     */
    private String charRelationshipType;
    /**
     * The order in which a CharacteristicSpecification appears within another CharacteristicSpecification that defines a grouping of CharacteristicSpecifications.
     * 
     * For example, a grouping may represent the name of an individual. The given name is first, the middle name is second, and the last name is third.
     */
    private int charSpecSeq;
    /**
     * The period for which the relationship is applicable.
     */
    private TimePeriod validFor;

    public String getCharRelationshipType() {
        return this.charRelationshipType;
    }

    public void setCharRelationshipType(String charRelationshipType) {
        this.charRelationshipType = charRelationshipType;
    }

    public int getCharSpecSeq() {
        return this.charSpecSeq;
    }

    public void setCharSpecSeq(int charSpecSeq) {
        this.charSpecSeq = charSpecSeq;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param srourceSpecChar
     * @param targetSpecChar
     * @param relationType
     * @param validFor
     */
    public ProductSpecCharRelationship(ProductSpecCharacteristic srourceSpecChar, ProductSpecCharacteristic targetSpecChar, String relationType, TimePeriod validFor) {

		if (null == srourceSpecChar) {
			logger.error("srcProdSpecChar should not be null");
			throw new IllegalArgumentException("srcProdSpecChar should not be null");
		}
		if (null == targetSpecChar) {
			logger.error("targetProdSpecChar should not be null");
			throw new IllegalArgumentException("targetProdSpecChar should not be null");
		}
		this.sourceProdSpecChar = srourceSpecChar;
    	this.targetProdSpecChar = targetSpecChar;
    	this.charRelationshipType = relationType;
    	this.validFor = validFor;
    }


    /**
     * 
     * @param srourceSpecChar
     * @param targetSpecChar
     * @param relationType
     * @param validFor
     * @param specSeq
     */
    public ProductSpecCharRelationship(ProductSpecCharacteristic srourceSpecChar,ProductSpecCharacteristic targetSpecChar, String relationType, TimePeriod validFor, int specSeq) {
		if (null == srourceSpecChar) {
			logger.error("srcProdSpecChar should not be null");
			throw new IllegalArgumentException("srcProdSpecChar should not be null");
		}
		if (null == targetSpecChar) {
			logger.error("targetProdSpecChar should not be null");
			throw new IllegalArgumentException("targetProdSpecChar should not be null");
		}
		this.sourceProdSpecChar = srourceSpecChar;
		this.targetProdSpecChar = targetSpecChar;
		this.charRelationshipType = relationType;
		this.validFor = validFor;
		this.charSpecSeq = specSeq;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((charRelationshipType == null) ? 0 : charRelationshipType
						.hashCode());
		result = prime
		* result
		+ ((targetProdSpecChar == null) ? 0 : targetProdSpecChar
				.hashCode());
		result = prime * result
				+ ((validFor == null) ? 0 : validFor.hashCode());
		result = prime * result + charSpecSeq;
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
		if (!(obj instanceof ProductSpecCharRelationship)){
			return false;
		}
		ProductSpecCharRelationship other = (ProductSpecCharRelationship) obj;
		if (charRelationshipType == null) {
			if (other.charRelationshipType != null){
				return false;
			}
		} else if (!charRelationshipType.equals(other.charRelationshipType)){
			return false;
		}
		if (targetProdSpecChar == null) {
			if (other.targetProdSpecChar != null){
				return false;
			}
		} else if (!targetProdSpecChar.equals(other.targetProdSpecChar)){
			return false;
		}
		if (charSpecSeq != other.charSpecSeq){
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

    
	@Override
	public String toString() {
		String value="";
	       for (ProdSpecEnum.ProdSpecRelationship aLight : ProdSpecEnum.ProdSpecRelationship.values ()) {
	    	   if(aLight.getValue().equals(charRelationshipType)){
	    		   value = aLight.getName(); 
	    	   }
	       }
		return "关系类型："
				+ value+ "\n"
				+"   "+targetProdSpecChar.toString()+ "   ";
	}

}