package sample.hello.dao;

import java.util.ArrayList;

import sample.hello.bean.Items;
import sample.hello.bean.business.SubjectAndItem;

public interface ItemMapper {
	public  boolean choose();

	/**
	 * 
	 * @param subjectId
	 * @param itemContent
	 */
	public  int AddItem(Items item);
	/**
	 * 添加主题与项的关系
	 */
	public int AddSubjectAndItem(SubjectAndItem si);

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
	public ArrayList<Items> queryItemsBySubjectId(String subjectId);
}
