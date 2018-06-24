/*
 * gavin mcpaul
 * manager class
 * 
 * 
 * 
 */
package classes;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jcp.xml.dsig.internal.dom.DOMUtils;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Manager extends JFrame {

	private JFrame frame;

		//VARIABLES FOR DATABSE CONNECTION
		private String url = "jdbc:mysql://localhost:3306/";
		private String dbName = "fast_food?autoReconnect=true&useSSL=false";
		private String driver = "com.mysql.jdbc.Driver";
		private String userName = "root";
		private String passwordDB = "password";
		private Statement statement = null;   
	    private ResultSet resultSet = null;
	    
	    //SOME VARIABLES FOR THE JPANELS ARE ANNOUNCED OUTSIDE THE METHOD SO OTHERS CAN USE 
		private JPanel panelMenu,panelChoose,panel_Order;
	
		private JPasswordField enterPassword;
		private JTextField add_codeIn;
		private JTextField add_nameIn;
		private JTextField add_priceIn;
		private JTextField remove_codeIn;
		private JTextField remove_nameIn;
		private JTextField update_codeIn;
		private JTextField update_nameIn;
		private JTextField update_priceIn;
		private JTextField user_emailIn;
		private JPasswordField user_passwordIn;
		private JTextField sec;
		String selectImage;
		String selectImage1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager window = new Manager();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Manager() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//JRAME CREATION AND ALL SEPERATE JPANEL CODE
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		frame = new JFrame();
		frame.setBounds(100, 100, 1404, 999);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		final JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(255, 224, 147));
		panelMenu.setForeground(Color.BLACK);
		frame.getContentPane().add(panelMenu, "name_10475017053377");
		panelMenu.setLayout(null);
		panelMenu.setVisible(true);
		
		final JPanel panelChoose = new JPanel();
		frame.getContentPane().add(panelChoose, "name_10477123123916");
		panelChoose.setBackground(new Color(255, 224, 147));
		panelChoose.setLayout(null);
		panelChoose.setVisible(false);
		
		final JPanel panel_Add = new JPanel();
		frame.getContentPane().add(panel_Add, "name_10478840946849");
		panel_Add.setLayout(null);
		panel_Add.setVisible(false);
		panel_Add.setBackground(new Color(255, 224, 147));

		JPanel panel_remove = new JPanel();
		frame.getContentPane().add(panel_remove, "name_44676578512252");
		panel_remove.setLayout(null);
		panel_remove.setVisible(false);
		panel_remove.setBackground(new Color(255, 224, 147));

		JPanel panel_update = new JPanel();
		frame.getContentPane().add(panel_update, "name_21178903507949");
		panel_update.setLayout(null);
		panel_update.setVisible(false);
		panel_update.setBackground(new Color(255, 224, 147));

		JPanel panel_user = new JPanel();
		frame.getContentPane().add(panel_user, "name_1167324204295");
		panel_user.setLayout(null);
		panel_user.setVisible(false);
		panel_user.setBackground(new Color(255, 224, 147));


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	    //PANEL ADD CODE
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


		JLabel add_code = new JLabel("Product Code:");
		add_code.setFont(new Font("Open Sans", Font.PLAIN, 30));
		add_code.setBounds(125, 112, 201, 28);
		panel_Add.add(add_code);
		
		JLabel add_name = new JLabel("Name:");
		add_name.setFont(new Font("Open Sans", Font.PLAIN, 30));
		add_name.setBounds(125, 178, 94, 28);
		panel_Add.add(add_name);
		
		JLabel add_price = new JLabel("Price:");
		add_price.setFont(new Font("Open Sans", Font.PLAIN, 30));
		add_price.setBounds(125, 241, 79, 35);
		panel_Add.add(add_price);
		
		JLabel add_descrip = new JLabel("Description:");
		add_descrip.setFont(new Font("Open Sans", Font.PLAIN, 30));
		add_descrip.setBounds(1011, 109, 177, 35);
		panel_Add.add(add_descrip);
		
		JLabel add_section = new JLabel("Section:");
		add_section.setFont(new Font("Open Sans", Font.PLAIN, 30));
		add_section.setBounds(125, 307, 141, 35);
		panel_Add.add(add_section);
		
		JLabel add_alergy = new JLabel("Alergy:");
		add_alergy.setFont(new Font("Open Sans", Font.PLAIN, 30));
		add_alergy.setBounds(1011, 289, 129, 39);
		panel_Add.add(add_alergy);
		
		//CODE THAT ONLY ALLOWS INTEGERS TO BE INTERTERED NO CHARACTERS 
		
		add_codeIn = new JTextField();
		add_codeIn.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyTyped(KeyEvent e) 
			{
				
				char vchar = e.getKeyChar();
				if(!(Character.isDigit(vchar)) 
						|| (vchar == KeyEvent.VK_BACK_SPACE)
                         || (vchar == KeyEvent.VK_DELETE)) 
						{
                    e.consume();
			}
			}});
		add_codeIn.setFont(new Font("Open Sans", Font.PLAIN, 25));
		add_codeIn.setBounds(334, 107, 262, 43);
		panel_Add.add(add_codeIn);
		add_codeIn.setColumns(10);
		
		add_nameIn = new JTextField();
		add_nameIn.setFont(new Font("Open Sans", Font.PLAIN, 25));
		add_nameIn.setBounds(334, 173, 262, 43);
		panel_Add.add(add_nameIn);
		add_nameIn.setColumns(10);
		
		add_priceIn = new JTextField();
		add_priceIn.setFont(new Font("Open Sans", Font.PLAIN, 25));
		add_priceIn.setBounds(334, 239, 262, 43);
		panel_Add.add(add_priceIn);
		add_priceIn.setColumns(10);
		
		JComboBox add_sectionIn = new JComboBox();
		add_sectionIn.setBackground(Color.WHITE);
		add_sectionIn.setFont(new Font("Open Sans", Font.PLAIN, 25));
		add_sectionIn.setModel(new DefaultComboBoxModel(new String[] {"", "Drink", "Main", "Side", "Dessert"}));
		add_sectionIn.setBounds(334, 305, 262, 43);
		panel_Add.add(add_sectionIn);
		
		//JLABEL SET TO DEFAULT LOGO
		JLabel imagelabel = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\logo.JPG"));
		imagelabel.setBounds(125, 461, 471, 359);
		panel_Add.add(imagelabel);
		
		JTextArea add_descripIn = new JTextArea();
		add_descripIn.setBounds(1011, 147, 336, 129);
		panel_Add.add(add_descripIn);
		add_descripIn.setFont(new Font("Open Sans", Font.PLAIN, 25));
		add_descripIn.setLineWrap(true);
		add_descripIn.setWrapStyleWord(true);
		

		JTextArea add_alergyIn = new JTextArea();
		add_alergyIn.setBounds(1011, 340, 336, 129);
		panel_Add.add(add_alergyIn);
		add_alergyIn.setFont(new Font("Open Sans", Font.PLAIN, 25));
		add_alergyIn.setLineWrap(true);
		add_alergyIn.setWrapStyleWord(true);

		//CODE TO GET AN IMAGE TO ADD IT TO A PRODUCT 
		JButton Browse = new JButton("Browse");
		Browse.setFont(new Font("Open Sans", Font.BOLD, 25));
		Browse.setBackground(Color.WHITE);
		Browse.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				 //LETS THE ADMIN CHOOSE IMAGE THAT IS STORED ON THERE PC
				 JFileChooser fileChooser = new JFileChooser();
		         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
		         fileChooser.addChoosableFileFilter(filter);
		         int result = fileChooser.showSaveDialog(null);
		         
		         //ONCE IMAGE IS SELECTED ITS BEING STORED IN JLABEL THAT IS THEN SCALED IN TEH SIXE OF THE JLABEL 
		         if(result == JFileChooser.APPROVE_OPTION)
		         {
		        	 	File selectedFile = fileChooser.getSelectedFile();
		        	 	String path = selectedFile.getAbsolutePath();
		        	 	ImageIcon image = new ImageIcon(path);
	                    Image im = image.getImage();
	                    Image myImg = im.getScaledInstance(imagelabel.getWidth(), imagelabel.getHeight(),Image.SCALE_SMOOTH);
	                    ImageIcon newImage = new ImageIcon(myImg);
	                    imagelabel.setIcon(newImage);
	                    selectImage = path;
		         }
			}
		});
		Browse.setBounds(334, 376, 262, 43);
		panel_Add.add(Browse);
		
		//CODE THAT IS ADDING THE PRODUCT TO THE DATBASE ALONG WITH THE IMAGE 
		
		JButton add_OK = new JButton("OK");
		add_OK.setBorderPainted(false);
		add_OK.setContentAreaFilled(false);

		add_OK.setFont(new Font("Open Sans", Font.BOLD, 30));
		add_OK.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				try 
				{

					 String code1 = add_codeIn.getText();
					 String name1 = add_nameIn.getText();
					 String price1 = add_priceIn.getText();
					 String descrip1 = add_descripIn.getText();
					// String section1 = add_sectionIn.getText();
					 String alergy1 = add_alergyIn.getText();
						
					//CODE TO VERFIY THE EMAIL ADDRESS AND PASSWORD FROM THE DATABASE 
					Class.forName("com.mysql.jdbc.Driver");
			        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
			        statement=conn.createStatement(); 
			        
			        //ERROR HANDLING 
			        if(e.getSource() == add_OK && add_codeIn.getText().equals("")|| add_nameIn.getText().equals("") || add_priceIn.getText().equals(""))
			        {
			        	 JOptionPane.showMessageDialog(null, "Allergy and Description can only be blank","Please Try again", JOptionPane.ERROR_MESSAGE);

			        }
			       
			        
			        String text = (String)add_sectionIn.getSelectedItem();
			       	
			        //ADDING IT TO THE DATABASE
			        if(code1 != null)
			        {
			        	
			        	String code = new String(code1);
			        	String query1 ="INSERT INTO Product (Product_Code, Name, Price, Description, Section, Alergy, Image)  values (?,?,?,?,?,?,?)";
			        	
			        	PreparedStatement ps = conn.prepareStatement(query1);
			        	InputStream inputImage = new FileInputStream(new File(selectImage));
			        	ps.setString(1, code1);
			        	ps.setString(2, name1);
			        	ps.setString(3, price1);
			        	ps.setString(4, descrip1);
			        	ps.setString(5, text);
			        	ps.setString(6, alergy1);
			        	ps.setBlob(7, inputImage);

			        	//ResultSet rs;
			        	ps.execute();
			        
				
			        	//CHECKING TO SEE IF THE PRODUCT WAS ADDED 
			        	try(PreparedStatement stmt = conn.prepareStatement("SELECT Product_Code, Name, Price, Description, Section, Alergy FROM product where Product_Code = ?"))
			        			{
			        				stmt.setString(1, code1);
			        				ResultSet rs =stmt.executeQuery();
			        				
			        				if(rs.next())
			        				{
			     			           JOptionPane.showMessageDialog(null, "New Product Was Added to Menu","Product Added", JOptionPane.INFORMATION_MESSAGE);
			     			           panel_Add.setVisible(false);
			     			           panelChoose.setVisible(true);
			     					   panelChoose.updateUI();
			     					   panel_Add.updateUI();
			     					   panel_remove.updateUI();
			     					   panel_update.updateUI();
			     					  add_codeIn.setText(null);
			     					  add_nameIn.setText(null);
			     					  add_priceIn.setText(null);
			     					  add_descripIn.setText(null);
			     					  //add_sectionIn.setText(null);
			     					  add_alergyIn.setText(null);
			     					 imagelabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\logo.JPG"));


			     			           
			        				}
			        				
			        				else
			        				{
			    			        	 JOptionPane.showMessageDialog(null, "Product already added","Please Try again", JOptionPane.ERROR_MESSAGE);

			        				}
			        			}
			        	
			        	
			        	ps.close();
			        	 conn.close();
			        }
				}catch(Exception ee) 
				{
					 JOptionPane.showMessageDialog(null, "Try again","TRY AGAIN", JOptionPane.ERROR_MESSAGE);
					 add_codeIn.setText(null);
					  add_nameIn.setText(null);
					  add_priceIn.setText(null);
					  add_descripIn.setText(null);
					  //add_sectionIn.setText(null);
					  add_alergyIn.setText(null);
					 imagelabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\logo.JPG"));
					System.out.println(ee);

				}
				
			}
		});
		//
		
		add_OK.setBounds(552, 858, 129, 43);
		panel_Add.add(add_OK);
		
		JLabel lblPleaseEnterProduct = new JLabel("Enter Product Details");
		lblPleaseEnterProduct.setForeground(new Color(239, 33, 33));
		lblPleaseEnterProduct.setFont(new Font("Open Sans", Font.BOLD | Font.ITALIC, 50));
		lblPleaseEnterProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseEnterProduct.setBounds(452, 14, 558, 82);
		panel_Add.add(lblPleaseEnterProduct);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBorderPainted(false);
		btnCancel.setContentAreaFilled(false);
		
		//SETTING FIELD TO NULL IF ADMIN CANCELS ADDING TEH PRODUCT 
		btnCancel.setFont(new Font("Open Sans", Font.BOLD, 30));
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panel_Add.setVisible(false);
				panelChoose.setVisible(true);
				add_codeIn.setText(null);
				add_nameIn.setText(null);
				add_priceIn.setText(null);
				add_descripIn.setText(null);
				//add_sectionIn.setText(null);
				add_alergyIn.setText(null);
				imagelabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\logo.JPG"));

			
			}
		});
		btnCancel.setBounds(800, 858, 180, 43);
		panel_Add.add(btnCancel);
		
		JLabel label_4 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\ok.png"));
		label_4.setBounds(522, 839, 94, 62);
		panel_Add.add(label_4);
		
		JLabel label_5 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\remove.png"));
		label_5.setBounds(775, 849, 68, 62);
		panel_Add.add(label_5);
		
		JLabel lblSelectImage = new JLabel("Select Image:");
		lblSelectImage.setFont(new Font("Open Sans", Font.PLAIN, 30));
		lblSelectImage.setBounds(125, 378, 201, 35);
		panel_Add.add(lblSelectImage);
		
	
		panel_Add.setVisible(false);
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//PANEL MENU CODE
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		JLabel lblNewLabel = new JLabel("The Chippy");
		lblNewLabel.setForeground(new Color(239, 33, 33));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Open Sans", Font.BOLD | Font.ITALIC, 80));
		lblNewLabel.setBounds(417, 161, 536, 120);
		panelMenu.add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Open Sans", Font.PLAIN, 30));
		lblEmail.setBackground(UIManager.getColor("Button.background"));
		lblEmail.setBounds(453, 361, 126, 35);
		panelMenu.add(lblEmail);
		
	    JTextField enterEmail = new JTextField();
	    enterEmail.setFont(new Font("Open Sans", Font.PLAIN, 25));
		enterEmail.setBounds(638, 359, 262, 43);
		panelMenu.add(enterEmail);
		enterEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Open Sans", Font.PLAIN, 30));
		lblPassword.setBounds(453, 474, 157, 35);
		panelMenu.add(lblPassword);
		
		enterPassword = new JPasswordField();
		enterPassword.setFont(new Font("Open Sans", Font.PLAIN, 25));
		enterPassword.setBounds(638, 472, 262, 43);
		panelMenu.add(enterPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setContentAreaFilled(false);

		btnLogin.setBorderPainted(false);
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setFont(new Font("Open Sans", Font.BOLD, 30));
		btnLogin.setBounds(574, 626, 226, 49);
		panelMenu.add(btnLogin);
		btnLogin.setBorderPainted(false);
		btnLogin.setBackground(new Color(187, 196, 205));
		
		JLabel label_11 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\lock.png"));
		label_11.setBounds(554, 615, 96, 60);
		panelMenu.add(label_11);


		
		//CODE TO SIGN IN
		// 
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//panelChoose.setVisible(true);

				try 
				{		
					 // CODE THAT WILL DISPLAY JTEXTBOX IF THE PASSWORDS OR EMAIL ARE NOT ENTERED CORRECTLY 
					 String email = enterEmail.getText();
			         String Password = enterPassword.getText();
				
					 // IF NO DATA ENTERERD TO JTEXTFIELD WILL POP UP ERROR
					 if(e.getSource() == btnLogin && email.equals("") || Password.equals(""))
					 {

						 JOptionPane.showMessageDialog(null, "Cannot be left blank, TRY AGAIN","TRY AGAIN", JOptionPane.ERROR_MESSAGE);
					 }
		         
		             // IF EMAIL DOES NOT CONTAIN @ OR .com WILL POP UP BOX INVALID EMIAL ADDRESS
					 else if(e.getSource() == btnLogin && !email.contains(".com") || !email.contains("@"))
					 {
						 JOptionPane.showMessageDialog(null, "invalid email or Password, TRY AGAIN","TRY AGAIN", JOptionPane.ERROR_MESSAGE);
					 }
						
					//CODE TO VERFIY THE EMAIL ADDRESS AND PASSWORD FROM THE DATABASE 
					Class.forName("com.mysql.jdbc.Driver");
			        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
			        statement=conn.createStatement(); 
			        
			        String emailAd = enterEmail.getText();
			        char[] passwd = enterPassword.getPassword();
			        String password1 = new String(passwd);
			        
			        //CHECKING TO SEE IF TEH DATA PROVIDED IS IN THE DATABSE AND IF SO CAN LOG IN
			        if(passwd != null)
			        {
			        	String pass = new String(passwd);
			        	String query1 ="SELECT Email,Password FROM manager where Email=? and Password=?";
			        	
			        	PreparedStatement ps = conn.prepareStatement(query1);
			        	ps.setString(1, emailAd);
			        	ps.setString(2, password1);
			        	
			        	ResultSet rs;
			        	 rs = ps.executeQuery();
			        	 
			        	 if(rs.next())
			        	 {  
			        		
			        		 JOptionPane.showMessageDialog(null, "Correct Credentials","Have a nice day", JOptionPane.INFORMATION_MESSAGE);

			        		 	panelMenu.setVisible(false);
			        			panelChoose.setVisible(true);
			        			

			        	 }
			        	 
			        	 else
			        	 {
			        		// JOptionPane.showMessageDialog(null, "Incorrect Password or Email, logIn","Please Try again", JOptionPane.ERROR_MESSAGE);

			        	 }
			        	 
			        	 ps.close();
			        	 rs.close();
			        	 conn.close();
			        }
				}catch(Exception ee) 
				{
					System.out.println(ee);

				}
				
			}
			//
		});

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//PANEL CHOOSE CODE
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//
		// AL
		JButton add = new JButton("Add Item");
		add.setBorderPainted(false);
		add.setContentAreaFilled(false);

		add.setBackground(new Color(187, 196, 205));
		add.setFont(new Font("Open Sans", Font.BOLD, 30));
		add.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panelChoose.setVisible(false);
				panel_Add.setVisible(true);

			}
		});
		//
		
		add.setBounds(392, 260, 199, 60);
		panelChoose.add(add);
		
		JButton remove = new JButton("Remove Item");
		remove.setBorderPainted(false);
		remove.setContentAreaFilled(false);
		remove.setBackground(new Color(187, 196, 205));
		remove.setFont(new Font("Open Sans", Font.BOLD, 30));
		remove.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panelChoose.setVisible(false);
				panel_remove.setVisible(true);

				
			}
			
		});
		
		remove.setBounds(386, 498, 264, 60);
		panelChoose.add(remove);
		
		JButton update = new JButton("Update Item ");
		update.setBorderPainted(false);
		update.setContentAreaFilled(false);

		update.setBackground(new Color(187, 196, 205));
		update.setFont(new Font("Open Sans", Font.BOLD, 30));
		update.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				panelChoose.setVisible(false);
				panel_update.setVisible(true);

			}
			
		});
		update.setBounds(766, 260, 284, 60);
		panelChoose.add(update);
		
		JLabel lblPleaseSelectAn = new JLabel("Please Select An Option");
		lblPleaseSelectAn.setForeground(new Color(239, 33, 33));
		lblPleaseSelectAn.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseSelectAn.setFont(new Font("Open Sans", Font.BOLD | Font.ITALIC, 55));
		lblPleaseSelectAn.setBounds(353, 84, 697, 78);
		panelChoose.add(lblPleaseSelectAn);
		
		//CODE TO CREATE NEW USER 
		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.setFont(new Font("Open Sans", Font.BOLD, 30));
		btnCreateUser.setBorderPainted(false);
		btnCreateUser.setContentAreaFilled(false);

		btnCreateUser.setBackground(new Color(187, 196, 205));
		btnCreateUser.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panelChoose.setVisible(false);
				panel_user.setVisible(true);

			}
		});
		btnCreateUser.setBounds(790, 498, 225, 60);
		panelChoose.add(btnCreateUser);
		
		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.setContentAreaFilled(false);
		btnSignOut.setBorderPainted(false);
		btnSignOut.setBackground(new Color(187, 196, 205));
		btnSignOut.setFont(new Font("Open Sans", Font.BOLD, 30));
		btnSignOut.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panelChoose.setVisible(false);
				panelMenu.setVisible(true);
				enterEmail.setText(null);
		        enterPassword.setText(null);
			}
		});
		btnSignOut.setBounds(77, 808, 249, 60);
		panelChoose.add(btnSignOut);
		
		JButton btnViewProducts = new JButton("View Products");
		btnViewProducts.setContentAreaFilled(false);

		btnViewProducts.setBorderPainted(false);
		btnViewProducts.setBackground(new Color(187, 196, 205));
		btnViewProducts.setFont(new Font("Open Sans", Font.BOLD, 30));
		btnViewProducts.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panelChoose.setVisible(true);
				View_Products products = new View_Products();
				products.main(null);
				//panelChoose.setVisible(true);

				//getClass().panelChoose.setVisible(false);
				//products.setVisible(true);
				//panelChoose.setVisible(false);
				//panel_view.setVisible(true);
			}
		});
		btnViewProducts.setBounds(1046, 808, 298, 60);
		panelChoose.add(btnViewProducts);
		
		JLabel lblNewLabel_4 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\add.png"));
		lblNewLabel_4.setBounds(371, 260, 50, 60);
		panelChoose.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\remove.png"));
		lblNewLabel_5.setBounds(341, 491, 107, 78);
		panelChoose.add(lblNewLabel_5);
		
		JLabel label = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\edit.png"));
		
		label.setBounds(730, 252, 107, 78);
		panelChoose.add(label);
		
		JLabel label_1 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\user.png"));
		label_1.setBounds(743, 491, 94, 78);
		panelChoose.add(label_1);
		
		JLabel label_2 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\sign.png"));
		label_2.setBounds(58, 795, 94, 78);
		panelChoose.add(label_2);
		
		JLabel label_3 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\list.png"));
		label_3.setBounds(1003, 808, 94, 65);
		panelChoose.add(label_3);
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//PANEL REMOVE PANEL
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		JLabel remove_code = new JLabel("Product Code:");
		remove_code.setFont(new Font("Open Sans", Font.PLAIN, 30));
		remove_code.setBounds(635, 398, 227, 30);
		panel_remove.add(remove_code);
		
		JLabel remove_name = new JLabel("Name:");
		remove_name.setFont(new Font("Open Sans", Font.PLAIN, 30));
		remove_name.setBounds(635, 506, 104, 30);
		panel_remove.add(remove_name);
		
		remove_codeIn = new JTextField();
		remove_codeIn.setFont(new Font("Open Sans", Font.PLAIN, 19));
		remove_codeIn.setBounds(864, 396, 264, 43);
		panel_remove.add(remove_codeIn);
		remove_codeIn.setColumns(10);
		
		remove_nameIn = new JTextField();
		remove_nameIn.setFont(new Font("Open Sans", Font.PLAIN, 19));
		remove_nameIn.setBounds(864, 504, 264, 43);
		panel_remove.add(remove_nameIn);
		remove_nameIn.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(30, 86, 174, -60);
		panel_remove.add(lblNewLabel_1);
		
		JLabel lblEnterDetailsTo = new JLabel("Enter Details to remove a Product");
		lblEnterDetailsTo.setForeground(new Color(239, 33, 33));
		lblEnterDetailsTo.setFont(new Font("Open Sans", Font.BOLD | Font.ITALIC, 50));
		lblEnterDetailsTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterDetailsTo.setBounds(201, 86, 919, 92);
		panel_remove.add(lblEnterDetailsTo);
		
		JButton remove_OK = new JButton("Remove");
		remove_OK.setBorderPainted(false);
		remove_OK.setContentAreaFilled(false);
		remove_OK.setFont(new Font("Open Sans", Font.BOLD, 30));
		remove_OK.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				try 
				{		
					 String code2 = remove_codeIn.getText();
					 String name2 = remove_nameIn.getText();
						
					//CODE TO VERFIY THE EMAIL ADDRESS AND PASSWORD FROM THE DATABASE 
					Class.forName("com.mysql.jdbc.Driver");
			        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
			        statement=conn.createStatement(); 
			       	
			        if(e.getSource()== remove_OK && code2.equals("") || name2.equals(""))
			        {
						 JOptionPane.showMessageDialog(null, "Cannot be left blank, TRY AGAIN","TRY AGAIN", JOptionPane.ERROR_MESSAGE);

			        }
			        
			        else if(code2 != null)
			        {
			        	
			        	String code3 = new String(code2);
			        	
			        	String query1 ="DELETE FROM Product WHERE Product_Code = ? AND Name = ?";
			        	
			        	PreparedStatement ps = conn.prepareStatement(query1);
			        	ps.setString(1, code3);
			        	ps.setString(2, name2);
			     
			        	//ResultSet rs;
			        	int check = ps.executeUpdate();
			        	
			        	if (check > 0) {
			        			JOptionPane.showMessageDialog(null, "Product Deleted from Menu","Product Deleted", JOptionPane.INFORMATION_MESSAGE);
			        			panel_remove.setVisible(false);
			        			panelChoose.setVisible(true);
			        			panelChoose.updateUI();
			        			panel_Add.updateUI();
			        			panel_remove.updateUI();
			        			panel_update.updateUI();
			        			remove_codeIn.setText(null);
			        			remove_nameIn.setText(null);
			        		}
			        	
			        	else
			        	{
		        			JOptionPane.showMessageDialog(null, "Product Does Not Exist","Try Again", JOptionPane.ERROR_MESSAGE);
		        			remove_codeIn.setText(null);
		        			remove_nameIn.setText(null);
			        	}
			        	
			        	

			        	
			        	ps.close();
			        	 
			        	 conn.close();			        }
				}catch(Exception ee) 
				{

					System.out.println(ee);

				}
				
				
			}
		});
		remove_OK.setBounds(469, 831, 169, 37);
		panel_remove.add(remove_OK);
		
		JButton btnCancel_1 = new JButton("Cancel");
		btnCancel_1.setBorderPainted(false);
		btnCancel_1.setContentAreaFilled(false);
		btnCancel_1.setFont(new Font("Open Sans", Font.BOLD, 30));
		btnCancel_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panel_remove.setVisible(false);
				panelChoose.setVisible(true);
				  remove_codeIn.setText(null);
					  remove_nameIn.setText(null);
			}
		});
		btnCancel_1.setBounds(795, 831, 140, 37);
		panel_remove.add(btnCancel_1);
		
		JLabel lblMain_1 = new JLabel("Main");
		lblMain_1.setFont(new Font("Open Sans", Font.BOLD, 30));
		lblMain_1.setBounds(44, 232, 147, 30);
		panel_remove.add(lblMain_1);
		
		JComboBox remove_main = new JComboBox();
		remove_main.setBackground(Color.WHITE);
		remove_main.setFont(new Font("Open Sans", Font.PLAIN, 25));
		remove_main.setBounds(44, 275, 264, 43);
		panel_remove.add(remove_main);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
	        statement=conn.createStatement(); 
			String s = "Select Name from product WHERE Section = 'Main';";
			ResultSet rs = statement.executeQuery(s);
			
			while(rs.next())
			{
				remove_main.addItem(rs.getString(1));
			}
		}
			catch (Exception e3)
			{
				System.out.print(e3);
				
			}
		
		remove_main.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					Class.forName("com.mysql.jdbc.Driver");
			        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
			        statement=conn.createStatement(); 
			        
			        String s = "Select Product_Code, Name from fast_food.product WHERE Section = 'Main' and Name = ?";
			        PreparedStatement pst = conn.prepareStatement(s);
			        pst.setString(1, (String)remove_main.getSelectedItem());
			        java.sql.ResultSet rs=pst.executeQuery();
			        	
			        
			        	while(rs.next()) 
			        	{
			        		remove_codeIn.setText(rs.getString("Product_Code"));
			        		remove_nameIn.setText(rs.getString("Name"));
			        		
			        	}
			        	
			        	pst.close();
						} catch (SQLException | ClassNotFoundException e1) 
			    	{
							
							System.out.print(e1);
						}
						
				
				
			}
		});
		
		
		JLabel lblSide = new JLabel("Side");
		lblSide.setFont(new Font("Open Sans", Font.BOLD, 30));
		lblSide.setBounds(50, 342, 78, 30);
		panel_remove.add(lblSide);
		
		JComboBox remove_side = new JComboBox();
		remove_side.setBackground(Color.WHITE);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
	        statement=conn.createStatement(); 
			String s = "Select Name from product WHERE Section = 'Side';";
			ResultSet rs = statement.executeQuery(s);
			
			while(rs.next())
			{
				remove_side.addItem(rs.getString(1));
			}
		}
			catch (Exception e3)
			{
				System.out.print(e3);
				
			}
		
		remove_side.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					Class.forName("com.mysql.jdbc.Driver");
			        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
			        statement=conn.createStatement(); 
			        
			        String s = "Select Product_Code, Name from fast_food.product WHERE Section = 'Side' and Name = ?";
			        PreparedStatement pst = conn.prepareStatement(s);
			        pst.setString(1, (String)remove_side.getSelectedItem());
			        java.sql.ResultSet rs=pst.executeQuery();
			        	
			        
			        	while(rs.next()) 
			        	{
			        		remove_codeIn.setText(rs.getString("Product_Code"));
			        		remove_nameIn.setText(rs.getString("Name"));
			        		
			        	}
			        	
			        	pst.close();
						} catch (SQLException | ClassNotFoundException e1) 
			    	{
							
							System.out.print(e1);
						}
						
				
			}
		});
		remove_side.setFont(new Font("Open Sans", Font.PLAIN, 25));
		remove_side.setBounds(46, 385, 262, 43);
		panel_remove.add(remove_side);
		
		JLabel lblDessert = new JLabel("Dessert");
		lblDessert.setFont(new Font("Open Sans", Font.BOLD, 30));
		lblDessert.setBounds(48, 459, 156, 30);
		panel_remove.add(lblDessert);
		
		JComboBox remove_dessert = new JComboBox();
		remove_dessert.setBackground(Color.WHITE);

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
	        statement=conn.createStatement(); 
			String s = "Select Name from product WHERE Section = 'Dessert';";
			ResultSet rs = statement.executeQuery(s);
			
			while(rs.next())
			{
				remove_dessert.addItem(rs.getString(1));
			}
		}
			catch (Exception e3)
			{
				System.out.print(e3);
				
			}
		
		remove_dessert.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					Class.forName("com.mysql.jdbc.Driver");
			        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
			        statement=conn.createStatement(); 
			        
			        String s = "Select Product_Code, Name from fast_food.product WHERE Section = 'Dessert' and Name = ?";
			        PreparedStatement pst = conn.prepareStatement(s);
			        pst.setString(1, (String)remove_dessert.getSelectedItem());
			        java.sql.ResultSet rs=pst.executeQuery();
			        	
			        
			        	while(rs.next()) 
			        	{
			        		remove_codeIn.setText(rs.getString("Product_Code"));
			        		remove_nameIn.setText(rs.getString("Name"));
			        		
			        	}
			        	
			        	pst.close();
						} catch (SQLException | ClassNotFoundException e1) 
			    	{
							
							System.out.print(e1);
						}
						
			}
		});
		remove_dessert.setFont(new Font("Open Sans", Font.PLAIN, 25));
		remove_dessert.setBounds(46, 502, 262, 43);
		panel_remove.add(remove_dessert);
		
		JLabel lblDrink_1 = new JLabel("Drink");
		lblDrink_1.setFont(new Font("Open Sans", Font.BOLD, 30));
		lblDrink_1.setBounds(53, 583, 95, 23);
		panel_remove.add(lblDrink_1);
		
		JComboBox remove_drink = new JComboBox();
		remove_drink.setBackground(Color.WHITE);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
	        statement=conn.createStatement(); 
			String s = "Select Name from product WHERE Section = 'Drink';";
			ResultSet rs = statement.executeQuery(s);
			
			while(rs.next())
			{
				remove_drink.addItem(rs.getString(1));
			}
		}
			catch (Exception e3)
			{
				System.out.print(e3);
				
			}
		
		remove_drink.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					Class.forName("com.mysql.jdbc.Driver");
			        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
			        statement=conn.createStatement(); 
			        
			        String s = "Select Product_Code, Name from fast_food.product WHERE Section = 'Drink' and Name = ?";
			        PreparedStatement pst = conn.prepareStatement(s);
			        pst.setString(1, (String)remove_drink.getSelectedItem());
			        java.sql.ResultSet rs=pst.executeQuery();
			        	
			        
			        	while(rs.next()) 
			        	{
			        		remove_codeIn.setText(rs.getString("Product_Code"));
			        		remove_nameIn.setText(rs.getString("Name"));
			        		
			        	}
			        	
			        	pst.close();
						} catch (SQLException | ClassNotFoundException e1) 
			    	{
							
							System.out.print(e1);
						}
			}
		});
		remove_drink.setFont(new Font("Open Sans", Font.PLAIN, 25));
		remove_drink.setBounds(46, 619, 262, 43);
		panel_remove.add(remove_drink);
		
		JLabel lblNewLabel_6 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\delete.png"));
		lblNewLabel_6.setBounds(436, 820, 83, 58);
		panel_remove.add(lblNewLabel_6);
		
		JLabel label_6 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\remove.png"));
		label_6.setBounds(746, 820, 78, 58);
		panel_remove.add(label_6);
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//PANEL_UPDATE CODE
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		JLabel lblEnterInformationTo = new JLabel("Enter Information to Update Product");
		lblEnterInformationTo.setForeground(new Color(239, 33, 33));
		lblEnterInformationTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterInformationTo.setFont(new Font("Open Sans", Font.BOLD | Font.ITALIC, 45));
		lblEnterInformationTo.setBounds(277, 38, 869, 63);
		panel_update.add(lblEnterInformationTo);
		
		JLabel update_code = new JLabel("Product Code:");
		update_code.setFont(new Font("Open Sans", Font.PLAIN, 30));
		update_code.setBounds(345, 142, 203, 25);
		panel_update.add(update_code);
		
		JLabel update_name = new JLabel("Name:");
		update_name.setFont(new Font("Open Sans", Font.PLAIN, 30));
		update_name.setBounds(345, 224, 98, 25);
		panel_update.add(update_name);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Open Sans", Font.PLAIN, 30));
		lblPrice.setBounds(345, 303, 78, 25);
		panel_update.add(lblPrice);
		
		JLabel lblSection = new JLabel("Section:");
		lblSection.setFont(new Font("Open Sans", Font.PLAIN, 30));
		lblSection.setBounds(345, 379, 116, 31);
		panel_update.add(lblSection);
		
		JLabel update_descrip = new JLabel("Description:");
		update_descrip.setFont(new Font("Open Sans", Font.PLAIN, 30));
		update_descrip.setBounds(1015, 134, 168, 40);
		panel_update.add(update_descrip);
		
		update_codeIn = new JTextField();
		update_codeIn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e)
			{
				char vchar = e.getKeyChar();
				if(!(Character.isDigit(vchar)) 
						|| (vchar == KeyEvent.VK_BACK_SPACE)
                         || (vchar == KeyEvent.VK_DELETE)) 
						{
                    e.consume();
			}
			
			}
		});
		update_codeIn.setFont(new Font("Open Sans", Font.PLAIN, 25));
		update_codeIn.setBounds(570, 142, 262, 43);
		panel_update.add(update_codeIn);
		update_codeIn.setColumns(10);
		
		update_nameIn = new JTextField();
		update_nameIn.setFont(new Font("Open Sans", Font.PLAIN, 25));
		update_nameIn.setBounds(570, 217, 262, 43);
		panel_update.add(update_nameIn);
		update_nameIn.setColumns(10);
		
		update_priceIn = new JTextField();
		update_priceIn.setFont(new Font("Open Sans", Font.PLAIN, 25));
		update_priceIn.setBounds(570, 296, 262, 43);
		panel_update.add(update_priceIn);
		update_priceIn.setColumns(10);
		
		JLabel lblAlergy = new JLabel("Alergy:");
		lblAlergy.setFont(new Font("Open Sans", Font.PLAIN, 30));
		lblAlergy.setBounds(1015, 339, 116, 46);
		panel_update.add(lblAlergy);
		
		JTextArea update_descripIn = new JTextArea();
		update_descripIn.setFont(new Font("Open Sans", Font.PLAIN, 25));
		update_descripIn.setBounds(1010, 183, 330, 143);
		panel_update.add(update_descripIn);
		update_descripIn.setLineWrap(true);
		update_descripIn.setWrapStyleWord(true);

		
		JTextArea update_alergyIn = new JTextArea();
		update_alergyIn.setFont(new Font("Open Sans", Font.PLAIN, 25));
		update_alergyIn.setBounds(1010, 389, 330, 143);
		panel_update.add(update_alergyIn);
		update_alergyIn.setLineWrap(true);
		update_alergyIn.setWrapStyleWord(true);
		
		JLabel image_update = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\logo.JPG"));
		image_update.setBounds(345, 505, 487, 330);
		panel_update.add(image_update);
		
		JButton btnNewButton_1 = new JButton("Browse");
		btnNewButton_1.setFont(new Font("Open Sans", Font.BOLD, 25));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				JFileChooser fileChooser = new JFileChooser();
		         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
		         fileChooser.addChoosableFileFilter(filter);
		         int result = fileChooser.showSaveDialog(null);
		         if(result == JFileChooser.APPROVE_OPTION){
		             File selectedFile = fileChooser.getSelectedFile();
		             String path = selectedFile.getAbsolutePath();
		             ImageIcon image = new ImageIcon(path);
	                    Image im = image.getImage();
	                    Image myImg = im.getScaledInstance(imagelabel.getWidth(), imagelabel.getHeight(),Image.SCALE_SMOOTH);
	                    ImageIcon newImage = new ImageIcon(myImg);
	                    image_update.setIcon(newImage);
	                    selectImage1 = path;
	                   
				
			}
			}});
		btnNewButton_1.setBounds(570, 449, 262, 43);
		panel_update.add(btnNewButton_1);
		
		
		
		JButton update_OK = new JButton("Update");
		update_OK.setBorderPainted(false);
		update_OK.setContentAreaFilled(false);
		update_OK.setFont(new Font("Open Sans", Font.BOLD, 30));
		update_OK.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{

				try 
				{		
					 String code3 = update_codeIn.getText();
					 String name3 = update_nameIn.getText();
					 String price3 = update_priceIn.getText();
					 String descrip3 = update_descripIn.getText();
					 String section3 = sec.getText();
					 String alergy3 = update_alergyIn.getText();
			         InputStream inputImage = new FileInputStream(new File(selectImage1));

				     image_update.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\logo.JPG"));

					//CODE TO VERFIY THE EMAIL ADDRESS AND PASSWORD FROM THE DATABASE 
					Class.forName("com.mysql.jdbc.Driver");
			        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
			        statement=conn.createStatement(); 
			       	        
			         if(code3 != null && inputImage != null)
			         {

			        	String code = new String(code3);
			        	String query1 ="UPDATE Product SET Product_Code=?, Name=?, Price=?, Description=?, Section=?, Alergy=?, Image=? WHERE Product_Code= ?";
			        	
			        	PreparedStatement ps = conn.prepareStatement(query1);
			        	//InputStream inputImage = new FileInputStream(new File(selectImage1));

			        	ps.setString(1, code3);
			        	ps.setString(2, name3);
			        	ps.setString(3, price3);
			        	ps.setString(4, descrip3);
			        	ps.setString(5, section3);
			        	ps.setString(6, alergy3);
			        	ps.setBlob(7, inputImage);
			        	ps.setString(8, code3);



			        	//ResultSet rs;
			        	ps.execute();
			        	try(PreparedStatement stmt = conn.prepareStatement("SELECT Product_Code, Name, Price, Description, Section, Alergy, Image FROM product where Product_Code = ?"))
			        			{
			        				stmt.setString(1, code3);
			        				ResultSet rs =stmt.executeQuery();
			        				
			        				if(rs.next())
			        				{
			     			           JOptionPane.showMessageDialog(null, "Product was Updated","Product Updated", JOptionPane.INFORMATION_MESSAGE);
			     			           panel_update.setVisible(false);
			     			           panelChoose.setVisible(true);
			     			           panelChoose.updateUI();
			     					   panel_Add.updateUI();
			     					   panel_remove.updateUI();
			     					   panel_update.updateUI();
			     					   update_codeIn.setText(null);
			     					   update_nameIn.setText(null);
			     					   update_priceIn.setText(null);
			     					   update_descripIn.setText(null);
			     					   sec.setText(null);
			     					   update_alergyIn.setText(null);
			     			           
			        				}
			        				
			        				else
			        				{
				     			           JOptionPane.showMessageDialog(null, "Product Not Updated","Product Not Updated", JOptionPane.ERROR_MESSAGE);

			        				}
			        			}
			        	
			        	

			        	
			        	ps.close();
			        	 conn.close();			        }
				}catch(Exception ee) 
				{
					 JOptionPane.showMessageDialog(null, "Must Update the Image, TRY AGAIN","TRY AGAIN", JOptionPane.ERROR_MESSAGE);

					System.out.println(ee);

				}
				
			}
		});
		update_OK.setBounds(524, 876, 180, 36);
		panel_update.add(update_OK);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFont(new Font("Open Sans", Font.BOLD, 30));
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panel_update.setVisible(false);
				panelChoose.setVisible(true);
				update_codeIn.setText(null);
				 update_nameIn.setText(null);
				 update_priceIn.setText(null);
				 update_descripIn.setText(null);
				 sec.setText(null);
				 update_alergyIn.setText(null);
				 image_update.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\logo.JPG"));

			}
		});
		btnNewButton.setBounds(805, 876, 189, 36);
		panel_update.add(btnNewButton);
		
		JLabel lblMain = new JLabel("Main:");
		lblMain.setFont(new Font("Open Sans", Font.BOLD, 30));
		lblMain.setBounds(12, 116, 91, 31);
		panel_update.add(lblMain);
		
		JComboBox update_main = new JComboBox();
		update_main.setBackground(Color.WHITE);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
	        statement=conn.createStatement(); 
			String s = "Select Name from product WHERE Section = 'Main';";
			ResultSet rs = statement.executeQuery(s);
			
			while(rs.next())
			{
				update_main.addItem(rs.getString(1));
			}
		}
			catch (Exception e3)
			{
				System.out.print(e3);
				
			}
		
		update_main.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					Class.forName("com.mysql.jdbc.Driver");
			        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
			        statement=conn.createStatement(); 
			        
			        String s = "Select Product_Code, Name, Price, Description, Section, Alergy, Image from fast_food.product WHERE Section = 'Main' and Name = ?";
			        PreparedStatement pst = conn.prepareStatement(s);
			        pst.setString(1, (String)update_main.getSelectedItem());
			        java.sql.ResultSet rs=pst.executeQuery();
			        	
			        
			        	while(rs.next()) 
			        	{
			        		update_codeIn.setText(rs.getString("Product_Code"));
			        		update_nameIn.setText(rs.getString("Name"));
			        		update_priceIn.setText(rs.getString("Price"));
			        		update_descripIn.setText(rs.getString("Description"));
			        		update_alergyIn.setText(rs.getString("Alergy"));
			        		sec.setText(rs.getString("Section"));
			        		
			        		byte[] img = rs.getBytes("Image");
		                    //Resize The ImageIcon
		                    ImageIcon image = new ImageIcon(img);
		                    Image im = image.getImage();
		                    Image myImg = im.getScaledInstance(image_update.getWidth(), image_update.getHeight(),Image.SCALE_SMOOTH);
		                    ImageIcon newImage = new ImageIcon(myImg);
		                    image_update.setIcon(newImage);
		                    
			        	}
			        	
			        	pst.close();
						} catch (SQLException | ClassNotFoundException e1) 
			    	{
							
							System.out.print(e1);
						}
						
			        
			}
		});
		update_main.setFont(new Font("Open Sans", Font.PLAIN, 25));
		update_main.setBounds(12, 148, 262, 43);
		panel_update.add(update_main);
		
		
       	        
		
		JLabel lblNewLabel_2 = new JLabel("Side:");
		lblNewLabel_2.setFont(new Font("Open Sans", Font.BOLD, 30));
		lblNewLabel_2.setBounds(12, 207, 78, 26);
		panel_update.add(lblNewLabel_2);
		
		JComboBox update_side = new JComboBox();
		update_side.setBackground(Color.WHITE);
		
		update_side.setFont(new Font("Open Sans", Font.PLAIN, 25));
		update_side.setBounds(12, 246, 262, 43);
		panel_update.add(update_side);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
	        statement=conn.createStatement(); 
			String s = "Select Name from product WHERE Section = 'Side';";
			ResultSet rs = statement.executeQuery(s);
			
			while(rs.next())
			{
				update_side.addItem(rs.getString(1));
			}
		}
			catch (Exception e3)
			{
				System.out.print(e3);
				
			}
		
		update_side.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					Class.forName("com.mysql.jdbc.Driver");
			        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
			        statement=conn.createStatement(); 
			        
			        String s = "Select Product_Code, Name, Price, Description, Section, Alergy, Image from fast_food.product WHERE Section = 'Side' and Name = ?";
			        PreparedStatement pst = conn.prepareStatement(s);
			        pst.setString(1, (String)update_side.getSelectedItem());
			        java.sql.ResultSet rs=pst.executeQuery();
			        	
			        
			        	while(rs.next()) 
			        	{
			        		update_codeIn.setText(rs.getString("Product_Code"));
			        		update_nameIn.setText(rs.getString("Name"));
			        		update_priceIn.setText(rs.getString("Price"));
			        		update_descripIn.setText(rs.getString("Description"));
			        		update_alergyIn.setText(rs.getString("Alergy"));
			        		sec.setText(rs.getString("Section"));
			        		
			        		byte[] img = rs.getBytes("Image");
		                    //Resize The ImageIcon
		                    ImageIcon image = new ImageIcon(img);
		                    Image im = image.getImage();
		                    Image myImg = im.getScaledInstance(image_update.getWidth(), image_update.getHeight(),Image.SCALE_SMOOTH);
		                    ImageIcon newImage = new ImageIcon(myImg);
		                    image_update.setIcon(newImage);
			        	}
			        	
			        	pst.close();
						} catch (SQLException | ClassNotFoundException e1) 
			    	{
							
							System.out.print(e1);
						}
						
			        
			
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("Dessert:");
		lblNewLabel_3.setFont(new Font("Open Sans", Font.BOLD, 30));
		lblNewLabel_3.setBounds(12, 302, 138, 26);
		panel_update.add(lblNewLabel_3);
		
		JComboBox update_dessert = new JComboBox();
		update_dessert.setBackground(Color.WHITE);
		
		update_dessert.setFont(new Font("Open Sans", Font.PLAIN, 25));
		update_dessert.setBounds(12, 343, 262, 43);
		panel_update.add(update_dessert);
		
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
	        statement=conn.createStatement(); 
			String s = "Select Name from product WHERE Section = 'Dessert';";
			ResultSet rs = statement.executeQuery(s);
			
			while(rs.next())
			{
				update_dessert.addItem(rs.getString(1));
			}
		}
			catch (Exception e3)
			{
				System.out.print(e3);
				
			}
		
		update_dessert.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					Class.forName("com.mysql.jdbc.Driver");
			        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
			        statement=conn.createStatement(); 
			        
			        String s = "Select Product_Code, Name, Price, Description, Section, Alergy, Image from fast_food.product WHERE Section = 'Dessert' and Name = ?";
			        PreparedStatement pst = conn.prepareStatement(s);
			        pst.setString(1, (String)update_dessert.getSelectedItem());
			        java.sql.ResultSet rs=pst.executeQuery();
			        	
			        
			        	while(rs.next()) 
			        	{
			        		update_codeIn.setText(rs.getString("Product_Code"));
			        		update_nameIn.setText(rs.getString("Name"));
			        		update_priceIn.setText(rs.getString("Price"));
			        		update_descripIn.setText(rs.getString("Description"));
			        		update_alergyIn.setText(rs.getString("Alergy"));
			        		sec.setText(rs.getString("Section"));
			        		
			        		 byte[] img = rs.getBytes("Image");
			                    //Resize The ImageIcon
			                    ImageIcon image = new ImageIcon(img);
			                    Image im = image.getImage();
			                    Image myImg = im.getScaledInstance(image_update.getWidth(), image_update.getHeight(),Image.SCALE_SMOOTH);
			                    ImageIcon newImage = new ImageIcon(myImg);
			                    image_update.setIcon(newImage);
			        	}
			        	
			        	pst.close();
						} catch (SQLException | ClassNotFoundException e1) 
			    	{
							
							System.out.print(e1);
						}
						
			}
		});
		
		JLabel lblDrink = new JLabel("Drink:");
		lblDrink.setFont(new Font("Open Sans", Font.BOLD, 30));
		lblDrink.setBounds(12, 414, 116, 26);
		panel_update.add(lblDrink);
		
		JComboBox update_drink = new JComboBox();
		update_drink.setBackground(Color.WHITE);
		
		update_drink.setFont(new Font("Open Sans", Font.PLAIN, 25));
		update_drink.setBounds(12, 453, 262, 43);
		panel_update.add(update_drink);
		
		sec = new JTextField();
		sec.setFont(new Font("Open Sans", Font.PLAIN, 25));
		sec.setBounds(570, 375, 262, 43);
		panel_update.add(sec);
		sec.setColumns(10);
		
		JLabel label_7 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\edit.png"));
		label_7.setBounds(494, 862, 91, 66);
		panel_update.add(label_7);
		
		JLabel lblNewLabel_7 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\remove.png"));
		lblNewLabel_7.setBounds(782, 862, 91, 66);
		panel_update.add(lblNewLabel_7);
		
		JLabel lblSelectNewImage = new JLabel("Select Image:");
		lblSelectNewImage.setFont(new Font("Open Sans", Font.PLAIN, 30));
		lblSelectNewImage.setBounds(345, 448, 189, 40);
		panel_update.add(lblSelectNewImage);
			
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
	        statement=conn.createStatement(); 
			String s = "Select Name from product WHERE Section = 'Drink';";
			ResultSet rs = statement.executeQuery(s);
			
			while(rs.next())
			{
				update_drink.addItem(rs.getString(1));
			}
			
		    }
			catch (Exception e3)
			{
				System.out.print(e3);
				
			}
		
		update_drink.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					Class.forName("com.mysql.jdbc.Driver");
			        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
			        statement=conn.createStatement(); 
			        
			        String s = "Select Product_Code, Name, Price, Description, Section, Alergy, Image from fast_food.product WHERE Section = 'Drink' and Name = ?";
			        PreparedStatement pst = conn.prepareStatement(s);
			        pst.setString(1, (String)update_drink.getSelectedItem());
			        java.sql.ResultSet rs=pst.executeQuery();
			        	
			        
			        	while(rs.next()) 
			        	{
			        		update_codeIn.setText(rs.getString("Product_Code"));
			        		update_nameIn.setText(rs.getString("Name"));
			        		update_priceIn.setText(rs.getString("Price"));
			        		update_descripIn.setText(rs.getString("Description"));
			        		update_alergyIn.setText(rs.getString("Alergy"));
			        		sec.setText(rs.getString("Section"));
			        		
			        		byte[] img = rs.getBytes("Image");
		                    //Resize The ImageIcon
		                    ImageIcon image = new ImageIcon(img);
		                    Image im = image.getImage();
		                    Image myImg = im.getScaledInstance(image_update.getWidth(), image_update.getHeight(),Image.SCALE_SMOOTH);
		                    ImageIcon newImage = new ImageIcon(myImg);
		                    image_update.setIcon(newImage);
			        	}
			        	
			        	pst.close();
						} catch (SQLException | ClassNotFoundException e1) 
			    	{
							
							System.out.print(e1);
						}
						
			
			}
		});
		
		
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		//PANEL ADD USER
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		JLabel user_email = new JLabel("Email:");
		user_email.setFont(new Font("Open Sans", Font.PLAIN, 30));
		user_email.setBounds(458, 435, 105, 26);
		panel_user.add(user_email);
		
		JLabel user_pass = new JLabel("Password:");
		user_pass.setFont(new Font("Open Sans", Font.PLAIN, 30));
		user_pass.setBounds(458, 551, 142, 28);
		panel_user.add(user_pass);
		
		user_emailIn = new JTextField();
		user_emailIn.setFont(new Font("Open Sans", Font.PLAIN, 19));
		user_emailIn.setBounds(629, 432, 262, 42);
		panel_user.add(user_emailIn);
		user_emailIn.setColumns(10);
		user_emailIn.setText(null);
		
		user_passwordIn = new JPasswordField();
		user_passwordIn.setFont(new Font("Open Sans", Font.PLAIN, 19));
		user_passwordIn.setBounds(629, 548, 262, 43);
		panel_user.add(user_passwordIn);
		user_passwordIn.setText(null);

		JButton user_OK = new JButton("Create User");
		user_OK.setBorderPainted(false);
		user_OK.setContentAreaFilled(false);
		user_OK.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				 try {

					 // CODE THAT WILL DISPLAY JTEXTBOX IF THE PASSWORDS OR EMAIL ARE NOT ENTERED CORRECTLY 
					 String email = user_emailIn.getText();
			         String password = user_passwordIn.getText();
				
					 // IF NO DATA ENTERERD TO JTEXTFIELD WILL POP UP ERROR
					 if(e.getSource() == user_OK && email.equals("") || password.equals(""))
					 {

						 JOptionPane.showMessageDialog(null, "Cannot be left blank, TRY AGAIN","TRY AGAIN", JOptionPane.ERROR_MESSAGE);
					 }
		         
		             // IF EMAIL DOES NOT CONTAIN @ OR .com WILL POP UP BOX INVALID EMIAL ADDRESS
					 else if(e.getSource() == user_OK && !email.contains(".com") || !email.contains("@"))
					 {
						 JOptionPane.showMessageDialog(null, "invalid email, TRY AGAIN","TRY AGAIN", JOptionPane.ERROR_MESSAGE);
					 }
					 
					// try {
					 else {
					//CODE TO VERFIY THE EMAIL ADDRESS AND PASSWORD FROM THE DATABASE 
					Class.forName("com.mysql.jdbc.Driver");
			        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
			        statement=conn.createStatement(); 
			        
			        String emailAd = user_emailIn.getText();
			        char[] passwd = user_passwordIn.getPassword();
			        String password1 = new String(passwd);
			        
			        
			       // if(passwd != null && emailAd != null)
			      //  {
			        	//String pass = new String(passwd);
			        	String query5 ="INSERT INTO manager values(?,?)";
			        	
			        	PreparedStatement ps = conn.prepareStatement(query5);
			        	ps.setString(1, user_emailIn.getText());
			        	ps.setString(2, user_passwordIn.getText());
			        	
			        	
			        	ps.execute();
  			            JOptionPane.showMessageDialog(null, "User was Registered as Admin","User Added", JOptionPane.INFORMATION_MESSAGE);
  			          user_emailIn.setText(null);
 			          user_passwordIn.setText(null);
			        		/*
			        //	try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM manager where Email = ? and Password = ?"))
			        			{
			        				stmt.setString(1, email);
			        				stmt.setString(2, password1);

			        				ResultSet rs =stmt.executeQuery();
			        				
			        				if(rs.next())
			        				{
			     			           JOptionPane.showMessageDialog(null, "User was Registered as Admin","User Added", JOptionPane.INFORMATION_MESSAGE);
			     			           panel_user.setVisible(false);
			     			           panelChoose.setVisible(true);
			     			           
			        				}
			        				
			        				else
			        				{
			    			        	 JOptionPane.showMessageDialog(null, "User was not created","Please Try again", JOptionPane.ERROR_MESSAGE);

			        				}
			        			}
			        	 */
			        	 ps.close();
			        	 conn.close();
			        	// rs.close();
			     // 
					 }
				}catch(Exception ee) 
				{

					System.out.println(ee);

				}
				
			}
		});
		user_OK.setFont(new Font("Open Sans", Font.BOLD, 30));
		user_OK.setBounds(357, 799, 243, 42);
		panel_user.add(user_OK);
		
		JLabel lblInsertNewUser = new JLabel("Insert New User Details");
		lblInsertNewUser.setForeground(new Color(239, 33, 33));
		lblInsertNewUser.setFont(new Font("Open Sans", Font.BOLD | Font.ITALIC, 60));
		lblInsertNewUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsertNewUser.setBounds(336, 233, 742, 54);
		panel_user.add(lblInsertNewUser);
		
		JButton btnCancel_2 = new JButton("Cancel");
		btnCancel_2.setBorderPainted(false);
		btnCancel_2.setContentAreaFilled(false);
		btnCancel_2.setFont(new Font("Open Sans", Font.BOLD, 30));
		btnCancel_2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panel_user.setVisible(false);
				panel_update.setVisible(false);
				panelChoose.setVisible(true);
				user_emailIn.setText(null);
		        user_passwordIn.setText(null);
			}
		});
		btnCancel_2.setBounds(765, 799, 168, 42);
		panel_user.add(btnCancel_2);
		
		
		JButton btnViewUsers = new JButton("View Users");
		btnViewUsers.setFont(new Font("Open Sans", Font.BOLD, 30));
		btnViewUsers.setBorderPainted(false);
		btnViewUsers.setContentAreaFilled(false);
		
		btnViewUsers.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panel_user.setVisible(true);
				View_Users view = new View_Users();
				view.main(null);
			}
		});
		btnViewUsers.setBounds(541, 884, 256, 42);
		panel_user.add(btnViewUsers);
		
		JLabel label_8 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\newuser.png"));
		label_8.setBounds(334, 786, 56, 72);
		panel_user.add(label_8);
		
		JLabel label_9 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\remove.png"));
		label_9.setBounds(716, 788, 105, 70);
		panel_user.add(label_9);
		
		JLabel label_10 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\list.png"));
		label_10.setBounds(495, 864, 105, 75);
		panel_user.add(label_10);
		
	
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


		
	}
}

	      


