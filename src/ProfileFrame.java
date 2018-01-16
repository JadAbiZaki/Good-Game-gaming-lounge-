

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ProfileFrame extends JFrame {

	public String currentUser;
	private JPanel contentPane;
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
	private JTextField textField;
	private JButton btnAdd;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileFrame frame = new ProfileFrame();
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
	public ProfileFrame() {
		initiComponents();
		createEvent();

	}


private void createEvent(){
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
				MainMenu MainMenu2 = new MainMenu();
				MainMenu2.setVisible(true);
				setVisible(false);
			}

			
			});
	btnAdd.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
	});
	
}
private void initiComponents(){
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
		
		
		
		textField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 69, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField, -600, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField, 33, SpringLayout.SOUTH, progressBar);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnAdd = new JButton("Add");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnAdd, 27, SpringLayout.EAST, textField);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnAdd, 0, SpringLayout.SOUTH, textField);
		contentPane.add(btnAdd);
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
		// TODO Auto-generated method stub
		
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
}