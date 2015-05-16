package sample.hello.service;

import java.util.List;

import sample.hello.bean.Contact;
import sample.hello.bean.ItemVoteRecord;

public interface VoteService {
   public List<Contact> getList();
	public String userVote(ItemVoteRecord itemvote);
}
