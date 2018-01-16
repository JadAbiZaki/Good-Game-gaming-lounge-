import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class test extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
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
	public test() {
		setRootPaneCheckingEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("C:\\Users\\nozer\\Desktop\\blue.PNG"));
		} catch (IOException e) {
		}
		BackgroundPanel bgpanel = new BackgroundPanel(img);
		
		bgpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(bgpanel);
		bgpanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(144, 118, 86, 20);
		bgpanel.add(textField);
		textField.setColumns(10);
		textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\nozer\\Desktop\\white.jpg"));
		lblNewLabel.setBounds(144, 118, 86, 20);
		bgpanel.add(lblNewLabel);
		
		bgpanel.setVisible(true);
	}
}
