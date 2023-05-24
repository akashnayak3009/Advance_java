package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.SQLException;

public class PrepareStatement {
	private static final String STUDENT_INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc =null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			sc = new Scanner(System.in);
			int count=0;
			if(sc!=null) {
				System.out.println("Enter students count::");
				count=sc.nextInt();
			}
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","akash","makeitlarge");
			
			if(con!=null)
				ps=con.prepareStatement(STUDENT_INSERT_QUERY);
			
			if(ps!=null&&sc!=null) {
				for(int i=1;i<count;++i) {
					System.out.println("enter"+i+"students details");
					System.out.println("Enter Student number::");
					int no=sc.nextInt();
					System.out.println("Enter student name::");
					String name=sc.next();
					System.out.println("Enter student address");
					String addrs =sc.next();
					System.out.println("Enter student avg::");
					float avg =sc.nextFloat();
					ps.setInt(1, no);
					ps.setString(2, name);
					ps.setString(3, addrs);
					ps.setFloat(4,avg);
					
					int result =ps.executeUpdate();
					
					if(result==0)
						System.out.println(i+" Student details not inserted");
					else
						System.out.println(i+" Student details inserted");
				}
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
