package sample.hello.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.hello.bean.Person;
import sample.hello.dao.UserMapper;
import sample.hello.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserMapper usermapper;
	/**
	 * 
	 * @param id
	 * @param password
	 */
	public String login(String id, String password) {
		// TODO - implement Person.login
		List<Person> listp=usermapper.login(id, password);
        if(listp.size()==0){
        	return "0";
        }else{
        	return "1";
        }
	}

	public void logout() {
		// TODO - implement Person.logout
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 * @param password
	 */
	public void updatePassword(String id, String password) {
		// TODO - implement Person.updatePassword
		throw new UnsupportedOperationException();
	}
}
