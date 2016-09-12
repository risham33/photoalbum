package cs213.photoAlbum.gui.view;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;

import cs213.photoAlbum.model.totalData;


public class music extends JFrame{
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton next;
    private javax.swing.JButton play;
    private javax.swing.JButton prev;
    private javax.swing.JButton stop;
    private javax.swing.JLabel name;
    String [] songs;
	int current; 
	AudioClip[] clip;
    URL []url;
    int playint;//0 if its not playing, and 1 if it is
    public music() {
        initComponents();
    }
	
	
	
	
	 private void initComponents() {
		 	songs = new String [5];
		 	current = -1;//not started yet
		 	playint = 0;
		 	clip = new AudioClip[5]; //nothing in, just declaring
		 	url = new URL[5];
		 	initsongs();
	        jLabel1 = new javax.swing.JLabel();
	        prev = new javax.swing.JButton();
	        next = new javax.swing.JButton();
	        stop = new javax.swing.JButton();
	        play = new javax.swing.JButton();
	        name = new javax.swing.JLabel();
	        stop.setEnabled(false); next.setEnabled(false); prev.setEnabled(false);
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
	        setTitle("MUSIC TIME!");
	        setResizable(false);

	        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
	        jLabel1.setText("Play music?");

	        prev.setText("<<Prev");

	        next.setText("Next>>");

	        stop.setText("Stop :(");

	        play.setText("Play :)");
	        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addGap(18, 18, 18)
	                .addComponent(prev)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(play)
	                        .addGap(24, 24, 24)
	                        .addComponent(next))
	                    .addComponent(jLabel1)
	                    .addComponent(stop))
	                .addGap(23, 23, 23))
	            .addGroup(layout.createSequentialGroup()
	                .addGap(52, 52, 52)
	                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel1)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addComponent(play)
	                        .addGap(18, 18, 18)
	                        .addComponent(stop))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(next)
	                            .addComponent(prev))
	                        .addGap(29, 29, 29))))
	        );

	        pack();
	        
	        
	        play.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	if(current==-1){
	            		current = 0; //start with first song 
	            	}
	            	//play now
	            	//try {
	            		
						/*url = new URL(songs[current]);
						if(clip!=null)
							clip.stop();
						clip = Applet.newAudioClip(url);
						*/
	            		clip[current].play();
	            		setName();
						stop.setEnabled(true); next.setEnabled(true); prev.setEnabled(true);
						play.setEnabled(false);
						playint = 1;
						
					//} catch (MalformedURLException e) {
						//System.out.println("couldnt play song");
						
					//}
	            	
	            }
	        });
	        
	        stop.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	
	            	//if(clip!=null){
	            	  if(playint == 1){
	            		clip[current].stop();
	            		name.setText("");
	            		//clip = null;
	            		playint = 0;
	            		stop.setEnabled(false); next.setEnabled(true); prev.setEnabled(true);
	            		play.setEnabled(true);
	            	}
	            	
	            }
	        });
	        
	        prev.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	if(playint==1){
	            		clip[current].stop();
						//clip = null;
						
					}
	            	if(current==0){
	            		current = 5;
	            	}
	            	current--; //going back
	            	
					
					//if(clip!=null){
					  
					//try {
	            		
						//url = new URL(songs[current]);
						//clip = Applet.newAudioClip(url);
	            	setName();
	            		clip[current].play();
						
						stop.setEnabled(true); next.setEnabled(true); prev.setEnabled(true);
						play.setEnabled(false);
						playint = 1;
					//} catch (MalformedURLException e) {
						//System.out.println("couldnt play song");
						//current ++;
						
					//}
	            	
	            }
	        });
	        
	        next.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	if(playint==1){
	            		clip[current].stop();
						//clip = null;
						
					}
	            	if(current==4){
	            		current = -1;
	            	}
	            	current++; //going up
	            	
					
					//if(clip!=null){
					  
					//try {
	            		
						// url = new URL(songs[current]);
						//clip = Applet.newAudioClip(url);
	            		setName();
	            		clip[current].play();
						
						stop.setEnabled(true); next.setEnabled(true); prev.setEnabled(true);
						play.setEnabled(false);
						playint = 1;
					//} catch (MalformedURLException e) {
						//System.out.println("couldnt play song");
						//current --;
						
					//}
	            	
	            }
	        });
	        
	        
	    }    

	 private void setName(){
		 
		 switch (current){
		 	
		 case 0:
			 name.setText("Song: Mockingbird");
			 break;
		 case 1:
			 name.setText("Song: Love the way you lie");
			 break;
		 case 2:
			 name.setText("Song: Sadi galli");
			 break;
		 case 3:
			 name.setText("Song: The way I are");
			 break;
		 case 4:
			 name.setText("Song: Numb");
			 break;
			default:
				name.setText("");
				break;
		  }
			 
	 }
	 
	 public void initsongs(){
		 
		 songs[0] = "file:/" + System.getProperty("user.dir") + "//music//"+ "mockingbird.wav";
		 songs[1] = "file:/" + System.getProperty("user.dir") + "//music//"+ "wayYouLie.wav";
		 songs[2] = "file:/" + System.getProperty("user.dir") + "//music//"+ "sadi.wav";
		 songs[3] = "file:/" + System.getProperty("user.dir") + "//music//"+ "wayIare.wav";
		 songs[4] = "file:/" + System.getProperty("user.dir") + "//music//"+ "numb.wav";
	
		 
		 
		 
		 for(int i =0; i <5; i++){
			songs[i] = songs[i].replace("\\", "//");
			 try {
				url[i] = new URL(songs[i]);
				clip[i] = Applet.newAudioClip(url[i]);
			} catch (MalformedURLException e) {
				
			}
			
		 }
		 
	 }
	 
	 
	
}
