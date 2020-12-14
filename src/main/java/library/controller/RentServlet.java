package library.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import library.dao.BookDao;
import library.dao.RentDao;
import library.model.Book;
import library.model.Rent;
import library.model.User;

/**
 * Servlet implementation class RentServlet
 */
@WebServlet("/RentServlet")
public class RentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static BookDao bookDao;
	public static RentDao rentDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentServlet() {
        super();
        // TODO Auto-generated constructor stub
        bookDao = new BookDao();
        rentDao = new RentDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("action");
		
		switch (action) {
			case "adminRent":
				adminRent(request, response);
				break;
			case "rentBook":
				rentBook(request, response);
				break;
			case "URentList":
				try {
					uRentList(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "returnBook":
				try {
					returnBook(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
		}
	}

	private void returnBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));
		Long uid = Long.parseLong(request.getParameter("uid"));
		String name = request.getParameter("name");
		User user;
		try {
			rentDao.updateReturnDate(id, uid);
			user = bookDao.selectByName(name);
			
			request.setAttribute("rentList", rentDao.getURentList(uid));
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("URentList.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void uRentList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("uid"));
		String name = request.getParameter("name");
		User user;
		try {
			user = bookDao.selectByName(name);
			request.setAttribute("rentList", rentDao.getURentList(id));
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("URentList.jsp");
			rd.forward(request, response);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void rentBook(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		
		try {
			request.setAttribute("rentList", rentDao.getRentList());
			RequestDispatcher rd = request.getRequestDispatcher("RentList.jsp");
			rd.forward(request, response);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void adminRent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Long id= Long.parseLong(request.getParameter("id"));
		Long uid = Long.parseLong(request.getParameter("uid"));
		String uname = request.getParameter("uname");
		List<Book> bookList;
		User user;
		Book book;
		Rent rent;
		try {
			book = bookDao.rentByID(id);
			rent = new Rent(0L, book.getId(), book.getBlog(), book.getBname(), book.getAuthor(), uid, uname);
			rentDao.insertRent(rent);
			
			bookList = bookDao.getBook();
			user = bookDao.selectByName(uname);
			request.setAttribute("user", user);
			request.setAttribute("categoryList", bookDao.getBookCategory());
			request.setAttribute("bookList", bookList);
			RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
