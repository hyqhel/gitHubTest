package sample.hello.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.hello.bean.Items;
import sample.hello.bean.Subject;
import sample.hello.bean.business.SubjectAndItem;
import sample.hello.dao.ItemMapper;
import sample.hello.dao.SubjectMapper;
import sample.hello.service.SubjectService;
import sample.hello.util.Index;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	SubjectMapper submapper;
	@Autowired
	ItemMapper itemapper;
	/* (non-Javadoc)
	 * @see sample.hello.service.impl.SubjectService#showResult(java.lang.String)
	 */
	public Items[] showResult(String subjectId) {
		// TODO - implement Subject.showResult
		throw new UnsupportedOperationException();
	}
	
	@Transactional
	public String createSubject(Subject subject) {
		System.out.println("2345");
		String subid=Index.CreateNoncestr();
		subject.setSubjectId(subid);
		submapper.addSubject(subject);//添加主题
		List<SubjectAndItem> listitemIds=new ArrayList<SubjectAndItem>();
		for(Items item:subject.getItemlist()){
			SubjectAndItem si=new SubjectAndItem();
			String iteid=Index.CreateNoncestr();
			item.setItemId(iteid);
			itemapper.AddItem(item);//添加主题项
			
			System.out.println("---------"+iteid);
			si.setSubjectId(subid);
			si.setItemId(iteid);
			listitemIds.add(si);
		}
		//添加主题项关系
		for(SubjectAndItem subIt:listitemIds){
			itemapper.AddSubjectAndItem(subIt);
		}
		return "1";
	}

	/* (non-Javadoc)
	 * @see sample.hello.service.impl.SubjectService#deleteSubject(java.lang.String)
	 */
	public boolean deleteSubject(String subjectId) {
		// TODO - implement Subject.deleteSubject
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see sample.hello.service.impl.SubjectService#updateSubject(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String updateSubject(String subjectId, String subjectContent, String subjectStartTime, String subjectEndTime, String subjectType, String subjectMaxSelectNum, String subjectState) {
		// TODO - implement Subject.updateSubject
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see sample.hello.service.impl.SubjectService#updateSubjectItem(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String updateSubjectItem(String subjectId, String itemId, String itemContent) {
		// TODO - implement Subject.updateSubjectItem
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see sample.hello.service.impl.SubjectService#querySubject(java.lang.String)
	 */
	public Subject[] querySubject(String subjectId) {
		// TODO - implement Subject.querySubject
		throw new UnsupportedOperationException();
	}
	
	public Subject getSubjectAndItems(String subjectId) {
		Subject subjectInfo = submapper.getSubjectById(subjectId);
		ArrayList<Items> itemInfos = itemapper.queryItemsBySubjectId(subjectId);
		
		subjectInfo.setItemlist(itemInfos);
		
		return subjectInfo;
	}

}
