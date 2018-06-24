package classes;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
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

import org.jcp.xml.dsig.internal.dom.DOMUtils;

import com.sun.glass.events.KeyEvent;
import com.sun.jmx.snmp.Timestamp;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.io.PrintWriter;

import javax.swing.JPanel;
import java.awt.CardLayout;


public class Payment extends JFrame {

	private JFrame frame;
	private JTextField payment_numberIn;
	private JTextField payment_ccvIn;
	
	//VARIABLES FOR DATABSE CONNECTION
	private String url = "jdbc:mysql://localhost:3306/";
	private String dbName = "fast_food?autoReconnect=true&useSSL=false";
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "root";
	private String passwordDB = "password";
	private Statement statement = null;   
    private ResultSet resultSet = null;
    private JTextField orderIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment window = new Payment();
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
	public Payment() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
	    
		frame.getContentPane().setFont(new Font("Open Sans", Font.BOLD, 20));
		frame.getContentPane().setBackground(new Color(255, 224, 147));

		frame.setBounds(100, 100, 1098, 775);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 224, 147));
		frame.getContentPane().add(panel, "name_2500531746099795");
		panel.setLayout(null);
		panel.setVisible(true);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 224, 147));
		frame.getContentPane().add(panel_1, "name_2500537793914788");
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		
		JLabel lblPleaseEnterCard = new JLabel("Please Enter Card Details");
		lblPleaseEnterCard.setBounds(23, 13, 929, 151);
		panel.add(lblPleaseEnterCard);
		lblPleaseEnterCard.setFont(new Font("Open Sans", Font.BOLD | Font.ITALIC, 50));
		
		JLabel lblCardType = new JLabel("Card Type:");
		lblCardType.setBounds(271, 232, 164, 28);
		panel.add(lblCardType);
		lblCardType.setFont(new Font("Open Sans", Font.BOLD, 25));
		
		JComboBox payment_cardIn = new JComboBox();
		payment_cardIn.setBounds(581, 231, 204, 34);
		panel.add(payment_cardIn);
		payment_cardIn.setBackground(Color.WHITE);
		payment_cardIn.setModel(new DefaultComboBoxModel(new String[] {"","Visa ", "Mastercard"}));
		payment_cardIn.setFont(new Font("Open Sans", Font.BOLD, 20));
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setBounds(271, 285, 116, 28);
		panel.add(lblNumber);
		lblNumber.setFont(new Font("Open Sans", Font.BOLD, 25));
		
		payment_numberIn = new JTextField();
		payment_numberIn.setBounds(581, 284, 204, 34);
		panel.add(payment_numberIn);
		
		payment_numberIn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent e) 
			{
				char vchar = e.getKeyChar();
				if(!(Character.isDigit(vchar)) 
						|| (vchar == KeyEvent.VK_BACKSPACE)
                         || (vchar == KeyEvent.VK_DELETE)) 
						{
                    e.consume();
                    
						}
                    
                   //JOptionPane.showMessageDialog(null, "NO LETTERS, NUMBERS ONLY","TRY AGAIN", JOptionPane.ERROR_MESSAGE);
                  
                    else if(payment_numberIn.getText().length() >= 15 )
                    {
						 JOptionPane.showMessageDialog(null, "NO MORE THAN 16 DIGITS","WARNING", JOptionPane.WARNING_MESSAGE);

                    }
				
                   
		
			}	
	
});
		payment_numberIn.setFont(new Font("Open Sans", Font.PLAIN, 20));
		payment_numberIn.setColumns(10);
		
		JLabel lblCsv = new JLabel("CCV:");
		lblCsv.setBounds(271, 341, 94, 34);
		panel.add(lblCsv);
		lblCsv.setFont(new Font("Open Sans", Font.BOLD, 25));
		
		payment_ccvIn = new JTextField();
		payment_ccvIn.setBounds(581, 343, 83, 34);
		panel.add(payment_ccvIn);
		payment_ccvIn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent e) 
			{
				char vchar = e.getKeyChar();
				if(!(Character.isDigit(vchar)) 
						|| (vchar == KeyEvent.VK_BACKSPACE)
                         || (vchar == KeyEvent.VK_DELETE)) 
						{
                    e.consume();
                    
						}
                    
                   //JOptionPane.showMessageDialog(null, "NO LETTERS, NUMBERS ONLY","TRY AGAIN", JOptionPane.ERROR_MESSAGE);
                  
                    else if(payment_ccvIn.getText().length() >= 3)
                    {
                    	 e.consume(); 
						 JOptionPane.showMessageDialog(null, "NO MORE THAN 3 DIGITS","WARNING", JOptionPane.WARNING_MESSAGE);
				         e.consume(); 

                    }
			}
			

		});
		
		
		payment_ccvIn.setToolTipText("3 DIGIT CODE AT THE BACK OF THE CARD");
		payment_ccvIn.setFont(new Font("Open Sans", Font.PLAIN, 20));
		payment_ccvIn.setColumns(10);
		
		JLabel lblEx = new JLabel("Expiry Date (MMM/YY):");
		lblEx.setBounds(271, 388, 300, 34);
		panel.add(lblEx);
		lblEx.setFont(new Font("Open Sans", Font.BOLD, 25));
		
		JComboBox payment_yearIn = new JComboBox();
		payment_yearIn.setBounds(702, 388, 83, 34);
		panel.add(payment_yearIn);
		payment_yearIn.setModel(new DefaultComboBoxModel(new String[] {"", "18", "19", "20", "21", "22", "23", "24", "25", "26"}));
		payment_yearIn.setBackground(Color.WHITE);
		payment_yearIn.setFont(new Font("Open Sans", Font.BOLD, 20));
		
		JComboBox payment_monthIn = new JComboBox();
		payment_monthIn.setBounds(581, 388, 83, 34);
		panel.add(payment_monthIn);
		payment_monthIn.setModel(new DefaultComboBoxModel(new String[] {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));
		payment_monthIn.setBackground(Color.WHITE);
		payment_monthIn.setFont(new Font("Open Sans", Font.BOLD, 20));
	
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(279, 484, 175, 51);
		panel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.dispose();
				try {
				Class.forName("com.mysql.jdbc.Driver");
		        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
		        statement=conn.createStatement(); 
				}
				
				catch(Exception ee)
				{
					
				}
				
				
			}
		});
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		btnCancel.setFont(new Font("Open Sans", Font.BOLD, 30));
		
		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\remove.png"));
		lblNewLabel_1.setBounds(252, 473, 83, 64);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("C:\\Users\\Administrator\\Desktop\\New Project Code\\src\\pay.png"));
		lblNewLabel.setBounds(490, 473, 105, 78);
		panel.add(lblNewLabel);
		
		
		
		JButton btnConfirm = new JButton("Pay");
		btnConfirm.setBounds(510, 484, 221, 51);
		panel.add(btnConfirm);
		
			btnConfirm.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e)
				{
					int orderNum = -1;

					   try 
					   {
						   String cardIn = (String)payment_cardIn.getSelectedItem();
						   String numberIn = payment_numberIn.getText();
						   String ccvIn = payment_ccvIn.getText();
						   String monthIn = (String)payment_monthIn.getSelectedItem();
						   String yearIn = (String)payment_yearIn.getSelectedItem();
						   
						   //numberIn.getDocument().getLength();

						   if(e.getSource() == btnConfirm && numberIn.equals("") || ccvIn.equals("") || ccvIn.equals("") || monthIn.equals("") || yearIn.equals(""))
							 {

								 JOptionPane.showMessageDialog(null, "Cannot be left blank, TRY AGAIN","TRY AGAIN", JOptionPane.ERROR_MESSAGE);
							 }
						   
						   else if(e.getSource() == btnConfirm && cardIn == "Visa" || numberIn.contains("4319"))
						   {
								 //JOptionPane.showMessageDialog(null, "Thank You", "Enjoy ", JOptionPane.ERROR_MESSAGE);
								 payment_cardIn.setSelectedItem("");
								 payment_numberIn.setText(null);
								 payment_ccvIn.setText(null);
								 payment_monthIn.setSelectedItem("");
								 payment_yearIn.setSelectedItem("");
						   }
						   
						   else if(e.getSource() == btnConfirm && cardIn == "Visa" || !numberIn.contains("4319"))
						   {
								 JOptionPane.showMessageDialog(null, "INVALID CARD", "TRY AGAIN ", JOptionPane.ERROR_MESSAGE);
								 payment_cardIn.setSelectedItem("");
								 payment_numberIn.setText(null);
								 payment_ccvIn.setText(null);
								 payment_monthIn.setSelectedItem("");
								 payment_yearIn.setSelectedItem("");

						   }
						   
						   else if(e.getSource() == btnConfirm && cardIn == "Mastercard" || numberIn.contains("5432"))
						   {
								// JOptionPane.showMessageDialog(null, "Thank You", "Enjoy ", JOptionPane.ERROR_MESSAGE);
								 payment_cardIn.setSelectedItem("");
								 payment_numberIn.setText(null);
								 payment_ccvIn.setText(null);
								 payment_monthIn.setSelectedItem("");
								 payment_yearIn.setSelectedItem("");
						   }
						   
						   else if(e.getSource() == btnConfirm && cardIn == "Mastercard" || !numberIn.contains("5432"))
						   {
								 JOptionPane.showMessageDialog(null, "INVALID CARD", "TRY AGAIN ", JOptionPane.ERROR_MESSAGE);
								 payment_cardIn.setSelectedItem("");
								 payment_numberIn.setText(null);
								 payment_ccvIn.setText(null);
								 payment_monthIn.setSelectedItem("");
								 payment_yearIn.setSelectedItem("");

						   }
						   
						   else if(e.getSource() == btnConfirm && cardIn == "Visa" || numberIn.contains("5432"))
						   {
								 JOptionPane.showMessageDialog(null, "INVALID CARD", "TRY AGAIN ", JOptionPane.ERROR_MESSAGE);
								 payment_cardIn.setSelectedItem("");
								 payment_numberIn.setText(null);
								 payment_ccvIn.setText(null);
								 payment_monthIn.setSelectedItem("");
								 payment_yearIn.setSelectedItem("");

						   }
						   
						   else if(e.getSource() == btnConfirm && cardIn == "Mastercard" || numberIn.contains("4319"))
						   {
								 JOptionPane.showMessageDialog(null, "INVALID CARD", "TRY AGAIN ", JOptionPane.ERROR_MESSAGE);
								 payment_cardIn.setSelectedItem("");
								 payment_numberIn.setText(null);
								 payment_ccvIn.setText(null);
								 payment_monthIn.setSelectedItem("");
								 payment_yearIn.setSelectedItem("");

						   }
						   
						   Class.forName("com.mysql.jdbc.Driver");
					        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
					        statement=conn.createStatement(); 
					        java.sql.ResultSet rs;

							if(numberIn.contains("4319") || numberIn.contains("5432") && cardIn != null && monthIn != null && yearIn != null)
							{
							
					        String s = "SELECT MAX(Order_Number) FROM fast_food.`order` ";
					       		        PreparedStatement pst = conn.prepareStatement(s);
					       		        ResultSet resultSet = pst.executeQuery();
					        		        while(resultSet.next())
					        		        {
					        		        	orderNum = resultSet.getInt(1);
					        		        }
					        			
					        			
						   
				        	    orderIn.setText(Integer.toString(orderNum));//Integer.toString(orderNum);
				        	    
				        		panel_1.setVisible(true);
				        		panel.setVisible(false);
				        		//GUI fm = new GUI();
								//fm.main(null);
				        		//fm.dispose();
							}
						  

					   }catch(Exception ee) 
						
					   {

						   	System.out.println(ee);
					
					  }
					  
					    int orderNum1=0;

					   try
					   {
						   
						   Class.forName("com.mysql.jdbc.Driver");
							java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
					        java.sql.ResultSet rs;
					        Statement stmt = conn.createStatement();
					        //java.sql.PreparedStatement pst;
					        
						    rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
						    //int orderNum1=0;
			        	    if (rs.next())
			        	    {
			        	    	orderNum1 = rs.getInt(1);
			        	    } else 
			        	    {
			        	        // throw an exception from here
			        	    }
			        	    
					   }catch(Exception e1)
			        	    {
			        	    	
			        	    }
					   
					    Double cost = 0.0;
					    String time = null;
					   try
					   {
						   Class.forName("com.mysql.jdbc.Driver");
							java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
					       
					        
			        	    String s1 = "Select TimeDate, Total_Cost from fast_food.order WHERE 'Order_Number' = ?";
			        	   // String s = "select product.Product_Code, Name, Quantity, Total_Price, TimeDate from fast_food.order, order_item, product where fast_food.order.Order_Number = order_item.Order_Number && order_item.Product_Code = product.Product_Code && fast_food.order_item.Order_Number = ?;";
				        	PreparedStatement pst = conn.prepareStatement(s1);
				        	pst.setInt(1, orderNum1);
				        	java.sql.ResultSet rst=pst.executeQuery();
				        	
			        		//cost = rst.getDouble("Total_Cost");

				        	
				        	while(rst.next()) 
				        	{
				        		
				        		cost = rst.getDouble("Total_Cost");
				        		time = rst.getString("TimeDate");
				        		
				        		
							
							 
				        	}
				        	
				        	//System.out.print(cost);
				        	pst.close();
				        	//out.close();
							} 
						   

					   catch(Exception ee) 
					   {
						   	System.out.println(ee);
					  }
					        
					   try
					   {
						   Class.forName("com.mysql.jdbc.Driver");
							java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
					       
					        
			        	    String s = "Select Product_Code, Quantity, Total_Price from fast_food.order_item WHERE 'Order_Number' = ?";
			        	   // String s = "select product.Product_Code, Name, Quantity, Total_Price, TimeDate from fast_food.order, order_item, product where fast_food.order.Order_Number = order_item.Order_Number && order_item.Product_Code = product.Product_Code && fast_food.order_item.Order_Number = ?;";
				        	PreparedStatement pst = conn.prepareStatement(s);
				        	pst.setInt(1, orderNum1);
				        	java.sql.ResultSet rst=pst.executeQuery();
				        	PrintWriter out = new PrintWriter("Recipt"+orderNum+".txt");
				        	
				        	out.println("*************** THE CHIPPY **************");
				        	out.println("*************** Port Road ***************");
				        	out.println("********** Letterkenny, Donegal *********");
				        	out.println("************ Tel: 0749154100 ************");
			        		out.println();
				        	out.println("Order Number: "+ orderNum);
				        	out.println("Order Placed: " + time);
				        	out.println("Product Code       Quantity      Total_Price");

				        	
				        	while(rst.next()) 
				        	{
				        		
				        		//out.print("Order Number " + rst.getInt("Order_Number"));
				        		
				        		out.print(rst.getInt("Product_Code"));
				        		out.print("                   " + rst.getInt("Quantity"));
				        		out.print("               €" + rst.getDouble("Total_Price"));
				        		out.println();
							
							 
				        	}
							   out.println("Total Cost: €" + cost);
							   out.println();
							   out.println("******** Thank you for your order *******");
							   out.println("**************** Enjoy ******************");


				        	pst.close();
				        	out.close();
							} 
					   
						   

					   catch(Exception ee) 
					   {
						   	System.out.println(ee);
					  }
				
				}});
			btnConfirm.setContentAreaFilled(false);
			btnConfirm.setBorderPainted(false);
			btnConfirm.setFont(new Font("Open Sans", Font.BOLD, 30));
			
			
			
			orderIn = new JTextField();
			orderIn.setBackground(new Color(255, 224, 147));
			orderIn.setFont(new Font("Tahoma", Font.BOLD, 50));
			orderIn.setBounds(421, 350, 162, 108);
			panel_1.add(orderIn);
			orderIn.setColumns(10);
			orderIn.setEditable(false);
			orderIn.setHorizontalAlignment(JTextField.CENTER);
			
		
			
			JLabel lblThankYourFor = new JLabel("THANK YOU FOR YOU ORDER ");
			lblThankYourFor.setFont(new Font("Open Sans", Font.BOLD, 50));
			lblThankYourFor.setBounds(138, 42, 930, 203);
			panel_1.add(lblThankYourFor);
			
			JLabel lblOrderNumber = new JLabel("Order Number:");
			lblOrderNumber.setFont(new Font("Open Sans", Font.BOLD, 30));
			lblOrderNumber.setBounds(386, 273, 322, 83);
			panel_1.add(lblOrderNumber);
			
			JLabel lblEnjoy = new JLabel("ENJOY ");
			lblEnjoy.setFont(new Font("Open Sans", Font.BOLD, 50));
			lblEnjoy.setBounds(421, 533, 177, 108);
			panel_1.add(lblEnjoy);
			
			JLabel lblYour = new JLabel("Your");
			lblYour.setFont(new Font("Open Sans", Font.BOLD, 30));
			lblYour.setBounds(458, 237, 88, 61);
			panel_1.add(lblYour);
		
			
			
		
	}
}
