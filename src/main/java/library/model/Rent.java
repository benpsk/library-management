package library.model;

import java.sql.Blob;
import java.sql.Date;

public class Rent {
	
	public Long getBookID() {
		return bookID;
	}


	public void setBookID(Long bookID) {
		this.bookID = bookID;
	}


	public Blob getBookPhoto() {
		return bookPhoto;
	}


	public void setBookPhoto(Blob bookPhoto) {
		this.bookPhoto = bookPhoto;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public String getBookAuthor() {
		return bookAuthor;
	}


	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}


	public Long getStudentID() {
		return studentID;
	}


	public void setStudentID(Long studentID) {
		this.studentID = studentID;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


//	public String getRentDate() {
//		return rentDate;
//	}
//
//
//	public void setRentDate(String rentDate) {
//		this.rentDate = rentDate;
//	}
//
//
//	public String getReturnDate() {
//		return returnDate;
//	}
//
//
//	public void setReturnDate(String returnDate) {
//		this.returnDate = returnDate;
//	}

	private Long id;
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	private String stringPhoto;
	public String getStringPhoto() {
		return stringPhoto;
	}


	public void setStringPhoto(String stringPhoto) {
		this.stringPhoto = stringPhoto;
	}


	private Long bookID;
	private Blob bookPhoto;
	private String bookName;
	private String bookAuthor;
	private Long studentID;
	private String userName;
	private String isbn;
	
public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	//	private String rentDate;
//	private String returnDate;
	private Date rentDate;
	public Date getRentDate() {
		return rentDate;
	}


	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}


	public Date getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}


	private Date returnDate;
	
	// Called by Rent
	public Rent(Long id, Long bookID, Blob bookPhoto, String bookName, String bookAuthor, Long studentID, String userName) {
		this.id = id;
		this.bookID = bookID;
		this.bookPhoto = bookPhoto;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.studentID = studentID;
		this.userName = userName;
	}

	
	public Rent(Long id, Long bookID, String bookPhoto, String bookName, String bookAuthor, Long studentID, String userName, Date rentDate) {
		this.id = id;
		this.bookID = bookID;
		this.stringPhoto = bookPhoto;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.studentID = studentID;
		this.userName = userName;
		this.rentDate = rentDate;
	}
	public Rent(Long id, Long bookID, String bookPhoto, String bookName, String bookAuthor, Long studentID, String userName, Date rentDate, Date returnDate) {
		this.id = id;
		this.bookID = bookID;
		this.stringPhoto = bookPhoto;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.studentID = studentID;
		this.userName = userName;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
	}
	
	public Rent(Long id, Long bookID, String bookPhoto, String bookName, String bookAuthor, Date rentDate) {
		this.id = id;
		this.bookID = bookID;
		this.stringPhoto = bookPhoto;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.rentDate = rentDate;
	}
	
	
	
	
	
//	public Rent(Long id, Long bookID, String bookPhoto, String bookName, String bookAuthor, Long studentID, String userName, String rentDate, String returnDate) {
//		this.id = id;
//		this.bookID = bookID;
//		this.stringPhoto = bookPhoto;
//		this.bookName = bookName;
//		this.bookAuthor = bookAuthor;
//		this.studentID = studentID;
//		this.userName = userName;
//		this.rentDate = rentDate;
//		this.returnDate = returnDate;
//	}

}
