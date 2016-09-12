/**
 * @version 1.0
 */

package cs213.photoAlbum.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.CalendarDateWithoutTimeComparator;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.Tag;
import cs213.photoAlbum.model.User;
import cs213.photoAlbum.model.totalData;
import cs213.photoAlbum.simpleview.InteractiveView;

/**
 * The implementation of the Controller interface
 * @author Jasmine Feng and Risham Chokshi
 */
public class ProgramControl implements Controller{
	
	ArrayList<User> users;
	public User currentUser;
	String userFilename = "\\data\\users.ser";
	String photoFilename = "\\data\\photos.txt";
	String albumFilename = "\\data\\albums.txt";
	
	/**
	 * the constructor
	 * @param users the list of users
	 */
	public ProgramControl(ArrayList<User> users) {
		this.users = users;
	}
	
	/**
	 * sets up the user arraylist
	 */
	public void setUsers(ArrayList<User> users){
		this.users = users;
	}
	
	//Functionality
	/**
	 * creates and adds an album to the user's list, calls addAlbum in User class
	 * @param name of the new album
	 * @return
	 */
	public boolean createAlbum(String name) {
		
		return currentUser.addAlbum(name);

	}
	
	/**
	 * removes an album from the user's list by name, calls deleteAlbum in User class
	 * @param name the string name of the album
	 * @return
	 */
	public boolean deleteAlbum(String name) {
		return currentUser.deleteAlbum(name);
	}
	
	/**
	 * to list album names, calls listAlbum in User class
	 */
	public void listAlbums() {
		currentUser.listAlbums();
	}
	
	/**
	 * renames the album
	 * @param oldName the album to be changed
	 * @param newName the new name of the album
	 * @return 
	 */
	public boolean renameAlbum(String oldName, String newName) {
		return currentUser.renameAlbum(oldName, newName);
	
	}
	
	/**
	 * lists the photos in a particular album
	 * @param albumName the string albumname
	 */
	public void listPhotos(String albumName) {
		//System.out.println("here");
		currentUser.listPhotos(albumName);
	}
	
	/**
	 * adds a photo to an album
	 * @param filename the string photo filename
	 * @param caption the string caption
	 * @param albumName the string album name
	 * @return boolean
	 */
	public boolean addPhoto(String filename, String caption, String albumName) {
		return currentUser.addPhoto(filename, caption, albumName);
		
	}
	
	/**
	 * moves a photo from one album to another
	 * @param filename the string photo filename
	 * @param oldAlbumName the string old album name
	 * @param newAlbumName the string album name that the photo will be moved to
	 * @return boolean
	 */
	public boolean movePhoto(String filename, String oldAlbumName, String newAlbumName) {
		return currentUser.movePhoto(filename, oldAlbumName, newAlbumName);
		//return true;
	}
	
	/**
	 * removes a photo from an album
	 * @param filename the String photo filename
	 * @param albumName the String albumname
	 * @return boolean
	 */
	public boolean removePhoto(String filename, String albumName) {
		return currentUser.removePhoto(filename, albumName);
		
	}
	
	/**
	 * edits the caption of the photo
	 * @param photo to be changed
	 * @param caption the new caption for the photo
	 * @return
	 */
	public boolean editCaption(String photo, String caption) {
		return totalData.editCaption(photo, caption, currentUser.getID());
		
	}
	
	
	
	/**
	 * adds a tag to a photo
	 * @param filename the string filename
	 * @param tagName the string tag name
	 * @param tagValue the string tag value
	 * @return
	 */
	public boolean addTag(String filename, String tagName, String tagValue) {
		//System.out.println(filename);
		//System.out.println(filename + " " + tagName + " " + tagValue);
		if(totalData.addTag(filename, tagName, tagValue)==-1)
			return false;
		return true;
		
	}
	
	/**
	 * deletes the tag
	 * @return true if success, false if failed
	 */
	public boolean deleteTag(String filename, String tagName, String tagValue) {
		if(totalData.deleteTag(filename, tagName, tagValue) == -1)
			return false;
		return true;
	}
	
	//Helper methods
	/**
	 * calls the findAlbum in User class
	 * @param name of the album
	 * @return Album object or null if not found
	 */
	public Album findAlbum(String name) {
		return null;
	}
	
	/**
	 * finds a photo
	 * @param filename the String filename of the photo
	 * @return photo object or null if not found
	 */
	public Photo findPhoto(String filename) {
		return null;
	}
	
	public static ArrayList<User> sortUsers(ArrayList<User> users){
		int i, j, first;
		User temp;  
		
	     for ( i = 0; i < users.size(); i++ )  
	     {
	    	 //int x = Integer.parseInt(users.get(i).getID());
	    	 String x = users.get(i).getID();
	    	 first = i;   //initialize to subscript of first element
	    	 //int smallest = x; //initalize to x
	         String smallest = x; 
	    	 for(j = i + 1; j < users.size(); j ++)   //locate smallest element between positions 1 and i.
	          {
	        	 //int y = Integer.parseInt(users.get(j).getID());
	             String y =   users.get(j).getID();
	    		 //if( y < smallest) {        
	            	 if(y.compareTo(smallest)<0){  
	            	   first = j;
	            	   smallest = y;
	            	   
	               }
	          }
	          temp = users.get(first);   //swap smallest found with element in position i.
	          users.set(first, users.get(i));
	          users.set(i, temp);
	      }
	     return users;
	}
	
	
	/**
	 * list out the users in the system
	 */
	public void listUsers() {
		//System.out.println(totalData.users.size());
		if(totalData.users.isEmpty()){
			System.out.println("no users exist");
		}
		ArrayList<User> temp = totalData.users;
		temp = sortUsers(temp);
		for(int i=0; i<temp.size(); i++) {
			//System.out.println("here");
			System.out.println(temp.get(i).getID());
		}
	}
	
	/**
	 * adds a user to the system
	 * @param id the string id
	 * @param name the name of the user
	 * @return true if success, false if failed
	 */
	public boolean addUser(String id, String name){
		
		try{
		int idI = Integer.parseInt(id);
		}
		catch(NumberFormatException e){
			//System.out.println("Error: User Id should be an integer");
			return false;
		}
		if(totalData.users == null){
			totalData.users.add(new User(id, name));
			System.out.println("created user "+id+ " with name " +name); 
			return true;
		}
		User us = findUser(id);
		if(us == null){
			totalData.users.add(new User(id, name));
			System.out.println("created user "+id+ " with name " +name); 
			return true;
			
		}
		else{
			System.out.println("user "+ id+" already exists with name " + us.getName());
			return false;
		}
		
	}
	
	/**
	 * removes a user by their id
	 * @param id as a String
	 * @return true if success, false if failed
	 */
	public boolean deleteUser(String id) {
		for(int i=0; i<totalData.users.size(); i++){
			if(totalData.users.get(i).getID().equalsIgnoreCase(id)){
				totalData.users.remove(i);
				System.out.println("deleted user " + id); 
				return true;
			}
		}
		//user not found
		System.out.println("user " + id+" does not exist");
		return false;
	}
	
	/**
	 * log in as a specific user by String id
	 * @param id of the user as a String
	 * @return true if success, false if failed
	 */
	public boolean login(String id) {
		User user = findUser(id);
		if(user != null) {
			currentUser = user;
			return true;
		}else{
			//user not found
			System.out.println("user "+ id+" does not exist");
			return false;
		}
	}
	
	/**
	 * logs the user out of the system
	 * @return true if success, false if failed
	 */
	public boolean logout() {
		if(currentUser != null){
			//there is someone logged in
			currentUser = null;
			return true;
		}else{
			//no one is logged in, error
			return false;
		}
	}
	
	
	//Helper Methods
	/**
	 * find a user by the String id
	 * @param id the String user id
	 * @return User the User if found, null otherwise
	 */
	public User findUser(String id) {
		//System.out.println("findUser");
		for(int i=0; i<totalData.users.size(); i++){
			if(totalData.users.get(i).getID().equals(id)){
				//System.out.println(totalData.users.get(i).getID() + " " + totalData.users.get(i).getName());
				return totalData.users.get(i);
			}
		}
		return null;
	}
	
	//reading files stuff
	/**
	 * load the users from a .ser file in data folder
	 * @return ArrayList<User>  
	 * @throws IOException 
	 */
	public ArrayList<User> readUsersFromFile() throws IOException {
		ArrayList<User> users = new ArrayList<User>();
		
		String path = System.getProperty("user.dir");
		//System.out.println(path);
		path = path.substring(0, path.length());
		path += userFilename;
		
		//System.out.println(path);
		
		BufferedReader br = new BufferedReader(new FileReader(path));     
		if (br.readLine() == null) {
		    System.out.println("empty file, no data to be read in");
		    br.close();
		    return users;
		}
		
		try{
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			users = (ArrayList<User>) in.readObject();
			in.close();
			fileIn.close();
			return users;
		}catch(IOException exception){
			
			System.out.println("file does not exist or is blank");
			return users;
		}catch(ClassNotFoundException exception){
			System.out.println("User class does not exist");
			return users;
		}
		
		
	}
	
	/**
	 * NOT USED
	 * load the photo information from file, like caption, filename, etc
	 * format like so:
	 * filename|caption|userid|album1@album2@album3@|
	 * @return true if success, false if failed
	 * @throws IOException 
	 */
	public boolean readPhotosFromFile() throws IOException {
		
		String path = System.getProperty("user.dir");
		path = path.substring(0, path.length()-4);
		path += photoFilename;
		
		File file = new File (path);
		
		if(!file.exists()) {
			file.createNewFile();
			return false;
		}
	
		//System.out.println("/n" + path + "/n");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		
		while((line = br.readLine()) != null) {
			if(line.length()>1){
				String[] tokens = line.split("\\|");
				Photo photo = new Photo(tokens[0], tokens[1], new GregorianCalendar());
			}
		}
		
		br.close();
		return true;
	}
	
	/**
	 * NOT USED
	 * load the album information from file
	 * format the album file like:
	 * name | userid
	 * @param filename String filename
	 * @return true if success, false if failed
	 * @throws IOException 
	 */
	public boolean readAlbumsFromFile() throws IOException {
		//System.out.println("reading albums from file");
		String path = System.getProperty("user.dir");
		path = path.substring(0, path.length()-4);
		path += albumFilename;
		
		File file = new File (path);
		
		if(!file.exists()) {
			file.createNewFile();
			return false;
		}
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String line;
		
		while((line = br.readLine()) != null) {
			if(line.length()>1){
				String[] tokens = line.split("\\|");
				System.out.println(tokens.length);
				//tokens[1] is where the user id is stored
				User user = findUser(tokens[1]);
				if(user != null){
					user.addAlbum(tokens[0]);
				}
			}
		}
		br.close();
		return true;
	}
	
	/**
	 * This method is called to update the .ser file when the user logs out.
	 * it generates a new .ser file in the data folder so that the current state can be
	 * reloaded again
	 */
	public void updateUserFile(ArrayList<User> users) throws IOException{
		//System.out.println("updating user file");
		String path = System.getProperty("user.dir");
		path = path.substring(0, path.length());
		path += userFilename;
		//System.out.println(path);
		try{

			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(totalData.users);
			out.close();
			fileOut.close();
			
		}catch(IOException i){
			i.printStackTrace();
		}
	}
	
	/**
	 * format like so:
	 * filename | caption | albumname | userid
	 * @throws IOException 
	 * NOT USED
	 * 
	 */
	public void updatePhotoFile() throws IOException{
		System.out.println("updating photo file");
		String path = System.getProperty("user.dir");
		path = path.substring(0, path.length()-4);
		path += photoFilename;
		
		File file = new File (path);
		
		if(!file.exists()) {
			file.createNewFile();
		}

		//System.out.println("/n" + path + "/n");		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		String line = "";
		ArrayList<Photo> photolist;
		for(int i=0; i<users.size(); i++) {
			System.out.println("\t user: " + users.get(i).getName());
			for(int y=0; y<users.get(i).getAlbums().size(); y++){
				photolist = users.get(i).getAlbums().get(y).getPhotos();
				System.out.println("\t album " + users.get(i).getAlbums().get(y).getName());
				for(int z=0; z<photolist.size(); z++){	
					System.out.println("\t photo: " + photolist.get(z).getFilename());
					line += photolist.get(z).getFilename() + "|" + photolist.get(z).getCaption() + "|" + users.get(i).getID() + "|";
						for(int t=0; t<photolist.get(z).getAlbums().size();t++){
							System.out.println("\t albums that contain it: " + photolist.get(z).getAlbums().get(t));
							line += photolist.get(z).getAlbums().get(t).getName() + "@";
						}
						line += "\n";
					System.out.println(line);
					bw.write(line);
					line = "";
				}
			}

		}
		bw.flush();
		bw.close();
	}
	
	/**
	 * not used
	 */
	public void updateAlbumFile() throws IOException{
		System.out.println("updating album file");
		String path = System.getProperty("user.dir");
		path = path.substring(0, path.length()-4);
		path += photoFilename;
		
		File file = new File (path);
		
		if(!file.exists()) {
			file.createNewFile();
			System.out.println("here");
		}

		System.out.println("\n" + path + "\n");		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		//testing the file writing.....
		bw.write("hi");
		
		
		String line = "";
		ArrayList<Photo> photolist;
		for(int i=0; i<users.size(); i++) {
			System.out.println("\t user: " + users.get(i).getName());
			for(int y=0; y<users.get(i).getAlbums().size(); y++){
				line += users.get(i).getAlbums().get(y).getName() + "|" + users.get(i).getID() + "\n";
				System.out.println(line);
				bw.write(line);
				bw.newLine();
				//bw.flush();
				line = "";
				
			}

		}
		bw.flush();
		bw.close();
	}



	/**
	 * lists photo by info
	 * @param filename the string name of the photo file
	 * @param id the string id of the user
	 */
	public void listPhotoInfo(String filename, String id){
		//System.out.println(id);
		totalData.listPhotoInfo(filename, currentUser.getID());
	}
	
	
	
	
	
	
	private int sanityCheck(int month, int day, int year, int hours, int min, int sec){
		if(month<=0 || month>12){
			return -1;
		}
		
		if(hours<0 || hours>23 || min<0 || min>59 || sec<0 || sec>59)
			return -1;
		
		
		if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12 ){
			return 31 - day;
		}
		else if(month==2){
			//CHECK IF ITS A LEAP YEAR OR NOT
			if((year%4==0)){
				if((year%100 == 0)){
					if((year%400==0)){
						//leap year
						return 29 - day;
					}
					else
						//not a leap year
						return 28 - day;
				}
				else
					//leap year
					return 29 - day;
				
			}
			else
				//not a leap year
				 return 28 - day;
		}
		
		else if(month==4 || month==6 || month==9 ||month==11 ){
			return 30 - day;
		}
		
		//doing the hours and mins and secs normally
		
		
		
		
		
		
	return -1;
		
	}
	
	/**
	 * retrieves all photos taken in a range of dates in chronological order
	 * @param startDate the beginning of the range
	 * @param endDate the end of the range
	 * @return ArrayList<Photo> 
	 * @throws ParseException 
	 */
	public ArrayList<Photo> getPhotosByDate(String startDate, String endDate) throws ParseException{
		Calendar cStart = Calendar.getInstance();
		Calendar cEnd = Calendar.getInstance();
		if(startDate!=null && endDate!=null){
		//System.out.println(startDate+ endDate);
		startDate = startDate.trim();
		endDate = endDate.trim();
		
		String test = startDate.substring(0, startDate.length());
		
		
		try{
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
			
			Date date = dateFormat.parse(startDate);
			
			cStart.setTime(date);
			
			int ind = startDate.indexOf('/');
		
			int mo = Integer.parseInt(startDate.substring(0, ind));
			
			String rest = startDate.substring(ind+1, startDate.length());
			int ind2 = rest.indexOf('/');
		
			int day = Integer.parseInt(rest.substring(0, ind2));
			
			rest = rest.substring(ind2+1, rest.length());
			
			//System.out.println(day + " " + mo + " " + rest);
			ind = rest.indexOf('-');
			int year = Integer.parseInt(rest.substring(0, ind));
			rest = rest.substring(ind+1, rest.length());
			//how doing the hours and mins and seconds
			ind = rest.indexOf(':');
			int hours = Integer.parseInt(rest.substring(0, ind));
			rest = rest.substring(ind+1, rest.length());
			
			ind = rest.indexOf(':');
			int min = Integer.parseInt(rest.substring(0, ind));
			rest = rest.substring(ind+1, rest.length());
			
			int sec = Integer.parseInt(rest);
			
			if(sanityCheck(mo,day,year,hours,min,sec)<0){
				//there is some error
				//System.out.println("Error: "+ startDate + " is not a valid entry, please try again");
				return null;
			}
		}
		catch(ParseException e){
			
			//System.out.println("Error: improperly formatted dates, please try again with MM/dd/yyyy-HH:mm:ss");
			return null;
		}
		
		try{
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
			Date date2 = dateFormat2.parse(endDate);
			
			cEnd.setTime(date2);
			//sanity check
			int ind = endDate.indexOf('/');
			
			int mo = Integer.parseInt(endDate.substring(0, ind));
			
			String rest = endDate.substring(ind+1, endDate.length());
			int ind2 = rest.indexOf('/');
		
			int day = Integer.parseInt(rest.substring(0, ind2));
			
			rest = rest.substring(ind2+1, rest.length());
			
			//System.out.println(day + " " + mo + " " + rest);
			
			ind = rest.indexOf('-');
			int year = Integer.parseInt(rest.substring(0, ind));
			rest = rest.substring(ind+1, rest.length());
			
			//how doing the hours and mins and seconds
			ind = rest.indexOf(':');
			int hours = Integer.parseInt(rest.substring(0, ind));
			rest = rest.substring(ind+1, rest.length());
			
			ind = rest.indexOf(':');
			int min = Integer.parseInt(rest.substring(0, ind));
			rest = rest.substring(ind+1, rest.length());
			
			int sec = Integer.parseInt(rest);
			//System.out.println("Month: " + mo + "day: " + day + "year: " + year + "hours: " + hours+ "min: " + min + "sec: " +sec);
			if(sanityCheck(mo,day,year,hours,min,sec)<0){
				//there is some error
				//System.out.println("Error: "+ endDate + " is not a valid entry, please try again");
				return null;
			}
			
			
			
			
			
			
		}catch(ParseException e){
			
			//System.out.println("Error: improperly formatted dates, please try again with MM/dd/yyyy-HH:mm:ss");
			return null;
		}
		
		cStart.set(Calendar.MILLISECOND, 0);
		cEnd.set(Calendar.MILLISECOND, 0);
		
		if(CalendarDateWithoutTimeComparator.sanityCheck(cStart)>= 0 && CalendarDateWithoutTimeComparator.sanityCheck(cEnd) >= 0 && CalendarDateWithoutTimeComparator.comparedate(cStart,cEnd)<=0){
			//System.out.println(cStart.getTime().toString() + " " + cEnd.getTime().toString());
			return currentUser.getPhotosByDate(cStart, cEnd);
		}
		else{
			//System.out.println("Error: improperly formatted dates, please try again");
		}
		}
		else{
			//System.out.println("Error: Input for getPhotosByDate cannot be null");
		}

		return null;
	}
	
	/**
	 * To retrieve all photos that have all the given tags, in chronological order. 
	 * Tags can be specified with or without their types
	 * @param tags String[] of tags in format - <tagType>:"tagValue"
	 */
	public ArrayList<Photo> getPhotosByTag(String[] tags, String search){
		ArrayList<Tag> tagList = new ArrayList<Tag>();
		String se="";
		for(int i=0; i<tags.length; i++){
			String[] info = tags[i].split(":");
			
			if(info.length == 1){
				//there is only a tag value
				Tag tag = new Tag("", info[0]);
				se = se + info[0] + ", ";
 				tagList.add(tag);
			}
			else if(info.length != 2){
				//System.out.println("Improperly formatted arguments. Enter help for a list of commands");
				return null;
			}
			else{
				se = se + info[0] + " : " + info[1] + ", ";
				
				Tag tag = new Tag(info[0], info[1]);
				tagList.add(tag);
			}
		}
		se = se.trim();
		
		if(se.charAt(se.length()-1)==',')
			se = se.substring(0, se.length()-1);
		//System.out.println(se);
		return totalData.getPhotosByTag(tagList, se, currentUser.getID());
	
	}
	
}
