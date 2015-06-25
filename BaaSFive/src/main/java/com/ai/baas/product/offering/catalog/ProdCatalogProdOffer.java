package com.ai.baas.product.offering.catalog;

import com.ai.baas.product.offering.*;
import java.util.*;
import com.ai.baas.product.offering.price.*;
import com.ai.baas.basetype.*;

/**
 * The appearance of a ProductOffering in a ProductCatalog.
 */
public class ProdCatalogProdOffer {

    public ProductOffering prodOffering;
    public List<ProductOfferingPrice> productOfferingPrice;
    /**
     * The period during which the ProductOffering appears in the ProductCatalog.
     */
    private TimePeriod validFor;

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param offering
     * @param validFor
     */
    public ProdCatalogProdOffer(ProductOffering offering, TimePeriod validFor) {
        // TODO - implement ProdCatalogProdOffer.ProdCatalogProdOffer
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offeringId
     * @param validFor
     */
    public ProdCatalogProdOffer(String offeringId, TimePeriod validFor) {
        // TODO - implement ProdCatalogProdOffer.ProdCatalogProdOffer
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     * @param validFor
     * @param price
     */
    public ProdCatalogProdOffer(ProductOffering offering, TimePeriod validFor, ProductOfferingPrice[] price) {
        // TODO - implement ProdCatalogProdOffer.ProdCatalogProdOffer
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offeringId
     * @param validFor
     * @param price
     */
    public ProdCatalogProdOffer(String offeringId, TimePeriod validFor, ProductOfferingPrice[] price) {
        // TODO - implement ProdCatalogProdOffer.ProdCatalogProdOffer
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param price
     */
    public void advertiseOfferingPrice(ProductOfferingPrice price) {
        // TODO - implement ProdCatalogProdOffer.advertiseOfferingPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param priceId
     */
    public void advertiseOfferingPrice(String priceId) {
        // TODO - implement ProdCatalogProdOffer.advertiseOfferingPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param price
     */
    public void setOfferingPrice(ProductOfferingPrice[] price) {
        // TODO - implement ProdCatalogProdOffer.setOfferingPrice
        throw new UnsupportedOperationException();
    }

}