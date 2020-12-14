package library.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import library.dao.BookDao;
import library.model.Book;
import library.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
@MultipartConfig()
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static BookDao bookDao;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
		bookDao = new BookDao();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("action");

		switch (action) {
			case "register":
				try {
					registerUser(request, response);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				break;
			case "login":
				loginUser(request, response);
				break;
			case "newBook":
				newBook(request, response);
				break;
			case "view":
				try {
					viewBook(request, response);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "admin":
				viewAdmin(request, response);
				break;
			case "byID":
				byID(request, response);
				break;
			case "newForm":
				newForm(request, response);
				break;
			case "search":
				search(request, response);
				break;
			case "byAdminID":
				byAdminID(request, response);
				break;
			case "searchAdmin":
				searchAdmin(request, response);
				break;
			case "showEditForm":
				try {
					showEditForm(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "update":
				try {
					update(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "delete":
				delete(request, response);
				break;
			default:
				loginUser(request, response);
				break;
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));
		List<Book> bookList;
		try {
			bookDao.deleteLibrary(id);
			bookList = bookDao.getBook();
			request.setAttribute("categoryList", bookDao.getBookCategory());
			request.setAttribute("bookList", bookList);
			RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));
		String bname = request.getParameter("bname");
		String isbn = request.getParameter("isbn");
		Integer qty = Integer.parseInt(request.getParameter("qty"));
		Double price = Double.parseDouble(request.getParameter("price"));
		String author = request.getParameter("author");
		Integer category_id = Integer.parseInt(request.getParameter("category"));
		// String photo = request.getParameter("photo");
		// String message = "";

		List<Book> bookList;
		// Book photo Upload
		InputStream inputStream;

		Part file = request.getPart("photo");
		inputStream = file.getInputStream();

		Book book = new Book(id, bname, qty, price, inputStream, author, category_id, isbn);

		try {
			bookDao.updateStudent(book);
			bookList = bookDao.getBook();
			request.setAttribute("categoryList", bookDao.getBookCategory());
			request.setAttribute("bookList", bookList);
			RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Book book;
		try {
			book = bookDao.selectByID(id);
			RequestDispatcher rd = request.getRequestDispatcher("updateForm.jsp");
			request.setAttribute("bookList", book);
			request.setAttribute("categoryList", bookDao.getBookCategory());
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void searchAdmin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("category"));

		try {
			request.setAttribute("bookList", bookDao.categoryID(id));
			RequestDispatcher rd = request.getRequestDispatcher("AdminSearch.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("category"));
		String name = request.getParameter("name");
		User user;

		try {
			user = bookDao.selectByName(name);
			request.setAttribute("user", user);
			request.setAttribute("bookList", bookDao.categoryID(id));
			RequestDispatcher rd = request.getRequestDispatcher("Search.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void newForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("InsertBook.jsp");

		try {
			request.setAttribute("categoryList", bookDao.getBookCategory());
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void byID(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("uname");
		User user;
		try {
			user = bookDao.selectByName(name);
			request.setAttribute("bookList", bookDao.selectByID(id));
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("Detail.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void byAdminID(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));
		try {
			request.setAttribute("bookList", bookDao.selectByID(id));
			RequestDispatcher rd = request.getRequestDispatcher("AdminDetail.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void viewAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		List<Book> bookList;
		try {
			bookList = bookDao.getBook();
			request.setAttribute("categoryList", bookDao.getBookCategory());
			request.setAttribute("bookList", bookList);
			RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void viewBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		List<Book> bookList;
		User user;
		try {
			
			bookList = bookDao.getBook();
			user = bookDao.selectByName(name);
			request.setAttribute("user", user);
			request.setAttribute("categoryList", bookDao.getBookCategory());
			request.setAttribute("bookList", bookList);
			RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void newBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String bname = request.getParameter("bname");
		String isbn = request.getParameter("isbn");
		Integer qty = Integer.parseInt(request.getParameter("qty"));
		Double price = Double.parseDouble(request.getParameter("price"));
		String author = request.getParameter("author");
		Integer category_id = Integer.parseInt(request.getParameter("category"));
		// String photo = request.getParameter("photo");
		// String message = "";

		List<Book> bookList;
		// Book photo Upload
		InputStream inputStream;

		Part file = request.getPart("photo");
		inputStream = file.getInputStream();

		Book book = new Book(0L, bname, qty, price, inputStream, author, category_id, isbn);

		try {
			bookDao.insertBook(book);
			bookList = bookDao.getBook();
			request.setAttribute("categoryList", bookDao.getBookCategory());
			request.setAttribute("bookList", bookList);
			RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String message;
		List<Book> bookList;

		User user;

		try {
			user = bookDao.selectByName(name);
			if (user != null) {
				String dpass = user.getPass();
				String role = user.getRole();
				if (pass.equals(dpass)) {

					if (role.equals("Admin")) {
						try {
							bookList = bookDao.getBook();
							request.setAttribute("categoryList", bookDao.getBookCategory());
							request.setAttribute("bookList", bookList);
							RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
							rd.forward(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						message = "Login Successfull!";

						try {
							bookList = bookDao.getBook();
							request.setAttribute("categoryList", bookDao.getBookCategory());
							request.setAttribute("bookList", bookList);
							request.setAttribute("message", message);
							request.setAttribute("user", user);
							RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
							rd.forward(request, response);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				} else {
					message = "Incorrect Password! Please try agian!";
					RequestDispatcher rdd = request.getRequestDispatcher("Login.jsp");
					request.setAttribute("message", message);
					rdd.forward(request, response);
				}
			} else {
				message = "No User Found!. Please Register!!";
				RequestDispatcher rdd = request.getRequestDispatcher("Register.jsp");
				request.setAttribute("message", message);
				rdd.forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String cpass = request.getParameter("cpass");
		String role = request.getParameter("role");
		String error = "";
		List<Book> bookList;
		User user;
		
		if (pass.equals(cpass)) {

			if (role.equals("User")) {
				user = new User(0L, name, cpass, role);
				try {
					bookDao.insertUser(user);
//					bookDao.createTable(user);
					user = bookDao.selectByName(name);
					bookList = bookDao.getBook();
					bookList = bookDao.getBook();
					request.setAttribute("user", user);
					request.setAttribute("categoryList", bookDao.getBookCategory());
					request.setAttribute("bookList", bookList);
					request.setAttribute("bookList", bookList);
					request.setAttribute("categoryList", bookDao.getBookCategory());
					RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
					rd.forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					error = "UserName is taken! Enter another name";
					RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
					request.setAttribute("message", error);
					rd.forward(request, response);
					//e.printStackTrace();
				}

			} else {

				user = new User(0L, name, cpass, role);
				try {
					bookDao.insertUser(user);
					bookList = bookDao.getBook();
					request.setAttribute("categoryList", bookDao.getBookCategory());
					RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
					request.setAttribute("bookList", bookList);
					rd.forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					error = "UserName is taken! Enter another name";
					RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
					request.setAttribute("message", error);
					rd.forward(request, response);
				}
			}

		} else {
			error = "Password Not Match, Please Try Again!";
			RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
			request.setAttribute("message", error);
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
