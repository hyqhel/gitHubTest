package sample.hello.service.impl;

import org.springframework.stereotype.Service;

import sample.hello.bean.Items;
import sample.hello.bean.Subject;
import sample.hello.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	/* (non-Javadoc)
	 * @see sample.hello.service.impl.SubjectService#showResult(java.lang.String)
	 */
	@Override
	public Items[] showResult(String subjectId) {
		// TODO - implement Subject.showResult
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see sample.hello.service.impl.SubjectService#createSubject(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, sample.hello.bean.Items[])
	 */
	@Override
	public String createSubject(String subjectId, String subjectContent, String subjectStartTime, String subjectEndTime, String subjectType, String subjectMaxSelectNum, String subjectState, Items[] subjectItem) {
		// TODO - implement Subject.createSubject
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see sample.hello.service.impl.SubjectService#deleteSubject(java.lang.String)
	 */
	@Override
	public boolean deleteSubject(String subjectId) {
		// TODO - implement Subject.deleteSubject
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see sample.hello.service.impl.SubjectService#updateSubject(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String updateSubject(String subjectId, String subjectContent, String subjectStartTime, String subjectEndTime, String subjectType, String subjectMaxSelectNum, String subjectState) {
		// TODO - implement Subject.updateSubject
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see sample.hello.service.impl.SubjectService#updateSubjectItem(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String updateSubjectItem(String subjectId, String itemId, String itemContent) {
		// TODO - implement Subject.updateSubjectItem
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see sample.hello.service.impl.SubjectService#querySubject(java.lang.String)
	 */
	@Override
	public Subject[] querySubject(String subjectId) {
		// TODO - implement Subject.querySubject
		throw new UnsupportedOperationException();
	}
}
