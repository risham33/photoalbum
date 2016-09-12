/**
 * @version 1.0
 */
package cs213.photoAlbum.gui.view;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import cs213.photoAlbum.control.Controller;
import cs213.photoAlbum.control.ProgramControl;
import cs213.photoAlbum.model.User;

/**
 * interactive view which connects both
 * @author Jasmine Feng and Risham Chokshi
 */
public class interactiveView {
	/**
	 * for one user
	 */
	public User user;
	
	/**
	 * the controller
	 */
	private ProgramControl controller;
	
	/**
	 * the constructor
	 * @param user the logged in user
	 * @param controller the controller
	 */
	public interactiveView(User user, ProgramControl controller) {
		this.user = user;
		this.controller = controller;
		this.controller.currentUser = user;
	}
	
	
	/**
	 * runs all the commands
	 * takes in a string and does all the parsing
	 * @param selection
	 * @return boolean
	 * */
	public boolean run(String selection) throws IOException, ParseException{
		
		
		String[] tokens = selection.split(" ");
		
		switch (tokens[0]){
			case "test":
				System.out.println("testing");
				for(int i=1; i<tokens.length; i++){
					System.out.println("" + tokens[i]);
				}
				break;
			case "createAlbum":
				if(tokens.length >= 2){
					String albumName = getSingleName(tokens);
					if(!albumName.equals("")){
						
						
						return createAlbum(albumName);
						
					}			
				}
				return false;
				
			case "deleteAlbum":
				if(tokens.length >= 2){
					String albumName = getSingleName(tokens);
					if(!albumName.equals("")){
						//System.out.println(albumName);
						if(deleteAlbum(albumName)){
							return true;
							//System.out.println("deleted album from user " + user.getID() + ": " + user.getName());
						}else{
							//System.out.println("album does not exist for user " + user.getID() + ": " + user.getName());
						}
						
					}			
				}
				return false;
				
			case "listAlbums":
				//System.out.println("Albums for user " + user.getID() + ": " + user.getName());
				listAlbums();
				break;
			case "listPhotos":
				if(tokens.length >= 2){
					String albumName = getSingleName(tokens);
					if(!albumName.equals("")){
						//System.out.println(albumName);
						
						//System.out.println("Photos for album " + albumName);
						listPhotos(albumName);
						break;
					}
				}	
				else{
					return false;
				}
				break;
			case "addPhoto":
				addPhoto(selection, tokens);
				break;
			case "movePhoto":
				movePhoto(tokens);
				break;
			case "removePhoto":
				removePhoto(tokens);
				break;
			case "addTag":
				//0 ->addtag
				//1 ->filename
				tokens[1] = tokens[1].replaceAll("\"", "");
				String con = "";
				for(int i =2; i < tokens.length; i++){
					con = con + tokens[i] + " ";
				}
				con = con.trim();
				//System.out.println(tokens[1] + " " + con);
				String[] toke = con.split(":");
				if(toke.length!=2){
					return false;
					
				}
				toke[1] = toke[1].replaceAll("\"", "");
				String[] pas = {tokens[1],toke[0],toke[1]};
				//for(int i =0; i<pas.length; i++){
					//System.out.println(pas[i]);
				//}
				//System.out.println(pas[0]);
				//System.out.println(pas[1]);
				//System.out.println(pas[2]);
				return addTag(pas);
				
			case "deleteTag":
				return deleteTag(tokens);
				
			case "listPhotoInfo":
				listPhotoInfo(tokens);
				break;
			case "renameAlbum":
				return renameAlbum(tokens);
			case "recaption":
				recaption(tokens);
				break;
			case "getPhotosByDate":
				getPhotosByDate(tokens);
				break;
			case "getPhotosByTag":
				getPhotosByTag(tokens);
				break;
			
			case "quit":
				return true;
			case "logout":
				return true;
			default:
				return false;
		
		
		
		
		
		
		
	}
		return false;
	}
	
private void recaption(String[] tokens) {
		
		String input = getSingleName(tokens);
		String[] inputs = input.split("\" \"");
		if(inputs.length != 2){
			//System.out.println("Inproperly formatted input, please enter again");
			//System.out.println("For a list of commands, please use the command help");
			return;
		}
		removeQuotes(inputs);
		//System.out.println(inputs[0] + " | " + inputs[1]);
		controller.editCaption(inputs[0], inputs[1]);
	
		
	}

	private boolean renameAlbum(String[] tokens) {
		String input = getSingleName(tokens);
		//System.out.println(input);
		String[] inputs = input.split("\" \"");
		if(inputs.length != 2){
			//System.out.println("Inproperly formatted input, please enter again");
			//System.out.println("For a list of commands, please use the command help");
			return false;
		}
		removeQuotes(inputs);
		return controller.renameAlbum(inputs[0], inputs[1]);
		
	}

	/**
	 * extracts one argument
	 * @param tokens the string array
	 * @return String
	 */
	private String getSingleName(String[] tokens){
		/*a remnant of hideous coding
		if(tokens[1].charAt(0)== '\"' && tokens[tokens.length-1].charAt(tokens[tokens.length-1].length()-1)=='\"'){
		*/
		String str = "";
		for(int i=1; i<tokens.length-1; i++){
			str = str + tokens[i] + " ";
		}
		str += tokens[tokens.length-1]; //fix for space issue


		//check if album name surrounded by ""
		if(str.charAt(0)=='\"' && str.charAt(str.length()-1)=='\"'){
			//System.out.println(str);
			str = str.substring(1, str.length()-1);
			//System.out.println(str);
			return str;
		}
		return "";
	}
	
	/**
	 * creates an album
	 * @param name the string name of album
	 * @return boolean
	 */
	private boolean createAlbum(String name){
		try{
			
			return controller.createAlbum(name);
			
		}catch (Exception e) {
			//System.out.println("darn.....creating album");
			return false;
		}
		
	}
	
	/**
	 * deletes an album
	 * @param name the string name of album
	 * @return boolean
	 */
	private boolean deleteAlbum(String name){
		try{
			return controller.deleteAlbum(name);
		}catch (Exception e) {
			//System.out.println("darn.....deleting album");
			return false;
		}
	}
	
	/**
	 * lists the albums
	 */
	private void listAlbums(){
		try{
			controller.listAlbums();
		}catch (Exception e) {
			//System.out.println("darn.....listing albums");
			
		}
	}
	
	/**
	 * lists the photos in an album
	 * @param albumName the album 
	 */
	private void listPhotos(String albumName){
		try{
			controller.listPhotos(albumName);
		}catch (Exception e) {
			//System.out.println("darn.....listing albums");
			
		}
	}
	
	/**
	 * removes quotes from a String[]
	 * @param tokens the String[] arguments
	 */
	private void removeQuotes(String[] tokens){
		for(int i=0; i<tokens.length; i++){
			tokens[i] = tokens[i].replaceAll("\"", "");
			//System.out.println(tokens[i]);
		}
	}
	
	/**
	 * extracts useful info from user input like filename albumname etc
	 * @param tokens the messed up tokens
	 * @return a String[] that holds usable info
	 */
	private String[] extractInfo(String[] tokens){
		String fullInfo = "";
		for(int i=1; i<tokens.length-1; i++){
			//System.out.println(tokens[i]);
			fullInfo = fullInfo + tokens[i] + " ";
		}
		fullInfo += tokens[tokens.length-1];
		//System.out.println(fullInfo);
		
		String[] info = fullInfo.split(" \"");
		removeQuotes(info);
		/*
		for(int i=0; i<info.length; i++){
			System.out.println(info[i]);
		}
		*/
		return info;
		
	}
	
	/**
	 * command is:
	 * addPhoto "<fileName>" "<caption>" "<albumName>" 
	 * @param selection the entire user input
	 * @param tokens the separated user input in an array
	 */
	private void addPhoto(String selection, String[] tokens){
		String[] info = extractInfo(tokens);
		
		//check for correct number of arguments
		if(info.length != 3){
			//System.out.println("Incorrectly formatted inputs, please enter again.");
			//System.out.println("For a list of commands, please use the command help");
		}
		else{ 
			controller.addPhoto(info[0], info[1], info[2]);
		}
		
	}
	/**
	 * movePhoto "<fileName>" "<oldAlbumName>" "<newAlbumName>" 
	 * @param tokens the String[] of tokens
	 * @return boolean
	 */
	private boolean movePhoto(String[] tokens){
		String[] info = extractInfo(tokens);
		
		//check for correct length of arguments
		if(info.length != 3){
			//System.out.println("Incorrectly formatted inputs, please enter again.");
			//System.out.println("For a list of commands, please use the command help");
			return false;
		}
		else{//TODO outprut for success?? emailed sesh
			controller.movePhoto(info[0], info[1], info[2]);
			return true;
			/*
			if(controller.movePhoto(info[0], info[1], info[2])){
				System.out.println("Moved photo " + info[0] + " - From album " + info[1] + " to album " + info[2]);
				return true;
			}
			else{
				System.out.println("Photo " + info[0] + "does not exist in "+ info[1]);
				return true;
			}*/
		}
		
	}
	
	/**
	 * removes a photo
	 * @param tokens the String[]
	 * @return boolean
	 */
	private boolean removePhoto(String[] tokens){
		String[] info = extractInfo(tokens);
		
		//check correct number of arguments
		if(info.length != 2){
			//System.out.println("Incorrectly formatted inputs, please enter again.");
			//System.out.println("For a list of commands, please use the command help");
			return false;
		}
		else{
			controller.removePhoto(info[0], info[1]);
			/*
			if(controller.removePhoto(info[0], info[1])){
				System.out.println("Removed photo: " + info[0] + " - From album " + info[1]);
				return true;
			}
			else{
				System.out.println("Photo " + info[0] + " does not exist in album " + info[1]);
				return true;
			}*/
			return true;
		}
	}
	
	/**
	 * adds a tag
	 * @param tokens String[] of tokens
	 */
	private boolean addTag(String[] tokens){
		if(tokens.length != 3){
			//System.out.println("Incorrectly formatted inputs, please enter again.");
			//System.out.println("For a list of commands, please use the command help");
			return false;
		}
		/*
		System.out.println(tokens[1]);
		String filename = tokens[1].replaceAll("\"", "");
		System.out.println(tokens[1] + " " + tokens[2]);
		String fullTag = tokens[2].replaceAll("\"", "");
		System.out.println(tokens[2]);
		*/
		//removeQuotes(tokens);
		//String[] tag = tokens[2].split(":");
		
		return controller.addTag(tokens[0], tokens[1], tokens[2]);
		
	}
	
	/**
	 * deletes a tag
	 * @param tokens String[] of tokens
	 */
	private boolean deleteTag(String[] tokens){
		String wholeTag = "";
		for(int i=2; i<tokens.length; i++){ //start at 2 bc 1 is filename
			wholeTag = wholeTag + tokens[i] + " ";
		}

		String[] tok = wholeTag.split(":");
		removeQuotes(tok);
		
		if(tok.length != 2){
			//System.out.println("Incorrectly formatted inputs, please enter again.");
			//System.out.println("For a list of commands, please use the command help");
			return false;
		}		
		
		for(int i=0; i<tok.length; i++){
			tok[i] = tok[i].trim();
			//System.out.println(tok[i]);
		}
		
		return controller.deleteTag(tokens[1], tok[0], tok[1]);
	}
	/**
		Output: 
		Photo file name: <fileName> 
		Album: <albumName>[,<albumName>]... 
		Date: <date> 
		Caption: <caption> 
		Tags: 
		<tagType>:<tagValue> 
		... 
		(grouped by tag type, in the order location first, then people, and then others, sorted by tag value within each type) 
		Or: 
		Photo <fileName> does not exist
		@param tokens the string[] tokens
	*/
	private void listPhotoInfo(String[] tokens){
		//controller.findPhoto(filename);
		if(tokens.length != 2){
			//System.out.println("Incorrectly formatted inputs, please enter again.");
			//System.out.println("For a list of commands, please use the command help");
			return;
		}
		removeQuotes(tokens);
		controller.listPhotoInfo(tokens[1], user.getID());
	}
	/**
	 * displays photos within a date range in chronological order
	 * @param tokens the string[] tokens
	 * @throws ParseException
	 */
	public ArrayList<cs213.photoAlbum.model.Photo> getPhotosByDate(String[] tokens) throws ParseException{
		if(tokens.length != 3){
			//System.out.println("Incorrectly formatted inputs, please enter again.");
			//System.out.println("For a list of commands, please use the command help");
			return null;
		}
		
		return controller.getPhotosByDate(tokens[1], tokens[2]);
	}
	
	/**
	 * displays photos by tag
	 * @param tokens the String[] tokens
	 */
	public ArrayList<cs213.photoAlbum.model.Photo> getPhotosByTag(String[] tokens){
		String search = "";
		
		for(int i=1; i<tokens.length; i++){
			search = search + tokens[i] + " ";
		}
		
		if(search.contains("\" \"")){
			
			//System.out.println("Improperly formatted inputs, did you forget to put commas between tags?");
			//System.out.println("Please enter help for a list of commands");
			return null;
		}
		
		String[] tags = search.split("\", ");
		
		
		/*for(int i=0; i<tags.length; i++){
			System.out.println(tags[i]);
		}*/
		removeQuotes(tags);
		//System.out.println(".........");
	
			//System.out.println(tags[i-1]);
		
		//System.out.println(tags[0] + " " + search);
		return controller.getPhotosByTag(tags, search);
	}
	
	
}
