package com.ai.baas.product.controller;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.enums.ProdOfferingEnum;
import com.ai.baas.common.util.CommonUtils;
import com.ai.baas.product.offering.ProductOffering;
import com.ai.baas.product.offering.SimpleProductOffering;
import com.ai.baas.product.offering.TestProductOfferingData;
import com.ai.baas.product.offering.catalog.ProdCatalogProdOffer;
import com.ai.baas.product.offering.catalog.ProductCatalog;
import com.ai.baas.product.spec.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by huangyq3 on 2015-07-10.
 */
public class ProductCatalogController {

    private Logger logger = Logger.getLogger(ProductCatalogController.class);

    private com.ai.baas.product.offering.catalog.ProductCatalog pcata = null;
    private List<ProductSpecCharacteristic> productSpecChars;
    private ProductSpecification specification128 = null;
    private ProductSpecification specification256 = null;
    private ProductSpecification specification512 = null;
    private ProductOffering offering128 = null;
    private ProductOffering offering256 = null;
    private ProductOffering offering512 = null;

    @Before
    public void setUp() {
        //create Char
        createProductSpecChar();
        //create spec
        createProductSpec();
        //create offering
        createProductOffering();

        TimePeriod validFor = new TimePeriod("2015-06-04 10:20:00", "2015-08-26 10:20:00");
        pcata = new com.ai.baas.product.offering.catalog.ProductCatalog("1", "13 英寸配备 Retina 显示屏的 MacBook Pro",
                ProdOfferingEnum.ProductCatalogType.WEB.getValue(), validFor);

    }


    @Test
    public void publishOffering() {
        logger.info("当前Catalog：" + CommonUtils.format(this.pcata.getBasicInfoToMap().toString()));
        logger.info("发布前catalog内Offering总数：" + (this.pcata.getProdCatalogProdOffer() == null ? 0 : this.pcata
                .getProdCatalogProdOffer().size()));
        TimePeriod validForPublish = new TimePeriod("2015-06-04 10:20:00", "2015-07-26 10:20:00");
        pcata.publishOffering(offering128, validForPublish);
        pcata.publishOffering(offering256, validForPublish);
        pcata.publishOffering(offering512, validForPublish);
        logger.info("发布后catalog内Offering总数：" + this.pcata.getProdCatalogProdOffer().size());
        logger.info("Catalog内Offering基本信息：");
        for (int i = 0; i < this.pcata.getProdCatalogProdOffer().size(); i++) {
            logger.info("\n" + CommonUtils.format(this.pcata.getProdCatalogProdOffer().get(i).getProdOffering()
                    .getBasicInfoToMap().toString()));
        }
    }

    @Test
    public void searchOffering() {
        TimePeriod validForPublish = new TimePeriod("2015-06-04 10:20:00", "2015-07-26 10:20:00");
        pcata.publishOffering(offering128, validForPublish);
        pcata.publishOffering(offering256, validForPublish);
        pcata.publishOffering(offering512, validForPublish);

        logger.info("Catalog内Offering总数：" + this.pcata.getProdCatalogProdOffer().size());
        logger.info("Catalog内Offering基本信息：");
        for (int i = 0; i < this.pcata.getProdCatalogProdOffer().size(); i++) {
            logger.info("\n" + CommonUtils.format(this.pcata.getProdCatalogProdOffer().get(i).getProdOffering()
                    .getBasicInfoToMap().toString()));
        }


        String charUseName = "存储";
        String value = "128";


        List<ProdCatalogProdOffer> searchOfferingLastResult = new ArrayList<ProdCatalogProdOffer>();
        List<ProdCatalogProdOffer> searchOfferingResult = pcata.retrieveOffering(new Date());
        if (null != searchOfferingResult) {
            for (ProdCatalogProdOffer pcpr : searchOfferingResult) {
                SimpleProductOffering spo = (SimpleProductOffering) pcpr.getProdOffering();
                Set<ProductSpecCharUse> specCharUse = spo.getProductSpecification().getProdSpecChar();
                for (ProductSpecCharUse pscu : specCharUse) {
                    if (charUseName.equals(pscu.getName())) {
                        List<ProdSpecCharValueUse> pcvu = pscu.getProdSpecCharValue();
                        if (null != pcvu) {
                            for (ProdSpecCharValueUse pc : pcvu) {
                                if (value.equals(pc.getProdSpecCharValue().getValue())) {
                                    searchOfferingLastResult.add(pcpr);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        logger.info("查询条件：charUseName=" + charUseName + " charValue=" + value);
        logger.info("查询到的Offering总数：" + searchOfferingLastResult.size());
        logger.info("Offering内容：");
        for (int i = 0; i < searchOfferingLastResult.size(); i++) {
            logger.info("\n" + CommonUtils.format(searchOfferingLastResult.get(i).getProdOffering().getBasicInfoToMap
                    ().toString()));
        }

    }

    @Test
    public void retiredOffering() {
        logger.info("当前Catalog：" + CommonUtils.format(this.pcata.getBasicInfoToMap().toString()));

        TimePeriod validForPublish = new TimePeriod("2015-06-04 10:20:00", "2015-07-26 10:20:00");
        pcata.publishOffering(offering128, validForPublish);
        pcata.publishOffering(offering256, validForPublish);
        pcata.publishOffering(offering512, validForPublish);

        logger.info("Catalog内Offering总数：" + this.pcata.getProdCatalogProdOffer().size());
        logger.info("Catalog内Offering基本信息：");
        for (int i = 0; i < this.pcata.getProdCatalogProdOffer().size(); i++) {
            logger.info("\n" + CommonUtils.format(this.pcata.getProdCatalogProdOffer().get(i).getProdOffering()
                    .getBasicInfoToMap().toString()));
        }

        logger.info("将以下Offering下架：\n" + offering512.getBasicInfoToMap().toString());
        pcata.retiredOffering(offering512);
        List<ProdCatalogProdOffer> prodCatalogProdOffers = this.pcata.retrieveOffering(new Date());
        logger.info("下架后catalog内Offering总数：" + prodCatalogProdOffers.size());
        logger.info("Offering内容：");
        for (int i = 0; i < prodCatalogProdOffers.size(); i++) {
            logger.info("\n" + CommonUtils.format(prodCatalogProdOffers.get(i).getProdOffering().getBasicInfoToMap
                    ().toString()));
        }
    }

    public void createProductSpecChar() {

        productSpecChars = new ArrayList<ProductSpecCharacteristic>();
        for (int i = 0; i < TestProductSpecificationData.specChar.length; i++) {
            String ID = TestProductSpecificationData.specChar[i][0].toString();
            ProductSpecCharacteristic productSpecCharProcessor1 = null;
            if (Boolean.parseBoolean(TestProductSpecificationData.specChar[i][7].toString())) {
                productSpecCharProcessor1 = new ConfigurableProductSpecCharacteristic(ID,
                        TestProductSpecificationData.specChar[i][1].toString(),
                        TestProductSpecificationData.specChar[i][2].toString(),
                        (TimePeriod) TestProductSpecificationData.specChar[i][3],
                        TestProductSpecificationData.specChar[i][4].toString(),
                        Integer.parseInt(TestProductSpecificationData.specChar[i][5].toString()),
                        Integer.parseInt(TestProductSpecificationData.specChar[i][6].toString()));
            } else {
                productSpecCharProcessor1 = new ProductSpecCharacteristic(ID,
                        TestProductSpecificationData.specChar[i][1].toString(),
                        TestProductSpecificationData.specChar[i][2].toString(),
                        (TimePeriod) TestProductSpecificationData.specChar[i][3],
                        TestProductSpecificationData.specChar[i][4].toString(),
                        Integer.parseInt(TestProductSpecificationData.specChar[i][5].toString()),
                        Integer.parseInt(TestProductSpecificationData.specChar[i][6].toString()));
            }

            for (int j = 0; j < TestProductSpecificationData.specCharValue.length; j++) {
                if (ID.equals(TestProductSpecificationData.specCharValue[j][0].toString())) {
                    ProductSpecCharacteristicValue oneprocessorValue1 = new ProductSpecCharacteristicValue(
                            TestProductSpecificationData.specCharValue[j][1].toString(),
                            Boolean.parseBoolean(TestProductSpecificationData.specCharValue[j][2].toString()),
                            TestProductSpecificationData.specCharValue[j][3].toString(),
                            (TimePeriod) TestProductSpecificationData.specCharValue[j][4],
                            TestProductSpecificationData.specCharValue[j][5].toString());
                    productSpecCharProcessor1.addValue(oneprocessorValue1);
                }
            }
            productSpecChars.add(productSpecCharProcessor1);
        }
        for (int i = 0; i < TestProductSpecificationData.relateSpecChar.length; i++) {
            String srcId = TestProductSpecificationData.relateSpecChar[i][0].toString();
            String targetId = TestProductSpecificationData.relateSpecChar[i][1].toString();
            ProductSpecCharacteristic srcChar = this.getProdSpecCharById(srcId);
            ProductSpecCharacteristic targetChar = this.getProdSpecCharById(targetId);

            srcChar.addRelatedCharacteristic(targetChar, TestProductSpecificationData.relateSpecChar[i][2].toString(),
                    (TimePeriod) TestProductSpecificationData.relateSpecChar[i][4], Integer.parseInt
                            (TestProductSpecificationData.relateSpecChar[i][3].toString()));
        }
    }

    public void createProductSpec() {

        // 创建规格（2.7GHz 处理器 128 GB 存储容量）
        specification128 = createProductSpecification(
                TestProductSpecificationData.specParameter1, TestProductSpecificationData.one_charData);
        // 创建规格（2.7GHz 处理器 256 GB 存储容量）
        specification256 = createProductSpecification(
                TestProductSpecificationData.specParameter2, TestProductSpecificationData.two_charData);
        // 创建规格（2.9GHz 处理器 512GB 存储容量）
        specification512 = createProductSpecification(
                TestProductSpecificationData.specParameter3, TestProductSpecificationData.three_charData);
    }

    /**
     * 创建规格
     *
     * @return
     * @throws Exception
     */
    public ProductSpecification createProductSpecification(Object[] specParameter, Object[][] charData) {
        ProductSpecification productSpec = null;
        if (specParameter != null) {
            if ("AtomicProductSpecification".equals(specParameter[10].toString())) {
                productSpec = new AtomicProductSpecification(specParameter[0].toString(), specParameter[1].toString(),
                        specParameter[2].toString());
            } else {
                productSpec = new CompositeProductSpecification(specParameter[0].toString(),
                        specParameter[1].toString(), specParameter[2].toString());
            }

            for (int i = 0; i < charData.length; i++) {

                ProductSpecCharacteristic prodSpecChar = null;
                prodSpecChar = this.getCharByCharId(charData[i][0].toString());

                productSpec
                        .addCharacteristic(charData[i][4].toString(), prodSpecChar, Boolean.parseBoolean(charData[i][1].toString()),
                                Boolean.parseBoolean(charData[i][2].toString()),
                                (TimePeriod) charData[i][3], charData[i][5].toString(),
                                Integer.parseInt(charData[i][6].toString()), Integer.parseInt(charData[i][7].toString()),
                                Boolean.parseBoolean(charData[i][8].toString()),
                                charData[i][9].toString());

                if (Boolean.parseBoolean(charData[i][10].toString())) {
                    ProductSpecCharacteristicValue[] values = this.getCharValue(prodSpecChar,
                            (String[]) charData[i][11]);
                    if (values != null) {
                        for (int j = 0; j < values.length; j++) {
                            productSpec.attachCharacteristicValue(charData[i][4].toString(), prodSpecChar, values[j],
                                    ((boolean[]) charData[i][12])[j], (TimePeriod) specParameter[4]);
                        }
                    }
                }
            }

            return productSpec;
        }
        return null;
    }

    public ProductSpecCharacteristic getCharByCharId(String id) {
        ProductSpecCharacteristic prodSpecChar = null;
        for (int i = 0; i < productSpecChars.size(); i++) {
            prodSpecChar = productSpecChars.get(i);
            if (id.equals(prodSpecChar.getID())) {
                return prodSpecChar;
            }
        }
        return null;

    }

    public ProductSpecCharacteristicValue[] getCharValue(ProductSpecCharacteristic characteristic, String[] values) {
        if (values != null) {
            Set<ProductSpecCharacteristicValue> productValues = characteristic.getProdSpecCharValue();
            List<ProductSpecCharacteristicValue> prodSpecChars = new ArrayList<ProductSpecCharacteristicValue>();

            for (String value : values) {
                for (ProductSpecCharacteristicValue productValue : productValues) {
                    if (value.equals(productValue.getValue())) {
                        prodSpecChars.add(productValue);
                        break;
                    }
                }
            }
            return (ProductSpecCharacteristicValue[]) prodSpecChars
                    .toArray(new ProductSpecCharacteristicValue[prodSpecChars.size()]);
        }
        return null;

    }

    public ProductSpecCharacteristic getProdSpecCharById(String id) {
        if (productSpecChars != null) {
            for (ProductSpecCharacteristic productSpecCharacteristic : productSpecChars) {
                if (id.equals(productSpecCharacteristic.getID())) {
                    return productSpecCharacteristic;
                }
            }
        }
        return null;
    }

    private SimpleProductOffering createSimpleProductOffering(Object[] obj, ProductSpecification prodSpec) {
        return new SimpleProductOffering((String) obj[0], (String) obj[1], (String) obj[2], (TimePeriod) obj[3], prodSpec);
    }

    public void createProductOffering() {
        this.offering128 = createSimpleProductOffering(TestProductOfferingData.offering[0], specification128);
        this.offering256 = createSimpleProductOffering(TestProductOfferingData.offering[1], specification256);
        this.offering512 = createSimpleProductOffering(TestProductOfferingData.offering[2], specification512);
    }

    @After
    public void printCatalog(){
        //logger.info(this.pcata.toString());
    }

}
