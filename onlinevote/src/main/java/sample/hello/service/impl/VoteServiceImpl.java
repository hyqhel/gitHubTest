package sample.hello.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.hello.bean.Contact;
import sample.hello.dao.VoteMapper;
import sample.hello.service.VoteService;
@Service
public class VoteServiceImpl implements VoteService{
	@Autowired
	private VoteMapper voteMapper;

	@Override
	public List<Contact> getList() {
		// TODO Auto-generated method stub
		return voteMapper.getList();
	}
	
	
}
