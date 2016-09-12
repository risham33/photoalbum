/**
 * @version 1.0
 */
package cs213.photoAlbum.gui.view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.Tag;
import cs213.photoAlbum.model.tagSort;
import cs213.photoAlbum.model.totalData;

/**
 * the window that displays the photo details 
 * has add/delete tag and move photo functionality
 * can cycle through the photos in an album with next/previous buttons
 * @author Jasmine Feng and Risham Chokshi
 */
public class photo extends javax.swing.JFrame {
	    
	public static DefaultListModel<String> albumsList;
    public static javax.swing.JButton addTagButton;
    public static javax.swing.JTextField addTagText;
    //public static JTextField addTagValueText;
    //public static JLabel tagValueLabel;
    //public static JLabel tagTypeLabel;
    public static javax.swing.JButton albumButton;
    private javax.swing.JLabel ErrorMessage;
    public static javax.swing.JButton deleteTagButton;
    public static javax.swing.JTextField deleteTagText;
    public static javax.swing.JButton logoutButton;
    public static javax.swing.JPanel movePanel;
    public static javax.swing.JButton movePhotoButton;
    public static javax.swing.JScrollPane moveScroll;
    private javax.swing.JScrollPane jScrollPane1;
    public static JList moveAlbumJList;
    public static javax.swing.JButton nextButton;
    public static javax.swing.JButton photoButton;
    public static JTextArea photoDetail;
    public static javax.swing.JPanel photoPanel;
    public static javax.swing.JButton previousButton;
    public static JFrame Login; //the main login page
    public static JFrame PhotoList;//photo list of the album
    public static JFrame Album;
    public static Photo currentPhoto;
    public static cs213.photoAlbum.model.Album currentAlbum;
    public static ArrayList<cs213.photoAlbum.model.Photo> photos;
    public static int currentIndex;
    // End of variables declaration    
    /**
     * Creates new form photo
     */
    public photo(JFrame Login, JFrame Album, JFrame PhotoList, Photo currentPhoto, cs213.photoAlbum.model.Album currentAlbum, ArrayList<cs213.photoAlbum.model.Photo> photos) {
    	this.Login = Login;
    	this.Album = Album;
    	this.PhotoList = PhotoList;
    	this.currentPhoto = currentPhoto;
    	this.currentAlbum = currentAlbum;
    	this.photos = photos;
        initComponents();
    }

    @SuppressWarnings("unchecked")
   
    
    /**
     * called from the constructor to initialize the form.
     */
    private void initComponents() {

        photoPanel = new javax.swing.JPanel();
        photoDetail = new JTextArea(50, 10);
        photoButton = new javax.swing.JButton();
        albumButton = new javax.swing.JButton();
        movePanel = new javax.swing.JPanel();
        movePhotoButton = new javax.swing.JButton();
        //moveScroll = new javax.swing.JScrollPane();
        addTagButton = new javax.swing.JButton();
        deleteTagButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        addTagText = new javax.swing.JTextField();
        //addTagValueText = new JTextField();
        //tagValueLabel = new JLabel();
        //tagTypeLabel = new JLabel();
        deleteTagText = new javax.swing.JTextField();
        logoutButton = new javax.swing.JButton();
        ErrorMessage = new javax.swing.JLabel();
        albumsList = new DefaultListModel();
        jScrollPane1 = new javax.swing.JScrollPane();
        

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
            	
            	try {
					login.Controller.updateUserFile(totalData.users);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
                System.exit(0);
            }
        });
        setTitle("Photo");
        setResizable(false);
        
        ImageIcon image = new ImageIcon(currentPhoto.getFilename());
        if(image.getIconHeight()>500 || image.getIconWidth()>600)
        	image = scale(image.getImage());
        JLabel imageLabel = new JLabel(image);
       //photoPanel.add(imageLabel);

        photoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout photoPanelLayout = new javax.swing.GroupLayout(photoPanel);
        photoPanel.setLayout(photoPanelLayout);
        photoPanelLayout.setHorizontalGroup(
            photoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
            .addComponent(imageLabel)
        );
        photoPanelLayout.setVerticalGroup(
            photoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 287, Short.MAX_VALUE)
            .addComponent(imageLabel)
        );

        photoButton.setText("Back to Photo");

        albumButton.setText("Back to Albums");

        movePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        movePhotoButton.setText("Move Photo");
        
        ErrorMessage.setText("");
        if(currentAlbum!=null)
        	createAlbumsStringList();
        moveAlbumJList = new JList(albumsList);
        moveAlbumJList.setVisibleRowCount(-1);
        if(login.Controller.currentUser.getAlbums().isEmpty() != true){
        	moveAlbumJList.setSelectedIndex(0);
        }
        
        moveAlbumJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        moveScroll = new javax.swing.JScrollPane(moveAlbumJList);
        

        javax.swing.GroupLayout movePanelLayout = new javax.swing.GroupLayout(movePanel);
        movePanel.setLayout(movePanelLayout);
        movePanelLayout.setHorizontalGroup(
            movePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movePanelLayout.createSequentialGroup()
                .addGroup(movePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(movePanelLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(moveScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(movePanelLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(movePhotoButton)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        movePanelLayout.setVerticalGroup(
            movePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movePanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(movePhotoButton)
                .addGap(18, 18, 18)
                .addComponent(moveScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        addTagButton.setText("Add Tag");
       
        deleteTagButton.setText("Delete Tag");

        previousButton.setText("Previous");

        nextButton.setText("Next");

        logoutButton.setText("Logout");
        
        setPhotoDetailText();
        
        
        photoDetail.setColumns(20);
        photoDetail.setRows(5);
        jScrollPane1.setViewportView(photoDetail);
        photoDetail.setEditable(false);
        photoDetail.setLineWrap(true);
        photoDetail.setWrapStyleWord(true);
        photoDetail.setOpaque(false);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(photoButton)
                        .addGap(54, 54, 54)
                        .addComponent(albumButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(photoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 1, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(movePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(deleteTagButton)
                                            .addComponent(addTagButton))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(addTagText)
                                            .addComponent(deleteTagText)))
                                    .addComponent(ErrorMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(previousButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nextButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(logoutButton)))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(photoButton)
                        .addComponent(albumButton))
                    .addComponent(ErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(photoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addTagButton)
                            .addComponent(addTagText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteTagButton)
                            .addComponent(deleteTagText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(movePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(previousButton)
                            .addComponent(nextButton)
                            .addComponent(logoutButton))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );
        ErrorMessage.setText("");
        
        if(currentAlbum == null){
        	movePanel.setEnabled(false);
        	movePhotoButton.setEnabled(false);
        	moveScroll.setEnabled(false);
        	moveAlbumJList.setEnabled(false);
        }
        
        pack();
        setLocationRelativeTo(null);
        
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            	ErrorMessage.setText("");
            	if(photos.size() == 0){
            		PhotoList.setVisible(true);
            		nextButton.setEnabled(false);
            		previousButton.setEnabled(false);
                	setVisible(false);
                	photolist.ErrorMessage.setText("no photos in album");
            	}
            	else if(photos.size()==1){
            		totalData.windowsIndex = photos.size()-1;
            		setVisible(false);
                	photolist.windowsList.get(totalData.windowsIndex).setVisible(true);
            		nextButton.setEnabled(false);
            		previousButton.setEnabled(false);
            	}
            	else if(totalData.windowsIndex == 0){
            		//go to end
            		totalData.windowsIndex = photos.size()-1;
            		previousButton.setEnabled(false);
            		nextButton.setEnabled(true);
            		setVisible(false);
                	photolist.windowsList.get(totalData.windowsIndex).setVisible(true);
            	}
            	else{
            		totalData.windowsIndex --;
            		setVisible(false);
                	photolist.windowsList.get(totalData.windowsIndex).setVisible(true);
                	
            	}
            	
            }
        });
        
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            	ErrorMessage.setText("");
            	if(photos.size() == 0){
            		PhotoList.setVisible(true);
                	setVisible(false);
                	photolist.ErrorMessage.setText("no photos in album");
            	}
            	else if(photos.size()==1){
            		totalData.windowsIndex = photos.size()-1;
            		setVisible(false);
                	photolist.windowsList.get(totalData.windowsIndex).setVisible(true);
            		nextButton.setEnabled(false);
            		previousButton.setEnabled(false);
            	}
            	else if(totalData.windowsIndex == photos.size()-1){
            		//go to beginning
            		totalData.windowsIndex = 0;
            		setVisible(false);
                	photolist.windowsList.get(totalData.windowsIndex).setVisible(true);
            	}
            	else{
            		totalData.windowsIndex++;
            		setVisible(false);
                	photolist.windowsList.get(totalData.windowsIndex).setVisible(true);
            	}
            	
            }
        });
        
        photoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	ErrorMessage.setText("");
            	PhotoList.setVisible(true);
            	photolist.RedoList();
            	photolist.select();
            	setVisible(false);
            }
        });
        
        movePhotoButton.addActionListener(new java.awt.event.ActionListener() {
        	
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	ErrorMessage.setText("");
            	int index = moveAlbumJList.getSelectedIndex();
            	//if there is something selected
            	if(index!=-1){
            		index = giveAlbumsIndex( index);
            		String newAlbum = login.Controller.currentUser.getAlbums().get(index).getName();
            		String oldAlbum = currentAlbum.getName();
            		
            		if(!login.Controller.movePhoto(currentPhoto.getFilename(), oldAlbum, newAlbum)){
            			ErrorMessage.setText("Error, photo already exists in album");
            		}
            		photos = totalData.albums.get(totalData.checkAlbumIndex(currentAlbum)).getPhotos();
            		currentAlbum = totalData.albums.get(totalData.checkAlbumIndex(currentAlbum));
            		photolist.photos = currentAlbum.getPhotos();
            		photolist.RedoList();
            		album.RedoList();
            		
            		if(photos.size() == 0){
            			PhotoList.setVisible(true);
                    	setVisible(false);
                    	photolist.ErrorMessage.setText("no photos in album");
                		
                	}
            		else if(photos.size()==1){
                		totalData.windowsIndex = photos.size()-1;
                		setVisible(false);
                    	photolist.windowsList.get(totalData.windowsIndex).setVisible(true);
                		nextButton.setEnabled(false);
                		previousButton.setEnabled(false);
                	}
                	else{
                		
                		totalData.windowsIndex++;
                		if(totalData.windowsIndex >= photos.size()){
                		 totalData.windowsIndex = 0;
                		}
                		setVisible(false);
                    	photolist.windowsList.get(totalData.windowsIndex).setVisible(true);
                	
                		
                	}
            	}
            	else{
            		ErrorMessage.setText("Error: Album not selected");
            	}
            	//currentAlbum = login.Controller.currentUser.getAlbums().get(index);
            }
        });
        
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	ErrorMessage.setText("");
            	setVisible(false);
            	Login.setVisible(true);            	
            }
        });
        
        albumButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	ErrorMessage.setText("");
            	setVisible(false);
            	Album.setVisible(true);
            	album.RedoList();
            	album.select();
            }
        });
        
        addTagButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	ErrorMessage.setText("");
            	String tagText = addTagText.getText();
            	String token[] = tagText.split(":");
            	if(token.length!=2){
            		ErrorMessage.setText("Format tag like tagType:\"tagValue\"");
            	}
            	//String pass = "addTag \"" + currentPhoto.getFilename()+"\" " + tagText; 
            	else{
            		//remove token[1] quotes
            	if(token[1].charAt(0)=='"' && token[1].charAt(token[1].length()-1)=='"' && token[1].length()>2 && token[0].length()>0){
            		token[1] = token[1].substring(1, token[1].length()-1);
            		
            		if(login.Controller.addTag(currentPhoto.getFilename(), token[0], token[1])){
            			setPhotoDetailText();
            		}
            		else{
    					ErrorMessage.setText("Error: tag already exist");
    				}
            	}
				else{
					ErrorMessage.setText("Format tag like tagType:\"tagValue\"");
				}
            	}
            	deleteTagText.setText(""); addTagText.setText("");
            }
        });
        
        deleteTagButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	
            	ErrorMessage.setText("");
            	String tagText = deleteTagText.getText();
            	if(tagText.equals("")){
            		ErrorMessage.setText("Error: Enter a tag. Format: tagType:\"tagVal\"");
            	}
            	else{
            	String token[] = tagText.split(":");
            	if(token.length!=2){
            		ErrorMessage.setText("Format tag like tagType:\"tagValue\"");
            	}
            	else{
            	//String pass = "deleteTag \"" + currentPhoto.getFilename()+"\" " + tagText; 
            		if(token[1].charAt(0)=='"' && token[1].charAt(token[1].length()-1)=='"' && token[1].length()>2 && token[0].length()>0){
                		token[1] = token[1].substring(1, token[1].length()-1);
                		
                		if(login.Controller.deleteTag(currentPhoto.getFilename(), token[0], token[1])){
                			setPhotoDetailText();
                		}
                		else{
        					ErrorMessage.setText("Error: tag doesn't exist");
        				}
                	}
    				else{
    					ErrorMessage.setText("Format tag like tagType:\"tagValue\"");
    				}

            	}
            	}
            	deleteTagText.setText(""); addTagText.setText("");
            	}
        });
        
        
        
        
        
        
    }   
    
    /**
     * makes all the tags on a photo into one string to be displayed
     * @return the string of formatted tags to be displayed
     */
    private String concatTags(){
    	String allTags = "";
    	tagSort c = new tagSort();
    	ArrayList<Tag> temp = currentPhoto.getTags();
    	Collections.sort(temp,c);
    	currentPhoto.setTags(temp);
    	for(int i=0; i<currentPhoto.getTags().size(); i++){
    		
    		allTags = allTags + currentPhoto.getTags().get(i).getName() + ": " + currentPhoto.getTags().get(i).getDescription() + ", ";
    		
    	}
    	if(allTags.equals("")){
    		return "(no tags)";
    	}
    	return allTags.substring(0, allTags.length()-2);
    }
    
    /**
     * sets the photo details displayed
     */
    private void setPhotoDetailText(){
    	String allTags = concatTags();
        photoDetail.setText("Caption: " + currentPhoto.getCaption() + "\n Date&Time: " + currentPhoto.getTimestamp().getTime().toString() + "\n Tags: " + allTags);
        
    }
    
    /**
     * adds the albums to the move photo scrollpane
     */
    private void createAlbumsStringList(){
    	for(int i=0; i<login.Controller.currentUser.getAlbums().size(); i++){
    		
    		String albumName = login.Controller.currentUser.getAlbums().get(i).getName();
    		if(!albumName.equals(currentAlbum.getName()))
    			albumsList.addElement(albumName);
    	}
    }
    /**
     * adds the albums to the move photo scrollpane
     */
    private int giveAlbumsIndex(int index){
    	int ret =-1; int i;
    	for(i=0; i<login.Controller.currentUser.getAlbums().size(); i++){
    		
    		String albumName = login.Controller.currentUser.getAlbums().get(i).getName();
    		if(albumName.equals(currentAlbum.getName()) && ret!=index){
    				}
    		else if(ret==index){
    			break;
    		}else{
    			ret++;
    			if(ret==index && index==i){
    				return ret;
    			}
    		}
    	}
    	if(i>ret && index==ret){
    		ret++;
    	}
    	return ret;
    }

    /**
     * scales the image to a manageable size
     * @param src the Image to be resized
     * @return the Image converted to an ImageIcon
     */
    private ImageIcon scale(Image src) {

        int type = BufferedImage.TYPE_INT_RGB;
        BufferedImage image = new BufferedImage(400, 400, type);
        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(src, 0, 0, 400, 400, this);
        graphics.dispose();
        return new ImageIcon(image);
    }
                     
}

