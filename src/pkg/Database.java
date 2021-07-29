package pkg;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Database {
	
	public static void main(String args[])
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/testdb?useSSL=false", "root", "(password)");
			// MySQL에 연결 시도
			
			String query = "SELECT Sname FROM STUDENT WHERE Year = ? AND GPA >= ?";
				
			ps = conn.prepareStatement(query);
			ps.setInt(1, 2);
			ps.setInt(2, 3);
				
			rs = ps.executeQuery();  // SELECT Sname FROM STUDENT WHERE Year = 2 AND GPA >= 3

			System.out.println("<테이블 출력 결과>");
			
			while(rs.next()) {
				System.out.println(rs.getString(1));  // 쿼리문 실행 후 결과값 출력
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}