package com.ai.baas.product.spec;

import java.util.*;

import com.ai.baas.basetype.*;

public class ProductSpecCharUse {

    ProductSpecCharacteristic prodSpecChar;
    public List<ProdSpecCharValueUse> prodSpecCharValue;
    /**
     * A word, term, or phrase by which the CharacteristicSpecification is known and distinguished from other CharacteristicSpecifications.
     */
    private String name;
    /**
     * A narrative that explains the CharacteristicSpecification.
     */
    private String description;
    /**
     * An indicator that specifies if a value is unique for the specification.
     * 
     * Possible values are: "unique while value is in effect" and "unique whether value is in effect or not"
     */
    private String unique;
    /**
     * An indicator that specifies if the associated CharacteristicSpecification is a composite.
     */
    private boolean isPackage;
    /**
     * An indicator that specifies that the CharacteristicSpecValues associated with the CharacteristicSpec cannot be changed when instantiating a ServiceCharacteristicValue. For example, a bandwidth of 64 MB cannot be changed.
     */
    private boolean canBeOveridden;
    /**
     * The minimum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where zero is the value for the minCardinality.
     */
    private int minCardinality;
    /**
     * The maximum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where five is the value for the maxCardinality.
     */
    private int maxCardinality;
    /**
     * An indicator that specifies that the values for the characteristic can be extended by adding new values when instantiating a characteristic for a Service.
     */
    private boolean extensible;

    public ProductSpecCharacteristic getProdSpecChar() {
        return this.prodSpecChar;
    }

    public void setProdSpecChar(ProductSpecCharacteristic prodSpecChar) {
        this.prodSpecChar = prodSpecChar;
    }

    public List<ProdSpecCharValueUse> getProdSpecCharValue() {
        return this.prodSpecCharValue;
    }

    public void setProdSpecCharValue(List<ProdSpecCharValueUse> prodSpecCharValue) {
        this.prodSpecCharValue = prodSpecCharValue;
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

    public boolean isIsPackage() {
        return this.isPackage;
    }

    public void setIsPackage(boolean isPackage) {
        this.isPackage = isPackage;
    }

    public boolean isCanBeOveridden() {
        return this.canBeOveridden;
    }

    public void setCanBeOveridden(boolean canBeOveridden) {
        this.canBeOveridden = canBeOveridden;
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

    /**
     * 
     * @param specChar
     * @param canBeOveridden
     * @param isPackage
     * @param validFor
     */
    public ProductSpecCharUse(ProductSpecCharacteristic specChar, boolean canBeOveridden, boolean isPackage, TimePeriod validFor) {
    	this.prodSpecChar =specChar;
    	this.canBeOveridden=canBeOveridden;
    	this.isPackage = isPackage;
    }

    /**
     * 
     * @param specCharId
     * @param canBeOveridden
     * @param isPackage
     * @param validFor
     */
    public ProductSpecCharUse(String specCharId, boolean canBeOveridden, boolean isPackage, TimePeriod validFor) {
    	this.canBeOveridden = canBeOveridden;
    	this.isPackage=isPackage;
    }

    /**
     * 
     * @param specChar
     * @param canBeOveridden
     * @param isPackage
     * @param validFor
     * @param name
     * @param unique
     * @param minCardinality
     * @param maxCardinality
     * @param extensible
     * @param description
     */
    public ProductSpecCharUse(ProductSpecCharacteristic specChar, boolean canBeOveridden, boolean isPackage, TimePeriod validFor, String name, String unique, int minCardinality, int maxCardinality, boolean extensible, String description) {
        this.prodSpecChar = specChar;
        this.canBeOveridden = canBeOveridden;
        this.isPackage = isPackage;
        this.name = name;
        this.unique = unique;
        this.minCardinality = minCardinality;
        this.maxCardinality = maxCardinality;
        this.extensible = extensible;
        this.description = description;
    }

    /**
     * 
     * @param specCharId
     * @param canBeOveridden
     * @param isPackage
     * @param validFor
     * @param name
     * @param unique
     * @param minCardinality
     * @param maxCardinality
     * @param extensible
     * @param description
     */
    public ProductSpecCharUse(String specCharId, boolean canBeOveridden, boolean isPackage, TimePeriod validFor, String name, String unique, int minCardinality, int maxCardinality, boolean extensible, String description) {
    	this.canBeOveridden = canBeOveridden;
    	this.isPackage =isPackage;
    	 this.name=name;
    	 this.unique =unique;
    	 this.minCardinality=minCardinality;
    	 this.maxCardinality =maxCardinality;
    	 this.extensible =extensible;
    	 this.description =description;
    }

    /**
     * 
     * @param minCardinality
     * @param maxCardinality
     */
    public void setCardinality(int minCardinality, int maxCardinality) {
    	this.minCardinality =minCardinality;
    	this.maxCardinality = maxCardinality;
    }

    /**
     * 
     * @param charValue
     * @param isDefault
     * @param validFor
     */
    public void addValue(ProductSpecCharacteristicValue charValue, boolean isDefault, TimePeriod validFor) {
    	ProdSpecCharValueUse prodSpecCharValueUse = new ProdSpecCharValueUse(charValue, isDefault, validFor);
    	
    	if(null==prodSpecCharValue ){
    		prodSpecCharValue = new ArrayList<ProdSpecCharValueUse>();
    	}
    	
    	prodSpecCharValue.add(prodSpecCharValueUse);
    }

    /**
     * 
     * @param charValueId
     * @param isDefault
     * @param validFor
     */
    public void addValue(String charValueId, boolean isDefault, TimePeriod validFor) {
    	ProdSpecCharValueUse pscvu=new ProdSpecCharValueUse(charValueId,isDefault,validFor);
    	this.prodSpecCharValue.add(pscvu);
    }

    /**
     * 
     * @param charValue
     */
    public void removeValue(ProductSpecCharacteristicValue charValue) {
    	if(this.prodSpecCharValue!=null){
    		for(int i=0;i<this.prodSpecCharValue.size();i++){
        		if(this.prodSpecCharValue.get(i).getProdSpecCharValue().getId().equals(charValue.getId())){
        			this.prodSpecCharValue.remove(i);
        		}
        	}	
    	}else{
    		 throw new UnsupportedOperationException("you remove not exists");
    	}
    	
    }

    /**
     * 
     * @param charValueId
     */
    public void removeValue(String charValueId) {
    	if(this.prodSpecCharValue!=null){
    		for(int i=0;i<this.prodSpecCharValue.size();i++){
        		if(this.prodSpecCharValue.get(i).getProdSpecCharValue().getId().equals(charValueId)){
        			this.prodSpecCharValue.remove(i);
        		}
        	}	
    	}else{
    		 throw new UnsupportedOperationException("you remove not exists");
    	}
    }

    /**
     * 
     * @param defaultValue
     */
    public void specifyDefaultCharacteristicValue(ProductSpecCharacteristicValue defaultValue) {
        if(this.prodSpecCharValue!=null){
        	for(int i=0;i<this.prodSpecCharValue.size();i++){
        		if(this.prodSpecCharValue.get(i).getProdSpecCharValue().getId().equals(defaultValue.getId())){
        			if(!this.prodSpecCharValue.get(i).getProdSpecCharValue().isIsDefault()){
        				this.prodSpecCharValue.get(i).getProdSpecCharValue().setIsDefault(true);
        			}
        		}
        	}
        }else{
        	throw new UnsupportedOperationException("you remove not exists");
        }
    }

    /**
     * 
     * @param defaultValueId
     */
    public void specifyDefaultCharacteristicValue(String defaultValueId) {
    	if(this.prodSpecCharValue!=null){
        	for(int i=0;i<this.prodSpecCharValue.size();i++){
        		if(this.prodSpecCharValue.get(i).getProdSpecCharValue().getId().equals(defaultValueId)){
        			if(!this.prodSpecCharValue.get(i).getProdSpecCharValue().isIsDefault()){
        				this.prodSpecCharValue.get(i).getProdSpecCharValue().setIsDefault(true);
        			}
        		}
        	}
        }else{
        	throw new UnsupportedOperationException("you specify default not exists");
        }
    }

}