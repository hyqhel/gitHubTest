package com.ai.baas.product.spec;

import com.ai.baas.basetype.Money;
import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.enums.ProdSpecEnum;
import com.ai.baas.common.util.CommonUtils;
import com.ai.baas.common.util.NumberUtil;
import com.ai.baas.product.offering.SimpleProductOffering;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * A detailed description of a tangible or intangible object made available externally in the form of a ProductOffering to Customers or other Parties playing a PartyRole. A ProductSpecification may consist of other ProductSpecifications supplied together as a collection. Members of the collection may be offered in their own right. ProductSpecifications may also exist within groupings, such as ProductCategories, ProductLines, and ProductTypes.
 */
public abstract class ProductSpecification {
    private static final Logger log = Logger.getLogger(ProductSpecification.class);

    public List<ProductSpecificationCost> productSpecificationCost;
    public List<ProductSpecificationRelationship> prodSpecRelationship;
    public List<ProductSpecificationVersion> prodSpecVersion;
    public Set<ProductSpecCharUse> prodSpecChar;
    public List<CompositeProductSpecification> compositeProdSpec;
    public List<SimpleProductOffering> simpleProdOffering;

    public List<ProductSpecificationCost> getProductSpecificationCost() {
        return productSpecificationCost;
    }

    public List<ProductSpecificationVersion> getProdSpecVersion() {
        return prodSpecVersion;
    }

    public Set<ProductSpecCharUse> getProdSpecChar() {
        return prodSpecChar;
    }


    public List<ProductSpecificationRelationship> getProdSpecRelationship() {
        return prodSpecRelationship;
    }

    public void setProdSpecRelationship(
            List<ProductSpecificationRelationship> prodSpecRelationship) {
        this.prodSpecRelationship = prodSpecRelationship;
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
     *
     * @param name          The name of the product specification.
     * @param productNumber An identification number assigned to uniquely identify the specification.
     * @param brand         The manufacturer or trademark of the specification.
     */
    public ProductSpecification(String productNumber, String name, String brand) {
        if (StringUtils.isEmpty(productNumber)) {
            log.error("The parameter " + productNumber + " is null");
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.productNumber = productNumber;
        this.brand = brand;
        this.lifecycleStatus = ProdSpecEnum.ProdSpecStatus.STATUS_ACTIVE.getValue();
    }

    /**
     * Initializes a newly created {@code ProductSpecification} object so that it represents the all information. When the specification is a new one, the state of the specification will be initialized as "planned"
     *
     * @param name          The name of the product specification.
     * @param productNumber An identification number assigned to uniquely identify the specification.
     * @param brand         The manufacturer or trademark of the specification.
     * @param validFor      The period of time for which the use of the ProductSpecification is applicable.
     * @param description   A narrative that explains in detail what the product spec is.
     */
    public ProductSpecification(String productNumber, String name, String brand, TimePeriod validFor, String description) {
        if (StringUtils.isEmpty(productNumber)) {
            log.error("The parameter " + productNumber + " is null");
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.productNumber = productNumber;
        this.brand = brand;
        this.validFor = validFor;
        this.description = description;
        this.lifecycleStatus = ProdSpecEnum.ProdSpecStatus.STATUS_ACTIVE.getValue();
    }

    /**
     * @param specChar       A characteristic quality or distinctive feature of a ProductSpecification. The object must exist in the system
     * @param canBeOveridden An indicator that specifies that the CharacteristicSpecValues associated with the CharacteristicSpec cannot be changed when instantiating a ServiceCharacteristicValue. For example, a bandwidth of 64 MB cannot be changed.
     * @param isPackage      An indicator that specifies if the associated CharacteristicSpecification is a composite. true��is a composite one
     * @param validFor       The period of time for which the use of the CharacteristicSpecification is applicable.
     */
    public boolean addCharacteristic(String name, ProductSpecCharacteristic specChar, boolean canBeOveridden, boolean isPackage, TimePeriod validFor) {
        //the parameter of specChar is null
        checkProdSpecChar(specChar);
        validNull(name);
        //initialize set of ProductSpecCharUse
        initProdSpecCharUseSet();
        //the characteristic has been used under the specification, can't add characteristic again
        if (null == retrieveProdSpecCharUse(name, specChar)) {
            ProductSpecCharUse prodSpecCharUse = new ProductSpecCharUse(specChar, canBeOveridden, isPackage, validFor, name);
            prodSpecChar.add(prodSpecCharUse);
        } else {
            log.warn("the characteristic is already in use. ");
            return false;
        }
        return true;
    }

    /**
     * @param specChar       A characteristic quality or distinctive feature of a ProductSpecification. The object must exist in the system
     * @param canBeOveridden An indicator that specifies that the CharacteristicSpecValues associated with the CharacteristicSpec cannot be changed when instantiating a ServiceCharacteristicValue. For example, a bandwidth of 64 MB cannot be changed.
     * @param isPackage      An indicator that specifies if the associated CharacteristicSpecification is a composite.
     * @param validFor       The period of time for which the use of the CharacteristicSpecification is applicable.
     * @param name           A word, term, or phrase by which the CharacteristicSpecification is known and distinguished from other CharacteristicSpecifications.
     * @param unique         An indicator that specifies if a value is unique for the specification. Possible values are: "unique while value is in effect" and "unique whether value is in effect or not"
     * @param minCardinality The minimum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where zero is the value for the minCardinality.
     * @param maxCardinality The maximum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where five is the value for the maxCardinality.
     * @param extensible     An indicator that specifies that the values for the characteristic can be extended by adding new values when instantiating a characteristic for a Service.
     * @param description    A narrative that explains the CharacteristicSpecification.
     */
    public boolean addCharacteristic(String name, ProductSpecCharacteristic specChar, boolean canBeOveridden, boolean isPackage, TimePeriod validFor, String unique, int minCardinality, int maxCardinality, boolean extensible, String description) {
        //the parameter of specChar is null
        checkProdSpecChar(specChar);
        validNull(name);
        //initialize set of ProductSpecCharUse
        initProdSpecCharUseSet();
        //the characteristic has been used under the specification, can't add characteristic again
        if (null == retrieveProdSpecCharUse(name, specChar)) {
            ProductSpecCharUse prodSpecCharUse = new ProductSpecCharUse(specChar, canBeOveridden, isPackage, validFor, name, unique, minCardinality, maxCardinality, extensible, description);
            prodSpecChar.add(prodSpecCharUse);
        } else {
            log.warn("the characteristic is already in use. ");
            return false;
        }
        return true;
    }

    /**
     * @param specChar A characteristic quality or distinctive feature of a ProductSpecification. The {@code ProductSpecification} must have the Characteristic before.
     */
    public boolean removeCharacteristic(String name, ProductSpecCharacteristic specChar) {
        // TODO - implement ProductSpecCharUse.removeValue
        return false;
    }

    /**
     * @param specChar       A characteristic quality or distinctive feature of a ProductSpecification. The {@code ProductSpecification} must have the Characteristic.
     * @param canBeOveridden An indicator that specifies that the CharacteristicSpecValues associated with the CharacteristicSpec cannot be changed when instantiating a ServiceCharacteristicValue. For example, a bandwidth of 64 MB cannot be changed.
     * @param isPackage      An indicator that specifies if the associated CharacteristicSpecification is a composite.
     * @param validFor       The period of time for which the use of the CharacteristicSpecification is applicable.
     * @param name           A word, term, or phrase by which the CharacteristicSpecification is known and distinguished from other CharacteristicSpecifications.
     * @param unique         An indicator that specifies if a value is unique for the specification. Possible values are: "unique while value is in effect" and "unique whether value is in effect or not"
     * @param minCardinality The minimum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where zero is the value for the minCardinality.
     * @param maxCardinality The maximum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where five is the value for the maxCardinality.
     * @param extensible     An indicator that specifies that the values for the characteristic can be extended by adding new values when instantiating a characteristic for a Service.
     * @param description    A narrative that explains the CharacteristicSpecification.
     */
    public boolean modifyCharacteristicInfo(String charUseName, ProductSpecCharacteristic specChar, boolean canBeOveridden, boolean isPackage, TimePeriod validFor, String name, String unique, int minCardinality, int maxCardinality, boolean extensible, String description) {
        // TODO - implement ProductSpecCharUse.removeValue
        return false;
    }

    /**
     * @param specChar  A characteristic quality or distinctive feature of a ProductSpecification. The object must exist in the system
     * @param charValue A number or text that be assigned to a ProductSpecCharacteristic. The value must be in the characterisc's values.
     * @param isDefault Indicates if the value is the default value for a characteristic. true��is default value
     * @param validFor  The period of time for which the use of the CharacteristicValue is applicable.
     */
    public void attachCharacteristicValue(String name, ProductSpecCharacteristic specChar, ProductSpecCharacteristicValue charValue, boolean isDefault, TimePeriod validFor) {
        //the parameter of specChar is null
        checkProdSpecChar(specChar);
        //judge charValue is null
        checkCharValue(charValue);
        validNull(name);
        if (this.prodSpecChar != null) {
            ProductSpecCharUse charUse = this.checkCharIsUse(name, specChar);
            if (null != specChar.getProdSpecCharValue()
                    && specChar.getProdSpecCharValue().contains(charValue)) {
                charUse.addValue(charValue, isDefault, validFor);
            } else {
                log.warn("Parameter characteristicValue is not belong to this characteristic ");
            }
        }
    }

    private void validNull(String name) {
        if (null == name || "".equals(name)) {
            log.error("parameter is error ：the parameter name is null. ");
            throw new IllegalArgumentException("name should not be null .");
        }
    }

    /**
     * @param specChar
     * @param charValue
     */
    public void detachCharacteristicValue(String name, ProductSpecCharacteristic specChar, ProductSpecCharacteristicValue charValue) {
        // TODO - implement ProductSpecCharUse.removeValue
    }

    /**
     * @param specChar
     * @param defaultCharValue
     */
    public void specifyDefaultCharacteristicValue(String name, ProductSpecCharacteristic specChar, ProductSpecCharacteristicValue defaultCharValue) {
        //the parameter of specChar is null
        checkProdSpecChar(specChar);
        //judge charValue is null
        checkCharValue(defaultCharValue);
        validNull(name);
        if (null != this.prodSpecChar) {
            ProductSpecCharUse charUse = this.checkCharIsUse(name, specChar);
            if (null != specChar.getProdSpecCharValue()
                    && specChar.getProdSpecCharValue().contains(defaultCharValue)) {
                charUse.specifyDefaultCharacteristicValue(defaultCharValue);
            } else {
                log.warn("Parameter characteristicValue is not belong to this characteristic ");
            }
        }
    }

    /**
     * @param time
     */
    public List<ProductSpecCharUse> retrieveCharacteristic(Date time) {
        List<ProductSpecCharUse> prodSpecCharUseByDate = new ArrayList<ProductSpecCharUse>();
        if (null == time) {
            log.error("parameter is error ：time is null. ");
            throw new IllegalArgumentException("specChar should not be null .");
        }
        for (ProductSpecCharUse productSpecCharUse : prodSpecChar) {
            TimePeriod validForTime = productSpecCharUse.getProdSpecChar().getValidFor();
            if (0 == validForTime.isInTimePeriod(time)) {
                prodSpecCharUseByDate.add(productSpecCharUse);
            }
        }
        return prodSpecCharUseByDate;
    }

    /**
     * @param specChar
     * @param time
     */
    public List<ProdSpecCharValueUse> retrieveCharacteristicValue(String name, ProductSpecCharacteristic specChar, Date time) {
        //the parameter of specChar is null
        checkProdSpecChar(specChar);
        validNull(name);
        List<ProdSpecCharValueUse> prodSpecCharValueUseByDate = new ArrayList<ProdSpecCharValueUse>();
        ProductSpecCharUse charUse = this.checkCharIsUse(name, specChar);
        List<ProdSpecCharValueUse> valueUseAllList = charUse.getProdSpecCharValue();
        if (null != valueUseAllList) {
            for (ProdSpecCharValueUse charValueUse : valueUseAllList) {
                TimePeriod validForTime = charValueUse.getValidFor();
                if (0 == validForTime.isInTimePeriod(time)) {
                    prodSpecCharValueUseByDate.add(charValueUse);
                }
            }
        } else {
            log.warn("The characteristic haven't charValue in use");
        }
        return prodSpecCharValueUseByDate;
    }


    /**
     * @return
     */
    public List<ProductSpecCharUse> retrieveRootCharacteristic() {
        List<ProductSpecCharUse> charUseList = new ArrayList<ProductSpecCharUse>();
        if (null != this.prodSpecChar) {
            charUseList.addAll(this.prodSpecChar);
            for (ProductSpecCharUse charUse : this.prodSpecChar) {
                List<ProductSpecCharacteristic> subProdSpecChar = charUse.getProdSpecChar().retrieveRelatedCharacteristic(
                        ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue());
                if (null != subProdSpecChar) {
                    for (ProductSpecCharacteristic specChar : subProdSpecChar) {
                        ProductSpecCharUse subCharUse = this.retrieveProdSpecCharUse(charUse.getName(), specChar);
                        if (null != subCharUse) {
                            charUseList.remove(subCharUse);
                        }
                    }
                }
            }
        }
        return charUseList;
    }

    /**
     * @param specChar
     * @param time
     */
    public List<ProductSpecCharUse> retrieveLeafCharacteristic(String name, ProductSpecCharacteristic specChar, Date time) {
        checkProdSpecChar(specChar);
        validNull(name);
        List<ProductSpecCharUse> charUses = new ArrayList<ProductSpecCharUse>();
        List<ProductSpecCharacteristic> subProdSpecChar = null;
        if (null == time)
            subProdSpecChar = specChar.retrieveRelatedCharacteristic(
                    ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue());
        else
            subProdSpecChar = specChar.retrieveRelatedCharacteristic(
                    ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), time);
        if (null != subProdSpecChar) {
            for (ProductSpecCharacteristic subspecChar : subProdSpecChar) {
                ProductSpecCharUse charUse = this.retrieveProdSpecCharUse(name, subspecChar);
                if (null != charUse) {
                    charUses.add(charUse);
                }
            }
        }
        return charUses;
    }

    /**
     * @param specChar
     * @param minCardinality
     * @param maxCardinality
     */
    public boolean specifyCardinality(String name, ProductSpecCharacteristic specChar, int minCardinality, int maxCardinality) {
        checkProdSpecChar(specChar);
        validNull(name);
        ProductSpecCharUse charUse = this.retrieveProdSpecCharUse(name, specChar);
        if (null != charUse) {
            charUse.specifyCardinality(minCardinality, maxCardinality);
            return true;
        } else {
            log.warn("Parameter characteristic is not used");
            return false;
        }
    }

    /**
     * @param verType
     * @param curTypeVersion
     * @param description
     * @param revisionDate
     * @param validFor
     */
    private void specifyVersion(String verType, String curTypeVersion, String description, Date revisionDate, TimePeriod validFor) {
        //TODO Do not do version and related methods
    }

    /**
     * @param version
     * @param description
     * @param revisionDate
     * @param validFor
     * @throws Exception
     */
    public void specifyVersion(String version, String description, Date revisionDate, TimePeriod validFor) throws Exception {
        //TODO Do not do version and related methods
    }

    public List<ProductSpecificationVersion> retrieveCurrentVersion() {
        //TODO Do not do version and related methods
        return null;
    }

    /**
     * @param majorVersion
     * @param description
     * @param revisionDate
     */
    public String upgradeMajorVersion(String majorVersion, String description, Date revisionDate) {
        //TODO Do not do version and related methods
        return "";
    }

    /**
     * @param minorVersion
     * @param description
     * @param revisionDate
     */
    public String upgradeMinorVersion(String minorVersion, String description, Date revisionDate) {
        //TODO Do not do version and related methods
        return "";
    }

    /**
     * @param patchVersion
     * @param description
     * @param revisionDate
     */
    public String upgradePatchVersion(String patchVersion, String description, Date revisionDate) {
        //TODO Do not do version and related methods
        return null;
    }

    /**
     * @param cost
     * @param validFor
     */
    public void addCost(Money cost, TimePeriod validFor) {
        //TODO Do not do Cost and related methods
    }

    /**
     * @param cost
     * @param validFor
     */
    public void updateCostPeriod(ProductSpecificationCost cost, TimePeriod validFor) {
        //TODO Do not do Cost and related methods
    }

    /**
     * @param time
     */
    public List<ProductSpecificationCost> retrieveCost(Date time) {
        //TODO Do not do Cost and related methods
        return null;
    }

    /**
     * @param prodSpec
     * @param type
     * @param validFor
     */
    public void addRelatedProdSpec(ProductSpecification prodSpec, String type, TimePeriod validFor) {
        if (null == this.prodSpecRelationship) {
            this.prodSpecRelationship = new ArrayList<ProductSpecificationRelationship>();
        }
        if (null == prodSpec) {
            throw new IllegalArgumentException("Parameter [prodSpec] cannot be null.");
        }
        if (null == type) {
            throw new IllegalArgumentException("Parameter [type] cannot be null. ProductNumber="
                    + prodSpec.getProductNumber() + "type=" + type);
        }
        if (this.equals(prodSpec)) {
            log.error("Cannot add relationship with it self!");
            throw new IllegalArgumentException("Cannot add relationship with it self!");
        }
        ProductSpecificationRelationship productSpecificationRelationship = new ProductSpecificationRelationship(this,
                prodSpec, type, validFor);
        if (this.prodSpecRelationship.contains(productSpecificationRelationship)) {
            log.error("the relationship already exist, Cannot repeatedly create relationship by the same type. ProductNumber="
                    + prodSpec.getProductNumber() + "type=" + type);
            throw new IllegalArgumentException(
                    "the relationship already exist, Cannot repeatedly create relationship by the same type.");
        }
        this.prodSpecRelationship.add(productSpecificationRelationship);
    }

    /**
     * @param prodSpec
     */
    public void removeRelatedProdSpec(ProductSpecification prodSpec) {
        //TODO Do not do remove and related methods

    }

    /**
     * @param type
     */
    public List<ProductSpecification> retrieveRelatedProdSpec(String type) {
        List<ProductSpecification> productSpecifications = new ArrayList<ProductSpecification>();

        if (StringUtils.isEmpty(type)) {
            throw new IllegalArgumentException("Parameter [prodSpec] cannot be null.");
        }
        if (null != this.prodSpecRelationship) {
            Iterator<ProductSpecificationRelationship> iterator = this.prodSpecRelationship.iterator();
            while (iterator.hasNext()) {
                ProductSpecificationRelationship productSpecRelationship = iterator.next();
                if (type.equals(productSpecRelationship.getType())) {
                    productSpecifications.add(productSpecRelationship.getTargetProdSpec());
                }
            }
        }
        return productSpecifications;
    }

    /**
     * @param type
     * @param time
     */
    public List<ProductSpecification> retrieveRelatedProdSpec(String type, Date time) {
        List<ProductSpecification> rtnResultRelations = new ArrayList<ProductSpecification>();
        if (null != this.prodSpecRelationship) {
            if (null == time) {
                for (ProductSpecificationRelationship prodSpecRelate : this.prodSpecRelationship) {
                    if (prodSpecRelate.getType().equals(type)) {
                        rtnResultRelations.add(prodSpecRelate.getTargetProdSpec());
                    }
                }
            } else {
                for (ProductSpecificationRelationship prodSpecRelate : this.prodSpecRelationship) {
                    if (prodSpecRelate.getValidFor().isInTimePeriod(time) == 0 && prodSpecRelate.getType().equals(type)) {
                        rtnResultRelations.add(prodSpecRelate.getTargetProdSpec());
                    }
                }
            }
        }
        return rtnResultRelations;
    }

    private ProductSpecCharUse retrieveProdSpecCharUse(String name, ProductSpecCharacteristic characteristic) {
        if (null != this.prodSpecChar) {
            for (ProductSpecCharUse charUse : this.prodSpecChar) {
                if (characteristic.equals(charUse.getProdSpecChar()) && charUse.getName().equals(name)) {
                    return charUse;
                }
            }
        }
        return null;
    }

    private ProductSpecCharUse checkCharIsUse(String name, ProductSpecCharacteristic characteristic) {
        ProductSpecCharUse charUse = this.retrieveProdSpecCharUse(name, characteristic);
        if (null == charUse) {
            log.error("Parameter characteristic is not used ");
            throw new IllegalArgumentException();
        } else {
            return charUse;
        }
    }

    /**
     * initialize set of ProductSpecCharUse
     */
    private void initProdSpecCharUseSet() {
        if (null == prodSpecChar) {
            prodSpecChar = new HashSet<ProductSpecCharUse>();
        }
    }

    /**
     * check parameter is null
     */
    public void checkProdSpecChar(ProductSpecCharacteristic prodSpecChar) {
        if (null == prodSpecChar) {
            log.error("parameter is error ：the Object of ProductSpecCharacteristic is null. ");
            throw new IllegalArgumentException("specChar should not be null .");
        }
    }

    /**
     * check parameter is null
     */
    public void checkCharValue(ProductSpecCharacteristicValue charValue) {
        if (null == charValue) {
            log.error("parameter is error ：the Object of ProductSpecCharacteristicValue is null. ");
            throw new IllegalArgumentException("charValue should not be null .");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductSpecification that = (ProductSpecification) o;

        if (prodSpecVersion != null ? !prodSpecVersion.equals(that.prodSpecVersion) : that.prodSpecVersion != null)
            return false;
        return productNumber.equals(that.productNumber);

    }

    @Override
    public int hashCode() {
        int result = prodSpecVersion != null ? prodSpecVersion.hashCode() : 0;
        result = 31 * result + productNumber.hashCode();
        return result;
    }

    public Map<String, Object> getProductInfoToMap() {
        Map<String, Object> result = getBasicInfoToMap();
        result.put("prodSpecCharRelationship", this.prodSpecRelationship);
        List<Map<String, Object>> prodSpecCharUseList = new ArrayList<Map<String, Object>>();
        if (this.prodSpecChar != null && this.prodSpecChar.size() > 0) {
            for (ProductSpecCharUse productSpecCharUse : this.prodSpecChar) {
                Map<String, Object> productSpecCharUseMap = productSpecCharUse.getBasicInfoToMap();
                ProductSpecCharacteristic specChar = productSpecCharUse.getProdSpecChar();
                Map<String, Object> specCharMap = specChar.getBasicInfoToMap();
                productSpecCharUseMap.put("prodSpecChar", specCharMap);

                List<Map<String, Object>> prodSpecCharValueUseList = new ArrayList<Map<String, Object>>();
                if (null != productSpecCharUse.getProdSpecCharValue()) {
                    for (ProdSpecCharValueUse valueUse : productSpecCharUse.getProdSpecCharValue()) {
                        Map<String, Object> productSpecCharValueUseMap = valueUse.getBasicInfoToMap();
                        productSpecCharValueUseMap.put("prodSpecCharValue", valueUse.getProdSpecCharValue()
                                .getBasicInfoToMap());
                        prodSpecCharValueUseList.add(productSpecCharValueUseMap);
                    }
                }
                productSpecCharUseMap.put("prodSpecCharValueUse", prodSpecCharValueUseList);
                prodSpecCharUseList.add(productSpecCharUseMap);
            }
        }
        result.put("prodSpecCharUse", prodSpecCharUseList);

        return result;
    }

    public Map<String, Object> getBasicInfoToMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("productNumber", productNumber);
        result.put("name", name);
        result.put("brand", brand);
        result.put("description", description);
        result.put("validFor", validFor);
        result.put("lifecycleStatus", ProdSpecEnum.ProdSpecStatus.getName(lifecycleStatus));
        return result;
    }
}
