package sample.hello.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.hello.bean.Items;
import sample.hello.bean.business.SubjectAndItem;
import sample.hello.dao.ItemMapper;
import sample.hello.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
@Autowired
ItemMapper itemapper;

	/* (non-Javadoc)
	 * @see sample.hello.service.impl.CreateSubjectService#choose()
	 */
	public boolean choose() {
		// TODO - implement Items.choose
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see sample.hello.service.impl.CreateSubjectService#AddItem(java.lang.String, java.lang.String)
	 */
	public  int AddItem(Items item){
		// TODO - implement Items.AddItem
		return itemapper.AddItem(item);
	}

	/* (non-Javadoc)
	 * @see sample.hello.service.impl.CreateSubjectService#deleteItem(java.lang.String, java.lang.String)
	 */
	public String deleteItem(String itemId, String subjectId) {
		// TODO - implement Items.deleteItem
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see sample.hello.service.impl.CreateSubjectService#queryItem(java.lang.String, java.lang.String)
	 */
	public Items queryItem(String subjectId, String itemId) {
		return itemapper.queryItem(subjectId, itemId);
	}
/**
 * 添加主题与项的关系
 */
	public int AddSubjectAndItem(SubjectAndItem si) {
		// TODO Auto-generated method stub
		return itemapper.AddSubjectAndItem(si);
	}
	public ArrayList<Items> queryItemsBySubjectId(String subjectId) {
		ArrayList<Items> itemInfos = itemapper.queryItemsBySubjectId(subjectId);
		return itemInfos;
	}

}