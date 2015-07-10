package com.ai.baas.product.offering.catalog;

import com.ai.baas.common.util.CommonUtils;
import com.ai.baas.product.offering.*;

import java.util.*;

import com.ai.baas.product.offering.price.*;
import com.ai.baas.basetype.*;
import org.apache.log4j.Logger;

/**
 * The appearance of a ProductOffering in a ProductCatalog.
 */
public class ProdCatalogProdOffer {
    private static final Logger logger = Logger.getLogger(ProdCatalogProdOffer.class);
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
     * @param offering
     * @param validFor
     */
    public ProdCatalogProdOffer(ProductOffering offering, TimePeriod validFor) {
        checkProductOffering(offering);
        checkTimePeriod(validFor);
        this.prodOffering = offering;
        this.validFor = validFor;
    }

    /**
     * @param offering
     * @param validFor
     * @param price
     */
    public ProdCatalogProdOffer(ProductOffering offering, TimePeriod validFor, List<ProductOfferingPrice> price) {
        checkProductOffering(offering);
        checkTimePeriod(validFor);
        this.prodOffering = offering;
        this.validFor = validFor;
        this.productOfferingPrice = price;
    }

    /**
     * @param price
     */
    public void specifyOfferingPrice(ProductOfferingPrice price) {
        // TODO - implement ProdCatalogProdOffer.specifyOfferingPrice
    }

    /**
     * @param price
     */
    public void specifyOfferingPrice(ProductOfferingPrice[] price) {
        // TODO - implement ProdCatalogProdOffer.specifyOfferingPrice
    }

    /**
     * check parameter is null
     */
    private void checkProductOffering(ProductOffering offering) {
        if (null == offering) {
            logger.error("parameter is error £ºthe Object of ProductOffering is null . ");
            throw new IllegalArgumentException("offering should not be null .");
        }
    }
    /**
     * check parameter is null
     */
    private void checkTimePeriod(TimePeriod validFor) {
        if (null == validFor) {
            logger.error("parameter is error £ºthe Object of TimePeriod is null . ");
            throw new IllegalArgumentException("validFor should not be null .");
        }else if(null == validFor.getStartDateTime()){
            logger.error("parameter is error £ºthe Object of TimePeriod's startDateTime is null . ");
            throw new IllegalArgumentException("startDateTime should not be null .");
        }else if(null == validFor.getEndDateTime()){
            logger.error("parameter is error £ºthe Object of TimePeriod's endDateTime is null . ");
            throw new IllegalArgumentException("endDateTime should not be null .");
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProdCatalogProdOffer that = (ProdCatalogProdOffer) o;

        if (!prodOffering.equals(that.prodOffering)) return false;
        if (productOfferingPrice != null ? !productOfferingPrice.equals(that.productOfferingPrice) : that.productOfferingPrice != null)
            return false;
        return !(validFor != null ? !validFor.equals(that.validFor) : that.validFor != null);

    }

    @Override
    public int hashCode() {
        int result = prodOffering.hashCode();
        result = 31 * result + (productOfferingPrice != null ? productOfferingPrice.hashCode() : 0);
        result = 31 * result + (validFor != null ? validFor.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("prodOffering", prodOffering);
        resultMap.put("productOfferingPrice", productOfferingPrice);
        return resultMap.toString();
    }
}