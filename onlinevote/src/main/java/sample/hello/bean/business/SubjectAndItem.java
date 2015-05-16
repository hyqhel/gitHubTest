package sample.hello.bean.business;

import java.util.List;

public class SubjectAndItem {

	private String subjectId;
	private String subjectContent;
	private String itemId;
	private String itemContent;
	private List<String> listItme;
	
	
	public List<String> getListItme() {
		return listItme;
	}
	public void setListItme(List<String> listItme) {
		this.listItme = listItme;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectContent() {
		return subjectContent;
	}
	public void setSubjectContent(String subjectContent) {
		this.subjectContent = subjectContent;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemContent() {
		return itemContent;
	}
	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}
	
}
