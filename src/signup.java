import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class signup extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
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
	public signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.blue);
		
		JLabel lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblEmailAddress.setBounds(31, 68, 130, 18);
		contentPane.add(lblEmailAddress);
				
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblUsername.setBounds(31, 97, 108, 20);
		contentPane.add(lblUsername);
		lblUsername.setBackground(Color.decode("0xff3300"));
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblPassword.setBounds(31, 128, 108, 19);
		contentPane.add(lblPassword);
		
		JLabel lblReenterPassword = new JLabel("Re-enter Password");
		lblReenterPassword.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblReenterPassword.setBounds(31, 158, 167, 20);
		contentPane.add(lblReenterPassword);
		
		JLabel lblTheTwoPasswords = new JLabel("The two passwords do not match");
		lblTheTwoPasswords.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblTheTwoPasswords.setBounds(214, 223, 157, 14);
		contentPane.add(lblTheTwoPasswords);
		lblTheTwoPasswords.setVisible(false);
		
		JLabel lblshortpass = new JLabel("Password should be at least eight characters");
		lblshortpass.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblshortpass.setBounds(214, 223, 195, 14);
		contentPane.add(lblshortpass);
		lblshortpass.setVisible(false);
		
		textField_1 = new JTextField();
		textField_1.setBounds(218, 67, 180, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.decode("0xff3300"));
		
		textField = new JTextField();
		textField.setBounds(218, 97, 178, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setBackground(Color.decode("0xff3300"));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(218, 127, 178, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(218, 158, 178, 20);
		contentPane.add(passwordField_1);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblTheTwoPasswords.setVisible(false);
				String username = textField.getText();
				char[] password1 = passwordField.getPassword();
				char[] password2 = passwordField_1.getPassword();
				if(password1.length < 8)
					lblshortpass.setVisible(true);
				else{
				if(!Arrays.equals(password1, password2)){
					lblTheTwoPasswords.setVisible(true);
				}
				else{
				
				boolean response = false ;//= Client.register(username, password1);
			
				
				if(response) {
					//email authentication
					JOptionPane.showMessageDialog(null, "Successfully registered");
				} else {
					JOptionPane.showMessageDialog(null, "Username already taken");
				}
				}
				}
			}
		});
		btnNewButton.setBounds(218, 189, 85, 23);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(323, 189, 73, 23);
		contentPane.add(button);
		
		
		
		
		
		
		
		
	}
}
