package com.ai.baas.product.offering;

import java.util.*;

import com.ai.baas.basetype.*;
import org.apache.log4j.Logger;

public class BundledProductOffering extends ProductOffering {

    private static Logger logger = Logger.getLogger(BundledProductOffering.class);

    public List<BundledProdOfferOption> bundledProdOfferOption;

    public List<BundledProdOfferOption> getBundledProdOfferOption() {
        return this.bundledProdOfferOption;
    }

    public void setBundledProdOfferOption(List<BundledProdOfferOption> bundledProdOfferOption) {
        this.bundledProdOfferOption = bundledProdOfferOption;
    }

    /**
     * @param id
     * @param name
     * @param description
     * @param validFor
     */
    public BundledProductOffering(String id, String name, String description, TimePeriod validFor) {
        super(id, name, description, validFor);
    }

    /**
     * @param offering
     */
    public void addSubOffering(ProductOffering offering) {
        addSubOffering(offering, -1, -1);
    }


    /**
     * @param offering
     * @param lowerLimit
     * @param upperLimit
     */
    public void addSubOffering(ProductOffering offering, int lowerLimit, int upperLimit) {
        if (null == this.bundledProdOfferOption) {
            this.bundledProdOfferOption = new ArrayList<BundledProdOfferOption>();
        }
        if (lowerLimit < -1 || upperLimit < -1) {
            logger.error("Parameter [lowerLimit]、[upperLimit] is not valid. lowerLimit=" + lowerLimit + "; " +
                    "upperLimit=" + upperLimit);
            throw new IllegalArgumentException("Parameter [lowerLimit]、[upperLimit] is not valid .");
        }
        if (lowerLimit > upperLimit) {
            logger.error("the lowLimit must be much lower than upperLimit. lowerLimit=" + lowerLimit + "; " +
                    "upperLimit=" + upperLimit);
            throw new IllegalArgumentException("the lowLimit must be much lower than upperLimit.");
        }
        if (null == offering) {
            logger.error("Parameter [offering] cannot be null.");
            throw new IllegalArgumentException("Parameter [offering] cannot be null.");
        }
        if (this.equals(offering)) {
            logger.error("Cannot add subOffering with it self!");
            throw new IllegalArgumentException("Cannot add subOffering with it self!");
        }
        BundledProdOfferOption subOfferingOption = new BundledProdOfferOption(offering, lowerLimit, upperLimit);
        if (this.bundledProdOfferOption.size() > 0) {
            for (BundledProdOfferOption bundledOfferingOption : this.bundledProdOfferOption) {
                if (bundledOfferingOption.equals(subOfferingOption)) {
                    logger.error("the subProdSpec already exist, Cannot repeatedly create subOffering. OfferingID="
                            + offering.getId());
                    throw new IllegalArgumentException("the subProdSpec already exist, Cannot repeatedly create subOffering. OfferingID="
                            + offering.getId());
                }
            }
        }

        this.bundledProdOfferOption.add(subOfferingOption);
    }


    /**
     * @return
     */
    public List<ProductOffering> retrieveSubOffering() {
        List<ProductOffering> offerings = new ArrayList<ProductOffering>();
        if (null != this.bundledProdOfferOption && this.bundledProdOfferOption.size() > 0) {
            for (BundledProdOfferOption bundledOfferingOption : this.bundledProdOfferOption) {
                offerings.add(bundledOfferingOption.getProductOffering());
            }
        }
        return offerings;
    }

}