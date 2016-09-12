/**
 * @version 1.0
 */
package cs213.photoAlbum.gui.view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import cs213.photoAlbum.control.ProgramControl;
import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.User;
import cs213.photoAlbum.model.totalData;



/**
 * displays the list of albums that a user has after logging in
 * has add/delete/rename album functionality
 * can also display photos by tag and by date
 * @author Jasmine Feng and Risham Chokshi
 */
public class album extends javax.swing.JFrame {
	                     
    public static javax.swing.JPanel Panel;
    int search = -1; //if this is 0 then search by date, and 1 if search by tag
    
    public static javax.swing.JLabel albumList;
    public javax.swing.JButton DoneButton;
    private javax.swing.JButton cancelButton;
    public static javax.swing.JScrollPane albumListScroll;
    public static javax.swing.JButton createButton;
    public static javax.swing.JButton deleteButton;
    public static javax.swing.JButton logoutButton;
    public static javax.swing.JButton openAlbum;
    public static javax.swing.JButton renameButton;
    public static javax.swing.JButton searchByDate;
    public static javax.swing.JButton searchByTag;
    public static javax.swing.JTextField searchText;
    private javax.swing.JLabel ErrorMessage;
    public static javax.swing.JTextField textField;
    public static JList ListofAlbums;
    public static DefaultListModel albumlist;
    public static JFrame Login;
    public static JFrame me;
    // End of variables declaration 
    /**
     * Creates new form album
     */
    public album(JFrame Login) {
    	me = this;
    	this.Login = Login;
        initComponents();
    }

   

	/**
	 * called from the constructor to initialize the form.
	 */
    private void initComponents() {

        albumList = new javax.swing.JLabel();
        albumlist = new DefaultListModel();
        deleteButton = new javax.swing.JButton();
        openAlbum = new javax.swing.JButton();
        Panel = new javax.swing.JPanel();
        textField = new javax.swing.JTextField();
        createButton = new javax.swing.JButton();
        renameButton = new javax.swing.JButton();
        searchByTag = new javax.swing.JButton();
        searchByDate = new javax.swing.JButton();
        searchText = new javax.swing.JTextField();
        logoutButton = new javax.swing.JButton();
        ErrorMessage = new javax.swing.JLabel();
        DoneButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        
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
        setTitle("Album List");
        setResizable(false);

        albumList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        albumList.setText("Album List");

        deleteButton.setText("Delete");

        openAlbum.setText("Open Album");
        
        

        Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        

        createButton.setText("Create");
        

        renameButton.setText("Rename");
        //TODO: DO THIS
        RedoList();
        
        ListofAlbums = new JList(albumlist);
        ListofAlbums.setVisibleRowCount(-1);
        
        if(albumlist.isEmpty()!=true){
        	ListofAlbums.setSelectedIndex(0);
        }
        ListofAlbums.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        albumListScroll = new javax.swing.JScrollPane(ListofAlbums);
        
        
        

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addComponent(createButton)
                        .addGap(18, 18, 18)
                        .addComponent(renameButton)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createButton)
                    .addComponent(renameButton))
                .addGap(29, 29, 29))
        );

        searchByTag.setText("Search by Tag");

        searchByDate.setText("Search by Date");

        searchText.setEnabled(false);

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        DoneButton.setText("Done");
        DoneButton.setEnabled(false);
       

        cancelButton.setText("Cancel");
        cancelButton.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(albumListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(DoneButton, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cancelButton))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(searchByTag)
                                        .addGap(28, 28, 28)
                                        .addComponent(searchByDate)))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(albumList, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteButton)
                        .addGap(33, 33, 33)
                        .addComponent(openAlbum)
                        .addGap(70, 70, 70))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(ErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(albumList, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton)
                    .addComponent(openAlbum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(albumListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchByTag)
                            .addComponent(searchByDate))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DoneButton)
                    .addComponent(cancelButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ErrorMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoutButton))
                .addContainerGap())
        );
        
        pack();
        setLocationRelativeTo(null);
        
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        
        renameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //renameAlbum "oldAlbumName" "newAlbumName"
            	searchText.setText(""); ErrorMessage.setText("");
            	if(ListofAlbums.getSelectedIndex()==-1){
            		ErrorMessage.setText("Error: Select an album first");
            	}
            	else if(textField.getText()==null || textField.getText().length()<=0){
            		ErrorMessage.setText("Error: Enter a Album Name");
            	}
            	else{
            		//album is selected
            		ErrorMessage.setText("");
            		int index = ListofAlbums.getSelectedIndex();
            		String pass = "renameAlbum \"" + login.iv.user.getAlbums().get(ListofAlbums.getSelectedIndex()).getName() + "\" \"" + textField.getText() + "\"";
            		try {
						if(login.iv.run(pass)){
							RedoList();
							ListofAlbums.setSelectedIndex(index);
							ListofAlbums.ensureIndexIsVisible(index);
						}
						else{
							ErrorMessage.setText("Error: album with that name exist");
							ListofAlbums.setSelectedIndex(index);
							ListofAlbums.ensureIndexIsVisible(index);
						}
					} catch (IOException e) {
						
					} catch (ParseException e) {
						
					}
            	}
            }
        });
        
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              
            	ErrorMessage.setText(""); searchText.setText("");
            	if(textField.getText()==null || textField.getText().length()<=0){
            		ErrorMessage.setText("Error: Enter a Album Name");
            	}
            	else{
            		int index = ListofAlbums.getSelectedIndex();
            		String pass = "createAlbum \"" + textField.getText() + "\"";
            		try {
						if(login.iv.run(pass)){
							RedoList();
							if(index==-1)
								index = 0;
							ListofAlbums.setSelectedIndex(index);
							ListofAlbums.ensureIndexIsVisible(index);
						}
						else{
							ErrorMessage.setText("Error: album with that name exist");
							ListofAlbums.setSelectedIndex(index);
							ListofAlbums.ensureIndexIsVisible(index);
						}
					} catch (IOException e) {
						
					} catch (ParseException e) {
						
					}
            	}
            	
            }
        });
        
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            	ErrorMessage.setText(""); searchText.setText("");
            	textField.setText("");
            	if(ListofAlbums.isSelectionEmpty()){
            		ErrorMessage.setText("Error: No Album selected");
            		}
            	else{
            		//row is selected
            		int index = ListofAlbums.getSelectedIndex();
            		//remove from the list
            		String pass = "deleteAlbum \"" + login.iv.user.getAlbums().get(index).getName() + "\"";
            		//delete from the totalDAta
            		try {
						if(login.iv.run(pass)){
							RedoList();
							if(!ListofAlbums.isSelectionEmpty()){
								index = 0;
								ListofAlbums.setSelectedIndex(index);
								ListofAlbums.ensureIndexIsVisible(index);
							}
						}
						else{
							ErrorMessage.setText("Error: album does not exist");
							ListofAlbums.setSelectedIndex(index);
							ListofAlbums.ensureIndexIsVisible(index);
						}
					} catch (IOException e) {
						
					} catch (ParseException e) {
						
					}
            		
            		
            	}
            }
        });
        
        openAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int index = ListofAlbums.getSelectedIndex();
                if(index==-1){
                	//error
                	ErrorMessage.setText("Error: Select an Album first");
                }
                else{
            	setVisible(false);
            	JFrame PhotoList = new photolist(Login,me,login.iv.user.getAlbums().get(index),login.iv.user.getAlbums().get(index).getPhotos());
            	PhotoList.setVisible(true);
            	ErrorMessage.setText(""); 
            }
                textField.setText(""); searchText.setText("");
            }
        });
        
        searchByDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            	ErrorMessage.setText("MM/dd/yyyy-HH:mm:ss MM/dd/yyyy-HH:mm:ss"); textField.setText("");
            	search = 0;
            	searchText.setEnabled(true);
            	DoneButton.setEnabled(true);
            	cancelButton.setEnabled(true);
            	searchByDate.setEnabled(false);
            	searchByTag.setEnabled(false);
            	searchText.setText("");
            }
        });
        
        searchByTag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	ErrorMessage.setText("<tagType>:\"<tagValue>\", \"<tagValue>\", ...");
            	 textField.setText("");
            	search = 1;
            	searchText.setEnabled(true);
            	DoneButton.setEnabled(true);
            	cancelButton.setEnabled(true);
            	searchByDate.setEnabled(false);
            	searchByTag.setEnabled(false);
            	searchText.setText("");
            }
        });
        
        DoneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                   	//2 cases
            	if(search==1){ //its search by tag
            		//getPhotosByTag <tagType>:"<tagValue>", "<tagValue>"... 
            		String selection = "";
            		if(searchText.getText().equals("")){
            			ErrorMessage.setText("Error: You need to enter tags");
            		}
            		else{
            			selection = "getPhotosByTag " + searchText.getText();
            			String[] tokens = selection.split(" ");
            			ArrayList<Photo> finallist = login.iv.getPhotosByTag(tokens);
            			if(finallist==null){
            				ErrorMessage.setText("Error: Enter in format: <tagType>:\"<tagValue>\", .. ");
            			}
            			else if(finallist.isEmpty()){
            				ErrorMessage.setText("No photos exist with this search");
            			}
            			else{
            				setVisible(false);
                        	JFrame PhotoList = new photolist(Login,me,null,finallist);
                        	PhotoList.setVisible(true);
                        	ErrorMessage.setText("");
                        	searchText.setEnabled(false);
                        	DoneButton.setEnabled(false);
                        	cancelButton.setEnabled(false);
                        	searchByDate.setEnabled(true);
                        	searchByTag.setEnabled(true);
                        	search = -1;
            			}
            		}
            		
            	}
            	else if(search == 0){ //its search by date
            		//getPhotosByDate MM/dd/yyyy-HH:mm:ss MM/dd/yyyy-HH:mm:ss
            		String selection = "";
            		if(searchText.getText().equals("")){
            			ErrorMessage.setText("Error format- MM/dd/yyyy-HH:mm:ss MM/dd/yyyy-HH:mm:ss");
            		}
            		else{
            			selection = "getPhotosByDate " + searchText.getText();
            			String[] tokens = selection.split(" ");
            			ArrayList<Photo> finallist;
						try {
							finallist = login.iv.getPhotosByDate(tokens);
							if(finallist==null){
	            				ErrorMessage.setText("Error format: MM/dd/yyyy-HH:mm:ss MM/dd/yyyy-HH:mm:ss");
	            			}
	            			else if(finallist.isEmpty()){
	            				ErrorMessage.setText("No photos exist with this search");
	            			}
	            			else{
	            				setVisible(false);
	                        	JFrame PhotoList = new photolist(Login,me,null,finallist);
	                        	PhotoList.setVisible(true);
	                        	ErrorMessage.setText("");
	                        	searchText.setEnabled(false);
	                        	DoneButton.setEnabled(false);
	                        	cancelButton.setEnabled(false);
	                        	searchByDate.setEnabled(true);
	                        	searchByTag.setEnabled(true);
	                        	search = -1;
	            			}
						} catch (ParseException e) {
							
							ErrorMessage.setText("Error format: MM/dd/yyyy-HH:mm:ss MM/dd/yyyy-HH:mm:ss");
						}
            			
            		}
            	}
            }
        });
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               	searchText.setText("");
               	search = -1;
               	ErrorMessage.setText("");
            	searchText.setEnabled(false);
            	DoneButton.setEnabled(false);
            	cancelButton.setEnabled(false);
            	searchByDate.setEnabled(true);
            	searchByTag.setEnabled(true);
            }
        });
        
    }                      

   
                                        

                                          
    /**
     * action listener for logout button, sets login screen visible
     * @param evt
     */
    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
       
    	setVisible(false);
    	Login.setVisible(true);
    	
    }                                            
    /**
     * sorts the album list
     * @param users the arraylist of albums
     * @return the sorted arraylist
     */
    public static ArrayList<Album> sortAlbum(ArrayList<Album> users){
		int i, j, first;
		Album temp;  
		
	     for ( i = 0; i < users.size(); i++ )  
	     {
	    	 //int x = Integer.parseInt(users.get(i).getID());
	    	 String x = users.get(i).getName();
	    	 first = i;   //initialize to subscript of first element
	    	 //int smallest = x; //initalize to x
	         String smallest = x; 
	    	 for(j = i + 1; j < users.size(); j ++)   //locate smallest element between positions 1 and i.
	          {
	        	 //int y = Integer.parseInt(users.get(j).getID());
	             String y =   users.get(j).getName();
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
     * selects
     * */
    public static void select(){
    	if(albumlist.isEmpty()!=true){
    		ListofAlbums.setSelectedIndex(0);
			ListofAlbums.ensureIndexIsVisible(0);
    	}
    }
    
    /**
     * updates the album list displayed in the scrollpane
     */
    public static void RedoList(){
    	albumlist.clear();
    	
    	ArrayList<Album> temp = login.iv.user.getAlbums();
		temp = sortAlbum(temp);
		for(int i =0; i<temp.size(); i++){
			String name = "";
			if(temp.get(i) != null ){
				if(temp.get(i).getPhotos().isEmpty() != true){
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					Date dat = temp.get(i).startdate.getTime();
					String dat1 = sdf.format(dat);
					dat = temp.get(i).enddate.getTime();
					String dat2 = sdf.format(dat);
					//System.out.println(albums.get(i).getName() + " number of photos: " + albums.get(i).getPhotos().size() + ", " + albums.get(i).startdate + " - " + albums.get(i).enddate);
					name = temp.get(i).getName() + " ~ photos: " + temp.get(i).getPhotos().size() + ", " + dat1 + " - " + dat2;

				}else
					name = temp.get(i).getName() + " ~ photos: " + temp.get(i).getPhotos().size();
			}
			
			if(name!=null){
				albumlist.addElement(name);
			}
			}
    }

                      
}
