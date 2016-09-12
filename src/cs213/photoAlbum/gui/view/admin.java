/**
 * @version 1.0
 */
package cs213.photoAlbum.gui.view;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import cs213.photoAlbum.control.ProgramControl;
import cs213.photoAlbum.model.User;
import cs213.photoAlbum.model.totalData;



/**
 * the admin view of the users
 * add/delete user functionality
 * @author Jasmine Feng and Risham Chokshi
 */
public class admin extends javax.swing.JFrame {
                    
    public static javax.swing.JButton Cancel;
    public static javax.swing.JPanel Panel;
    private javax.swing.JLabel ErrorMessage;
    private javax.swing.JLabel listofUsersText;
    public static javax.swing.JButton deleteUser;
    public static javax.swing.JLabel idLabel;
    public static javax.swing.JTextField idText;
    public static javax.swing.JButton addButton;
    public static javax.swing.JScrollPane listUser;
    public static javax.swing.JButton loginButton;
    public static javax.swing.JLabel nameLabel;
    public static javax.swing.JTextField nameText;
    public static DefaultListModel<String> userlist;
    public static JList ListofUser;
    public static JFrame Login; // so we can refer back 
    // End of variables declaration      
    /**
     * Creates new form admin
     */
    public admin(JFrame Login) {
    	this.Login = Login;
    	initComponents();
    }

    /**
     * called from the constructor to initialize the form.
     * 
     */
    @SuppressWarnings("unchecked")
                           
    private void initComponents() {

       
        deleteUser = new javax.swing.JButton();
        loginButton = new javax.swing.JButton();
        Panel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        idText = new javax.swing.JTextField();
        userlist = new DefaultListModel();
        ErrorMessage = new javax.swing.JLabel();
        listofUsersText = new javax.swing.JLabel();
        
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
        setTitle("Admin Page");
        setResizable(false);

        deleteUser.setText("Delete User");

        loginButton.setText("Login Screen");

        Panel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        addButton.setText("Add User");

        Cancel.setText("Cancel");

        nameLabel.setText("Name:");

        idLabel.setText("ID:");

        
        //add everything to the userlist for the users to be displayed
        //sets up all the userlist
        
        RedoList();
        
        ListofUser = new JList(userlist);
        ListofUser.setVisibleRowCount(-1);
        if(totalData.users.isEmpty()!=true){
        	ListofUser.setSelectedIndex(0);
        }
        ListofUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listUser = new javax.swing.JScrollPane(ListofUser);
        
        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cancel)
                    .addComponent(addButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idLabel)
                    .addComponent(nameLabel)
                    .addComponent(nameText)
                    .addComponent(idText, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addButton)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(nameText)))
                .addGap(18, 18, 18)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancel)
                    .addComponent(idLabel))
                .addGap(10, 10, 10)
                .addComponent(idText, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        listofUsersText.setText("List of Users:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(listofUsersText)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(deleteUser)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(loginButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(ErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(ErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(listofUsersText)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteUser)
                            .addComponent(loginButton))
                        .addGap(18, 18, 18)
                        .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(listUser, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
        
             
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            	ErrorMessage.setText("");
            	//try{
            		//int idI = Integer.parseInt(idText.getText());
            		if(nameText.getText()==null || nameText.getText().isEmpty()){
            			ErrorMessage.setText("Error: Enter something in Name field");
            		}
            		else{
            		if(totalData.users == null){
            			
            			totalData.users.add(new User(idText.getText(), nameText.getText()));
            			RedoList();
            			
            			int index = findUser(idText.getText());
            			
            			if(index!=-1){
            				ListofUser.setSelectedIndex(index);
            				ListofUser.ensureIndexIsVisible(ListofUser.getSelectedIndex());
            				ErrorMessage.setText("");
                        	idText.setText(""); nameText.setText("");
            			}
            			}
            		else{
            			int i =0;
            			//find the name or the id
            			for(;i<totalData.users.size();i++){
            				
            				if(totalData.users.get(i).getID().equals(idText.getText())){
            					break;
            				}
            				
            			}
            		//couldnt find the user, so add the user
            		if(i==totalData.users.size()){
            			totalData.users.add(new User(idText.getText(), nameText.getText()));
            			RedoList();
            			int index = findUser(idText.getText());
            			if(index!=-1){
            				ListofUser.setSelectedIndex(index);
            				ListofUser.ensureIndexIsVisible(ListofUser.getSelectedIndex());
            				ErrorMessage.setText("");
                        	idText.setText(""); nameText.setText("");
            			}
            		}
            		else{
            			ErrorMessage.setText("Error: ID has to be unique");
            			idText.setText("");
            			nameText.setText("");
            		}
            		}
            		}
            		}
            		//catch(NumberFormatException e){
            			//ErrorMessage.setText("Error: User Id should be an integer");
            			//idText.setText("");
            			
            		//}
            		
           //  }
        });
        
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               	ErrorMessage.setText("");
            	idText.setText("");
            	nameText.setText("");
            }
        });
        
        deleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              
            	ErrorMessage.setText("");
            	idText.setText(""); nameText.setText("");
            	int index = ListofUser.getSelectedIndex();
            	if(index==-1){
            		ErrorMessage.setText("Error: Select a user first");
            	}
            	else{
            		ArrayList<User> temp = totalData.users;
            		temp = ProgramControl.sortUsers(temp);
            		if(deleteUser(temp.get(index).getID())){
            			RedoList();
            			if(totalData.users.isEmpty()!=true){
            				ListofUser.setSelectedIndex(0);
            				ListofUser.ensureIndexIsVisible(ListofUser.getSelectedIndex());
            			}
            		}
            		else{
            			ErrorMessage.setText("Error: Select a user first");
            		}
            	}
            	
            	
            }
        });
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
					login.Controller.updateUserFile(totalData.users);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
            	ErrorMessage.setText("");
            	idText.setText(""); nameText.setText("");
               	setVisible(false);
            	Login.setVisible(true);
            }
        });
        
        
        
        
        
    }                     

   
    /**
     * updates the list of users displayed in scrollpane
     */
    public void RedoList(){
		
    	userlist.clear();
    	
    	ArrayList<User> temp = totalData.users;
		temp = ProgramControl.sortUsers(temp);	
		for(int i =0; i<temp.size(); i++){
			String name = temp.get(i).getID() + ", " + temp.get(i).getName();
			if(name!=null){
				userlist.addElement(name);
			}
			}
			
		}
    
    /**
	 * find a user by the String id
	 * @param id the String user id
	 * @return User the User if found, null otherwise
	 */
	public int findUser(String id) {
		//System.out.println("findUser");
		totalData.users = ProgramControl.sortUsers(totalData.users);
		for(int i=0; i<totalData.users.size(); i++){
			if(totalData.users.get(i).getID().equals(id)){
				//System.out.println(totalData.users.get(i).getID() + " " + totalData.users.get(i).getName());
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * removes a user by their id
	 * @param id as a String
	 * @return true if success, false if failed
	 */
	public boolean deleteUser(String id) {
		for(int i=0; i<totalData.users.size(); i++){
			if(totalData.users.get(i).getID().equalsIgnoreCase(id)){
				totalData.users.remove(i);
				return true;
			}
		}
		//user not found
		return false;
	}
	
 
                 
}
