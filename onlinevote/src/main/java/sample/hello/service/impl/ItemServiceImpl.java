package sample.hello.service.impl;

import org.springframework.stereotype.Service;

import sample.hello.bean.Items;
import sample.hello.bean.ResultData;
import sample.hello.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {


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
	public ResultData<Items> AddItem(String subjectId, String itemContent) {
		// TODO - implement Items.AddItem
		throw new UnsupportedOperationException();
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
		// TODO - implement Items.queryItem
		throw new UnsupportedOperationException();
	}

}