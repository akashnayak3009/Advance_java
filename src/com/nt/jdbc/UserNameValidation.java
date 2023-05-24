package com.nt.jdbc;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


@SuppressWarnings("unused")
public class UserNameValidation {

	public static void main(String[] args) {
		Scanner sc=null;
		ResultSet rs=null;
		Connection con=null;
		Statement st = null;
		
		try {
			sc=new Scanner(System.in);
			String user = null, pass=null;
			if(sc!=null) {
				System.out.println("Enter Username:");
				user=sc.nextLine();
				System.out.println("Enter Password");
				pass=sc.nextLine();
			}
			user="'"+user+"'";
			pass="'"+pass+"'";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","akash","makeitlarge");
			if(con!=null) 
				st=con.createStatement();
			String query="SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME="+user+" AND PWD="+pass;
			System.out.println(query);
			
			if(st!=null) {
				rs=st.executeQuery(query);
				if(rs!=null)
					rs.next();
				int count =rs.getInt(1);
				if(count==0)
					System.out.println("INVALID CREDENTIALS");
				else
					System.out.println("VALID CREDENTIALS");
			}		
		}
		
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
