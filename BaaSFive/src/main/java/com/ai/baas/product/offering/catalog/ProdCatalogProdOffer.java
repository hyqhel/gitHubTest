package com.ai.baas.product.offering.catalog;

import com.ai.baas.product.offering.*;
import java.util.*;
import com.ai.baas.product.offering.price.*;
import com.ai.baas.basetype.*;
import org.apache.log4j.Logger;

/**
 * The appearance of a ProductOffering in a ProductCatalog.
 */
public class ProdCatalogProdOffer {
    private static final Logger logger = Logger.getLogger(ProductCatalog.class);
    private ProductOffering prodOffering;
    private List<ProductOfferingPrice> productOfferingPrice;
    /**
     * The period during which the ProductOffering appears in the ProductCatalog.
     */
    private TimePeriod validFor;

    public ProductOffering getProdOffering() {
        return this.prodOffering;
    }

    public void setValidFor(ProductOffering prodOffering) {
        this.prodOffering = prodOffering;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }
    public List<ProductOfferingPrice> getProductOfferingPrice() {
        return this.productOfferingPrice;
    }

    public void setProductOfferingPrice(List<ProductOfferingPrice> productOfferingPrice) {
        this.productOfferingPrice = productOfferingPrice;
    }

    /**
     * 
     * @param offering
     * @param validFor
     */
    public ProdCatalogProdOffer(ProductOffering offering, TimePeriod validFor) {
        checkProductOffering(offering);
        this.prodOffering = offering;
        this.validFor = validFor;
    }

    /**
     * 
     * @param offering
     * @param validFor
     * @param price
     */
    public ProdCatalogProdOffer(ProductOffering offering, TimePeriod validFor, List<ProductOfferingPrice> price) {
        checkProductOffering(offering);
        this.prodOffering = offering;
        this.validFor = validFor;
        this.productOfferingPrice = price;
    }

    /**
     * 
     * @param price
     */
    public void specifyOfferingPrice(ProductOfferingPrice price) {
        // TODO - implement ProdCatalogProdOffer.specifyOfferingPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param price
     */
    public void specifyOfferingPrice(ProductOfferingPrice[] price) {
        // TODO - implement ProdCatalogProdOffer.specifyOfferingPrice
        throw new UnsupportedOperationException();
    }
    /**
     * check parameter is null
     */
    private void checkProductOffering(ProductOffering offering){
        if(null == offering){
            logger.error("parameter is error £ºthe Object of ProductOffering is null . ");
            throw new IllegalArgumentException("offering should not be null .");
        }
    }
}