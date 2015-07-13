package com.digiwes.product.spec;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ProductSpecCharUseTest {
    private	ProductSpecCharUse pscu =null;
	private ProductSpecCharacteristic prodSpecCharOwn=null;
    private static TimePeriod validFor;
	@Before
	public void createProductSpecCharacteristic(){
		prodSpecCharOwn = new ProductSpecCharacteristic("1", "深度", "number", validFor, "false",  1,  1, true, "height","");
		ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1",false, "cm", validFor, "12", "", "");
		prodSpecCharOwn.addValue(prodSpecCharValue);
		ProductSpecCharacteristicValue prodSpecCharValuee = new ProductSpecCharacteristicValue("1",false, "cm", validFor, "12.3", "", "");
		prodSpecCharOwn.addValue(prodSpecCharValuee);
	    pscu = new ProductSpecCharUse(prodSpecCharOwn, false, false, validFor,"深度");
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
		ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1", false,"cm", validFor, "12", "", "");
		ProductSpecCharacteristicValue prodSpecCharValue2 = new ProductSpecCharacteristicValue("1",false, "cm", validFor, "12", "", "");
		ProductSpecCharacteristicValue prodSpecCharValue3 = new ProductSpecCharacteristicValue("1", false,"cm", validFor, "13", "", "");

		ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(prodSpecCharValue, false, validFor);

		pscu.addValue(prodSpecCharValue, false, validFor);
		assertEquals("add a charValue ,compare charValues's size", 1, pscu.getProdSpecCharValue().size());
		assertTrue("add a charValue, check whether the charValue contained in the charValues", pscu.getProdSpecCharValue().contains(charValueUse));
		
		pscu.addValue(prodSpecCharValue2, false, validFor);
		assertEquals("add a same charValue ,compare charValues's size", 1, pscu.getProdSpecCharValue().size());
		assertTrue("add a same charValue, check whether the charValue contained in the charValues", pscu.getProdSpecCharValue().contains(charValueUse));

		pscu.addValue(prodSpecCharValue3, false, validFor);
		assertEquals("add a charValue but the charValue isn't belong to the char ,compare charValues's size", 1, pscu.getProdSpecCharValue().size());
		assertTrue("add a charValue but the charValue isn't belong to the char, check whether the charValue contained in the charValues", pscu.getProdSpecCharValue().contains(charValueUse));

		try{
			pscu.addValue(null, false, validFor);
			fail("add a null");
		}catch(IllegalArgumentException e){}
    }

	@Test 
	public void testSpecifyDefaultCharacteristicValue(){
		ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue("1", false,"cm", validFor, "12", "", "");
		ProductSpecCharacteristicValue prodSpecCharValue2 = new ProductSpecCharacteristicValue("1",false, "cm", validFor, "12.3", "", "");

		List<ProdSpecCharValueUse> expectCharValueUse = new ArrayList<ProdSpecCharValueUse>();

		pscu.specifyDefaultCharacteristicValue(prodSpecCharValue);
		assertEquals("specify default characteristicValue but the characteristic haven't value", null, pscu.getProdSpecCharValue());

		pscu.addValue(prodSpecCharValue, false, validFor);
		pscu.addValue(prodSpecCharValue2, true, validFor);

		ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(prodSpecCharValue,true,validFor);
		ProdSpecCharValueUse defcharValueUse = new ProdSpecCharValueUse(prodSpecCharValue2,false,validFor);
		expectCharValueUse.add(charValueUse);
		expectCharValueUse.add(defcharValueUse);

		pscu.specifyDefaultCharacteristicValue(prodSpecCharValue);
		assertEquals("specify default characteristicValue", expectCharValueUse, pscu.getProdSpecCharValue());

		try{
			pscu.specifyDefaultCharacteristicValue(null);
			fail("specify default characteristicValue，but the parameter is null");
		}catch(IllegalArgumentException e){
		}

		ProductSpecCharacteristicValue defaultValue2 = new ProductSpecCharacteristicValue("1",false, "cm", validFor, "15", "", "");
		pscu.specifyDefaultCharacteristicValue(defaultValue2);
		assertEquals("specify default characteristicValue，but the charValue isn't belong to the characteristic", expectCharValueUse, pscu.getProdSpecCharValue());
	}

	@Test
	public void testSetCardinality(){
		pscu.specifyCardinality(1, 5);
		assertEquals("set characteristicUse cardinality,judet minCardinality", 1, pscu.getMinCardinality());
		assertEquals("set characteristicUse cardinality,judet maxCardinality",5,pscu.getMaxCardinality());

		try{
			pscu.specifyCardinality(6, 5);
			fail("set characteristicUse cardinality , but minCardinality is greater than maxCardinality");
		}catch(IllegalArgumentException e){}

		pscu.specifyCardinality(5, 5);
		assertEquals("set characteristicUse cardinality,judet minCardinality", 5, pscu.getMinCardinality());
		assertEquals("set characteristicUse cardinality,judet maxCardinality",5,pscu.getMaxCardinality());
	}
}
