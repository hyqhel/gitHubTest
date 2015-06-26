package com.ai.baas.product.spec;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.util.DateUtils;

public class ProductSpecCharacteristicTest {
	/**id,name,dis,min,max**/
	private    Object [][] specChar= {
		        {"1","处理器","cpu",1,2},		
				{"2","尺寸与重量","compistchar",1,1},			
				{"3","高度","high",1,1},
				{"4","宽度","width",1,1},
				{"5","深度","height",1,1}
		};
		
	/**valueType,id,unitOfMeasure,value, valueform,valueto,rangeInterval**/
	private	Object [][] specCharValue= {
				{"1","11","GHz","1.6GHz"},
				{"1","12","GHz","2.0GHz"},
				{"3","13","cm",0.3,1.7,1},
				{"2","14","cm",1.08},
				{"2","15","cm",19.2},
				{"2","16","cm",30}
		};
		
	private	int [][] specCharRelateValue= {
				{0,0},
				{0,1},
				{2,2},
				{3,3},
				{4,4}
		};
	private List<ProductSpecCharacteristic> prodSpecChars;
	public List<ProductSpecCharacteristic> getProdSpecChars() {
		createProdSpecCharTest();
		return prodSpecChars;
	}

	public void setProdSpecCharList(List<ProductSpecCharacteristic> prodSpecChars) {
		this.prodSpecChars = prodSpecChars;
	}

	@Test
	public void createProdSpecCharTest(){
			TimePeriod validFor = new TimePeriod();
			String startDate = "2015-06-01";
			String endDate = "2015-08-21";
			validFor.setStartDateTime(DateUtils.str2Date(startDate, DateUtils.date_sdf));
			validFor.setEndDateTime(DateUtils.str2Date(endDate, DateUtils.date_sdf));
			String unique = "";
			String derivationFormula = "";
			boolean extensible = false;
			String valueType="";
			if(prodSpecChars ==null ){
				prodSpecChars = new ArrayList<ProductSpecCharacteristic>();
			}
			for(int i=0;i<specChar.length;i++){
				ProductSpecCharacteristic prodSpecChar = new ProductSpecCharacteristic(specChar[i][0].toString(), specChar[i][1].toString(), valueType, validFor, unique,  Integer.parseInt(specChar[i][3].toString()),  Integer.parseInt((String)specChar[i][4].toString()), extensible, specChar[i][2].toString(),derivationFormula);
				for(int j=0;j<specCharRelateValue.length;j++){
					if(i==specCharRelateValue[j][0]){
						int charvaluesub=specCharRelateValue[j][1];
						/** form to value**/
						ProductSpecCharacteristicValue  prodSpecCharValue=null;
						String charvalueId=specCharValue[charvaluesub][0].toString();
						if("3".equals((String)specCharValue[charvaluesub][0])){
							  prodSpecCharValue = new ProductSpecCharacteristicValue((String)specCharValue[charvaluesub][0], specCharValue[charvaluesub][2].toString(), validFor, specCharValue[charvaluesub][3].toString(), specCharValue[charvaluesub][4].toString(), specCharValue[charvaluesub][5].toString());
						}else{
							  prodSpecCharValue = new ProductSpecCharacteristicValue((String)specCharValue[charvaluesub][0], specCharValue[charvaluesub][2].toString(), validFor, specCharValue[charvaluesub][3].toString(), false);
						}
						prodSpecCharValue.setId(charvalueId);
						prodSpecChar.addValue(prodSpecCharValue);
					}
				}
				prodSpecChars.add(prodSpecChar);
			}
		}
}
