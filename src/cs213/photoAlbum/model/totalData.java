/**
 * @version 1.0
 */

package cs213.photoAlbum.model;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * this is the class for all the data to be stored
 * @author Jasmine Feng and Risham Chokshi
 */
public class totalData {
	private static final long serialVersionUID = 7526472295622776147L;
	/*
	 * for all users static variables to store all the data *
	 */
	public static ArrayList<User> users = new ArrayList<User>();
	/*
	 * for all albums static variables to store all the data *
	 */
	public static ArrayList<Album> albums = new ArrayList<Album>();
	/*
	 * for all photos static variables to store all the data 
	 * *
	 */
	public static ArrayList<Photo> photos = new ArrayList<Photo>();
	/*
	 * for all tags static variables to store all the data *
	 */
	public static ArrayList<Tag> tags = new ArrayList<Tag>();
	
	public static int windowsIndex;

	
	public static String filepass(String filename){
		
		filename = filename.trim();
		File file = new File(filename);
		//create a photo object
		//if file exists of not
		if(file.exists() && file.isDirectory()!=true){
		//then you dont need to do anything and return
			try {
				filename = file.getCanonicalPath();
				return filename;
			} catch (IOException e) {
				//System.out.println("Cannot get CanonicalPath");
				return null;
			}
		
		}
		//try and see if it opens
		
		String path = System.getProperty("user.dir");
		//path = path.substring(0, path.length()-4);
		
		file = new File(path+"\\data\\images\\"+filename);
		//System.out.println(path+"\\images\\"+filename);
		if(file.exists() && file.isDirectory()!=true){
			//then you dont need to do anything and return
				try {
					filename = file.getCanonicalPath();
					return filename;
				} catch (IOException e) {
					//System.out.println("Cannot get CanonicalPath");
					return null;
				}
			
			}
		//System.out.println("Error: File " + filename + " does not exist");
		return null;		
		
	}
	
	public static boolean editCaption(String photo, String caption, String ID){
		if(caption==null){
			//System.out.println("Caption cannot be null");
			return false;
			}
		caption = caption.trim();
		if(caption.equals("")){
			//System.out.println("Caption cannot be empty");
			return false;
		}
		if(photo!=null){
		
			photo = filepass(photo);
			if(photo==null)
				return false;
			//find the photo
			int ch = checkPhotoIndex(photo);
			if(ch!=-1){
			Photo phot = photos.get(ch);
			//we need to check for album
				for(int i =0; phot.getAlbums().isEmpty()!=true && i < phot.getAlbums().size(); i++){
					if(phot.getAlbums().get(i).getUser().getID().equals(ID)){
						//change the caption
						if(phot.getCaption().equals(caption)){
							//System.out.println("Error: Photo " + photo +" already has " + caption + " as its caption");
							return false;
						}
						phot.editCaption(caption);
						int c = checkPhotoIndex(phot.getFilename());
						if(c!=-1)
							photos.get(c).setCaption(caption);
						phot.getAlbums().get(i).editcaption(photo,caption);
						//System.out.println("Photo " + photo + " caption changed to " + caption);
						return true;
					}
				}
				//System.out.println("filename " + photo + " does not exist for this user: " + ID);
				return false;
			}
			else{
				//System.out.println("Error: filename " + photo + " does not exist");
				return false;
			}
		}
		else{
		return false;
	}
	}
	/**
	 * @param photo for the photo passed in
	 * @param album for the album of the photo
	 * adds the photo
	 * 
	 * */
	
	public static void addPhoto(Photo photo, Album album) {

		if (photos == null || photos.isEmpty() == true && photo != null) {
			photos.add(photo);

		}

		else if (photo != null) {

			// to check if photo already exists
			Photo p = checkPhoto(photo);

			if (p != null) {
				// they are the same
				// check if the album already exists in photo
				int j;
				for (j = 0; p.getAlbums().isEmpty() != true&& j < p.getAlbums().size(); j++) {

					if (p.getAlbums().get(j).getName().equals(album.getName())&& p.getAlbums().get(j).getUser().getID() == album.getUser().getID()) {
						// the album exists in the photo no need to add
						break;

					}
				}
				// album does not exist. so add album
				if (j == p.getAlbums().size()) {
					p.getAlbums().add(album);
				}
			}
			// if photo doesnt exist
			else {
				photos.add(photo);
			}
		}

		// albums start
		if (albums.isEmpty() == true) {
			albums.add(album);
		} else {
			// albums has something
			if (checkAlbum(album) == null) {
				// that means it does not exist
				// add the album to the list
				albums.add(album);
				return;
			} else {
				// it does exist, now check if the photo exists, if yes then
				// return or else add

				Album a = checkAlbum(album);

				for (int i = 0; a.getPhotos().isEmpty() != true
						&& i < a.getPhotos().size(); i++) {
					// check the photos
					if (a.getPhotos().get(i).getFilename().equals(photo.getFilename()))
						return; // it exists

				}
				// it does not then add it
				a.getPhotos().add(photo);

				return;
			}

		}

	}
	
	/**
	 * @param p for the photo passed in
	 * @return photo is returned
	 * finds the photo
	 * 
	 * */

	public static Photo checkPhoto(Photo p) {
		for (int i = 0; photos != null && photos.isEmpty() == false
				&& i < photos.size(); i++) {

			if (photos.get(i).getFilename().equals(p.getFilename())) {
				return photos.get(i);

			}
		}

		return null;

	}
	
	/**
	 * @param a passed in
	 * @return the album
	 * finds the album
	 * 
	 * */
	
	
	public static Album checkAlbum(Album a) {
		if (a != null && albums != null) {
			for (int i = 0; albums.isEmpty() == false && i < albums.size(); i++) {

				if (albums.get(i).getName().equals(a.getName())
						&& albums.get(i).getUser().getID() == a.getUser()
								.getID()) {
					return albums.get(i);
				}
			}
		}
		return null;

	}
	
	/**
	 * 
	 * @param a for the album of the photo
	 * @return the int of the index where the album is
	 * 
	 * */
	
	
	public static int checkAlbumIndex(Album a) {

		for (int i = 0; albums.isEmpty() == false && i < albums.size(); i++) {

			if (albums.get(i).getName().equals(a.getName())
					&& albums.get(i).getUser().getID() == a.getUser().getID()) {
				return i;
			}
		}

		return -1;

	}
	
	/**
	 * @param p for the photo passed in
	 * @return the integer of the index
	 *  
	 * */
	
	
	public static int checkPhotoIndex(Photo p) {
		for (int i = 0; photos.isEmpty() == false && i < photos.size(); i++) {

			if (photos.get(i).getFilename().equals(p.getFilename())) {
				return i;

			}
		}

		return -1;

	}

	
	/**
	 * @param p name for the photo passed in
	 * @return the integer of the index
	 *  
	 * */
	
	public static int checkPhotoIndex(String p) {
		for (int i = 0; photos.isEmpty() == false && i < photos.size(); i++) {
			//System.out.println(photos.get(i).getFilename());
			if (photos.get(i).getFilename().equals(p)) {
				return i;

			}
		}

		return -1;

	}

	
	/**
	 * @param photo for the photo passed in
	 * @param album in photo
	 *   
	 * */
	
	public static void deletePhoto(Photo photo, Album album) {
		Photo p = checkPhoto(photo);
		if (p == null) {
			// phto not found

		} else {
			// photo found, delete that album name
			if (p.getAlbums().isEmpty() == true || (p.getAlbums().size() < 2 && p.getAlbums().get(0).getName().equals(album.getName()) && p.getAlbums().get(0).getUser().getID() == album.getUser().getID())) {
				// delete photo with the album
				if (p.getAlbums().isEmpty() != true) {
					// there is something
					p.getAlbums().remove(0);
				}

			}
			// now there is more than one or not empty
			else if (p.getAlbums().size() > 1) {
				// check if the album exists
				int i;
				for (i = 0; i < p.getAlbums().size(); i++) {
					if (p.getAlbums().get(i).getName().equals(album.getName()) && p.getAlbums().get(i).getUser().getID() == album.getUser().getID()) {
						// album found
						p.getAlbums().remove(i);
						break;
					}
				}

				if (i == p.getAlbums().size()) {
					// not found
					// no need to delete that album
				}

			}
		}

		// delete photo from the ablum list
		// now go into the album list
		Album a = checkAlbum(album);
		if (a != null) {
			// there is an album, now check if there is a photo that needs to be
			// delete
			for (int i = 0; a.getPhotos().isEmpty() == false
					&& i < a.getPhotos().size(); i++) {

				if (a.getPhotos().get(i).getFilename()
						.equals(photo.getFilename())) {
					// has the photo, delete it
					a.getPhotos().remove(i);
					break;
				}
			}

		} else;
		
		//now delete the photos from the tags list
		
		for(int i =0; tags.isEmpty()!=true && photo.getTags().isEmpty()!=true && i<photo.getTags().size(); i ++){
			//check all the tags and delete them from the previous list
			int check = checkTagIndex(photo.getTags().get(i));
			if(check!=-1){
				//the tag was found
				//delete the photo
				for(int j =0; tags.get(check).getPhotos().isEmpty()!=true && j<tags.get(check).getPhotos().size(); j++){
					
					if(tags.get(check).getPhotos().get(j).getFilename().equals(photo.getFilename())){
						//same file, delete it
						tags.get(check).getPhotos().remove(j);
						break;
					}
				}
			}
		}
		
		
	}

	
	private static int checkUserIndex(User user) {

		for (int i = 0; users != null && users.isEmpty() == false
				&& i < users.size(); i++) {
			if (users.get(i).getID() == user.getID()
					&& users.get(i).getName().equals(user.getName()))
				return i;
		}

		return -1;
	}
	
	/**
	 * @param album which needs to be added
	 * @param user which needs to be added
	 * added the album
	 *  
	 * */
	public static void addAlbum(Album album, User user) {
		if (checkAlbum(album) == null) {
			// add album, it is not found
			album.setUser(user);
			albums.add(album); // album added
		}
		// then album is already in the list
		// add album in user
		int check = checkUserIndex(user);
		if (check == -1) {
			// there is no user in that list with that name
			users.add(user);
		}
		check = checkUserIndex(user);

		// the user exist, add the album if it doesnt exist

		int i = 0;

		for (i = 0; users.get(check).getAlbums().isEmpty() == false && i < users.get(check).getAlbums().size(); i++) {
			if (album.getName().equals(users.get(check).getAlbums().get(i).getName()))
				return; // there is already a name
		}

		// there is not that album in that user
		users.get(check).getAlbums().add(album);

	}
	
	/**
	 * @param album which needs to be delete
	 * @param user which needs to be delete
	 * album which needs to be deleted
	 *  
	 * */
	
	public static void deleteAlbum(Album album, User user) {

		int check = checkAlbumIndex(album);
		if (check != -1) {
			// delete the album
			while (albums.get(check).getPhotos().isEmpty() == false) {
				// delete the photos
				albums.get(check).getPhotos().remove(0);
			}
			albums.remove(check);
		}

		// delete from the users
		check = checkUserIndex(user);
		if (check != -1) {
			// user was found
			// delete the album
			for (int i = 0; users != null
					&& users.get(check).getAlbums().isEmpty() == false
					&& i < users.get(check).getAlbums().size(); i++) {
				if (users.get(check).getAlbums().get(i).getName()
						.equals(album.getName())) {
					// album exist
					users.get(check).getAlbums().remove(i);
					break;
				}
			}
		}
	}
	
	/**
	 * @param current which needs to be added
	 * @param newName of the album
	 * @param user of the user
	 *  
	 * */
	public static void renameAlbum(Album current, String newName, User user) {
		int check = checkAlbumIndex(current);

		if (check != -1 && albums.get(check).getUser().getID().equals(user.getID())) {
			// found from the album and you would need to replace it.
			albums.get(check).setName(newName);

		}
		int ucheck = checkUserIndex(user);
		if (ucheck != -1) {
			for (int i = 0; users != null && users.get(ucheck).getAlbums().isEmpty() == false && i < users.get(ucheck).getAlbums().size(); i++) {
				if (users.get(ucheck).getAlbums().get(i).getName().equals(current.getName()) && users.get(ucheck).getAlbums().get(i).getUser().getID().equals(user.getID())) {
					// album exist
					users.get(ucheck).getAlbums().get(i).setName(newName);
					break;
				}
			}
		}
	}

	/**
	 * @param tag which needs to be found
	 * @return int of the index 
	 *  
	 * */
	public static int checkTagIndex(Tag tag) {
		for (int i = 0; tag != null && tags.isEmpty() != true&& i < tags.size(); i++) {
			
			if (tags.get(i).getName().equalsIgnoreCase("location") && tags.get(i).getDescription().equalsIgnoreCase(tag.getDescription())&& tags.get(i).getName().equalsIgnoreCase(tag.getName())) {
				return i;
			}
			else if (tags.get(i).getName().equalsIgnoreCase("person") && tags.get(i).getDescription().equalsIgnoreCase(tag.getDescription())&& tags.get(i).getName().equalsIgnoreCase(tag.getName())) {
				return i;
			}
			else if (tags.get(i).getDescription().equals(tag.getDescription())&& tags.get(i).getName().equals(tag.getName())) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @param filename which needs to be found
	 * @param tagName which needs to be added
	 * @param tagValue which needs to be added
	 * @return int for error or other things 
	 *  
	 * */
	public static int addTag(String filename, String tagName, String tagValue) {
		// add it to the tag list and the list of all the photos
		if (filename != null) {
			// just trimed it
			filename = filename.trim();

			if (filename.equals("") != true) {
				filename = filepass(filename);
				if(filename==null)
					return -1;
				int ie = checkPhotoIndex(filename);
				//System.out.println("in total data");
				if (ie != -1) {
					Photo photo = photos.get(ie);
					//System.out.println("found photo");
					if (tagName != null && tagValue != null) {
						tagName = tagName.trim();
						tagValue = tagValue.trim();
						//System.out.println("This is " + tagName + tagValue);
						if (tagName.isEmpty()!=true) {
							Tag tag = new Tag(tagName, tagValue);
							//System.out.println(tagName + " " + tagValue);
							if (tags.isEmpty()) {
								
								// then just add the tag
								tags.add(tag);
								tags.get(0).addPhoto(photo);
								if(photo.addTag(tags.get(0))){
									//then the tag is added
									return 0 ;
								}
								else
									return -1;
							}

							// check for the tag
							else{
								//System.out.println("i m here3: " + tag.getName() + " " + tag.getDescription());
							int ch = checkTagIndex(tag);
							//tag does not exist
							if (ch == -1) {
								// add the tag
								//check if the tag is a location or not
								//if the photo doesnt have it already
								if(photo.addTag(tag)){
								tags.add(tag);
								ch = checkTagIndex(tag);
								if (ch != -1) {
									tags.get(ch).addPhoto(photo);
									return 0;
								}
								}
								else
									return -1;
								}
							//System.out.println("i m here4: " + tag.getName() + " " + tag.getDescription());
							//tag already exists
							// find the photo in the tag
							ch = checkTagIndex(tag);
							
							if (ch != -1){
								//if photo does exist then add photo
								//System.out.println("i m here5: " + tags.get(ch).getName() + " " + tag.getDescription());
								int t;
								for(t = 0; tags.get(ch).getPhotos().isEmpty()!=true && t<tags.get(ch).getPhotos().size(); t++){
									if(tags.get(ch).getPhotos().get(t).getFilename().equals(photo.getFilename()))
										break;
								}
								tags.get(ch).setName(tag.getName()); tags.get(ch).setDescription(tag.getDescription());
								if(t==tags.get(ch).getPhotos().size()){
									//no photos add it
									tags.get(ch).addPhoto(photo);
								}
								//System.out.println("i m here7: " + tags.get(ch).getName() + " " + tags.get(ch).getDescription());
								if(photo.addTag(tags.get(ch))){
									//then the tag is added
									return 0 ;
								}
								else{
									//System.out.println("i m here7: " + tag.getName() + " " + tag.getDescription());
									return -1;
								}
								
							}		
							// check if the tag exists, if not then add
							// it
							}
						} else {
							//System.out.println("Error: tagName needs to be filled");
							return -1;
						}

					} else {
						//System.out.println("Error: tagName and tagValue cannot be null");
						return -1;
					}
				}
			} // filename not found
		}
		else{
		//System.out.println("Photo " + filename + " does not exist");
		}
		return -1;
	}
	
	/**
	 * @param filename which needs to be found
	 * @param tagName which needs to be added
	 * @param tagValue which needs to be added
	 * @return int
	 * */
	public static int deleteTag(String filename, String tagname, String tagValue) {
		// find the photo


		//System.out.println(filename);
		if (filename != null && tagname != null && tagValue != null) {
			filename = filename.trim();
			tagname = tagname.trim();
			tagValue = tagValue.trim();
			
			if(filename.length()>2){
				filename = filename.replaceAll("\"", "");
			}
			
			filename = filepass(filename);
			if(filename==null)return -1;
			int check = checkPhotoIndex(filename);
			if (check != -1) {
				// now we need to delete from the photo and from the tag
				if (photos.get(check).deleteTag(tagname, tagValue)) {
					// it deleted and if it did then it should delete it from
					// the tags list
					Tag temp = new Tag(tagname, tagValue);
					int che = checkTagIndex(temp);
					if (che != -1) {
						// find photo in there and remove it from the lsit
						for (int i = 0; tags.isEmpty() != true
								&& tags.get(che).getPhotos().isEmpty() != true
								&& i < tags.get(che).getPhotos().size(); i++) {
							// find the photo and then delete it
							if (tags.get(che).getPhotos().get(i).getFilename().equals(filename)) {
								// found the filename
								tags.get(che).getPhotos().remove(i);
								return 0;
							}
						}
					}
					
				}
				else
					return -1;
				return 0;
			} else {
				// the photo was not found and the error message should be given
				//System.out.println("Photo " + filename + " does not exist");
				return -1;
			}

		}

		else {
			//System.out.println("Error: Cannot have a null entry");
			return -1;
		}
	}
	
	/**
	 * @param filename which needs to be found
	 * @param ID of the user
	 * list photo info of the photo
	 * */
	public static void listPhotoInfo(String filename, String ID) {

		if (filename != null) {

			filename = filename.trim();
			if (filename.equals("") != true) {
				// there is something, find the filename
				filename = filepass(filename);
				if(filename==null)return;
				int ch = checkPhotoIndex(filename);
				if (ch != -1) {
					// file is found
					//System.out.println("here " + ID );
					photos.get(ch).listPhotoInfo(ID);
					return;
				} else {
					//System.out.println("Photo " + filename + " does not exist");
					return;
				}
			} else {
				//System.out.println("Error: Photo " + filename+ " does not exist, filename cannot be empty");
				return;
			}

		} else {
			// filename was null
			//System.out.println("Error: Filename given was null");
			return;
		}

	}
	
	private static int checkTagIndex(String tagname, String tagValue) {
		for (int i = 0; tagname != null && tagValue!=null && tags.isEmpty() != true && i < tags.size(); i++) {
			if (tags.get(i).getName().equalsIgnoreCase("location") && tags.get(i).getDescription().equalsIgnoreCase(tagValue)&& tags.get(i).getName().equalsIgnoreCase(tagname)) {
				return i;
			}
			else if (tags.get(i).getName().equalsIgnoreCase("person") && tags.get(i).getDescription().equalsIgnoreCase(tagValue)&& tags.get(i).getName().equalsIgnoreCase(tagname)) {
				return i;
			}
			else if (tags.get(i).getDescription().equals(tagValue)&& tags.get(i).getName().equals(tagname)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @param p of the arraylist of photo
	 * @param ID of the suer
	 * for the printing
	 * */
	public static void printPhoto(ArrayList<Photo> p, String ID){
		int first =0; //for photo
		int firstp = 0; 
	for(int i =0; p.isEmpty()!=true && i< p.size(); i++){
		Photo ptr = p.get(i);
		//now get the albums
		for(int j =0; ptr.getAlbums().isEmpty()!=true && j<ptr.getAlbums().size();j++){
		if(ptr.getAlbums().get(j).getUser().getID().equals(ID) && first==0 && firstp==0){
			//first time being printed
			
			firstp++;
			System.out.print(ptr.getCaption() + " - Album: " + ptr.getAlbums().get(j).getName());
			first++;
		}
		else if(ptr.getAlbums().get(j).getUser().getID().equals(ID) && first==0){
			System.out.print(ptr.getCaption() + " - Album: " + ptr.getAlbums().get(j).getName());
			first++;
		}
		else if(ptr.getAlbums().get(j).getUser().getID().equals(ID)){
			System.out.print("[,"+ptr.getAlbums().get(j).getName()+"]");
		}
	}
		if(first>0){ //there was something added to the photo
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date dat = ptr.getTimestamp().getTime();
			String dat1 = sdf.format(dat);
			System.out.print(" - Date:"+dat1);
			System.out.println();
			}
			first =0; //initialized
	}
	}
	
	
	private static ArrayList<Photo> finalist(ArrayList<Photo>temp, ArrayList<Photo> finallist){
		if(temp.isEmpty())
			return temp;
		if(finallist.isEmpty())
			return finallist;
		//now do the and function
		ArrayList<Photo> ret = new ArrayList<Photo>();
		for(int i =0; finallist.isEmpty()!=true && i<finallist.size(); i++){
			//every temp needs to be in th
			int j = 0;
			for(j =0 ; temp.isEmpty()!=true && j<temp.size();j++){
				//check for the object in temp if not then delete it
				if(finallist.get(i).getFilename().equals(temp.get(j).getFilename()))
					ret.add(finallist.get(i));
			}
			
			
		}
		return ret;
		
	}
	
	/**
	 * @param tag list
	 * @param searchString string of which was given
	 * @param ID would be of the user
	 * @return ArrayList<Photo>
	 *  getphotosbytag given
	 * */
	
	public static ArrayList<Photo> getPhotosByTag(ArrayList<Tag> tag, String searchString, String ID){
		
		ArrayList<Photo> temp = new ArrayList<Photo>(); //contains the temp from the list
		ArrayList<Photo> finallist = new ArrayList<Photo>(); //contains the final list of all the photos that needs to be printed
		
		if(tags.isEmpty()){
			return finallist;
		}
		
		for(int i =0; tag.isEmpty()!=true && i<tag.size(); i ++){
			//reading from the tag and reading in all the tags and passing it to the method
			//System.out.println("Name: " + tag.get(i).getName() + " tagValue: " + tag.get(i).getDescription());
			temp = PhotosByTag(tag.get(i).getName(),tag.get(i).getDescription(),ID); //the photo list given	
			
			//System.out.println("here");
			
			if(temp==null){
				//System.out.println("Error: temp No photos exists with these tags");
				return new ArrayList<Photo>(); //error
			}
			
			if(i==0){//first tag
			finallist = temp;
			}else{
			finallist = finalist(temp,finallist);
			}
			if(finallist.isEmpty()){
				//System.out.println("Error: finallist No photos exists with these tags");
				return new ArrayList<Photo>();
			}
		}
		
		//System.out.println("Photos for user "+ ID + " with tags " + searchString);
		CalendarDateWithoutTimeComparator c = new CalendarDateWithoutTimeComparator();
		Collections.sort(finallist,c);
		//printPhoto(finallist, ID);
		return finallist;
		
	}
	
	
	private static ArrayList<Photo> PhotosByTag(String tagName, String tagValue, String ID){
		//sort this data
		ArrayList<Photo> temp = new ArrayList<Photo>();
		if(tags.isEmpty()!=true){
			//sort it
			//System.out.println("tag not empty");
			tagSort n = new tagSort();
			Collections.sort(tags,n);
			//find the tags
			if(tagName==null || tagValue == null || ID == null){
				//System.out.println("Error: passing parameter cannot be null");
				return null;
			}
			tagName = tagName.trim(); tagValue = tagValue.trim();
						
			if(tagName.equals("")!=true){ // that means there is a name to it
			int check = checkTagIndex(tagName,tagValue);
			if(check==-1 || (check>=0 && tags.get(check).getPhotos().isEmpty())){
				//error
				//System.out.println("Error: This tag does not exist");
				return null;
			}
			//found the tag
			
			
			int first =0; //for photo
			int firstp = 0; //for putting the extra println at the top
			
			for(int i =0 ; tags.get(check).getPhotos().isEmpty()!=true && i<tags.get(check).getPhotos().size();i++){
				//go through photos, albums
				Photo ptr = tags.get(check).getPhotos().get(i);
				int add_once = 0;
				for(int j =0; ptr.getAlbums().isEmpty()!=true && j<ptr.getAlbums().size();j++){
						if(ptr.getAlbums().get(j).getUser().getID().equals(ID) && add_once==0){
							temp.add(ptr);
							add_once++;
						} 
						
				}
				
			}
			}
			else{
			//tagName is ""	
			//try and get all the tags
				if(tagValue.equals("")){
					//System.out.println("Error: TagName and TagValue both cannot be empty");
					return null;
				}
				//after knowsing that there is a value for tagValue
				ArrayList <Tag> tag = tagValueSearch(tagValue);
			for(int h =0; tag.isEmpty()!=true && h<tag.size(); h++){
				for(int i =0 ; tag.get(h).getPhotos().isEmpty()!=true && i<tag.get(h).getPhotos().size();i++){
					//go through photos, albums
					Photo ptr = tag.get(h).getPhotos().get(i);
					
					for(int j =0; ptr.getAlbums().isEmpty()!=true && j<ptr.getAlbums().size();j++){
							if(ptr.getAlbums().get(j).getUser().getID().equals(ID)){
								temp = addDistinct(ptr, temp);
							} 
							}
				
			}
			}
			}
		return temp;
	}
		//error tag is equal to empty
		//System.out.println("Error: There are no tags added");
		return null;
	}
	
	private static ArrayList<Tag> tagValueSearch(String tagValue){
		ArrayList <Tag> temp = new ArrayList<Tag>();
		for(int i =0; tags.isEmpty()!=true && i<tags.size(); i++){
			if(tags.get(i).getDescription().equals(tagValue))
				temp.add(tags.get(i));
		}
		return temp;
	}
	
	private static ArrayList<Photo> addDistinct(Photo p, ArrayList<Photo> temp){
		
		if(temp.isEmpty()){
			temp.add(p);
			return temp;
		}
		
		//gotta check now
		for(int i =0 ; p != null && temp.isEmpty()!=true && i<temp.size(); i++){
			
			if(p.getFilename().equals(temp.get(i))){
				return temp;
			}
		}
		if(p!=null)
		temp.add(p);
		return temp;
	}
	
}