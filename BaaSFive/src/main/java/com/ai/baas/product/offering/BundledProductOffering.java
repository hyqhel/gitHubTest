package com.ai.baas.product.offering;

import java.util.*;
import com.ai.baas.basetype.*;

public class BundledProductOffering extends ProductOffering {

    public List<BundledProdOfferOption> bundledProdOfferOption;

    public List<BundledProdOfferOption> getBundledProdOfferOption() {
        return this.bundledProdOfferOption;
    }

    public void setBundledProdOfferOption(List<BundledProdOfferOption> bundledProdOfferOption) {
        this.bundledProdOfferOption = bundledProdOfferOption;
    }

    /**
     * 
     * @param id
     * @param name
     * @param description
     * @param validFor
     */
    public BundledProductOffering(String id, String name, String description, TimePeriod validFor) {
        // TODO - implement BundledProductOffering.BundledProductOffering
    	super(id, name, description, validFor);
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     */
    public void addSubOffering(ProductOffering offering) {
        // TODO - implement BundledProductOffering.addSubOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offeringId
     */
    public void addSubOffering(String offeringId) {
        // TODO - implement BundledProductOffering.addSubOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     * @param lowerLimit
     * @param upperLimit
     */
    public void addSubOffering(ProductOffering offering, int lowerLimit, int upperLimit) {
        // TODO - implement BundledProductOffering.addSubOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offeringId
     * @param lowerLimit
     * @param upperLimit
     */
    public void addSubOffering(String offeringId, int lowerLimit, int upperLimit) {
        // TODO - implement BundledProductOffering.addSubOffering
        throw new UnsupportedOperationException();
    }

    public ProductOffering[] getSubOffering() {
        // TODO - implement BundledProductOffering.getSubOffering
        throw new UnsupportedOperationException();
    }

}