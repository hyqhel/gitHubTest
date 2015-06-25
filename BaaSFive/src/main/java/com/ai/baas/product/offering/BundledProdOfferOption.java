package com.ai.baas.product.offering;

/**
 * A set of numbers that specifies the lower and upper limits for a ProductOffering that can be procured as part of the related BundledProductOffering.
 * 
 * Values can range from 0 to unbounded.
 */
public class BundledProdOfferOption {

    public ProductOffering productOffering;
    public BundledProductOffering bundledProductOffering;
    /**
     * The lower limit of related ProductOfferings that can be procured as part of the BundledProductOffering.
     * 
     * Values can range from 0 to unbounded.
     */
    private int numberRelOfferLowerLimit;
    /**
     * The upper limit of related ProductOfferings that can be procured as part of the BundledProductOffering.
     * 
     * Values can range from 0 to unbounded.
     */
    private int numberRelOfferUpperLimit;

    public int getNumberRelOfferLowerLimit() {
        return this.numberRelOfferLowerLimit;
    }

    public void setNumberRelOfferLowerLimit(int numberRelOfferLowerLimit) {
        this.numberRelOfferLowerLimit = numberRelOfferLowerLimit;
    }

    public int getNumberRelOfferUpperLimit() {
        return this.numberRelOfferUpperLimit;
    }

    public void setNumberRelOfferUpperLimit(int numberRelOfferUpperLimit) {
        this.numberRelOfferUpperLimit = numberRelOfferUpperLimit;
    }

    /**
     * 
     * @param offering
     * @param lowerLimit
     * @param upperLimit
     */
    public BundledProdOfferOption(ProductOffering offering, int lowerLimit, int upperLimit) {
        // TODO - implement BundledProdOfferOption.BundledProdOfferOption
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offeringId
     * @param lowerLimit
     * @param upperLimit
     */
    public BundledProdOfferOption(String offeringId, int lowerLimit, int upperLimit) {
        // TODO - implement BundledProdOfferOption.BundledProdOfferOption
        throw new UnsupportedOperationException();
    }

}