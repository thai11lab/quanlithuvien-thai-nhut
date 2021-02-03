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
import quanlithuvien.entity.Category;
import quanlithuvien.service.BookService;
import quanlithuvien.service.CategoryService;

@WebServlet(urlPatterns = { "/books" })
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookService();;

	private CategoryService categoryService = new CategoryService();

	public BookController() {
//		super();
//		// TODO Auto-generated constructor stub
//		bookService =
//		categoryService = ;
	}

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> listBook = new ArrayList<Book>();
		String action = request.getParameter("action");
		List<Category> lisCategories = categoryService.findAll();

		Book book = new Book();
		listBook = bookService.findAll();

		switch (action) {
		case "LIST":
			request.setAttribute("listBook", listBook);
			request.getRequestDispatcher(request.getContextPath() + "/views/books/books.jsp").forward(request,
					response);
			break;
		case "ADD":
//			if (lisCategories == null) {
//				response.sendRedirect("");
//			}
			request.setAttribute("listCT", lisCategories);
			request.getRequestDispatcher(request.getContextPath() + "/views/books/addBooks.jsp").forward(request,
					response);
			break;
		case "EDIT":
//			Long id = Long.parseLong(request.getParameter("id").toString()); 
			Long id = Long.parseLong(request.getParameter("id").toString());
			book = bookService.findById(id);
			request.setAttribute("listCT", lisCategories);
			request.setAttribute("bookUpdate", book);
			request.getRequestDispatcher(request.getContextPath() + "/views/books/update.jsp").forward(request,
					response);
			break;
		case "DELETE":
			Long id1 = Long.parseLong(request.getParameter("id").toString());
			bookService.deleteById(id1);
			response.sendRedirect("/books?action=LIST");
			break;
		case "SEARCH":
			request.setAttribute("listBook", listBook);
			request.getRequestDispatcher(request.getContextPath() + "/views/books/books.jsp").forward(request,
					response);
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("dsdsja");
		String id = request.getParameter("id").toString();

		String name = request.getParameter("name").toString();
		String code = request.getParameter("code").toString();
		String company = request.getParameter("company").toString();
		int totalBook = Integer.parseInt(request.getParameter("totalBook").toString());
		Long categoryID = Long.parseLong(request.getParameter("category").toString());
		Category category = categoryService.findById(categoryID);

		Book book = new Book();
		book.setName(name);
		book.setCode(code);
		book.setCompany(company);
		book.setTotalBook(totalBook);
		book.setCategory(category);
		if (id != null && !(id.equals(""))) {
			Long idUpdate = Long.parseLong(id);
			bookService.update(book, idUpdate);
		} else {
			bookService.save(book);
		}

		response.sendRedirect("/books?action=LIST");
	}

}
