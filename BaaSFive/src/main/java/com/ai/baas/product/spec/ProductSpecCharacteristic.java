package com.ai.baas.product.spec;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ai.baas.basetype.TimePeriod;

/**
 * A characteristic quality or distinctive feature of a ProductSpecification. The characteristic can be take on a discrete value, such as color, can take on a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) = 30 - talk time *3). Certain characteristics, such as color, may be configured during the ordering or some other process.
 */
public class ProductSpecCharacteristic {
	private static final Logger logger = Logger.getLogger(ProductSpecCharacteristic.class);
    private  List<ProductSpecCharacteristicValue> prodSpecCharValue;
    private List<ProductSpecCharRelationship> prodSpecCharRelationship;
    public List<ProductSpecCharacteristicValue> getProdSpecCharValue() {
		return prodSpecCharValue;
	}

	public void setProdSpecCharValue(
			List<ProductSpecCharacteristicValue> prodSpecCharValue) {
		this.prodSpecCharValue = prodSpecCharValue;
	}

	public List<ProductSpecCharRelationship> getProdSpecCharRelationship() {
		return prodSpecCharRelationship;
	}

	public void setProdSpecCharRelationship(
			List<ProductSpecCharRelationship> prodSpecCharRelationship) {
		this.prodSpecCharRelationship = prodSpecCharRelationship;
	}

	/**
     * A unique identifier for the ProductSpecCharacteristic.
     * ?
     */
    private String ID;
    /**
     * A word, term, or phrase by which the characteristic is known and distinguished from characteristics.
     */
    private String name;
    /**
     * A narrative that explains the characteristic.
     */
    private String description;
    /**
     * An indicator that specifies if a value is unique for the specification.
     * 
     * Possible values are; "unique while value is in effect" and "unique whether value is in effect or not"
     */
    private String unique;
    /**
     * A kind of value that the characteristic can take on, such as numeric, text, and so forth.
     */
    private String valueType;
    /**
     * The minimum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where zero is the value for the minCardinality.
     */
    private int minCardinality;
    /**
     * The maximum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where five is the value for the maxCardinality.
     */
    private int maxCardinality;
    /**
     * An indicator that specifies that the values for the characteristic can be extended by adding new values when instantiating a characteristic for an Entity.
     */
    private boolean extensible;
    /**
     * A rule or principle represented in symbols, numbers, or letters, often in the form of an equation used to derive the value of a characteristic value.
     */
    private String derivationFormula;
    /**
     * The period of time for which a characteristic is applicable.
     */
    private TimePeriod validFor;

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnique() {
        return this.unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public String getValueType() {
        return this.valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public int getMinCardinality() {
        return this.minCardinality;
    }

    public void setMinCardinality(int minCardinality) {
        this.minCardinality = minCardinality;
    }

    public int getMaxCardinality() {
        return this.maxCardinality;
    }

    public void setMaxCardinality(int maxCardinality) {
        this.maxCardinality = maxCardinality;
    }

    public boolean isExtensible() {
        return this.extensible;
    }

    public void setExtensible(boolean extensible) {
        this.extensible = extensible;
    }

    public String getDerivationFormula() {
        return this.derivationFormula;
    }

    public void setDerivationFormula(String derivationFormula) {
        this.derivationFormula = derivationFormula;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param id
     * @param name
     * @param valueType
     * @param validFor
     * @param unique
     * @param minCardinality
     * @param maxCardinality
     * @param extensible
     * @param description
     * @param derivationFormula
     */
    public ProductSpecCharacteristic(String id, String name, String valueType, TimePeriod validFor, String unique, int minCardinality, int maxCardinality, boolean extensible, String description, String derivationFormula) {
        if ( StringUtils.isEmpty(id) ) {
            logger.error("id should not be null");
            throw new IllegalArgumentException("id should not be null");
        }
        if ( StringUtils.isEmpty(valueType) ) {
            logger.error("valueType should not be null");
            throw new IllegalArgumentException("valueType should not be null");
        }
        if ( StringUtils.isEmpty(name)){
            logger.error("name should not be null");
            throw new IllegalArgumentException("name should not be null");
        }
        this.ID = id;
        this.name = name;
        this.valueType = valueType;
        this.validFor = validFor;
        this.unique = unique;
        this.minCardinality = minCardinality;
        this.maxCardinality = maxCardinality;
        this.extensible = extensible;
        this.description = description;
        this.derivationFormula = derivationFormula;
    }

    /**
     * 
     * @param minCardinality
     * @param maxCardinality
     */
    public void specifyCardinality(int minCardinality, int maxCardinality) {
    	if (minCardinality > maxCardinality){
    		throw new IllegalArgumentException("param is not illegal");
    	}
        this.minCardinality =minCardinality;
        this.maxCardinality =maxCardinality;
    }

    /**
     * 
     * @param charVal
     */
    public void addValue(ProductSpecCharacteristicValue charVal) {
    	if(null == charVal){
    		throw new IllegalArgumentException("param is not illegal");
    	}
    	if(null == this.prodSpecCharValue){
    		this.prodSpecCharValue=new ArrayList<ProductSpecCharacteristicValue>();
    	}
    	for(ProductSpecCharacteristicValue pscv:prodSpecCharValue){
    		if(pscv.equals(charVal)){
    			return;
    		}
    	}
    	this.prodSpecCharValue.add(charVal);
    }

    /**
     * 
     * @param charVal
     */
    public void removeValue(ProductSpecCharacteristicValue charVal) {
    	if(null == charVal){
    		throw new IllegalArgumentException("param is not illegal");
    	}
    	if(null != this.prodSpecCharValue && this.prodSpecCharValue.size()>0){
    		if(prodSpecCharValue.contains(charVal)){
    			prodSpecCharValue.remove(charVal);
    		}
    	}
    }

    /**
     * 
     * @param time
     */
    public ProductSpecCharacteristicValue[] getValue(Date time) {
    	// TODO
    	return null;
    }

    /**
     * 
     * @param defaultCharVal
     */
    public void specifyDefaultValue(ProductSpecCharacteristicValue defaultCharVal) {
    	if (null == defaultCharVal){
    		throw new IllegalArgumentException("param is not illegal");
    	}
    	if(null != this.prodSpecCharValue){
    		for(ProductSpecCharacteristicValue productSpecCharacteristicValue:prodSpecCharValue){
        		if(productSpecCharacteristicValue.equals(defaultCharVal)){
        			if(!productSpecCharacteristicValue.isIsDefault()){
        				productSpecCharacteristicValue.setIsDefault(true);
        			}
        		}
    		}
        }else{
            logger.error("you set setDefaultValue  not exists");
        }
    }

    public ProductSpecCharacteristicValue getDefaultValue() {
        if(null == this.prodSpecCharValue){
        	throw new IllegalArgumentException("param is not illegal");
        }else{
        	for(ProductSpecCharacteristicValue productSpecCharacteristicValue :prodSpecCharValue){
        		if(productSpecCharacteristicValue.isIsDefault()){
        			return productSpecCharacteristicValue;
        		}
        	}
        }
        return null;
    }

    /**
     * 
     * @param specChar
     * @param type
     * @param validFor
     */
    public void addRelatedCharacteristic(ProductSpecCharacteristic specChar, String type, TimePeriod validFor) {
    	ProductSpecCharRelationship pship = new ProductSpecCharRelationship(this,specChar,type,validFor);    	
    	if(null == prodSpecCharRelationship){
    		prodSpecCharRelationship = new ArrayList<ProductSpecCharRelationship>();
    	}
    	for(ProductSpecCharRelationship productSpecCharRelationship :prodSpecCharRelationship){
    		if(productSpecCharRelationship.equals(pship)){
    			return;
    		}
    	}
    	prodSpecCharRelationship.add(pship);
    }


    /**
     * 
     * @param specChar
     * @param type
     * @param validFor
     * @param charSpecSeq
     */
    public void addRelatedCharacteristic(ProductSpecCharacteristic specChar, String type, TimePeriod validFor, int charSpecSeq) {
    	if(null == this.prodSpecCharRelationship){
    		this.prodSpecCharRelationship =new ArrayList<ProductSpecCharRelationship>();
    	}
    	ProductSpecCharRelationship ship =new ProductSpecCharRelationship(this,specChar,type,validFor,charSpecSeq);
    	for(ProductSpecCharRelationship productSpecCharRelationship :prodSpecCharRelationship){
    		if(productSpecCharRelationship.equals(ship)){
    			return;
    		}
    	}
    	this.prodSpecCharRelationship.add(ship);
    }


    /**
     * 
     * @param specChar
     */
    public void removeRelatedCharacteristic(ProductSpecCharacteristic specChar) {
    	if (null == specChar){
    		throw new IllegalArgumentException("param is not illegal");
    	}
    	if(null == this.prodSpecCharRelationship){
    		return;
    	}
    	 Iterator<ProductSpecCharRelationship> iterator = prodSpecCharRelationship.iterator();    
         while (iterator.hasNext()) { 
        	 if(iterator.next().getTargetProdSpecChar().equals(specChar)){
     			 iterator.remove();
     		}
         }
    }


    /**
     * 
     * @param charRelationshipType
     */
    public ProductSpecCharacteristic[] retieveRelatedCharacteristic(String charRelationshipType) {
    	// TODO
    	return null;
    }

    /**
     * 
     * @param charRelationshipType
     * @param time
     */
    public ProductSpecCharacteristic[] retieveRelatedCharacteristic(String charRelationshipType, Date time) {
        // TODO 
    	return null;
    }

    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProductSpecCharacteristic))
			return false;
		ProductSpecCharacteristic other = (ProductSpecCharacteristic) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer tostr = new StringBuffer();
		tostr.append("ID=" + ID + ", name=" + name)
			 .append(", description=" + description + ", unique=" + unique)
			 .append(", valueType=" + valueType + ", minCardinality="+ minCardinality)
			 .append(", maxCardinality=" + maxCardinality)
			 .append(", extensible=" + extensible + ", derivationFormula="+ derivationFormula)
			 .append(", validFor=" + validFor );
		if(prodSpecCharValue!=null){
			tostr.append("\n     ProductSpecCharacteristicValue【");
			for(ProductSpecCharacteristicValue pv:prodSpecCharValue){
				tostr.append("\n     "+pv.toString());
			}
			tostr.append("\n     】");
		}
		if(prodSpecCharRelationship!=null){
			tostr.append("\n     ProductSpecCharRelationship【");
			for(ProductSpecCharRelationship pvRship:prodSpecCharRelationship){
				tostr.append("\n     "+pvRship.toString());
			}
			tostr.append("\n】");
		}
		return tostr.toString();
	}

	
}
