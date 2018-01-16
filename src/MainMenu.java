

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class MainMenu extends javax.swing.JFrame {

	public JPanel contentPane;
	private JButton LIB;
	private JButton Profile;
	private JButton btnSettings;
	public String currentUser;
	public static Socket socket;
    public static BufferedReader datain;
    public static DataOutputStream dataout;
	
	private JButton btnChat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		
		initComponents();
		createEvents();
		
		
	}
	
	private void createEvents() {
		LIB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		//	JOptionPane.showMessageDialog(null,"congrats!"); TESTING
	
			}
		});
		Profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfileFrame Prof = new ProfileFrame();
				Prof.currentUser = currentUser;
				Prof.setVisible(true);
				setVisible(false);
			
			}
		});
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		
		
		btnChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chatBox Chat = new chatBox();
				Chat.setVisible(true);
			
			}
		});
	}

	private void initComponents() {
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
				
				JLabel lblWelcomeUser = new JLabel("Welcome " );//username.getText());
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
	private class WindowHandler implements WindowListener{

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			JOptionPane.showMessageDialog(null, "YES");
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static void executeCmd(String[] cmd){						//executes splitServerCmd[0]
		if(cmd[0].equals("OKfriend")){
			JOptionPane.showMessageDialog(null, "Friend Request to user " + cmd[1] + " successfully sent");
		}
		else if(cmd[0].equals("NOfriend")){
			JOptionPane.showMessageDialog(null, "User does not exist");
		}
		else if(cmd[0].equals("newf"))
			JOptionPane.showMessageDialog(null, "friend request from " + cmd[1]);
		else if(cmd[0].equals("chat")){
																//some user is chatting with you
																//cmd[1] name of user chatting with you
																//cmd[2] and above message
		}
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