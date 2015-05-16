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
	private  List<Items> itemlist=null;
	
	
	
	public List<Items> getItemlist() {
		return itemlist;
	}
	public void setItemlist(List<Items> itemlist) {
		this.itemlist = itemlist;
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
