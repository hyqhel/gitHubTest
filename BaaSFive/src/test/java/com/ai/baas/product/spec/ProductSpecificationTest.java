package com.ai.baas.product.spec;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.constant.Const;
import com.ai.baas.common.util.DateUtils;

public class ProductSpecificationTest {
	private static final Logger logger = Logger.getLogger(ProductSpecificationTest.class);

	String[] specSelectCharIds = {"1","2","4","3"};
	String[] specSelectCharValueIds = {"11","12","14","13"};
	int[][] specCharUseValueRelate = {
			{0,0},
			{2,2},
			{3,3}
	};
	
	private ProductSpecification atomicProdSpec;
	
	private static TimePeriod validFor;
	
	String units = "pound";
	long amount = 100;
	
	/**
	 * this is an operation to create ProductSpecification Object
	 * @return
	 * @throws ParseException
	 */
	@Ignore
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
			System.out.println(selectedSpecChar.toString()+"---");
		}
		logger.info("11111111111");
		//add prodSpecVersion
		atomicProdSpec.setVersion("1.0.0", "create a version", new Date(), validFor);
		
		
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
	
	/**
	 * print result
	 */
	@Ignore
	public void  printResult(){
		System.out.println("规格："+atomicProdSpec.getName());
		List<ProductSpecCharUse> prodSpecCharUseList = atomicProdSpec.getProdSpecChar();
		if(null!=prodSpecCharUseList && prodSpecCharUseList.size()>0){
			for (int i = 0; i < prodSpecCharUseList.size(); i++) {
				ProductSpecCharUse  prodSpecCharUse = prodSpecCharUseList.get(i);
				System.out.print("     "+prodSpecCharUse.getName());
				List<ProdSpecCharValueUse> prodSpecCharValueUseList = prodSpecCharUse.getProdSpecCharValue();
				if(null!=prodSpecCharValueUseList && prodSpecCharValueUseList.size()>0){
					for (int j = 0; j < prodSpecCharValueUseList.size(); j++) {
						ProdSpecCharValueUse prodSpecCharValueUse = prodSpecCharValueUseList.get(j);
						if(null!=prodSpecCharValueUse){
							if(null!=prodSpecCharValueUse.getProdSpecCharValue()){
								//如果是from to的则输出范围值
								String unitmeasure =prodSpecCharValueUse.getProdSpecCharValue().getUnitOfMeasure();
								if(prodSpecCharValueUse.getProdSpecCharValue().getValueType().equals(Const.SPEC_CHAR_VALUE_TYPE_FORTH)){
									System.out.println("特征的取值范围是："+prodSpecCharValueUse.getProdSpecCharValue().getValueFrom()+unitmeasure+"~"+prodSpecCharValueUse.getProdSpecCharValue().getValueTo()+unitmeasure);
								}else{
									System.out.println("："+prodSpecCharValueUse.getProdSpecCharValue().getValue()+unitmeasure);
								}
							}
						}
					}
				}else{
					System.out.println();
				}
			}
		}
		//version
		List<ProductSpecificationVersion> prodSpecCharVersionList = atomicProdSpec.getProdSpecVersion();
		Map <String,String> mapv=new HashedMap();
		if(null!=prodSpecCharVersionList && prodSpecCharVersionList.size()>0){
			for (int i = 0; i < prodSpecCharVersionList.size(); i++) {
				String versionType = prodSpecCharVersionList.get(i).getProdSpecRevisionType();
				String versionNum = prodSpecCharVersionList.get(i).getProdSpecRevisionNumber();
				mapv.put(versionType, versionNum);
			}
		}
		System.out.println("创建版本是："+mapv.get(Const.SPEC_MAJOR_VERSION)+"."+mapv.get(Const.SPEC_MINOR_VERSION)+"."+mapv.get(Const.SPEC_PATCH_VERSION));
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
	
	public ProductSpecification getAtomicProdSpec() {
		return atomicProdSpec;
	}

	public void setAtomicProdSpec(ProductSpecification atomicProdSpec) {
		this.atomicProdSpec = atomicProdSpec;
	}
	
	@Before
	public  void getData(){
		atomicProdSpec = new AtomicProductSpecification("1", "11 英寸 MacBook Air", "apple", "Mac", validFor);
	}
	
	/**
	 * 创建规格test类
	 */
	@Test
	public void addCharacteristicTest(){
		ProductSpecCharacteristic specChar = new ProductSpecCharacteristic("1", "颜色", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		atomicProdSpec.addCharacteristic(specChar, false, false, validFor);
		assertEquals(1,atomicProdSpec.getProdSpecChar().size());
		
		ProductSpecCharacteristic specCharTwo = new ProductSpecCharacteristic("1", "颜色", "1", validFor, "unique", 1, 3, false, "description", "derivationFormula");
		atomicProdSpec.addCharacteristic(specCharTwo, false, false, validFor);
		assertEquals(1,atomicProdSpec.getProdSpecChar().size());
	}

}
