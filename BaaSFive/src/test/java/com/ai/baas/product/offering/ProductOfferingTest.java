package com.ai.baas.product.offering;

import java.text.ParseException;

import org.junit.Ignore;
import org.junit.Test;

import com.ai.baas.basetype.TimePeriod;
import com.ai.baas.common.util.DateUtils;
import com.ai.baas.product.spec.ProductSpecification;
import com.ai.baas.product.spec.ProductSpecificationTest;

public class ProductOfferingTest {
	private ProductOffering offering =null;
	
	public ProductOffering getOffering() {
		return offering;
	}

	public void setOffering(ProductOffering offering) {
		this.offering = offering;
	}

	@Test
    public  void createSimpleProductOffering(){
		String id = "0001" ;
		String name = "11 英寸 MacBook Air";
		TimePeriod validFor = new TimePeriod(); 
		String description = "1.6GHz 双核 Intel Core i5 处理器，Turbo Boost 高达 2.7GHz"; 
		ProductSpecification prodSpec = null;
		ProductSpecificationTest pst =new ProductSpecificationTest();
		try {
				pst.createProdSpec();
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		prodSpec=pst.getAtomicProdSpec();
		String startdate = "2015-06-04 10:20:00";
		String enddate = "2015-06-26 10:20:00";
		validFor.setStartDateTime(DateUtils.str2Date(startdate, DateUtils.datetimeFormat));
		validFor.setEndDateTime(DateUtils.str2Date(enddate, DateUtils.datetimeFormat));
		 offering = new SimpleProductOffering( id,  name,  description,  validFor,  prodSpec);
    }
	
	@Ignore
    public  void createBundledProductOffering(){
		String id = "0001" ;
		String name = "11 英寸 MacBook Air";
		TimePeriod validFor = new TimePeriod(); 
		String description = "1.6GHz 双核 Intel Core i5 处理器，Turbo Boost 高达 2.7GHz"; 
		ProductSpecification prodSpec = null;
		String startdate = "2015-06-04 10:20:00";
		String enddate = "2015-06-26 10:20:00";
		validFor.setStartDateTime(DateUtils.str2Date(startdate, DateUtils.datetimeFormat));
		validFor.setEndDateTime(DateUtils.str2Date(enddate, DateUtils.datetimeFormat));
		 offering = new SimpleProductOffering( id,  name,  description,  validFor,  prodSpec);
    }
}
