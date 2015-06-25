package com.ai.baas.product.spec;

import java.util.List;

import org.junit.Test;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.constant.Const;
import com.ai.baas.common.util.DateUtils;

public class ProductSpecCharacteristicTest {

	private List<ProductSpecCharacteristic> prodSpecChars;
	
	public List<ProductSpecCharacteristic> getProdSpecChars() {
		createProdSpecChar();
		return prodSpecChars;
	}

	public void setProdSpecCharList(List<ProductSpecCharacteristic> prodSpecChars) {
		this.prodSpecChars = prodSpecChars;
	}

	@Test
	public void createProdSpecChar(){
		TimePeriod validFor = new TimePeriod();
		String startDate = "2015-06-01";
		String endDate = "2015-08-21";
		validFor.setStartDateTime(DateUtils.str2Date(startDate, DateUtils.date_sdf));
		validFor.setEndDateTime(DateUtils.str2Date(endDate, DateUtils.date_sdf));
		int minCardinality = 1;
		int maxCardinality = 2; 
		String valueType = Const.SPEC_CHAR_VALUE_TYPE_TEXT;
		String unique = "";
		String derivationFormula = "";
		boolean extensible = false;
		/**  特征一**/
		String id = "1";
		String name = "处理器";
		String description = "双核 Intel Core i5 处理器"; 
		ProductSpecCharacteristic prodSpecChar = new ProductSpecCharacteristic(id, name, valueType, validFor, unique, minCardinality, maxCardinality, extensible, description, derivationFormula);
		/**  特征一 value 一**/
		String prodSpecCharvalueType = "";
		String prodSpecCharunitOfMeasure = "";
		String prodSpecCharvalue = "1.6GHz"; 
		boolean prodSpecCharisDefault = false;
		ProductSpecCharacteristicValue prodSpecCharValue = new ProductSpecCharacteristicValue(prodSpecCharvalueType, prodSpecCharunitOfMeasure, validFor, prodSpecCharvalue, prodSpecCharisDefault);
		/**  特征一 value 二**/
		String prodSpecCharvalueTypetwo = "";
		String prodSpecCharunitOfMeasuretwo = "";
		String prodSpecCharvaluetwo = "2.2GHz"; 
		boolean prodSpecCharisDefaulttwo = false;
		ProductSpecCharacteristicValue prodSpecCharValuetwo = new ProductSpecCharacteristicValue(prodSpecCharvalueTypetwo, prodSpecCharunitOfMeasuretwo, validFor, prodSpecCharvaluetwo, prodSpecCharisDefaulttwo);
		prodSpecChar.addValue(prodSpecCharValue);
		prodSpecChar.addValue(prodSpecCharValuetwo);
		/**  特征二**/
		String idtwo = "2";
		String nametwo = "内存";
		String descriptiontwo = "高通内存"; 
		ProductSpecCharacteristic prodSpecChartwo = new ProductSpecCharacteristic(idtwo, nametwo, valueType, validFor, unique, minCardinality, maxCardinality, extensible, descriptiontwo, derivationFormula);
		/**  特征二 value 一**/
		String prodSpecChatworvalueType = "";
		String prodSpecChartwounitOfMeasure = "";
		String prodSpecChartwovalue = "4GB 1600MHz LPDDR3 SDRAM"; 
		boolean prodSpecChartwoisDefault = false;
		ProductSpecCharacteristicValue prodSpecChartwoValue = new ProductSpecCharacteristicValue(prodSpecChatworvalueType, prodSpecChartwounitOfMeasure, validFor, prodSpecChartwovalue, prodSpecChartwoisDefault);
		/**  特征二 value 二**/
		String prodSpecChartwovalueTypetwo = "";
		String prodSpecChartwounitOfMeasuretwo = "";
		String prodSpecChartwovaluetwo = "8GB 1600MHz LPDDR3 SDRAM"; 
		boolean prodSpecChartwoisDefaulttwo = false;
		ProductSpecCharacteristicValue prodSpecChartwoValuetwo = new ProductSpecCharacteristicValue(prodSpecChartwovalueTypetwo, prodSpecChartwounitOfMeasuretwo, validFor, prodSpecChartwovaluetwo, prodSpecChartwoisDefaulttwo);
		prodSpecChartwo.addValue(prodSpecChartwoValue);
		prodSpecChartwo.addValue(prodSpecChartwoValuetwo);
		
		String idthree = "3";
		String namethree = "键盘";
		String descriptionthree = "不知名键盘"; 
		ProductSpecCharacteristic prodSpecCharthree = new ProductSpecCharacteristic(idthree, namethree, valueType, validFor, unique, minCardinality, maxCardinality, extensible, descriptionthree, derivationFormula);
		/**  特征三 value 一**/
		String prodSpecCharThreevalueType = "";
		String prodSpecCharThreeunitOfMeasure = "";
		String prodSpecCharThreevalue = "背光键盘（美式）"; 
		boolean prodSpecCharThreeisDefault = false;
		ProductSpecCharacteristicValue prodSpecCharThreeValue = new ProductSpecCharacteristicValue(prodSpecCharThreevalueType, prodSpecCharThreeunitOfMeasure, validFor, prodSpecCharThreevalue, prodSpecCharThreeisDefault);
		/**  特征三 value 二**/
		String prodSpecCharThreevalueTypetwo = "";
		String prodSpecCharThreeunitOfMeasuretwo = "";
		String prodSpecCharThreevaluetwo = "背光键盘（英式）"; 
		boolean prodSpecCharThreeisDefaulttwo = false;
		ProductSpecCharacteristicValue prodSpecCharThreeValuetwo = new ProductSpecCharacteristicValue(prodSpecCharThreevalueTypetwo, prodSpecCharThreeunitOfMeasuretwo, validFor, prodSpecCharThreevaluetwo, prodSpecCharThreeisDefaulttwo);
		prodSpecCharthree.addValue(prodSpecCharThreeValue);
		prodSpecCharthree.addValue(prodSpecCharThreeValuetwo);
		
		prodSpecChars.add(prodSpecChar);
		prodSpecChars.add(prodSpecChartwo);
		prodSpecChars.add(prodSpecCharthree);
	}
	
	
}
