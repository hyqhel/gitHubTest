package com.ai.baas.product.spec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.enums.ProdSpecEnum;
import com.ai.baas.common.util.DateUtils;

public class ProductSpecCharacteristicTest {
	private static final Logger logger = Logger.getLogger(ProductSpecCharacteristicTest.class);
	ProductSpecCharacteristic prodSpecCharOwn=null;
	private static TimePeriod validFor;
	/**id,name,dis,min,max,uniqe,extensible**/
	private    Object [][] specChar= {
		        {"1","处理器","cpu",1,2,"false",false},		
				{"2","尺寸与重量","compistchar",1,1,"true",false},			
				{"3","高度","high",1,1,"true",true},
				{"4","宽度","width",1,1,"true",true},
				{"5","深度","height",1,1,"true",true},
				{"6","存储设备","cache",1,1,"true",true}
		};
		
	//valueType,id,unitOfMeasure,value, valueform,valueto,rangeInterval
	private	Object [][] specCharValue= {
				{"1","11","GHz","1.6"},
				{"1","12","GHz","2.0"},
				{"3","13","cm",0.3,1.7,1},
				{"2","14","cm",1.08},
				{"2","15","cm",19.2},
				{"1","16","GB",128},
				{"1","17","GB",256},
				{"1","18","GB",512}
		};
		
	private	int [][] specCharRelateValue= {
				{0,0},
				{0,1},
				{2,2},
				{3,3},
				{4,4},
				{5,5},
				{5,6},
				{5,7}
		};
	
	private	String [][] specCharRelate= {
			{"2","3"},
			{"2","4"},
			{"2","5"}
	};
	private static ProductSpecCharacteristic psc =null;
	private List<ProductSpecCharacteristic> prodSpecChars;
	public List<ProductSpecCharacteristic> getProdSpecChars() {
		createProdSpecCharTest();
		return prodSpecChars;
	}

	public void setProdSpecCharList(List<ProductSpecCharacteristic> prodSpecChars) {
		this.prodSpecChars = prodSpecChars;
	}

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
	public void createProdSpecCharTest(){
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
						// form to value
						ProductSpecCharacteristicValue  prodSpecCharValue=null;
						String charvalueId=specCharValue[charvaluesub][1].toString();
						if(ProdSpecEnum.ProdSpecType.FORTH.getValue().equals((String)specCharValue[charvaluesub][0])){
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
			/*for(int i=0;i<prodSpecChars.size();i++){
				if(prodSpecChars.get(i) instanceof ConfigurableProductSpecCharacteristic){
					System.out.println("config");
				}else{
					System.out.println("not config");
				}
			}*/
			addRelatedCharacteristic();
		}
	
	public void addRelatedCharacteristic(){
	    String carId = specCharRelate[0][0].toString();
	     psc =getCharac(carId);
		TimePeriod validFor = new TimePeriod();
		String startDate = "2015-06-01";
		String endDate = "2015-08-21";
		validFor.setStartDateTime(DateUtils.str2Date(startDate, DateUtils.date_sdf));
		validFor.setEndDateTime(DateUtils.str2Date(endDate, DateUtils.date_sdf));
		for(int j=0;j<specCharRelate.length;j++){
			String dependenccharId=specCharRelate[j][1];
			psc.addRelatedCharacteristic(getCharac(dependenccharId),ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
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
	@AfterClass
	public static void printRelationChara(){
		logger.info(psc.toString());
		System.out.println("特征：\n"+psc.toString());
		/*System.out.println("ProductSpecCharacteristic name:"+psc.getName());
		System.out.println("I'm a Composite charac,sub chara is:");
		for(int i=0;i<psc.getProdSpecCharRelationship().size();i++){
			ProductSpecCharRelationship tarc= psc.getProdSpecCharRelationship().get(i);
			System.out.println("characname:"+tarc.getTargetProdSpecChar().getName()+"    ,RelationshipType:"+tarc.getCharRelationshipType());
		}*/
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
