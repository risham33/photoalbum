/**
 * @version 1.0
 */
package cs213.photoAlbum.gui.view;

import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;

import cs213.photoAlbum.control.Controller;
import cs213.photoAlbum.control.ProgramControl;
import cs213.photoAlbum.model.totalData;
import cs213.photoAlbum.simpleview.CmdView;

/**
 * allows the user to log in as an admin or a user
 * @author Risham Chokshi and Jasmine Feng
 */

public class login extends JFrame{
private static javax.swing.JLabel ErrorMessage;
private static javax.swing.JButton loginButton;
private static javax.swing.JLabel usernameLabel;
private static javax.swing.JTextField usernameText;
private static javax.swing.JLabel welcomeLabel;
private static JFrame me; 
public static interactiveView iv;
/**
 * the instance of the Controller
 */
static ProgramControl Controller;
// End of variables declaration 

/**
 * constructor
 */
public login(){
	init();
		
}

/**
 * called from the constructor to initialize the form.
 */
public void init(){
	 	
		loginButton = new javax.swing.JButton();
        welcomeLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        usernameText = new javax.swing.JTextField();
        ErrorMessage = new javax.swing.JLabel();
        Controller = new ProgramControl(totalData.users);
        
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
        setTitle("Login Page");
        setResizable(false);

        loginButton.setText("Log in");
        
        usernameText.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		ErrorMessage.setText("");
        	}
        });
        
        
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            	try {
					totalData.users = Controller.readUsersFromFile();
					CmdView.setup();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	if(usernameText.getText().equals("admin")){
            		setVisible(false);
            		JFrame Admin = new admin(me);
            		Admin.setVisible(true);
            		ErrorMessage.setText("");
            	}
            	
            	else{
            		 
            		//check if the username exists
            		int i;
            		for(i =0; totalData.users.isEmpty()!= true && i <totalData.users.size();i++){
            			if(usernameText.getText().equals(totalData.users.get(i).getID())){
            				setVisible(false);
            				iv = new interactiveView(totalData.users.get(i),Controller);
            				Controller.currentUser = totalData.users.get(i);
                    		JFrame Album = new album(me);
                    		Album.setVisible(true);
                    		ErrorMessage.setText("");
                    		break;
            			}
            		}
            		if(totalData.users.isEmpty() || i==totalData.users.size())
            		ErrorMessage.setText("Error: The username does not exists");
            		
            	}
            	
            }
        });

        welcomeLabel.setFont(new java.awt.Font("BatangChe", 1, 48)); // NOI18N
        welcomeLabel.setText("WELCOME");

        usernameLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        usernameLabel.setText("Username:");

        //ErrorMessage.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addComponent(usernameLabel)
                .addGap(42, 42, 42)
                .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(loginButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(welcomeLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(ErrorMessage)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(65, Short.MAX_VALUE)
                        .addComponent(welcomeLabel)
                        .addGap(111, 111, 111))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usernameLabel)
                            .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(loginButton)
                .addGap(39, 39, 39)
                .addComponent(ErrorMessage)
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
	
}
/**
 * the main
 * @param args
 */
public static void main(String args[]){
	JFrame Music = new music();
	Music.setVisible(true);
	JFrame Login = new login();
	me = Login;
	Login.setVisible(true);
	
}
}
