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
		System.out.println(id+""+password);
		Person p=new Person();
		p.setPersonId(id);
		p.setPersonPassword(password);
		List<Person> listp=usermapper.login(p);
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
