package library.model;

import java.io.InputStream;
import java.sql.Blob;

public class Book {
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getCategory() {
		return category_id;
	}
	public void setCategory(Integer category) {
		this.category_id = category;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	private Long id;
	private String bname;
	private String isbn;
	private Integer qty;
	private Double price;
	private String author;
	private Integer category_id;
	private String photo;
	private InputStream pt;
	public Blob getBlog() {
		return blog;
	}
	public void setBlog(Blob blog) {
		this.blog = blog;
	}
	
	private Blob blog;
	
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public Integer getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	private Integer categoryID;
	private String cname;
	
	public InputStream getPt() {
		return pt;
	}
	public void setPt(InputStream pt) {
		this.pt = pt;
	}
	public Book(Long id, String bname, Integer qty, Double price, InputStream photo, String author, Integer category_id, String isbn) {
		this.id = id;
		this.bname = bname;
		this.isbn = isbn;
		this.qty = qty;
		this.price = price;
		this.author = author;
		this.category_id = category_id;
		this.pt = photo;
	}
	
	public Book(Long id, String bname, Integer qty, Double price, String photo, String author, Integer category_id, String isbn) {
		this.id = id;
		this.bname = bname;
		this.isbn = isbn;
		this.qty = qty;
		this.price = price;
		this.author = author;
		this.category_id = category_id;
		this.photo = photo;
	}
	
	public Book(Long id, String bname, Integer qty, Double price, String photo, String author, Integer category_id, String isbn, Integer categoryID, String cname) {
		this.id = id;
		this.bname = bname;
		this.isbn = isbn;
		this.qty = qty;
		this.price = price;
		this.author = author;
		this.category_id = category_id;
		this.photo = photo;
		this.category_id = categoryID;
		this.cname = cname;
	}
	
//	public Book(Long id, String bname, String isbn, Integer qty, Double price, Blob photo, String author) {
//		this.id = id;
//		this.bname = bname;
//		this.isbn = isbn;
//		this.qty = qty;
//		this.price = price;
//		this.author = author;
//		this.blog = photo;
//	}
	
	public Book() {
		
	}
	
	public Book(Long id, String bname, String isbn, Integer qty, Double price, Blob photo, String author) {
		this.id = id;
		this.bname = bname;
		this.isbn = isbn;
		this.qty = qty;
		this.price = price;
		this.author = author;
		this.blog = photo;
	}

}