package quanlithuvien.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quanlithuvien.entity.Book;
import quanlithuvien.entity.Category;
import quanlithuvien.entity.Reader;
import quanlithuvien.service.BookService;
import quanlithuvien.service.CategoryService;
import quanlithuvien.service.ReaderService;

@WebServlet(urlPatterns = {"/home-admin"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService;
	private ReaderService readerService;
	private CategoryService categoryService;
    public HomeController() {
        super();
       bookService = new BookService();
       
       readerService = new ReaderService();
       categoryService = new CategoryService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> books = bookService.findAll();
		List<Reader> readers = readerService.findAll();
		List<Category> categories = categoryService.findAll();
		request.setAttribute("books", books.size());
		request.setAttribute("readers", readers.size());
		request.setAttribute("categories", categories.size());
		request.setAttribute("view","/views/home.jsp");
		request.getRequestDispatcher(request.getContextPath()+"/views/layout.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
