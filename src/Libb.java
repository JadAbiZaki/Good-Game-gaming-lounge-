

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class Libb extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Libb frame = new Libb();
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
	public Libb() {
	
		initicomponents();
		createEV();
	}
private void initicomponents(){	
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
	
}
private void createEV(){
	
}
}
