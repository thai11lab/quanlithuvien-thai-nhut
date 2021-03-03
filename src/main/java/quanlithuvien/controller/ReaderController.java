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
import quanlithuvien.entity.BookReader;
import quanlithuvien.entity.Category;
import quanlithuvien.entity.Reader;
import quanlithuvien.service.BookService;
import quanlithuvien.service.ReaderService;

@WebServlet(urlPatterns = {"/reader"})
public class ReaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private ReaderService readerService;
    private BookService bookService;
    public ReaderController() {
        super();
        // TODO Auto-generated constructor stub
        readerService = new ReaderService();
        bookService = new BookService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");  
		request.setCharacterEncoding("utf-8");
		List<Reader> lisReaders = readerService.findAll();
		List<Book> books = bookService.findAll();
		String action = request.getParameter("action");
		Reader reader = new Reader();
		request.setAttribute("listBook",books);
		
		switch (action) {
		case "LIST":
			request.setAttribute("lisReaders", lisReaders);
			request.setAttribute("view","/views/reader/reader.jsp");
			request.getRequestDispatcher(request.getContextPath() + "/views/layout.jsp").forward(request,
					response);
			break;
		case "ADD":		
			request.getRequestDispatcher(request.getContextPath() + "/views/reader/addReader.jsp").forward(request,
					response);
			break;
		case "EDIT":
			Long id = Long.parseLong(request.getParameter("id").toString());
			reader = readerService.findById(id);
//			List<BookReader>
			 
			request.setAttribute("reader", reader);
			request.getRequestDispatcher(request.getContextPath() + "/views/reader/updateReader.jsp").forward(request,
					response);
			break;
		case "DELETE":
			Long id1 = Long.parseLong(request.getParameter("id").toString());
			readerService.deleteById(id1);
			response.sendRedirect("/books?action=LIST");
			break;
//		case "SEARCH":
//			request.setAttribute("listBook", listBook);
//			request.getRequestDispatcher(request.getContextPath() + "/views/books/books.jsp").forward(request,response);
//			break;
		default:
			break;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");  
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action").toString();
		
		
		if (action.equals("SEARCH")) {		
			String key = request.getParameter("key").toString();
			List<Reader> lisReaders = readerService.findBySearch(key);
			request.setAttribute("lisReaders", lisReaders);
			request.getRequestDispatcher(request.getContextPath()+"/views/books/books.jsp").forward(request, response);
		}else {
			String id = request.getParameter("id").toString();
			String name = request.getParameter("name").toString();
			String code = request.getParameter("code").toString();
			String address = request.getParameter("address").toString();
			int age = Integer.parseInt(request.getParameter("age").toString());
			
			List<Long> productIdL = new ArrayList<Long>();
			String[] productId = request.getParameterValues("product_id");
			for (int i = 0; i < productId.length; i++) {
				productIdL.add((long) Integer.parseInt(productId[i]));
			}
			
			
//			Book book = bookService.findById(id);

			Reader reader = new Reader();
			reader.setName(name);
			reader.setCode(code);
			reader.setAddress(address);
			reader.setAge(age);
//			book.setCompany(company);
//			book.setTotalBook(totalBook);
//			book.setCategory(category);
			if (id != null && !(id.equals(""))) {
				Long idUpdate = Long.parseLong(id);
				readerService.update(reader, idUpdate,productIdL);
			} else {
				readerService.save(reader,productIdL);
			}
			response.sendRedirect("/reader?action=LIST");
		}		
	}
}
