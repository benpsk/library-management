package library.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import library.model.Rent;

public class RentDao {

	public String db = "jdbc:mysql://localhost/demo";
	public String dbuser = "root";
	public String dbpass = "root";
	
	// Update return DAte
	public boolean updateReturnDate(Long id, Long uid) throws SQLException {
		String sql = "UPDATE rent SET returnDate=now() WHERE id=? AND userID=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db, dbuser, dbpass);
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setLong(1, id);
			pstmt.setLong(2, uid);
			
			int status = pstmt.executeUpdate();
			if (status > 0) {
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	// USer Rent List 
	public List<Rent> getURentList(Long iid) throws SQLException, IOException {
		List<Rent> URentList = new ArrayList<Rent>();
		String sql = "SELECT * FROM rent WHERE userID=? AND returnDate IS NULL";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db, dbuser, dbpass);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, iid);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Long id = rs.getLong("id");
				Long bookID = rs.getLong("bookID");
				String bookName = rs.getString("bookName");
				String bookAuthor = rs.getString("bookAuthor");
				Date rentDate = rs.getDate("rentDate");
				
				Blob blob = rs.getBlob("bookPhoto");
				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream ops = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					ops.write(buffer, 0, bytesRead);
				}

				byte[] imgBytes = ops.toByteArray();
				String photo = Base64.getEncoder().encodeToString(imgBytes);
				
				Rent rent = new Rent(id, bookID, photo, bookName, bookAuthor, rentDate);
				URentList.add(rent);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return URentList;
	}
	

	// Insert Rent Book for Admin
	public boolean insertRent(Rent rent) throws SQLException {
		String sql = "INSERT INTO rent(bookID, bookPhoto, bookName, bookAuthor, userID, userName, rentDate, returnDate) VALUES (?,?,?,?,?,?, now(), null)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db, dbuser, dbpass);
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setLong(1, rent.getBookID());
			pstmt.setBlob(2, rent.getBookPhoto());
			pstmt.setString(3, rent.getBookName());
			pstmt.setString(4, rent.getBookAuthor());
			pstmt.setLong(5, rent.getStudentID());
			pstmt.setString(6, rent.getUserName());

			int status = pstmt.executeUpdate();
			if (status > 0) {
				return true;
			} else {
				return false;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
	
	// SHOW Admin Rent List
	public List<Rent> getRentList() throws SQLException, IOException {
		List<Rent> rentList = new ArrayList<Rent>();
		String sql = "SELECT * FROM rent";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db, dbpass, dbuser);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Long id = rs.getLong("id");
				Long bookID = rs.getLong("bookID");
				String bookName = rs.getString("bookName");
				String bookAuthor = rs.getString("bookAuthor");
				Long userID = rs.getLong("userID");
				String userName = rs.getString("userName");
				Date rentDate = rs.getDate("rentDate");
				Date returnDate = rs.getDate("returnDate");				
				
				Blob blob = rs.getBlob("bookPhoto");
				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream ops = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					ops.write(buffer, 0, bytesRead);
				}
				byte[] imgBytes = ops.toByteArray();
				String photo = Base64.getEncoder().encodeToString(imgBytes);
				
				Rent rent = new Rent(id, bookID, photo, bookName, bookAuthor, userID, userName, rentDate, returnDate);
				
				rentList.add(rent);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rentList;
	}

}
