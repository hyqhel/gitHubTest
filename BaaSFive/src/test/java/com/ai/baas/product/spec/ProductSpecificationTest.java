package com.ai.baas.product.spec;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.constant.Const;
import com.ai.baas.common.enums.ProdSpecEnum;
import com.ai.baas.common.util.DateUtils;

public class ProductSpecificationTest {
	private static final Logger log = Logger.getLogger(ProductSpecificationTest.class);
	private static ProductSpecCharacteristic psc =null;
	//scene
	private static ProductSpecification prodSpec;
	//test prodSpec
	private  ProductSpecification atomicProdSpec;
	private static TimePeriod validFor;
	String units = "pound";
	long amount = 100;
	//data about prodSpec which is prepared
	private static List<ProductSpecCharacteristic> specCharList = null;
	
	public static ProductSpecification getProdSpec() {
		return prodSpec;
	}

	public static void setProdSpec(ProductSpecification prodSpec) {
		ProductSpecificationTest.prodSpec = prodSpec;
	}
	
	@Ignore
	@BeforeClass
	public static void  createProdSpec() throws Exception{
		validFor = new TimePeriod();
		String startDate = "2015-06-01";
		String endDate = "2015-08-01";
		validFor.setStartDateTime(DateUtils.str2Date(startDate, DateUtils.date_sdf));
		validFor.setEndDateTime(DateUtils.str2Date(endDate, DateUtils.date_sdf));
		prodSpec = new AtomicProductSpecification("1", "11 英寸 MacBook Air", "apple", "Mac", validFor);
		createProdSpecCharTest();
		for (int i = 0; i < SpecCharData.specSelectCharIds.length; i++) {
			ProductSpecCharacteristic selectedSpecChar = selectedProdChar(specCharList,SpecCharData.specSelectCharIds[i]);
			prodSpec.addCharacteristic(selectedSpecChar, false, true, validFor, selectedSpecChar.getName(),  "", 1, 3, false,  "this is a description about size and weight used by CharUse");
			List<ProductSpecCharacteristicValue> prodSpecCharValueList = selectedSpecChar.getProdSpecCharValue();
			for(int j=0;j<SpecCharData.specCharUseValueRelate.length;j++){
				if(i==SpecCharData.specCharUseValueRelate[j][0]){
					int valuesub=SpecCharData.specCharUseValueRelate[j][1];
					ProductSpecCharacteristicValue selectvalue=selectedProdCharValue(prodSpecCharValueList,SpecCharData.specSelectCharValueIds[valuesub]);
					prodSpec.attachCharacteristicValue(selectedSpecChar, selectvalue, false, validFor);
				}
			}
		}
		prodSpec.setVersion("1.0.0", "create a version", new Date(), validFor);
	}
	public static void createProdSpecCharTest(){
			String derivationFormula = "";
			String valueType="";
			if(specCharList ==null ){
				specCharList = new ArrayList<ProductSpecCharacteristic>();
			}
			for(int i=0;i<SpecCharData.specChar.length;i++){
				ProductSpecCharacteristic prodSpecChar = new ProductSpecCharacteristic(SpecCharData.specChar[i][0].toString(), SpecCharData.specChar[i][1].toString(), valueType, validFor, SpecCharData.specChar[i][5].toString(),  Integer.parseInt(SpecCharData.specChar[i][3].toString()),  Integer.parseInt((String)SpecCharData.specChar[i][4].toString()), (Boolean)SpecCharData.specChar[i][6], SpecCharData.specChar[i][2].toString(),derivationFormula);
				for(int j=0;j<SpecCharData.specCharRelateValue.length;j++){
					if(i==SpecCharData.specCharRelateValue[j][0]){
						int charvaluesub=SpecCharData.specCharRelateValue[j][1];
						// form to value
						ProductSpecCharacteristicValue  prodSpecCharValue=null;
						String charvalueId=SpecCharData.specCharValue[charvaluesub][1].toString();
						if(ProdSpecEnum.ProdSpecType.FORTH.getValue().equals((String)SpecCharData.specCharValue[charvaluesub][0])){
							  prodSpecCharValue = new ProductSpecCharacteristicValue((String)SpecCharData.specCharValue[charvaluesub][0], SpecCharData.specCharValue[charvaluesub][2].toString(), validFor, SpecCharData.specCharValue[charvaluesub][3].toString(), SpecCharData.specCharValue[charvaluesub][4].toString(), SpecCharData.specCharValue[charvaluesub][5].toString());
						}else{
							  prodSpecCharValue = new ProductSpecCharacteristicValue((String)SpecCharData.specCharValue[charvaluesub][0], SpecCharData.specCharValue[charvaluesub][2].toString(), validFor, SpecCharData.specCharValue[charvaluesub][3].toString(), false);
						}
						prodSpecCharValue.setId(charvalueId);
						prodSpecChar.addValue(prodSpecCharValue);
					}
				}
				specCharList.add(prodSpecChar);
			}
			addRelatedCharacteristic();
		}
	
	public static void addRelatedCharacteristic(){
	    String carId = SpecCharData.specCharRelate[0][0].toString();
	     psc =getCharac(carId);
		TimePeriod validFor = new TimePeriod();
		String startDate = "2015-06-01";
		String endDate = "2015-08-21";
		validFor.setStartDateTime(DateUtils.str2Date(startDate, DateUtils.date_sdf));
		validFor.setEndDateTime(DateUtils.str2Date(endDate, DateUtils.date_sdf));
		for(int j=0;j<SpecCharData.specCharRelate.length;j++){
			String dependenccharId=SpecCharData.specCharRelate[j][1];
			psc.addRelatedCharacteristic(getCharac(dependenccharId),ProdSpecEnum.ProdSpecRelationship.AGGREGATION.getValue(), validFor);
		}
	}
	private static ProductSpecCharacteristic getCharac(String carId){
		for(int i=0;i<specCharList.size();i++){
			 if(carId.equals(specCharList.get(i).getID())){
				 return  specCharList.get(i);
			 }
		}
		return null;
	}
	
	/**
	 * this is an operation to return ProductSpecification Object
	 * @return
	 * @throws Exception 
	 */
	private static  ProductSpecCharacteristic selectedProdChar(List<ProductSpecCharacteristic> specCharList, String selectedProdSpecCharId){
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
	
	/**
	 * this is an operation to return ProductSpecCharacteristicValue Object
	 * @return
	 * @throws Exception 
	 */
	private static ProductSpecCharacteristicValue selectedProdCharValue(List<ProductSpecCharacteristicValue> specCharValueList, String selectedProdSpecCharValueId){
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
	
	@Before
	public  void setUp(){
		atomicProdSpec = new AtomicProductSpecification("1", "11 Pounds MacBook Air", "apple", "Mac", validFor);
	}
	
	/**
	 * the test class about productSpecification when add a characteristic
	 */
	@Test
	public void testAddCharacteristic(){
		ProductSpecCharacteristic specChar = new ProductSpecCharacteristic("1", "color", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		
		//TODO exception handing？？？    judgment when call？？？
		//atomicProdSpec.getProdSpecChar().size();
		
		atomicProdSpec.addCharacteristic(specChar, false, false, validFor);
		assertEquals("add a characteristic success",1,atomicProdSpec.getProdSpecChar().size());
		//assertEquals("characteristic is correct to ProductSpecification",false,atomicProdSpec.getProdSpecChar().iterator().next().getProdSpecChar().equals(specChar));
		ProductSpecCharUse prodSpecCharUse = new ProductSpecCharUse(specChar, false, false, validFor);
		assertEquals("characteristic is correct to ProductSpecification",true,atomicProdSpec.getProdSpecChar().contains(prodSpecCharUse));
		
		ProductSpecCharacteristic specCharTwo = new ProductSpecCharacteristic("1", "color", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		atomicProdSpec.addCharacteristic(specCharTwo, false, false, validFor);
		assertEquals("whether the same characteristic value can repeat add",1,atomicProdSpec.getProdSpecChar().size());
		
		specCharTwo = null;
		try {
			atomicProdSpec.addCharacteristic(specCharTwo, false, false, validFor);
			assertEquals("is a null object about characteristic can be add",1,atomicProdSpec.getProdSpecChar().size());
			fail("the object of ProductSpecCharacteristic is null, no check");
		} catch (Exception e) {
			// check specCharTwo is null
		}
		
	}
	
	/**
	 * 给规格remove一个特征的test类
	 */
	@Ignore
	public void testRemoveCharacteristic(){
		ProductSpecCharacteristic specChar = new ProductSpecCharacteristic("1", "颜色", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		
		atomicProdSpec.removeCharacteristic(specChar);
		assertEquals("不添加，直接删除是否有问题",0, (atomicProdSpec.getProdSpecChar() == null ? 0 : atomicProdSpec.getProdSpecChar().size()));
		
		Boolean rtnFlag = false;
		atomicProdSpec.addCharacteristic(specChar, false, false, validFor);
		
		ProductSpecCharacteristic specCharNotExsit = new ProductSpecCharacteristic("2", "颜色", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		rtnFlag = atomicProdSpec.removeCharacteristic(specCharNotExsit);
		assertEquals("是否成功删除一个不存在的特征", false,rtnFlag);
		
		rtnFlag = atomicProdSpec.removeCharacteristic(specChar);
		assertEquals("是否成功删除一个已存在的特征", true,rtnFlag);
		
		specChar = null;
		rtnFlag = atomicProdSpec.removeCharacteristic(specChar);
		assertEquals("入参中要删除的特征为null是否正确", false,rtnFlag);
	}
	
	/**
	 * 给规格下的特征添加特征值的test类
	 */
	@Ignore
	public void testAttachCharacteristicValue(){
		Boolean rtnFlag = false;
		ProductSpecCharacteristic specChar = new ProductSpecCharacteristic("1", "颜色", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getValue(), "GBK", validFor, "red", false);
        specChar.addValue(charValue);		
		atomicProdSpec.addCharacteristic(specChar, false, false, validFor);
		
		atomicProdSpec.attachCharacteristicValue(specChar, charValue, false, validFor);
		assertEquals("是否成功添加一个特征值",1,atomicProdSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().size());
		
		atomicProdSpec.attachCharacteristicValue(specChar, charValue, false, validFor);
		assertEquals("同一个特征值能否重复添加同一个对象",1,atomicProdSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().size());
		
		ProductSpecCharacteristicValue charValueTwo = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getValue(), "GBK", validFor, "red", false);
		atomicProdSpec.attachCharacteristicValue(specChar, charValueTwo, false, validFor);
		assertEquals("同一个特征值能否重复添加new一个新的对象",1,atomicProdSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().size());
		
		specChar = null;
		atomicProdSpec.attachCharacteristicValue(specChar, charValue, false, validFor);
		assertEquals("为空的特征能否成功添加特征值",1,atomicProdSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().size());
		
		ProductSpecCharacteristic specCharNotExist = new ProductSpecCharacteristic("1", "颜色", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		atomicProdSpec.attachCharacteristicValue(specCharNotExist, charValue, false, validFor);
		assertEquals("找不到的特征能否成功添加特征值",1,atomicProdSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().size());
		
		specChar = new ProductSpecCharacteristic("1", "颜色", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		charValue = null;
		atomicProdSpec.attachCharacteristicValue(specChar, charValue, false, validFor);
		assertEquals("为空的特征值能否添加成功",false,rtnFlag);
	}
	
	/**
	 * 删除特征下的某一个特征值得test类
	 */
	@Ignore
	public void testDetachCharacteristicValue(){
		ProductSpecCharacteristic specChar = new ProductSpecCharacteristic("1", "颜色", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getValue(), "GBK", validFor, "red", false);
		specChar.addValue(charValue);
		atomicProdSpec.addCharacteristic(specChar, false, false, validFor);
		
		atomicProdSpec.detachCharacteristicValue(specChar, charValue);
		assertEquals("不添加，直接删除特征值是否有问题",0, (atomicProdSpec.getProdSpecChar().iterator().next().getProdSpecCharValue() == null ? 0 : atomicProdSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().size()));
		
		atomicProdSpec.attachCharacteristicValue(specChar, charValue, false, validFor);
		ProductSpecCharacteristicValue charValueNotExist = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getValue(), "GBK", validFor, "green", false);
		atomicProdSpec.detachCharacteristicValue(specChar, charValueNotExist);
		assertEquals("是否成功删除一个不存在的特征值", 1, atomicProdSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().size());
		
		atomicProdSpec.detachCharacteristicValue(specChar, charValue);
		assertEquals("是否成功删除一个已存在的特征值", 0, atomicProdSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().size());
		
		specChar = null;
		atomicProdSpec.detachCharacteristicValue(specChar, charValue);
		assertEquals("入参中要删除的特征值为null是否正确", 0, atomicProdSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().size());
	}
	
	/**
	 * 给特征增加一个关系特征
	 */
	@Ignore
	public void testAddRelatedProdSpec(){
		ProductSpecification atomicProdSpecTwo = new AtomicProductSpecification("2", "13 英寸 MacBook Air", "apple", "Mac", validFor);
		
		atomicProdSpec.addRelatedProdSpec(atomicProdSpecTwo, ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getValue(), validFor);
		assertEquals("是否成功添加一个互斥关系的prodSpec", 1, atomicProdSpec.getProdSpecRelationship().size());
		
		atomicProdSpec.addRelatedProdSpec(atomicProdSpecTwo, ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getValue(), validFor);
		assertEquals("与同一个spec建立同一个类型关系是否可以", 1, atomicProdSpec.getProdSpecRelationship().size());
		
		atomicProdSpec.addRelatedProdSpec(atomicProdSpecTwo, ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getValue(), validFor);
		assertEquals("是否可以与一个为null的spec建立关系", 1, atomicProdSpec.getProdSpecRelationship().size());
		
		atomicProdSpec.addRelatedProdSpec(atomicProdSpecTwo, "", validFor);
		assertEquals("是否成功添加一个关系类型为空的prodSpec", 1, atomicProdSpec.getProdSpecRelationship().size());
	}
	
	/**
	 * 给特征设置某一个默认的特征值
	 */
	@Ignore
	 public void testSpecifyDefaultCharacteristicValue(){
		 ProductSpecCharacteristic specChar = new ProductSpecCharacteristic("1", "颜色", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		 ProductSpecCharacteristicValue charValueOne = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getValue(), "GBK", validFor, "red", false);
		 ProductSpecCharacteristicValue charValueTwo = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getValue(), "GBK", validFor, "yellow", false);
		 specChar.addValue(charValueOne);
		 specChar.addValue(charValueTwo);
		 atomicProdSpec.addCharacteristic(specChar, false, false, validFor);
		 atomicProdSpec.attachCharacteristicValue(specChar, charValueOne, false, validFor);
		 atomicProdSpec.attachCharacteristicValue(specChar, charValueTwo, false, validFor);
		 
		 //TODO 
		 ProductSpecCharacteristicValue defaultCharValue = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getValue(), "GBK", validFor, "red", true);
		 atomicProdSpec.specifyDefaultCharacteristicValue(specChar, defaultCharValue);
		 assertEquals("正常设置一个默认值是否成功",true, atomicProdSpec.getProdSpecChar().iterator().next().getProdSpecCharValue().get(0).isIsDefault());
		 
		 
		 /*assertEquals("该特征不存在,能否成功设置默认值", false, atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue().get(0).isIsDefault());
		 
		 assertEquals("该特征存在,特征下没有特征值是否设置成功", false, atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue().get(0).isIsDefault());
		 
		 assertEquals("该特征存在,没有默认值是否设置成功", true, atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue().get(0).isIsDefault());
		 
		 assertEquals("该特征存在,默认值存在是否设置成功", true, atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue().get(0).isIsDefault());*/
	 }
	 
	 /**
	  * 根据时间查询spec下的所有特征
	  */
	@Ignore
	 public void testRetrieveCharacteristic(){
		 boolean rtnFlag = false;
		 
		 //TODO
		 assertEquals("时间参数传入为null能否查询", false, rtnFlag);
		 
		 assertEquals("时间参数传入在时间段之前的能否查到数据", false, rtnFlag);
		 
		 assertEquals("时间参数传入在时间段内的能否查到数据", false, rtnFlag);
		 
		 assertEquals("时间参数传入在时间段之后的能否查到数据", false, rtnFlag);
	 }
	 
	/**
	 * print result
	 */
	@Ignore
	public static void  printResult(){
		System.out.println("总共有"+specCharList.size()+"个特征：\n");
		log.info("总共有"+specCharList.size()+"个特征：\t");
		for(int i=0;i<specCharList.size();i++){
			String comp = "";
			if(specCharList.get(i).getProdSpecCharRelationship()!=null && specCharList.get(i).getProdSpecCharRelationship().size()>0){
				comp = "(复合特征)";
			}
			log.info("特征"+(i+1)+comp+":\t"+specCharList.get(i).toString());
			System.out.println("特征"+(i+1)+comp+":\n"+specCharList.get(i).toString());
		}
		
		Set<ProductSpecCharUse> prodSpecCharUseList = prodSpec.getProdSpecChar();
		System.out.println("\n\n规格："+prodSpec.getName()+"使用了"+prodSpecCharUseList.size()+"个特征:\n");
		log.info("\t\t规格："+prodSpec.getName()+"使用了"+prodSpecCharUseList.size()+"个特征:\t");
		if(null != prodSpecCharUseList && prodSpecCharUseList.size()>0){
			for (int i = 0; i < prodSpecCharUseList.size(); i++) {
				ProductSpecCharacteristic  psc = prodSpecCharUseList.iterator().next().getProdSpecChar();
				String comp = "";
				if(psc.getProdSpecCharRelationship() != null && psc.getProdSpecCharRelationship().size()>0){
					comp = "(复合特征)";
				}
				log.info("特征"+(i+1)+comp+":\t"+psc.toString());
				System.out.println("特征"+(i+1)+comp+":\n"+psc.toString());
			}
		}
		//version
		List<ProductSpecificationVersion> prodSpecCharVersionList = prodSpec.getProdSpecVersion();
		Map<String,String> mapv = new HashedMap();
		if(null != prodSpecCharVersionList && prodSpecCharVersionList.size()>0){
			for (int i = 0; i < prodSpecCharVersionList.size(); i++) {
				String versionType = prodSpecCharVersionList.get(i).getProdSpecRevisionType();
				String versionNum = prodSpecCharVersionList.get(i).getProdSpecRevisionNumber();
				mapv.put(versionType, versionNum);
			}
		}
		log.info("创建版本是："+mapv.get(Const.SPEC_MAJOR_VERSION)+"."+mapv.get(Const.SPEC_MINOR_VERSION)+"."+mapv.get(Const.SPEC_PATCH_VERSION));
		System.out.println("创建版本是："+mapv.get(Const.SPEC_MAJOR_VERSION)+"."+mapv.get(Const.SPEC_MINOR_VERSION)+"."+mapv.get(Const.SPEC_PATCH_VERSION));
	}
	
	
}
