package com.ai.baas.product.spec;

import java.util.*;
import com.ai.baas.basetype.*;

/**
 * A particular form or variety of a ProductSpecification that is different from others or from the original. The form represents differences in properties that characterize a ProductSpecification, that are not enough to warrant creating a new ProductSpecification.
 */
public class ProductSpecificationVersion {

    /**
     * The significance of the revision.
     */
    private String prodSpecRevisionType;
    /**
     * A narrative that explains the reason for the version's creation.
     */
    private String description;
    /**
     * A number that represents the occurrence of the version in the sequence of versions.
     */
    private String prodSpecRevisionNumber;
    /**
     * The date the version was created.
     */
    private Date prodSpecRevisionDate;
    /**
     * The period during which the version is applicable.
     */
    private TimePeriod validFor;

    public String getProdSpecRevisionType() {
        return this.prodSpecRevisionType;
    }

    public void setProdSpecRevisionType(String prodSpecRevisionType) {
        this.prodSpecRevisionType = prodSpecRevisionType;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProdSpecRevisionNumber() {
        return this.prodSpecRevisionNumber;
    }

    public void setProdSpecRevisionNumber(String prodSpecRevisionNumber) {
        this.prodSpecRevisionNumber = prodSpecRevisionNumber;
    }

    public Date getProdSpecRevisionDate() {
        return this.prodSpecRevisionDate;
    }

    public void setProdSpecRevisionDate(Date prodSpecRevisionDate) {
        this.prodSpecRevisionDate = prodSpecRevisionDate;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param revisionType
     * @param description
     * @param revisionNumber
     * @param revisionDate
     * @param validFor
     */
    public ProductSpecificationVersion(String revisionType, String description, String revisionNumber, Date revisionDate, TimePeriod validFor) {
        // TODO - implement ProductSpecificationVersion.ProductSpecificationVersion
    	this.prodSpecRevisionType = revisionType;
    	this.description = description;
    	this.validFor = validFor;
    	this.prodSpecRevisionNumber = revisionNumber;
    }

}