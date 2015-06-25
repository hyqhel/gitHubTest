package com.ai.baas.product.offering.catalog;

import com.ai.baas.common.catalog.*;
import java.util.*;

import com.ai.baas.basetype.*;
import com.ai.baas.product.offering.*;
import com.ai.baas.product.offering.price.*;

/**
 * A list of ProductOfferings for sale, with prices and illustrations, for example in book form or on the web. ProductCatalogs can be used by Customers during a self-care ordering process and may be used across one or more DistributionChannels.
 *  
 * A list of ProductOfferings for sale, with prices and illustrations, for example in book form or on the web. ProductCatalogs can be used by Customers during a self-care ordering process and may be used across one or more DistributionChannels.
 * ?
 */
public class ProductCatalog extends Catalog {

    public List<ProdCatalogProdOffer> prodCatalogProdOffer;

    public List<ProdCatalogProdOffer> getProdCatalogProdOffer() {
		return prodCatalogProdOffer;
	}

	/**
     * 
     * @param id
     * @param name
     * @param type
     * @param validFor
     */
    public ProductCatalog(String id, String name, String type, TimePeriod validFor) {
        // TODO - implement ProductCatalog.ProductCatalog
    	super(id, name, type, validFor);
    }

    /**
     * 
     * @param offering
     * @param validFor
     */
    public void publishOffering(ProductOffering offering, TimePeriod validFor) {
        // TODO - implement ProductCatalog.publishOffering
    	ProdCatalogProdOffer prodOffer = new ProdCatalogProdOffer( offering,  validFor);
    	if(this.prodCatalogProdOffer == null){
    		this.prodCatalogProdOffer =  new ArrayList<ProdCatalogProdOffer>();
    	}
    	this.prodCatalogProdOffer.add(prodOffer);
    }

    /**
     * 
     * @param offeringId
     * @param validFor
     */
    public void publishOffering(String offeringId, TimePeriod validFor) {
        // TODO - implement ProductCatalog.publishOffering
    	
    }

    /**
     * 
     * @param offering
     * @param validFor
     * @param price
     */
    public void publishOffering(ProductOffering offering, TimePeriod validFor, ProductOfferingPrice[] price) {
        // TODO - implement ProductCatalog.publishOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offeringId
     * @param validFor
     * @param price
     */
    public void publishOffering(String offeringId, TimePeriod validFor, ProductOfferingPrice[] price) {
        // TODO - implement ProductCatalog.publishOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     */
    public void unPublishOffering(ProductOffering offering) {
        // TODO - implement ProductCatalog.unPublishOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offeringId
     */
    public void unPublishOffering(String offeringId) {
        // TODO - implement ProductCatalog.unPublishOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     * @param time
     */
    public ProductOfferingPrice[] queryOfferingPrice(ProductOffering offering, Date time) {
        // TODO - implement ProductCatalog.queryOfferingPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offeringId
     * @param time
     */
    public ProductOfferingPrice[] queryOfferingPrice(String offeringId, Date time) {
        // TODO - implement ProductCatalog.queryOfferingPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     * @param timePeriod
     * @param price
     */
    public void advertiseOfferingPrice(ProductOffering offering, TimePeriod timePeriod, ProductOfferingPrice price) {
        // TODO - implement ProductCatalog.advertiseOfferingPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offeringId
     * @param timePeriod
     * @param priceId
     */
    public void advertiseOfferingPrice(String offeringId, TimePeriod timePeriod, String priceId) {
        // TODO - implement ProductCatalog.advertiseOfferingPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     * @param timePeriod
     * @param price
     */
    public void setOfferingPrice(ProductOffering offering, TimePeriod timePeriod, ProductOfferingPrice[] price) {
        // TODO - implement ProductCatalog.setOfferingPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offeringId
     * @param timePeriod
     * @param price
     */
    public void setOfferingPrice(String offeringId, TimePeriod timePeriod, ProductOfferingPrice[] price) {
        // TODO - implement ProductCatalog.setOfferingPrice
        throw new UnsupportedOperationException();
    }

}