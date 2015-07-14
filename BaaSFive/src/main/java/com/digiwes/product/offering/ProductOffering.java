package com.digiwes.product.offering;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.enums.ProdOfferingEnum;
import com.digiwes.common.util.CommonUtils;
import com.digiwes.product.offering.catalog.ProdCatalogProdOffer;
import com.digiwes.product.offering.price.ProductOfferingPrice;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * The presentation of one or more ProductSpecifications to the marketplace for sale, rental, or lease for a ProductOfferingPrice. A ProductOffering may target one or more MarketSegments, be included in one or more ProductCatalog, presented in support of one or more ProductStrategies, and made available in one or more Places. ProductOffering may represent a simple offering of a single ProductSpecification or could represent a bundling of one or more other ProductOffering.
 */
public abstract class ProductOffering {

    private static Logger logger = Logger.getLogger(ProductOffering.class);

    private List<ProductOfferingPrice> productOfferingPrice;
    private List<ProductOfferingRelationship> prodOfferingRelationship;
    private List<ProdCatalogProdOffer> prodCatalogProdOffer;

    public List<ProductOfferingPrice> getProductOfferingPrice() {
        return productOfferingPrice;
    }

    public void setProductOfferingPrice(List<ProductOfferingPrice> productOfferingPrice) {
        this.productOfferingPrice = productOfferingPrice;
    }

    public List<ProductOfferingRelationship> getProdOfferingRelationship() {
        return prodOfferingRelationship;
    }

    public void setProdOfferingRelationship(List<ProductOfferingRelationship> prodOfferingRelationship) {
        this.prodOfferingRelationship = prodOfferingRelationship;
    }

    public List<ProdCatalogProdOffer> getProdCatalogProdOffer() {
        return prodCatalogProdOffer;
    }

    public void setProdCatalogProdOffer(List<ProdCatalogProdOffer> prodCatalogProdOffer) {
        this.prodCatalogProdOffer = prodCatalogProdOffer;
    }

    /**
     * A unique identifier for the ProductOffering.
     */
    private String id;
    /**
     * A word, term, or phrase by which the ProductOffeirng is known and distinguished from other ProductOfferings.
     */
    private String name;
    /**
     * A narrative that explains what the offering is.
     */
    private String description;
    /**
     * The period during which the offering is applicable.
     */
    private TimePeriod validFor;
    /**
     * The condition in which the offering exists, such as planned, obsolete, active
     */
    private String status;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public String getStatus() {
        return this.status;
    }

    /**
     * @param id
     * @param name
     * @param description
     * @param validFor
     */
    public ProductOffering(String id, String name, String description, TimePeriod validFor) {
        assert StringUtils.isNotEmpty(id) : "Param [id] must be not null!";

        this.id = id;
        this.name = name;
        this.description = description;
        this.validFor = validFor;
        this.status = ProdOfferingEnum.ProductOfferingStatus.PLANNED.getValue();
    }

    /**
     * @param offering
     * @param relationType
     * @param validFor
     */
    public void addRelatedOffering(ProductOffering offering, String relationType, TimePeriod validFor) {
        if (null == this.prodOfferingRelationship) {
            this.prodOfferingRelationship = new ArrayList<ProductOfferingRelationship>();
        }
        if (null == offering) {
            throw new IllegalArgumentException("Parameter [offering] cannot be null.");
        }
        if (null == relationType) {
            throw new IllegalArgumentException("Parameter [relationType] cannot be null. ID="
                    + offering.getId() + "relationType=" + relationType);
        }
        if (this.equals(offering)) {
            logger.error("Cannot add relationship with it self! ID=" + offering.getId() + "relationType=" + relationType);
            throw new IllegalArgumentException("Cannot add relationship with it self!");
        }
        if (this.prodOfferingRelationship.size() > 0) {
            for (ProductOfferingRelationship offeringRelationship : this.prodOfferingRelationship) {
                if (offering.equals(offeringRelationship.getTargetOffering()) && relationType.equals
                        (offeringRelationship.getTypeRelationship()) && offeringRelationship.getValidFor().isOverlap(validFor)) {
                    logger.error("the relationship already exist as the same timePeriod. Cannot add relationship." +
                            "  ID=" + offering.getId() + "relationType=" + relationType);
                    throw new IllegalArgumentException("the relationship already exist as the same timePeriod. Cannot add relationship.");

                }
            }
        }

        ProductOfferingRelationship offeringRelationship = new ProductOfferingRelationship(this, offering, relationType, validFor);
        this.prodOfferingRelationship.add(offeringRelationship);
    }

    public boolean updateRelatedOfferingValidPeriod(ProductOffering offering, String relationType, TimePeriod
            validFor, TimePeriod newValid) {
        //TODO
        //ProductOfferingRelationship offeringRelationship = new ProductOfferingRelationship(this, offering,
        //      relationType, validFor);

        return false;
    }

    /**
     * @param offering
     */
    public void removeRelatedOffering(ProductOffering offering) {
        //TODO
    }

    /**
     * @param relationType
     */
    public List<ProductOffering> retrieveRelatedOffering(String relationType) {
        List<ProductOffering> offerings = new ArrayList<ProductOffering>();
        if (StringUtils.isEmpty(relationType)) {
            throw new IllegalArgumentException("Parameter [relationType] cannot be null.");
        }
        if (null != this.prodOfferingRelationship && this.prodOfferingRelationship.size() > 0) {
            for (ProductOfferingRelationship relationship : prodOfferingRelationship) {
                if (relationType.equals(relationship.getTypeRelationship())) {
                    offerings.add(relationship.getTargetOffering());
                }
            }
        }
        return offerings;
    }

    /**
     * @param relationType
     * @param time
     */
    public List<ProductOffering> retrieveRelatedOffering(String relationType, Date time) {
        List<ProductOffering> offerings = new ArrayList<ProductOffering>();

        CommonUtils.checkParamIsNull("relationType", relationType);
        CommonUtils.checkParamIsNull("time", time);

        if (null != this.prodOfferingRelationship && this.prodOfferingRelationship.size() > 0) {
            for (ProductOfferingRelationship relationship : prodOfferingRelationship) {
                if (relationType.equals(relationship.getTypeRelationship()) && 0 == relationship.getValidFor().isInTimePeriod(time) && 0 == relationship.getValidFor().isInTimePeriod(time)) {
                    offerings.add(relationship.getTargetOffering());
                }
            }
        }
        return offerings;
    }

    /**
     * @param price
     */
    public void addPrice(ProductOfferingPrice price) {
        //TODO price
    }

    /**
     * @param price
     */
    public void removePrice(ProductOfferingPrice price) {
        //TODO price
    }

    /**
     * @param price
     */
    public void specifyPrice(ProductOfferingPrice[] price) {
        //TODO price
    }

    /**
     * @param time
     */
    public List<ProductOfferingPrice> retrievePrice(Date time) {
        //TODO price
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductOffering that = (ProductOffering) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Map<String, Object> getBasicInfoToMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("id", id);
        result.put("name", name);
        result.put("description", description);
        result.put("status", ProdOfferingEnum.ProductOfferingStatus.getName(status));
        result.put("validFor", validFor);
        return result;
    }
}