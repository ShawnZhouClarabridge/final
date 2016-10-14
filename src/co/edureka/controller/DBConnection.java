package co.edureka.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static String dbURL = "jdbc:postgresql://localhost:5432/edureka";
	private static String dbUser = "postgres";
	private static String dbPassword = "123";

	public static Connection getConnection(){
		Connection  con=null;
		try{
			try{
				Class.forName("org.postgresql.Driver");
				}
			catch(ClassNotFoundException ex){
				System.out.println("Can not load Driver class");
				System.exit(1);
			   }
			con=DriverManager.getConnection(dbURL,dbUser,dbPassword);
		 }
		catch(SQLException e){ System.out.println("Error While creating connection to database");}
		return con;
	}
	

}
