package employee;
// To establish a jdbc connection between Java and Mysql
// 1. import java.sql package
// 2. load and register jdbc driver i.e "com.mysql.cj.jdbc.Driver"
// 3. create Connection --> using Connection interface and getConnection() function
// 4. create Statement using Statement class
// 5. excute query statement --> storing query in a variable
// 6. process i.e store result in a "ResultSet"
// 7. close "con" object and "stmt" object 

// importing required packages for connecting with database
import java.sql.*;
import javax.swing.JOptionPane;

public class EmployeeData {

	// Creating a connection fucnction to return the connection object 
	// to the main application for database access and modification
	public static Connection ConnectDB()
	{
		// this is applicable if your using MySql 
		// mysql path to create connection to the required database
		// jdbc:mysql://localhost:3306/your database name
		String url = "jdbc:mysql://localhost:3306/employee";
		String uname = "root"; // username variable to pass value to the connection function
//		VirenWik@2515
		String pswd = "VirenWik@2515"; // password variable to pass value to the connection function
		
// here we are using try and catch blocks to handle the exceptions raised when there are any connection isssues
		try
		{
			// loading the jdbc driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// registering with the Driver Manager and creating the connection object
			Connection con = DriverManager.getConnection(url,uname,pswd);
			// this message will be displayed when the connection made is successfull
			JOptionPane.showMessageDialog(null, " Connection Made ");
			// returning the connection object con
			return con;
		}
		catch(Exception e)
		{
			//this message will be displayed when the connection made is un-successfull 
			JOptionPane.showMessageDialog(null, " Connection Error ");
			return null;
		}
	}
}
