package com.ai.baas.product.offering.catalog;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.enums.ProdOfferingEnum;
import com.ai.baas.common.util.DateUtils;
import com.ai.baas.product.offering.BundledProdOfferOption;
import com.ai.baas.product.offering.ProductOffering;
import com.ai.baas.product.offering.SimpleProductOffering;
import com.ai.baas.product.spec.AtomicProductSpecification;
import com.ai.baas.product.spec.ProductSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Created by huangyq3 on 2015-07-09.
 */
public class ProductCatalogTest {
    private ProductOffering poff = null;
    private ProductCatalog pcata = null;
    @Before
    public void initCatalog(){
        TimePeriod validFor = new TimePeriod("2015-06-04 10:20:00", "2015-06-26 10:20:00");
        pcata = new ProductCatalog("1","13 ´ç", ProdOfferingEnum.ProductCatalogType.BOOK.getValue(),validFor);

        String id = "0001OF";
        String name = "11 Ó¢´ç MacBook Air";
        String description = "1.6GHz Ë«ºË Intel Core i5 ´¦ÀíÆ÷£¬Turbo Boost ¸ß´ï 2.7GHz";

        ProductSpecification  prodSpec = new AtomicProductSpecification("001SP", "11 Ó¢´ç MacBook Air SPEC", "Mac Air");
        SimpleProductOffering offering = new SimpleProductOffering(id, name, description, validFor, prodSpec);
    }
    @Test
    public void testPublishOffering(){
        ProductOffering offering = null ;
        TimePeriod validFor = new TimePeriod("2015-06-04 10:20:00", "2015-06-26 10:20:00");

        pcata.publishOffering(offering,validFor);
        assertEquals("publish one null offering to catalog", 0, pcata.getProdCatalogProdOffer().size());

        List<ProdCatalogProdOffer> expectedProdCatalogProdList = new ArrayList<ProdCatalogProdOffer>();
        ProdCatalogProdOffer expectedSubOffering1 = new ProdCatalogProdOffer(offering,validFor);
        expectedProdCatalogProdList.add(expectedSubOffering1);

        pcata.publishOffering(poff, validFor);
        assertEquals("publish one null offering to catalog,check size ", 1, pcata.getProdCatalogProdOffer().size());
        assertEquals("publish one null offering to catalog,check content", pcata.getProdCatalogProdOffer(), expectedProdCatalogProdList);


        TimePeriod validFor1 = new TimePeriod("2015-06-04 10:20:00", "2015-07-26 10:20:00");
        pcata.publishOffering(poff, validFor1);
        assertEquals("publish one null offering to catalog,check size ", 1, pcata.getProdCatalogProdOffer().size());
        assertEquals("publish one null offering to catalog,check content", pcata.getProdCatalogProdOffer(), expectedProdCatalogProdList);
    }

    @Test
    public void testRetiredOffering(){
        ProductOffering offering = null ;
        pcata.retiredOffering(offering);
        assertNull("retired  one null offering", pcata.getProdCatalogProdOffer());

        TimePeriod validFor1 = new TimePeriod("2015-06-04 10:20:00", "2015-07-26 10:20:00");
        pcata.publishOffering(poff, validFor1);
        pcata.retiredOffering(poff);
        assertEquals("retired  one  offering", 0, pcata.getProdCatalogProdOffer().size());

        pcata.publishOffering(poff, validFor1);

        List<ProdCatalogProdOffer> expectedProdCatalogProdList = new ArrayList<ProdCatalogProdOffer>();
        ProdCatalogProdOffer expectedSubOffering1 = new ProdCatalogProdOffer(poff,validFor1);
        expectedProdCatalogProdList.add(expectedSubOffering1);
        ProductSpecification  prodSpec = new AtomicProductSpecification("001SP", "11 Ó¢´ç MacBook Air SPEC", "Mac Air");
        SimpleProductOffering offering1 = new SimpleProductOffering("00011F", "13 Ó¢´ç MacBook Air",  "1.6GHz Ë«ºË Intel Core i5 ´¦ÀíÆ÷£¬Turbo Boost ¸ß´ï 2.7GHz", validFor1, prodSpec);
        pcata.retiredOffering(offering1);
        assertEquals("retired  one  offering", pcata.getProdCatalogProdOffer(), expectedProdCatalogProdList);
    }

    @Test
    public void testRetrieveOfferingByTime(){
        TimePeriod validFor1 = new TimePeriod("2015-06-04 10:20:00", "2015-07-26 10:20:00");
        pcata.publishOffering(poff, validFor1);

        TimePeriod validFor2 = new TimePeriod("2015-04-04 10:20:00", "2015-08-26 10:20:00");
        ProductSpecification  prodSpec = new AtomicProductSpecification("001SP", "11 Ó¢´ç MacBook Air SPEC", "Mac Air");
        SimpleProductOffering offering1 = new SimpleProductOffering("00011F", "13 Ó¢´ç MacBook Air",  "1.6GHz Ë«ºË Intel Core i5 ´¦ÀíÆ÷£¬Turbo Boost ¸ß´ï 2.7GHz", validFor1, prodSpec);
        pcata.publishOffering(offering1, validFor2);

        List<ProdCatalogProdOffer> expectedProdCatalogProdList = new ArrayList<ProdCatalogProdOffer>();
        ProdCatalogProdOffer expectedSubOffering1 = new ProdCatalogProdOffer(poff,validFor1);
        expectedProdCatalogProdList.add(expectedSubOffering1);

        ProdCatalogProdOffer expectedSubOffering2 = new ProdCatalogProdOffer(offering1,validFor2);
        expectedProdCatalogProdList.add(expectedSubOffering2);

        pcata.retrieveOffering(new Date());
        assertEquals("retrieve   offering", 2, pcata.getProdCatalogProdOffer().size());
        assertEquals("retrieve   offering", pcata.getProdCatalogProdOffer(), expectedProdCatalogProdList);

        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        pcata.retrieveOffering(DateUtils.str2Date("2015-04-03 10:20:00", sim));
        assertEquals("retrieve   offering",0, pcata.getProdCatalogProdOffer().size());
    }
}
