package com.ai.baas.product.spec;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.constant.Const;
import com.ai.baas.common.enums.ProdSpecEnum;
import com.ai.baas.common.util.DateUtils;

public class ProductSpecificationTest {
	private static final Logger log = Logger.getLogger(ProductSpecificationTest.class);
	private static ProductSpecCharacteristic psc =null;
	//场景所用
	private static ProductSpecification prodSpec;
	//测spec 方法所用
	private  ProductSpecification atomicProdSpec;
	private static TimePeriod validFor;
	String units = "pound";
	long amount = 100;
	//存准备好的特征
	private static List<ProductSpecCharacteristic> specCharList = null;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		validFor = new TimePeriod();
		String startDate = "2015-06-01";
		String endDate = "2015-08-01";
		validFor.setStartDateTime(DateUtils.str2Date(startDate, DateUtils.date_sdf));
		validFor.setEndDateTime(DateUtils.str2Date(endDate, DateUtils.date_sdf));
	}
	
	public static ProductSpecification getProdSpec() {
		return prodSpec;
	}

	public static void setProdSpec(ProductSpecification prodSpec) {
		ProductSpecificationTest.prodSpec = prodSpec;
	}
	
	@BeforeClass
	public static void  createProdSpec() throws Exception{
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
	 * this is an operation to create ProductSpecification Object
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
		atomicProdSpec = new AtomicProductSpecification("1", "11 英寸 MacBook Air", "apple", "Mac", validFor);
	}
	/**
	 * 给规格添加特征的test类
	 */
	@Test
	public void testAddCharacteristic(){
		ProductSpecCharacteristic specChar = new ProductSpecCharacteristic("1", "颜色", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		
		//TODO 空指针异常怎么处理？？？    调用的地方加判断？？？
		//atomicProdSpec.getProdSpecChar().size();
		
		atomicProdSpec.addCharacteristic(specChar, false, false, validFor);
		assertEquals("是否成功添加一个特征",1,atomicProdSpec.getProdSpecChar().size());
		
		ProductSpecCharacteristic specCharTwo = new ProductSpecCharacteristic("1", "颜色", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		atomicProdSpec.addCharacteristic(specCharTwo, false, false, validFor);
		assertEquals("同一个特征值是否能重复添加",1,atomicProdSpec.getProdSpecChar().size());
		
		specCharTwo = null;
		atomicProdSpec.addCharacteristic(specCharTwo, false, false, validFor);
		assertEquals("能否添加一个特征值为null的特征对象",1,atomicProdSpec.getProdSpecChar().size());
	}
	
	/**
	 * 给规格remove一个特征的test类
	 */
	@Test
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
	@Test
	public void testAttachCharacteristicValue(){
		Boolean rtnFlag = false;
		ProductSpecCharacteristic specChar = new ProductSpecCharacteristic("1", "颜色", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getValue(), "GBK", validFor, "red", false);
        specChar.addValue(charValue);		
		atomicProdSpec.addCharacteristic(specChar, false, false, validFor);
		
		atomicProdSpec.attachCharacteristicValue(specChar, charValue, false, validFor);
		assertEquals("是否成功添加一个特征值",1,atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue().size());
		
		atomicProdSpec.attachCharacteristicValue(specChar, charValue, false, validFor);
		assertEquals("同一个特征值能否重复添加",1,atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue().size());
		
		specChar = null;
		atomicProdSpec.attachCharacteristicValue(specChar, charValue, false, validFor);
		assertEquals("为空的特征能否成功添加特征值",1,atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue().size());
		
		ProductSpecCharacteristic specCharNotExist = new ProductSpecCharacteristic("1", "颜色", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		atomicProdSpec.attachCharacteristicValue(specCharNotExist, charValue, false, validFor);
		assertEquals("找不到的特征能否成功添加特征值",1,atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue().size());
		
		specChar = new ProductSpecCharacteristic("1", "颜色", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		charValue = null;
		atomicProdSpec.attachCharacteristicValue(specChar, charValue, false, validFor);
		assertEquals("为空的特征值能否添加成功",false,rtnFlag);
	}
	
	/**
	 * 删除特征下的某一个特征值得test类
	 */
	@Test
	public void testDetachCharacteristicValue(){
		ProductSpecCharacteristic specChar = new ProductSpecCharacteristic("1", "颜色", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getValue(), "GBK", validFor, "red", false);
		specChar.addValue(charValue);
		atomicProdSpec.addCharacteristic(specChar, false, false, validFor);
		atomicProdSpec.attachCharacteristicValue(specChar, charValue, false, validFor);
		
		ProductSpecCharacteristicValue charValueNotExist = new ProductSpecCharacteristicValue(ProdSpecEnum.ProdSpecType.TEXT.getValue(), "GBK", validFor, "green", false);
		atomicProdSpec.detachCharacteristicValue(specChar, charValueNotExist);
		assertEquals("是否成功删除一个不存在的特征值", 1, atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue().size());
		
		atomicProdSpec.detachCharacteristicValue(specChar, charValue);
		assertEquals("是否成功删除一个已存在的特征值", 0, atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue().size());
		
		specChar = null;
		atomicProdSpec.detachCharacteristicValue(specChar, charValue);
		assertEquals("入参中要删除的特征值为null是否正确", 0, atomicProdSpec.getProdSpecChar().get(0).getProdSpecCharValue().size());
	}
	
	@Test
	public void testAddRelatedProdSpec(){
		ProductSpecification atomicProdSpecTwo = new AtomicProductSpecification("2", "13 英寸 MacBook Air", "apple", "Mac", validFor);
		
		atomicProdSpec.addRelatedProdSpec(atomicProdSpecTwo, ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getValue(), validFor);
		assertEquals("是否成功添加一个互斥关系的prodSpec", 1, atomicProdSpec.getProdSpecRelationship().size());
		
		atomicProdSpec.addRelatedProdSpec(atomicProdSpecTwo, ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getValue(), validFor);
		assertEquals("与同一个spec建立同一个类型关系是否可以", 1, atomicProdSpec.getProdSpecRelationship().size());
		
		atomicProdSpec.addRelatedProdSpec(atomicProdSpecTwo, ProdSpecEnum.ProdSpecRelationship.EXCLUSIBITY.getValue(), validFor);
		assertEquals("是否可以与一个为null的spec建立关系", 1, atomicProdSpec.getProdSpecRelationship().size());
	}
	/**
	 * print result
	 */
	@AfterClass
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
		
		List<ProductSpecCharUse> prodSpecCharUseList = prodSpec.getProdSpecChar();
		System.out.println("\n\n规格："+prodSpec.getName()+"使用了"+prodSpecCharUseList.size()+"个特征:\n");
		log.info("\t\t规格："+prodSpec.getName()+"使用了"+prodSpecCharUseList.size()+"个特征:\t");
		if(null!=prodSpecCharUseList && prodSpecCharUseList.size()>0){
			for (int i = 0; i < prodSpecCharUseList.size(); i++) {
				ProductSpecCharacteristic  psc = prodSpecCharUseList.get(i).getProdSpecChar();
				String comp = "";
				if(psc.getProdSpecCharRelationship()!=null && psc.getProdSpecCharRelationship().size()>0){
					comp = "(复合特征)";
				}
				log.info("特征"+(i+1)+comp+":\t"+psc.toString());
				System.out.println("特征"+(i+1)+comp+":\n"+psc.toString());
			}
		}
		//version
		List<ProductSpecificationVersion> prodSpecCharVersionList = prodSpec.getProdSpecVersion();
		Map <String,String> mapv=new HashedMap();
		if(null!=prodSpecCharVersionList && prodSpecCharVersionList.size()>0){
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
