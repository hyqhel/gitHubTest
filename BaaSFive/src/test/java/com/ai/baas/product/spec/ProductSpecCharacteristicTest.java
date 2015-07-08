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

public class ProductSpecCharacteristicTest {
	private static final Logger logger = Logger.getLogger(ProductSpecCharacteristicTest.class);
	private ProductSpecCharacteristic prodSpecCharOwn=null;
	private static TimePeriod validFor;
	@Before
	public void createProductSpecCharacteristic(){
		prodSpecCharOwn = new ConfigurableProductSpecCharacteristic("1", "high", "1", validFor, "false",  1,  1, true, "height","");
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
		prodSpecCharOwn.addValue(prodSpecCharValue);
		assertEquals("check ProductSpecCharacteristic add value success",1,prodSpecCharOwn.getProdSpecCharValue().size());
		
		ProductSpecCharacteristicValue prodSpecCharValues = new ProductSpecCharacteristicValue("1", "GHz", validFor, "8", "", "");
		prodSpecCharOwn.addValue(prodSpecCharValues);
		assertEquals("check ProductSpecCharacteristic again  add value success",1,prodSpecCharOwn.getProdSpecCharValue().size());
		
		prodSpecCharValues = null;
		try {
			prodSpecCharOwn.addValue(prodSpecCharValues);
			fail("param is not illegal");
		} catch (Exception e) {
		}
		assertEquals("add empty value",1,prodSpecCharOwn.getProdSpecCharValue().size());
		prodSpecCharValues = new ProductSpecCharacteristicValue("1", "cm", validFor, "8", "", "");
		prodSpecCharOwn.addValue(prodSpecCharValues);
		assertEquals("add new create  same value",2,prodSpecCharOwn.getProdSpecCharValue().size());
		
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
		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
		
		assertEquals("check add realte",1,prodSpecCharOwn.getProdSpecCharRelationship().size());
		 
		assertEquals("check add the type value is right","1",prodSpecCharOwn.getProdSpecCharRelationship().get(0).getCharRelationshipType());
		 
		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
		assertEquals("add same relate again",1,prodSpecCharOwn.getProdSpecCharRelationship().size());
		 
		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.DEPENDENCY.getValue(), validFor);
		assertEquals("add not same relate again",2,prodSpecCharOwn.getProdSpecCharRelationship().size());
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
	public void testSetDefaultValue(){
		
		ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1", "GHz", validFor, "8",false);
		prodSpecCharOwn.specifyDefaultValue(prodSpecCharValue);
		assertNull("specify one defalut value to a empty value ProductSpecCharacteristic",prodSpecCharOwn.getProdSpecCharValue());
		
		prodSpecCharOwn.addValue(prodSpecCharValue);
		prodSpecCharValue = null;
		try {
			prodSpecCharOwn.specifyDefaultValue(prodSpecCharValue);
			fail("param is not illegal");
		} catch (Exception e) {
		}
		assertFalse("specify one null defalut value",prodSpecCharOwn.getProdSpecCharValue().get(0).isIsDefault());
		
		prodSpecCharValue =  new ProductSpecCharacteristicValue("1", "cm", validFor, "9",false);
		prodSpecCharOwn.specifyDefaultValue(prodSpecCharValue);
		assertFalse("specify one defalut value but not exists ProductSpecCharacteristicValue",prodSpecCharOwn.getProdSpecCharValue().get(0).isIsDefault());
		
		prodSpecCharValue =  new ProductSpecCharacteristicValue("1", "GHz", validFor, "8",false);
		prodSpecCharOwn.specifyDefaultValue(prodSpecCharValue);
		assertTrue("specify one defalut value from ProductSpecCharacteristicValue",prodSpecCharOwn.getProdSpecCharValue().get(0).isIsDefault());
		
		
	}
}
