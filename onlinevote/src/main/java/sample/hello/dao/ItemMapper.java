package sample.hello.dao;

import sample.hello.bean.Items;
import sample.hello.bean.ResultData;

public interface ItemMapper {
	public  boolean choose();

	/**
	 * 
	 * @param subjectId
	 * @param itemContent
	 */
	public  ResultData<Items> AddItem(String subjectId,
			String itemContent);

	/**
	 * 
	 * @param itemId
	 * @param subjectId
	 */
	public  String deleteItem(String itemId, String subjectId);

	/**
	 * 
	 * @param subjectId
	 * @param itemId
	 */
	public  Items queryItem(String subjectId,
			String itemId);
	
}
