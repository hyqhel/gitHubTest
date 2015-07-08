package com.ai.baas.product.spec;

import java.util.*;

import com.ai.baas.basetype.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * A number or text that can be assigned to a ProductSpecCharacteristic.
 */
public class ProductSpecCharacteristicValue {
	private static final Logger logger = Logger.getLogger(ProductSpecCharacteristicValue.class);
    private List<ProdSpecCharValueRelationship> prodSpecCharValueRelationship;
    private String id;
    
    /**
     * A kind of value that the characteristic can take on, such as numeric, text, and so forth.
     */
    private String valueType;
    /**
     * Indicates if the value is the default value for a characteristic.
     */
    private boolean isDefault;
    /**
     * A discrete value that the characteristic can take on.
     */
    private String value;
    /**
     * A length, surface, volume, dry measure, liquid measure, money, weight, time, and the like. Iin general, a determinate quantity or magnitude of the kind designated, taken as a standard of comparison for others of the same kind, in assigning to them numerical values, as 1 foot, 1 yard, 1 mile, 1 square foot.
     */
    private String unitOfMeasure;
    /**
     * The low range value that a characteristic can take on.
     */
    private String valueFrom;
    /**
     * The upper range value that a characteristic can take on.
     */
    private String valueTo;
    /**
     * An indicator that specifies the inclusion or exclusion of the valueFrom and valueTo attributes.
     * 
     * Possible values are "open", "closed", "closedBottom" and "closedTop".
     */
    private String rangeInterval;
    /**
     * The period of time for which a value is applicable.
     */
    private TimePeriod validFor;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValueType() {
        return this.valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public boolean isIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnitOfMeasure() {
        return this.unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getValueFrom() {
        return this.valueFrom;
    }

    public void setValueFrom(String valueFrom) {
        this.valueFrom = valueFrom;
    }

    public String getValueTo() {
        return this.valueTo;
    }

    public void setValueTo(String valueTo) {
        this.valueTo = valueTo;
    }

    public String getRangeInterval() {
        return this.rangeInterval;
    }

    public void setRangeInterval(String rangeInterval) {
        this.rangeInterval = rangeInterval;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param valueType
     * @param unitOfMeasure
     * @param validFor
     * @param value
     * @param isDefault
     */
    public ProductSpecCharacteristicValue(String valueType, String unitOfMeasure, TimePeriod validFor, String value, boolean isDefault) {
		if ( StringUtils.isEmpty(valueType) ) {
			logger.error("valueType should not be null");
			throw new IllegalArgumentException("valueType should not be null");
		}
		if(StringUtils.isEmpty(value)){
			logger.error("value should not be null");
			throw new IllegalArgumentException("value should not be null");
		}
        this.valueType = valueType;
        this.unitOfMeasure = unitOfMeasure;
        this.validFor = validFor;
        this.isDefault = isDefault;
        this.value = value;
        
    }

    /**
     * 
     * @param valueType
     * @param unitOfMeasure
     * @param validFor
     * @param valueFrom
     * @param valueTo
     * @param rangeInterval
     */
    public ProductSpecCharacteristicValue(String valueType, String unitOfMeasure, TimePeriod validFor, String valueFrom, String valueTo, String rangeInterval) {
		if(null == validFor){
			logger.error("validFor should not be null");
			throw new IllegalArgumentException("validFor should not be null");
		}
		if(StringUtils.isEmpty(valueType)){
			logger.error("valueType should not be null");
			throw new IllegalArgumentException("valueType should not be null");
		}

		if (StringUtils.isEmpty(valueFrom) && StringUtils.isEmpty(valueTo)) {
			logger.error("valueFrom and valueTo should not be null at the same time.");
			throw new IllegalArgumentException("valueFrom and valueTo should not be null at the same time.");

		}else if(StringUtils.isEmpty(valueFrom)){
			logger.error("valueFrom should not be null .");
			throw new IllegalArgumentException("valueFrom should not be null .");

		}else if(StringUtils.isEmpty(valueTo)){

			valueTo=valueFrom;
		}
		this.valueType = valueType;
        this.unitOfMeasure = unitOfMeasure;
        this.validFor = validFor;
        this.valueFrom = valueFrom;
        this.valueTo = valueTo;
        this.rangeInterval=rangeInterval;
    }

    /**
     * 
     * @param unitOfMeasure
     * @param value
     */
    public void specifyValue(String unitOfMeasure, String value) {
    	this.unitOfMeasure = unitOfMeasure;
    	this.value = value;
    }

    /**
     * 
     * @param unitOfMeasure
     * @param valueFrom
     * @param valueTo
     * @param rangeInterval
     */
    public void specifyValue(String unitOfMeasure, String valueFrom, String valueTo, String rangeInterval) {
    	this.unitOfMeasure = unitOfMeasure;
    	this.valueFrom = valueFrom;
    	this.valueTo = valueTo;
    	this.rangeInterval = rangeInterval;
    }

    /**
     * 
     * @param charValue
     * @param relationType
     * @param validFor
     */
    public void addRelatedCharValue(ProductSpecCharacteristicValue charValue, String relationType, TimePeriod validFor) {
    	ProdSpecCharValueRelationship pship = new ProdSpecCharValueRelationship(this,charValue,relationType,validFor);    	
    	if(null == prodSpecCharValueRelationship){
    		prodSpecCharValueRelationship = new ArrayList<ProdSpecCharValueRelationship>();
    	}
    	for(ProdSpecCharValueRelationship psvr :prodSpecCharValueRelationship){
    		if(psvr.equals(charValue)){
    			return;
    		}
    	}
    	prodSpecCharValueRelationship.add(pship);
    }


    /**
     * 
     * @param charValue
     */
    public void removeRelatedCharValue(ProductSpecCharacteristicValue charValue) {
    	if(null == this.prodSpecCharValueRelationship){
    		return;
    	}
    	for(ProdSpecCharValueRelationship psvr :prodSpecCharValueRelationship){
    		if(psvr.equals(charValue)){
    			prodSpecCharValueRelationship.remove(psvr);
    		}
    	}
    }


    /**
     * 
     * @param relationType
     */
    public Set<ProductSpecCharacteristicValue> retieveRelatedCharValue(String relationType) {
        // TODO 
    	return null;
    }

    /**
     * 
     * @param relationType
     * @param time
     */
    public Set<ProductSpecCharacteristicValue> retieveRelatedCharValue(String relationType, Date time) {
        // TODO 
    	return null;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((rangeInterval == null) ? 0 : rangeInterval.hashCode());
		result = prime * result
				+ ((unitOfMeasure == null) ? 0 : unitOfMeasure.hashCode());
		result = prime * result
				+ ((validFor == null) ? 0 : validFor.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result
				+ ((valueFrom == null) ? 0 : valueFrom.hashCode());
		result = prime * result + ((valueTo == null) ? 0 : valueTo.hashCode());
		result = prime * result
				+ ((valueType == null) ? 0 : valueType.hashCode());
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
		ProductSpecCharacteristicValue other = (ProductSpecCharacteristicValue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (rangeInterval == null) {
			if (other.rangeInterval != null)
				return false;
		} else if (!rangeInterval.equals(other.rangeInterval))
			return false;
		if (unitOfMeasure == null) {
			if (other.unitOfMeasure != null)
				return false;
		} else if (!unitOfMeasure.equals(other.unitOfMeasure))
			return false;
		if (validFor == null) {
			if (other.validFor != null)
				return false;
		} else if (!validFor.equals(other.validFor))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (valueFrom == null) {
			if (other.valueFrom != null)
				return false;
		} else if (!valueFrom.equals(other.valueFrom))
			return false;
		if (valueTo == null) {
			if (other.valueTo != null)
				return false;
		} else if (!valueTo.equals(other.valueTo))
			return false;
		if (valueType == null) {
			if (other.valueType != null)
				return false;
		} else if (!valueType.equals(other.valueType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "id=" + id + ", valueType="
				+ valueType + ", isDefault=" + isDefault + ", value=" + value
				+ ", unitOfMeasure=" + unitOfMeasure + ", valueFrom="
				+ valueFrom + ", valueTo=" + valueTo + ", rangeInterval="
				+ rangeInterval + ", validFor=" + validFor;
	}

}