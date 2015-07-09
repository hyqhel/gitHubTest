package com.ai.baas.product.spec;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.enums.ProdSpecEnum;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ProductSpecificationTest {
    private Logger logger = Logger.getLogger(ProductSpecificationTest.class);
    private ProductSpecification prodSpec = null;
    private ProductSpecification expectProdSpec = null;
    private TimePeriod validFor = null;
    private ProductSpecification srcProdSpec = null;

    @Before
    public void initProdSpec() {
        srcProdSpec = new AtomicProductSpecification("S001", "iPhone6", "Apple iPhone");
        validFor = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
        prodSpec = new AtomicProductSpecification("mac-13", "13-inch MacBook Pro", "apple");
        expectProdSpec = new AtomicProductSpecification("mac-13", "13-inch MacBook Pro", "apple");
    }

    @Test
    public void testAddCharacteristic() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[4]);
        Set<ProductSpecCharUse> expectCharUse = new HashSet<ProductSpecCharUse>();
        ProductSpecCharUse charUse = new ProductSpecCharUse(characteristic, false, false, validFor, "CPU");
        ProductSpecCharUse charUse2 = new ProductSpecCharUse(characteristic, false, false, validFor, "处理器(CPU)");
        expectCharUse.add(charUse);
        // 添加一个特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        assertEquals("添加一个特征", expectCharUse, prodSpec.getProdSpecChar());

        // 添加一个已存在的特征
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "CPU");
        assertEquals("添加一个已存在的特征", expectCharUse, prodSpec.getProdSpecChar());

        // 添加一个已存在的特征，使用名字不同
        expectCharUse.add(charUse2);
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "处理器(CPU)");
        assertEquals("添加一个已存在的特征，使用名字不同", 2, prodSpec.getProdSpecChar().size());
        assertEquals("添加一个已存在的特征，使用名字不同", expectCharUse, prodSpec.getProdSpecChar());

        // 添加一个特征为空
        try {
            prodSpec.addCharacteristic(null, false, false, validFor, "CPU");
            fail("添加一个特征为空");
        } catch (Exception e) {
        }

        // 添加一个特征为空，使用名为空null
        try {
            prodSpec.addCharacteristic(characteristic2, false, false, validFor, null);
            fail("添加一个特征为空，使用名为空null");
        } catch (Exception e) {
        }

        // 添加一个特征，使用名为空字符
        try {
            prodSpec.addCharacteristic(characteristic2, false, false, validFor, "");
            fail("添加一个特征，使用名为空字符");
        } catch (Exception e) {
        }
    }

    @Test
    public void testAttachCharacteristicValue() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic3 = this.createChar(TestProductSpecificationData.specChar[0]);

        ProductSpecCharacteristicValue charValue = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[10]);
        ProductSpecCharacteristicValue charValue4 = this.createValue(TestProductSpecificationData.specCharValue[10]);

        characteristic.addValue(charValue);
        characteristic.addValue(charValue3);

        characteristic2.addValue(charValue);
        characteristic2.addValue(charValue3);

        // 添加特征 //显示屏
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");

        ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue, false, validFor);

        // 添加一个特征值
        prodSpec.attachCharacteristicValue(characteristic, charValue, false, validFor, "CPU");
        assertEquals("添加一个特征值", 1, prodSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().size());
        assertTrue("添加一个特征值", prodSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().contains(charValueUse));

        // 添加一个已存在的特征值
        prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor, "CPU");
        assertEquals("添加一个已存在的特征值", 1, prodSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().size());
        assertTrue("添加一个已存在的特征值", prodSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().contains(charValueUse));

        // 添加一个特征值,特征值不属于该特征
        prodSpec.attachCharacteristicValue(characteristic, charValue4, false, validFor, "CPU");
        assertEquals("添加一个特征值,特征值不属于该特征", 1, prodSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().size());
        assertTrue("添加一个特征值,特征值不属于该特征", prodSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().contains(charValueUse));

        // 添加一个特征值,特征不属于该规格
        try {
            prodSpec.attachCharacteristicValue(characteristic3, charValue3, false, validFor, "CPU");
            fail("添加一个特征值,特征不属于该规格");
        } catch (Exception e) {
        }

        // 添加一个特征值为空
        try {
            prodSpec.attachCharacteristicValue(characteristic, null, false, validFor, "CPU");
            fail("添加一个特征值为空");
        } catch (Exception e) {
        }

        // 添加一个特征值,特征为空
        try {
            prodSpec.attachCharacteristicValue(null, charValue2, false, validFor, "CPU");
            fail("添加一个特征值,特征为空");
        } catch (Exception e) {
        }

        // 添加一个特征值,特征名为空
        try {
            prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor, "");
            fail("添加一个特征值,特征名为空");
        } catch (Exception e) {
        }
    }

    @Test
    public void testSpecifyDefaultCharacteristicValue() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
        characteristic.addValue(charValue1);
        characteristic.addValue(charValue2);
        // 添加特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");

        // 添加特征值
        prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor, "CPU");
        prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor, "CPU");

        Set<ProdSpecCharValueUse> expectCharValueUse = new HashSet<ProdSpecCharValueUse>();
        ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue2, true, validFor);
        ProdSpecCharValueUse charValueUse2 = new ProdSpecCharValueUse(charValue1, false, validFor);
        expectCharValueUse.add(charValueUse);
        expectCharValueUse.add(charValueUse2);

        Set<ProdSpecCharValueUse> expectCharValueUse2 = new HashSet<ProdSpecCharValueUse>();
        ProdSpecCharValueUse charValueUse3 = new ProdSpecCharValueUse(charValue1, true, validFor);
        ProdSpecCharValueUse charValueUse4 = new ProdSpecCharValueUse(charValue2, false, validFor);
        expectCharValueUse2.add(charValueUse3);
        expectCharValueUse2.add(charValueUse4);


        // 设置某一特征的默认值
        prodSpec.specifyDefaultCharacteristicValue(characteristic, charValue2, "CPU");
        assertEquals("设置某一特征的默认值", expectCharValueUse, prodSpec.getProdSpecChar().iterator().next().getProdSpecCharValue());

		// 设置某一特征的默认值，传入的值对象为null
        try {
            prodSpec.specifyDefaultCharacteristicValue(characteristic, null, "CPU");
            fail("设置某一特征的默认值，传入的值对象为null");
        } catch (Exception e) {
        }

        // 设置某一特征的默认值，传入的值对象为不是该特征所有的
        ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[11]);
        prodSpec.specifyDefaultCharacteristicValue(characteristic, charValue3, "CPU");
        assertEquals("设置某一特征的默认值，传入的值对象为不是该特征所有的", expectCharValueUse2, prodSpec.getProdSpecChar().iterator().next().getProdSpecCharValue());

		// 设置某一特征的默认值，传入的特征对象为null
        try {
            prodSpec.specifyDefaultCharacteristicValue(null, charValue2, "CPU");
            fail("设置某一特征的默认值，传入的特征对象为null");
        } catch (Exception e) {
        }

        // 设置某一特征的默认值，传入的特征名为空
        try {
            prodSpec.specifyDefaultCharacteristicValue(characteristic, charValue2, "");
            fail("设置某一特征的默认值，传入的特征名为空");
        } catch (Exception e) {
        }
    }

    @Test
    public void testRetrieveCharacteristic() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
        // 添加特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "CPU");

        // 查询当前时间点的特征
        List<ProductSpecCharUse> charUses = prodSpec.retrieveCharacteristic(new Date());
        assertNotNull("查询当前时间点的特征", charUses);
        assertEquals("查询当前时间点的特征", 2, charUses.size());
    }

    @Test
    public void testRetrieveCharacteristicValue() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        // 添加特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");

        ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
        // 添加特征值
        prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor, "");
        prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor, "");

		// 查询某一特征在当前时间点的值
        List<ProdSpecCharValueUse> charValueUses = prodSpec.retrieveCharacteristicValue(characteristic, new Date(), "CPU");
        assertNotNull("查询某一特征在当前时间点的值", charValueUses);
		assertEquals("查询某一特征在当前时间点的值", 2, charValueUses.size());
    }

    @Test
    public void testRetrieveRootCharacteristic() {
        //TODO
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
        ProductSpecCharacteristic characteristic3 = this.createChar(TestProductSpecificationData.specChar[6]);
        ProductSpecCharacteristic characteristic4 = this.createChar(TestProductSpecificationData.specChar[7]);
        ProductSpecCharacteristic characteristic5 = this.createChar(TestProductSpecificationData.specChar[8]);
        ProductSpecCharacteristic characteristic6 = this.createChar(TestProductSpecificationData.specChar[9]);
        // 添加Char聚合关系
        characteristic2.addRelatedCharacteristic(characteristic3, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor, 1);
        characteristic2.addRelatedCharacteristic(characteristic4, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor, 1);
        characteristic2.addRelatedCharacteristic(characteristic5, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor, 1);
        characteristic2.addRelatedCharacteristic(characteristic6, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor, 1);
		// 添加特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
		prodSpec.addCharacteristic(characteristic2, false, false, validFor, "尺寸和重量");
        prodSpec.addCharacteristic(characteristic3, false, false, validFor, "长");
        prodSpec.addCharacteristic(characteristic4, false, false, validFor, "宽");
		prodSpec.addCharacteristic(characteristic5, false, false, validFor, "高");
        prodSpec.addCharacteristic(characteristic6, false, false, validFor, "重量");

		// 查询规格的一级特征
        //List<ProductSpecCharUse> rootCharUses = prodSpec.retrieveRootCharacteristic();
        // assertNotNull("查询规格的一级特征", rootCharUses);
        // assertEquals("查询规格的一级特征", 2, rootCharUses.size());
    }

    @Test
    public void getLeafCharacteristic() {
        //TODO
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
        ProductSpecCharacteristic characteristic3 = this.createChar(TestProductSpecificationData.specChar[6]);
        ProductSpecCharacteristic characteristic4 = this.createChar(TestProductSpecificationData.specChar[7]);
        ProductSpecCharacteristic characteristic5 = this.createChar(TestProductSpecificationData.specChar[8]);
        ProductSpecCharacteristic characteristic6 = this.createChar(TestProductSpecificationData.specChar[9]);
        // 添加Char聚合关系
        characteristic2.addRelatedCharacteristic(characteristic3, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor, 1);
		characteristic2.addRelatedCharacteristic(characteristic4, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor, 1);
		characteristic2.addRelatedCharacteristic(characteristic5, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor, 1);
		characteristic2.addRelatedCharacteristic(characteristic6, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor, 1);
		// 添加特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
		prodSpec.addCharacteristic(characteristic2, false, false, validFor, "尺寸和重量");
		prodSpec.addCharacteristic(characteristic3, false, false, validFor, "长");
		prodSpec.addCharacteristic(characteristic4, false, false, validFor, "宽");
        prodSpec.addCharacteristic(characteristic5, false, false, validFor, "高");
		prodSpec.addCharacteristic(characteristic6, false, false, validFor, "重量");

		// 查询规格的某一特征的子特征
        // List<ProductSpecCharUse> leafCharUses = prodSpec.retrieveLeafCharacteristic(characteristic2, "", new Date());
        // assertNotNull("查询规格的某一特征的子特征", leafCharUses);
        // assertEquals("查询规格的某一特征的子特征", 4, leafCharUses.size());

        // 查询规格的某一特征的子特征
        //  List<ProductSpecCharUse> leafCharUses2 = prodSpec.retrieveLeafCharacteristic(null, "", new Date());
        //  assertNull("查询规格的某一特征的子特征", leafCharUses2);
    }

    @Test
    public void testSpecifyCardinality() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
        // 添加特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");

        ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
        // 添加特征值
        prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor, "");
        prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor, "");

		// 设置某一特征的Cardinality
        boolean retFlag = prodSpec.specifyCardinality(characteristic, 1, 5, "");
        assertTrue("设置某一特征的Cardinality", retFlag);

		// 设置Cardinality，特征为空
        retFlag = prodSpec.specifyCardinality(null, 1, 5, "");
        assertFalse("设置Cardinality，特征为空", retFlag);

        // 设置Cardinality，特征不是被用的
        retFlag = prodSpec.specifyCardinality(characteristic2, 1, 5, "");
        assertFalse("设置Cardinality，特征不是被用的", retFlag);
	}

    @Test
    public void testAddRelatedProdSpec() {

        // *********** Case1 **************
        ProductSpecification targetProdSpec = new AtomicProductSpecification("T001", "AppleCare For iPhone", "AppleCare");
        String type = ProdSpecEnum.ProdSpecRelationship.DEPENDENCY.getValue();
        TimePeriod validFor = new TimePeriod();
        List<ProductSpecificationRelationship> expectedRelatedSpecList = new ArrayList<ProductSpecificationRelationship>();
        ProductSpecificationRelationship expectedRelatedSpec = new ProductSpecificationRelationship(srcProdSpec,
                targetProdSpec, type, validFor);
        expectedRelatedSpecList.add(expectedRelatedSpec);
        this.srcProdSpec.addRelatedProdSpec(targetProdSpec, type, validFor);
        assertEquals("add a normal AtomicProductSpecification.", 1, this.srcProdSpec.getProdSpecRelationship().size());
        assertEquals(expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());

        // *********** Case2 **************
        // add the same AtomicProductSpecification and the same relationshipType again.
        ProductSpecification targetProdSpec2 = new AtomicProductSpecification("T001", "AppleCare For iPhone2",
                "AppleCare");
        try {
            this.srcProdSpec.addRelatedProdSpec(targetProdSpec2, type, validFor);
            fail("expected IllegalArgumentException for srcProdSpec");
        } catch (Exception e) {

        }
        assertEquals("add same AtomicProductSpecification and the same relationshipType again", 1, this.srcProdSpec.getProdSpecRelationship().size());
        assertEquals("add same AtomicProductSpecification and the same relationshipType again", expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());

        // *********** Case3 **************
        ProductSpecification targetProdSpec3 = new AtomicProductSpecification("T002", "AppleCare For iPhone2",
                "AppleCare");
        this.srcProdSpec.addRelatedProdSpec(targetProdSpec3, type, validFor);

        ProductSpecificationRelationship expectedRelatedSpec3 = new ProductSpecificationRelationship(srcProdSpec,
                targetProdSpec3, type, validFor);
        expectedRelatedSpecList.add(expectedRelatedSpec3);
        assertEquals("add a different AtomicProductSpecification and the same relationshipType again.", 2, this.srcProdSpec.getProdSpecRelationship().size());
        assertEquals("add a different AtomicProductSpecification and the same relationshipType again.", expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());

        // *********** Case4 **************
        // add the same AtomicProductSpecification and different relationshipType again.
        String type4 = ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue();
        ProductSpecification targetProdSpec4 = new AtomicProductSpecification("T001", "AppleCare For iPhone2",
                "AppleCare");
        this.srcProdSpec.addRelatedProdSpec(targetProdSpec4, type4, validFor);

        ProductSpecificationRelationship expectedRelatedSpec4 = new ProductSpecificationRelationship(srcProdSpec,
                targetProdSpec4, type4, validFor);
        expectedRelatedSpecList.add(expectedRelatedSpec4);
        assertEquals("add the same AtomicProductSpecification and different relationshipType again.", 3, this.srcProdSpec.getProdSpecRelationship().size());
        assertEquals("add the same AtomicProductSpecification and different relationshipType again.", expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());

        // *********** Case5 **************
        // add relationship with srcProdSpec itSelf.
        try {
            this.srcProdSpec.addRelatedProdSpec(this.srcProdSpec, type4, validFor);
            fail("expected IllegalArgumentException for srcProdSpec");
        } catch (IllegalArgumentException e) {
        }
        assertEquals("add relationship with srcProdSpec itSelf.", 3, this.srcProdSpec.getProdSpecRelationship().size());
        assertEquals("add relationship with srcProdSpec itSelf.", expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());
    }

    @Test
    public void testRetrieveRelatedProdSpec() {

        // *********** Case1 *************
        String dependencyType = ProdSpecEnum.ProdSpecRelationship.DEPENDENCY.getValue();
        String aggregationType = ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue();
        ProductSpecification targetProdSpecDependency1 = new AtomicProductSpecification("T001", "AppleCare For iPhone",
                "AppleCare");
        ProductSpecification targetProdSpecAggregation1 = new AtomicProductSpecification("T002",
                "AppleCare For iPhone", "AppleCare");
        TimePeriod validFor = new TimePeriod();

        List<ProductSpecification> expectedRelatedSpecList = new ArrayList<ProductSpecification>();
        ProductSpecification expectedTargetProdSpec = new AtomicProductSpecification("T002", "AppleCare For iPhone",
                "AppleCare");
        expectedRelatedSpecList.add(expectedTargetProdSpec);

        this.srcProdSpec.addRelatedProdSpec(targetProdSpecDependency1, dependencyType, validFor);
        this.srcProdSpec.addRelatedProdSpec(targetProdSpecAggregation1, aggregationType, validFor);
        List<ProductSpecification> productSpecificationList = this.srcProdSpec.retrieveRelatedProdSpec(aggregationType);
        assertEquals("retrieve specification of a relationshipType from more than 2 type.", 1, productSpecificationList.size());
		assertEquals("retrieve specification of a relationshipType from more than 2 type.", expectedRelatedSpecList, productSpecificationList);

		// *********** Case2 **************
        List<ProductSpecification> productSpecificationList2 = this.srcProdSpec
                .retrieveRelatedProdSpec(ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getValue());
        assertEquals("retrieve ProductSpecification from productSpecRelationships by a no existent type.", 0, productSpecificationList2.size());

		// *********** Case3 **************
        try {
            List<ProductSpecification> productSpecificationList3 = this.srcProdSpec.retrieveRelatedProdSpec(null);
            fail("Case 3 ： fail when type is null。");
        } catch (IllegalArgumentException e) {
        }

        // *********** Case4 **************
        this.srcProdSpec.getProdSpecRelationship().clear();
		List<ProductSpecification> productSpecificationList4 = this.srcProdSpec
                .retrieveRelatedProdSpec(aggregationType);
        assertEquals("retrieve ProductSpecification from empty relationships", 0, productSpecificationList4.size());
	}


    private ProductSpecCharacteristicValue createValue(Object[] obj) {
		//String valueType, boolean isDefault, String unitOfMeasure,
		//TimePeriod validFor, String value
		//String valueType, String unitOfMeasure, TimePeriod validFor, String value, boolean isDefault
        ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue((String) obj[1],
                (boolean) obj[2], (String) obj[3], (TimePeriod) obj[4], (String) obj[5]);
        return charValue;
    }

    private ProductSpecCharacteristic createChar(Object[] obj) {
        ProductSpecCharacteristic specChar = new ProductSpecCharacteristic((String) obj[0], (String) obj[1],
                (String) obj[2], (TimePeriod) obj[3], (String) obj[4], (Integer) obj[5], (Integer) obj[5]);
        return specChar;
    }
}
