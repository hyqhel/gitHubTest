package com.ai.baas.product.spec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
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
	private static ProductSpecCharacteristic psc =null;
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
		logger.info("\t判断特征是否成功加上了value");
		assertEquals("判断是否成功加上了value",1,prodSpecCharOwn.getProdSpecCharValue().size());
		
		logger.info("\t对比特征所添加的值的类型是否相同");
		assertEquals("对比所添加的值的类型是否为1","1",prodSpecCharOwn.getProdSpecCharValue().get(0).getValueType());
		
		ProductSpecCharacteristicValue prodSpecCharValues = new ProductSpecCharacteristicValue("1", "GHz", validFor, "8", "", "");
		prodSpecCharOwn.addValue(prodSpecCharValues);
		
		logger.info("\t判断特征再次加相同的value");
		assertEquals("判断再次加相同的value",1,prodSpecCharOwn.getProdSpecCharValue().size());
	} 
	@Test
	public void  testRemoveValue(){
		ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1", "GHz", validFor, "8", "", "");
		
		logger.info("\t删除特征不存在的value");
		assertFalse("删除不存在的value",prodSpecCharOwn.removeValue(prodSpecCharValue));
		
		prodSpecCharOwn.addValue(prodSpecCharValue);
		prodSpecCharOwn.removeValue(prodSpecCharValue);
		
		logger.info("\t判断特证是否删除value成功");
		assertEquals("判断是否删除成功",0,prodSpecCharOwn.getProdSpecCharValue().size());
		
		logger.info("\t删除特征不存在的value");
		assertFalse("删除不存在的value",prodSpecCharOwn.removeValue(prodSpecCharValue));
	} 
	@Test
	public void  testAddRelatedCharacteristic(){
		ProductSpecCharacteristic prodSpecCharRelate = new ProductSpecCharacteristic("2", "尺寸与重量", "", validFor, "true",  1,  1, true, "compistchar","");
		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
		
		logger.info("\t判断特征是否加上了关系");
		assertEquals("判断是否加了关系",1,prodSpecCharOwn.getProdSpecCharRelationship().size());
		 
		logger.info("\t判断特征添加的关系类型是否正确");
		assertEquals("判断添加的关系类型","1",prodSpecCharOwn.getProdSpecCharRelationship().get(0).getCharRelationshipType());
		 
		logger.info("\t再次为该特征关联相同特征相同关系");
		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
		assertEquals("再次添加相同关系",1,prodSpecCharOwn.getProdSpecCharRelationship().size());
		 
		logger.info("\t再次为该特征关联相同特征不同关系");
		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.DEPENDENCY.getValue(), validFor);
		assertEquals("再次添加不相同关系",2,prodSpecCharOwn.getProdSpecCharRelationship().size());
	}
	
	@Test
	public void  testRemoveRelatedCharacteristic(){
		ProductSpecCharacteristic prodSpecCharRelate = new ProductSpecCharacteristic("2", "尺寸与重量", "", validFor, "true",  1,  1, true, "compistchar","");
		prodSpecCharOwn.removeRelatedCharacteristic(prodSpecCharRelate);
		logger.info("\t特征删除关系");
		assertNull("判断删除关系",prodSpecCharOwn.getProdSpecCharRelationship());
		prodSpecCharOwn.addRelatedCharacteristic(prodSpecCharRelate, ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
		
		logger.info("\t在特征已添加一个关系基础上删除不存在的关系");
		ProductSpecCharacteristic prodSpecCharRelatee = new ProductSpecCharacteristic("1", "颜色", "", validFor, "true",  1,  1, false, "compistchar","");
		prodSpecCharOwn.removeRelatedCharacteristic(prodSpecCharRelatee);
		assertEquals("在已添加一个关系基础上删除不存在的关系",1,prodSpecCharOwn.getProdSpecCharRelationship().size());
		
		prodSpecCharOwn.removeRelatedCharacteristic(prodSpecCharRelate);
		assertEquals("判断删除关系",0,prodSpecCharOwn.getProdSpecCharRelationship().size());
	}
	
	@Test 
	public void testSetDefaultValue(){
		ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1", "GHz", validFor, "8",false);
		prodSpecCharOwn.addValue(prodSpecCharValue);
		
		prodSpecCharValue = null;
		prodSpecCharOwn.setDefaultValue(prodSpecCharValue);
		assertFalse("判断是否指定了默认值",prodSpecCharOwn.getProdSpecCharValue().get(0).isIsDefault());
		
		prodSpecCharValue =  new ProductSpecCharacteristicValue("1", "GHz", validFor, "8",false);
		prodSpecCharOwn.setDefaultValue(prodSpecCharValue);
		assertTrue("判断是否指定了默认值",prodSpecCharOwn.getProdSpecCharValue().get(0).isIsDefault());
	}
}
