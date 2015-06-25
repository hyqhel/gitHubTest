package com.ai.baas.product.spec;

import com.ai.baas.basetype.*;

public class ProdSpecCharValueRelationship {

    public ProductSpecCharacteristicValue sourceCharValue;
    public ProductSpecCharacteristicValue productSpecCharacteristicValue;
    /**
     * A categorization of the relationship between values, such as aggregation, migration, substitution, dependency, exclusivity.
     */
    private String charValueRelationshipType;
    /**
     * The period for which the relationship is applicable.
     */
    private TimePeriod validFor;

    public String getCharValueRelationshipType() {
        return this.charValueRelationshipType;
    }

    public void setCharValueRelationshipType(String charValueRelationshipType) {
        this.charValueRelationshipType = charValueRelationshipType;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param srourceCharValue
     * @param targetCharValue
     * @param relationType
     * @param validFor
     */
    public ProdSpecCharValueRelationship(ProductSpecCharacteristicValue srourceCharValue, ProductSpecCharacteristicValue targetCharValue, String relationType, TimePeriod validFor) {
        // TODO - implement ProdSpecCharValueRelationship.ProdSpecCharValueRelationship
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param srourceCharValue
     * @param targetCharValueId
     * @param relationType
     * @param validFor
     */
    public ProdSpecCharValueRelationship(ProductSpecCharacteristicValue srourceCharValue, String targetCharValueId, String relationType, TimePeriod validFor) {
        // TODO - implement ProdSpecCharValueRelationship.ProdSpecCharValueRelationship
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param srourceCharValueId
     * @param targetCharValue
     * @param relationType
     * @param validFor
     */
    public ProdSpecCharValueRelationship(String srourceCharValueId, ProductSpecCharacteristicValue targetCharValue, String relationType, TimePeriod validFor) {
        // TODO - implement ProdSpecCharValueRelationship.ProdSpecCharValueRelationship
        throw new UnsupportedOperationException();
    }

}