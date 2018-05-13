package jdbcapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jdbcapp.model.Circle;

@Component
public class JdbcAppDAOImpl {

	private String driver 	= "org.apache.derby.jdbc.ClientDriver";
	private String url 		= "jdbc:derby://localhost:1527/db";
	
	@Autowired
	private DataSource dataSource;
	
	public Circle getCircle_usingSpringDataSource(int id) {
		Connection con 			= null;
		PreparedStatement pStmt = null;
		ResultSet rSet 			= null;
		Circle circle 			= null;
		
		try {
			/* 	You don't have to create DriverManager to get connection object 
			 * 	
			 *  Class.forName(driver).newInstance();
			 * 	con = DriverManager.getConnection(url);
			 */
			
			con 	= dataSource.getConnection();
			pStmt 	= con.prepareStatement("SELECT NAME FROM CIRCLE WHERE ID=?");
			pStmt.setInt(1, id);
			
			rSet 	= pStmt.executeQuery();
			if (rSet.next()) {
				circle = new Circle(id, rSet.getString("NAME").trim());
			}
			rSet.close();
			pStmt.close();
		
		} catch (Exception e) {
			System.err.println("[getCircle_usingSpringDataSource] Some Error occurred");
			throw new RuntimeException(e);
		
		} finally {
			try {
				rSet.close();
				pStmt.close();
				con.close();
				
			} catch (Exception e2) {
				System.err.println("[getCircle_usingSpringDataSource] Some Error occurred while closing objects");
			}
		}
		return circle;
	}
	
	public Circle getCircle_usingJDBC(int id) {
		Connection con 			= null;
		PreparedStatement pStmt = null;
		ResultSet rSet 			= null;
		Circle circle 			= null;
		
		try {
			Class.forName(driver).newInstance();
			con 	= DriverManager.getConnection(url);
			pStmt 	= con.prepareStatement("SELECT NAME FROM CIRCLE WHERE ID=?");
			pStmt.setInt(1, id);
			
			rSet 	= pStmt.executeQuery();
			if (rSet.next()) {
				circle = new Circle(id, rSet.getString("NAME").trim());
			}
			rSet.close();
			pStmt.close();
		
		} catch (Exception e) {
			System.err.println("[getCircle_usingJDBC] Some Error occurred");
			throw new RuntimeException(e);
		
		} finally {
			try {
				rSet.close();
				pStmt.close();
				con.close();
				
			} catch (Exception e2) {
				System.err.println("[getCircle_usingJDBC] Some Error occurred while closing objects");
			}
		}
		return circle;
	}
}
