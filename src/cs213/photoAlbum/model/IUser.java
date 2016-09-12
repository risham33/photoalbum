/**
 * @version 1.0
 */
package cs213.photoAlbum.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this is the user interface
 * @author Jasmine Feng and Risham Chokshi
 *
 */
public interface IUser extends Serializable{

	/**
	 * the id of the user. it is a string
	 * unique, used to log in
	 */
	public String ID = ""; 
	
	/**
	 * the full name of the user
	 */
	public String name = "";

	
	/**
	 * an list of albums for that user
	 */
	public ArrayList<Album> albums = null;

	
	// Getters and Setters
	/**
	 * get the string id
	 * @return the user id
	 */
	public String getID();

	/**
	 * set the user id 
	 * @param iD the string to be set as id
	 */
	public void setID(String iD);

	/**
	 * get the name of the user
	 * @return the name
	 */
	public String getName() ;

	/**
	 * set the name of the user
	 * @param name the String of the full name
	 */
	public void setName(String name) ;

	/**
	 * get the album list for that user
	 * @return the arraylist of albums
	 */
	public ArrayList<Album> getAlbums() ;

	/**
	 * set the list of albums for a user
	 * @param albums the arraylist of albums
	 */
	public void setAlbums(ArrayList<Album> albums) ;
	
	
	
	//Album Functionality
	/**
	 * add an album to the list of albums for the user
	 * @param albumName  the name of the album to be added
	 * @return success or not
	 */
	public boolean addAlbum(String albumName);
	
	/**
	 * delete an album from the list of a user's albums
	 * @param albumName the name of th album to be deleted
	 * @return success or not
	 */
	public boolean deleteAlbum(String albumName) ;
	
	/**
	 * renames an album 
	 * @param current the name of the album to be renamed
	 * @param newName the new name that it is to be set to
	 * @return success or not
	 */
	public boolean renameAlbum(String current, String newName) ;
	
	/**
	 * lists the albums that belong to the user
	 */
	public void listAlbums();
	
	/**
	 * @param photoId for the photo passed in
	 * @param caption
	 * @param albumName
	 * @return boolean
	 * */
	public boolean addPhoto(String photoId, String caption, String albumName);

	
}
