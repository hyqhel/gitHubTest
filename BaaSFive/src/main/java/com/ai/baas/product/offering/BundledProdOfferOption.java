package com.ai.baas.product.offering;

/**
 * A set of numbers that specifies the lower and upper limits for a ProductOffering that can be procured as part of the related BundledProductOffering.
 * <p/>
 * Values can range from 0 to unbounded.
 */
public class BundledProdOfferOption {

    private ProductOffering productOffering;
    private BundledProductOffering bundledProductOffering;

    public ProductOffering getProductOffering() {
        return productOffering;
    }

    public void setProductOffering(ProductOffering productOffering) {
        this.productOffering = productOffering;
    }

    public BundledProductOffering getBundledProductOffering() {
        return bundledProductOffering;
    }

    public void setBundledProductOffering(
            BundledProductOffering bundledProductOffering) {
        this.bundledProductOffering = bundledProductOffering;
    }

    /**
     * The lower limit of related ProductOfferings that can be procured as part of the BundledProductOffering.
     * <p/>
     * Values can range from 0 to unbounded.
     */
    private int numberRelOfferLowerLimit;
    /**
     * The upper limit of related ProductOfferings that can be procured as part of the BundledProductOffering.
     * <p/>
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
     * @param offering
     * @param lowerLimit
     * @param upperLimit
     */
    public BundledProdOfferOption(ProductOffering offering, int lowerLimit, int upperLimit) {
        this.productOffering = offering;
        this.numberRelOfferLowerLimit = lowerLimit;
        this.numberRelOfferUpperLimit = upperLimit;

    }

}