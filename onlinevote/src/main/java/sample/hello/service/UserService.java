package sample.hello.service;


public interface UserService {
	/**
	 * 
	 * @param id
	 * @param password
	 */
	public String login(String id, String password) ;

	public void logout() ;

	/**
	 * 
	 * @param id
	 * @param password
	 */
	public void updatePassword(String id, String password);
}
