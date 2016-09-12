/**
 * @version 1.0
 */
package cs213.photoAlbum.model;

import java.util.ArrayList;

/**
 * this is the class for all the backend storing stuff
 * @author Jasmine Feng and Risham Chokshi
 */
public interface backend {

	/**
	 * users Arraylist
	 * */
	ArrayList <User> users = new ArrayList<User>();
	/**
	 * add user
	 * @param id of the user
	 * @param name of the user
	 * @return is the true or false boolean
	 * */
	public boolean addUser(String id, String name);
	/**
	 * delete user
	 * @param id of the user
	 * @return the boolean if true or false
	 * */
	public boolean deleteUser(String id);
	/**
	 * @param id for the user
	 * @return true of false for the login
	 * */
	public boolean login(String id);
	/**
	 * @return true and false for successfull logout
	 * */
	public boolean logout();
	/**
	 * @param id of the user
	 * @return user of the user you are looking for
	 * */
	public User findUser(String id);
	


}
