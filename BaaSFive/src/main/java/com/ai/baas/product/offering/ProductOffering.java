package com.ai.baas.product.offering;

import java.util.*;
import com.ai.baas.product.offering.price.*;
import com.ai.baas.product.offering.catalog.*;
import com.ai.baas.basetype.*;

/**
 * The presentation of one or more ProductSpecifications to the marketplace for sale, rental, or lease for a ProductOfferingPrice. A ProductOffering may target one or more MarketSegments, be included in one or more ProductCatalog, presented in support of one or more ProductStrategies, and made available in one or more Places. ProductOffering may represent a simple offering of a single ProductSpecification or could represent a bundling of one or more other ProductOffering.
 */
public abstract class ProductOffering {

    public List<ProductOfferingPrice> productOfferingPrice;
    public List<ProductOfferingRelationship> prodOfferingRelationship;
    public List<ProdCatalogProdOffer> prodCatalogProdOffer;
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
     * 
     * @param id
     * @param name
     * @param description
     * @param validFor
     */
    public ProductOffering(String id, String name, String description, TimePeriod validFor) {
        // TODO - implement ProductOffering.ProductOffering
    	this.id = id;
    	this.name = name;
    	this.description = description;
    	this.validFor = validFor;
    }

    /**
     * 
     * @param offering
     * @param relationType
     * @param validFor
     */
    public void addRelatedOffering(ProductOffering offering, String relationType, TimePeriod validFor) {
        // TODO - implement ProductOffering.addRelatedOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offeringId
     * @param relationType
     * @param validFor
     */
    public void addRelatedOffering(String offeringId, String relationType, TimePeriod validFor) {
        // TODO - implement ProductOffering.addRelatedOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     */
    public void removeRelatedOffering(ProductOffering offering) {
        // TODO - implement ProductOffering.removeRelatedOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offeringId
     */
    public void removeRelatedOffering(String offeringId) {
        // TODO - implement ProductOffering.removeRelatedOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param relationType
     */
    public ProductOffering[] queryRelatedOffering(String relationType) {
        // TODO - implement ProductOffering.queryRelatedOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param relationType
     * @param time
     */
    public ProductOffering[] queryRelatedOffering(String relationType, Date time) {
        // TODO - implement ProductOffering.queryRelatedOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param price
     */
    public void addPrice(ProductOfferingPrice price) {
        // TODO - implement ProductOffering.addPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param priceId
     */
    public void addPrice(String priceId) {
        // TODO - implement ProductOffering.addPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param price
     */
    public void removePrice(ProductOfferingPrice price) {
        // TODO - implement ProductOffering.removePrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param priceId
     */
    public void removePrice(String priceId) {
        // TODO - implement ProductOffering.removePrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param price
     */
    public void setPrice(ProductOfferingPrice[] price) {
        // TODO - implement ProductOffering.setPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param time
     */
    public ProductOfferingPrice[] queryPrice(Date time) {
        // TODO - implement ProductOffering.queryPrice
        throw new UnsupportedOperationException();
    }

}