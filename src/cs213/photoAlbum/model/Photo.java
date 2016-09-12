/**
 * @version 1.0
 */
package cs213.photoAlbum.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * this is the photo class for all the photos 
 * @author Jasmine Feng and Risham Chokshi
 */
public class Photo implements Serializable{
	private static final long serialVersionUID = 7526472295622776147L;
	/**
	 * the name of the file where the photo is stored
	 * it is unique per user
	 */
	private String filename; //this is unique per user
	/**
	 * the caption for the photo
	 */
	private String caption;
	/**
	 * the date and time the photo was taken
	 */
	private Calendar timestamp; //date and time photo was taken, set milliseconds to zero
	/**
	 * the albums the photo belongs in as an arraylist
	 */
	private ArrayList<Album> albums;
	
	/**
	 * the list of tags associated with the photo
	 */
	private ArrayList<Tag> tags;

	/**
	 * Constructor, initializes a Tag ArrayList
	 * @param filename
	 * @param caption
	 * @param timestamp
	 */
	public Photo(String filename, String caption, Calendar timestamp) {

		this.filename = filename;
		this.caption = caption;
		this.timestamp = timestamp;
		this.albums = new ArrayList<Album>();
		this.tags = new ArrayList<Tag>();
	}
		
	//Getters and Setters
	/**
	 * get the file name of the photo
	 * @return the string filename
	 */
	public String getFilename() {
		return filename;
	}
	
	/**
	 * set the filename of the photo
	 * @param filename the String filename of the photo
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	/**
	 * gives the caption
	 * @return the string caption
	 */
	public String getCaption() {
		return caption;
	}
	
	/**
	 * set the caption
	 * @param caption as a string
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	/**
	 * get the timestamp
	 * @return the timestamp of the photo
	 */
	public Calendar getTimestamp() {
		return timestamp;
	}
	
	/**
	 * set the timestamp
	 * @param timestamp the Calendar object
	 */
	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * get the albums that the photo is in as an arraylist
	 * @return the albums as an arraylist
	 */
	public ArrayList<Album> getAlbums() {
		return albums;
	}

	/**
	 * set the albums
	 * @param albums the albums to set
	 */
	public void setAlbums(ArrayList<Album> albums) {
		this.albums = albums;
	}
	

	/**
	 * adds a tag to a photo
	 * @param tag of the tag which neds to be added
	 * @return true if success, false if failed
	 */
	public boolean addTag(Tag tag) {
		//add tag if its unique and not found, 
		//if tagName matched then you can check if there is another one 
		//location cant be two. 
		//System.out.println("i m here2: " + tag.getName() + " " + tag.getDescription());
		
			if(tags==null){
			//first node which needs to be the added
				tags = new ArrayList<Tag>();
			}
					if(tags.isEmpty()==true){
					//this is false, could add stuff
					//Tag temp = new Tag(tagName, tagValue);
					//totalData.addTag(this, temp);
					//System.out.println("i m here: " + tag.getName() + " " + tag.getDescription());					
					tags.add(tag);
					//System.out.println("Added tag:");
					//System.out.println(filename + " " + tag.getName() + ": " + tag.getDescription());
					return true;
				}
					else{
						//see if the tag already exists
						
						for (int j = 0; tags.isEmpty() != true && j < tags.size(); j++) {
							//getting the tags of the photo
							String name = tags.get(j).getName();
							String descrip = tags.get(j).getDescription();
							if (name.equalsIgnoreCase("location") == true && tag.getName().equalsIgnoreCase("location")) {
								// the location is the same, you
								// cant do
								// that
								//System.out.println("Error: Location for photo " + filename + " already exists");
								return false;
							} 
							else if(name.equalsIgnoreCase(tag.getName())&& name.equalsIgnoreCase("person")&& descrip.equalsIgnoreCase(tag.getDescription())){
								//System.out.println("Tag already exists for " + filename + " " + tag.getName() +":"+ tag.getDescription());
								return false;
							}
							else if (name.equals(tag.getName()) && descrip.equals(tag.getDescription())) {
								// same tag
								//System.out.println("Tag already exists for " + filename + " " + tag.getName() +":"+ tag.getDescription());
								return false;
							}
						}
						// if here then the tag does not exist, and
						// we can
						// add it
						
						tags.add(tag);
						sortTags(this);
						//System.out.println("Added tag:");
						//System.out.println(filename + " " + tag.getName() + " " + tag.getDescription());
						return true;
					}
						
	
	}
	/**
	 * delete a tag to a photo
	 * @param tagType the type of tag, like location, etc
	 * @param tagValue the value of the tag, like New Jersey, etc
	 * @return true if success, false if failed
	 */
	public boolean deleteTag( String tagType , String tagValue){
		//to delete we need to find it
		for (int j = 0; tags.isEmpty() != true && j < tags.size(); j++) {
			//getting the tags of the photo
			String name = tags.get(j).getName();
			String descrip = tags.get(j).getDescription();
			
			if (name.equalsIgnoreCase("location") && descrip.equalsIgnoreCase(tagValue)&& name.equalsIgnoreCase(tagType)) {
				tags.remove(j);
				sortTags(this);
					//System.out.println("Deleted tag:");
					//System.out.println(filename + " " + tagType + " " + tagValue);
				return true;
			}
			else if (name.equalsIgnoreCase("person") && descrip.equalsIgnoreCase(tagValue)&& name.equalsIgnoreCase(tagType)) {
				tags.remove(j);
				sortTags(this);
					//System.out.println("Deleted tag:");
					//System.out.println(filename + " " + tagType + " " + tagValue);
				return true;
			}
			else if (descrip.equals(tagValue)&& name.equals(tagType)) {
				// same tag, delete it out
				tags.remove(j);
				sortTags(this);
					//System.out.println("Deleted tag:");
					//System.out.println(filename + " " + tagType + " " + tagValue);
				return true;
			}
		}
		//System.out.println("Tag does not exist for " + filename + " " + tagType +": " + tagValue);
		
		return false;
	}
	
	/**
	 * get the tags of the photos as an arraylist
	 * @return the tags
	 */
	public ArrayList<Tag> getTags() {
		return tags;
	}

	/**
	 * set the tags of a photo
	 * probably not necessary as the arraylist will be initialized and the tags added one by one after
	 * @param tags the tags to set
	 */
	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}
	
	/**
	 * To string method for photos
	 * @return string that needs to be returned
	 * */
	
	@Override
	public String toString(){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date dat = timestamp.getTime();
		String dat1 = sdf.format(dat);
		return this.filename + " - " + dat1;
	}
	
	/**
	 * list the info for the photo
	 * @param ID for the user which needs to be listed
	 */
	public void listPhotoInfo(String ID){
		int first =0;
		String alb = "";
		if(albums.isEmpty())
			System.out.println("");
		
		for(int i = 0; albums.isEmpty()==false && i<albums.size(); i ++){
			//System.out.println("Album: " + albums.get(i).getName() + " " + albums.get(i).getUser().getID());
		
			if(first==0 && albums.get(i).getUser().getID().equals(ID)){
				alb = alb + "Album: " + albums.get(i).getName();
				first++;
			}
			else if(first>0 && albums.get(i).getUser().getID().equals(ID))
				alb = alb + "[," + albums.get(i).getName() + "]";
			
		}
		if(first==0){
			//no albums from the user
			System.out.println("Error: Photo " + filename + " does not exist for this user");
			return;
			
		}
		System.out.println("Photo file name: " + filename);
		System.out.print(alb);
		
		System.out.println();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date dat = timestamp.getTime();
		String dat1 = sdf.format(dat);
		
		System.out.println("Date: " + dat1);
		System.out.println("Caption: " + caption);
		System.out.println("Tags: ");
		sortTags(this);
		
		for(int i = 0; tags.isEmpty()==false && i<tags.size(); i ++){
			
				System.out.println( tags.get(i).getName() + ":" + tags.get(i).getDescription());
			
		}
		}
	
	
	/**
	 * edit tag of the tags
	 * @param caption of the photo which needs to be edited
	 */
	public void editCaption(String caption){
		if(caption!=null)
		this.setCaption(caption);
	}
	
	
	
	
	/**
	 * sortTags of the tags
	 * @param photo of the photo which needs to be sorted
	 */
	public void sortTags(Photo photo){
		//sort with location and then people
		if(photo.getTags().isEmpty() || photo.getTags().size()<=1){
			return;
		}
	
		
		for(int i =0; i<photo.getTags().size(); i++){
			
			for(int j = i+1; j<photo.getTags().size();j++){
				if(photo.getTags().get(j).getName().equalsIgnoreCase("location") && photo.getTags().get(i).getName().equalsIgnoreCase("location")!=true){ // this is location so needs to be first
					//take it to the front
					Tag ptr = photo.getTags().get(j);
					photo.getTags().set(j, photo.getTags().get(i));
					photo.getTags().set(i, ptr);
					break;
				}
				else if(photo.getTags().get(j).getName().equalsIgnoreCase("person")&&photo.getTags().get(i).getName().equalsIgnoreCase("person")!=true && photo.getTags().get(i).getName().equalsIgnoreCase("location")!=true){
					Tag ptr = photo.getTags().get(j);
					photo.getTags().set(j, photo.getTags().get(i));
					photo.getTags().set(i, ptr);
					
				}
				else if(photo.getTags().get(j).getName().equalsIgnoreCase("person")&&photo.getTags().get(i).getName().equalsIgnoreCase("person")==true&&photo.getTags().get(i).getDescription().compareTo(photo.getTags().get(j).getDescription())>0){
					Tag ptr = photo.getTags().get(j);
					photo.getTags().set(j, photo.getTags().get(i));
					photo.getTags().set(i, ptr);
				}
				else if(photo.getTags().get(j).getName().equals(photo.getTags().get(i).getName())&&photo.getTags().get(i).getDescription().compareTo(photo.getTags().get(j).getDescription())>0){
					Tag ptr = photo.getTags().get(j);
					photo.getTags().set(j, photo.getTags().get(i));
					photo.getTags().set(i, ptr);
				}
				
				
				}
			
		}
			
	}

}
