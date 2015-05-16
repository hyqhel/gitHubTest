package sample.hello.bean;

import java.util.List;

public class Subject {

	private String subjectId;
	private String subjectContent;
	private String subjectStatus;
	private String startTime;
	private String endTime;
	private String type;
	private String maxSelect;
	private  List<Items> Items;
	
	
	
	public List<Items> getItems() {
		return Items;
	}
	public void setItems(List<Items> items) {
		Items = items;
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
	public String getSubjectStatus() {
		return subjectStatus;
	}
	public void setSubjectStatus(String subjectStatus) {
		this.subjectStatus = subjectStatus;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMaxSelect() {
		return maxSelect;
	}
	public void setMaxSelect(String maxSelect) {
		this.maxSelect = maxSelect;
	}
	
}
