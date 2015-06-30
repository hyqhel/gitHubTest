package com.ai.baas.product.spec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public boolean removeValue(ProductSpecCharacteristicValue charVal) {
    	if(charVal==null){
    		return false;
    	}
    	if(this.prodSpecCharValue!=null && this.prodSpecCharValue.size()>0){
    		if(!prodSpecCharValue.contains(charVal)){
    			return false;
    		}else{
    			prodSpecCharValue.remove(charVal);
    			return true;
    		}
    	}else{
    		return false;
    	}
    }

    /**
     * 
     * @param time
     */
    public ProductSpecCharacteristicValue[] getValue(Date time) {
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param defaultCharVal
     */
    public void setDefaultValue(ProductSpecCharacteristicValue defaultCharVal) {
    	if(this.prodSpecCharValue!=null){
        	for(int i=0;i<this.prodSpecCharValue.size();i++){
        		ProductSpecCharacteristicValue psv = this.prodSpecCharValue.get(i);
        		if(psv.equals(defaultCharVal)){
        			if(!psv.isIsDefault()){
        				psv.setIsDefault(true);
        			}
        		}
        	}
        }else{
            logger.error("you set setDefaultValue  not exists");
        }
    }

    /**
     * 
     * @param defaultCharValId
     */
    public void setDefaultValue(String defaultCharValId) {
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
    	for(int i=0;i<prodSpecCharRelationship.size();i++){
    		if(prodSpecCharRelationship.get(i).equals(pship)){
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
    	if(this.prodSpecCharRelationship==null){
    		this.prodSpecCharRelationship =new ArrayList<ProductSpecCharRelationship>();
    	}
    	ProductSpecCharRelationship ship =new ProductSpecCharRelationship(this,specChar,type,validFor,charSpecSeq);
    	for(int i=0;i<prodSpecCharRelationship.size();i++){
    		if(prodSpecCharRelationship.get(i).equals(ship)){
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
    	if(this.prodSpecCharRelationship == null){
    		return;
    	}
    	for(int i=0;i<prodSpecCharRelationship.size();i++){
    		if(prodSpecCharRelationship.get(i).getTargetProdSpecChar().equals(specChar)){
    			prodSpecCharRelationship.remove(i);
    		}
    	}
    }


    /**
     * 
     * @param charRelationshipType
     */
    public ProductSpecCharacteristic[] queryRelatedCharacteristic(String charRelationshipType) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
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
		if (!(obj instanceof ProductSpecCharacteristic)){
			return false;
		}
		ProductSpecCharacteristic other = (ProductSpecCharacteristic) obj;
		if (ID == null && other.getID() == null
				|| ID != null && ID.equals(other.getID())) {
				return true;
		} 
		return false;
	}

	@Override
	public String toString() {
		StringBuffer tostr = new StringBuffer();
		tostr.append("特征 \n   ")
		     .append("ID=" + ID + ", name=" + name)
			 .append(", description=" + description + ", unique=" + unique)
			 .append(", valueType=" + valueType + ", minCardinality="+ minCardinality)
			 .append(", maxCardinality=" + maxCardinality)
			 .append(", extensible=" + extensible + ", derivationFormula="+ derivationFormula)
			 .append(", validFor=" + validFor );
		if(prodSpecCharValue!=null){
			tostr.append("\n     特征值 "); 
			for(ProductSpecCharacteristicValue pv:prodSpecCharValue){
				tostr.append("\n     "+pv.toString());
			}
		}
		if(prodSpecCharRelationship!=null){
			tostr.append("\n\n关联的特征【"); 
			for(ProductSpecCharRelationship pvRship:prodSpecCharRelationship){
				tostr.append("\n   "+pvRship.toString());
			}
			tostr.append("\n】");
		}
		return tostr.toString();
	}

	
}
