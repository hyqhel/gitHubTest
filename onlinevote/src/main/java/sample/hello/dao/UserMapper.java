package sample.hello.dao;

import java.util.List;

import sample.hello.bean.Person;

public interface UserMapper {
	/**
	 * 
	 * @param id
	 * @param password
	 */
	public List<Person> login(String id, String password) ;
}
