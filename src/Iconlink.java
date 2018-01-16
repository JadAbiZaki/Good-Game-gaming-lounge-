

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.EventListener;
import java.awt.event.ActionEvent;

public class Iconlink extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Iconlink frame = new Iconlink();
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
	public Iconlink() {
	initiComponents();
	createEvents();
	}
	
	//public String linkI = "/Project/sources/Gg-64.png";
	String  linkI ;
	boolean Action = false;
	private void createEvents(){
		btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		  linkI = textField.getText()	;
		  Action = true; 	 
			}
		});
	
	}
	
	public String getlinkI(String  link ){
		if (linkI == "")
		linkI = "/Project/sources/avatar.png";
		else
		{	linkI = link;
			return linkI;}
		
		return linkI;
	}
	
    boolean closed = false;
	private void initiComponents(){
	
		setBounds(100, 100, 508, 175);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblIconAddress = new JLabel("Icon address:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblIconAddress, 45, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblIconAddress, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblIconAddress, 101, SpringLayout.WEST, contentPane);
		contentPane.add(lblIconAddress);
		
		textField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField, 47, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 11, SpringLayout.EAST, lblIconAddress);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField, 275, SpringLayout.EAST, lblIconAddress);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("New button");

		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, -2, SpringLayout.NORTH, textField);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, -97, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, contentPane);
		contentPane.add(btnNewButton);
	}
	
	
	private class EventHandler implements EventListener{
		
	}
	
	
	public int occured = 0 ;
	
	private class WindowHandler implements WindowListener{

		@Override
		public void windowActivated(WindowEvent e) {
		
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			occured = 1;
			System.exit(0);
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
	
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
		
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
	
			
		}
		
	}

}
