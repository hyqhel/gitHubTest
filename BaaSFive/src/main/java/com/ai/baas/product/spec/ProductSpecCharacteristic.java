package com.ai.baas.product.spec;

import java.text.ParseException;
import java.util.*;

import com.ai.baas.common.util.CommonUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ai.baas.basetype.TimePeriod;

/**
 * A characteristic quality or distinctive feature of a ProductSpecification. The characteristic can be take on a discrete value, such as color, can take on a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) = 30 - talk time *3). Certain characteristics, such as color, may be configured during the ordering or some other process.
 */
public class ProductSpecCharacteristic {
	private static final Logger logger = Logger.getLogger(ProductSpecCharacteristic.class);
    private Set<ProductSpecCharacteristicValue> prodSpecCharValue;
    private List<ProductSpecCharRelationship> prodSpecCharRelationship;
    public Set<ProductSpecCharacteristicValue> getProdSpecCharValue() {
		return prodSpecCharValue;
	}

	public void setProdSpecCharValue(
            Set<ProductSpecCharacteristicValue> prodSpecCharValue) {
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
     */
    public ProductSpecCharacteristic(String id, String name, String valueType, TimePeriod validFor, String unique,
                                     int minCardinality, int maxCardinality) {

        if ( StringUtils.isEmpty( id ) ) {
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
        this.maxCardinality = maxCardinality;
        this.minCardinality = minCardinality;
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
            logger.error("maxCardinality is less than minCardinality");
            throw new IllegalArgumentException("maxCardinality is less than minCardinality");
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
    		throw new IllegalArgumentException("Parameter charVal should not be null");
    	}
    	if(null == this.prodSpecCharValue){
    		this.prodSpecCharValue = new HashSet<ProductSpecCharacteristicValue>();
    	}
        for (ProductSpecCharacteristicValue pscv:prodSpecCharValue){
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
    		throw new IllegalArgumentException("Parameter charVal should not be  null");
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
    public List<ProductSpecCharacteristicValue> retrieveValue(Date time) {
        List<ProductSpecCharacteristicValue> productSpecCharValues = new ArrayList<ProductSpecCharacteristicValue>();
        if (null == time) {
            throw new IllegalArgumentException("DateTime should not be null.");
        }
        if ( null != this.prodSpecCharValue ) {
            for (ProductSpecCharacteristicValue charValue : prodSpecCharValue) {
                if (null != charValue.getValidFor() && 0 == charValue.getValidFor().isInTimePeriod(time)) {
                    productSpecCharValues.add(charValue);
                }
            }
        }
        return productSpecCharValues;
    }

    /**
     * 
     * @param defaultCharVal
     */
    public void specifyDefaultValue(ProductSpecCharacteristicValue defaultCharVal) {
    	if (null == defaultCharVal){
    		throw new IllegalArgumentException("Parameter  defaultCharVal should not be null");
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

    public List<ProductSpecCharacteristicValue> retrieveDefaultValue() {
        if(null == this.prodSpecCharValue){
            return null;
        }else{
            List<ProductSpecCharacteristicValue> psvcoll = new ArrayList<ProductSpecCharacteristicValue>();
        	for(ProductSpecCharacteristicValue productSpecCharacteristicValue :prodSpecCharValue){
        		if(productSpecCharacteristicValue.isIsDefault()){
                    psvcoll.add(productSpecCharacteristicValue);
        		}
        	}
            return psvcoll;
        }
    }

    /**
     * 
     * @param specChar
     * @param type
     * @param validFor
     */
    public void addRelatedCharacteristic(ProductSpecCharacteristic specChar, String type, TimePeriod validFor) {
    	if(null == prodSpecCharRelationship){
    		prodSpecCharRelationship = new ArrayList<ProductSpecCharRelationship>();
    	}
        ProductSpecCharRelationship relationship=this.retrieveRelatedCharacteristic(specChar);
        if(relationship!=null){
            if(type.equals(relationship.getCharRelationshipType()) && relationship.getValidFor().isOverlap(validFor)){
                logger.warn("Characteristic have been created in the specified time");
                logger.warn("the exists relationship :"+relationship.toString());
                return;
            }
        }
        ProductSpecCharRelationship pship = new ProductSpecCharRelationship(this,specChar,type,validFor);
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
        ProductSpecCharRelationship relationship=this.retrieveRelatedCharacteristic(specChar);
        if(relationship!=null){
            if(type.equals(relationship.getCharRelationshipType()) && relationship.getValidFor().isOverlap(validFor)){
                logger.warn("Characteristic have been created in the specified time");
                logger.warn("the exists relationship :"+relationship.toString());
                return;
            }
        }
        ProductSpecCharRelationship ship = new ProductSpecCharRelationship(this,specChar,type,validFor,charSpecSeq);
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
    		throw new IllegalArgumentException("Parameter specChar should not be  null");
    	}
    	if(null == this.prodSpecCharRelationship){
    		return;
    	}
        for(ProductSpecCharRelationship psr:prodSpecCharRelationship){
            if(psr.getTargetProdSpecChar().equals(specChar)){
                prodSpecCharRelationship.remove(psr);
            }
        }
    }


    /**
     * 
     * @param charRelationshipType
     */
    public List<ProductSpecCharacteristic> retrieveRelatedCharacteristic(String charRelationshipType) {
        if(StringUtils.isEmpty(charRelationshipType)){
            throw new IllegalArgumentException("type should not be null.");
        }
        List<ProductSpecCharacteristic>  characteristic=new ArrayList<ProductSpecCharacteristic>();
        if ( null != prodSpecCharRelationship ) {
            for (ProductSpecCharRelationship productSpecCharRelationship : prodSpecCharRelationship) {
                if(charRelationshipType.equals(productSpecCharRelationship.getCharRelationshipType())){
                    characteristic.add(productSpecCharRelationship.getTargetProdSpecChar());
                }
            }
        }
        return characteristic;
    }

    /**
     * 
     * @param charRelationshipType
     * @param time
     */
    public List<ProductSpecCharacteristic> retrieveRelatedCharacteristic(String charRelationshipType, Date time){
        if (StringUtils.isEmpty(charRelationshipType) ) {
            throw new IllegalArgumentException("type or dateTime  should not be null");
        }
        if ( null == time) {
            logger.error(" dateTime  should not be null");
            throw new IllegalArgumentException(" dateTime  should not be null");
        }
        List<ProductSpecCharacteristic>  characteristic=new ArrayList<ProductSpecCharacteristic>();;
        if (null !=prodSpecCharRelationship ) {
            for (ProductSpecCharRelationship productSpecCharRelationship : prodSpecCharRelationship) {
                if(charRelationshipType.equals(productSpecCharRelationship.getCharRelationshipType()) && 0 == productSpecCharRelationship.getValidFor().isInTimePeriod(time)){
                    characteristic.add(productSpecCharRelationship.getTargetProdSpecChar());
                }
            }
        }
        return characteristic;
    }
    private ProductSpecCharRelationship retrieveRelatedCharacteristic(ProductSpecCharacteristic characteristic ){

        if ( null == characteristic ) {
            logger.error("characteristic  should not be null");
            throw new IllegalArgumentException("characteristic  should not be null");
        }
        if (null !=prodSpecCharRelationship) {
            for (ProductSpecCharRelationship productSpecCharRelationship : prodSpecCharRelationship) {
                if( productSpecCharRelationship.getTargetProdSpecChar().equals(characteristic)){
                    return productSpecCharRelationship;
                }
            }
        }
        return null;
    }
    public boolean updateRelatedCharValidPeriod(ProductSpecCharacteristic prodSpecChar,TimePeriod validFor){

        if (null == prodSpecChar ) {
            logger.error("prodSpecChar should not be null .");
            throw new IllegalArgumentException("prodSpecChar should not be null .");
        }
        if (null == validFor ){
            logger.error("validFor should not be null .");
            throw new IllegalArgumentException("validFor should not be null .");
        }

        if ( null!=prodSpecCharRelationship ) {
            for (ProductSpecCharRelationship productSpecRelationship : prodSpecCharRelationship) {
                if ( productSpecRelationship.getTargetProdSpecChar().equals(prodSpecChar)) {
                    productSpecRelationship.setValidFor(validFor);
                    return true;
                }
            }
        }
        return false;
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
    /*
    * (non-Javadoc)
    *
    * @see java.lang.Object#toString()
    */
    @Override
    public String toString() {
        Map <String,Object> result=getBasicInfoToMap();
        result.put("prodSpecCharValue", prodSpecCharValue);
        result.put("charRelationShip", prodSpecCharRelationship);
        return  CommonUtils.format(result.toString());
    }

    public String basicInfoToString(){
        return getBasicInfoToMap().toString();
    }
    private Map<String,Object> getBasicInfoToMap(){
        Map <String,Object> result=new HashMap<String,Object>();
        result.put("name", name);
        result.put("id", ID);
        result.put("unique", unique);
        result.put("valueType",valueType);
        result.put("extensible",extensible);
        result.put("derivationFormula",derivationFormula);
        result.put("maxCardinality",maxCardinality);
        result.put("minCardinality",minCardinality);
        result.put("validFor",validFor);
        result.put("description",description);
        return result;
    }
	
}
