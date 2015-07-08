package com.ai.baas.product.offering;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.product.offering.catalog.ProdCatalogProdOffer;
import com.ai.baas.product.offering.price.ProductOfferingPrice;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        if (StringUtils.isEmpty(id)) {
            logger.error("The parameter " + id + " is null");
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.name = name;
        this.description = description;
        this.validFor = validFor;
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
        ProductOfferingRelationship offeringRelationship = new ProductOfferingRelationship(this, offering, relationType, validFor);
        if (this.prodOfferingRelationship.contains(offeringRelationship)) {
            logger.warn("the relationship already exist, Cannot repeatedly create relationship by the same type. ID="
                    + offering.getId() + "relationType=" + relationType);
        } else {
            this.prodOfferingRelationship.add(offeringRelationship);
        }
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
    public List<ProductOffering> queryRelatedOffering(String relationType, Date time) {
        List<ProductOffering> offerings = new ArrayList<ProductOffering>();
        if (StringUtils.isEmpty(relationType)) {
            throw new IllegalArgumentException("Parameter [relationType] cannot be null.");
        }
        if (null == time) {
            throw new IllegalArgumentException("Parameter [time] cannot be null.");
        }
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
    public ProductOfferingPrice[] retrievePrice(Date time) {
        //TODO price
        return null;
    }

}