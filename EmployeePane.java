package employee;

// SIMPLE JAVA EMPLOYEE MANAGEMENT SYSTEM APPLICATION CODE
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;

@SuppressWarnings("unused")
public class EmployeePane {

	private JFrame frame;
	private JTextField jtxtempname;
	private JTextField jtxtempid;
	private JTextField jtxtgender;
	private JTextField jtxtage;
	private JTextField jtxtdob;
	private JTextField jtxtsalary;
	private JTable table;
	
	// To establish a jdbc connection between Java and Mysql
	// 1. import java.sql package
	// 2. load and register jdbc driver i.e "com.mysql.cj.jdbc.Driver"
	// 3. create Connection --> using Connection interface and getConnection() function
	// 4. create Statement using Statement class
	// 5. execute query statement --> storing query in a variable 
	// 6. process i.e store result in a "ResultSet" 
	// 7. close "con" object and "statement" object 
	
	
	// declaring the Connection object to null initially
	Connection con = null;
	// declaring the PreparedStatement object to null initially
	PreparedStatement pst = null;
	// declaring the ResultSet object to null initially
	ResultSet rs = null;
	
	// Constructs a DefaultTableModel which is a table of zero columns and zero rows.
	DefaultTableModel model = new DefaultTableModel();
	
	
	
	/**
	 * Launch the application.
	 */
	
	public void updateTable()
	{
		// this connection object is created to connect to database when data is to be updated in the table
		con = EmployeeData.ConnectDB();
		
		//checking whether the connection object is null or not
		// satisfies condition if con is not null and enters into the if block
		if(con !=null)
		{
		// assigning an sql query to 'sql'variable which selects employee details from table present in database
			String sql = "Select empid,empname,gender,age,dob,salary";
		
		// using try and catch blocks to handle edge cases and exceptions if any arises
		try
		{
			//Executes the SQL query in this PreparedStatement object and returns 
			//the ResultSet object generated by the query
			pst  = con.prepareStatement(sql);
//		   Executes the given SQL statement, which may be an INSERT,UPDATE, or DELETE statement or an
//		   SQL statement that returns nothing, such as an SQL DDL statement.
			rs = pst.executeQuery();
			
			// Declaring a object array to store values of table data entered by user
			// We are using object array to store values irrespective of type of the data
			Object[] columnData = new Object[6];
			
			// moving the result set pointer to next
			while(rs.next())
			{
				// assigning each column data to its respective table attribute 
				// table attributes include empid,empname,gender,age,dob,salary
				columnData[0] = rs.getString("empid");
				columnData[1] = rs.getString("empname");
				columnData[2] = rs.getString("gender");
				columnData[3] = rs.getString("age");
				columnData[4] = rs.getString("dob");
				columnData[5] = rs.getString("salary");
				
				// adding the column data to row of the table which is visible to user
				// model is an empty table, now adding attributes to table using model.addRow() 
				model.addRow(columnData);
			}
		}
		// catch block to handle the exception
		catch(Exception e)
		{
			// displaying the exception arised to user 
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeePane window = new EmployeePane();
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
	//  constructor of EmployeePane
	public EmployeePane() {
		initialize();
		
		// instantiating the connection object which is declared in EmployeeData.java
		// this connection object is created to connect to database initially
		con = EmployeeData.ConnectDB();
		// creating idetifiers for table columns, displayed in the user pane 
		Object col[] = {"empid", "empname", "gender", "age", "dob", "salary"};
		// adding the idetifiers to the table
		model.setColumnIdentifiers(col);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0,0, 755, 439);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("empname");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 159, 62, 29);
		frame.getContentPane().add(lblNewLabel);
		
		jtxtempname = new JTextField();
		jtxtempname.setBounds(82, 159, 86, 20);
		frame.getContentPane().add(jtxtempname);
		jtxtempname.setColumns(10);
		
		JLabel lblGender = new JLabel("gender");
		lblGender.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblGender.setBounds(10, 185, 62, 29);
		frame.getContentPane().add(lblGender);
		
		JLabel lblAge = new JLabel("age");
		lblAge.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblAge.setBounds(10, 214, 62, 29);
		frame.getContentPane().add(lblAge);
		
		JLabel lblDob = new JLabel("dob");
		lblDob.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblDob.setBounds(10, 243, 62, 29);
		frame.getContentPane().add(lblDob);
		
		JLabel lblSalary = new JLabel("salary");
		lblSalary.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblSalary.setBounds(10, 274, 62, 29);
		frame.getContentPane().add(lblSalary);
		
		JLabel lblEmpid = new JLabel("empid");
		lblEmpid.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblEmpid.setBounds(10, 119, 62, 29);
		frame.getContentPane().add(lblEmpid);
		
		jtxtempid = new JTextField();
		jtxtempid.setColumns(10);
		jtxtempid.setBounds(82, 125, 86, 20);
		frame.getContentPane().add(jtxtempid);
		
		jtxtgender = new JTextField();
		jtxtgender.setColumns(10);
		jtxtgender.setBounds(82, 191, 86, 20);
		frame.getContentPane().add(jtxtgender);
		
		jtxtage = new JTextField();
		jtxtage.setColumns(10);
		jtxtage.setBounds(82, 220, 86, 20);
		frame.getContentPane().add(jtxtage);
		
		jtxtdob = new JTextField();
		jtxtdob.setColumns(10);
		jtxtdob.setBounds(82, 249, 86, 20);
		frame.getContentPane().add(jtxtdob);
		
		jtxtsalary = new JTextField();
		jtxtsalary.setColumns(10);
		jtxtsalary.setBounds(82, 280, 86, 20);
		frame.getContentPane().add(jtxtsalary);
		
		// ADD NEW Button function 
		JButton btnNewButton = new JButton("Add New");
		btnNewButton.addActionListener(new ActionListener() {
			// Function which is invoked when user clicks the ADD NEW Button
			public void actionPerformed(ActionEvent e) {
				
				// sql query to insert data into table (storing the query in the sql variable)  
				String sql = "INSERT INTO empsystem(empid,empname,gender,age,dob,salary) VALUES(?,?,?,?,?,?)";
				
				// here we are using try and catch blocks to handle edge cases and any exceptions if raised
				try
				{
				// reading and passing the user entered data from the textFields using the Prepared Statement object
					pst = con.prepareStatement(sql);
					pst.setString(1, jtxtempid.getText());
					pst.setString(2, jtxtempname.getText());
					pst.setString(3, jtxtgender.getText());
					pst.setString(4, jtxtage.getText());
					pst.setString(5, jtxtdob.getText());
					pst.setString(6, jtxtsalary.getText());
					
					pst.execute();
					
					//***MUST***  closing the PreparedStatement and ResultSet objects after execution 
					rs.close();
					pst.close();
				}
				catch(Exception ean)
				{
					// displaying the message when a succesfull update is performed on the table in database
					JOptionPane.showMessageDialog(null," System Update Completed ");
				}
				
				// creating a new table model and adding data entered by user to the database
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {
//				Returns the selected text contained in the text fileds --> jtxtempid,jtextempname etc..
				jtxtempid.getText(),
				jtxtempname.getText(),
				jtxtgender.getText(),
				jtxtage.getText(),
				jtxtdob.getText(),
				jtxtsalary.getText(),
				});
				
				// Returns the index of the first selected row, -1 if no row is selected.
				if(table.getSelectedRow() == -1)
				{
				// Returns the number of selected rows, 0 if no rows are selected
					if(table.getSelectedRowCount()==0)
					{
//				Brings up a dialog that displays a message using a default icon determined by the user
						JOptionPane.showMessageDialog(null, " MemberShip Update Confirmed",
								" Employee Database System",JOptionPane.OK_OPTION);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		btnNewButton.setBounds(21, 334, 103, 29);
		frame.getContentPane().add(btnNewButton);
		
		// PRINT Button
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			// Function which is invoked when user clicks the PRINT Button
			public void actionPerformed(ActionEvent e) {
				
				MessageFormat header = new MessageFormat("Printing in Progress");
				MessageFormat footer = new MessageFormat("Page {0, number, integer}");
				
				try
				{
	//A convenience method that displays a printing dialog,and then prints the Table in the PrintMode.
					table.print();
				}
				// To handle exception if Printer is not detected by the java application
				catch(java.awt.print.PrinterException ep)
				{
					// Displays the Printer not found Error message to the user
					System.err.format("No Printer Found", ep.getMessage());
				}
			}
		});
		btnPrint.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		btnPrint.setBounds(167, 334, 113, 29);
		frame.getContentPane().add(btnPrint);
		
		// RESET Button  
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			// Function which is invoked when user clicks the RESET Button
			public void actionPerformed(ActionEvent e) {
			// Sets the text of the respective text field to the specified text.  
//				If the text is null or empty, has the effect of simply deleting the old text.
				jtxtempname.setText(null);
				jtxtempid.setText(null);
				jtxtgender.setText(null);
				jtxtage.setText(null);
				jtxtdob.setText(null);
				jtxtsalary.setText(null);
			}
		});
		btnReset.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		btnReset.setBounds(316, 334, 113, 29);
		frame.getContentPane().add(btnReset);
		
		// EXIT Button
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			// Function which is invoked when user clicks the EXIT Button
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Exit");
//	Brings up a dialog where the number of choices is determined by the user parameter.
				// Here we are passing "YES" or "NO" as parameters to confirm it from user
				if(JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit ",
						" Employee Database System",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION);
				// If the confirmed parameter is YES then the application is closed
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		btnExit.setBounds(470, 334, 113, 29);
		frame.getContentPane().add(btnExit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(195, 51, 520, 249);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"empid", "empname", "gender", "age", "dob", "salary"
			}
		));
		table.setFont(new Font("Serif", Font.BOLD, 12));
		scrollPane.setViewportView(table);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 314, 719, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("EMPLOYEE DATABASE MANAGEMENT SYSTEM");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		lblNewLabel_1.setBounds(180, 11, 393, 29);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
