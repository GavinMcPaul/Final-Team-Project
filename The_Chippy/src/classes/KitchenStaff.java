package classes;

import java.awt.EventQueue;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.Button;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.border.BevelBorder;

public class KitchenStaff {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	private JFrame frame;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitchenStaff window = new KitchenStaff();
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
	public KitchenStaff() {
		initialize();
		//conn = ConnectionDB.connectDB();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//frame.setBounds(100, 100, 659, 385);
		frame.setBounds(100, 100, 1360, 613);
		frame.getContentPane().setBackground(new Color(255, 244, 147));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


///////////////////// ORDER STATUS COMBO BOX ////////////////////////////////////////////////	
		JLabel StatusLabel = new JLabel("Order Status");
		StatusLabel.setBounds(941, 488, 112, 43);
		StatusLabel.setForeground(new Color(0, 0, 0));
		StatusLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JComboBox<String> OrderStatus = new JComboBox<String>();
		OrderStatus.setBounds(1076, 494, 243, 30);
		OrderStatus.setForeground(new Color(0, 0, 0));
		OrderStatus.setBackground(Color.WHITE);
		OrderStatus.setFont(new Font("Tahoma", Font.PLAIN, 20));
		OrderStatus.setModel(new DefaultComboBoxModel(new String[] {"Not Ready", "Ready"}));
		try
		{
			java.sql.ResultSet rs;
			java.sql.Statement st;
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
			st = conn.createStatement();
			String s = "Select Order_Status from fast_food.order;";
			rs = st.executeQuery(s);
		}
		catch (Exception x2)
		{
			System.out.print(x2);
			JOptionPane.showMessageDialog(null, "Error");
		}

///////////////// ORDER NUMBER COMBO BOX /////////////////////////////////////////////////////	
		JLabel NumberLabel = new JLabel("Order Number");
		NumberLabel.setBounds(44, 488, 136, 43);
		NumberLabel.setForeground(new Color(0, 0, 0));
		NumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JComboBox<String> OrderNum = new JComboBox();
		OrderNum.setBounds(190, 494, 151, 31);
		OrderNum.setForeground(new Color(0, 0, 0));
		OrderNum.setBackground(new Color(255, 255, 255));
		OrderNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		OrderNum.setModel(new DefaultComboBoxModel(new String[] {"Select Order"}));
		try
		{
			java.sql.ResultSet rs;
			java.sql.Statement st;
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
			st = conn.createStatement();
			String s = "Select Order_Number from fast_food.order where order_status = 'Not Ready';";
			rs = st.executeQuery(s);
			while(rs.next())
			{
				OrderNum.addItem(rs.getString(1));
			}
		}
		catch (Exception x2)
		{
			System.out.print(x2);
			JOptionPane.showMessageDialog(null, "Error");
		}
		OrderNum.addActionListener(new ActionListener()
		{
		    public void actionPerformed(ActionEvent arg0) {
		    	try {
		    	Class.forName("com.mysql.jdbc.Driver");
				java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
		        	String s = "Select * from fast_food.order WHERE Order_Number = ?";

		        	if(OrderNum.getSelectedItem() != "Select Order")
		        	{		        				        	
		        	PreparedStatement pst = conn.prepareStatement(s);
		        	pst.setString(1, (String)OrderNum.getSelectedItem());
		        	java.sql.ResultSet rs=pst.executeQuery();
		        	table.setModel(DbUtils.resultSetToTableModel(rs));	
		        	} 
		        	else
		        	{		
		        		String se ="Select * from fast_food.order where order_status = 'Not Ready'";
		        		PreparedStatement pst = conn.prepareStatement(se);
			        	java.sql.ResultSet rs=pst.executeQuery();
			        	table.setModel(DbUtils.resultSetToTableModel(rs));	        		
		        	pst.close();}
					} catch (SQLException | ClassNotFoundException e1) 
		    	{						
						System.out.print(e1);
					}					
		        }
		    });
//////////////////////////// SET STATUS BUTTON //////////////////////////////////////////////////////		
		JButton SetStatus = new JButton("Set Status");
		SetStatus.setBounds(1021, 310, 198, 125);
		SetStatus.setForeground(new Color(0, 0, 0));
		SetStatus.setBackground(Color.WHITE);
		SetStatus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		SetStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
					
					String s = "UPDATE Fast_Food.order SET Order_Status = ? where Order_Number=?";
					PreparedStatement pst = conn.prepareStatement(s);
					pst.setString(1, (String)OrderStatus.getSelectedItem());
					
					pst.setString(2, (String)OrderNum.getSelectedItem());
					if(OrderNum.getSelectedItem() == "Select Order")
		        	{
		        		JOptionPane.showMessageDialog(null, "You may not have selected a number, please try again!","Invalid Selection", JOptionPane.INFORMATION_MESSAGE);
		        	}
		        	else {
		        	pst.setString(2, (String)OrderNum.getSelectedItem());
					int rs=pst.executeUpdate();
					pst = conn.prepareStatement(s);
					rs = pst.executeUpdate();
					pst.close();}
				}catch(SQLException e1) {
					System.out.print("");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
/////////////////////////////////// Load Table Button ////////////////////////////////////////////////////////	
		JButton LoadOrderButton = new JButton("View All Orders");
		LoadOrderButton.setBounds(81, 314, 217, 117);
		LoadOrderButton.setForeground(new Color(0, 0, 0));
		LoadOrderButton.setBackground(Color.WHITE);
		LoadOrderButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LoadOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
					String query="Select * from Fast_Food.order where Order_Status = 'Not Ready'";

					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();				
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 1330, 277);
////////////////////////////// DISPLAY ORDER ITEM //////////////////////////////////////////////////////////		
		JButton DisplayOrderItem = new JButton("View Order Details");
		DisplayOrderItem.setBounds(524, 314, 232, 117);
		DisplayOrderItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
					
				   String query = "select fast_food.order_item.Order_Number, Name, Quantity, Total_Price, Order_Status, TimeDate from fast_food.order, order_item, product where fast_food.order.Order_Number = order_item.Order_Number && order_item.Product_Code = product.Product_Code && fast_food.order.Order_Number = ?;";

					PreparedStatement pst = conn.prepareStatement(query);
		        	pst.setString(1, (String)OrderNum.getSelectedItem());
		        	if(OrderNum.getSelectedItem() == "Select Order")
		        	{		        		
		        		JOptionPane.showMessageDialog(null, "You may not have selected a number, please try again!","Invalid Selection", JOptionPane.INFORMATION_MESSAGE);
		        	}
		        	else {
		        	pst.setString(1, (String)OrderNum.getSelectedItem());
		        	java.sql.ResultSet rs=pst.executeQuery();
		        	table.setModel(DbUtils.resultSetToTableModel(rs));				        
		        	pst.close();}
					} catch (SQLException | ClassNotFoundException e1) 
		    	{						
						System.out.print(e1);
					}					
		        }
			
		    });
		DisplayOrderItem.setForeground(Color.BLACK);
		DisplayOrderItem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		DisplayOrderItem.setBackground(Color.WHITE);

		table = new JTable();
		table.setRowHeight(35);
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane_1.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Order_Number", "Total_Cost", "TimeDate", "Order_Status"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(89);
		table.getColumnModel().getColumn(3).setPreferredWidth(79);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(scrollPane_1);
		frame.getContentPane().add(LoadOrderButton);
		frame.getContentPane().add(DisplayOrderItem);
		frame.getContentPane().add(SetStatus);
		frame.getContentPane().add(NumberLabel);
		frame.getContentPane().add(OrderNum);
		frame.getContentPane().add(StatusLabel);
		frame.getContentPane().add(OrderStatus);
	}
}