package jdbcapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import jdbcapp.model.Circle;
import jdbcapp.model.Triangle;

@Component
public class JdbcAppDAOImpl {

	private String driver 	= "org.apache.derby.jdbc.ClientDriver";
	private String url 		= "jdbc:derby://localhost:1527/db";
	
	public Circle getCircle_usingJDBC(int id) {
		Connection con 		= null;
		Circle circle 		= null;
		
		try {
			Class.forName(driver).newInstance();
			con 	= DriverManager.getConnection(url);
			circle  = getCircle(con, id);
		
		} catch (Exception e) {
			System.err.println("[getCircle_usingJDBC] Some Error occurred");
			throw new RuntimeException(e);
		
		} finally {
			try {
				con.close();
				
			} catch (Exception e2) {
				System.err.println("[getCircle_usingJDBC] Some Error occurred while closing connection");
			}
		}
		return circle;
	}
	
	@Autowired
	private DataSource dataSource;
	
	public Circle getCircle_usingSpringDataSource(int id) {
		Connection con 			= null;
		Circle circle 			= null;
		
		try {
			/* 	You don't have to create DriverManager to get connection object 
			 *  Class.forName(driver).newInstance();
			 * 	con = DriverManager.getConnection(url);
			 */
			
			con 	= dataSource.getConnection();
			circle  = getCircle(con, id);
		
		} catch (Exception e) {
			System.err.println("[getCircle_usingSpringDataSource] Some Error occurred");
			throw new RuntimeException(e);
		
		} finally {
			try {
				con.close();
				
			} catch (Exception e2) {
				System.err.println("[getCircle_usingSpringDataSource] Some Error occurred while closing connection");
			}
		}
		return circle;
	}
	
	@Autowired
	private DataSource dbcpDataSource;
	
	public Circle getCircle_usingDbcpDataSource(int id) {
		Connection con 			= null;
		Circle circle 			= null;
		
		try {
			/* DBCP DataSource provides connection pooling */
			
			con 	= dbcpDataSource.getConnection();
			circle  = getCircle(con, id);
		
		} catch (Exception e) {
			System.err.println("[getCircle_usingSpringDataSource] Some Error occurred");
			throw new RuntimeException(e);
		
		} finally {
			try {
				con.close();
				
			} catch (Exception e2) {
				System.err.println("[getCircle_usingSpringDataSource] Some Error occurred while closing connection");
			}
		}
		return circle;
	}
	
	public Circle getCircle(Connection con, int id) {
		PreparedStatement pStmt = null;
		ResultSet rSet 			= null;
		Circle circle 			= null;
		
		try {
			pStmt 	= con.prepareStatement("SELECT NAME FROM CIRCLE WHERE ID=?");
			pStmt.setInt(1, id);
			
			rSet 	= pStmt.executeQuery();
			if (rSet.next()) {
				circle = new Circle(id, rSet.getString("NAME").trim());
			}
			rSet.close();
			pStmt.close();
		
		} catch (Exception e) {
			System.err.println("[getCircle] Some Error occurred");
			throw new RuntimeException(e);
		
		} finally {
			try {
				rSet.close();
				pStmt.close();
				
			} catch (Exception e2) {
				System.err.println("[getCircle] Some Error occurred while closing objects");
			}
		}
		return circle;
	}
	
	private DataSource dbcpNewDataSource;
	private JdbcTemplate jdbcTemplate;
	public DataSource getDbcpNewDataSource() {
		return dbcpNewDataSource;
	}
	@Autowired
	public void setDbcpNewDataSource(DataSource dbcpNewDataSource) {
		this.jdbcTemplate = new JdbcTemplate(dbcpNewDataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dbcpNewDataSource);
	}
	
	public int getCircleCount() {
		String sql = "SELECT COUNT(*) FROM CIRCLE";
		//jdbcTemplate.setDataSource(getDbcpDataSource());
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		
		return count;
	}

	public String getCircleName_usingJdbcTemplate(int id) {
		String sql = "SELECT NAME FROM CIRCLE WHERE ID=?";
		String name = jdbcTemplate.queryForObject(
				sql, 
				new Object[] {id} ,
				String.class);
		
		return name;
	}
	
	private static final class CircleMapper implements RowMapper<Circle>{
		@Override
		public Circle mapRow(ResultSet rSet, int rowNo) throws SQLException {
			return new Circle(rSet.getInt("ID"), rSet.getString("NAME"));
		}
	}
	
	public Circle getCircleById_usingJdbcTemplate(int id) {
		String sql = "SELECT * FROM CIRCLE WHERE ID=?";
		Circle circle = jdbcTemplate.queryForObject(
				sql, 
				new Object[] {id} ,
				new CircleMapper());
		
		return circle;
	}

	public List<Circle> getAllCircles_usingJdbcTemplate() {
		String sql = "SELECT * FROM CIRCLE";
		return jdbcTemplate.query(sql, new CircleMapper());
	}
	
	public void insertCircleRecord_usingJdbcTemplate(Circle circle) {
		String sql = "INSERT INTO CIRCLE(ID, NAME) VALUES (?, ?)";
		int count = jdbcTemplate.update(sql, new Object[] {circle.getId(), circle.getName()});
		
		System.err.println("[insertCircleRecord_usingJdbcTemplate] "+count+" row inserted");
	}
	
	public void createTraingleTable_usingJdbcTemplate() {
		String sql = "CREATE TABLE TRIANGLE(ID INTEGER, NAME CHAR(50))";
		jdbcTemplate.execute(sql);
		
		System.err.println("[createTraingleTable_usingJdbcTemplate] Table created successfully.");
	}
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public void insertTriangleRecord_usingNamedParameterJdbcTemplate(Triangle triangle) {
		String sql = "INSERT INTO TRIANGLE (ID, NAME) VALUES(:id, :name)";
		
		SqlParameterSource namedParams = new MapSqlParameterSource()
		.addValue("id", triangle.getId())
		.addValue("name", triangle.getName());
		
		int count = namedParameterJdbcTemplate.update(sql, namedParams);
		
		System.err.println("[insertTriangleRecord_usingNamedParameterJdbcTemplate] "+count+" row inserted");
	}
	
	
	/* 	It doesn't contains all methods we use previously, 
	 *	rather it contains more commonly used features of JdbcTemplate & NamedParameterJdbcTemplate
	 */
	
	private SimpleJdbcCall simpleJdbcCall; 
	
	/* DAO Support Classes have simply datasource, templates, etc variables 
	 * and their getters & setters
	 */
	
}
