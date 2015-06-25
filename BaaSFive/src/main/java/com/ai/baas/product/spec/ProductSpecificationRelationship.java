package com.ai.baas.product.spec;

import com.ai.baas.basetype.*;

/**
 * A migration, substitution, dependency, or exclusivity relationship between/among ProductSpecifications.
 */
public class ProductSpecificationRelationship {

    ProductSpecification targetProdSpec;
    ProductSpecification sourceSpec;
    /**
     * A categorization of the relationship, such as migration, substitution, dependency, exclusivity.
     */
    private String type;
    /**
     * The period for which the relationship is applicable.
     */
    private TimePeriod validFor;

    /**
     * 
     * @param sourceSpec
     * @param targetSpec
     * @param type
     * @param validFor
     */
    public ProductSpecificationRelationship(ProductSpecification sourceSpec, ProductSpecification targetSpec, String type, TimePeriod validFor) {
        // TODO - implement ProductSpecificationRelationship.ProductSpecificationRelationship
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param sourceSpec
     * @param targetSpecId
     * @param type
     * @param validFor
     */
    public ProductSpecificationRelationship(ProductSpecification sourceSpec, String targetSpecId, String type, TimePeriod validFor) {
        // TODO - implement ProductSpecificationRelationship.ProductSpecificationRelationship
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param sourceSpecId
     * @param targetSpec
     * @param type
     * @param validFor
     */
    public ProductSpecificationRelationship(String sourceSpecId, ProductSpecification targetSpec, String type, TimePeriod validFor) {
        // TODO - implement ProductSpecificationRelationship.ProductSpecificationRelationship
        throw new UnsupportedOperationException();
    }

}