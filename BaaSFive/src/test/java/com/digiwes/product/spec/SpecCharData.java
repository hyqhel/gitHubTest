package com.digiwes.product.spec;

public class SpecCharData {
	public static String[] specSelectCharIds = {"1","2","4","3"};
	public static String[] specSelectCharValueIds = {"11","12","14","13"};
	public static int[][] specCharUseValueRelate = {
			{0,0},
			{2,2},
			{3,3}
	};
	public   static Object [][] specChar= {
	        {"1","处理器","cpu",1,2,"false",false},		
			{"2","尺寸与重量","compistchar",1,1,"true",false},			
			{"3","高度","high",1,1,"true",true},
			{"4","宽度","width",1,1,"true",true},
			{"5","深度","height",1,1,"true",true},
			{"6","存储设备","cache",1,1,"true",true}
	};
	
//valueType,id,unitOfMeasure,value, valueform,valueto,rangeInterval
	public	static Object [][] specCharValue= {
			{"1","11","GHz","1.6"},
			{"1","12","GHz","2.0"},
			{"3","13","cm",0.3,1.7,1},
			{"2","14","cm",1.08},
			{"2","15","cm",19.2},
			{"1","16","GB",128},
			{"1","17","GB",256},
			{"1","18","GB",512}
	};
	
	public	static int [][] specCharRelateValue= {
			{0,0},
			{0,1},
			{2,2},
			{3,3},
			{4,4},
			{5,5},
			{5,6},
			{5,7}
	};

	public	static String [][] specCharRelate= {
		{"2","3"},
		{"2","4"},
		{"2","5"}
};
}
