package com.ai.baas.product.offering;

import com.ai.baas.common.util.CommonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * A set of numbers that specifies the lower and upper limits for a ProductOffering that can be procured as part of the related BundledProductOffering.
 * <p/>
 * Values can range from 0 to unbounded.
 */
public class BundledProdOfferOption {

    public ProductOffering productOffering;
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

    public ProductOffering getProductOffering() {
        return productOffering;
    }

    public void setProductOffering(ProductOffering productOffering) {
        this.productOffering = productOffering;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BundledProdOfferOption that = (BundledProdOfferOption) o;

        return productOffering.equals(that.productOffering);

    }

    @Override
    public int hashCode() {
        return productOffering.hashCode();
    }

    @Override
    public String toString() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("productOffering", this.productOffering);
        result.put("numberRelOfferLowerLimit", this.numberRelOfferLowerLimit);
        result.put("numberRelOfferUpperLimit", this.numberRelOfferUpperLimit);
        return CommonUtils.format(result.toString());
    }
}