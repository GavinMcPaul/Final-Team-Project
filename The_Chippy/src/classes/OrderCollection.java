package classes;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class OrderCollection {
	
	/* Database Variables */
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ppdStmt = null;

	private JFrame frame;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderCollection window = new OrderCollection();
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
	public OrderCollection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 950, 597);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(255, 244, 147));
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 914, 351);
		frame.getContentPane().add(scrollPane);
		
		/******************* ORDER STATUS LABEL AND COMBO BOX ********************/

		
		table_1 = new JTable();
		table_1.setRowHeight(35);
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Order No.", "Order Status", "Time"
			}
			
			
		));
		try {
			
		
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
		String query="Select order_number, order_status, TimeDate from Fast_Food.order where Order_Status = 'Ready'";

		PreparedStatement pst = conn.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		table_1.setModel(DbUtils.resultSetToTableModel(rs));
		
		}catch(Exception e)
		{
			System.out.print(e);
		}
		
		
		/*********************** REFRESH BUTTON **************************/
		JButton refreshBtn = new JButton("Refresh");
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
					String query="Select order_number, order_status, TimeDate from Fast_Food.order where Order_Status = 'Ready'";

					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));

					pst.close();
					rs.close();				
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		refreshBtn.setForeground(new Color(0, 0, 0));
		refreshBtn.setBackground(Color.WHITE);
		refreshBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		refreshBtn.setBounds(400, 489, 140, 37);
		frame.getContentPane().add(refreshBtn);		

		
		/******************** ORDER NUMBER LABEL AND COMBO BOX ********************/
		
		JLabel lblOrderNumber = new JLabel("Order Number:");
		lblOrderNumber.setFont(new Font("Ebrima", Font.PLAIN, 25));
		lblOrderNumber.setForeground(new Color(0, 0, 0));
		lblOrderNumber.setBounds(10, 400, 190, 30);
		frame.getContentPane().add(lblOrderNumber);
		
		JComboBox<String> orderNumBox = new JComboBox();
		orderNumBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		orderNumBox.setForeground(new Color(0, 0, 0));
		orderNumBox.setBackground(new Color(255, 255, 255));
		orderNumBox.setModel(new DefaultComboBoxModel(new String[] {"Select Order number"}));
		orderNumBox.setBounds(212, 393, 216, 50);
		try
		{
			java.sql.ResultSet rs;
			java.sql.Statement st;
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
			st = conn.createStatement();
			String s = "Select Order_Number from fast_food.order WHERE order_status = 'Ready';";
			rs = st.executeQuery(s);
			while(rs.next())
			{
				orderNumBox.addItem(rs.getString(1));
			}
		}
		catch (Exception x2)
		{
			System.out.print(x2);
			JOptionPane.showMessageDialog(null, "Error");
		}
		orderNumBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
		    	try {
		    	Class.forName("com.mysql.jdbc.Driver");
				java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
		        	String s = "Select order_number, order_status, TimeDate from fast_food.order WHERE Order_Number = ?";

		        	if(orderNumBox.getSelectedItem() != "Select Order")
		        	{		        				        	
		        	PreparedStatement ppdStmt = conn.prepareStatement(s);
		        	ppdStmt.setString(1, (String)orderNumBox.getSelectedItem());
		        	java.sql.ResultSet rs=ppdStmt.executeQuery();
		        	table_1.setModel(DbUtils.resultSetToTableModel(rs));	
		        	} 
		        	else
		        	{		
		        		String se ="Select order_number, order_status, TimeDate from fast_food.order";
		        		PreparedStatement pdStmt = conn.prepareStatement(se);
			        	java.sql.ResultSet rs=pdStmt.executeQuery();
			        	table_1.setModel(DbUtils.resultSetToTableModel(rs));	        		
		        	pdStmt.close();}
					}
		    		catch (SQLException | ClassNotFoundException eO1) 
		    		{		
		    			System.out.print(eO1);
		    		}
			}
		});
		frame.getContentPane().add(orderNumBox);
		
		/*************************** COLLECTED STATUS BUTTON *******************************/
		
		JButton collectBtn = new JButton("Collected");
		collectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fast_Food?autoReconnect=true&useSSL=false","root","password");
					String s = "UPDATE fast_food.order SET Order_Status = 'Collected' where order_number = ?";
					PreparedStatement ppdStmt = conn.prepareStatement(s);
					ppdStmt.setString(1, (String)orderNumBox.getSelectedItem());
					ppdStmt.execute();
					
					
				}
				catch(SQLException | ClassNotFoundException e1) {
					System.out.print(e1);
				}
			}
		});
		collectBtn.setForeground(new Color(0, 0, 0));
		collectBtn.setBackground(Color.WHITE); 
		collectBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		collectBtn.setBounds(572, 394, 200, 50);
		frame.getContentPane().add(collectBtn);
				
		
	}
}