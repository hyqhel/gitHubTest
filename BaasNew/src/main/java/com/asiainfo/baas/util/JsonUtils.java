package com.asiainfo.baas.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

  
import org.apache.commons.beanutils.PropertyUtils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.*;
import java.util.*;


public class JsonUtils {
	private final static Log log = LogFactory.getLog(JsonUtils.class);
	/**
	 * 
	 * @param jsonString		：json字符�?
	 * @param keyString			：如�? response.data[].categoryId
	 * @return
	 * 
	 * 示例1�?
	 * jsonString = 	
	  	{"response":
			{ 
			  "data":[
			 				{"categoryId":"1080","name":"REDHAT"},
			 				{"categoryId":"1141","name":"linux"}
			 		 ],
			  "result":"success"
 		  	}
		};
		keyString = response.data[].categoryId;
		getValueList(jsonString,keyString);//得到List[1080,1141]
		
		示例2�?
		jsonString = 
		[
		 		{
		 			"attr1":[
		 	 			{"code":"aaa1","name":"bbb1"},
		 	 			{"code":"aaa2","name":"bbb2"}
		 			],
		 			"attr2":[
		 				{"code":"aaa3","name":"bbb3"},
		 	 			{"code":"aaa4","name":"bbb4"}
		 			]
		 		}
	 	 ];
	 	 keyString = [].attr1[].code;
	 	 getValueList(jsonString,keyString);//得到List["aa1","aa2"]
	 */
	public static List<String> getValueList(String jsonString,String keyString,List<String> result){
		log.debug("keyString:"+keyString);
		log.debug("jsonString:"+jsonString);
		
		if(StringUtils.isEmptyString(keyString) || StringUtils.isEmptyString(jsonString) || jsonString.equals("[]")){
			return result;
		}
		
		JSONObject jsonObject = null;//json对象
		JSONArray jsonArray = null;//json数组
		Object pObj = null;
		if(keyString.startsWith("[]")){//如果�?上层为数�?
			pObj = JSONArray.fromObject(jsonString);
		}else{//如果�?上层为对�?
			pObj = JSONObject.fromObject(jsonString);
		}
		
		String[] tempKeyArr = keyString.split("\\.");
		for(int i = 0;i<tempKeyArr.length;i++){
			if(tempKeyArr[i].endsWith("[]")){//某节点为数组
				if(tempKeyArr[i].startsWith("[]")){//如果是第�?个节�?
					jsonArray = (JSONArray)pObj; 
					if(i == tempKeyArr.length - 1){//当前节点为最后一个节�?
						for(int j = 0; j<jsonArray.size();j++){
							result.add(jsonArray.getString(j));
						}
					}else{//当前节点不为�?后一个节�?
						for(int j = 0;j< jsonArray.size();j++){
							String tempKeyString = "";
							for(int n = i+1;n < tempKeyArr.length;n++){
								tempKeyString = tempKeyString + tempKeyArr[n]+".";
							}
							tempKeyString = tempKeyString.substring(0,tempKeyString.length()-1);
							getValueList(jsonArray.getString(j),tempKeyString,result);//递归
						}
						break;//跳出�?外层循环
					}
				}else{
					jsonObject = (JSONObject)pObj; 
					String realKey = tempKeyArr[i].substring(0,tempKeyArr[i].length()-2);
					if(i == tempKeyArr.length - 1){
						jsonArray = jsonObject.getJSONArray(realKey);
						for(int j = 0; j<jsonArray.size();j++){
							result.add(jsonArray.getString(j));
						}
					}else{
						for(int j = 0;j< jsonObject.getJSONArray(realKey).size();j++){
							String tempKeyString = "";
							for(int n = i+1;n < tempKeyArr.length;n++){
								tempKeyString = tempKeyString + tempKeyArr[n]+".";
							}
							tempKeyString = tempKeyString.substring(0,tempKeyString.length()-1);
							getValueList(jsonObject.getJSONArray(realKey).getString(j),tempKeyString,result);
						}
						break;
					}
				}
				
			}else{//当前节点不是数组
				jsonObject = (JSONObject)pObj; 
				if(i == tempKeyArr.length - 1){
					result.add(jsonObject.getString(tempKeyArr[i]));
				}else{
					pObj = jsonObject.getJSONObject(tempKeyArr[i]);
				}
			}
		}
		
		return result;
	}
	
	
	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，形如：
	 * {"id" : idValue, "name" : nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...}}
	 * @param object
	 * @param clazz
	 * @return
	 */
	public static Object getObject(String jsonString, Class clazz) {
		JSONObject jsonObject = null;
		try {
			setDataFormat2JAVA();
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toBean(jsonObject, clazz);
	}
 
	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，其中beansList是一类的集合，形如：
	 * {"id" : idValue, "name" : nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...},
	 * beansList:[{}, {}, ...]}
	 * @param jsonString
	 * @param clazz
	 * @param map 集合属�?�的类型 (key : 集合属�?�名, value : 集合属�?�类型class) eg: ("beansList" : Bean.class)
	 * @return
	 */
	public static Object getObject(String jsonString, Class clazz, Map map) {
		JSONObject jsonObject = null;
		try {
			setDataFormat2JAVA();
			jsonObject = JSONObject.fromObject(jsonString);
			System.out.println(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toBean(jsonObject, clazz, map);
	}
 
	/**
	 * 从一个JSON数组得到�?个java对象数组，形如：
	 * [{"id" : idValue, "name" : nameValue}, {"id" : idValue, "name" : nameValue}, ...]
	 * @param object
	 * @param clazz
	 * @return
	 */
	public static Object[] getObjectArray(String jsonString, Class clazz) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz);
		}
		return obj;
	}
 
	/**
	 * 从一个JSON数组得到�?个java对象数组，形如：
	 * [{"id" : idValue, "name" : nameValue}, {"id" : idValue, "name" : nameValue}, ...]
	 * @param object
	 * @param clazz
	 * @param map
	 * @return
	 */
	public static Object[] getObjectArray(String jsonString, Class clazz, Map map) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz, map);
		}
		return obj;
	}
 
	/**
	 * 从一个JSON数组得到�?个java对象集合
	 * @param object
	 * @param clazz
	 * @return
	 */
 
	public static List getObjectList(String jsonString, Class clazz) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for (Iterator iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz));
		}
		return list;
	}
 
	/**
	 * 从一个JSON数组得到�?个java对象集合，其中对象中包含有集合属�?
	 * @param object
	 * @param clazz
	 * @param map 集合属�?�的类型
	 * @return
	 * class MyBean{   
	 *   private List data;   
	 *		   // getters & setters   
	 *	}   
	 *	  
	 * class Person{   
	 *	   private String name;   
	 *	   // getters & setters   
	 *	}   
	 *	  
	 *	...   
	 *	  
	 *	String json = "{'data':[{'name':'Wallace'},{'name':'Grommit'}]}";   
	 *  Map classMap = new HashMap();   
	 *	classMap.put( "data", Person.class );   
	 *	MyBean bean = JSONObject.toBean( JSONObject.fromObject(json), MyBean.class, classMap );  
	 */
	public static List getObjectList(String jsonString, Class clazz, Map map) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for (Iterator iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz, map));
		}
		return list;
	}
 
	/**
	 * 从json HASH表达式中获取�?个map，该map支持嵌套功能
	 * 形如：{"id" : "johncon", "name" : "小强"}
	 * 注意commons-collections版本，必须包含org.apache.commons.collections.map.MultiKeyMap
	 * @param object
	 * @return
	 */
	public static Map getMapFromJson(String jsonString) {
		setDataFormat2JAVA();
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Map map = new HashMap();
		for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			map.put(key, jsonObject.get(key));
		}
		return map;
	}
 
	/**
	 * 从json数组中得到相应java数组
	 * json形如：["123", "456"]
	 * @param jsonString
	 * @return
	 */
	public static Object[] getObjectArrayFromJson(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return jsonArray.toArray();
	}
	
	/**
	 * 根据Map获得JSON字符�?
	 * @param map
	 * @param array: 
	 * @return
	 */
	public static String getJsonStringFromMap(Map<String,String> map){
		return getJsonStringFromMap(map,null);
	}
	
	/**
	 * 根据Map获得JSON字符�?
	 * @param map
	 * @param jsonArray: map中哪些key标识数组
	 * 			如：map.put("key1","[value1,value2]";  则应该将key1放入jsonArray
	 * @return
	 */
	public static String getJsonStringFromMap(Map<String,String> map,List<String> jsonArray){
		StringBuilder jsonBuilder = new StringBuilder("{");
		String key = "";
		String value = "";
		Map.Entry entry = null;
		int i = 0;
		for(Iterator it = map.entrySet().iterator();it.hasNext();i++){
			entry = (Map.Entry)it.next();
			key = (String)entry.getKey();
			value = (String)entry.getValue();
			if(i==map.size()-1){
				if(jsonArray != null && jsonArray.contains(key)){
					jsonBuilder.append(key).append(":").append(value);
				}else{
					jsonBuilder.append(key).append(":\"").append(value).append("\"");
				}
			}else{
				if(jsonArray != null && jsonArray.contains(key)){
					jsonBuilder.append(key).append(":").append(value).append(",");
				}else{
					jsonBuilder.append(key).append(":\"").append(value).append("\",");
				}
			}
		}
		jsonBuilder.append("}");
		return jsonBuilder.toString();
	}
	
	/**
	 * 根据Map获得JSON字符�?
	 * map包含map�?,data:[{key:value,key:value}]  => mapToArr:true
	 * map包含map�?,data:{key:value,key:value}  	 => mapToArr:false
	 * map包含map数组�?,data:[{key:value,key:value},{key:value,key:value)}]
	 * @param map
	 * @param map
	 * @return
	 */
	public static String getJsonStringFromLevelMap(Map map,boolean mapToArr){
		StringBuilder jsonBuilder = new StringBuilder("{");
		String key = "";
		Object value = null;
		Map.Entry entry = null;
		int i = 0;
		for(Iterator it = map.entrySet().iterator();it.hasNext();i++){
			entry = (Map.Entry)it.next();
			key = (String)entry.getKey();
			value = entry.getValue();
			if(value instanceof HashMap){
				if(mapToArr){
					jsonBuilder.append(key).append(":[");
				}else{
					jsonBuilder.append(key).append(":");
				}
				jsonBuilder.append(getJsonStringFromLevelMap((HashMap)value,mapToArr));
				if(mapToArr){
					if(i==map.size()-1){
						jsonBuilder.append("]");
					}
					else{
						jsonBuilder.append("],");
					}
				}else{
					if(i!=map.size()-1){
						jsonBuilder.append(",");
					}
				}
			}
			else if(value instanceof HashMap[]){
				jsonBuilder.append(key).append(":[");
				HashMap[] maps = (HashMap[])value;
				for(int j=0;j<maps.length;j++){
					jsonBuilder.append(getJsonStringFromLevelMap(maps[j],mapToArr));
					if(j!=maps.length-1){
						jsonBuilder.append(",");
					}
				}
				if(i==map.size()-1){
					jsonBuilder.append("]");
				}
				else{
					jsonBuilder.append("],");
				}
			}
			else{
				if(i==map.size()-1){
					jsonBuilder.append(key).append(":\"").append(value).append("\"");
				}else{
					jsonBuilder.append(key).append(":\"").append(value).append("\",");
				}
			}
		}
		jsonBuilder.append("}");
		return jsonBuilder.toString();
	}
 
	private static void setDataFormat2JAVA() {
		//设定日期转换格式
		JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher(new String[] { "yyyy-MM-dd",
						"yyyy-MM-dd HH:mm:ss" }));
	}
}
