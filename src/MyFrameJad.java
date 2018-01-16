

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.JList;


public class MyFrameJad extends JFrame{
	String[][] friendsList = new String[10][2];			//max 10 friends
	static String currentUser;									//user that is logged in
	private JPanel mainPane;
	private JTextField user;
	private JPasswordField pass;
	private JButton btnLogin;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JButton LIB;
	private JButton btnSettings;
	private JButton Profile;
	private JButton btnChat;
	private JTextArea chatArea;
	private JPanel profilePane;
	private JTextField chatField;
	private JButton btnSend;
	private JTextArea textArea;
	private JButton btnProf;
	private JButton goat;
	private JButton dog;
	private JButton btnBackToMain;
	private JButton pig;
	private JButton horse;
	private JButton dragon;
	private JButton rooster;
	private JButton ox;
	private JButton rat;
	private JButton rabbit;
	private JButton monkey;
	private JButton tiger;
	private JButton snake;
	public int lvlcounter = 0;
	 public static Socket socket;
     public static BufferedReader datain;
     public static DataOutputStream dataout;
     public static chatBox[] chats = new chatBox[10];
     private static int chatBoxCounter = -1;
	
	
	private CardLayout myLayout;
	
	private JPanel myPanel;
	private JPanel myHome;
	private JTextField textField;
	private JButton btnAddfriend;
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrameJad frame = new MyFrameJad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyFrameJad() {
		setTitle("Log In");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 450);
		
		
		
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		mainPane.setLayout(new CardLayout(0, 0));
		myLayout = (CardLayout) mainPane.getLayout();
		
		
		
		
		
		myPanel = new JPanel();
		myPanel.setLayout(null);
		mainPane.add(myPanel,"firstPanel");
		
		
		myHome = new JPanel();
		myHome.setLayout(null);
		mainPane.add(myHome,"homePanel");
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(116, 130, 89, 23);
		myHome.add(btnExit);
		
		textField = new JTextField();
		textField.setBounds(48, 197, 86, 20);
		myHome.add(textField);
		textField.setColumns(10);
		
		btnAddfriend = new JButton("Addfriend");
		btnAddfriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newfriend = new String(textField.getText());
				Client.addFriend(newfriend, currentUser);
			}
		});
		btnAddfriend.setBounds(152, 196, 89, 23);
		myHome.add(btnAddfriend);
		myPanel.setBackground(new Color(0, 128, 128));
		btnExit.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				Client.Exit(currentUser);
				dispose();
			}
			
		});
		
		myLayout.show(mainPane, "firstPanel");
		lblUsername = new JLabel();
		lblUsername.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 15));
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(29, 163, 103, 50);
		lblUsername.setText("Username:");
		myPanel.add(lblUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 15));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(29, 217, 200, 50);
		myPanel.add(lblPassword);
		
		user = new JTextField();
		user.setBounds(124, 174, 200, 32);
		myPanel.add(user);
		user.setColumns(10);
		user.setBorder(BorderFactory.createEmptyBorder());
		user.setBackground(Color.WHITE);
		
		pass = new JPasswordField();
		pass.setBounds(124, 227, 200, 34);
		myPanel.add(pass);
		pass.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentUser = user.getText();
				char[] password = pass.getPassword();
				try{
				socket = new Socket("localhost",6789);
				datain = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				dataout = new DataOutputStream(socket.getOutputStream());
				boolean response = Client.Authenticate(currentUser, password);
				if(response) {
					if(!Client.isLoggedin){
					JOptionPane.showMessageDialog(null, "Welcome " + currentUser);
					myLayout.show(mainPane, "homePanel");
					initComponentsMM();
					createEventsMM();
													//create new string stored in myframe
					
					clientThread thread = new clientThread();
					new Thread(thread).start();							//thread responsible for reading from server
					}																	
					else
						JOptionPane.showMessageDialog(null, "User is already online");
						
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Username or Password");
			}
				}
				catch(Exception e){
					
				}
				
				
				
			}
		});
		btnLogin.setBounds(244, 278, 80, 23);
		myPanel.add(btnLogin);
		
		JButton btnNewButton = new JButton("Sign up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			signup signupframe = new signup();
				signupframe.NewScreen();
			}
		});
		
		btnNewButton.setBounds(124, 278, 80, 23);
		myPanel.add(btnNewButton);
	    	setBounds(100, 100, 800, 700);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(0, 128, 128));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			mainPane.add(contentPane, "MainMenuPanel");
		mainPane.add(contentPane,"ProfilePane" );
			
		
			
			
			
			
			
			
			
			
			
			
			
			
			
	}
	private void initComponentsPROF(){
		setTitle("TITLE");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProfileFrame.class.getResource("/Project/sources/Gg-64.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		btnProf = new JButton("");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnProf, 69, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnProf, -438, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnProf, -600, SpringLayout.EAST, contentPane);

		btnProf.setBackground(Color.LIGHT_GRAY);
		
		btnProf.setIcon(new ImageIcon(ProfileFrame.class.getResource("/Project/sources/avatar.png")));
		contentPane.add(btnProf);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(btnProf, popupMenu);
		
		goat = new JButton("  Goat  ");
		horse = new JButton("  Horse ");
		tiger = new JButton("  Tiger ");
		rooster = new JButton("Rooster");
		ox = new JButton("    Ox     ");
		rabbit = new JButton(" Rabbit ");
		pig = new JButton("    Pig   ");                                                                                         
		monkey = new JButton("Monkey");
		rat = new JButton("    Rat   ");
		dragon = new JButton(" Dragon");
		snake = new JButton(" Snake ");
		
		dog = new JButton("   Dog  ");
		popupMenu.add(horse);
		popupMenu.add(dog);
		popupMenu.add(goat);
		popupMenu.add(snake);
		popupMenu.add(pig);
		popupMenu.add(rat);
		popupMenu.add(rabbit);
		popupMenu.add(ox);
		popupMenu.add(dragon);
		popupMenu.add(rooster);
		popupMenu.add(monkey);
		popupMenu.add(tiger);
		
		btnBackToMain = new JButton("Back to Main Menu");
		btnBackToMain.setForeground(Color.WHITE);
		btnBackToMain.setBackground(Color.DARK_GRAY);
		btnBackToMain.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 10));
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnProf, 38, SpringLayout.SOUTH, btnBackToMain);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnBackToMain, 285, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnBackToMain, -589, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnBackToMain, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnBackToMain, 66, SpringLayout.WEST, contentPane);
		contentPane.add(btnBackToMain);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(0);
		sl_contentPane.putConstraint(SpringLayout.NORTH, progressBar, 53, SpringLayout.SOUTH, btnProf);
		sl_contentPane.putConstraint(SpringLayout.WEST, progressBar, 0, SpringLayout.WEST, btnProf);
		contentPane.add(progressBar);
		
		JLabel lblLevel = new JLabel("Level: " + lvlcounter);
		lblLevel.setForeground(Color.WHITE);
		lblLevel.setBackground(Color.BLACK);
		lblLevel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		sl_contentPane.putConstraint(SpringLayout.WEST, lblLevel, 70, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblLevel, -6, SpringLayout.NORTH, progressBar);
		contentPane.add(lblLevel);
		
		JButton tester = new JButton("+");
		tester.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent arg0) {
				progressBar.setValue(progressBar.getValue() + 10);
				if(progressBar.getValue()== 100)
				{ progressBar.setValue(0);
				lvlcounter++;
				lblLevel.setText("Level: " + lvlcounter);
				}
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, tester, 0, SpringLayout.NORTH, progressBar);
		sl_contentPane.putConstraint(SpringLayout.WEST, tester, 16, SpringLayout.EAST, progressBar);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, tester, 25, SpringLayout.NORTH, progressBar);
		sl_contentPane.putConstraint(SpringLayout.EAST, tester, 58, SpringLayout.EAST, progressBar);
		contentPane.add(tester);
}

	private void addPopup(JButton btnProf2, JPopupMenu popupMenu) {
		// TODO Auto-generated method stub
		
	}

	private void initComponentsCHAT(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(chatBox.class.getResource("/Project/sources/Gg-64.png")));
			setTitle("Chat");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBackground(Color.DARK_GRAY);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			SpringLayout sl_contentPane = new SpringLayout();
			contentPane.setLayout(sl_contentPane);
			
			chatField = new JTextField();
			textField.setBackground(SystemColor.textHighlightText);
			sl_contentPane.putConstraint(SpringLayout.NORTH, textField, 211, SpringLayout.NORTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.WEST, textField, 10, SpringLayout.WEST, contentPane);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, textField, -10, SpringLayout.SOUTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.EAST, textField, -93, SpringLayout.EAST, contentPane);
			contentPane.add(textField);
			textField.setColumns(10);
			
			chatArea = new JTextArea();
			chatArea.setEditable(false);
			chatArea.setBackground(Color.LIGHT_GRAY);
			sl_contentPane.putConstraint(SpringLayout.NORTH, chatArea, 5, SpringLayout.NORTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.WEST, chatArea, 0, SpringLayout.WEST, textField);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, chatArea, -10, SpringLayout.NORTH, textField);
			sl_contentPane.putConstraint(SpringLayout.EAST, chatArea, 329, SpringLayout.WEST, contentPane);
			contentPane.add(chatArea);
			
			btnSend = new JButton("Send");
			sl_contentPane.putConstraint(SpringLayout.WEST, btnSend, 6, SpringLayout.EAST, textField);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSend, -10, SpringLayout.SOUTH, contentPane);
			contentPane.add(btnSend);
			

		
		}
	
	private void createEventsMM(){
		LIB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,"congrats!"); 
	
			}
		});
		Profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myLayout.show(mainPane, "ProfilePane");
				initComponentsPROF();
				createEventsPROF();
			
			}
		});
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		
		
		btnChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null, "Who do you wish to chat with?");
				//Client.chat(name, currentUser, "");
				chatBoxCounter++;
				chats[chatBoxCounter] = new chatBox(currentUser, name);
				chats[chatBoxCounter].setVisible(true);		
			
			}
		});
	}
	private void createEventsPROF(){
		goat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnProf.setIcon(new ImageIcon(ProfileFrame.class.getResource("/Project/sources/Year of Goat Filled.png")));
				
				
				
				
			}
		});
		dog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnProf.setIcon(new ImageIcon(ProfileFrame.class.getResource("/Project/sources/Year of Dog Filled-100.png")));
			
				
				
				
			}
		});
		snake.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					btnProf.setIcon(new ImageIcon(ProfileFrame.class.getResource("/Project/sources/Year of Snake Filled.png")));}
			});
		horse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnProf.setIcon(new ImageIcon(ProfileFrame.class.getResource("/Project/sources/Year of Horse Filled.png")));}
		});
		dragon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnProf.setIcon(new ImageIcon(ProfileFrame.class.getResource("/Project/sources/Year of Dragon Filled-100.png")));}
		});
		ox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnProf.setIcon(new ImageIcon(ProfileFrame.class.getResource("/Project/sources/Year of Ox Filled.png")));}
		});
		rabbit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnProf.setIcon(new ImageIcon(ProfileFrame.class.getResource("/Project/sources/Year of Rabbit Filled.png")));}
		});
		rat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnProf.setIcon(new ImageIcon(ProfileFrame.class.getResource("/Project/sources/Year of Rat Filled.png")));}
		});
		pig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnProf.setIcon(new ImageIcon(ProfileFrame.class.getResource("/Project/sources/Year of Pig Filled.png")));}
		});
		rooster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnProf.setIcon(new ImageIcon(ProfileFrame.class.getResource("/Project/sources/Year of Rooster Filled-100.png")));}
		});
		tiger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnProf.setIcon(new ImageIcon(ProfileFrame.class.getResource("/Project/sources/Year of Tiger Filled.png")));}
		});
		monkey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnProf.setIcon(new ImageIcon(ProfileFrame.class.getResource("/Project/sources/Year of Monkey Filled.png")));}
		});
		
		
		btnBackToMain.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mainPane.add(contentPane, "MainMenuPanel");
					initComponentsMM();
					createEventsMM();
				}

				
				});
	}
	
	private void initComponentsMM() {
		setDefaultCloseOperation(MainMenu.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		 LIB = new JButton(" Library ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, LIB, 88, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, LIB, 0, SpringLayout.WEST, contentPane);
		
		sl_contentPane.putConstraint(SpringLayout.EAST, LIB, -631, SpringLayout.EAST, contentPane);
		LIB.setIcon(new ImageIcon(MainMenu.class.getResource("/Project/sources/002-game.png")));
		LIB.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		LIB.setForeground(Color.WHITE);
		LIB.setBackground(Color.DARK_GRAY);
		contentPane.add(LIB);
		
		 btnSettings = new JButton("Settings");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSettings, 2, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSettings, -355, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnSettings, 0, SpringLayout.EAST, LIB);
		btnSettings.setIcon(new ImageIcon(MainMenu.class.getResource("/Project/sources/001-cogwheel.png")));
		btnSettings.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		btnSettings.setBackground(Color.DARK_GRAY);
		btnSettings.setForeground(Color.WHITE);
		contentPane.add(btnSettings);
		
		 Profile = new JButton("Profile");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnSettings, 23, SpringLayout.SOUTH, Profile);
		sl_contentPane.putConstraint(SpringLayout.NORTH, Profile, 40, SpringLayout.SOUTH, LIB);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, Profile, -426, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, Profile, 0, SpringLayout.WEST, LIB);
		sl_contentPane.putConstraint(SpringLayout.EAST, Profile, -2, SpringLayout.EAST, LIB);
		
		Profile.setIcon(new ImageIcon(MainMenu.class.getResource("/Project/sources/avatar.png")));
		Profile.setBackground(Color.DARK_GRAY);
		Profile.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		Profile.setForeground(Color.WHITE);

		contentPane.add(Profile);
		
				
				 btnChat = new JButton("Chat");
				sl_contentPane.putConstraint(SpringLayout.SOUTH, btnChat, -62, SpringLayout.SOUTH, contentPane);
				sl_contentPane.putConstraint(SpringLayout.EAST, btnChat, -44, SpringLayout.EAST, contentPane);
				
				btnChat.setIcon(new ImageIcon(MainMenu.class.getResource("/Project/sources/Speech Bubble-50.png")));
				btnChat.setBackground(Color.DARK_GRAY);
				btnChat.setForeground(Color.WHITE);
				btnChat.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
				contentPane.add(btnChat);
				
				JLabel lblWelcomeUser = new JLabel("Welcome " + currentUser);//username.getText());
				sl_contentPane.putConstraint(SpringLayout.NORTH, lblWelcomeUser, 15, SpringLayout.NORTH, contentPane);
				sl_contentPane.putConstraint(SpringLayout.WEST, lblWelcomeUser, 15, SpringLayout.WEST, contentPane);
				sl_contentPane.putConstraint(SpringLayout.SOUTH, lblWelcomeUser, 38, SpringLayout.NORTH, contentPane);
				sl_contentPane.putConstraint(SpringLayout.EAST, lblWelcomeUser, 112, SpringLayout.WEST, contentPane);
				lblWelcomeUser.setForeground(SystemColor.text);
				lblWelcomeUser.setFont(new Font("Arial Narrow", Font.BOLD, 15));
				contentPane.add(lblWelcomeUser);
				

					setTitle("TITLE");
					setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenu.class.getResource("/Project/sources/Gg-64.png")));
						
	}
	
	
	
	public static void newConnection(BufferedReader inFromServer, DataOutputStream outToServer){
		try{
		Socket clientSocket = new Socket("localhost",6789);
		
		 inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		 outToServer = new DataOutputStream(clientSocket.getOutputStream());
		}
		catch(Exception e){
			
		}
	}
	
	public static void newFriend(String friend){						//should show dialogue box for confirming/rejecting
											
	}
	
	public static void executeCmd(String[] cmd){						//executes splitServerCmd[0]
		System.out.println("yooyo");
		for(int j = 0; j < cmd.length; j++)
			System.out.println(cmd[j]);
		if(cmd[0].equals("OKfriend")){
			JOptionPane.showMessageDialog(null, "Friend Request to user " + cmd[1] + " successfully sent");
		}
		else if(cmd[0].equals("NOfriend")){
			JOptionPane.showMessageDialog(null, "User does not exist");
		}
		else if(cmd[0].equals("newf"))
			JOptionPane.showMessageDialog(null, "friend request from " + cmd[1]);
		else if(cmd[0].equals("chat")){
			boolean found = false;
			int i = 0;
			while ((i <= chatBoxCounter) && !found){
								//cycle through all active chats
			if(chats[i].chattingWith.equals(cmd[1])){		
			
				if(!chats[i].isVisible())
					chats[i].setVisible(true);
				chats[i].receiveMessage(cmd);
				System.out.println("yeboy");
				found = true;
			}
			i++;
			}
			if(!found){
			chatBoxCounter++;
			chats[chatBoxCounter] = new chatBox(currentUser, cmd[1]);		//some user is chatting with you
			chats[chatBoxCounter].setVisible(true);							//cmd[1] name of user chatting with you
			chats[chatBoxCounter].receiveMessage(cmd);
			}																//cmd[2] and above message
		}
		else if(cmd[0].equals("msgnotsent"))
			JOptionPane.showMessageDialog(null, "user not online");
		else if(cmd[0].equals("NOfriendlist"))
			JOptionPane.showMessageDialog(null, "user not in friends list");
	}
	
	
	public class clientThread implements Runnable{
	
		public clientThread(){
			
		}
	public void run(){
		String serverCmd;
		String[] splitServerCmd;
		try {
			while((serverCmd = datain.readLine())!=null){
				System.out.println(serverCmd);
				splitServerCmd = serverCmd.split(" ");
				executeCmd(splitServerCmd);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
}