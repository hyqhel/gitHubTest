package com.ai.baas.product.spec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ai.baas.basetype.TimePeriod;

/**
 * A characteristic quality or distinctive feature of a ProductSpecification. The characteristic can be take on a discrete value, such as color, can take on a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) = 30 - talk time *3). Certain characteristics, such as color, may be configured during the ordering or some other process.
 */
public class ProductSpecCharacteristic {

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
    public void setCardinality(int minCardinality, int maxCardinality) {
        this.minCardinality =minCardinality;
        this.maxCardinality =maxCardinality;
    }

    /**
     * 
     * @param charVal
     */
    public void addValue(ProductSpecCharacteristicValue charVal) {
    	if(this.prodSpecCharValue ==null ){
    		this.prodSpecCharValue=new ArrayList<ProductSpecCharacteristicValue>();
    	}
    	this.prodSpecCharValue.add(charVal);
    }

    /**
     * 
     * @param charValId
     */
    public void addValue(String charValId) {
    	//this.prodSpecCharValue.add(e)
    }

    /**
     * 
     * @param charVal
     */
    public void removeValue(ProductSpecCharacteristicValue charVal) {
    	if(charVal==null){
            throw new UnsupportedOperationException("remove charval is null");
    	}
    	if(this.prodSpecCharValue!=null && this.prodSpecCharValue.size()>0){
    		for(int i=0;i<this.prodSpecCharValue.size();i++){
    			if(prodSpecCharValue.get(i).getId().equals(charVal.getId())){
    				prodSpecCharValue.remove(i);
    			}
    		}
    	}
    }

    /**
     * 
     * @param charValId
     */
    public void removeValue(String charValId) {
    	if("".equals(charValId)){
            throw new UnsupportedOperationException("remove charval is null");
    	}
    	if(this.prodSpecCharValue!=null && this.prodSpecCharValue.size()>0){
    		for(int i=0;i<this.prodSpecCharValue.size();i++){
    			if(prodSpecCharValue.get(i).getId().equals(charValId)){
    				prodSpecCharValue.remove(i);
    			}
    		}
    	}
    }

    /**
     * 
     * @param time
     */
    public ProductSpecCharacteristicValue[] getValue(Date time) {
        // TODO - implement ProductSpecCharacteristic.getValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param defaultCharVal
     */
    public void setDefaultValue(ProductSpecCharacteristicValue defaultCharVal) {
    	if(this.prodSpecCharValue!=null){
        	for(int i=0;i<this.prodSpecCharValue.size();i++){
        		if(this.prodSpecCharValue.get(i).getId().equals(defaultCharVal.getId())){
        			if(!this.prodSpecCharValue.get(i).isIsDefault()){
        				this.prodSpecCharValue.get(i).setIsDefault(true);
        			}
        		}
        	}
        }else{
        	throw new UnsupportedOperationException("you remove not exists");
        }
    }

    /**
     * 
     * @param defaultCharValId
     */
    public void setDefaultValue(String defaultCharValId) {
    	if(this.prodSpecCharValue!=null){
        	for(int i=0;i<this.prodSpecCharValue.size();i++){
        		if(this.prodSpecCharValue.get(i).getId().equals(defaultCharValId)){
        			if(!this.prodSpecCharValue.get(i).isIsDefault()){
        				this.prodSpecCharValue.get(i).setIsDefault(true);
        			}
        		}else{
        			if(this.prodSpecCharValue.get(i).isIsDefault()){
        				this.prodSpecCharValue.get(i).setIsDefault(false);
        			}
        		}
        	}
        }else{
        	throw new UnsupportedOperationException("you remove not exists");
        }
    }

    public ProductSpecCharacteristicValue getDefaultValue() {
        if(this.prodSpecCharValue==null){
        	throw new UnsupportedOperationException("no value");
        }else{
        	for(int i=0;i<this.prodSpecCharValue.size();i++){
        		if(prodSpecCharValue.get(i).isIsDefault()){
        			return prodSpecCharValue.get(i);
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
        // TODO - implement ProductSpecCharacteristic.addRelatedCharacteristic
    	ProductSpecCharRelationship pship = new ProductSpecCharRelationship(this,specChar,type,validFor);    	
    	if(prodSpecCharRelationship ==null){
    		prodSpecCharRelationship = new ArrayList<ProductSpecCharRelationship>();
    	}
    	prodSpecCharRelationship.add(pship);
    }

    /**
     * 
     * @param specCharId
     * @param type
     * @param validFor
     */
    public void addRelatedCharacteristic(String specCharId, String type, TimePeriod validFor) {
        // TODO - implement ProductSpecCharacteristic.addRelatedCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param specChar
     * @param type
     * @param validFor
     * @param charSpecSeq
     */
    public void addRelatedCharacteristic(ProductSpecCharacteristic specChar, String type, TimePeriod validFor, int charSpecSeq) {
        // TODO - implement ProductSpecCharacteristic.addRelatedCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param specCharId
     * @param type
     * @param validFor
     * @param charSpecSeq
     */
    public void addRelatedCharacteristic(String specCharId, String type, TimePeriod validFor, int charSpecSeq) {
        // TODO - implement ProductSpecCharacteristic.addRelatedCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param specChar
     */
    public void removeRelatedCharacteristic(ProductSpecCharacteristic specChar) {
        // TODO - implement ProductSpecCharacteristic.removeRelatedCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param specCharId
     */
    public void removeRelatedCharacteristic(String specCharId) {
        // TODO - implement ProductSpecCharacteristic.removeRelatedCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param charRelationshipType
     */
    public ProductSpecCharacteristic[] queryRelatedCharacteristic(String charRelationshipType) {
        // TODO - implement ProductSpecCharacteristic.queryRelatedCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param charRelationshipType
     * @param time
     */
    public ProductSpecCharacteristic[] queryRelatedCharacteristic(String charRelationshipType, Date time) {
        // TODO - implement ProductSpecCharacteristic.queryRelatedCharacteristic
        throw new UnsupportedOperationException();
    }

}
