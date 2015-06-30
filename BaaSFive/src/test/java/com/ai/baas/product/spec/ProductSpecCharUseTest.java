package com.ai.baas.product.spec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.util.DateUtils;

public class ProductSpecCharUseTest {
    private	ProductSpecCharUse pscu =null;
	private ProductSpecCharacteristic prodSpecCharOwn=null;
    private static TimePeriod validFor;
	@Before
	public void createProductSpecCharacteristic(){
		prodSpecCharOwn = new ProductSpecCharacteristic("1", "深度", "", validFor, "false",  1,  1, true, "height","");
		ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1", "cm", validFor, "12", "", "");
		prodSpecCharOwn.addValue(prodSpecCharValue);
		ProductSpecCharacteristicValue prodSpecCharValuee = new ProductSpecCharacteristicValue("1", "cm", validFor, "12.3", "", "");
		prodSpecCharOwn.addValue(prodSpecCharValuee);
	    pscu = new ProductSpecCharUse(prodSpecCharOwn, false, false, validFor);
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
    public void testAddValue(){
		ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1", "cm", validFor, "12", "", "");
		pscu.addValue(prodSpecCharValue, false, validFor);
		assertEquals("判断是否成功加上了valueuse",1,pscu.getProdSpecCharValue().size());
		
		pscu.addValue(prodSpecCharValue, false, validFor);
		assertEquals("重新再加相同的valueuse",1,pscu.getProdSpecCharValue().size());
		
		ProductSpecCharacteristicValue prodSpecCharValuee = new ProductSpecCharacteristicValue("1", "cm", validFor, "12.3", "", "");
		pscu.addValue(prodSpecCharValuee, false, validFor);
		assertEquals("判断是否再次成功加上不同的value了valueuse",2,pscu.getProdSpecCharValue().size());
    }
	@Test
	public void testRemoveValue(){
		ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1", "cm", validFor, "12", "", "");
		pscu.removeValue(prodSpecCharValue);
		assertNull("未添加value时删除valueuse",pscu.getProdSpecCharValue());
		
		pscu.addValue(prodSpecCharValue, false, validFor);
		pscu.removeValue(prodSpecCharValue);
		assertEquals("已添加value后再删除valueuse",0,pscu.getProdSpecCharValue().size());
	}
	@Test 
	public void testSpecifyDefaultCharacteristicValue(){
		ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1", "cm", validFor, "12", "", "");
		pscu.addValue(prodSpecCharValue, false, validFor);
		
		prodSpecCharValue = null;
		pscu.specifyDefaultCharacteristicValue(prodSpecCharValue);
		assertFalse("判断是否指定了默认值",pscu.getProdSpecCharValue().get(0).getProdSpecCharValue().isIsDefault());
		
		prodSpecCharValue = new ProductSpecCharacteristicValue("1", "cm", validFor, "12", "", "");
		pscu.specifyDefaultCharacteristicValue(prodSpecCharValue);
		assertTrue("判断是否指定了默认值",pscu.getProdSpecCharValue().get(0).getProdSpecCharValue().isIsDefault());
	}
}
