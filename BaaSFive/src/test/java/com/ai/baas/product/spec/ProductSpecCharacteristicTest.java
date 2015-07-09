package com.ai.baas.product.spec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.enums.ProdSpecEnum;
import com.ai.baas.common.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecCharacteristicTest {
	private static final Logger logger = Logger.getLogger(ProductSpecCharacteristicTest.class);
	private ProductSpecCharacteristic prodSpecCharOwn=null;
	private ProductSpecCharacteristic exceptChar;
	private static TimePeriod validFor;
	@Before
	public void createProductSpecCharacteristic(){
		prodSpecCharOwn = new ConfigurableProductSpecCharacteristic("1", "high", "1", validFor, "false",  1,  1, true, "height","");
		exceptChar = prodSpecCharOwn;
	}
	@BeforeClass
	public static void initVliadFor(){
		validFor = new TimePeriod();
		String startDate = "2015-06-01";
		String endDate = "2015-08-01";
		validFor.setStartDateTime(DateUtils.str2Date(startDate, DateUtils.date_sdf));
		validFor.setEndDateTime(DateUtils.str2Date(endDate, DateUtils.date_sdf));
	}
	@Test
	public void  testAddValue(){
		ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1", "GHz", validFor, "8", "", "");
		//exceptChar.getProdSpecCharValue().add(prodSpecCharValue);
		prodSpecCharOwn.addValue(prodSpecCharValue);
		assertEquals("check ProductSpecCharacteristic add value success", 1, prodSpecCharOwn.getProdSpecCharValue().size());
		assertTrue("check ProductSpecCharacteristic  other content", prodSpecCharOwn.getProdSpecCharValue().contains(prodSpecCharValue));

		ProductSpecCharacteristicValue prodSpecCharValues = new ProductSpecCharacteristicValue("1", "GHz", validFor, "8", "", "");
		prodSpecCharOwn.addValue(prodSpecCharValues);
		assertEquals("check ProductSpecCharacteristic again  add value success", 1, prodSpecCharOwn.getProdSpecCharValue().size());
		assertTrue("check ProductSpecCharacteristic  other content",prodSpecCharOwn.getProdSpecCharValue().contains(prodSpecCharValue));

		prodSpecCharValues = null;
		try {
			prodSpecCharOwn.addValue(prodSpecCharValues);
			fail("param is not illegal");
		} catch (Exception e) {
		}
		prodSpecCharValues = new ProductSpecCharacteristicValue("1", "cm", validFor, "8", "", "");
		prodSpecCharOwn.addValue(prodSpecCharValues);
		assertEquals("add a different value",2,prodSpecCharOwn.getProdSpecCharValue().size());
		
		prodSpecCharOwn.addValue(prodSpecCharValues);
		assertEquals("add same project value agian",2,prodSpecCharOwn.getProdSpecCharValue().size());
	} 
	@Test
	public void  testRemoveValue(){
		ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1", "GHz", validFor, "8", "", "");
		prodSpecCharOwn.removeValue(prodSpecCharValue);
		assertNull("remove one not exists value", prodSpecCharOwn.getProdSpecCharValue());
		prodSpecCharOwn.addValue(prodSpecCharValue);
		prodSpecCharOwn.removeValue(prodSpecCharValue);
		assertEquals("remove one exists value",0,prodSpecCharOwn.getProdSpecCharValue().size());
	}
	@Test
	public void  testAddRelatedCharacteristic(){
		ProductSpecCharacteristic prodSpecCharRelate = new ProductSpecCharacteristic("2", "Size and weight", "1", validFor, "true",  1,  1, true, "compistchar","");
		List<ProductSpecCharRelationship>  exceptRelationship = new ArrayList<ProductSpecCharRelationship>();

		ProductSpecCharRelationship exceptShip = new ProductSpecCharRelationship(prodSpecCharOwn,prodSpecCharRelate,ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
		exceptRelationship.add(exceptShip);
		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
		assertEquals("check add realte", 1, prodSpecCharOwn.getProdSpecCharRelationship().size());
		assertEquals("check add the type value is right", exceptRelationship, prodSpecCharOwn.getProdSpecCharRelationship());

		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
		assertEquals("add a same relate characteristic and the validFor same too ", exceptRelationship, prodSpecCharOwn.getProdSpecCharRelationship());

		TimePeriod validFor2 = new TimePeriod("2015-07-15","2015-07-30");
		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor2);
		assertEquals("add a same relate characteristic but the validFor is in last one", exceptRelationship, prodSpecCharOwn.getProdSpecCharRelationship());

		TimePeriod validFor3 = new TimePeriod("2015-08-02","2015-09-01");
		ProductSpecCharRelationship exceptShip2 = new ProductSpecCharRelationship(prodSpecCharOwn,prodSpecCharRelate,ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor3);
		exceptRelationship.add(exceptShip2);
		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor3);
		assertEquals("add a same relate characteristic but the validFor is after last one", exceptRelationship, prodSpecCharOwn.getProdSpecCharRelationship());

		ProductSpecCharRelationship exceptShip3 = new ProductSpecCharRelationship(prodSpecCharOwn,prodSpecCharRelate,ProdSpecEnum.ProdSpecRelationship.DEPENDENCY.getValue(), validFor);
		exceptRelationship.add(exceptShip3);
		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.DEPENDENCY.getValue(), validFor);
		assertEquals("in the same time add a same relate characteristic but the relationshipType is different ", exceptRelationship, prodSpecCharOwn.getProdSpecCharRelationship());

		try {
			prodSpecCharOwn.addRelatedCharacteristic(null, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
			fail("add a relate characteristic but the characteristic is null");
		}catch(IllegalArgumentException e){}
	}
	
	@Test
	public void  testRemoveRelatedCharacteristic(){
		ProductSpecCharacteristic prodSpecCharRelate = new ProductSpecCharacteristic("2", "Size and weight", "1", validFor, "true",  1,  1, true, "compistchar","");
		prodSpecCharOwn.removeRelatedCharacteristic(prodSpecCharRelate);
		assertNull("check remove relate",prodSpecCharOwn.getProdSpecCharRelationship());
		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
		
		ProductSpecCharacteristic prodSpecCharRelatee = new ProductSpecCharacteristic("1", "color", "1", validFor, "true",  1,  1, false, "compistchar","");
		prodSpecCharOwn.removeRelatedCharacteristic(prodSpecCharRelatee);
		assertEquals("remove one not exists relate",1,prodSpecCharOwn.getProdSpecCharRelationship().size());
		
		prodSpecCharOwn.removeRelatedCharacteristic(prodSpecCharRelate);
		assertEquals("check remove relate",0,prodSpecCharOwn.getProdSpecCharRelationship().size());
	}
	
	@Test 
	public void testSpecifyDefaultValue(){
		
		ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1", "GHz", validFor, "8",false);
		prodSpecCharOwn.specifyDefaultValue(prodSpecCharValue);
		assertNull("specify one defalut value to a empty value ProductSpecCharacteristic", prodSpecCharOwn.getProdSpecCharValue());
		
		prodSpecCharOwn.addValue(prodSpecCharValue);
		prodSpecCharValue = null;
		try {
			prodSpecCharOwn.specifyDefaultValue(prodSpecCharValue);
			fail("param is not illegal");
		} catch (IllegalArgumentException e) {
		}

		prodSpecCharValue =  new ProductSpecCharacteristicValue("1", "cm", validFor, "9",false);
		prodSpecCharOwn.specifyDefaultValue(prodSpecCharValue);
		assertFalse("specify one defalut value but not exists ProductSpecCharacteristicValue", prodSpecCharOwn.getProdSpecCharValue().iterator().next().isIsDefault());
		assertFalse("specify one defalut value but not exists ProductSpecCharacteristicValue", prodSpecCharOwn.getProdSpecCharValue().contains(prodSpecCharValue));

		prodSpecCharValue =  new ProductSpecCharacteristicValue("1", "GHz", validFor, "8",false);
		prodSpecCharOwn.specifyDefaultValue(prodSpecCharValue);
		assertTrue("specify one defalut value from ProductSpecCharacteristicValue", prodSpecCharOwn.getProdSpecCharValue().iterator().next().isIsDefault());
		assertEquals("specify one defalut value from ProductSpecCharacteristicValue", 1,prodSpecCharOwn.getProdSpecCharValue().size());
		
		
	}
}
