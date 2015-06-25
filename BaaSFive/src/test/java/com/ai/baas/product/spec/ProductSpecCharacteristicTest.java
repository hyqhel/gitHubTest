package com.ai.baas.product.spec;

import org.junit.Test;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.constant.Const;
import com.ai.baas.common.util.DateUtils;

public class ProductSpecCharacteristicTest {

	//ProductSpecCharacteristic Object
	private ProductSpecCharacteristic prodSpecChar;
	
	/**
	 * this is an operation to create ProductSpecCharacteristic Object
	 * @return
	 */
	@Test
	public void createProdSpecChar(){
		String id = "1";
		String name = "ÄÚ´æ";
		String valueType = Const.SPEC_CHAR_VALUE_TYPE_TEXT;
		TimePeriod validFor = new TimePeriod();
		String startDate = "2015-06-01";
		String endDate = "2015-08-01";
		validFor.setStartDateTime(DateUtils.str2Date(startDate, DateUtils.date_sdf));
		validFor.setEndDateTime(DateUtils.str2Date(endDate, DateUtils.date_sdf));
		String unique = "";
		int minCardinality = 1;
		int maxCardinality = 3; 
		boolean extensible = false;
		String description = "this is a description about size and weight"; 
		String derivationFormula = "";
		prodSpecChar = new ProductSpecCharacteristic(id, name, valueType, validFor, unique, minCardinality, maxCardinality, extensible, description, derivationFormula);
	
	}

	public ProductSpecCharacteristic getProdSpecChar() {
		return prodSpecChar;
	}

	public void setProdSpecChar(ProductSpecCharacteristic prodSpecChar) {
		this.prodSpecChar = prodSpecChar;
	}
	
	
}
