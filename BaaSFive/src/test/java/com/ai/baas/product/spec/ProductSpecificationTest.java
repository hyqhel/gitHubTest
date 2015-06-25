package com.ai.baas.product.spec;

import java.text.ParseException;
import java.util.List;


import org.junit.After;
import org.junit.Test;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.util.DateUtils;

public class ProductSpecificationTest {
	
	//ProductSpecification Object
	ProductSpecification atomicProdSpec;

	/**
	 * this is an operation to create ProductSpecification Object
	 * @return
	 * @throws ParseException
	 */
	@Test
	public void createProdSpec() throws ParseException{
		String productNumber = "1";
		String name = "11 Ó¢´ç MacBook Air";
		String brand = "apple";
		String description = "Mac";
		TimePeriod validFor = new TimePeriod();
		String startDate = "2015-06-01";
		String endDate = "2015-08-01";
		validFor.setStartDateTime(DateUtils.str2Date(startDate, DateUtils.date_sdf));
		validFor.setEndDateTime(DateUtils.str2Date(endDate, DateUtils.date_sdf));
		/*new AtomicProductSpecification Object*/
		atomicProdSpec = new AtomicProductSpecification(productNumber, name, brand, description, validFor);
		
		ProductSpecCharacteristicTest prodSpecCharTest = new ProductSpecCharacteristicTest();
		/*create ProductSpecCharacteristic Object*/
		prodSpecCharTest.createProdSpecChar();
		/*get ProductSpecCharacteristic Object*/
		ProductSpecCharacteristic specChar = prodSpecCharTest.getProdSpecChar();
		
		boolean canBeOveridden = false;
		boolean isPackage = true;
		String nameUse ="ÄÚ´æ_Use";
		String unique = ""; 
		int minCardinality = 1;
		int maxCardinality =3;
		boolean extensible = false;
		String descriptionUse = "this is a description about size and weight used by CharUse";
		
		/*new CharUse Object*/
		atomicProdSpec.addCharacteristic(specChar, canBeOveridden, isPackage, validFor, nameUse,  unique, minCardinality, maxCardinality, extensible,  descriptionUse);
		
		ProductSpecCharacteristicValueTest prodSpecCharValueTest = new ProductSpecCharacteristicValueTest();
		prodSpecCharValueTest.createProdSpecCharValue();
		ProductSpecCharacteristicValue charValue = prodSpecCharValueTest.getProdSpecCharValue();
		
		boolean isDefault = false;
		/*new charValueUse Object*/
		atomicProdSpec.attachCharacteristicValue(specChar, charValue, isDefault, validFor);
		
		
	}
	
	@After
	public void  printResult(){
		/*print result*/
		System.out.println(atomicProdSpec.getName());
		List<ProductSpecCharUse> prodSpecCharUseList = atomicProdSpec.getProdSpecChar();
		ProductSpecCharUse prodSpecCharUse ;
		if(null!=prodSpecCharUseList && prodSpecCharUseList.size()>0){
			for (int i = 0; i < prodSpecCharUseList.size(); i++) {
				prodSpecCharUse = prodSpecCharUseList.get(i);
				System.out.println(prodSpecCharUse.getName());
			}
		}
	}
	
}
