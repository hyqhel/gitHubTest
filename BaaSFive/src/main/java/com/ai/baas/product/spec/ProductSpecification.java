package com.ai.baas.product.spec;

import java.util.*;

import com.ai.baas.basetype.*;
import com.ai.baas.product.offering.SimpleProductOffering;

/**
 * A detailed description of a tangible or intangible object made available externally in the form of a ProductOffering to Customers or other Parties playing a PartyRole. A ProductSpecification may consist of other ProductSpecifications supplied together as a collection. Members of the collection may be offered in their own right. ProductSpecifications may also exist within groupings, such as ProductCategories, ProductLines, and ProductTypes.
 */
public abstract class ProductSpecification {

    public List<ProductSpecificationCost> productSpecificationCost;
    public List<ProductSpecificationRelationship> prodSpecRelationship;
    public List<ProductSpecificationVersion> prodSpecVersion;
    public List<ProductSpecCharUse> prodSpecChar;
    public List<CompositeProductSpecification> compositeProdSpec;
    public List<SimpleProductOffering> simpleProdOffering;
    
    public List<ProductSpecCharUse> getProdSpecChar() {
		return prodSpecChar;
	}

	/**
     * The name of the product specification.
     */
    private String name;
    /**
     * The manufacturer or trademark of the specification.
     */
    private String brand;
    /**
     * A narrative that explains in detail what the product spec is.
     */
    private String description;
    /**
     * An identification number assigned to uniquely identify the specification.
     */
    private String productNumber;
    /**
     * The period for which the product specification is valid.
     */
    private TimePeriod validFor;
    /**
     * The condition of the product specification, such as active, inactive, planned.
     */
    private String lifecycleStatus;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductNumber() {
        return this.productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public String getLifecycleStatus() {
        return this.lifecycleStatus;
    }

    /**
     * Initializes a newly created {@code ProductSpecification} object so that it represents the all information. When the specification is a new one, the state of the specification will be initialized as "planned"
     * @param name The name of the product specification.
     * @param productNumber An identification number assigned to uniquely identify the specification.
     * @param brand The manufacturer or trademark of the specification.
     */
    public ProductSpecification(String name, String productNumber, String brand) {
        // TODO - implement ProductSpecification.ProductSpecification
        throw new UnsupportedOperationException();
    }

    /**
     * Initializes a newly created {@code ProductSpecification} object so that it represents the all information. When the specification is a new one, the state of the specification will be initialized as "planned"
     * @param name The name of the product specification.
     * @param productNumber An identification number assigned to uniquely identify the specification.
     * @param brand The manufacturer or trademark of the specification.
     * @param validFor The period of time for which the use of the ProductSpecification is applicable.
     * @param description A narrative that explains in detail what the product spec is.
     */
    public ProductSpecification(String name, String productNumber, String brand, TimePeriod validFor, String description) {
        this.name = name;
        this.productNumber = productNumber;
        this.brand = brand;
        this.validFor = validFor;
        this.description = description;
    }

    /**
     * 
     * @param specChar A characteristic quality or distinctive feature of a ProductSpecification. The object must exist in the system
     * @param canBeOveridden An indicator that specifies that the CharacteristicSpecValues associated with the CharacteristicSpec cannot be changed when instantiating a ServiceCharacteristicValue. For example, a bandwidth of 64 MB cannot be changed.
     * @param isPackage An indicator that specifies if the associated CharacteristicSpecification is a composite. true£ºis a composite one
     * @param validFor The period of time for which the use of the CharacteristicSpecification is applicable.
     */
    public void addCharacteristic(ProductSpecCharacteristic specChar, boolean canBeOveridden, boolean isPackage, TimePeriod validFor) {
        ProductSpecCharUse prodSpecCharUse = new ProductSpecCharUse(specChar, canBeOveridden, isPackage, validFor);
    	
        prodSpecChar.add(prodSpecCharUse);
    }

    /**
     * 
     * @param specCharId A characteristic quality or distinctive feature of a ProductSpecification. The object must exist in the system
     * @param canBeOveridden An indicator that specifies that the CharacteristicSpecValues associated with the CharacteristicSpec cannot be changed when instantiating a ServiceCharacteristicValue. For example, a bandwidth of 64 MB cannot be changed.
     * @param isPackage An indicator that specifies if the associated CharacteristicSpecification is a composite. true£ºis a composite one
     * @param validFor The period of time for which the use of the CharacteristicSpecification is applicable.
     */
    public void addCharacteristic(String specCharId, boolean canBeOveridden, boolean isPackage, TimePeriod validFor) {
        // TODO - implement ProductSpecification.addCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param specChar A characteristic quality or distinctive feature of a ProductSpecification. The object must exist in the system
     * @param canBeOveridden An indicator that specifies that the CharacteristicSpecValues associated with the CharacteristicSpec cannot be changed when instantiating a ServiceCharacteristicValue. For example, a bandwidth of 64 MB cannot be changed.
     * @param isPackage An indicator that specifies if the associated CharacteristicSpecification is a composite.
     * @param validFor The period of time for which the use of the CharacteristicSpecification is applicable.
     * @param name A word, term, or phrase by which the CharacteristicSpecification is known and distinguished from other CharacteristicSpecifications.
     * @param unique An indicator that specifies if a value is unique for the specification. Possible values are: "unique while value is in effect" and "unique whether value is in effect or not"
     * @param minCardinality The minimum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where zero is the value for the minCardinality.
     * @param maxCardinality The maximum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where five is the value for the maxCardinality.
     * @param extensible An indicator that specifies that the values for the characteristic can be extended by adding new values when instantiating a characteristic for a Service.
     * @param description A narrative that explains the CharacteristicSpecification.
     */
    public abstract void addCharacteristic(ProductSpecCharacteristic specChar, boolean canBeOveridden, boolean isPackage, TimePeriod validFor, String name, String unique, int minCardinality, int maxCardinality, boolean extensible, String description);

    /**
     * 
     * @param specCharId A characteristic quality or distinctive feature of a ProductSpecification. The object must exist in the system
     * @param canBeOveridden An indicator that specifies that the CharacteristicSpecValues associated with the CharacteristicSpec cannot be changed when instantiating a ServiceCharacteristicValue. For example, a bandwidth of 64 MB cannot be changed.
     * @param isPackage An indicator that specifies if the associated CharacteristicSpecification is a composite.
     * @param validFor The period of time for which the use of the CharacteristicSpecification is applicable.
     * @param name A word, term, or phrase by which the CharacteristicSpecification is known and distinguished from other CharacteristicSpecifications.
     * @param unique An indicator that specifies if a value is unique for the specification. Possible values are: "unique while value is in effect" and "unique whether value is in effect or not"
     * @param minCardinality The minimum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where zero is the value for the minCardinality.
     * @param maxCardinality The maximum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where five is the value for the maxCardinality.
     * @param extensible An indicator that specifies that the values for the characteristic can be extended by adding new values when instantiating a characteristic for a Service.
     * @param description A narrative that explains the CharacteristicSpecification.
     */
    public abstract void addCharacteristic(String specCharId, boolean canBeOveridden, boolean isPackage, TimePeriod validFor, String name, String unique, int minCardinality, int maxCardinality, boolean extensible, String description);

    /**
     * 
     * @param specChar A characteristic quality or distinctive feature of a ProductSpecification. The {@code ProductSpecification} must have the Characteristic before.
     */
    public abstract void removeCharacteristic(ProductSpecCharacteristic specChar);

    /**
     * 
     * @param specCharId A characteristic quality or distinctive feature of a ProductSpecification. The {@code ProductSpecification} must have the Characteristic before.
     */
    public abstract void removeCharacteristic(String specCharId);

    /**
     * 
     * @param specChar A characteristic quality or distinctive feature of a ProductSpecification. The {@code ProductSpecification} must have the Characteristic.
     * @param canBeOveridden An indicator that specifies that the CharacteristicSpecValues associated with the CharacteristicSpec cannot be changed when instantiating a ServiceCharacteristicValue. For example, a bandwidth of 64 MB cannot be changed.
     * @param isPackage An indicator that specifies if the associated CharacteristicSpecification is a composite.
     * @param validFor The period of time for which the use of the CharacteristicSpecification is applicable.
     * @param name A word, term, or phrase by which the CharacteristicSpecification is known and distinguished from other CharacteristicSpecifications.
     * @param unique An indicator that specifies if a value is unique for the specification. Possible values are: "unique while value is in effect" and "unique whether value is in effect or not"
     * @param minCardinality The minimum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where zero is the value for the minCardinality.
     * @param maxCardinality The maximum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where five is the value for the maxCardinality.
     * @param extensible An indicator that specifies that the values for the characteristic can be extended by adding new values when instantiating a characteristic for a Service.
     * @param description A narrative that explains the CharacteristicSpecification.
     */
    public void modifyCharacteristicInfo(ProductSpecCharacteristic specChar, boolean canBeOveridden, boolean isPackage, TimePeriod validFor, String name, String unique, int minCardinality, int maxCardinality, boolean extensible, String description) {
        // TODO - implement ProductSpecification.modifyCharacteristicInfo
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param specCharId A characteristic quality or distinctive feature of a ProductSpecification. The {@code ProductSpecification} must have the Characteristic.
     * @param canBeOveridden An indicator that specifies that the CharacteristicSpecValues associated with the CharacteristicSpec cannot be changed when instantiating a ServiceCharacteristicValue. For example, a bandwidth of 64 MB cannot be changed.
     * @param isPackage An indicator that specifies if the associated CharacteristicSpecification is a composite.
     * @param validFor The period of time for which the use of the CharacteristicSpecification is applicable.
     * @param name A word, term, or phrase by which the CharacteristicSpecification is known and distinguished from other CharacteristicSpecifications.
     * @param unique An indicator that specifies if a value is unique for the specification. Possible values are: "unique while value is in effect" and "unique whether value is in effect or not"
     * @param minCardinality The minimum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where zero is the value for the minCardinality.
     * @param maxCardinality The maximum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where five is the value for the maxCardinality.
     * @param extensible An indicator that specifies that the values for the characteristic can be extended by adding new values when instantiating a characteristic for a Service.
     * @param description A narrative that explains the CharacteristicSpecification.
     */
    public void modifyCharacteristicInfo(String specCharId, boolean canBeOveridden, boolean isPackage, TimePeriod validFor, String name, String unique, int minCardinality, int maxCardinality, boolean extensible, String description) {
        // TODO - implement ProductSpecification.modifyCharacteristicInfo
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param specChar A characteristic quality or distinctive feature of a ProductSpecification. The object must exist in the system
     * @param charValue A number or text that be assigned to a ProductSpecCharacteristic. The value must be in the characterisc's values.
     * @param isDefault Indicates if the value is the default value for a characteristic. true£ºis default value
     * @param validFor The period of time for which the use of the CharacteristicValue is applicable.
     */
    public void attachCharacteristicValue(ProductSpecCharacteristic specChar, ProductSpecCharacteristicValue charValue, boolean isDefault, TimePeriod validFor) {
    	
    	List<ProductSpecCharUse> prodSpecCharUseList = this.prodSpecChar;
    	if(null!=prodSpecCharUseList && prodSpecCharUseList.size()>0){
    		for (int i = 0; i < prodSpecCharUseList.size(); i++) {
    			ProductSpecCharUse prodSpecCharUse = prodSpecCharUseList.get(i);
				if(prodSpecCharUse.getProdSpecChar().getID().equals(specChar.getID())){
					prodSpecCharUse.addValue(charValue, isDefault, validFor);
				}
			}
    	}
    	
    }

    /**
     * 
     * @param specCharId A characteristic quality or distinctive feature of a ProductSpecification. The object must exist in the system
     * @param charValue A number or text that be assigned to a ProductSpecCharacteristic. The value must be in the characterisc's values.
     * @param isDefault Indicates if the value is the default value for a characteristic. true£ºis default value
     * @param validFor The period of time for which the use of the CharacteristicValue is applicable.
     */
    public void attachCharacteristicValue(String specCharId, ProductSpecCharacteristicValue charValue, boolean isDefault, TimePeriod validFor) {
        // TODO - implement ProductSpecification.attachCharacteristicValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param specCharId A characteristic quality or distinctive feature of a ProductSpecification. The object must exist in the system
     * @param charValueId A number or text that be assigned to a ProductSpecCharacteristic. The value must be in the characterisc's values.
     * @param isDefault Indicates if the value is the default value for a characteristic. true£ºis default value
     * @param validFor The period of time for which the use of the CharacteristicValue is applicable.
     */
    public void attachCharacteristicValue(String specCharId, String charValueId, boolean isDefault, TimePeriod validFor) {
        // TODO - implement ProductSpecification.attachCharacteristicValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param specChar
     * @param charValue
     */
    public abstract void detachCharacteristicValue(ProductSpecCharacteristic specChar, ProductSpecCharacteristicValue charValue);

    /**
     * 
     * @param specCharId
     * @param charValueId
     */
    public abstract void detachCharacteristicValue(String specCharId, String charValueId);

    /**
     * 
     * @param specChar
     * @param defaultCharValue
     */
    public void specifyDefaultCharacteristicValue(ProductSpecCharacteristic specChar, ProductSpecCharacteristicValue defaultCharValue) {
        // TODO - implement ProductSpecification.specifyDefaultCharacteristicValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param specCharId
     * @param defaultCharValueId
     */
    public void specifyDefaultCharacteristicValue(String specCharId, String defaultCharValueId) {
        // TODO - implement ProductSpecification.specifyDefaultCharacteristicValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param time
     */
    public ProductSpecCharUse[] retrieveCharacteristic(Date time) {
        // TODO - implement ProductSpecification.retrieveCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param specChar
     * @param time
     */
    public ProdSpecCharValueUse[] retrieveCharacteristicValue(ProductSpecCharacteristic specChar, Date time) {
        // TODO - implement ProductSpecification.retrieveCharacteristicValue
        throw new UnsupportedOperationException();
    }

    public ProductSpecCharUse[] getRootCharacteristic() {
        // TODO - implement ProductSpecification.getRootCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param specChar
     * @param time
     */
    public ProductSpecCharUse[] getLeafCharacteristic(ProductSpecCharacteristic specChar, Date time) {
        // TODO - implement ProductSpecification.getLeafCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param specCharId
     * @param time
     */
    public ProductSpecCharUse[] getLeafCharacteristic(String specCharId, Date time) {
        // TODO - implement ProductSpecification.getLeafCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param specChar
     * @param minCardinality
     * @param maxCardinality
     */
    public void setCardinality(ProductSpecCharacteristic specChar, int minCardinality, int maxCardinality) {
        // TODO - implement ProductSpecification.setCardinality
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param verType
     * @param curTypeVersion
     * @param description
     * @param revisionDate
     * @param validFor
     */
    private void setVersion(String verType, String curTypeVersion, String description, Date revisionDate, TimePeriod validFor) {
        // TODO - implement ProductSpecification.setVersion
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param version
     * @param description
     * @param revisionDate
     * @param validFor
     */
    public void setVersion(String version, String description, Date revisionDate, TimePeriod validFor) {
        // TODO - implement ProductSpecification.setVersion
        throw new UnsupportedOperationException();
    }

    public ProductSpecificationVersion[] getCurrentVersion() {
        // TODO - implement ProductSpecification.getCurrentVersion
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param majorVersion
     * @param description
     * @param revisionDate
     */
    public String upgradeMajorVersion(String majorVersion, String description, Date revisionDate) {
    	throw new UnsupportedOperationException();

    }

    /**
     * 
     * @param minorVersion
     * @param description
     * @param revisionDate
     */
    public String upgradeMinorVersion(String minorVersion, String description, Date revisionDate) {
        // TODO - implement ProductSpecification.upgradeMinorVersion
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param patchVersion
     * @param description
     * @param revisionDate
     */
    public String upgradePatchVersion(String patchVersion, String description, Date revisionDate) {
        // TODO - implement ProductSpecification.upgradePatchVersion
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param cost
     * @param validFor
     */
    public void addCost(Money cost, TimePeriod validFor) {
        // TODO - implement ProductSpecification.addCost
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param cost
     * @param validFor
     */
    public void updateCostPeriod(ProductSpecificationCost cost, TimePeriod validFor) {
        // TODO - implement ProductSpecification.updateCostPeriod
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param time
     */
    public ProductSpecificationCost[] queryCost(Date time) {
        // TODO - implement ProductSpecification.queryCost
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param prodSpec
     * @param type
     * @param validFor
     */
    public void addRelatedProdSpec(ProductSpecification prodSpec, String type, TimePeriod validFor) {
        // TODO - implement ProductSpecification.addRelatedProdSpec
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param prodSpecId
     * @param type
     * @param validFor
     */
    public void addRelatedProdSpec(String prodSpecId, String type, TimePeriod validFor) {
        // TODO - implement ProductSpecification.addRelatedProdSpec
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param prodSpec
     */
    public void removeRelatedProdSpec(ProductSpecification prodSpec) {
        // TODO - implement ProductSpecification.removeRelatedProdSpec
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param prodSpecId
     */
    public void removeRelatedProdSpec(String prodSpecId) {
        // TODO - implement ProductSpecification.removeRelatedProdSpec
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param type
     */
    public ProductSpecification[] queryRelatedProdSpec(String type) {
        // TODO - implement ProductSpecification.queryRelatedProdSpec
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param type
     * @param time
     */
    public ProductSpecification[] queryRelatedProdSpec(String type, Date time) {
        // TODO - implement ProductSpecification.queryRelatedProdSpec
        throw new UnsupportedOperationException();
    }

}