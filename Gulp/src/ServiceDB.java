import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletException;

public class ServiceDB {
	private  Connection conn;
	
	public ServiceDB() {
		try {
			conn = getConnected();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  Connection getConn() {
		return conn;
	}

	public  void setConn(Connection conn) {
		this.conn = conn;
	}

	public  Connection getConnected() throws SQLException {
		// URL of Oracle database server
		Connection conn;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:oracle:thin:testuserdb/password@localhost";
		// properties for creating connection to Oracle database
		Properties props = new Properties();
		props.setProperty("user", "testuserdb");
		props.setProperty("password", "password");

		// creating connection to Oracle database using JDBC
		conn = DriverManager.getConnection(url, props);
		return conn;
	}
	public ArrayList<Resturant> getResturantsByRating() {
		ArrayList<Resturant> resturantList = null;
		return resturantList;
	}
	public Reviewer getReviewerProfile(String reviewerName) {
		Reviewer aReviewer = null;
		return aReviewer;
	}
	public ArrayList<Rating> getReviewsByReviewer(int reviewerId) {
		ArrayList<Rating> ratingList = null;
		return ratingList;
	}
	public ArrayList<Rating> getResturantReviews(int resturantId) {
		ArrayList<Rating> ratingList = null;
		return ratingList;
	}
	public boolean validReviewer(String loginName) {
		ResultSet rs = null;
		boolean flag = false;
		int count = 0;
	   	String sql = "select count(NAME) as num from reviewers where NAME = '" + loginName + "'";
		try {
/*			PreparedStatement st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			count = rs.next();
*/			Statement st = conn.createStatement();
			rs = st.executeQuery(sql) ;
			while (rs.next()) {
				count = rs.getInt("num");
			}
			if (count > 0) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public String getReviewerName(int reviewerId) {
		String name = "";
		ResultSet rs = null;
	   	String sql = "select name from reviewers where id = " + ((Integer) reviewerId).toString() ;
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql) ;
			while (rs.next()) {
				name = rs.getString(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	public String getResturantName(int resturantId) {
		String name = "";
		ResultSet rs = null;
	   	String sql = "select name from resturants where id = " + ((Integer) resturantId).toString() ;
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql) ;
			while (rs.next()) {
				name = rs.getString(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}	
	public int getReviewerId(String reviewerName) {
		int id = 0;
		ResultSet rs = null;
	   	String sql = "select id from reviewers where name = " + reviewerName;
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql) ;
			while (rs.next()) {
				id = rs.getInt(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	public int getResturantId(String resturantName) {
		int id = 0;
		ResultSet rs = null;
	   	String sql = "select id from resturants where name = " + resturantName;
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql) ;
			while (rs.next()) {
				id = rs.getInt(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}	
	public void addReview(Rating aRating) {
		PreparedStatement stmt = null;
		String sql = "insert into ratings (reviewer_id, resturant_id, rating, ratedate) values (?,?,?,?)";
		 Date currentDate = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-YYYY");
		try {
			stmt = this.conn.prepareStatement(sql);
			stmt.setInt(1, aRating.getReviewerId());
			stmt.setInt(2, aRating.getResturantId());
			stmt.setInt(3, aRating.getRate());
			String formattedDate = sdf.format(currentDate);
			java.util.Date utilDate = null;
			try {
				utilDate = sdf.parse(formattedDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//			currentDate = "TO_DATE('" + aStudent.getRegdate() + "', 'MM-DD-YYYY')"; // TO_DATE('10/30/2015', 'MM-DD-YYYY' 
			stmt.setDate(4,  sqlDate);  
			stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	public void addReviewer(Reviewer aReviewer) {
		PreparedStatement stmt = null;
		String sql = "insert into reviewers (email, name, zipcode, regdate) values (?,?,?,?)";
		 Date currentDate = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-YYYY");
		try {
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, aReviewer.getEmail());
			stmt.setString(2, aReviewer.getName());
			stmt.setString(3, aReviewer.getZipcode());
			String formattedDate = sdf.format(currentDate);
			java.util.Date utilDate = null;
			try {
				utilDate = sdf.parse(formattedDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//			currentDate = "TO_DATE('" + aStudent.getRegdate() + "', 'MM-DD-YYYY')"; // TO_DATE('10/30/2015', 'MM-DD-YYYY' 
			stmt.setDate(4,  sqlDate);  
			stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addResturant(Resturant aResturant) {
		PreparedStatement stmt = null;
		String sql = "insert into resturants (name, addr) values (?,?)";
		try {
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, aResturant.getName());
			stmt.setString(2, aResturant.getAddr());
			stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void disconnectDB() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

