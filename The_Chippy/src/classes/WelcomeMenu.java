package classes;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Color;

public class WelcomeMenu {
	
	private JFrame frmWelcome;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeMenu window = new WelcomeMenu();
					window.frmWelcome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WelcomeMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcome = new JFrame();
		frmWelcome.setTitle("Welcome");
		frmWelcome.setBounds(100, 100, 1272, 776);
		frmWelcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frmWelcome.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome to");
		lblWelcome.setFont(new Font("Open Sans", Font.BOLD | Font.ITALIC, 60));
		lblWelcome.setBounds(44, 43, 514, 73);
		panel.add(lblWelcome);
		
		JButton btnNewButton = new JButton("Menu");
		btnNewButton.setMnemonic('M');
		btnNewButton.setFont(new Font("Open Sans", Font.BOLD, 50));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(516, 412, 294, 78);
		panel.add(btnNewButton);
		
		JLabel label_logo = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\logo.JPG"));
		label_logo.setBounds(415, 173, 431, 245);
		panel.add(label_logo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(220, 69, 69));
		panel_1.setBounds(0, 0, 1254, 175);
		panel.add(panel_1);
		
		JLabel label = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\menu.png"));
		label.setBounds(484, 412, 120, 88);
		panel.add(label);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 623, 1254, 106);
		panel_3.setBackground(new Color(115, 168, 212));

		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setBounds(29, 33, 225, 51);
		panel_3.add(btnAdmin);
		btnAdmin.setContentAreaFilled(false);
		btnAdmin.setBorderPainted(false);
		btnAdmin.setFont(new Font("Open Sans", Font.BOLD, 30));
		
		JLabel label_1 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\admin.png"));
		label_1.setBounds(12, 13, 96, 84);
		panel_3.add(label_1);
		btnAdmin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Manager manager = new Manager();
				manager.main(null);
				frmWelcome.dispose();

			}
		});
		
		JLabel label_2 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\burger.JPG"));
		label_2.setBounds(61, 315, 221, 175);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\chip.JPG"));
		label_3.setBounds(976, 264, 228, 226);
		panel.add(label_3);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				GUI fm = new GUI();
				fm.main(null);
				frmWelcome.dispose();
				//CloseWindow upon button press
				JComponent comp = (JComponent) arg0.getSource();
		         Window win = SwingUtilities.getWindowAncestor(comp);
		         win.dispose();
				
				
				
			}
		});
	}
}