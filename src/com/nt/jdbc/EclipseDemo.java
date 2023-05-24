package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class EclipseDemo {

	public static void main(String[] args) {
		
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","akash","makeitlarge");
			if(con!=null)
				st=con.createStatement();
			
			String query=" SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP)";
			System.out.println(query);
			
			if(st!=null)
				rs=st.executeQuery(query);
			
			if(rs!=null) {
				if(rs.next())
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
				else
					System.out.println("No Record found");
			}//if
		} //try
		catch(SQLException se){
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
			
		}
		
		}//main
	}//class


