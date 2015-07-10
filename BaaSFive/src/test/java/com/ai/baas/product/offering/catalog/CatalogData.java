package com.ai.baas.product.offering.catalog;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.enums.ProdOfferingEnum;
import com.ai.baas.product.offering.ProductOffering;
import com.ai.baas.product.offering.SimpleProductOffering;
import com.ai.baas.product.spec.ProdSpecCharValueUse;
import com.ai.baas.product.spec.ProductSpecCharUse;
import com.ai.baas.product.spec.ProductSpecCharacteristicValue;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by huangyq3 on 2015-07-10.
 */
public class CatalogData {
    private ProductCatalog pcata = null;
    private ProductOffering poff = null;
    @Before
    public  void setUp(){
        TimePeriod validFor = new TimePeriod("2015-06-04 10:20:00", "2015-08-26 10:20:00");
        pcata = new ProductCatalog("1","13 英寸配备 Retina 显示屏的 MacBook Pro", ProdOfferingEnum.ProductCatalogType.BOOK.getValue(),validFor);
    }
    @Test
    public void publishOffering(){
        TimePeriod validForPublish = new TimePeriod("2015-06-04 10:20:00", "2015-07-26 10:20:00");
        pcata.publishOffering(poff, validForPublish);
    }
    @Test
    public void searchOffering(){
        TimePeriod validForPublish = new TimePeriod("2015-06-04 10:20:00", "2015-07-26 10:20:00");
        String charUseName = "CPU";
        ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1", false,"GHz", validForPublish, "128");

        List<ProdCatalogProdOffer> searchOfferingLastResult = new ArrayList<ProdCatalogProdOffer>()
        List<ProdCatalogProdOffer> searchOfferingResult =  pcata.retrieveOffering(new Date());
        if(null != searchOfferingResult){
            for(ProdCatalogProdOffer pcpr : searchOfferingResult){
                SimpleProductOffering spo = (SimpleProductOffering) pcpr.getProdOffering();
                Set<ProductSpecCharUse> specCharUse = spo.getProductSpecification().getProdSpecChar();
                for(ProductSpecCharUse pscu : specCharUse){
                    if(charUseName.equals(pscu.getName())){
                        List<ProdSpecCharValueUse> pcvu = pscu.getProdSpecCharValue();
                        if(null == pcvu){
                            for(ProdSpecCharValueUse pc :pcvu){
                                if(pc.getProdSpecCharValue().equals(prodSpecCharValue)){
                                    searchOfferingLastResult.add(pcpr);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }



    }
}
