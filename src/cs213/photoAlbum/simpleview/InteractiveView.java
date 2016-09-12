/**
 * @version 1.0
 */
package cs213.photoAlbum.simpleview;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import cs213.photoAlbum.control.Controller;
import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.User;

/**
 * The interactive view allows the user to interact with their photos and albums. 
 * @author Jasmine Feng and Risham Chokshi
 *
 */
public class InteractiveView {
	/**
	 * for one user
	 */
	private User user;
	
	/**
	 * the controller
	 */
	private Controller controller;
	
	/**
	 * the constructor
	 * @param user the logged in user
	 * @param controller the controller
	 */
	public InteractiveView(User user, Controller controller) {
		this.user = user;
		this.controller = controller;
	}
	
	/**
	 * runs all the stuff 
	 * from interactive view :)
	 * @return true if success, false if not
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public boolean run() throws IOException, ParseException {
		System.out.println("\n entering Interactive mode... \n");
		
		System.out.println("Hello " + user.name +":) \n");
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("What would you like to do?");
		String selection = "";
		//asks for input until user enters no input
		//TODO should this be changed to while input does not equal "quit"
		while(!selection.equals("quit")){
			
			selection = scanner.nextLine();
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
							//System.out.println(albumName);
							/*
							if(createAlbum(albumName)){
								System.out.println("created album for user " + user.getID() + ": " + user.getName());
							}else{
								System.out.println("album exists for user " + user.getID() + ": " + user.getName());
							}*/
							createAlbum(albumName);
							break;
						}			
					}
					System.out.println("Inproperly formatted input, please enter again");
					System.out.println("For a list of commands, please use the command help");
					break;
				case "deleteAlbum":
					if(tokens.length >= 2){
						String albumName = getSingleName(tokens);
						if(!albumName.equals("")){
							//System.out.println(albumName);
							if(deleteAlbum(albumName)){
								//System.out.println("deleted album from user " + user.getID() + ": " + user.getName());
							}else{
								//System.out.println("album does not exist for user " + user.getID() + ": " + user.getName());
							}
							break;
						}			
					}
					System.out.println("Inproperly formatted input, please enter again");
					System.out.println("For a list of commands, please use the command help");
					break;
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
						System.out.println("Inproperly formatted input, please enter again");
						System.out.println("For a list of commands, please use the command help");
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
						System.out.println("Incorrectly formatted inputs, please enter again.");
						break;
					}
					toke[1] = toke[1].replaceAll("\"", "");
					String[] pas = {tokens[1],toke[0],toke[1]};
					//for(int i =0; i<pas.length; i++){
						//System.out.println(pas[i]);
					//}
					//System.out.println(pas[0]);
					//System.out.println(pas[1]);
					//System.out.println(pas[2]);
					addTag(pas);
					break;
				case "deleteTag":
					deleteTag(tokens);
					break;
				case "listPhotoInfo":
					listPhotoInfo(tokens);
					break;
				case "renameAlbum":
					renameAlbum(tokens);
					break;
				case "recaption":
					recaption(tokens);
					break;
				case "getPhotosByDate":
					getPhotosByDate(tokens);
					break;
				case "getPhotosByTag":
					getPhotosByTag(tokens);
					break;
				case "help":
					help();
					break;
				case "quit":
					return true;
				case "logout":
					return true;
				default:
					System.out.println("Incorrectly formatted inputs, please enter again.");
					break;
			}
			//System.out.println("\nWhat would you like to do?");
			System.out.println("\n");
		}
		
		
		
		
		return false;
	}
	
	private void recaption(String[] tokens) {
		
		String input = getSingleName(tokens);
		String[] inputs = input.split("\" \"");
		if(inputs.length != 2){
			System.out.println("Inproperly formatted input, please enter again");
			System.out.println("For a list of commands, please use the command help");
			return;
		}
		removeQuotes(inputs);
		//System.out.println(inputs[0] + " | " + inputs[1]);
		controller.editCaption(inputs[0], inputs[1]);
	
		
	}

	private void renameAlbum(String[] tokens) {
		String input = getSingleName(tokens);
		//System.out.println(input);
		String[] inputs = input.split("\" \"");
		if(inputs.length != 2){
			System.out.println("Inproperly formatted input, please enter again");
			System.out.println("For a list of commands, please use the command help");
			return;
		}
		removeQuotes(inputs);
		controller.renameAlbum(inputs[0], inputs[1]);
		
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
			System.out.println("darn.....creating album");
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
			System.out.println("darn.....deleting album");
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
			System.out.println("darn.....listing albums");
			
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
			System.out.println("darn.....listing albums");
			
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
			System.out.println("Incorrectly formatted inputs, please enter again.");
			System.out.println("For a list of commands, please use the command help");
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
			System.out.println("Incorrectly formatted inputs, please enter again.");
			System.out.println("For a list of commands, please use the command help");
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
			System.out.println("Incorrectly formatted inputs, please enter again.");
			System.out.println("For a list of commands, please use the command help");
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
	private void addTag(String[] tokens){
		if(tokens.length != 3){
			System.out.println("Incorrectly formatted inputs, please enter again.");
			System.out.println("For a list of commands, please use the command help");
			return;
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
		
		controller.addTag(tokens[0], tokens[1], tokens[2]);
		
	}
	
	/**
	 * deletes a tag
	 * @param tokens String[] of tokens
	 */
	private void deleteTag(String[] tokens){
		String wholeTag = "";
		for(int i=2; i<tokens.length; i++){ //start at 2 bc 1 is filename
			wholeTag = wholeTag + tokens[i] + " ";
		}

		String[] tok = wholeTag.split(":");
		removeQuotes(tok);
		
		if(tok.length != 2){
			System.out.println("Incorrectly formatted inputs, please enter again.");
			System.out.println("For a list of commands, please use the command help");
			return;
		}		
		
		for(int i=0; i<tok.length; i++){
			tok[i] = tok[i].trim();
			//System.out.println(tok[i]);
		}
		
		controller.deleteTag(tokens[1], tok[0], tok[1]);
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
			System.out.println("Incorrectly formatted inputs, please enter again.");
			System.out.println("For a list of commands, please use the command help");
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
	private void getPhotosByDate(String[] tokens) throws ParseException{
		if(tokens.length != 3){
			System.out.println("Incorrectly formatted inputs, please enter again.");
			System.out.println("For a list of commands, please use the command help");
			return;
		}
		controller.getPhotosByDate(tokens[1], tokens[2]);
	}
	
	/**
	 * displays photos by tag
	 * @param tokens the String[] tokens
	 */
	private void getPhotosByTag(String[] tokens){
		String search = "";
		
		for(int i=1; i<tokens.length; i++){
			search = search + tokens[i] + " ";
		}
		
		if(search.contains("\" \"")){
			System.out.println("Improperly formatted inputs, did you forget to put commas between tags?");
			System.out.println("Please enter help for a list of commands");
			return;
		}
		
		String[] tags = search.split("\", ");
		
		
		/*for(int i=0; i<tags.length; i++){
			System.out.println(tags[i]);
		}*/
		removeQuotes(tags);
		//System.out.println(".........");
	
			//System.out.println(tags[i-1]);
		

		controller.getPhotosByTag(tags, search);
	}
	
	/**
	 * displays the help menu/list of commands
	 */
	private void help(){
		System.out.println("\n========");
		System.out.println("Commands");
		System.out.println("========");
		System.out.println("createAlbum \"<albumName>\"");
		System.out.println("deleteAlbum \"<albumName>\"");
		System.out.println("listAlbums");
		System.out.println("renameAlbum \"<oldAlbumName>\" \"<newAlbumName>\"");
		System.out.println("listPhotos \"<albumName>\"");
		System.out.println("addPhoto \"<fileName>\" \"<caption>\" \"<albumName>\" ");
		System.out.println("movePhoto \"<fileName>\" \"<oldAlbumName>\" \"<newAlbumName>\" ");
		System.out.println("removePhoto \"<fileName>\" \"<albumName>\" ");
		System.out.println("editCaption \"<fileName>\" \"<newCaption>\" ");
		System.out.println("addTag \"<fileName>\" <tagType>:\"<tagValue>\" ");
		System.out.println("deleteTag \"<fileName>\" <tagType>:\"<tagValue>\" ");
		System.out.println("recaption \"<filename>\" \"<newCaption>\"");
		System.out.println("renameAlbum \"<oldAlbumName>\" \"<newAlbumName>\"");
		System.out.println("listPhotoInfo \"<fileName>\"");
		System.out.println("getPhotosByDate <start date> <end date> ");
		System.out.println("getPhotosByTag <tagType>:\"<tagValue>\", <tagType>:\"<tagValue>\"... ");
		System.out.println("logout");
		
	}
}
