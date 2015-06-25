package com.ai.baas.product.offering;

import org.junit.Before;
import org.junit.Test;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.util.DateUtils;
import com.ai.baas.product.offering.catalog.ProductCatalog;

public class ProductCatalogTest {
	ProductCatalog pclog = null;
	@Before
  public  void createProductCatalog(){
		String id = "";
		String name = "MacBook Air";
		String type = "电子";
		TimePeriod validFor = new TimePeriod(); 
		String startdate = "2015-06-04 10:20:00";
		String enddate = "2015-06-26 10:20:00";
		validFor.setStartDateTime(DateUtils.str2Date(startdate, DateUtils.datetimeFormat));
		validFor.setEndDateTime(DateUtils.str2Date(enddate, DateUtils.datetimeFormat));
		 pclog = new ProductCatalog(id, name, type, validFor);
  }
	@Test
	public void publishOffering(){
		TimePeriod validFor = new TimePeriod(); 
		String startdate = "2015-06-04 10:20:00";
		String enddate = "2015-06-26 10:20:00";
		validFor.setStartDateTime(DateUtils.str2Date(startdate, DateUtils.datetimeFormat));
		validFor.setEndDateTime(DateUtils.str2Date(enddate, DateUtils.datetimeFormat));
		SimpleProductOfferingTest.createProductOffering();
		ProductOffering offering =SimpleProductOfferingTest.offering ;
		pclog.publishOffering(offering, validFor);
	}
}
