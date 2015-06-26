package com.ai.baas.product.spec;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.util.DateUtils;

public class ProductSpecCharacteristicTest {
	/**id,name,dis,min,max,uniqe,extensible**/
	private    Object [][] specChar= {
		        {"1","处理器","cpu",1,2,"false",false},		
				{"2","尺寸与重量","compistchar",1,1,"true",false},			
				{"3","高度","high",1,1,"true",true},
				{"4","宽度","width",1,1,"true",true},
				{"5","深度","height",1,1,"true",true}
		};
		
	/**valueType,id,unitOfMeasure,value, valueform,valueto,rangeInterval**/
	private	Object [][] specCharValue= {
				{"1","11","GHz","1.6"},
				{"1","12","GHz","2.0"},
				{"3","13","cm",0.3,1.7,1},
				{"2","14","cm",1.08},
				{"2","15","cm",19.2},
		};
		
	private	int [][] specCharRelateValue= {
				{0,0},
				{0,1},
				{2,2},
				{3,3},
				{4,4}
		};
	
	private	String [][] specCharRelate= {
			{"2","3"},
			{"2","4"},
			{"2","5"}
	};
	private List<ProductSpecCharacteristic> prodSpecChars;
	public List<ProductSpecCharacteristic> getProdSpecChars() {
		createProdSpecCharTest();
		return prodSpecChars;
	}

	public void setProdSpecCharList(List<ProductSpecCharacteristic> prodSpecChars) {
		this.prodSpecChars = prodSpecChars;
	}

	@Before
	public void createProdSpecCharTest(){
			TimePeriod validFor = new TimePeriod();
			String startDate = "2015-06-01";
			String endDate = "2015-08-21";
			validFor.setStartDateTime(DateUtils.str2Date(startDate, DateUtils.date_sdf));
			validFor.setEndDateTime(DateUtils.str2Date(endDate, DateUtils.date_sdf));
			String derivationFormula = "";
			String valueType="";
			if(prodSpecChars ==null ){
				prodSpecChars = new ArrayList<ProductSpecCharacteristic>();
			}
			for(int i=0;i<specChar.length;i++){
				ProductSpecCharacteristic prodSpecChar = new ProductSpecCharacteristic(specChar[i][0].toString(), specChar[i][1].toString(), valueType, validFor, specChar[i][5].toString(),  Integer.parseInt(specChar[i][3].toString()),  Integer.parseInt((String)specChar[i][4].toString()), (Boolean)specChar[i][6], specChar[i][2].toString(),derivationFormula);
				for(int j=0;j<specCharRelateValue.length;j++){
					if(i==specCharRelateValue[j][0]){
						int charvaluesub=specCharRelateValue[j][1];
						/** form to value**/
						ProductSpecCharacteristicValue  prodSpecCharValue=null;
						String charvalueId=specCharValue[charvaluesub][1].toString();
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
	
	@Test
	public void addRelatedCharacteristic(){
	    String carId = specCharRelate[0][0].toString();
	    ProductSpecCharacteristic psc =getCharac(carId);
		TimePeriod validFor = new TimePeriod();
		String startDate = "2015-06-01";
		String endDate = "2015-08-21";
		validFor.setStartDateTime(DateUtils.str2Date(startDate, DateUtils.date_sdf));
		validFor.setEndDateTime(DateUtils.str2Date(endDate, DateUtils.date_sdf));
		for(int j=0;j<specCharRelate.length;j++){
			String dependenccharId=specCharRelate[j][1];
			psc.addRelatedCharacteristic(getCharac(dependenccharId), "dependences", validFor);
		}
	}
	private  ProductSpecCharacteristic getCharac(String carId){
		for(int i=0;i<prodSpecChars.size();i++){
			 if(carId.equals(prodSpecChars.get(i).getID())){
				 return  prodSpecChars.get(i);
			 }
		}
		return null;
	}
	@After
	public void printRelationChara(){
		
	}
}
