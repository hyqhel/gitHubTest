package com.digiwes.product.spec;

import com.digiwes.basetype.TimePeriod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.digiwes.common.util.CommonUtils;
import org.apache.log4j.Logger;

/**
 * A type of ProductSpecification that is formed by aggregating other ProductSpecifications, which may be Composite or Atomic ProductSpecifications.
 */
public class CompositeProductSpecification extends ProductSpecification {

    private static Logger logger = Logger.getLogger(CompositeProductSpecification.class);

    public List<ProductSpecification> prodSpec;

    /**
     * @param productNumber
     * @param name
     * @param brand
     */
    public CompositeProductSpecification(String productNumber, String name, String brand) {
        super(productNumber, name, brand);
    }

    /**
     * @param productNumber
     * @param name
     * @param brand
     * @param description
     * @param validFor
     */
    public CompositeProductSpecification(String productNumber, String name, String brand, String description, TimePeriod validFor) {
        super(productNumber, name, brand, validFor, description);
    }

    /**
     * @param prodSpec
     */
    public void addSubProdSpec(ProductSpecification prodSpec) {
        if (null == this.prodSpec) {
            this.prodSpec = new ArrayList<ProductSpecification>();
        }
        if (null == prodSpec) {
            throw new IllegalArgumentException("Parameter [prodSpec] cannot be null.");
        }
        if (this.prodSpec.contains(prodSpec)) {
            logger.error("the subProdSpec already exist, Cannot repeatedly create subProdSpec. ProductNumber="
                    + prodSpec.getProductNumber());
            throw new IllegalArgumentException("the subProdSpec already exist, Cannot repeatedly create subProdSpec.");
        }
        if (this.equals(prodSpec)) {
            logger.error("Cannot add subProdSpec with it self!");
            throw new IllegalArgumentException("Cannot add subProdSpec with it self!");
        }
        this.prodSpec.add(prodSpec);
    }

    @Override
    public String toString() {
        Map<String, Object> result = this.getProductInfoToMap();
        result.put("prodSpec", prodSpec);
        return CommonUtils.format(result.toString());
    }
}