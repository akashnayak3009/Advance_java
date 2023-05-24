package com.nt.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Scanner;

public class LoginPreparedStatement {
	
	private static final String Login_Query="SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME=? AND PWD=?";

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps =null;
		
		try {
			sc = new Scanner(System.in);
			String user =null,pass=null;
			
			if(sc!=null) {
				System.out.println("Enter Login Username");
				user=sc.nextLine();
				System.out.println("Enter Login Password");
				pass=sc.nextLine();
			}
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","akash","makeitlarge");
			
			if(con!=null)
				ps=con.prepareStatement(Login_Query);
			if(ps!=null)
				ps.setString(1, user);
				ps.setString(2, pass);
			
			if(ps!=null)
				rs=ps.executeQuery();
			
			if(rs!=null) {
				rs.next();
				int count=rs.getInt(1);
				
				if(count==0)
					System.out.println("Invalid Credentials");
				else
					System.out.println("Valid Credentials");
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
				if(ps!=null)
					ps.close();
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
