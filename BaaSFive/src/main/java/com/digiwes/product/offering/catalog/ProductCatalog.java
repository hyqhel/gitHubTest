package com.digiwes.product.offering.catalog;

import java.util.*;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.catalog.Catalog;
import com.digiwes.common.util.CommonUtils;
import com.digiwes.product.offering.ProductOffering;
import com.digiwes.product.offering.price.ProductOfferingPrice;
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
        checkTimePeriod(validFor);
        if(null == prodCatalogProdOffer){
            prodCatalogProdOffer = new ArrayList<ProdCatalogProdOffer>();
        }
        ProdCatalogProdOffer catalogOffer = retrieveProdCatalogProdOffer(offering,validFor);
        if(null != catalogOffer){
            logger.warn("The offering is already publish the time .");
            return;
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
        checkTimePeriod(validFor);
        if(null == prodCatalogProdOffer){
            prodCatalogProdOffer = new ArrayList<ProdCatalogProdOffer>();
        }
        ProdCatalogProdOffer catalogOffer = retrieveProdCatalogProdOffer(offering,validFor);
        if(null != catalogOffer){
            logger.warn("The offering is already publish the time .");
            return;
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
    public boolean retiredOffering(ProductOffering offering) {
        checkProductOffering(offering);
        ProdCatalogProdOffer catalogOffer = retrieveCurrentProdCatalogProdOffer(offering, new Date());
        if(null != catalogOffer) {
            catalogOffer.getValidFor().setEndDateTime(new Date());
        }else{
            logger.warn("the Object of ProductOffering Has not been published to the ProductCatalog . ");
            return false;
        }
        return true;
    }

    /**
     *
     * @param offerings
     */
    public void retiredOffering(List<ProductOffering> offerings) {
        if(null == offerings || 0 == offerings.size()){
            logger.error("parameter is error ：the Object of ProductOffering is null . ");
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
            logger.error("parameter is error ：the Object of ProductOffering is null . ");
            throw new IllegalArgumentException("offering should not be null .");
        }
    }

    /**
     * check parameter is null
     */
    private void checkTimePeriod(TimePeriod validFor) {
        if (null == validFor) {
            logger.error("parameter is error ：the Object of TimePeriod is null . ");
            throw new IllegalArgumentException("validFor should not be null .");
        }else if(null == validFor.getStartDateTime()){
            logger.error("parameter is error ：the Object of TimePeriod's startDateTime is null . ");
            throw new IllegalArgumentException("startDateTime should not be null .");
        }else if(null == validFor.getEndDateTime()){
            logger.error("parameter is error ：the Object of TimePeriod's endDateTime is null . ");
            throw new IllegalArgumentException("endDateTime should not be null .");
        }
    }

    /**
     * retrieve the current work ProdCatalogProdOffer
     * @param offering
     * @param validFor
     * @return
     */
    private ProdCatalogProdOffer retrieveProdCatalogProdOffer(ProductOffering offering,TimePeriod validFor){
        if(null != prodCatalogProdOffer){
            for(ProdCatalogProdOffer catalogProdOffer : prodCatalogProdOffer) {
                if(offering.equals(catalogProdOffer.getProdOffering()) && catalogProdOffer.getValidFor().isOverlap(validFor)){
                        return catalogProdOffer;
                }
            }
        }
        return null;
    }
    private ProdCatalogProdOffer retrieveCurrentProdCatalogProdOffer(ProductOffering offering,Date time){
        if(null != prodCatalogProdOffer){
            for(ProdCatalogProdOffer catalogProdOffer : prodCatalogProdOffer) {
                if(offering.equals(catalogProdOffer.getProdOffering()) && 0 == catalogProdOffer.getValidFor().isInTimePeriod(time)){
                    return catalogProdOffer;
                }
            }
        }
        return null;
    }
    public boolean modifyOfferingValidTime(ProductOffering offering,TimePeriod oldValidFor,TimePeriod newValidFor){
        checkProductOffering(offering);
        checkTimePeriod(oldValidFor);
        checkTimePeriod(newValidFor);
        if(null != prodCatalogProdOffer){
            ProdCatalogProdOffer catalogProdOffer = retrieveProdCatalogProdOffer(offering,oldValidFor);
            if(null != catalogProdOffer) {
                catalogProdOffer.setValidFor(newValidFor);
            }else{
                logger.warn("the Object of ProductOffering Has not been published to the ProductCatalog . ");
                return false;
            }
        }else{
            logger.warn("the Object of ProductCatalog haven't ProductOffering . ");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        Map<String, Object> resultMap = this.getBasicInfoToMap();
        resultMap.put("prodCatalogProdOffer", prodCatalogProdOffer);
        return CommonUtils.format(resultMap.toString());
    }
}