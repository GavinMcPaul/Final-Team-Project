
package classes;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.Image;
import java.awt.Window;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import com.sun.javafx.tk.Toolkit;
import com.sun.jmx.snmp.Timestamp;
import java.math.*;



import javafx.scene.control.TableColumn;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.CardLayout;

public class GUI {

	protected static final ResultSet Statement = null;
	private JFrame frmMenu;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public GUI() {
		initialize();
	}

	
	private void initialize() {
		frmMenu = new JFrame();

		frmMenu.getContentPane().setBackground(new Color(255, 224, 147));
		frmMenu.setTitle("MENU");
		frmMenu.setBounds(100, 100, 1638, 947);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		
		
		JLabel name = new JLabel("");
		
		JLabel ImageLabel = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\logo.JPG"));
		ImageLabel.setBounds(450, 103, 432, 364);
		frmMenu.getContentPane().add(ImageLabel);
		
		JTextField pc = new JTextField();
		

		
		JTextArea PriceBox = new JTextArea();
		PriceBox.setFont(new Font("Open Sans", Font.PLAIN, 25));
		PriceBox.setText("");
		//PriceBox.setFormat("%.2f");
		PriceBox.setEditable(false);
		PriceBox.setBounds(568, 763, 114, 34);
		frmMenu.getContentPane().add(PriceBox);
		
		JTextArea DescriptionBox = new JTextArea();
		DescriptionBox.setLineWrap(true);
		DescriptionBox.setWrapStyleWord(true);


		DescriptionBox.setFont(new Font("Open Sans", Font.PLAIN, 25));
		DescriptionBox.setText("");
		DescriptionBox.setEditable(false);
		DescriptionBox.setBounds(568, 507, 345, 100);
		frmMenu.getContentPane().add(DescriptionBox);
		
		JTextArea AllergenBox = new JTextArea();
		AllergenBox.setLineWrap(true);
		AllergenBox.setWrapStyleWord(true);


		AllergenBox.setFont(new Font("Open Sans", Font.PLAIN, 25));
		AllergenBox.setText("");
		AllergenBox.setEditable(false);
		AllergenBox.setBounds(568, 633, 345, 100);
		frmMenu.getContentPane().add(AllergenBox);
		String columns[] = new String[] {"Product Code", "Product", "Quantity", "Price"};
		DefaultTableModel dtm = new DefaultTableModel(columns, 0);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.WHITE);
		scrollPane_1.setFont(new Font("Open Sans", Font.PLAIN, 15));
		scrollPane_1.setBounds(973, 94, 621, 475);
		frmMenu.getContentPane().add(scrollPane_1);
		
		JTable OrderSummary = new JTable();
		OrderSummary.setRowHeight(35);
		OrderSummary.setBackground(Color.WHITE);
		OrderSummary.setFont(new Font("Open Sans", Font.PLAIN, 25));
		scrollPane_1.setViewportView(OrderSummary);
		OrderSummary.setModel(dtm);
		
		OrderSummary.setAutoResizeMode(OrderSummary.AUTO_RESIZE_OFF);
		javax.swing.table.TableColumn col = OrderSummary.getColumnModel().getColumn(0);
		col.setPreferredWidth(100);
		col=OrderSummary.getColumnModel().getColumn(1);
		col.setPreferredWidth(300);
		col=OrderSummary.getColumnModel().getColumn(2);
		col.setPreferredWidth(100);
		col=OrderSummary.getColumnModel().getColumn(3);
		col.setPreferredWidth(100);
		OrderSummary.setDefaultEditor(Object.class, null);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Main's");
		lblNewLabel.setFont(new Font("Open Sans", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 42, 127, 34);
		frmMenu.getContentPane().add(lblNewLabel);
		
		JComboBox<String> MainCB = new JComboBox<String>();
		MainCB.setBackground(Color.WHITE);
		MainCB.setFont(new Font("Open Sans", Font.BOLD, 25));
		MainCB.setModel(new DefaultComboBoxModel<>(new String[] {"Select Main"}));
		MainCB.setBounds(10, 87, 300, 40);
		try
		{
			java.sql.ResultSet rs;
			java.sql.Statement st;
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
			st = conn.createStatement();
			String s = "Select Name from fast_food.product WHERE Section = 'Main';";
			rs = st.executeQuery(s);
			while(rs.next())
			{
				MainCB.addItem(rs.getString(1));
			}
		}
			catch (Exception x2)
			{
				System.out.print(x2);
				JOptionPane.showMessageDialog(null, "Error");
			}
		MainCB.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent arg0) {
		    	try {
		    	Class.forName("com.mysql.jdbc.Driver");
				java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
		        	String s = "Select * from fast_food.product WHERE Section = 'Main' and Name = ?";
		        	PreparedStatement pst = conn.prepareStatement(s);
		        	pst.setString(1, (String)MainCB.getSelectedItem());
		        	java.sql.ResultSet rs=pst.executeQuery();
		        	
		        
		        	while(rs.next()) 
		        	{
		        		PriceBox.setText(rs.getString("Price"));
		        		DescriptionBox.setText(rs.getString("Description"));
		        		AllergenBox.setText(rs.getString("Alergy"));
		        		name.setText(rs.getString("Name"));
		        		pc.setText(rs.getString("Product_Code"));
						
		        		
					
					 byte[] img = rs.getBytes("Image");
		                    //Resize The ImageIcon
		                    ImageIcon image = new ImageIcon(img);
		                    Image im = image.getImage();
		                    Image myImg = im.getScaledInstance(ImageLabel.getWidth(), ImageLabel.getHeight(),Image.SCALE_SMOOTH);
		                    ImageIcon newImage = new ImageIcon(myImg);
		                    ImageLabel.setIcon(newImage);
		        	}
		        	pst.close();
					} catch (SQLException | ClassNotFoundException e1) 
		    	{
						
						System.out.print(e1);
					}
					
		        }

			private JTextField setText(int int1) {
				// TODO Auto-generated method stub
				return null;
			}

		    });
			
		frmMenu.getContentPane().add(MainCB);
		
		JLabel lblSides = new JLabel("Side's");
		lblSides.setFont(new Font("Open Sans", Font.BOLD, 30));
		lblSides.setBounds(10, 161, 114, 34);
		frmMenu.getContentPane().add(lblSides);
		
		JComboBox<String> SideCB = new JComboBox<String>();
		SideCB.setFont(new Font("Open Sans", Font.BOLD, 25));
		SideCB.setBackground(Color.WHITE);
		SideCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Select Sides"}));
		SideCB.setBounds(10, 206, 300, 40);
		try
		{
			java.sql.ResultSet rs;
			java.sql.Statement st;
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
			st = conn.createStatement();
			String s = "Select Name from fast_food.product WHERE Section = 'Side';";
			rs = st.executeQuery(s);
			while(rs.next())
			{
				SideCB.addItem(rs.getString(1));
			}
		}
			catch (Exception x2)
			{
				System.out.print(x2);
				JOptionPane.showMessageDialog(null, "Error");
			}
		SideCB.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent arg0) {
		    	try {
		    	Class.forName("com.mysql.jdbc.Driver");
				java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
		        	String s = "Select * from fast_food.product WHERE Section = 'Side' and Name = ?";
		        	PreparedStatement pst = conn.prepareStatement(s);
		        	pst.setString(1, (String)SideCB.getSelectedItem());
		        	java.sql.ResultSet rs=pst.executeQuery();
		        
		        
		        	while(rs.next()) 
		        	{
		        		PriceBox.setText(rs.getString("Price"));
		        		DescriptionBox.setText(rs.getString("Description"));
		        		AllergenBox.setText(rs.getString("Alergy"));
		        		name.setText(rs.getString("Name"));
		        		pc.setText(rs.getString("Product_Code"));
					
					 byte[] img = rs.getBytes("Image");
		                    //Resize The ImageIcon
		                    ImageIcon image = new ImageIcon(img);
		                    Image im = image.getImage();
		                    Image myImg = im.getScaledInstance(ImageLabel.getWidth(), ImageLabel.getHeight(),Image.SCALE_SMOOTH);
		                    ImageIcon newImage = new ImageIcon(myImg);
		                    ImageLabel.setIcon(newImage);
		        		
		        	}
		        	pst.close();
					} catch (SQLException | ClassNotFoundException e1) 
		    	{
						
						System.out.print(e1);
					}
					
		        }

		    });
		frmMenu.getContentPane().add(SideCB);
		
		JLabel lblDesserts = new JLabel("Dessert's");
		lblDesserts.setFont(new Font("Open Sans", Font.BOLD, 30));
		lblDesserts.setBounds(10, 280, 164, 34);
		frmMenu.getContentPane().add(lblDesserts);
		
		JComboBox<String> DessertCB = new JComboBox<String>();
		DessertCB.setFont(new Font("Open Sans", Font.BOLD, 25));
		DessertCB.setBackground(Color.WHITE);
		DessertCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Select Dessert"}));
		DessertCB.setBounds(10, 325, 300, 40);
		try
		{
			java.sql.ResultSet rs;
			java.sql.Statement st;
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
			st = conn.createStatement();
			String s = "Select Name from fast_food.product WHERE Section = 'Dessert';";
			rs = st.executeQuery(s);
			while(rs.next())
			{
				DessertCB.addItem(rs.getString(1));
			}
		}
			catch (Exception x2)
			{
				System.out.print(x2);
				JOptionPane.showMessageDialog(null, "Error");
			}
		DessertCB.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent arg0) {
		    	try {
		    	Class.forName("com.mysql.jdbc.Driver");
				java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
		        	String s = "Select * from fast_food.product WHERE Section = 'Dessert' and Name = ?";
		        	PreparedStatement pst = conn.prepareStatement(s);
		        	pst.setString(1, (String)DessertCB.getSelectedItem());
		        	java.sql.ResultSet rs=pst.executeQuery();
		        	
		        
		        	while(rs.next()) 
		        	{
		        		PriceBox.setText(rs.getString("Price"));
		        		DescriptionBox.setText(rs.getString("Description"));
		        		AllergenBox.setText(rs.getString("Alergy"));
		        		name.setText(rs.getString("Name"));
		        		pc.setText(rs.getString("Product_Code"));
					
					 byte[] img = rs.getBytes("Image");
		                    //Resize The ImageIcon
		                    ImageIcon image = new ImageIcon(img);
		                    Image im = image.getImage();
		                    Image myImg = im.getScaledInstance(ImageLabel.getWidth(), ImageLabel.getHeight(),Image.SCALE_SMOOTH);
		                    ImageIcon newImage = new ImageIcon(myImg);
		                    ImageLabel.setIcon(newImage);
		        	}
		        	pst.close();
					} catch (SQLException | ClassNotFoundException e1) 
		    	{
						
						System.out.print(e1);
					}
					
		        }

		    });
		frmMenu.getContentPane().add(DessertCB);
		
		JLabel lblDrinks = new JLabel("Drink's");
		lblDrinks.setFont(new Font("Open Sans", Font.BOLD, 30));
		lblDrinks.setBounds(10, 399, 142, 34);
		frmMenu.getContentPane().add(lblDrinks);
		
		JComboBox<String> DrinkCB = new JComboBox<String>();
		DrinkCB.setFont(new Font("Open Sans", Font.BOLD, 25));
		DrinkCB.setBackground(Color.WHITE);
		DrinkCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Select Drink"}));
		DrinkCB.setBounds(10, 445, 300, 40);
		try
		{
			java.sql.ResultSet rs;
			java.sql.Statement st;
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
			st = conn.createStatement();
			String s = "Select Name from fast_food.product WHERE Section = 'Drink';";
			rs = st.executeQuery(s);
			while(rs.next())
			{
				DrinkCB.addItem(rs.getString(1));
			}
		}
			catch (Exception x2)
			{
				System.out.print(x2);
				JOptionPane.showMessageDialog(null, "Error");
			}
		
		DrinkCB.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent arg0) {
		    	try {
		    	Class.forName("com.mysql.jdbc.Driver");
				java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
		        	String s = "Select * from fast_food.product WHERE Section = 'Drink' and Name = ?";
		        	PreparedStatement pst = conn.prepareStatement(s);
		        	pst.setString(1, (String)DrinkCB.getSelectedItem());
		        	java.sql.ResultSet rs=pst.executeQuery();
		        	
		        
		        	while(rs.next()) 
		        	{
		        		PriceBox.setText(rs.getString("Price"));
		        		DescriptionBox.setText(rs.getString("Description"));
		        		AllergenBox.setText(rs.getString("Alergy"));
		        		name.setText(rs.getString("Name"));
		        		pc.setText(rs.getString("Product_Code"));
					
					 byte[] img = rs.getBytes("Image");
		                  
		                    ImageIcon image = new ImageIcon(img);
		                    Image im = image.getImage();
		                    Image myImg = im.getScaledInstance(ImageLabel.getWidth(), ImageLabel.getHeight(),Image.SCALE_SMOOTH);
		                    ImageIcon newImage = new ImageIcon(myImg);
		                    ImageLabel.setIcon(newImage);
		        	}
		        	pst.close();
					} catch (SQLException | ClassNotFoundException e1) 
		    	{		
						System.out.print(e1);
					}
					
		        }

		    });
		frmMenu.getContentPane().add(DrinkCB);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Open Sans", Font.PLAIN, 30));
		lblDescription.setBounds(369, 541, 187, 38);
		frmMenu.getContentPane().add(lblDescription);
		
		
		
		JLabel lblAllergens = new JLabel("Allergens:");
		lblAllergens.setFont(new Font("Open Sans", Font.PLAIN, 30));
		lblAllergens.setBounds(371, 660, 142, 40);
		frmMenu.getContentPane().add(lblAllergens);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Open Sans", Font.PLAIN, 30));
		lblPrice.setBounds(369, 767, 114, 27);
		frmMenu.getContentPane().add(lblPrice);
		
		JLabel lblOrderSummary = new JLabel("Order Summary:");
		lblOrderSummary.setFont(new Font("Open Sans", Font.BOLD, 30));
		lblOrderSummary.setBounds(973, 42, 294, 34);
		frmMenu.getContentPane().add(lblOrderSummary);
		

		JTextField FinalPrice = new JTextField();
		FinalPrice.setBounds(1096, 575, 93, 32);
		frmMenu.getContentPane().add(FinalPrice);
		FinalPrice.setColumns(10);
		FinalPrice.setFont(new Font("Open Sans", Font.PLAIN, 25));
		FinalPrice.setEditable(false);
	  
	    
		
		JButton DeleteBtn = new JButton("Delete");
		DeleteBtn.setContentAreaFilled(false);
		DeleteBtn.setBorderPainted(false);
		DeleteBtn.setFont(new Font("Open Sans", Font.BOLD, 25));
		DeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel dtm = (DefaultTableModel) OrderSummary.getModel();
				
				int sRow = OrderSummary.getSelectedRow();
				
				dtm.removeRow(sRow);
				int rows = OrderSummary.getRowCount();
				double total = 0;
				
				for(int i =0; i<rows; i++)
				{
					total=total+Double.parseDouble(OrderSummary.getValueAt(i, 3)+"");
				}
			         
				//String res = String.format("%.2f", total);
				String res = String.format("%.2f", total);

			    FinalPrice.setText(res);
			}
		});
		DeleteBtn.setBounds(1137, 726, 294, 40);
		frmMenu.getContentPane().add(DeleteBtn);
		
		
		
		
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinner.setFont(new Font("Open Sans", Font.BOLD, 20));
		spinner.setBounds(853, 763, 60, 34);
		frmMenu.getContentPane().add(spinner);
		
		
		
		JButton addBtn = new JButton("Add To Order");
		addBtn.setContentAreaFilled(false);
		addBtn.setBorderPainted(false);
		addBtn.setFont(new Font("Open Sans", Font.BOLD, 30));
		addBtn.setBounds(595, 835, 314, 52);
		frmMenu.getContentPane().add(addBtn);
		
		
		addBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				

				Double sprice = Double.parseDouble(( PriceBox).getText());
				String fname = name.getText();
				
				Integer fQuan = (Integer)spinner.getValue();
				
				int Finalpc = Integer.parseInt(pc.getText());
				
				DefaultTableModel dtm = (DefaultTableModel) OrderSummary.getModel();
				
				int rowss = OrderSummary.getRowCount();
				int cols = OrderSummary.getColumnCount();
				
				DecimalFormat df = new DecimalFormat("#.00"); 
				double fprice = sprice * fQuan;
				
				//String res = String.format("%.2f", total)
				
				dtm.addRow(new Object[]{Finalpc, fname, fQuan, df.format(fprice)});
				
				spinner.setValue(1);
				
				
				int rows = OrderSummary.getRowCount();
				double total = 0;
				
				for(int i =0; i<rows; i++)
				{
					total=total+Double.parseDouble(OrderSummary.getValueAt(i, 3)+"");
				}
			          
				//String res = Double.toString(total);

				String res = String.format("%.2f", total);

			    FinalPrice.setText(res);
			    
				
			}
		});
	
		
		JButton ConfirmBtn = new JButton("Confirm Order");
		ConfirmBtn.setFont(new Font("Open Sans", Font.BOLD, 25));
		ConfirmBtn.setContentAreaFilled(false);
		ConfirmBtn.setBorderPainted(false);
		ConfirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int r = OrderSummary.getRowCount();
				if(r==0)
				{
					JOptionPane.showMessageDialog(null, "Please select an item to Order!", null, JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				String price = FinalPrice.getText();
				
				Payment payment = new Payment();
				payment.main(null);
				
				java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
				
				//RETURN_GENERATED_KEYS
				int orderNum = -1;
				System.out.print(price);
				try 
				{
			    	Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
			        java.sql.ResultSet rs;
			        
			        
			       String s = "INSERT INTO fast_food.Order (Total_Cost, TimeDate, Order_Status) VALUES(?,?,?)";
			       PreparedStatement pst = conn.prepareStatement(s);
			       Statement stmt = conn.createStatement();

			        double fprice = Double.parseDouble(FinalPrice.getText());
			        	
			        	pst.setDouble(1, fprice);
			        	pst.setTimestamp(2, date);
			        	pst.setString(3, "Not Ready");
			        	
			        	pst.executeUpdate();
			  
			        	// int autoIncKeyFromFunc = -1;
			        	    rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");

			        	    if (rs.next())
			        	    {
			        	    	orderNum = rs.getInt(1);
			        	    } else 
			        	    {
			        	        // throw an exception from here
			        	    }

			        	    System.out.println("Key returned from " +
			        	                       "'SELECT LAST_INSERT_ID()': " +
			        	                       orderNum);

			        	
			       conn.close();
			       pst.close();
				}
				catch(Exception e5)
				{
					System.out.print(e5);
				}
				
				
						
				try 
				{
			    	Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
			        java.sql.ResultSet rs;
			        conn.setAutoCommit(false);

			        
			        String s = "INSERT INTO fast_food.Order_Item (Order_Number, Product_Code, Quantity, Total_Price) VALUES(?,?,?,?)";
			        PreparedStatement pst = conn.prepareStatement(s);
			  
			        
			        int orderNo = orderNum;
			        int data =OrderSummary.getRowCount();
			        for(int r1=0; r1<data; r1++)
			        {
			        	
			        	int pc = (Integer)OrderSummary.getValueAt(r1, 0);
			        	//String name1 = (String)OrderSummary.getValueAt(r1, 1);
			        	int quan = (Integer)OrderSummary.getValueAt(r1, 2);
			        	String rprice = (String)OrderSummary.getValueAt(r1, 3);
			        	
			        	double fprice = Double.parseDouble(rprice);
			        	
			        	
			        	pst.setInt(1, orderNo);
			        	pst.setInt(2, pc);
			        	pst.setInt(3, quan);
			        	pst.setDouble(4, fprice);
			        	
			        	pst.addBatch();
			       }

			        pst.executeBatch();
			        conn.commit();
			       
				}
				catch(Exception e5)
				{
					System.out.print("wrong"+e5);
				}
			}
			}
		});
		ConfirmBtn.setBounds(1383, 632, 225, 40);
		frmMenu.getContentPane().add(ConfirmBtn);
    	
	JButton CancelBtn = new JButton("Cancel Order");
	CancelBtn.setFont(new Font("Open Sans", Font.BOLD, 25));
	CancelBtn.setContentAreaFilled(false);
	CancelBtn.setBorderPainted(false);
	CancelBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			WelcomeMenu wm = new WelcomeMenu();
			wm.main(null);
			
			
			JComponent comp = (JComponent) arg0.getSource();
	         Window win = SwingUtilities.getWindowAncestor(comp);
	         win.dispose();
		}
		}
	);
	CancelBtn.setBounds(998, 632, 212, 40);
	frmMenu.getContentPane().add(CancelBtn);
	
	JLabel lblTotal = new JLabel("Total:");
	lblTotal.setFont(new Font("Open Sans", Font.BOLD, 30));
	lblTotal.setBounds(973, 578, 101, 27);
	frmMenu.getContentPane().add(lblTotal);
	
	JLabel label = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\add.png"));
	label.setBounds(578, 826, 83, 74);
	frmMenu.getContentPane().add(label);
	
	JLabel label_1 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\remove.png"));
	label_1.setBounds(958, 629, 83, 53);
	frmMenu.getContentPane().add(label_1);
	
	JLabel label_2 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\ok.png"));
	label_2.setBounds(1338, 619, 93, 63);
	frmMenu.getContentPane().add(label_2);
	
	JLabel label_3 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\bin.png"));
	label_3.setBounds(1161, 711, 93, 69);
	frmMenu.getContentPane().add(label_3);
	
	JLabel lblNewLabel_1 = new JLabel("\u20AC");
	lblNewLabel_1.setFont(new Font("Open Sans Semibold", Font.BOLD, 35));
	lblNewLabel_1.setBounds(535, 758, 36, 40);
	frmMenu.getContentPane().add(lblNewLabel_1);
		
	JLabel label_4 = new JLabel("\u20AC");
	label_4.setFont(new Font("Open Sans Semibold", Font.BOLD, 35));
	label_4.setBounds(1074, 569, 36, 40);
	frmMenu.getContentPane().add(label_4);
	

	JLabel time_label = new JLabel("");
	time_label.setBounds(426, 26, 487, 30);
	frmMenu.getContentPane().add(time_label);
	time_label.setFont(new Font("Open Sans", Font.BOLD, 30));

	String timeStamp = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss").format(Calendar.getInstance().getTime());	
	time_label.setText(timeStamp + "");
	
	int delay = 1000; // milliseconds
	ActionListener al = new ActionListener() {
	public void actionPerformed(ActionEvent evt) {
		String date = new java.text.SimpleDateFormat("HH:mm:ss")
				.format(new java.util.Date(System.currentTimeMillis()));
		time_label.setText("" + Calendar.getInstance().getTime());
	}
};
	new Timer(delay, al).start();

	JLabel lblQuantity = new JLabel("Quantity:");
	lblQuantity.setFont(new Font("Open Sans", Font.PLAIN, 25));
	lblQuantity.setBounds(743, 765, 118, 34);
	frmMenu.getContentPane().add(lblQuantity);
	

	
	
}
}