package com.ai.baas.product.spec;

import java.text.ParseException;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.util.DateUtils;

public class ProductSpecificationTest {
	String[] specSelectCharIds = {"1","2","4"};
	int[][] specCharUseValueRelate = {
			{0,0},
			{0,1},
			{2,2}
	};
	String[] specSelectCharValueIds = {"11","12","15"};
	
	private ProductSpecification atomicProdSpec;
	
	public ProductSpecification getAtomicProdSpec() {
		return atomicProdSpec;
	}

	public void setAtomicProdSpec(ProductSpecification atomicProdSpec) {
		this.atomicProdSpec = atomicProdSpec;
	}
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
		/*get list ProductSpecCharacteristic Object */
		List<ProductSpecCharacteristic> specCharList = prodSpecCharTest.getProdSpecChars();
		
		boolean isDefault = false;
		boolean canBeOveridden = false;
		boolean isPackage = true;
		String nameUse ="char_Use";
		String unique = ""; 
		int minCardinality = 1;
		int maxCardinality =3;
		boolean extensible = false;
		String descriptionUse = "this is a description about size and weight used by CharUse";
		
		for (int i = 0; i < specSelectCharIds.length; i++) {
			//selectedProdChar(specCharList, specCharIds[i], specCharValueIds);
			/*new CharUse Object*/
			ProductSpecCharacteristic selectedSpecChar = selectedProdChar(specCharList,specSelectCharIds[i]);
			atomicProdSpec.addCharacteristic(selectedSpecChar, canBeOveridden, isPackage, validFor, nameUse,  unique, minCardinality, maxCardinality, extensible,  descriptionUse);
			List<ProductSpecCharacteristicValue> prodSpecCharValueList = selectedSpecChar.getProdSpecCharValue();
			for(int j=0;j<specCharUseValueRelate.length;j++){
				if(i==specCharUseValueRelate[j][0]){
					int valuesub=specCharUseValueRelate[j][1];
					ProductSpecCharacteristicValue selectvalue=selectedProdCharValue(prodSpecCharValueList,specSelectCharValueIds[valuesub]);
					atomicProdSpec.attachCharacteristicValue(selectedSpecChar, selectvalue, isDefault, validFor);
				}
			}
		}
	}
	private ProductSpecCharacteristic selectedProdChar(List<ProductSpecCharacteristic> specCharList, String selectedProdSpecCharId){
		String prodSpecCharId = "";
		ProductSpecCharacteristic prodSpecSelectChar=null;
		if(null!=specCharList && specCharList.size()>0){
			for (int i = 0; i < specCharList.size(); i++) {
				prodSpecCharId = specCharList.get(i).getID();
				if(prodSpecCharId.equals(selectedProdSpecCharId)){
					prodSpecSelectChar= specCharList.get(i);
				}
			}
		}
		return prodSpecSelectChar;
	}
	
	private ProductSpecCharacteristicValue selectedProdCharValue(List<ProductSpecCharacteristicValue> specCharValueList, String selectedProdSpecCharValueId){
		String prodSpecCharVauleId = "";
		ProductSpecCharacteristicValue prodSpecSelectCharValue=null;
		if(null!=specCharValueList && specCharValueList.size()>0){
			for (int i = 0; i < specCharValueList.size(); i++) {
				prodSpecCharVauleId = specCharValueList.get(i).getUnitOfMeasure();
				if(prodSpecCharVauleId.equals(selectedProdSpecCharValueId)){
					prodSpecSelectCharValue= specCharValueList.get(i);
				}
			}
		}
		return prodSpecSelectCharValue;
	}
	@After
	public void  printResult(){
		/*print result*/
		System.out.println(atomicProdSpec.getName());
		List<ProductSpecCharUse> prodSpecCharUseList = atomicProdSpec.getProdSpecChar();
		if(null!=prodSpecCharUseList && prodSpecCharUseList.size()>0){
			for (int i = 0; i < prodSpecCharUseList.size(); i++) {
				ProductSpecCharUse  prodSpecCharUse = prodSpecCharUseList.get(i);
				System.out.println(prodSpecCharUse.getName());
			}
		}
	}
}
