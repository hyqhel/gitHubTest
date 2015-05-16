package sample.hello.service;

import sample.hello.bean.Items;
import sample.hello.bean.Subject;

public interface SubjectService {

	/**
	 * 
	 * @param subjectId
	 */
	public Items[] showResult(String subjectId);

	/**
	 * 
	 * @param subjectId
	 * @param subjectContent
	 * @param subjectStartTime
	 * @param subjectEndTime
	 * @param subjectType
	 * @param subjectMaxSelectNum
	 * @param subjectState
	 * @param subjectItem
	 */
	public String createSubject(Subject subject);

	/**
	 * 
	 * @param subjectId
	 */
	public boolean deleteSubject(String subjectId);

	/**
	 * 
	 * @param subjectId
	 * @param subjectContent
	 * @param subjectStartTime
	 * @param subjectEndTime
	 * @param subjectType
	 * @param subjectMaxSelectNum
	 * @param subjectState
	 */
	public String updateSubject(String subjectId, String subjectContent,
			String subjectStartTime, String subjectEndTime, String subjectType,
			String subjectMaxSelectNum, String subjectState);

	/**
	 * 
	 * @param subjectId
	 * @param itemId
	 * @param itemContent
	 */
	public String updateSubjectItem(String subjectId, String itemId,
			String itemContent);

	/**
	 * 
	 * @param subjectId
	 */
	public Subject[] querySubject(String subjectId);
	
	public Subject getSubjectAndItems(String subjectId);

}