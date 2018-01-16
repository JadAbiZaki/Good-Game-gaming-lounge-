

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
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.JLayeredPane;


public class MyFrameJad2 extends JFrame{
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
	private JPopupMenu popupMenu;
	private JPanel MainMenu;
	private JButton btnPlaySolo3;
	public int lvlcounter = 0;
	 public static Socket socket;
     public static BufferedReader datain;
     public static DataOutputStream dataout;
	public String profilePic ;
	JLabel lblWelcomeUser;
	
	private CardLayout myLayout;
	private JPanel myPanel;
	private JPanel myHome;
	private JTextField textField;
	private JButton btnAddfriend;
	private JPanel MyProfile;
	private JPanel MyLibrary;
	private JButton button;
	private JButton btnSudoku;
	private JButton btnTictactoe;
	private JPanel sudokuPanel;
	private JPanel TTTPanel;
	private JButton btnPlayFriend;
	private JButton btnPlayGlobally;
	public static chatBox[] chats = new chatBox[10];
    private static int chatBoxCounter = -1;
    static JFrame currentGame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrameJad2 frame = new MyFrameJad2();
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
	public MyFrameJad2() {
		setResizable(false);
		setTitle("Log In");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 833, 732);
		
		
		
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
			//	Client.addFriend(newfriend, currentUser);
			}
		});
		btnAddfriend.setBounds(152, 196, 89, 23);
		myHome.add(btnAddfriend);
		myPanel.setBackground(new Color(0, 128, 128));
		btnExit.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
			//	Client.Exit(currentUser);
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
		user.setFont(new Font("Tahoma", Font.PLAIN, 15));
		user.setText("Username");
		user.setBounds(124, 174, 200, 32);
		myPanel.add(user);
		user.setColumns(10);
		user.setBorder(BorderFactory.createEmptyBorder());
		user.setBackground(new Color(0, 128, 128));
		
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
					//JOptionPane.showMessageDialog(null, "Welcome " + username);
					myLayout.show(mainPane, "MainMenu");
					lblWelcomeUser.setText("Welcome " + currentUser);
				//	initComponentsMM();
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
	
		
		
		
		
		
		////////////////////////////////////////////////////////////
		/////Main Menu			///////////////////////////////////
		//////////////////////////////////////////////////////////
		
		
		
		
		
		
		
		
		
		
		 MainMenu = new JPanel();
		mainPane.add(MainMenu, "MainMenu");
		
		
			

			setBounds(100, 100, 800, 700);
		
			MainMenu.setBackground(new Color(0, 128, 128));
			MainMenu.setBorder(new EmptyBorder(5, 5, 5, 5));

			SpringLayout sl_MainMenu = new SpringLayout();
			MainMenu.setLayout(sl_MainMenu);
			
			 LIB = new JButton(" Library ");
			 sl_MainMenu.putConstraint(SpringLayout.WEST, LIB, 0, SpringLayout.WEST, MainMenu);
			 sl_MainMenu.putConstraint(SpringLayout.SOUTH, LIB, -522, SpringLayout.SOUTH, MainMenu);
			 sl_MainMenu.putConstraint(SpringLayout.EAST, LIB, -631, SpringLayout.EAST, MainMenu);
			LIB.setIcon(new ImageIcon(MainMenu.class.getResource("/Project/sources/002-game.png")));
			LIB.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
			LIB.setForeground(Color.WHITE);
			LIB.setBackground(Color.DARK_GRAY);
			MainMenu.add(LIB);
			
			 btnSettings = new JButton("Settings");
			 sl_MainMenu.putConstraint(SpringLayout.WEST, btnSettings, 0, SpringLayout.WEST, LIB);
			 sl_MainMenu.putConstraint(SpringLayout.SOUTH, btnSettings, -402, SpringLayout.SOUTH, MainMenu);
			 sl_MainMenu.putConstraint(SpringLayout.EAST, btnSettings, 0, SpringLayout.EAST, LIB);
			btnSettings.setIcon(new ImageIcon(MainMenu.class.getResource("/Project/sources/001-cogwheel.png")));
			btnSettings.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
			btnSettings.setBackground(Color.DARK_GRAY);
			btnSettings.setForeground(Color.WHITE);
			MainMenu.add(btnSettings);
			
			 Profile = new JButton("Profile");
			 sl_MainMenu.putConstraint(SpringLayout.NORTH, btnSettings, 19, SpringLayout.SOUTH, Profile);
			 sl_MainMenu.putConstraint(SpringLayout.SOUTH, Profile, -462, SpringLayout.SOUTH, MainMenu);
			 sl_MainMenu.putConstraint(SpringLayout.EAST, Profile, 0, SpringLayout.EAST, LIB);
			 sl_MainMenu.putConstraint(SpringLayout.NORTH, Profile, 19, SpringLayout.SOUTH, LIB);
			 sl_MainMenu.putConstraint(SpringLayout.WEST, Profile, 0, SpringLayout.WEST, LIB);
			
			Profile.setIcon(new ImageIcon(MainMenu.class.getResource("/Project/sources/avatar.png")));
			Profile.setBackground(Color.DARK_GRAY);
			Profile.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
			Profile.setForeground(Color.WHITE);

			MainMenu.add(Profile);
			
					
					 btnChat = new JButton("Chat");
					sl_MainMenu.putConstraint(SpringLayout.SOUTH, btnChat, -62, SpringLayout.SOUTH, MainMenu);
					sl_MainMenu.putConstraint(SpringLayout.EAST, btnChat, -44, SpringLayout.EAST, MainMenu);
					
					btnChat.setIcon(new ImageIcon(MainMenu.class.getResource("/Project/sources/Speech Bubble-50.png")));
					btnChat.setBackground(Color.DARK_GRAY);
					btnChat.setForeground(Color.WHITE);
					btnChat.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
					MainMenu.add(btnChat);
					
					lblWelcomeUser = new JLabel("Welcome " + currentUser);//username.getText());
					sl_MainMenu.putConstraint(SpringLayout.NORTH, lblWelcomeUser, 15, SpringLayout.NORTH, MainMenu);
					sl_MainMenu.putConstraint(SpringLayout.WEST, lblWelcomeUser, 15, SpringLayout.WEST, MainMenu);
					sl_MainMenu.putConstraint(SpringLayout.SOUTH, lblWelcomeUser, 38, SpringLayout.NORTH, MainMenu);
					sl_MainMenu.putConstraint(SpringLayout.EAST, lblWelcomeUser, 112, SpringLayout.WEST, MainMenu);
					lblWelcomeUser.setForeground(SystemColor.text);
					lblWelcomeUser.setFont(new Font("Arial Narrow", Font.BOLD, 15));
					MainMenu.add(lblWelcomeUser);
					
				

						setTitle("TITLE");
						setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenu.class.getResource("/Project/sources/Gg-64.png")));
			
			
			createEventsMM();
			
			/////////////////////////////////////////////////////
			// Profile 			////////////////////////////////
			///////////////////////////////////////////////////
						
						
					MyProfile = new JPanel();
					mainPane.add(MyProfile, "MyProfile");
					MyProfile.setBackground(new Color(0, 128, 128));
					MyProfile.setBorder(new EmptyBorder(5, 5, 5, 5));
					
					SpringLayout sl_MyProfile = new SpringLayout();
					MyProfile.setLayout(sl_MyProfile);
					
					btnProf = new JButton("");
					sl_MyProfile.putConstraint(SpringLayout.WEST, btnProf, 10, SpringLayout.WEST, MyProfile);
					sl_MyProfile.putConstraint(SpringLayout.EAST, btnProf, -659, SpringLayout.EAST, MyProfile);
					MyProfile.add(btnProf);
					btnProf.setBackground(Color.LIGHT_GRAY);
					if ( profilePic == null)
					{btnProf.setIcon(new ImageIcon(ProfileFrame.class.getResource("/Project/sources/avatar.png")));}
					else{
					btnProf.setIcon(new ImageIcon(ProfileFrame.class.getResource(profilePic)));
					}
					
					
				   
					
					btnBackToMain = new JButton("");
					sl_MyProfile.putConstraint(SpringLayout.NORTH, btnProf, 34, SpringLayout.SOUTH, btnBackToMain);
					sl_MyProfile.putConstraint(SpringLayout.EAST, btnBackToMain, 120, SpringLayout.WEST, MyProfile);
					sl_MyProfile.putConstraint(SpringLayout.NORTH, btnBackToMain, 10, SpringLayout.NORTH, MyProfile);
					sl_MyProfile.putConstraint(SpringLayout.WEST, btnBackToMain, 10, SpringLayout.WEST, MyProfile);
					sl_MyProfile.putConstraint(SpringLayout.SOUTH, btnBackToMain, -589, SpringLayout.SOUTH, MyProfile);
					btnBackToMain.setForeground(Color.WHITE);
					btnBackToMain.setBackground(Color.DARK_GRAY);
					btnBackToMain.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 10));
					MyProfile.add(btnBackToMain);
					
					JProgressBar progressBar = new JProgressBar();
					sl_MyProfile.putConstraint(SpringLayout.NORTH, progressBar, 260, SpringLayout.NORTH, MyProfile);
					sl_MyProfile.putConstraint(SpringLayout.WEST, progressBar, 0, SpringLayout.WEST, btnProf);
					progressBar.setValue(0);
					
					popupMenu = new JPopupMenu();
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
					MyProfile.add(progressBar);
					
					JLabel lblLevel = new JLabel("Level: " + lvlcounter);
					sl_MyProfile.putConstraint(SpringLayout.SOUTH, btnProf, -30, SpringLayout.NORTH, lblLevel);
					sl_MyProfile.putConstraint(SpringLayout.WEST, lblLevel, 0, SpringLayout.WEST, btnProf);
					sl_MyProfile.putConstraint(SpringLayout.SOUTH, lblLevel, -6, SpringLayout.NORTH, progressBar);
					lblLevel.setForeground(Color.WHITE);
					lblLevel.setBackground(Color.BLACK);
					lblLevel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
					MyProfile.add(lblLevel);
					
						
			
			createEventsPROF();
			
			
			
			
			////////////////////////////////////////////////////////
			////// 	Library 		///////////////////////////////
			//////////////////////////////////////////////////////
			
			
			
			
			
			
					MyLibrary = new JPanel();
					MyLibrary.setBackground(new Color(0, 128, 128));
					mainPane.add(MyLibrary, "MyLibrary");
					SpringLayout sl_MyLibrary = new SpringLayout();
					MyLibrary.setLayout(sl_MyLibrary);
					
					JButton backtoMain = new JButton("");
					backtoMain.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							myLayout.show(mainPane, "MainMenu");
						}
					});
					backtoMain.setBackground(new Color(0, 128, 128));
					backtoMain.setIcon(new ImageIcon(MyFrameJad2.class.getResource("/Project/sources/Leftist.png")));
					sl_MyLibrary.putConstraint(SpringLayout.SOUTH, backtoMain, 62, SpringLayout.NORTH, MyLibrary);
					sl_MyLibrary.putConstraint(SpringLayout.NORTH, backtoMain, 25, SpringLayout.NORTH, MyLibrary);
					sl_MyLibrary.putConstraint(SpringLayout.WEST, backtoMain, 8, SpringLayout.WEST, MyLibrary);
					MyLibrary.add(backtoMain);
					backtoMain.setFocusPainted(false);
					backtoMain.setBorderPainted(false);
					JPanel ChessPanel = new JPanel();
					ChessPanel.setForeground(new Color(255, 0, 0));
					ChessPanel.setBackground(new Color(153, 0, 102));
					sl_MyLibrary.putConstraint(SpringLayout.WEST, ChessPanel, 260, SpringLayout.WEST, MyLibrary);
					sl_MyLibrary.putConstraint(SpringLayout.EAST, ChessPanel, 0, SpringLayout.EAST, MyLibrary);
					sl_MyLibrary.putConstraint(SpringLayout.NORTH, ChessPanel, 0, SpringLayout.NORTH, MyLibrary);
					sl_MyLibrary.putConstraint(SpringLayout.SOUTH, ChessPanel, 0, SpringLayout.SOUTH, MyLibrary);
					MyLibrary.add(ChessPanel);
					SpringLayout sl_ChessPanel = new SpringLayout();
					ChessPanel.setLayout(sl_ChessPanel);
					
					button = new JButton("Chess");
					sl_MyLibrary.putConstraint(SpringLayout.NORTH, button, 26, SpringLayout.SOUTH, backtoMain);
					sl_MyLibrary.putConstraint(SpringLayout.WEST, button, 8, SpringLayout.WEST, MyLibrary);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ChessPanel.setVisible(true);
							TTTPanel.setVisible(false);
							sudokuPanel.setVisible(false);
							
						}
					});
					button.setHorizontalAlignment(SwingConstants.LEFT);
					button.setForeground(Color.WHITE);
					button.setFont(new Font("Tahoma", Font.PLAIN, 19));
					button.setBackground(new Color(0, 128, 128));
					MyLibrary.add(button);
					
					btnSudoku = new JButton("Sudoku");
					sl_MyLibrary.putConstraint(SpringLayout.SOUTH, button, -6, SpringLayout.NORTH, btnSudoku);
					sl_MyLibrary.putConstraint(SpringLayout.NORTH, btnSudoku, 131, SpringLayout.NORTH, MyLibrary);
					sl_MyLibrary.putConstraint(SpringLayout.WEST, btnSudoku, 8, SpringLayout.WEST, MyLibrary);
					sl_MyLibrary.putConstraint(SpringLayout.SOUTH, btnSudoku, -487, SpringLayout.SOUTH, MyLibrary);
					btnSudoku.setBorderPainted(false);
					btnSudoku.setFocusPainted(false);
					btnSudoku.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ChessPanel.setVisible(false);
							sudokuPanel.setVisible(true);
							TTTPanel.setVisible(false);
							
						}
					});
					btnSudoku.setHorizontalAlignment(SwingConstants.LEFT);
					btnSudoku.setForeground(Color.WHITE);
					btnSudoku.setFont(new Font("Tahoma", Font.PLAIN, 19));
					btnSudoku.setBackground(new Color(0, 128, 128));
					MyLibrary.add(btnSudoku);
					
					btnTictactoe = new JButton("Tic-Tac-Toe");
					sl_MyLibrary.putConstraint(SpringLayout.NORTH, btnTictactoe, 6, SpringLayout.SOUTH, btnSudoku);
					sl_MyLibrary.putConstraint(SpringLayout.WEST, btnTictactoe, 8, SpringLayout.WEST, MyLibrary);
					sl_MyLibrary.putConstraint(SpringLayout.SOUTH, btnTictactoe, -444, SpringLayout.SOUTH, MyLibrary);
					sl_MyLibrary.putConstraint(SpringLayout.EAST, btnTictactoe, 0, SpringLayout.EAST, button);
					btnTictactoe.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							TTTPanel.setVisible(true);
							sudokuPanel.setVisible(false);
							ChessPanel.setVisible(false);
							
						}
					});
					btnTictactoe.setBorderPainted(false);
					btnTictactoe.setFocusPainted(false);
					
					JLabel label = new JLabel("");
					label.setHorizontalAlignment(SwingConstants.CENTER);
					sl_ChessPanel.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, ChessPanel);
					sl_ChessPanel.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, ChessPanel);
					sl_ChessPanel.putConstraint(SpringLayout.SOUTH, label, 428, SpringLayout.NORTH, ChessPanel);
					sl_ChessPanel.putConstraint(SpringLayout.EAST, label, 0, SpringLayout.EAST, ChessPanel);
					label.setIcon(new ImageIcon(MyFrameJad2.class.getResource("/Project/sources/library_chess.png")));
					ChessPanel.add(label);
					btnTictactoe.setHorizontalAlignment(SwingConstants.LEFT);
					btnTictactoe.setForeground(Color.WHITE);
					btnTictactoe.setFont(new Font("Tahoma", Font.PLAIN, 19));
					btnTictactoe.setBackground(new Color(0, 128, 128));
					MyLibrary.add(btnTictactoe);
			
					button.setFocusPainted(false);
					button.setBorderPainted(false);
					
					sudokuPanel = new JPanel();
					sl_MyLibrary.putConstraint(SpringLayout.EAST, backtoMain, -180, SpringLayout.WEST, sudokuPanel);
					sl_MyLibrary.putConstraint(SpringLayout.EAST, btnSudoku, -103, SpringLayout.WEST, sudokuPanel);
					sl_MyLibrary.putConstraint(SpringLayout.EAST, button, -103, SpringLayout.WEST, sudokuPanel);
					sudokuPanel.setBackground(new Color(153, 0, 102));
					MyLibrary.add(sudokuPanel);
					sl_MyLibrary.putConstraint(SpringLayout.WEST, sudokuPanel, 260, SpringLayout.WEST, MyLibrary);
					sl_MyLibrary.putConstraint(SpringLayout.EAST, sudokuPanel, 0, SpringLayout.EAST, MyLibrary);
					sl_MyLibrary.putConstraint(SpringLayout.NORTH, sudokuPanel, 0, SpringLayout.NORTH, MyLibrary);
					sl_MyLibrary.putConstraint(SpringLayout.SOUTH, sudokuPanel, 0, SpringLayout.SOUTH, MyLibrary);
					
					SpringLayout SudokuLayout = new SpringLayout();
					sudokuPanel.setLayout(SudokuLayout);
					JLabel SudokuPic = new JLabel("");
					SudokuPic.setHorizontalAlignment(SwingConstants.CENTER);
					SudokuLayout.putConstraint(SpringLayout.NORTH, SudokuPic, 37, SpringLayout.NORTH, sudokuPanel);
					SudokuLayout.putConstraint(SpringLayout.WEST, SudokuPic, 10, SpringLayout.WEST, sudokuPanel);
					SudokuLayout.putConstraint(SpringLayout.SOUTH, SudokuPic, 455, SpringLayout.NORTH, sudokuPanel);
					SudokuLayout.putConstraint(SpringLayout.EAST, SudokuPic, 10, SpringLayout.EAST, sudokuPanel);
					SudokuPic.setIcon(new ImageIcon(MyFrameJad2.class.getResource("/Project/sources/sudoku500.png")));
					sudokuPanel.add(SudokuPic);
					
					TTTPanel = new JPanel();
					sl_MyLibrary.putConstraint(SpringLayout.WEST, TTTPanel, 103, SpringLayout.EAST, button);
					sl_MyLibrary.putConstraint(SpringLayout.SOUTH, TTTPanel, -240, SpringLayout.SOUTH, MyLibrary);
					sl_MyLibrary.putConstraint(SpringLayout.EAST, TTTPanel, 0, SpringLayout.EAST, MyLibrary);
					
					btnPlayFriend = new JButton("Play Friends");
					btnPlayFriend.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							String name = JOptionPane.showInputDialog(null, "Who do you wish to challenge?");
							Client.challenge(currentUser, name, "chess");
						
						}
					});
					sl_ChessPanel.putConstraint(SpringLayout.NORTH, btnPlayFriend, 573, SpringLayout.NORTH, ChessPanel);
					sl_ChessPanel.putConstraint(SpringLayout.SOUTH, btnPlayFriend, -27, SpringLayout.SOUTH, ChessPanel);
					
					btnPlayFriend.setFocusPainted(false);
					sl_ChessPanel.putConstraint(SpringLayout.WEST, btnPlayFriend, 63, SpringLayout.WEST, ChessPanel);
					btnPlayFriend.setBackground(Color.DARK_GRAY);
					btnPlayFriend.setForeground(new Color(255, 255, 255));
					btnPlayFriend.setFont(new Font("Tahoma", Font.BOLD, 16));
					ChessPanel.add(btnPlayFriend);
					
					btnPlayGlobally = new JButton("Play Globally");
					btnPlayGlobally.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							
						}
					});
					sl_ChessPanel.putConstraint(SpringLayout.NORTH, btnPlayGlobally, 573, SpringLayout.NORTH, ChessPanel);
					sl_ChessPanel.putConstraint(SpringLayout.SOUTH, btnPlayGlobally, -27, SpringLayout.SOUTH, ChessPanel);
					btnPlayGlobally.setFocusPainted(false);
					sl_ChessPanel.putConstraint(SpringLayout.EAST, btnPlayFriend, -103, SpringLayout.WEST, btnPlayGlobally);
					sl_ChessPanel.putConstraint(SpringLayout.WEST, btnPlayGlobally, 316, SpringLayout.WEST, ChessPanel);
					sl_ChessPanel.putConstraint(SpringLayout.EAST, btnPlayGlobally, -58, SpringLayout.EAST, ChessPanel);
					btnPlayGlobally.setForeground(Color.WHITE);
					btnPlayGlobally.setFont(new Font("Tahoma", Font.BOLD, 16));
					btnPlayGlobally.setBackground(Color.DARK_GRAY);
					ChessPanel.add(btnPlayGlobally);
					MyLibrary.add(TTTPanel);
					TTTPanel.setBackground(new Color(153, 0, 102));
					SpringLayout TTTLayout = new SpringLayout();
					TTTPanel.setLayout(TTTLayout);
					sl_MyLibrary.putConstraint(SpringLayout.EAST, TTTPanel, 0, SpringLayout.EAST, MyLibrary);
					sl_MyLibrary.putConstraint(SpringLayout.NORTH, TTTPanel, 0, SpringLayout.NORTH, MyLibrary);
					sl_MyLibrary.putConstraint(SpringLayout.SOUTH, TTTPanel, 0, SpringLayout.SOUTH, MyLibrary);
					
					JLabel TTTPic = new JLabel("");
					TTTPic.setHorizontalAlignment(SwingConstants.CENTER);
					TTTLayout.putConstraint(SpringLayout.NORTH, TTTPic, 37, SpringLayout.NORTH, TTTPanel);
					TTTLayout.putConstraint(SpringLayout.WEST, TTTPic, 10, SpringLayout.WEST, TTTPanel);
					TTTLayout.putConstraint(SpringLayout.SOUTH, TTTPic, 455, SpringLayout.NORTH, TTTPanel);
					TTTLayout.putConstraint(SpringLayout.EAST, TTTPic, 10, SpringLayout.EAST, TTTPanel);
					TTTPic.setIcon(new ImageIcon(MyFrameJad2.class.getResource("/Project/sources/TTTnew.png")));
					TTTPanel.add(TTTPic);
			
					JButton btnPlaySolo = new JButton("Play Friends");
					
					btnPlaySolo.setFocusPainted(false);
					
					SudokuLayout.putConstraint(SpringLayout.NORTH, btnPlaySolo, 573, SpringLayout.NORTH, sudokuPanel);
					SudokuLayout.putConstraint(SpringLayout.WEST, btnPlaySolo, 63, SpringLayout.WEST, sudokuPanel);
					SudokuLayout.putConstraint(SpringLayout.SOUTH, btnPlaySolo, -27, SpringLayout.SOUTH, sudokuPanel);
					
					btnPlaySolo.setBackground(Color.DARK_GRAY);
					btnPlaySolo.setForeground(new Color(255, 255, 255));
					btnPlaySolo.setFont(new Font("Tahoma", Font.BOLD, 16));
					sudokuPanel.add(btnPlaySolo);
					
					
					JButton btnPlayGlobally2 = new JButton("Play Globally");
					SudokuLayout.putConstraint(SpringLayout.NORTH, btnPlayGlobally2, 573, SpringLayout.NORTH, sudokuPanel);
					SudokuLayout.putConstraint(SpringLayout.SOUTH, btnPlayGlobally2, -27, SpringLayout.SOUTH, sudokuPanel);
					btnPlayGlobally2.setFocusPainted(false);
					
					SudokuLayout.putConstraint(SpringLayout.WEST, btnPlayGlobally2, 316, SpringLayout.WEST, sudokuPanel);
					SudokuLayout.putConstraint(SpringLayout.EAST, btnPlayGlobally2, -58, SpringLayout.EAST, sudokuPanel);
					btnPlayGlobally2.setForeground(Color.WHITE);
					btnPlayGlobally2.setFont(new Font("Tahoma", Font.BOLD, 16));
					btnPlayGlobally2.setBackground(Color.DARK_GRAY);
					sudokuPanel.add(btnPlayGlobally2);
					SudokuLayout.putConstraint(SpringLayout.EAST, btnPlaySolo, -103, SpringLayout.WEST, btnPlayGlobally2);
					
			     
					btnPlaySolo3 = new JButton("Play Friends");
					
					btnPlaySolo3.setFocusPainted(false);
					
					TTTLayout.putConstraint(SpringLayout.NORTH, btnPlaySolo3, 573, SpringLayout.NORTH, TTTPanel);
					TTTLayout.putConstraint(SpringLayout.WEST, btnPlaySolo3, 63, SpringLayout.WEST, TTTPanel);
					TTTLayout.putConstraint(SpringLayout.SOUTH, btnPlaySolo3, -27, SpringLayout.SOUTH, TTTPanel);
					
					btnPlaySolo3.setBackground(Color.DARK_GRAY);
					btnPlaySolo3.setForeground(new Color(255, 255, 255));
					btnPlaySolo3.setFont(new Font("Tahoma", Font.BOLD, 16));
					TTTPanel.add(btnPlaySolo3);
					
					
					JButton btnPlayGlobally3 = new JButton("Play Globally");
					TTTLayout.putConstraint(SpringLayout.NORTH, btnPlayGlobally3, 573, SpringLayout.NORTH, TTTPanel);
					TTTLayout.putConstraint(SpringLayout.SOUTH, btnPlayGlobally3, -27, SpringLayout.SOUTH, TTTPanel);
					btnPlayGlobally3.setFocusPainted(false);
					
					TTTLayout.putConstraint(SpringLayout.WEST, btnPlayGlobally3, 316, SpringLayout.WEST, TTTPanel);
					TTTLayout.putConstraint(SpringLayout.EAST, btnPlayGlobally3, -58, SpringLayout.EAST, TTTPanel);
					btnPlayGlobally3.setForeground(Color.WHITE);
					btnPlayGlobally3.setFont(new Font("Tahoma", Font.BOLD, 16));
					btnPlayGlobally3.setBackground(Color.DARK_GRAY);
					TTTPanel.add(btnPlayGlobally3);
					TTTLayout.putConstraint(SpringLayout.EAST, btnPlaySolo3, -103, SpringLayout.WEST, btnPlayGlobally3);
					
					
					btnPlaySolo3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String name = JOptionPane.showInputDialog(null, "Who do you wish to challenge?");
							Client.challenge(currentUser, name, "TTT");
						
						}
					});
				
					btnPlaySolo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String name = JOptionPane.showInputDialog(null, "Who do you wish to challenge?");
							Client.challenge(currentUser, name, "sudoku");
							
						}
					});
					
					
					
					
					
					
					this.getRootPane().setDefaultButton(btnLogin);				
			
					
					createEventsLIB();	
					
					
					
					
					
					
					
					
					
					
	}
	



	
	private void createEventsMM(){
		LIB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				myLayout.show(mainPane, "MyLibrary");
				
			}
		});
		Profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myLayout.show(mainPane, "MyProfile");
				
			
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
	
	private void createEventsLIB(){
		
		
		
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
					profilePic = "/Project/sources/Year of Snake Filled.png";
					btnProf.setIcon(new ImageIcon(ProfileFrame.class.getResource("/Project/sources/Year of Snake Filled.png")));
					}
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
					myLayout.show(mainPane, "MainMenu");
				

				}
				});
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
		else if(cmd[0].equals("challsent"))
			JOptionPane.showMessageDialog(null, "waiting for response");		
		else if(cmd[0].equals("gamechall")){
			int reply = JOptionPane.showConfirmDialog(null, cmd[1] + " has challenged you to a game of " + cmd[2], "challenge", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION)			
				Client.respondChallenge(cmd[1], currentUser, cmd[2], true);			
			else
				Client.respondChallenge(cmd[1], currentUser, cmd[2], false);
		}
		else if(cmd[0].equals("response")){
			if(cmd[1].equals("rej"))
				JOptionPane.showMessageDialog(null, "game challenge rejected");
			else if(cmd[1].equals("acc")){
				String gameserverID = cmd[3];
				JOptionPane.showMessageDialog(null, "game challenge accepted");
				if(cmd[2].equals("TTT")){
				currentGame = new TicTacToeFrame("X", true, gameserverID);
				currentGame.setVisible(true);
				currentGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				else if(cmd[2].equals("chess")){
					currentGame = new jFrameChessLan(true, true, gameserverID);
					currentGame.setVisible(true);
					currentGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				else if(cmd[2].equals("sudoku")){
					currentGame = new jFramesudoku();
					currentGame.setVisible(true);
					currentGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}
		}
		else if(cmd[0].equals("gameserverID")){
			String gameserverID = cmd[2];
			if(cmd[1].equals("TTT")){
				currentGame = new TicTacToeFrame("O", true, gameserverID);
				currentGame.setVisible(true);
				currentGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
			else if(cmd[1].equals("chess")){
				currentGame = new jFrameChessLan(true, false, gameserverID);
				currentGame.setVisible(true);
				currentGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
			else if(cmd[1].equals("sudoku")){
				currentGame = new jFramesudoku();
				currentGame.setVisible(true);
				currentGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		}
		else if(cmd[0].equals("gamemove"))
			if (cmd[1].equals("TTT"))
				((TicTacToeFrame) currentGame).receiveMove(cmd[2], cmd[3]);
			else if(cmd[1].equals("chess"))
				((jFrameChessLan) currentGame).receiveMove(cmd[2],cmd[3],cmd[4],cmd[5],cmd[6]);
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
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	public JPanel getSudokuPanel() {
		return sudokuPanel;
	}
}