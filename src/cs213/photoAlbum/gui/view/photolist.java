/**
 * @version 1.0
 */
package cs213.photoAlbum.gui.view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import cs213.photoAlbum.control.ProgramControl;
import cs213.photoAlbum.model.CalendarDateWithoutTimeComparator;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.User;
import cs213.photoAlbum.model.totalData;


/**
 * displays the list of photos in an album
 * has the add/delete photo, recaption functionality
 * from here you can open up a photo in more detail
 * @author Risham Chokshi and Jasmine Feng
 */
public class photolist extends javax.swing.JFrame {
	                    
    public static javax.swing.JScrollPane ScrollPanel;
    public static DefaultListModel<ImageIcon> photoslist;
    public static JList photosJList;
    public static javax.swing.JButton addButton;
    public static JLabel addTextLabel;
    public static javax.swing.JTextField addText;
    public static JLabel captionTextLabel;
    public static javax.swing.JTextField captionText;
    public static javax.swing.JButton albumButton;
    public static javax.swing.JButton deleteButton;
    public static javax.swing.JButton displayButton;
    public static javax.swing.JButton logoutButton;
    public static javax.swing.JButton recaptionButton;
    public static JLabel recaptionLabel;
    public static javax.swing.JTextField recaptionText;
    public static javax.swing.JLabel ErrorMessage;
    public static JFrame Login; //for login screen
    public static JFrame Album;//for the album frame
    public static cs213.photoAlbum.model.Album currentalbum;
    public static JFrame me;
    public static ArrayList<cs213.photoAlbum.model.Photo> photos;
    public static ArrayList<photo> windowsList;
    // End of variables declaration      
   

        /**
         * Creates new form photolist
         */
        public photolist(JFrame Login, JFrame Album, cs213.photoAlbum.model.Album currentalbum, ArrayList<cs213.photoAlbum.model.Photo> photos) {
        	this.me = this;
        	this.Album = Album;
        	this.Login = Login;
        	this.currentalbum = currentalbum;
        	this.photos = photos;
        	this.windowsList = new ArrayList<photo>();
            initComponents();
        }

        @SuppressWarnings("unchecked")
                             
        private void initComponents() {

            //ScrollPanel = new javax.swing.JScrollPane();
            deleteButton = new javax.swing.JButton();
            addButton = new javax.swing.JButton();
            addText = new javax.swing.JTextField();
            captionText = new javax.swing.JTextField();
            addTextLabel = new JLabel();
            captionTextLabel = new JLabel();
            recaptionButton = new javax.swing.JButton();
            recaptionText = new javax.swing.JTextField();
            recaptionLabel = new JLabel();
            displayButton = new javax.swing.JButton();
            albumButton = new javax.swing.JButton();
            logoutButton = new javax.swing.JButton();
            ErrorMessage = new javax.swing.JLabel();
            photoslist = new DefaultListModel();

        
            
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
            setTitle("Photo List");
            setResizable(false);

            deleteButton.setText("Delete Photo");

            addButton.setText("Add Photo");

            recaptionButton.setText("Recaption Photo");

            displayButton.setText("Display Photo");

            albumButton.setText("Back to Albums");

            logoutButton.setText("Logout");
            
            addTextLabel.setText("filename:");
            
            captionTextLabel.setText("Caption:");
            
            ErrorMessage.setText("");
            
            recaptionLabel.setText("new caption:");
            
            RedoList();
            
            photosJList = new JList(photoslist);
            photosJList.setVisibleRowCount(-1);
            if(photos.isEmpty() !=true){
            	photosJList.setSelectedIndex(0);
            }
            photosJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            ScrollPanel = new javax.swing.JScrollPane(photosJList);
          

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(albumButton))
                    .addGap(29, 29, 29)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(displayButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                            .addComponent(logoutButton)
                            .addGap(55, 55, 55))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(deleteButton)
                                    .addComponent(addTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addText, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                    .addComponent(captionTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(captionText, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                    .addComponent(addButton)
                                    .addComponent(recaptionButton)
                                    .addComponent(recaptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(recaptionText))
                                .addComponent(ErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE))))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(albumButton)
                        .addComponent(ErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(deleteButton)
                            .addGap(36, 36, 36)
                            //.addComponent(addButton)
                           // .addGap(18, 18, 18)
                            .addComponent(addTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            //.addGap(36, 36, 36)
                            .addComponent(captionTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(captionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(9,9,9)
                            .addComponent(addButton)
                            .addGap(36, 36, 36)
                            //.addComponent(recaptionButton)
                            //.addGap(18, 18, 18)
                            .addComponent(recaptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(recaptionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(9,9,9)
                            .addComponent(recaptionButton)
                            .addGap(46, 46, 46)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(logoutButton)
                                .addComponent(displayButton)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(ScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(12, Short.MAX_VALUE))
            );
            
            if(currentalbum==null){
            	addButton.setEnabled(false);
            	addTextLabel.setEnabled(false);
            	addText.setEnabled(false);
            	captionTextLabel.setEnabled(false);
            	captionText.setEnabled(false);
            	deleteButton.setEnabled(false);
            }
            
            pack();
            setLocationRelativeTo(null);
            
            logoutButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                	setVisible(false);
                	Login.setVisible(true);
                }
            });
            
            addButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                   
                	if(addText.getText().equals("")){
                		ErrorMessage.setText("Please enter a filename");
                	}
                	else {
                		ErrorMessage.setText("");
                		boolean answer = login.Controller.addPhoto(addText.getText(), captionText.getText(), currentalbum.getName()/*login.iv.user.getAlbums().get(album.ListofAlbums.getSelectedIndex()).getName()*/);
                		if(answer){
                		RedoList();
                		String filename = totalData.filepass(addText.getText());
                		int index = findPhoto(addText.getText());
            			if(index==-1){
            				index = findPhoto(filename);
            				if(index==-1)
            					index = 0;
            				}
            				photosJList.setSelectedIndex(index);
            				photosJList.ensureIndexIsVisible(photosJList.getSelectedIndex());
            				ErrorMessage.setText("");
                        	addText.setText(""); captionText.setText("");
            			
                	}
                		else{
                			//error
                			ErrorMessage.setText("Error: check if file exist or caption empty");
                			int index = 0;
                			photosJList.setSelectedIndex(index);
            				photosJList.ensureIndexIsVisible(photosJList.getSelectedIndex());
            				
                		}
                	}
                	
                }
            });
            
            recaptionButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                   
                	if(photoslist.isEmpty() || photosJList.isSelectionEmpty()){
                		ErrorMessage.setText("Error: no photo selected");
                	}
                	else if(recaptionText.getText().equals("")){
                		ErrorMessage.setText("Please enter a caption");
                	}
                	else{
                		ErrorMessage.setText("");
                		
                		int index = photosJList.getSelectedIndex();
                    	boolean answer = login.Controller.editCaption(photos.get(index).getFilename(), recaptionText.getText());
                    	if(answer){
                    		ErrorMessage.setText("Photo's caption has changed!");
                    		recaptionText.setText("");
                    		RedoList();
                    	}
                    	else{
                    		ErrorMessage.setText("Error: Enter new caption for photo");
                    	}
                    	photosJList.setSelectedIndex(index);
        				photosJList.ensureIndexIsVisible(photosJList.getSelectedIndex());
                	}
                	
                }
            });
            
            displayButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                	
                	if(photosJList.isSelectionEmpty()){
                		ErrorMessage.setText("Error: no photos");
                	}
                	else{
                		ErrorMessage.setText("");
                		int index = photosJList.getSelectedIndex();
                		
                		totalData.windowsIndex = index;
                    	
                    	setVisible(false);
                    	photo Photo = new photo(Login,Album,me, photos.get(index), currentalbum,photos);

                    	Photo.setVisible(true);
                	}	
                	
                }
            });
            
            deleteButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                	if(photoslist.isEmpty() || photosJList.isSelectionEmpty()){
                		ErrorMessage.setText("Error: no photos");
                	}
                	else{
                		ErrorMessage.setText("");
                		int index = photosJList.getSelectedIndex();
                    	
                		boolean answer = login.Controller.removePhoto(photos.get(index).getFilename(), currentalbum.getName());
                    		if(answer)
                    			RedoList();
                    		else{
                    			ErrorMessage.setText("Error in deleting photo");
                    		}
                    		index = 0;
            				
            				photosJList.setSelectedIndex(index);
            				photosJList.ensureIndexIsVisible(photosJList.getSelectedIndex());
            				ErrorMessage.setText("");
                        	addText.setText(""); captionText.setText("");
            			
                	
                	}
                	
                }
            });
            
            albumButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                	Album.setVisible(true);
                	album.RedoList();
                	album.select();
                	setVisible(false);
                }
            });
            
            
            
        }                      

        public static void RedoList(){
    		
        	photoslist.clear();
        	windowsList.clear();
        	if(currentalbum!=null)
        		photos=currentalbum.getPhotos();
        	ArrayList<Photo> temp = photos;
        	CalendarDateWithoutTimeComparator c = new CalendarDateWithoutTimeComparator();
    		Collections.sort(temp,c);
        	//temp = ProgramControl.sortUsers(temp);
        	for(int i =0; i<temp.size(); i++){
    			ImageIcon image = new ImageIcon(temp.get(i).getFilename());
    			image = scale(image.getImage());
    			
    			if(image!=null){
    				photoslist.addElement(image);
    			}
    			
    			photo Photo = new photo(Login,Album,me, photos.get(i), currentalbum,photos);
    			windowsList.add(Photo);
    			
    		}
    			
    	}
        
        /**
    	 * find a photo by the String id
    	 * @param id the String user id
    	 * @return int  if found, null otherwise
    	 */
    	public int findPhoto(String id) {
    		//System.out.println("findUser");
    		CalendarDateWithoutTimeComparator c = new CalendarDateWithoutTimeComparator();
    		
    		Collections.sort(photos,c);
    		for(int i=0; i<photos.size(); i++){
    			if(photos.get(i).getFilename().equals(id)){
    				//System.out.println(totalData.users.get(i).getID() + " " + totalData.users.get(i).getName());
    				return i;
    			}
    		}
    		return -1;
    	}
    	
        /**
         * selects
         * */
        public static void select(){
        	if(photoslist.isEmpty()!=true){
        		photosJList.setSelectedIndex(0);
    			photosJList.ensureIndexIsVisible(0);
        	}
        }
    	
        private static ImageIcon scale(Image src) {

            int type = BufferedImage.TYPE_INT_RGB;
            BufferedImage image = new BufferedImage(50, 50, type);
            Graphics2D graphics = image.createGraphics();
            graphics.drawImage(src, 0, 0, 50, 50, null);
            graphics.dispose();
            return new ImageIcon(image);
        }
                 
    }
