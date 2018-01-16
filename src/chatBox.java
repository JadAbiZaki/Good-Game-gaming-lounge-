

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;
import javax.swing.SpringLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class chatBox extends JFrame {

	private static JPanel contentPane;
	private static JTextField textField;
	private static JButton btnSend;
	private static JTextPane textArea;
	public static String chattingWith = "";
	public static boolean active = false;
	public static String youAre = "";
	

	/**
	 * Launch the application.
	 */
	public static void chatBox() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chatBox frame = new chatBox();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void receiveMessage(String[] message){
		String s = textArea.getText();		
		if (s.equals("")){
			textArea.setText(textArea.getText() + chattingWith + ": ");
			for(int i = 3; i < message.length; i++){
				textArea.setText(textArea.getText() + message[i] + " ");
			}
		}
		else
		{
			textArea.setText(textArea.getText() + "\n"+ chattingWith + ": ");
			for(int i = 3; i < message.length; i++){
				textArea.setText(textArea.getText() + message[i] + " ");
			}
		}
		
	}
		
	
	public chatBox() {
	
		initComponents();
		createEvents();
		active = true;
	}
	public chatBox(String youAre,String chattingWith) {
		this.chattingWith = chattingWith;
		this.youAre = youAre;
		initComponents();
		createEvents();
		active = true;
		
	}
	
	private void createEvents(){
		this.getRootPane().setDefaultButton(btnSend);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String s = textArea.getText() ;
			String message = textField.getText();
			Client.chat(chattingWith, youAre, message);
			if (message.equals("")){}
			else{
			if ( s.equals("")){
				textArea.setText(textArea.getText() + "You: "+textField.getText() );
			}
			else
			{
				textArea.setText(textArea.getText() + "\nYou: "+textField.getText() );
				
			}
		textField.setText("");
		
			}	}});
		
		
		
		
	}
	private void initComponents(){
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
		
		textField = new JTextField();
		textField.setBackground(SystemColor.textHighlightText);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField, 211, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField, -93, SpringLayout.EAST, contentPane);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textArea = new JTextPane();
		textArea.setEditable(false);
		textArea.setBackground(Color.LIGHT_GRAY);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textArea, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textArea, 0, SpringLayout.WEST, textField);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textArea, -10, SpringLayout.NORTH, textField);
		sl_contentPane.putConstraint(SpringLayout.EAST, textArea, 329, SpringLayout.WEST, contentPane);
		contentPane.add(textArea);
		
		btnSend = new JButton("Send");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSend, 6, SpringLayout.EAST, textField);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSend, -10, SpringLayout.SOUTH, contentPane);
		contentPane.add(btnSend);
		

	
	}
}

