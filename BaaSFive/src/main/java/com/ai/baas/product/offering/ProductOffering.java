package com.ai.baas.product.offering;

import java.util.*;

import com.ai.baas.product.offering.price.*;
import com.ai.baas.product.offering.catalog.*;
import com.ai.baas.basetype.*;

/**
 * The presentation of one or more ProductSpecifications to the marketplace for sale, rental, or lease for a ProductOfferingPrice. A ProductOffering may target one or more MarketSegments, be included in one or more ProductCatalog, presented in support of one or more ProductStrategies, and made available in one or more Places. ProductOffering may represent a simple offering of a single ProductSpecification or could represent a bundling of one or more other ProductOffering.
 */
public abstract class ProductOffering {

    private List<ProductOfferingPrice> productOfferingPrice;
    private List<ProductOfferingRelationship> prodOfferingRelationship;
    private List<ProdCatalogProdOffer> prodCatalogProdOffer;
    
    public List<ProductOfferingPrice> getProductOfferingPrice() {
		return productOfferingPrice;
	}

	public void setProductOfferingPrice(
			List<ProductOfferingPrice> productOfferingPrice) {
		this.productOfferingPrice = productOfferingPrice;
	}

	public List<ProductOfferingRelationship> getProdOfferingRelationship() {
		return prodOfferingRelationship;
	}

	public void setProdOfferingRelationship(
			List<ProductOfferingRelationship> prodOfferingRelationship) {
		this.prodOfferingRelationship = prodOfferingRelationship;
	}

	public List<ProdCatalogProdOffer> getProdCatalogProdOffer() {
		return prodCatalogProdOffer;
	}

	public void setProdCatalogProdOffer(
			List<ProdCatalogProdOffer> prodCatalogProdOffer) {
		this.prodCatalogProdOffer = prodCatalogProdOffer;
	}

	/**
     * A unique identifier for the ProductOffering.
     */
    private String id;
    /**
     * A word, term, or phrase by which the ProductOffeirng is known and distinguished from other ProductOfferings.
     */
    private String name;
    /**
     * A narrative that explains what the offering is.
     */
    private String description;
    /**
     * The period during which the offering is applicable.
     */
    private TimePeriod validFor;
    /**
     * The condition in which the offering exists, such as planned, obsolete, active
     */
    private String status;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public String getStatus() {
        return this.status;
    }

    /**
     * 
     * @param id
     * @param name
     * @param description
     * @param validFor
     */
    public ProductOffering(String id, String name, String description, TimePeriod validFor) {
        // TODO - implement ProductOffering.ProductOffering
    	this.id = id;
    	this.name = name;
    	this.description = description;
    	this.validFor = validFor;
    }

    /**
     * 
     * @param offering
     * @param relationType
     * @param validFor
     */
    public void addRelatedOffering(ProductOffering offering, String relationType, TimePeriod validFor) {
    	ProductOfferingRelationship pors =  new ProductOfferingRelationship(this,offering,relationType,validFor);
    	if(this.prodOfferingRelationship == null){
    		this.prodOfferingRelationship = new ArrayList<ProductOfferingRelationship>();
    	}
    	this.prodOfferingRelationship.add(pors);
    	
    }

    /**
     * 
     * @param offeringId
     * @param relationType
     * @param validFor
     */
    public void addRelatedOffering(String offeringId, String relationType, TimePeriod validFor) {
    }

    /**
     * 
     * @param offering
     */
    public void removeRelatedOffering(ProductOffering offering) {
    	if(this.prodOfferingRelationship != null && this.prodOfferingRelationship.size()>0){
    		this.prodOfferingRelationship.remove(offering);
    	}
    }

    /**
     * 
     * @param offeringId
     */
    public void removeRelatedOffering(String offeringId) {
    }

    /**
     * 
     * @param relationType
     */
    public List<ProductOffering> queryRelatedOffering(String relationType) {
    	List<ProductOffering> poarr= new ArrayList<ProductOffering>();
    	if("".equals(relationType)){
    		return null;
    	}
    	if(this.prodOfferingRelationship != null && this.prodOfferingRelationship.size()>0){
    		for(int i=0 ;i<this.prodOfferingRelationship.size();i++){
    			if(this.prodOfferingRelationship.get(i).getTypeRelationship().equals(relationType)){
    				poarr.add(this.prodOfferingRelationship.get(i).getTargetOffering());
    			}
    		}
    	}
        return poarr;
    }

    /**
     * 
     * @param relationType
     * @param time
     */
    public ProductOffering[] queryRelatedOffering(String relationType, Date time) {
    	return null;
    }

    /**
     * 
     * @param price
     */
    public void addPrice(ProductOfferingPrice price) {
    	if(price !=null){
    		if(this.productOfferingPrice ==null ){
    			this.productOfferingPrice = new ArrayList<ProductOfferingPrice>();
    		}
    		this.productOfferingPrice.add(price);
    	}
    	
    }

    /**
     * 
     * @param priceId
     */
    public void addPrice(String priceId) {
    }

    /**
     * 
     * @param price
     */
    public void removePrice(ProductOfferingPrice price) {
    	if(price !=null){
    		if(this.productOfferingPrice !=null && this.productOfferingPrice.size()>0){
    			this.productOfferingPrice.remove(price);
    		}
    	}
    }

    /**
     * 
     * @param priceId
     */
    public void removePrice(String priceId) {
    }

    /**
     * 
     * @param price
     */
    public void setPrice(ProductOfferingPrice[] price) {
    }

    /**
     * 
     * @param time
     */
    public ProductOfferingPrice[] queryPrice(Date time) {
    	return null;
    }

}