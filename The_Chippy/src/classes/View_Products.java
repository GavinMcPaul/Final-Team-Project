package classes;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;

public class View_Products extends JFrame {

	private JFrame frame;
	private JTable view_table;
	
	//VARIABLES FOR DATABSE CONNECTION
	private String url = "jdbc:mysql://localhost:3306/";
	private String dbName = "fast_food?autoReconnect=true&useSSL=false";
	private String driver = "com.mysql.jdbc.Driver";
	private String userName = "root";
	private String passwordDB = "password";
	private Statement statement = null;   
    private ResultSet resultSet = null;
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Products window = new View_Products();
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
	public View_Products() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 224, 147));
		//frame.setBackground(new Color(115, 168, 212));
		frame.setBounds(100, 100, 1412, 690);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBackground(new Color(255, 216, 120));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 23, 1354, 547);
		frame.getContentPane().add(scrollPane);
		
		view_table = new JTable();
		view_table.setRowHeight(30);
		view_table.setFont(new Font("Open Sans", Font.PLAIN, 25));
		scrollPane.setViewportView(view_table);
		
		
		
		
		JButton btnViewProducts = new JButton("View Products");
		btnViewProducts.setFont(new Font("Open Sans", Font.BOLD, 30));
		btnViewProducts.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
					Class.forName("com.mysql.jdbc.Driver");
			        Connection conn = DriverManager.getConnection(url+dbName,userName,passwordDB);
			        statement=conn.createStatement();
			        
			        String sql="SELECT * FROM product";
			        PreparedStatement stmt = conn.prepareStatement(sql);
			        ResultSet rs = stmt.executeQuery(sql);
			        
			        view_table.setModel(DbUtils.resultSetToTableModel(rs));
			        
				}
				catch(Exception e1)
				{
					
				}
			}
		});
		btnViewProducts.setBounds(554, 583, 295, 36);
		frame.getContentPane().add(btnViewProducts);
	}

	
}
