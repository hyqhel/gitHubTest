package com.ai.baas.product.offering.catalog;

import com.ai.baas.common.catalog.*;
import java.util.*;
import com.ai.baas.basetype.*;
import com.ai.baas.common.util.CommonUtils;
import com.ai.baas.product.offering.*;
import com.ai.baas.product.offering.price.*;
import org.apache.log4j.Logger;
import java.util.Date;


/**
 * A list of ProductOfferings for sale, with prices and illustrations, for example in book form or on the web. ProductCatalogs can be used by Customers during a self-care ordering process and may be used across one or more DistributionChannels.
 *  
 * A list of ProductOfferings for sale, with prices and illustrations, for example in book form or on the web. ProductCatalogs can be used by Customers during a self-care ordering process and may be used across one or more DistributionChannels.
 * ?
 */
public class ProductCatalog extends Catalog {
    private static final Logger logger = Logger.getLogger(ProductCatalog.class);
    private List<ProdCatalogProdOffer> prodCatalogProdOffer;

    public List<ProdCatalogProdOffer> getProdCatalogProdOffer() {
        return this.prodCatalogProdOffer;
    }

    public void setProdCatalogProdOffer(List<ProdCatalogProdOffer> prodCatalogProdOffer) {
        this.prodCatalogProdOffer = prodCatalogProdOffer;
    }
    /**
     * 
     * @param id
     * @param name
     * @param type
     * @param validFor
     */
    public ProductCatalog(String id, String name, String type, TimePeriod validFor) {
        super(id, name, type, validFor);
    }

    /**
     * 
     * @param offering
     * @param validFor
     */
    public void publishOffering(ProductOffering offering, TimePeriod validFor) {
        checkProductOffering(offering);
        if(null == prodCatalogProdOffer){
            prodCatalogProdOffer = new ArrayList<ProdCatalogProdOffer>();
        }
        ProdCatalogProdOffer seac = retrieveProdCatalogProdOffer(offering);
        if(null != seac){
            if(seac.getValidFor().isOverlap(validFor)){
                logger.warn("Characteristic have been created in the specified time");
                return;
            }
        }
        ProdCatalogProdOffer catalogProdOffer = new ProdCatalogProdOffer(offering,validFor);
        if(!prodCatalogProdOffer.contains(catalogProdOffer)) {
            prodCatalogProdOffer.add(catalogProdOffer);
        }else {
            logger.warn("The offering is already publish . ");
        }
    }

    /**
     * 
     * @param offering
     * @param validFor
     * @param price
     */
    public void publishOffering(ProductOffering offering, TimePeriod validFor, List<ProductOfferingPrice> price) {
        checkProductOffering(offering);
        if(null == prodCatalogProdOffer){
            prodCatalogProdOffer = new ArrayList<ProdCatalogProdOffer>();
        }
        ProdCatalogProdOffer seac = retrieveProdCatalogProdOffer(offering);
        if(null != seac){
            if(seac.getValidFor().isOverlap(validFor)){
                logger.warn("Characteristic have been created in the specified time");
                return;
            }
        }
        ProdCatalogProdOffer catalogProdOffer = new ProdCatalogProdOffer(offering,validFor,price);
        if(!prodCatalogProdOffer.contains(catalogProdOffer)) {
            prodCatalogProdOffer.add(catalogProdOffer);
        }else {
            logger.warn("The offering is already publish . ");
        }
    }

    /**
     * 
     * @param offering
     */
    public void retiredOffering(ProductOffering offering) {
        checkProductOffering(offering);
        modifyOfferingValidTime(offering);
    }

    /**
     * 
     * @param offerings
     */
    public void retiredOffering(List<ProductOffering> offerings) {
        if(null == offerings || 0 == offerings.size()){
            logger.error("parameter is error £ºthe Object of ProductOffering is null . ");
            throw new IllegalArgumentException("offering should not be null .");
        }
        for(ProductOffering offering:offerings){
            retiredOffering(offering);
        }
    }


    /**
     * 
     * @param offering
     * @param time
     */
    public List<ProductOfferingPrice> retrieveOfferingPrice(ProductOffering offering, int time) {
        // TODO - implement ProductCatalog.retrieveOfferingPrice
        return null;
    }

    /**
     * 
     * @param offering
     * @param timePeriod
     * @param price
     */
    public void specifyOfferingPrice(ProductOffering offering, TimePeriod timePeriod, ProductOfferingPrice price) {
        // TODO - implement ProductCatalog.specifyOfferingPrice
    }

    /**
     * 
     * @param offering
     * @param timePeriod
     * @param price
     */
    public void specifyOfferingPrice(ProductOffering offering, TimePeriod timePeriod, ProductOfferingPrice[] price) {
        // TODO - implement ProductCatalog.specifyOfferingPrice
    }

    /**
     * 
     * @param time
     */
    public List<ProdCatalogProdOffer> retrieveOffering(Date time) {
        List<ProdCatalogProdOffer> catalogProdOffers = new ArrayList<ProdCatalogProdOffer>();
        if(null != prodCatalogProdOffer){
            for(ProdCatalogProdOffer catalogProdOffer : prodCatalogProdOffer) {
                if( 0 == catalogProdOffer.getValidFor().isInTimePeriod(time)){
                    catalogProdOffers.add(catalogProdOffer);
                }
            }
        }
        return catalogProdOffers;
    }

    /**
     * 
     * @param price
     */
    public List<ProductOffering> retrieveOffering(ProductOfferingPrice price) {
        // TODO - implement ProductCatalog.retrieveOffering
        return null;
    }
    /**
     * check parameter is null
     */
    private void checkProductOffering(ProductOffering offering){
        if(null == offering){
            logger.error("parameter is error £ºthe Object of ProductOffering is null . ");
            throw new IllegalArgumentException("offering should not be null .");
        }
    }

    private ProdCatalogProdOffer retrieveProdCatalogProdOffer(ProductOffering offering){
        if(null != prodCatalogProdOffer){
            for(ProdCatalogProdOffer catalogProdOffer : prodCatalogProdOffer) {
                if(offering.equals(catalogProdOffer.getProdOffering()) && 0 == catalogProdOffer.getValidFor().isInTimePeriod(new Date())){
                    return catalogProdOffer;
                }
            }
        }
        return null;
    }
    private void modifyOfferingValidTime(ProductOffering offering){
        checkProductOffering(offering);
        if(null != prodCatalogProdOffer){
            for(ProdCatalogProdOffer catalogProdOffer : prodCatalogProdOffer) {
                if(offering.equals(catalogProdOffer.getProdOffering()) && 0 == catalogProdOffer.getValidFor().isInTimePeriod(new Date())){
                    TimePeriod validTime = new TimePeriod();
                    validTime.setEndDateTime(new Date());
                    validTime.setStartDateTime(catalogProdOffer.getValidFor().getStartDateTime());
                    catalogProdOffer.setValidFor(validTime);
                }
            }
        }else{
            logger.warn("the Object of ProductCatalog haven't ProductOffering . ");
        }
    }

    @Override
    public String toString() {
        Map<String, Object> resultMap = this.getBasicInfoToMap();
        resultMap.put("prodCatalogProdOffer", prodCatalogProdOffer);
        return CommonUtils.format(resultMap.toString());
    }
}