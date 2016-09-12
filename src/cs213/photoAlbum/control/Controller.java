/**
 * @version 1.0
 */

package cs213.photoAlbum.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.User;
import cs213.photoAlbum.model.backend;

/**
 * The interface that the controller used in view implements
 * @author Jasmine Feng and Risham Chokshi
 */
public interface Controller extends backend{
	
	public void setUsers(ArrayList<User> users);
	//Functionality
    /**
	* @param name the String name of the album
	* @return true or false for album existence
	*/
	public boolean createAlbum(String name);
		
	/**
	* @param name the String to delete that album name
	* @return to exit with a error if false and normally if true
	*/
	public boolean deleteAlbum(String name);
		
	/**
	* it would list out all the album names
	*/
	public void listAlbums();
	
	/**
	 * Lists the photos in one album
	 * @param albumName the name of the album
	 */
	public void listPhotos(String albumName);
	
	/**
	 * adds a photo to the album
	 * @param filename the String of the photo file
	 * @param caption the String photo description
	 * @param albumName the String album name
	 * @return success or not 
	 */
	public boolean addPhoto(String filename, String caption, String albumName);
	
	/**
	 * moves a photo from one album to another
	 * @param filename the string of the photo file
	 * @param oldAlbumName the string old album
	 * @param newAlbumName the string new album
	 * @return success or not
	 */
	public boolean movePhoto(String filename, String oldAlbumName, String newAlbumName);
	
	/**
	 * deletes a photo from the album
	 * @param filename the string filename of the photo
	 * @param albumName the string album name
	 * @return success or not
	 */
	public boolean removePhoto(String filename, String albumName);
	
	/**
	 * adds a tag to a photo
	 * @param filename the string filename
	 * @param tagName the string tag name
	 * @param tagValue the string tag value
	 * @return true if success
	 */
	public boolean addTag(String filename, String tagName, String tagValue);
	
	/**
	 * removes a tag from a photo
	 * @param filename the string photo filename
	 * @param tagName the tag name string
	 * @param tagValue the tag value string
	 * @return true if success
	 */
	public boolean deleteTag(String filename, String tagName, String tagValue);
	
	//Helper methods
	/**
	 * finds an album by its name
	 * @param name the string album name
	 * @return the album
	 */
	public Album findAlbum(String name);
	
	/**
	 * finds a photo
	 * @param filename the String filename of the photo
	 * @return photo object or null if not found
	 */
	public Photo findPhoto(String filename);
	
	/**
	 * list out the users in the system
	 */
	public void listUsers();
	
	/**
	 * adds a user to the system
	 * @param id the string id
	 * @param name the name of the user
	 * @return true if success
	 */
	public boolean addUser(String id, String name);
	
	/**
	 * removes a user by their id
	 * @param id as a String
	 * @return true if sucess
	 */
	public boolean deleteUser(String id);
	
	/**
	 * log in as a specific user by String id
	 * @param id string id of user
	 * @return true if success
	 */
	public boolean login(String id);
	
	
	//Helper Methods
	/**
	 * find a user by the String id
	 * @param id string id of user
	 * @return the User if found, null otherwise
	 */
	public User findUser(String id);
	
	/**
	 * load the users from a file
	 * @return an arraylist of User from the last time it was logged out
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public ArrayList<User> readUsersFromFile() throws FileNotFoundException, IOException;
	
	/**
	 * load the photo information from file, like caption, filename, etc
	 * not needed
	 * @return boolean
	 * @throws IOException 
	 */
	public boolean readPhotosFromFile() throws IOException;
	/**
	 * renames the album
	 * @param oldAlbum
	 * @param newAlbum
	 * @return boolean
	 */
	public boolean renameAlbum(String oldAlbum, String newAlbum);
	
	/**
	 * load the album information from file,
	 * method not needed
	 * @return boolean
	 * @throws IOException 
	 */
	public boolean readAlbumsFromFile() throws IOException;
	
	/**
	 * updates the user file with any changes that might have been made since log in
	 * @param users the list of users to update the file with
	 * @throws IOException
	 */
	public void updateUserFile(ArrayList<User> users) throws IOException;
	
	/**
	 * method not needed
	 * @throws IOException
	 */
	public void updatePhotoFile() throws IOException;
	
	/**
	 * method not needed
	 * @throws IOException
	 */
	public void updateAlbumFile()throws IOException;
	
	/**
	 * lists photo by info
	 * @param filename the string name of the photo file
	 * @param id the string id of the user
	 */
	public void listPhotoInfo(String filename, String id);
	
	/**
	 * retrieves all photos taken in a range of dates in chronological order
	 * @param startDate the beginning of the range
	 * @param endDate the end of the range
	 * @return ArrayList<Photo> 
	 * @throws ParseException 
	 */
	public ArrayList<Photo>  getPhotosByDate(String startDate, String endDate) throws ParseException;
	/**
	 * edits the caption of the photo
	 * @param photo to be changed
	 * @param caption the new caption for the photo
	 * @return boolean
	 */
	public boolean editCaption(String photo, String caption);
	/**
	 * To retrieve all photos that have all the given tags, in chronological order. 
	 * Tags can be specified with or without their types
	 * @param tags String[] of tags in format - <tagType>:"tagValue"
	 * @param search the search string
	 * @return ArrayList<Photo> 
	 */
	public ArrayList<Photo> getPhotosByTag(String[] tags, String search);
}
