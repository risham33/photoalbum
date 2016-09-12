/**
 * @version 1.0
 */
package cs213.photoAlbum.simpleview;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import cs213.photoAlbum.control.Controller;
import cs213.photoAlbum.control.ProgramControl;
import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.Tag;
import cs213.photoAlbum.model.User;
import cs213.photoAlbum.model.totalData;


/**
 * This is the command view, where the user interacts with the program via command line arguments.
 * Once the user logs in, it switches to the interactive view
 * @author Jasmine Feng and Risham Chokshi
 *
 */
public class CmdView {
	
	/**
	 * the instance of the Controller
	 */
	static Controller Controller;
	
	/**
	 * the list of users
	 */
	private static ArrayList<User> users;

	/**
	 * main method
	 * @param args the arguments
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {


		
		
		//initialize the controller and user list
		Controller = new ProgramControl(totalData.users);
		
		totalData.users = Controller.readUsersFromFile();
		Controller.setUsers(users);
		users = totalData.users;
		setup();
		//Controller.readAlbumsFromFile();
		//Controller.addUser("10", "hi");
		//System.exit(0);
		
		if(args.length == 1 && args[0].equals("test")){
			ArrayList<Photo> photo = new ArrayList<Photo>();
			photo.add(new Photo("hi.jpg", "test file", new GregorianCalendar(2013,0,31)));
			Album album = new Album("hi", users.get(0));
			album.setPhotos(photo);
			ArrayList<Album> albums = new ArrayList<Album>();
			photo.get(0).setAlbums(albums);
			albums.add(album);
			users.get(0).setAlbums(albums);
			Controller.updatePhotoFile();
			System.exit(0);
		}
		
		if(args.length == 1 && args[0].equals("listusers")){
			listUsers();
			System.exit(0);
		}
		else if(args.length ==3 && args[0].equals("adduser")){
			addUser(args[1], args[2]);
			Controller.updateUserFile(users);
			System.exit(0);
		}
		else if(args.length==2 && args[0].equals("deleteuser")){
			deleteUser(args[1]);
			Controller.updateUserFile(users);
			System.exit(0);
		}
		else if(args.length==2 && args[0].equals("login")){		
			login(args[1]);
			Controller.updateUserFile(users);
			System.exit(0);
		}
		else{
			//error in arguments
			System.out.println("incorrect format/number of arguments");
			System.out.println("listusers - list users");
			System.out.println("adduser <id> <name> - add a user");
			System.out.println("deleteuser <id> - delete a user");
			System.out.println("login <id> - log in as a user");
			System.exit(1);
		}

	}
	
	//Functionality
	
	/**
	 * Calls control to add a user to the system
	 * @param id the id of the user
	 * @param name the full name of the user to be added
	 * @return success or not
	 */
	public static boolean addUser(String id, String name){
		Controller.addUser(id, name);
		return true;
	}
	
	/**
	 * Calls control to delete a user by id
	 * @param id of the user to be deleted
	 * @return success or not
	 */
	public static boolean deleteUser(String id) {
		Controller.deleteUser(id);
		return true;
	}
	
	/**
	 * calls control to log in to the system with the id of a user
	 * @param id the string of the user logging in
	 * @return success or not
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static boolean login(String id) throws IOException, ParseException {
			//System.out.println(users.size());
			if(users == null ){
				System.out.println("no users found, please add a user first");
				return false;
			}else if(users.isEmpty()){
				System.out.println("no users found, please add a user first");
				return false;
			}
		
			User user = findUser(id);

			//System.out.println("...");
			boolean success = Controller.login(id);
			if(!success){
				//System.out.println("user does not exist :(");
				return false;
			}
			System.out.println("logging in....");
			InteractiveView iv = new InteractiveView(user, Controller);
			iv.run();
			
			
			
			
			return true;
			
	}
	
	/**
	 * lists the users
	 */
	public static void listUsers(){
		Controller.listUsers();
	}
	
	/**
	 * finds a user
	 * @param id the string id of the user
	 * @return User
	 */
	private static User findUser(String id){
		for(int i=0; i<users.size();i++){
			if(users.get(i).getID().equals(id)){
				return users.get(i);
			}
		}
		return null;
	}
	
	/**
	 * initializes totalData's data
	 */
	public static void setup(){
		//user list already created from deserialization
		
		//add all albums for every user to global stuff
		for(int i=0; i<totalData.users.size(); i++){
			totalData.albums.addAll(totalData.users.get(i).getAlbums());
		}
		
		//add all photos for every album for every user to global stuff
		for(int i=0; i<totalData.albums.size(); i++){
			for(int y=0; y<totalData.albums.get(i).getPhotos().size();y++){
				if(totalData.checkPhoto(totalData.albums.get(i).getPhotos().get(y)) == null){
					totalData.photos.add(totalData.albums.get(i).getPhotos().get(y));
				}
			}
		}
		
		//add all tags to global stuff
		for(int i=0; i<totalData.photos.size(); i++){
			for(int y=0; y<totalData.photos.get(i).getTags().size();y++){
				if(totalData.checkTagIndex(totalData.photos.get(i).getTags().get(y)) == -1){
					totalData.tags.add(totalData.photos.get(i).getTags().get(y));
				}
			}
		}
	}
	
	
	
	

}
