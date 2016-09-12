/**
 * @version 1.0
 */

package cs213.photoAlbum.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/*
 * Album will contain zero or more photos, 
 * A single user may not have duplicate album names, 
 * but the name may be duplicated across users.
 * Photos may appear in multiple albums of a user
 */

/**
 * this class is the album
 * @author Jasmine Feng and Risham Chokshi
 */
public class Album implements Serializable{

	/*
	 * the name of the album
	 */
	/**
	 * the name of the album
	 * will be stored here
	 */
	private String name; 
	
	/**
	 * the list of photos in an album
	 * stored in an ArrayList
	 */
	private ArrayList<Photo> photos;
	
	/**
	 * the user that the album belongs to
	 */
	private User user;
	private static final long serialVersionUID = 7526472295622776147L;
	


	/**
	 * start and end dates for the album
	 * */
	public Calendar startdate;
	public Calendar enddate; 
	
	/**
	 * the constructor, initializes an arraylist of photos
	 * 
	 * @param name the name of the album as a String
	 * @param user the User object of the user
	 */
	public Album(String name, User user) {
		super();
		this.name = name;
		this.photos = new ArrayList<Photo>();
		this.user = user;
		startdate = null;
		enddate = null;
	}
	
	
	/**
	 * gets the name of the album
	 * @return the string name of the album name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets the name of the album
	 * @param name of the album
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * get user
	 * @return user which will be returned
	 * */
	public User getUser() {
		return user;
	}

	/**
	 * set user value
	 * @param user
	 * */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * get photos as arraylist
	 * @return arraylist of photos
	 */
	public ArrayList<Photo> getPhotos() {
		return photos;
	}
	
	/**
	 * set photos
	 * @param photos in an arraylist
	 */
	public void setPhotos(ArrayList<Photo> photos) {
		this.photos = photos;
	}
	
	//functionality stuff
	/**
	 * @return the error, 0= ok and -1 no photos
	 * lists and prints out all the photos in an album
	 */
	public int listPhotos() {
		//error checking'
		//System.out.println("here");
		if(photos.isEmpty() == true || photos.size()==0){
			System.out.println("No photos in this album");
			return -1;
			}
		//if there is photos in this album
		
		//i need to sort
		sortbydate();
		for(int i =0; i < photos.size(); i++){
			
			System.out.println(photos.get(i));
		}
		return 0;
	}
	
	/**
	 * Helper method
	 * @param filename
	 * @return int of filename already exists
	 * */
	
	public int checkPhotos(String filename){
		if(photos.isEmpty() == true || photos.size()==0)
			return -1;
		filename = filename.trim();
		for(int i =0; i < photos.size(); i ++){
			
			if(photos.get(i)!=null && photos.get(i).getFilename().equals(filename)){
				return i;
			}
			
		}
		return -1;
	}
	
	/**
	 * @return it would return null if there is an error + print the error message
	 * @param filename
	 * @param caption
	 * */
	
	public Photo addPhotos(String filename, String caption,int ye) {
		
		
		if(filename == null ){
			//System.out.println("Error: filename cannot be null"); return null;
			return null;
			}
		
		//check if the filename already exists
		if(checkPhotos(filename)!=-1){
			//System.out.println("Error: Photo " + filename + " already exists in the album "+ name);
			return null;
		}
		
		//check for the file opening 
		filename = filename.trim();
		
		filename = totalData.filepass(filename);
		
		if(filename!=null){
		//System.out.println(filename);
		File file = new File(filename);
		//create a photo object
		//if file exists of not
		if(file.exists() && file.isDirectory()!=true){
			
		try
		{
		filename = file.getCanonicalPath();
		Calendar c = Calendar.getInstance();
		//to get the format
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		
		Date date = sdf.parse(sdf.format(file.lastModified()));
		//set the time for c
		
		c.setTime(date);
		c.set(Calendar.MILLISECOND,0);
		int ch = CalendarDateWithoutTimeComparator.sanityCheck(c); 
		if(ch<0){
			//sanity check is done and it failed
			//System.out.println("Error: Last day Modified for "+ filename + " is not correct, Photo is not added");
			return null;
		}
			
		//to set times according to the last and first photos
		if(this.enddate != null && this.startdate!=null){
			
			if(this.startdate.compareTo(c)>0)
				this.startdate = c;
			if(this.enddate.compareTo(c)<0)
				this.enddate = c;
		}
		
		else{
			this.startdate = c;
			this.enddate = c;
		}
		
		Photo temp;
		//adding photo to arraylist
		int check = totalData.checkPhotoIndex(filename);
		if(check==-1){ //photo not in there
		temp = new Photo(filename, caption,c);
		photos.add(temp);
		temp.getAlbums().add(this);
		totalData.addPhoto(temp, this); // add it to the totalDAta list
		
		
		}
		else{
			//take the photo they tell you
			temp = totalData.photos.get(check);
			temp.setTimestamp(c);
			photos.add(temp);
			temp.getAlbums().add(this);
			totalData.addPhoto(temp, this);
						
		}
		if(ye!=0){
		//System.out.println("Added photo " + filename + " :");
		//System.out.println(caption +" - Album: " + name);
		}
		return temp;
		
		}
		catch (ParseException e){
			//System.out.println("Error: Date of the file is cannot be parsed");
			return null;
		} catch (IOException e) {
			
			//System.out.println("Error: Cannot get file's path");
			return null;
		}
		}
		
		else
		{//to catch the invalid filename
			//System.out.println("Error: File " + filename + " does not exist");
			return null;			
		}
		}
		else
		{//to catch the invalid filename
			
			return null;			
		}
		}
	
	
	
	/**
	 * delete the photo
	 * @param filename
	 * @return int to tell them if there was an error or not
	 * */
	
	public int deletePhotos(String filename, int ye){
		if(filename!=null){
			filename = filename.trim();
			int p = checkPhotos(filename);
		if(p!=-1){
			//the index does exist and need to delete the photo
			//photos.remove(p);
			totalData.deletePhoto(photos.get(p), this);
			p = checkPhotos(filename);
			if(p!=-1)
				photos.remove(p);
			if(ye!=0){
			//System.out.println("Removed photo:");
			//System.out.println(filename + " - From album " + name);
			}
			return 0;
		}
		
		//System.out.println("Error: Photo " + filename + "is not in album " + name);
		return -1;
	}
		return -1;
	}
	
	private void sortbydate(){
		if(photos.isEmpty()!=true){
		CalendarDateWithoutTimeComparator c = new CalendarDateWithoutTimeComparator();
		Collections.sort(getPhotos(),c);
		//this will sort by date
		}
	}
	
	/**
	 * @param statedate
	 * @param enddate
	 * @return Arraylist of photo
	 * to list by date
	 * */
	
	
	public ArrayList<Photo> photoslistbydate(Calendar startdate, Calendar enddate){
		ArrayList<Photo> temp = new ArrayList<Photo>();
		sortbydate();//done sorting
		CalendarDateWithoutTimeComparator c = new CalendarDateWithoutTimeComparator();
		
		for(int i=0; photos.isEmpty()!=true && i < photos.size(); i++){
			//now if the statedate and enddate in the middle
		
			if((c.comparedate(startdate, photos.get(i).getTimestamp())<=0) && (c.comparedate(enddate, photos.get(i).getTimestamp())>=0))
				temp.add(photos.get(i));
			
			
		
		}
		return temp;
	}
	public void editcaption(String filename, String caption){
		for(int i =0; photos.isEmpty()!=true && i<photos.size(); i ++){
			if(photos.get(i).getFilename().equals(filename)){
				//change the caption
				photos.get(i).setCaption(caption);
				return;
			}
		}
	}
}
