package com.ai.baas.product.spec;

import com.ai.baas.basetype.Money;
import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.enums.ProdSpecEnum;
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
        if (StringUtils.isNotEmpty(productNumber)) {
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
        if (StringUtils.isNotEmpty(productNumber)) {
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
    public boolean addCharacteristic(ProductSpecCharacteristic specChar, boolean canBeOveridden, boolean isPackage, TimePeriod validFor) {
        //the parameter of specChar is null
        checkProdSpecChar(specChar);
        //initialize set of ProductSpecCharUse
        initProdSpecCharUseSet();
        //the characteristic has been used under the specification, can't add characteristic again
        for (ProductSpecCharUse prodSpecCharUse : prodSpecChar) {
            if (prodSpecCharUse.getProdSpecChar().equals(specChar)) {
                return false;
            }
        }
        ProductSpecCharUse prodSpecCharUse = new ProductSpecCharUse(specChar, canBeOveridden, isPackage, validFor);
        prodSpecChar.add(prodSpecCharUse);
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
    public boolean addCharacteristic(ProductSpecCharacteristic specChar, boolean canBeOveridden, boolean isPackage, TimePeriod validFor, String name, String unique, int minCardinality, int maxCardinality, boolean extensible, String description) {
        //the parameter of specChar is null
        checkProdSpecChar(specChar);
        //initialize set of ProductSpecCharUse
        initProdSpecCharUseSet();
        //the characteristic has been used under the specification, can't add characteristic again
        for (ProductSpecCharUse prodSpecCharUse : prodSpecChar) {
            if (prodSpecCharUse.getProdSpecChar().equals(specChar)) {
                return false;
            }
        }
        //TODO list  Contance？？？
        /*if(prodSpecChar.contains(prodSpecCharUse)){
            return false;
    	}*/
        ProductSpecCharUse prodSpecCharUse = new ProductSpecCharUse(specChar, canBeOveridden, isPackage, validFor, name, unique, minCardinality, maxCardinality, extensible, description);
        prodSpecChar.add(prodSpecCharUse);
        return true;
    }

    /**
     * @param specChar A characteristic quality or distinctive feature of a ProductSpecification. The {@code ProductSpecification} must have the Characteristic before.
     */
    public boolean removeCharacteristic(ProductSpecCharacteristic specChar) {
        //the parameter of specChar is null
        checkProdSpecChar(specChar);
        boolean isExist = false;
        if (null != this.prodSpecChar) {
            for (ProductSpecCharUse prodSpecUse : prodSpecChar) {
                if (prodSpecUse.getProdSpecChar().equals(specChar)) {
                    isExist = true;
                    this.prodSpecChar.remove(prodSpecUse);
                    break;
                }
            }
        }
        //delete an Object which is not exist
        if (!isExist) {
            log.error("parameter is error ：the Object of ProductSpecCharacteristic is null, the value of parameter specChar = " + specChar);
            return false;
        }
        return true;
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
    public boolean modifyCharacteristicInfo(ProductSpecCharacteristic specChar, boolean canBeOveridden, boolean isPackage, TimePeriod validFor, String name, String unique, int minCardinality, int maxCardinality, boolean extensible, String description) {
        //the parameter of specChar is null
        checkProdSpecChar(specChar);
        if (null != this.prodSpecChar) {
            for (ProductSpecCharUse prodSpecCharUse : prodSpecChar) {
                if (prodSpecCharUse.getProdSpecChar().getID().equals(specChar.getID())) {
                    prodSpecCharUse.setCanBeOveridden(canBeOveridden);
                    prodSpecCharUse.setIsPackage(isPackage);
                    prodSpecCharUse.setName(name == null ? prodSpecCharUse.getName() : name);
                    prodSpecCharUse.setUnique(unique == null ? prodSpecCharUse.getUnique() : unique);
                    prodSpecCharUse.setExtensible(extensible);
                    prodSpecCharUse.setMaxCardinality(maxCardinality);
                    prodSpecCharUse.setMinCardinality(minCardinality);
                    prodSpecCharUse.setDescription(description == null ? prodSpecCharUse.getDescription() : description);
                }
            }
        }
        return true;
    }

    /**
     * @param specChar  A characteristic quality or distinctive feature of a ProductSpecification. The object must exist in the system
     * @param charValue A number or text that be assigned to a ProductSpecCharacteristic. The value must be in the characterisc's values.
     * @param isDefault Indicates if the value is the default value for a characteristic. true��is default value
     * @param validFor  The period of time for which the use of the CharacteristicValue is applicable.
     */
    public void attachCharacteristicValue(ProductSpecCharacteristic specChar, ProductSpecCharacteristicValue charValue, boolean isDefault, TimePeriod validFor) {
        //the parameter of specChar is null
        checkProdSpecChar(specChar);
        //judge charValue is null
        checkProdSpecCharValue(charValue);
        if (null != prodSpecChar && prodSpecChar.size() > 0) {
            for (ProductSpecCharUse prodSpecCharUse : this.prodSpecChar) {
                if (prodSpecCharUse.getProdSpecChar().equals(specChar)) {
                    prodSpecCharUse.addValue(charValue, isDefault, validFor);
                }
            }
        }
    }

    /**
     * @param specChar
     * @param charValue
     */
    public void detachCharacteristicValue(ProductSpecCharacteristic specChar, ProductSpecCharacteristicValue charValue) {
        //the parameter of specChar is null
        checkProdSpecChar(specChar);
        //judge charValue is null
        checkProdSpecCharValue(charValue);
        boolean charIsExist = false;
        if (null != prodSpecChar && prodSpecChar.size() > 0) {
            for (ProductSpecCharUse prodSpecCharUse : prodSpecChar) {
                if (prodSpecCharUse.getProdSpecChar().equals(specChar)) {
                    charIsExist = true;
                    prodSpecCharUse.removeValue(charValue);
                }
            }
            if (!charIsExist) {
                log.error("under the prodSpec didn't use the characteristic which the value will be used by characteristic");
            }
        }
    }

    /**
     * @param specChar
     * @param defaultCharValue
     */
    public void specifyDefaultCharacteristicValue(ProductSpecCharacteristic specChar, ProductSpecCharacteristicValue defaultCharValue) {
        //the parameter of specChar is null
        checkProdSpecChar(specChar);
        //judge charValue is null
        checkProdSpecCharValue(defaultCharValue);
        if (null != this.prodSpecChar) {
            for (ProductSpecCharUse prodSpecCharUse : prodSpecChar) {
                if (prodSpecCharUse.getProdSpecChar().equals(specChar)) {
                    prodSpecCharUse.specifyDefaultCharacteristicValue(defaultCharValue);
                }
            }
        }
    }

    /**
     * @param time
     */
    public ProductSpecCharUse[] retrieveCharacteristic(Date time) {
        Set<ProductSpecCharUse> prodSpecCharUseList = this.prodSpecChar;
        List<ProductSpecCharUse> prodSpecCharUseByDate = new ArrayList<ProductSpecCharUse>();

        if (null == time) {
            log.error("parameter is error ：time is null. ");
            throw new IllegalArgumentException("specChar should not be null .");
        }

        for (ProductSpecCharUse productSpecCharUse : prodSpecCharUseList) {
            TimePeriod validForTime = productSpecCharUse.getProdSpecChar().getValidFor();
            if (validForTime.isInTimePeriod(time) == 0) {
                prodSpecCharUseByDate.add(productSpecCharUse);
            }
        }
        return prodSpecCharUseByDate.toArray(new ProductSpecCharUse[prodSpecCharUseByDate.size()]);
    }

    /**
     * @param specChar
     * @param time
     */
    public ProdSpecCharValueUse[] retrieveCharacteristicValue(ProductSpecCharacteristic specChar, Date time) {
        Set<ProductSpecCharUse> prodSpecCharUseList = this.prodSpecChar;
        List<ProdSpecCharValueUse> prodSpecCharValueUseByDate = new ArrayList<ProdSpecCharValueUse>();
        if (null != prodSpecCharUseList && prodSpecCharUseList.size() > 0) {
            for (ProductSpecCharUse productSpecCharUse : prodSpecCharUseList) {
                if (productSpecCharUse.getProdSpecChar().equals(specChar)) {
                    if (productSpecCharUse.getProdSpecChar().getValidFor().isInTimePeriod(time) == 0) {
                        for (ProdSpecCharValueUse prodSpecCharValueUse : productSpecCharUse.getProdSpecCharValue()) {
                            if (prodSpecCharValueUse.getValidFor().isInTimePeriod(time) == 0) {
                                prodSpecCharValueUseByDate.add(prodSpecCharValueUse);
                            }
                        }
                    } else {
                        //the characteristics of the prodSpec isn't in the time period
                        return prodSpecCharValueUseByDate.toArray(new ProdSpecCharValueUse[prodSpecCharValueUseByDate.size()]);
                    }
                }
            }
        }
        return prodSpecCharValueUseByDate.toArray(new ProdSpecCharValueUse[prodSpecCharValueUseByDate.size()]);
    }

    /**
     * this operation is  convert ProductSpecCharacteristic type to ProductSpecCharUse type
     *
     * @param prodSpecCharN
     * @return
     */
    private ProductSpecCharUse getProdSpecCharUse(ProductSpecCharacteristic prodSpecCharN) {
        if (null != prodSpecChar && prodSpecChar.size() > 0) {
            for (ProductSpecCharUse prodSpecCharUse : prodSpecChar) {
                if (prodSpecCharUse.getProdSpecChar().equals(prodSpecCharN)) {
                    return prodSpecCharUse;
                }
            }
        }
        return null;
    }

    /**
     * @return
     */
    public Set<ProductSpecCharUse> getRootCharacteristic() {
        Set<ProductSpecCharUse> resultProdSpecCharUseList = new HashSet<ProductSpecCharUse>();
        resultProdSpecCharUseList = this.prodSpecChar;
        if (null != prodSpecChar && prodSpecChar.size() > 0) {
            for (ProductSpecCharUse prodSpecCharUse : prodSpecChar) {
                ProductSpecCharacteristic[] relatedChars = prodSpecCharUse.getProdSpecChar().retieveRelatedCharacteristic(ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue());
                if (null == relatedChars || relatedChars.length == 0) {
                    for (ProductSpecCharacteristic prodSpecChar : relatedChars) {
                        ProductSpecCharUse prodSpecCharSub = getProdSpecCharUse(prodSpecChar);
                        if (null != prodSpecCharSub) {
                            resultProdSpecCharUseList.remove(prodSpecCharSub);
                        }
                    }
                    resultProdSpecCharUseList.add(prodSpecCharUse);
                }
            }
        }
        return resultProdSpecCharUseList;
    }

    /**
     * @param specChar
     * @param time
     */
    public Set<ProductSpecCharUse> getLeafCharacteristic(ProductSpecCharacteristic specChar, Date time) {
        Set<ProductSpecCharUse> prodSpecCharUseList = this.prodSpecChar;
        Set<ProductSpecCharUse> resultProdSpecCharUseSet = new HashSet<ProductSpecCharUse>();
        if (null != prodSpecCharUseList && prodSpecCharUseList.size() > 0) {
            /*for (int i = 0; i < prodSpecCharUseList.size(); i++) {
                if(prodSpecCharUseList.get(i).getProdSpecChar().getID().equals(specChar.getID())){
    				ProductSpecCharacteristic[] relatedChars = prodSpecCharUseList.get(i).getProdSpecChar().queryRelatedCharacteristic(ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue());
        			if(null != relatedChars && relatedChars.length > 0){
        				resultProdSpecCharUseList.add(prodSpecCharUseList.get(i));
        			}
    			}
			}*/
        }
        return resultProdSpecCharUseSet;
    }

    /**
     * @param specChar
     * @param minCardinality
     * @param maxCardinality
     */
    public boolean setCardinality(ProductSpecCharacteristic specChar, int minCardinality, int maxCardinality) {
        checkProdSpecChar(specChar);
        int rtnNum = NumberUtil.compareTheNumber(minCardinality, maxCardinality);
        if (rtnNum == 1) {
            log.error("minCardinality should be less than or equal to  maxCardinality.");
            throw new IllegalArgumentException("the parameter of verType is error.");
        }
        if (null != prodSpecChar && prodSpecChar.size() > 0) {
            for (ProductSpecCharUse prodSpecCharUse : prodSpecChar) {
                if (prodSpecCharUse.getProdSpecChar().equals(specChar)) {
                    prodSpecCharUse.setMinCardinality(minCardinality);
                    prodSpecCharUse.setMaxCardinality(maxCardinality);
                }
            }
        }
        return true;
    }

    /**
     * @param verType
     * @param curTypeVersion
     * @param description
     * @param revisionDate
     * @param validFor
     */
    private void setVersion(String verType, String curTypeVersion, String description, Date revisionDate, TimePeriod validFor) {
        //TODO Do not do version and related methods
        if (null == verType || "".equals(verType)) {
            log.error("the parameter of verType is null. ");
            throw new IllegalArgumentException("the parameter of verType is error.");
        }
        if (null == curTypeVersion || "".equals(curTypeVersion)) {
            log.error("the parameter of curTypeVersion is null. ");
            throw new IllegalArgumentException("the parameter of curTypeVersion is error.");
        }
        if (null == validFor) {
            log.error("the parameter of validFor is null. ");
            throw new IllegalArgumentException("the parameter of validFor is error.");
        }
        ProductSpecificationVersion versi = new ProductSpecificationVersion(verType, description, curTypeVersion, revisionDate, validFor);
        if (prodSpecVersion == null) {
            prodSpecVersion = new ArrayList<ProductSpecificationVersion>();
        }
        if (null != this.getProdSpecVersion()) {
            for (int i = 0; i < getProdSpecVersion().size(); i++) {
                if (getProdSpecVersion().get(i).equals(versi)) {
                    return;
                }
            }
        }
        prodSpecVersion.add(versi);
    }

    /**
     * @param version
     * @param description
     * @param revisionDate
     * @param validFor
     * @throws Exception
     */
    public void setVersion(String version, String description, Date revisionDate, TimePeriod validFor) throws Exception {
        //TODO Do not do version and related methods
        if (version != null && !"".equals(version)) {
            String[] vos = version.split("\\.");
            if (vos.length != 3) {
                log.error("the format of version is not correct");
                throw new IllegalArgumentException("the parameter of version is error.");
            } else {
                setVersion(ProdSpecEnum.VersionLevel.MAJOR_VERSION.getValue(), vos[0], description, revisionDate, validFor);
                setVersion(ProdSpecEnum.VersionLevel.MINOR_VERSION.getValue(), vos[1], description, revisionDate, validFor);
                setVersion(ProdSpecEnum.VersionLevel.PATCH_VERSION.getValue(), vos[2], description, revisionDate, validFor);
            }
        }
    }

    public ProductSpecificationVersion[] getCurrentVersion() {
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
        ProductSpecificationCost prodSpecCost = new ProductSpecificationCost(cost, validFor);
        this.getProductSpecificationCost().add(prodSpecCost);
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
    public ProductSpecificationCost[] queryCost(Date time) {
        //TODO Do not do Cost and related methods
        ProductSpecificationCost[] prodSpecCosts = null;
        return prodSpecCosts;
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
        if (null == prodSpec) {
            log.error("the object of ProductSpecification is null");
            throw new IllegalArgumentException("prodSpec should not be null .");
        }
        if (null == this.prodSpecRelationship) {
            log.error("prodSpecRelationship is null");
            throw new IllegalArgumentException("prodSpecRelationship should not be null .");
        }
        this.prodSpecRelationship.remove(prodSpec);
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
    public void checkProdSpecCharValue(ProductSpecCharacteristicValue charValue) {
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
}
