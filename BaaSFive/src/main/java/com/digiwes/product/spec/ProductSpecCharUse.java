package com.digiwes.product.spec;

import java.util.*;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.util.CommonUtils;
import org.apache.log4j.Logger;

public class ProductSpecCharUse {
    private static final Logger logger = Logger.getLogger(ProductSpecCharUse.class);

    private ProductSpecCharacteristic prodSpecChar;
    private List<ProdSpecCharValueUse> prodSpecCharValue;
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
     * <p/>
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

    private TimePeriod validFor;

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

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * @param specChar
     * @param canBeOveridden
     * @param isPackage
     * @param validFor
     */
    public ProductSpecCharUse(ProductSpecCharacteristic specChar, boolean canBeOveridden, boolean isPackage, TimePeriod validFor, String name) {
       if(CommonUtils.checkParamIsNull(specChar) || CommonUtils.checkParamIsNull(name) ) {

       }
        this.prodSpecChar = specChar;
        this.name = name;
        this.canBeOveridden = canBeOveridden;
        this.isPackage = isPackage;
    }


    /**
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
        checkProdSpecChar(specChar);
        if (null == name || "".equals(name)) {
            logger.error("parameter name is error ��the parameter is null . ");
            throw new IllegalArgumentException("name should not be null .");
        }
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
     * @param minCardinality
     * @param maxCardinality
     */
    public void specifyCardinality(int minCardinality, int maxCardinality) {
        if (minCardinality <= maxCardinality) {
            this.minCardinality = minCardinality;
            this.maxCardinality = maxCardinality;
        } else {
            logger.error("minCardinality is greater than maxCardinality .");
            throw new IllegalArgumentException("minCardinality is greater than maxCardinality .");
        }
    }

    /**
     * @param charValue
     * @param isDefault
     * @param validFor
     */
    public void addValue(ProductSpecCharacteristicValue charValue, boolean isDefault, TimePeriod validFor) {
        CommonUtils.checkParamIsNull(charValue);

        if (prodSpecChar.getProdSpecCharValue() != null && prodSpecChar.getProdSpecCharValue().contains(charValue)) {
            ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue, isDefault, validFor);
            if (null == this.prodSpecCharValue) {
                this.prodSpecCharValue = new ArrayList<ProdSpecCharValueUse>();
            }
            if (!prodSpecCharValue.contains(charValueUse)) {
                this.prodSpecCharValue.add(charValueUse);
            } else {
                logger.warn("The charValue is already in use . ");
            }
        } else {
            logger.warn("The charValue isn't belong to the characteristic .");
        }
    }


    /**
     * @param charValue
     */
    public void removeValue(ProductSpecCharacteristicValue charValue) {
        // TODO - implement ProductSpecCharUse.removeValue
    }


    /**
     * @param defaultValue
     */
    public void specifyDefaultCharacteristicValue(ProductSpecCharacteristicValue defaultValue) {
        CommonUtils.checkParamIsNull(defaultValue);
        if (null != this.prodSpecCharValue) {
            ProdSpecCharValueUse valueUse = this.retrieveProdSpecCharValueUse(defaultValue);
            if (null != valueUse) {
                valueUse.setIsDefault(true);
            } else {
                logger.warn("Parameter characteristicValue is not used");
            }
        } else {
            logger.warn("There is no used characteristicValue ");
        }
    }

    /**
     * get parameter charValue is used
     *
     * @param charValue
     * @return
     */
    private ProdSpecCharValueUse retrieveProdSpecCharValueUse(ProductSpecCharacteristicValue charValue) {
        for (ProdSpecCharValueUse valueUse : prodSpecCharValue) {
            if (valueUse.getProdSpecCharValue().equals(charValue)) {
                return valueUse;
            }
        }
        return null;
    }

    /**
     * check parameter is null
     */
    private void checkProdSpecChar(ProductSpecCharacteristic prodSpecChar) {
        if (null == prodSpecChar) {
            logger.error("parameter is error :the Object of ProductSpecCharacteristic is null . ");
            throw new IllegalArgumentException("specChar should not be null .");
        }
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((null == this.name) ? 0 : this.name.hashCode());
        result = prime * result
                + ((null == this.prodSpecChar) ? 0 : this.prodSpecChar.hashCode());
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
        ProductSpecCharUse other = (ProductSpecCharUse) obj;
        if (null == this.name) {
            if (null != other.name)
                return false;
        } else if (!this.name.equals(other.name))
            return false;
        if (null == this.prodSpecChar) {
            if (null != other.prodSpecChar)
                return false;
        } else if (!this.prodSpecChar.equals(other.prodSpecChar))
            return false;
        return true;
    }

    public Map<String, Object> getBasicInfoToMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("name", this.name);
        result.put("description", this.description);
        result.put("unique", this.unique);
        result.put("isPackage", this.isPackage);
        result.put("canBeOveridden", this.canBeOveridden);
        result.put("minCardinality", this.minCardinality);
        result.put("maxCardinality", this.maxCardinality);
        result.put("extensible", this.extensible);
        result.put("validFor", this.validFor);
        return result;
    }

}