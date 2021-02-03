package quanlithuvien.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quanlithuvien.entity.Book;
import quanlithuvien.service.BookService;
import quanlithuvien.service.CategoryService;

@WebServlet(urlPatterns = {"/search-book"})
public class BookSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService= new BookService();;

	private CategoryService categoryService=new CategoryService();
	
    public BookSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<Book> listBook = new ArrayList<Book>();
//		String key = request.getParameter("key");
//		if(key.equals("")) {
//			listBook=bookService.findAll();
//		}else {
//			listBook = bookService.findBySearch(key);
//		}
//		request.getRequestDispatcher(request.getContextPath()+"")
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
