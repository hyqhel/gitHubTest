package sample.hello.dao;

import java.util.List;

import sample.hello.bean.Contact;
import sample.hello.bean.business.VoteSubjectItem;

public interface VoteMapper {
	public List<Contact> getList();
	public int userVote(VoteSubjectItem itemvote);
}
