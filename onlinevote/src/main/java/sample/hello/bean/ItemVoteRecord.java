package sample.hello.bean;

import java.util.List;

/**
 * 投票记录
 * @author huangyq3
 *
 */
public class ItemVoteRecord {

	private List<Items> voteItems;
	private String personId;
	private String time;
	private String subjectId;
	private String personPassword;
	
	
	public String getPersonPassword() {
		return personPassword;
	}
	public void setPersonPassword(String personPassword) {
		this.personPassword = personPassword;
	}
	
	public List<Items> getVoteItems() {
		return voteItems;
	}
	public void setVoteItems(List<Items> voteItems) {
		this.voteItems = voteItems;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	
}