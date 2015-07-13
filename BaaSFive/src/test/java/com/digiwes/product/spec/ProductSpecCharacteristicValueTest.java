package com.digiwes.product.spec;

import org.junit.Test;

import com.digiwes.basetype.TimePeriod;
import com.digiwes.common.util.DateUtils;

public class ProductSpecCharacteristicValueTest {

	//ProductSpecCharacteristicValue Object
	ProductSpecCharacteristicValue prodSpecCharValue;
	
	/**
	 * this is an operation to create ProductSpecCharacteristicValue Object
	 * @return
	 */
	@Test
	public void createProdSpecCharValue(){
		String valueType = "";
		String unitOfMeasure = "";
		TimePeriod validFor = new TimePeriod();
		String startDate = "2015-06-01";
		String endDate = "2015-08-01";
		validFor.setStartDateTime(DateUtils.str2Date(startDate, DateUtils.date_sdf));
		validFor.setEndDateTime(DateUtils.str2Date(endDate, DateUtils.date_sdf));
		String value = ""; 
		boolean isDefault = false;
		prodSpecCharValue = new ProductSpecCharacteristicValue(valueType,  isDefault,unitOfMeasure, validFor, value);
	}

	public ProductSpecCharacteristicValue getProdSpecCharValue() {
		return prodSpecCharValue;
	}
	
	
}
