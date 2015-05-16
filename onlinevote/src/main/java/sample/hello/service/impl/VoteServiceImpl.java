package sample.hello.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.hello.bean.Contact;
import sample.hello.bean.ItemVoteRecord;
import sample.hello.bean.Items;
import sample.hello.bean.business.VoteSubjectItem;
import sample.hello.dao.VoteMapper;
import sample.hello.service.VoteService;
@Service
public class VoteServiceImpl implements VoteService{
	@Autowired
	private VoteMapper voteMapper;

	public List<Contact> getList() {
		// TODO Auto-generated method stub
		return voteMapper.getList();
	}
	@Transactional
	public String userVote(ItemVoteRecord itemvote){
		// TODO Auto-generated method stub
		List<Items> listems=itemvote.getVoteItems();
		for(Items its:listems){
			VoteSubjectItem vite=new VoteSubjectItem();
			vite.setItemId(its.getItemId());
			vite.setPersonId(itemvote.getPersonId());
			vite.setSubjectId(itemvote.getSubjectId());
			voteMapper.userVote(vite);
		}
		return "1";
	}
	
	
}
