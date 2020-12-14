package library.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import library.model.Book;
import library.model.Category;
import library.model.User;

public class BookDao {

	public String db = "jdbc:mysql://localhost/demo";
	public String dbuser = "root";
	public String dbpass = "root";
	
	
	// Create User Table for each 
	public boolean createTable(User user) throws Exception {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db, dbuser, dbpass);
			
			String str = user.getName().replaceAll("\\s+", "");
			String sql = "CREATE TABLE " +str+ "(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, bookPhoto MEDIUMBLOB, bookName VARCHAR(40), bookAuthor VARCHAR(40), rentDate DATETIME)";
			PreparedStatement pstmt = con.prepareStatement(sql);
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
	
	//Selecy By ID for Admin Rent
		public Book rentByID(Long iid) throws Exception {
			String sql = "SELECT * FROM library WHERE id=?";
			Book book = new Book();
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(db, dbuser, dbpass);
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, iid);
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					Long id = rs.getLong("id");
					String name = rs.getString("name");
					String isbn = rs.getString("isbn");
					Integer qty = rs.getInt("qty");
					Double price = rs.getDouble("price");
					String author = rs.getString("author");

					Blob photo = rs.getBlob("photo");
					
					book = new Book(id, name, isbn, qty, price, photo, author);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return book;
		}
	
	
	// Delete Library DAta
	public boolean deleteLibrary(Long id) throws Exception {
		String sql = "DELETE FROM library WHERE id=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db, dbuser, dbpass);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);

			int status = pstmt.executeUpdate();
			if (status > 0)
				return true;
			else
				return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	// Update Library
	public boolean updateStudent(Book book) throws Exception {
		String sql = "UPDATE library SET name=?, qty=?, price=?, photo=?, author=?, category_id=?, isbn=? WHERE id=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db, dbuser, dbpass);
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, book.getBname());
			pstmt.setInt(2, book.getQty());
			pstmt.setDouble(3, book.getPrice());
			pstmt.setBlob(4, book.getPt());
			pstmt.setString(5, book.getAuthor());
			pstmt.setInt(6, book.getCategory());
			pstmt.setString(7, book.getIsbn());
			pstmt.setLong(8, book.getId());

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

	// Search Category by category_ID
	public List<Book> categoryID(int iid) throws Exception {
		String sql = "SELECT * FROM library WHERE category_id=?";
		List<Book> bookList = new ArrayList<Book>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db, "root", "root");
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, iid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
//				Book book = new Book(rs.getLong("id"), rs.getString("name"), rs.getString("isbn"),
//						rs.getInt("qty"), rs.getInt("price"), rs.getString("photo"), rs.getString("category"), rs.getString("author"));
//				bookList.add(book);

				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String isbn = rs.getString("isbn");
				Integer qty = rs.getInt("qty");
				Double price = rs.getDouble("price");
				Integer category_id = rs.getInt("category_id");
				String author = rs.getString("author");
				Blob blob = rs.getBlob("photo");

				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream ops = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					ops.write(buffer, 0, bytesRead);
				}

				byte[] imgBytes = ops.toByteArray();
				String photo = Base64.getEncoder().encodeToString(imgBytes);

				Book book = new Book(id, name, qty, price, photo, author, category_id, isbn);
				bookList.add(book);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}

	// Select By ID
	public Book selectByID(Long iid) throws Exception {
		String sql = "SELECT b.*,bc.id AS categoryId, bc.name AS categoryName FROM library b LEFT JOIN category bc ON b.category_id=bc.id WHERE b.id=?";
		Book book = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db, "root", "root");
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, iid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
//				Book book = new Book(rs.getLong("id"), rs.getString("name"), rs.getString("isbn"),
//						rs.getInt("qty"), rs.getInt("price"), rs.getString("photo"), rs.getString("category"), rs.getString("author"));
//				bookList.add(book);

				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String isbn = rs.getString("isbn");
				Integer qty = rs.getInt("qty");
				Double price = rs.getDouble("price");
				Integer category_id = rs.getInt("category_id");
				String author = rs.getString("author");
				Integer cid = rs.getInt("categoryId");
				String cname = rs.getString("categoryName");

				Blob blob = rs.getBlob("photo");

				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream ops = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					ops.write(buffer, 0, bytesRead);
				}

				byte[] imgBytes = ops.toByteArray();
				String photo = Base64.getEncoder().encodeToString(imgBytes);

				book = new Book(id, name, qty, price, photo, author, category_id, isbn, cid, cname);

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}

	// Select all book
	public List<Book> getBook() throws SQLException, IOException {
		List<Book> bookList = new ArrayList<Book>();
		String sql = "SELECT b.*,bc.id AS categoryId, bc.name AS categoryName FROM library b LEFT JOIN category bc ON b.category_id=bc.id";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db, dbuser, dbpass);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
//				Book book = new Book(rs.getLong("id"), rs.getString("name"), rs.getString("isbn"),
//						rs.getInt("qty"), rs.getInt("price"), rs.getString("photo"), rs.getString("category"), rs.getString("author"));
//				bookList.add(book);

				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String isbn = rs.getString("isbn");
				Integer qty = rs.getInt("qty");
				Double price = rs.getDouble("price");
				Integer category_id = rs.getInt("category_id");
				String author = rs.getString("author");
				Integer cid = rs.getInt("categoryId");
				String cname = rs.getString("categoryName");

				Blob blob = rs.getBlob("photo");

				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream ops = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					ops.write(buffer, 0, bytesRead);
				}

				byte[] imgBytes = ops.toByteArray();
				String photo = Base64.getEncoder().encodeToString(imgBytes);

				Book book = new Book(id, name, qty, price, photo, author, category_id, isbn, cid, cname);
				bookList.add(book);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bookList;
	}

	// Insert Library
	public boolean insertBook(Book book) throws SQLException {

		String sql = "INSERT INTO library(name, qty, price, photo, author, category_id, isbn) VALUES(?, ?, ?, ?, ?, ?, ?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db, dbuser, dbpass);
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, book.getBname());
			pstmt.setInt(2, book.getQty());
			pstmt.setDouble(3, book.getPrice());
			pstmt.setBlob(4, book.getPt());
			pstmt.setString(5, book.getAuthor());
			pstmt.setInt(6, book.getCategory());
			pstmt.setString(7, book.getIsbn());

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

	// Select from Category
	public List<Category> getBookCategory() throws SQLException {
		String sql = "SELECT * FROM category";
		List<Category> catList = new ArrayList<Category>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db, dbuser, dbpass);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Category cat = new Category(rs.getInt("id"), rs.getString("name"));
				catList.add(cat);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return catList;
	}

	public boolean insertUser(User user) throws Exception {

		String sql = "INSERT INTO register(name, pass, role) VALUES(?, ?, ?)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db, dbuser, dbpass);
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPass());
			pstmt.setString(3, user.getRole());

			int status = pstmt.executeUpdate();
			if (status > 0)
				return true;
			else
				return false;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	// SELECT BY NAME
	public User selectByName(String name) throws Exception {
		String sql = "SELECT * FROM register WHERE name=?";
		User user = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(db, dbuser, dbpass);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new User(rs.getLong("id"), rs.getString("name"), rs.getString("pass"), rs.getString("role"));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
//// TEST IF name already exist
//	public int testName(String name) throws Exception {
//		String sql = "SELECT EXISTS(SELECT * FROM register WHERE name=?)";
//		int result = 0;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con = DriverManager.getConnection(db, dbuser, dbpass);
//			PreparedStatement pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, name);
//			
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				result = 
//			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//	}

}