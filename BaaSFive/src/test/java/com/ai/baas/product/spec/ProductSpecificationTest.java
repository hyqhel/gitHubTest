package com.ai.baas.product.spec;

import java.text.ParseException;
import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.constant.Const;
import com.ai.baas.common.util.DateUtils;

public class ProductSpecificationTest {
	
	String[] specSelectCharIds = {"1","2","4","3"};
	String[] specSelectCharValueIds = {"11","12","14","13"};
	int[][] specCharUseValueRelate = {
			{0,0},
			{2,2},
			{3,3}
	};
	
	private ProductSpecification atomicProdSpec;
	
	private static TimePeriod validFor;
	
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
		
		/*new AtomicProductSpecification Object*/
		atomicProdSpec = new AtomicProductSpecification("1", "11 英寸 MacBook Air", "apple", "Mac", validFor);
		
		ProductSpecCharacteristicTest prodSpecCharTest = new ProductSpecCharacteristicTest();
		/*get list ProductSpecCharacteristic Object */
		List<ProductSpecCharacteristic> specCharList = prodSpecCharTest.getProdSpecChars();
		
		for (int i = 0; i < specSelectCharIds.length; i++) {
			/*new CharUse Object*/
			ProductSpecCharacteristic selectedSpecChar = selectedProdChar(specCharList,specSelectCharIds[i]);
			atomicProdSpec.addCharacteristic(selectedSpecChar, false, true, validFor, selectedSpecChar.getName(),  "", 1, 3, false,  "this is a description about size and weight used by CharUse");
			List<ProductSpecCharacteristicValue> prodSpecCharValueList = selectedSpecChar.getProdSpecCharValue();
			for(int j=0;j<specCharUseValueRelate.length;j++){
				if(i==specCharUseValueRelate[j][0]){
					int valuesub=specCharUseValueRelate[j][1];
					ProductSpecCharacteristicValue selectvalue=selectedProdCharValue(prodSpecCharValueList,specSelectCharValueIds[valuesub]);
					atomicProdSpec.attachCharacteristicValue(selectedSpecChar, selectvalue, false, validFor);
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
					break;
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
				prodSpecCharVauleId = specCharValueList.get(i).getId();
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
		System.out.println("规格："+atomicProdSpec.getName());
		List<ProductSpecCharUse> prodSpecCharUseList = atomicProdSpec.getProdSpecChar();
		if(null!=prodSpecCharUseList && prodSpecCharUseList.size()>0){
			for (int i = 0; i < prodSpecCharUseList.size(); i++) {
				ProductSpecCharUse  prodSpecCharUse = prodSpecCharUseList.get(i);
				System.out.println("    特征："+prodSpecCharUse.getName());
				List<ProdSpecCharValueUse> prodSpecCharValueUseList = prodSpecCharUse.getProdSpecCharValue();
				if(null!=prodSpecCharValueUseList && prodSpecCharValueUseList.size()>0){
					for (int j = 0; j < prodSpecCharValueUseList.size(); j++) {
						ProdSpecCharValueUse prodSpecCharValueUse = prodSpecCharValueUseList.get(j);
						if(null!=prodSpecCharValueUse){
							if(null!=prodSpecCharValueUse.getProdSpecCharValue()){
								//如果是from to的则输出范围值
								if(prodSpecCharValueUse.getProdSpecCharValue().getValueType().equals(Const.SPEC_CHAR_VALUE_TYPE_FORTH)){
									//System.out.println("特征的取值范围是："+prodSpecCharValueUse.getProdSpecCharValue().getValueFrom()+"~"+prodSpecCharValueUse.getProdSpecCharValue().getValueTo());
									String validFrom = prodSpecCharValueUse.getProdSpecCharValue().getValueFrom();
									String rangeInterval = prodSpecCharValueUse.getProdSpecCharValue().getRangeInterval();
									String theValue = String.valueOf(Double.parseDouble(validFrom)+Double.parseDouble(rangeInterval));
									System.out.println("         特征值："+theValue+prodSpecCharValueUse.getProdSpecCharValue().getUnitOfMeasure());
								}else{
									System.out.println("         特征值："+prodSpecCharValueUse.getProdSpecCharValue().getValue()+prodSpecCharValueUse.getProdSpecCharValue().getUnitOfMeasure());
								}
							}
							
						}
					}
				}
			}
		}
	}
	
	@BeforeClass
	public static void initVliadFor(){
		validFor = new TimePeriod();
		String startDate = "2015-06-01";
		String endDate = "2015-08-01";
		validFor.setStartDateTime(DateUtils.str2Date(startDate, DateUtils.date_sdf));
		validFor.setEndDateTime(DateUtils.str2Date(endDate, DateUtils.date_sdf));
	}
	
	public void getAllRootProdSpec(){
		
	}
}
