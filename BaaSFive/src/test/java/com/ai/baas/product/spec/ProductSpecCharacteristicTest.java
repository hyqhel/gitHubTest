package com.ai.baas.product.spec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
		prodSpecCharOwn = new ConfigurableProductSpecCharacteristic("1", "深度", "", validFor, "false",  1,  1, true, "height","");
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
		assertEquals("判断是否成功加上了value",1,prodSpecCharOwn.getProdSpecCharValue().size());
		
		ProductSpecCharacteristicValue prodSpecCharValues = new ProductSpecCharacteristicValue("1", "GHz", validFor, "8", "", "");
		prodSpecCharOwn.addValue(prodSpecCharValues);
		assertEquals("判断再次加相同的value",1,prodSpecCharOwn.getProdSpecCharValue().size());
		
		prodSpecCharValues = null;
		prodSpecCharOwn.addValue(prodSpecCharValues);
		
		assertEquals("加空value",1,prodSpecCharOwn.getProdSpecCharValue().size());
		
		prodSpecCharValues = new ProductSpecCharacteristicValue("1", "cm", validFor, "8", "", "");
		prodSpecCharOwn.addValue(prodSpecCharValues);
		assertEquals("再次加新建的值相同的value",2,prodSpecCharOwn.getProdSpecCharValue().size());
		
		prodSpecCharOwn.addValue(prodSpecCharValues);
		assertEquals("t再次加同一对象value",2,prodSpecCharOwn.getProdSpecCharValue().size());
	} 
	@Test
	public void  testRemoveValue(){
		ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1", "GHz", validFor, "8", "", "");
		prodSpecCharOwn.removeValue(prodSpecCharValue);
		assertNull("没有value时删除不存在的value",prodSpecCharOwn.getProdSpecCharValue());
		
		prodSpecCharOwn.addValue(prodSpecCharValue);
		prodSpecCharOwn.removeValue(prodSpecCharValue);
		
		assertEquals("删除存在的value",0,prodSpecCharOwn.getProdSpecCharValue().size());
		
		assertEquals("value大小为0时再删除value",0,prodSpecCharOwn.getProdSpecCharValue().size());
	} 
	@Test
	public void  testAddRelatedCharacteristic(){
		ProductSpecCharacteristic prodSpecCharRelate = new ProductSpecCharacteristic("2", "尺寸与重量", "", validFor, "true",  1,  1, true, "compistchar","");
		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
		
		assertEquals("判断是否加了关系",1,prodSpecCharOwn.getProdSpecCharRelationship().size());
		 
		assertEquals("判断添加的关系类型","1",prodSpecCharOwn.getProdSpecCharRelationship().get(0).getCharRelationshipType());
		 
		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
		assertEquals("再次添加相同关系",1,prodSpecCharOwn.getProdSpecCharRelationship().size());
		 
		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.DEPENDENCY.getValue(), validFor);
		assertEquals("再次添加不相同关系",2,prodSpecCharOwn.getProdSpecCharRelationship().size());
	}
	
	@Test
	public void  testRemoveRelatedCharacteristic(){
		ProductSpecCharacteristic prodSpecCharRelate = new ProductSpecCharacteristic("2", "尺寸与重量", "", validFor, "true",  1,  1, true, "compistchar","");
		prodSpecCharOwn.removeRelatedCharacteristic(prodSpecCharRelate);
		assertNull("判断删除关系",prodSpecCharOwn.getProdSpecCharRelationship());
		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
		
		ProductSpecCharacteristic prodSpecCharRelatee = new ProductSpecCharacteristic("1", "颜色", "", validFor, "true",  1,  1, false, "compistchar","");
		prodSpecCharOwn.removeRelatedCharacteristic(prodSpecCharRelatee);
		assertEquals("在已添加一个关系基础上删除不存在的关系",1,prodSpecCharOwn.getProdSpecCharRelationship().size());
		
		prodSpecCharOwn.removeRelatedCharacteristic(prodSpecCharRelate);
		assertEquals("判断删除关系",0,prodSpecCharOwn.getProdSpecCharRelationship().size());
	}
	
	@Test 
	public void testSetDefaultValue(){
		
		ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1", "GHz", validFor, "8",false);
		prodSpecCharOwn.setDefaultValue(prodSpecCharValue);
		assertNull("特征没有值，指定一个默认值",prodSpecCharOwn.getProdSpecCharValue());
		
		prodSpecCharOwn.addValue(prodSpecCharValue);
		prodSpecCharValue = null;
		prodSpecCharOwn.setDefaultValue(prodSpecCharValue);
		assertFalse("值为空时判断能否指定了默认值",prodSpecCharOwn.getProdSpecCharValue().get(0).isIsDefault());
		
		prodSpecCharValue =  new ProductSpecCharacteristicValue("1", "cm", validFor, "9",false);
		prodSpecCharOwn.setDefaultValue(prodSpecCharValue);
		assertFalse("指定一个不是特征的value值",prodSpecCharOwn.getProdSpecCharValue().get(0).isIsDefault());
		
		prodSpecCharValue =  new ProductSpecCharacteristicValue("1", "GHz", validFor, "8",false);
		prodSpecCharOwn.setDefaultValue(prodSpecCharValue);
		assertTrue("指定一个是特征value值默认值",prodSpecCharOwn.getProdSpecCharValue().get(0).isIsDefault());
		
		
	}
}
