/**
 * @version 1.0
 */
package cs213.photoAlbum.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * this is the user class for all the users
 * @author Jasmine Feng and Risham Chokshi
 *
 */
public class User implements IUser{
	private static final long serialVersionUID = 7526472295622776147L;
	/**
	 * the id of the user. it is a string
	 * unique, used to log in
	 */
	public String ID; 
	
	/**
	 * the full name of the user
	 */
	public String name;
	
	/**
	 * an list of albums for that user
	 */
	public ArrayList<Album> albums;
	
	/**
	 * Constructor, initializes the album arraylist for the user
	 * @param iD the String id of the user
	 * @param name the full name of the user
	 */
	public User(String iD, String name) {
		super();
		ID = iD;
		this.name = name;
		this.albums = new ArrayList<Album>();
	}

	// Getters and Setters
	/**
	 * get the string id
	 * @return the user id
	 */
	public String getID() {
		return ID;
	}

	/**
	 * set the user id 
	 * @param iD the string to be set as id
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * get the name of the user
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set the name of the user
	 * @param name the String of the full name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get the album list for that user
	 * @return the arraylist of albums
	 */
	public ArrayList<Album> getAlbums() {
		return albums;
	}

	/**
	 * set the list of albums for a user
	 * @param albums the arraylist of albums
	 */
	public void setAlbums(ArrayList<Album> albums) {
		this.albums = albums;
	}
	
	
	//Album Helper
	/**
	 * @param albumName
	 * @return -1 for error or index of the albumname
	 * */
	public int checkAlbum(String albumName){
		
		if(albums.isEmpty()!=true){
			
			for(int i = 0 ; i < albums.size(); i++){
				
				if(albums.get(i)!= null && albums.get(i).getName().equals(albumName)){
					return i;
				}
			}
			
			
		}
		return -1;
	}
	
	
	//Album Functionality
	/**
	 * add an album to the list of albums for the user
	 * @param albumName  the name of the album to be added
	 * @return success or not
	 */
	public boolean addAlbum(String albumName) {
		// check if there already is the album name
		//System.out.println("it comes her");
		if(albumName == null){
			//System.out.println("Error: albumName cannot be null");
			return false;
		}
		if(checkAlbum(albumName)==-1){
			//System.out.println("here add album");
			Album temp = new Album(albumName, this);
			temp.getUser().setID(ID);
			temp.getUser().setName(name);
			
			albums.add(temp);
			totalData.addAlbum(temp, this); // added in totaldata
			//System.out.println("created album for user "+ this.ID + ":");
			//System.out.println(albumName);
			return true;
		}
		//System.out.println("album exists for user " + this.ID + ":");
		//System.out.println(albumName);
		return false;
	}
	
	/**
	 * @param albumName
	 * list all the photos
	 * */
	
	public void listPhotos(String albumName){
		
		if(albumName!=null){
			
			albumName = albumName.trim();
			int check = checkAlbum(albumName);
			if(check!=-1){
				System.out.println("Photos for album " + albumName + " :" );
				albums.get(check).listPhotos();
			}
			else{
				System.out.println("Error: Album Name: "+ albumName + " does not exist");
			}
		}
		
	}
	
	
	/**
	 * delete an album from the list of a user's albums
	 * @param albumName the name of th album to be deleted
	 * @return success or not
	 */
	public boolean deleteAlbum(String albumName) {
		//to check if it exists
		if(albumName==null){
			//System.out.println("Error: albumName cannot be null");
			return false;
		}
		if(albumName!= null && checkAlbum(albumName)!=-1){
			//delete all the photos
			int check = checkAlbum(albumName);
			for(int i = albums.get(check).getPhotos().size()-1; albums.get(check).getPhotos().isEmpty() == false && i>=0; i--){
				//delete every photo one by one
				totalData.deletePhoto(albums.get(check).getPhotos().get(i), albums.get(check));
			}
			//delete photos from the album if there is 
			for(int i = albums.get(check).getPhotos().size()-1; albums.get(check).getPhotos().isEmpty() == false && i>=0; i--){
				//delete every photo one by one
				albums.get(check).getPhotos().remove(i);
			}
		//all photos removed
		//now remove user from the album
			totalData.deleteAlbum(albums.get(check), this);
		//delete
		check = checkAlbum(albumName);
		if(check!=-1){
			albums.remove(check);
		}
		//System.out.println("deleted album from user " + ID +" :"); 
		//System.out.println(albumName);
		return true;
		}
		//not found
		//System.out.println("album does not exist for user" + ID +":"); 
		//System.out.println(albumName);
		return false;
	}
	
	/**
	 * renames an album 
	 * @param current the name of the album to be renamed
	 * @param newName the new name that it is to be set to
	 * @return success or not
	 */
	public boolean renameAlbum(String current, String newName) {
		int check = checkAlbum(current);
		int check2 = checkAlbum(newName);
		if(check!=-1 && check2==-1){
			//found from the album and you would need to replace it. 
			
			totalData.renameAlbum(albums.get(check),newName,this);
			check = checkAlbum(current);
			if(check!=-1)
			albums.get(check).setName(newName);
			//System.out.println("Rename successful Album: "+ current+ " changed to: "+newName);
			return true;
		}
		
		//System.out.println("Error: in renaming of Album, could not find the album");
		return false;
	}
	
	
	/**
	 * lists the albums that belong to the user
	 */
	public void listAlbums(){
		if(albums.isEmpty()==false){
		
				
			System.out.println("Albums for user " + ID + ":");
			for(int i =0 ; i <albums.size(); i ++){
				if(albums.get(i) != null ){
				if(albums.get(i).getPhotos().isEmpty() != true){
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					Date dat = albums.get(i).startdate.getTime();
					String dat1 = sdf.format(dat);
					dat = albums.get(i).enddate.getTime();
					String dat2 = sdf.format(dat);
					//System.out.println(albums.get(i).getName() + " number of photos: " + albums.get(i).getPhotos().size() + ", " + albums.get(i).startdate + " - " + albums.get(i).enddate);
					System.out.println(albums.get(i).getName() + " number of photos: " + albums.get(i).getPhotos().size() + ", " + dat1 + " - " + dat2  );

				}else
					System.out.println(albums.get(i).getName() + " number of photos: " + albums.get(i).getPhotos().size());
			}
			}
		}
		else{ // no albums for the user
			System.out.println("No albums exist for user " + ID);
		}
	}
	
	/**
	 * @param filename
	 * @param caption
	 * @param albumName
	 * @return boolean
	 * * */
	
	public boolean addPhoto(String filename, String caption, String albumName){
	//check if the caption is  empty or not.
		
	if(filename!=null && caption!=null && albumName!=null){	
		caption = caption.trim();
		//there is something in it. 
			int check = checkAlbum(albumName);
			//album found?
			if(check !=-1){
				//album found
				//find the filename if it exists
				int check2 = albums.get(check).checkPhotos(filename);
				if(check2==-1){
					//filename not found, so add a new one
					//check if file actually exists or not
					filename = filename.trim();
					filename = totalData.filepass(filename);
					if(filename==null)
						return false;
					File file = new File(filename);
					//if file exists of not
					if(file.exists() && file.isDirectory()!=true){
						//add the photo in, see if the caption is empty or not
						if(caption.equals("")==true){
							//find the photo and get the caption from totalData
							int index = totalData.checkPhotoIndex(filename);
							if(index==-1){
								//there was no photo found
								//.out.println("File "+ filename + " caption not found");
								return false;
							}
							else{
								//file is found
								caption = totalData.photos.get(index).getCaption().substring(0, totalData.photos.get(index).getCaption().length());
								//create new photo object
								}
						}
						
							//caption is there, just normal add
						
							albums.get(check).addPhotos(filename, caption,1);
											
						return true;
						
					}
					else{
						//its not, throw an error
						//System.out.println("Error: File " +filename+" does not exist");
						return false;
					}
				}
				else{
					//filename already exists
					//System.out.println("Photo " + filename+ " already exists in album " +albumName);
					return false;
				}
			}
			else{
				//album not found
				//System.out.println("Error: Album "+ albumName + " does not exist");
				return false;
			}
		}
		return false;
	}
	
	/**
	 * @param filename
	 * @param oldAlbumName
	 * @param newAlbumName
	 * moves photo from old ablum to new album
	 * */
	
	
	public boolean movePhoto(String filename, String oldAlbumName, String newAlbumName){
			
			if(filename!=null && oldAlbumName!=null && newAlbumName!=null){
				//find the oldAlbumname
				oldAlbumName = oldAlbumName.trim();
				newAlbumName = newAlbumName.trim();
				filename = filename.trim();
				
				
				int check = checkAlbum(oldAlbumName);
				int check2 = checkAlbum(newAlbumName);
				if(check!=-1 && check2!=-1){
					
					//they both exists
					//check if there is the filename in the oldalbum
					filename = totalData.filepass(filename);
					if(filename==null)
						return false;
					//System.out.println(filename);
					int file = albums.get(check).checkPhotos(filename);
					if(file!=-1){
						//so the file does exists
						//store the old photos data
						//System.out.println("here");
						String caption = albums.get(check).getPhotos().get(file).getCaption();
						
						ArrayList<Tag> tags = albums.get(check).getPhotos().get(file).getTags();

						
						//add the photo
						 Photo ret = albums.get(check2).addPhotos(filename, caption,0);
						 
						 if(ret == null){
							 //there was an error
							 //System.out.println(oldAlbumName + " " + newAlbumName);
							 return false;
						 }
						//remove the photo first 
						albums.get(check).deletePhotos(filename,0);
						//System.out.println("after this check");	
						 file = albums.get(check2).checkPhotos(filename);
						if(file!=-1){
							//add all the tags and arraylist
							albums.get(check2).getPhotos().get(file).setTags(tags);
							if(totalData.checkPhotoIndex(filename)!=-1)
							albums.get(check2).getPhotos().get(file).setAlbums(totalData.photos.get(totalData.checkPhotoIndex(filename)).getAlbums());
						}
						//System.out.println("Moved photo " + filename + " :"); 
						//System.out.println(filename + " - From album " + oldAlbumName + " to album " +newAlbumName);
						return true;
					
					}
					
					else{ 
						//file does not exists
						//System.out.println("Error: Photo "+filename +" is not in album "+oldAlbumName);
						return false;
					}
				}
				else{
					//System.out.println("Error: Album Name does not exists");
					return false;
				}
			}
			return false;
	}

	/**
	 * @param filename
	 * @param albumName
	 * @return boolean
	 *removes photo from album
	 * */
	
	public boolean removePhoto(String filename, String albumName){
		if(filename!=null && albumName!=null){
			filename = filename.trim();
			albumName = albumName.trim();
			String temp = filename;
			//find the album
			int check = checkAlbum(albumName);
			if(check!=-1){
				//album exists
				filename = totalData.filepass(filename);
				int index = totalData.albums.get(check).checkPhotos(temp);
				if(filename==null && index==-1)
						return false;
					
				if(index!=-1 && filename==null)
					filename = temp;
				albums.get(check).deletePhotos(filename,1);
				return true;
			}
			else{
				//album does not exist
				//System.out.println("Error: Album " + albumName + " does not exists");
				return false;
			}
			
		}
		return false;
	}
	
	private ArrayList<Photo> mergelist(ArrayList<Photo> finallist, ArrayList<Photo> temp){
		if(finallist==null || temp==null)
			return new ArrayList<Photo>();
		if(finallist.isEmpty())
			return temp;
		else if(temp.isEmpty())
			return finallist;
		
		//merge them
		for(int i =0; i < temp.size(); i++){
			int j;
			for(j =0; j<finallist.size(); j++){
				if(temp.get(i).getFilename().equals(finallist.get(j).getFilename())){
					//same file
					break;}
			}
			if(j == finallist.size()){
				//end of the list
				finallist.add(temp.get(i));
			}
		}
		return finallist;
	}
	
	
	private void printPhotos(ArrayList<Photo> temp){
		
		for(int j = 0; temp.isEmpty()==false && j<temp.size(); j ++){
			System.out.print(temp.get(j).getCaption() + " - ");
			for(int i = 0; temp.get(j).getAlbums().isEmpty()==false && i<temp.get(j).getAlbums().size(); i ++){
				if(i==0)
				System.out.print("Album: " + albums.get(i).getName());
				else
				System.out.print( "[," + albums.get(i).getName() + "]");
			
		}
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date dat = temp.get(j).getTimestamp().getTime();
			String dat1 = sdf.format(dat);
			System.out.print(" - Date:"+dat1);
			System.out.println();
		}
	}
	
	private ArrayList<Photo> deleteDuplicate(ArrayList<Photo> temp){
		ArrayList<Photo> finalist = new ArrayList<Photo>();
		for(int i =0; temp.isEmpty()!=true && i<temp.size();i++){
			//now search through the finallist
			int j =0;
			for(j=0; finalist.isEmpty()!=true && j < finalist.size(); j++){
				
				if(temp.get(i).getFilename().equals(finalist.get(j).getFilename())){
					//dont add 
					break;
				}
			}
			if(j==finalist.size()){
				finalist.add(temp.get(i));
			}
		}
		return finalist;
		
	}
	
	/**
	 * @param startdate
	 * @param enddate
	 * @return ArrayList<Photo> 
	 * get photos according to that date
	 * */
	
	public ArrayList<Photo> getPhotosByDate(Calendar startdate, Calendar enddate){
		ArrayList<Photo> finallist = new ArrayList<Photo>();
		ArrayList<Photo> temp = new ArrayList<Photo>();
		
		if(albums.isEmpty()==true || totalData.photos.isEmpty()==true){
			//System.out.println("Error: No photos added for this user");
			return finallist;
		}
			
		
		if(startdate!=null && enddate!=null){
			//now start by the start date till the enddate
			//it needs to be greater than or equal to start date
			// and less than equal to enddate
			
			for(int i =0 ; albums.isEmpty()!=true && i<albums.size(); i++){
				
				temp = albums.get(i).photoslistbydate(startdate, enddate);
				finallist = mergelist(finallist,temp);
								
			}
			
			//sort now and then send it to put it in order
			if(finallist.isEmpty()!=true){
				CalendarDateWithoutTimeComparator c = new CalendarDateWithoutTimeComparator();
				Collections.sort(finallist,c);
				//this will sort by date
				}
			else{
				//System.out.println("Error: No photos were added in this timeframe");
				return finallist;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date dat = startdate.getTime(); 
			String dat1 = sdf.format(dat);
			dat = enddate.getTime();
			String dat2 = sdf.format(dat);
			//System.out.println("Photos for user " + ID + "in range " +dat1 + " to " + dat2 +" :"); 
			CalendarDateWithoutTimeComparator c = new CalendarDateWithoutTimeComparator();
			Collections.sort(finallist,c);
			//before printing, get rid of duplicates
			finallist = deleteDuplicate(finallist);
			//printPhotos(finallist);
			return finallist;
		
		}
		
		else{
			//System.out.println("Error: Passed argument is null");
			return null;
		}
	
	}
	
	
	
	
	
}
