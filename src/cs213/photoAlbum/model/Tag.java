/**
 * @version 1.0
 */
package cs213.photoAlbum.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this is the class for all the tags 
 * @author Jasmine Feng and Risham Chokshi
 */
public class Tag implements Serializable{
	private static final long serialVersionUID = 7526472295622776147L;
	/**
	 * name/type of the tag
	 * for example location
	 */
	private String name; 
	
	/**
	 * description/value added with the tag
	 * for example new jersey
	 */	
	private String description;
	/**
	 * this will keep track of all the photos that have that particular tag
	 */
	private ArrayList<Photo> photos;
	

	/**
	 * @param name
	 * @param description
	 */
	
	
	public Tag(String name, String description){
		this.name = name;
		this.description = description;
		//System.out.println("i m here3: " + getName() + " " + getDescription());
		photos = new ArrayList<Photo>();
		
	}

	/**
	 * get the type of the tag
	 * @return the string of the tag type
	 */
	public String getName() {
		return name;
	}

	/**
	 * set the type of the tag
	 * @param name the tag type
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * get the arrylist of the photos
	 * @return arraylist of photo
	 */
	public ArrayList<Photo> getPhotos() {
		return photos;
	}
	/**
	 * set photos
	 * @param photos for setting it
	 */
	public void setPhotos(ArrayList<Photo> photos) {
		this.photos = photos;
	}

	/**
	 * get the value of the tag
	 * @return the string value
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * sets the value of the tag
	 * @param description the string value of the tag
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * to add photo
	 * @param photo
	 * */
	public void addPhoto(Photo photo){
		if(photos.isEmpty())
			photos.add(photo);
		else{
			//added to it, if not found, then add
			for(int i =0; photos.isEmpty()!=true && i< photos.size(); i ++){
				if(photos.get(i).getFilename().equals(photo.getFilename()))
					return;
			}
			// not in the list
			photos.add(photo);
		}
	}
	/**
	 * to delete photo
	 * @param photo
	 * */
	public void deletePhoto(Photo photo){
		if(photo!=null){
			if(photos.isEmpty()==false){
				//it has something in it
				for(int i = 0 ; i < photos.size(); i ++){
					if(photo.getFilename().equals(photos.get(i).getFilename())){
						photos.remove(i);
						return;
					}
						
				}
			}
		}
	}
}