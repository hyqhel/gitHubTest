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
        prodSpec.addCharacteristic("CPU",characteristic, false, false, validFor);
        assertEquals("and one characteristic ", expectCharUse, prodSpec.getProdSpecChar());

        prodSpec.addCharacteristic("CPU",characteristic2, false, false, validFor);
        assertEquals(" and one exists characteristic", expectCharUse, prodSpec.getProdSpecChar());

        expectCharUse.add(charUse2);
        prodSpec.addCharacteristic( "处理器(CPU)",characteristic2, false, false, validFor);
        assertEquals("and one exists characteristic but use different name，check size ", 2, prodSpec.getProdSpecChar().size());
        assertEquals("and one exists characteristic but use different name，check content", expectCharUse, prodSpec.getProdSpecChar());

        try {
            prodSpec.addCharacteristic("CPU",null, false, false, validFor);
            fail("add a null characteristic");
        } catch (IllegalArgumentException e) {
        }

        try {
            prodSpec.addCharacteristic(null,characteristic2, false, false, validFor);
            fail("add a characteristic and use characteristic by null name ");
        } catch (IllegalArgumentException e) {
        }

        try {
            prodSpec.addCharacteristic("",characteristic2, false, false, validFor);
            fail("add a characteristic and use characteristic by blank name");
        } catch (IllegalArgumentException e) {
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

        prodSpec.addCharacteristic("CPU", characteristic, false, false, validFor);
        ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue, false, validFor);

        prodSpec.attachCharacteristicValue( "CPU",characteristic, charValue, false, validFor);
        assertEquals("add a  characteristic value", 1, prodSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().size());
        assertTrue("add a  characteristic value", prodSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().contains(charValueUse));

        prodSpec.attachCharacteristicValue("CPU",characteristic, charValue2, false, validFor );
        assertEquals("add a exists  characteristic value and check size", 1, prodSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().size());
        assertTrue("add a exists  characteristic value and check context", prodSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().contains(charValueUse));

        try {
            prodSpec.attachCharacteristicValue("CPU",characteristic3, charValue3, false, validFor);
            fail("add a characteristic value but the characteristic  not exists in spec ");
        } catch (IllegalArgumentException e) {
        }

        try {
            prodSpec.attachCharacteristicValue("CPU",characteristic, null, false, validFor);
            fail("add a null value for characteristic");
        } catch (IllegalArgumentException e) {
        }

        try {
            prodSpec.attachCharacteristicValue("CPU",null, charValue2, false, validFor);
            fail("add a characteristic value but characteristic is null");
        } catch (IllegalArgumentException e) {
        }

        try {
            prodSpec.attachCharacteristicValue("",characteristic, charValue2, false, validFor);
            fail("add a characteristic value but characteristic name is null");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testSpecifyDefaultCharacteristicValue() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
        characteristic.addValue(charValue1);
        characteristic.addValue(charValue2);
        prodSpec.addCharacteristic("CPU",characteristic, false, false, validFor);
        prodSpec.attachCharacteristicValue("CPU",characteristic, charValue1, true, validFor);
        prodSpec.attachCharacteristicValue("CPU",characteristic, charValue2, false, validFor);

        Set<ProdSpecCharValueUse> expectCharValueUse = new HashSet<ProdSpecCharValueUse>();
        ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue2, true, validFor);
        ProdSpecCharValueUse charValueUse2 = new ProdSpecCharValueUse(charValue1, false, validFor);
        expectCharValueUse.add(charValueUse);
        expectCharValueUse.add(charValueUse2);

        List<ProdSpecCharValueUse> expectCharValueUse2 = new ArrayList<ProdSpecCharValueUse>();
        ProdSpecCharValueUse charValueUse3 = new ProdSpecCharValueUse(charValue1, true, validFor);
        ProdSpecCharValueUse charValueUse4 = new ProdSpecCharValueUse(charValue2, false, validFor);
        expectCharValueUse2.add(charValueUse3);
        expectCharValueUse2.add(charValueUse4);


        prodSpec.specifyDefaultCharacteristicValue("CPU", characteristic, charValue2);
        assertEquals("set one defalut value for a  characteristic ", expectCharValueUse2, prodSpec.getProdSpecChar().iterator().next().getProdSpecCharValue());

        try {
            prodSpec.specifyDefaultCharacteristicValue("CPU",characteristic, null);
            fail("set one defalut value for a characteristic by null value");
        } catch (IllegalArgumentException e) {
        }

        ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[11]);
        prodSpec.specifyDefaultCharacteristicValue("CPU", characteristic, charValue3);
        assertEquals("set one a default value for  characteristicuse,but the value not exists characteristic", expectCharValueUse2, prodSpec.getProdSpecChar().iterator().next().getProdSpecCharValue());

        try {
            prodSpec.specifyDefaultCharacteristicValue("CPU", null, charValue2);
            fail("set one null  characteristic a default value");
        } catch (IllegalArgumentException e) {
        }

        try {
            prodSpec.specifyDefaultCharacteristicValue("", characteristic, charValue2);
            fail("set one null name characteristicuse a default value");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testRetrieveCharacteristic() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);

        prodSpec.addCharacteristic( "CPU",characteristic, false, false, validFor);
        prodSpec.addCharacteristic("CPU",characteristic2, false, false, validFor);

        List<ProductSpecCharUse> charUses = prodSpec.retrieveCharacteristic(new Date());
        assertNotNull("search one characteristic  use by current time", charUses);
        assertEquals("search one characteristic  use by current time", 2, charUses.size());
    }

    @Test
    public void testRetrieveCharacteristicValue() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        prodSpec.addCharacteristic("CPU",characteristic, false, false, validFor);

        ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);

        characteristic.addValue(charValue1);
        characteristic.addValue(charValue2);

        prodSpec.attachCharacteristicValue("CPU",characteristic, charValue1, true, validFor);
        prodSpec.attachCharacteristicValue("CPU",characteristic, charValue2, false, validFor);

        List<ProdSpecCharValueUse> charValueUses = prodSpec.retrieveCharacteristicValue("CPU",characteristic, new Date());
        assertNotNull("search one characteristic value use by current time", charValueUses);
		assertEquals("search one characteristic value use by current time", 2, charValueUses.size());
    }



    @Test
    public void testSpecifyCardinality() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
        prodSpec.addCharacteristic("CPU", characteristic, false, false, validFor);

        ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);

        prodSpec.attachCharacteristicValue("CPU", characteristic, charValue1, true, validFor);
        prodSpec.attachCharacteristicValue("CPU",characteristic, charValue2, false, validFor);

        boolean retFlag = prodSpec.specifyCardinality("CPU",characteristic, 1, 5);
        assertTrue("set one characteristic Cardinality", retFlag);

        try{
            retFlag = prodSpec.specifyCardinality("",null, 1, 5);
            fail("set Cardinality，characteristic is null");
        }catch (IllegalArgumentException e ){
        }
        try{
            retFlag = prodSpec.specifyCardinality("",characteristic2, 1, 5);
            fail("set Cardinality， not use");
        }catch (IllegalArgumentException e ){
        }

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
		//String valueType, String unitOfMeasure, TimePeriod validFor, String value, boolean isDefault
        ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue((String) obj[1],
                 (String) obj[3], (TimePeriod) obj[4], (String) obj[5],(Boolean) obj[2]);
       return charValue;
    }

    private ProductSpecCharacteristic createChar(Object[] obj) {
        ProductSpecCharacteristic specChar = new ProductSpecCharacteristic((String) obj[0], (String) obj[1],
                (String) obj[2], (TimePeriod) obj[3], (String) obj[4], (Integer) obj[5], (Integer) obj[5]);
        return specChar;
    }
}
