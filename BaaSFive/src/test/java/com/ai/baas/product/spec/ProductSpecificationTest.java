package com.ai.baas.product.spec;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import org.junit.After;
import org.junit.Test;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.util.DateUtils;

public class ProductSpecificationTest {
	
	//ProductSpecification Object
	ProductSpecification atomicProdSpec;
	
	//selectedProdSpecChar
	ProductSpecCharacteristic selectedProdSpecChar;
	
	//
	List<ProductSpecCharacteristicValue> selectedProdSpecCharValuesList = new ArrayList<ProductSpecCharacteristicValue>();

	/**
	 * this is an operation to create ProductSpecification Object
	 * @return
	 * @throws ParseException
	 */
	@Test
	public void createProdSpec() throws ParseException{
		String productNumber = "1";
		String name = "11 英寸 MacBook Air";
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
		//prodSpecCharTest.createProdSpecChar();
		
		/*get ProductSpecCharacteristic Object*/
		List<ProductSpecCharacteristic> specCharList = prodSpecCharTest.getAllProdSpecChar();
		
		boolean isDefault = false;
		
		boolean canBeOveridden = false;
		boolean isPackage = true;
		String nameUse ="内存_Use";
		String unique = ""; 
		int minCardinality = 1;
		int maxCardinality =3;
		boolean extensible = false;
		String descriptionUse = "this is a description about size and weight used by CharUse";
		String[] specCharIds = new String[2];
		specCharIds[0] = "1";
		specCharIds[1] = "2";
		
		String[] specCharValueIds = new String[2];
		specCharValueIds[0] = "1";
		specCharValueIds[1] = "2";
		
		
		for (int i = 0; i < specCharIds.length; i++) {
			
			selectedProdChar(specCharList, specCharIds[i],);
			
			/*new CharUse Object*/
			atomicProdSpec.addCharacteristic(selectedProdSpecChar, canBeOveridden, isPackage, validFor, nameUse,  unique, minCardinality, maxCardinality, extensible,  descriptionUse);
			
			
			
			for (int j = 0; j < specCharValueIds.length; j++) {
				
				/*new charValueUse Object*/
				atomicProdSpec.attachCharacteristicValue(selectedProdSpecChar, charValue, isDefault, validFor);
				
			}
		}
		
		ProductSpecCharacteristicValueTest prodSpecCharValueTest = new ProductSpecCharacteristicValueTest();
		prodSpecCharValueTest.createProdSpecCharValue();
		ProductSpecCharacteristicValue charValue = prodSpecCharValueTest.getProdSpecCharValue();
		
		boolean isDefault = false;
		/*new charValueUse Object*/
		atomicProdSpec.attachCharacteristicValue(specChar, charValue, isDefault, validFor);
		
		
	}
	
	@After
	private void  printResult(){
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
	
	/*get ProductSpecCharacteristic which selected by user*/
	private void selectedProdChar(List<ProductSpecCharacteristic> specCharList, String selectedProdSpecCharId){
		String prodSpecCharId = "";
		if(null!=specCharList && specCharList.size()>0){
			for (int i = 0; i < specCharList.size(); i++) {
				prodSpecCharId = specCharList.get(i).getID();
				if(prodSpecCharId.equals(selectedProdSpecCharId)){
					selectedProdSpecChar = specCharList.get(i);
				}
			}
		}
	}
	
	/*get ProductSpecCharValue which selected by user*/
	private void selectedProdChar(List<ProductSpecCharacteristic> specCharList, String selectedProdSpecCharId, String[] selectedProdSpecCharValues){
		String prodSpecCharId = "";
		if(null!=specCharList && specCharList.size()>0){
			for (int i = 0; i < specCharList.size(); i++) {
				prodSpecCharId = specCharList.get(i).getID();
				if(prodSpecCharId.equals(selectedProdSpecCharId)){
					//selectedProdSpecChar = specCharList.get(i);
					ProductSpecCharacteristic prodSpecChar = specCharList.get(i);
					
					//得到选择的char的value数组
					if(null!=selectedProdSpecCharValues && selectedProdSpecCharValues.length>0){
						List<ProductSpecCharacteristicValue> prodSpecCharValues = prodSpecChar.getProdSpecCharValue();
						for (int j = 0; j < prodSpecCharValues.size(); j++) {
							for (int j2 = 0; j2 < selectedProdSpecCharValues.length; j2++) {
								if(prodSpecCharValues.get(j).getValue().equals(selectedProdSpecCharValues[j2])){
									selectedProdSpecCharValuesList.add(prodSpecCharValues.get(j));
								}
							}
						}
					}
					
				}
			}
		}
	}
	
}
