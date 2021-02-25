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
import quanlithuvien.entity.Reader;
import quanlithuvien.service.ReaderService;

@WebServlet(urlPatterns = {"/reader"})
public class ReaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private ReaderService readerService;
    public ReaderController() {
        super();
        // TODO Auto-generated constructor stub
        readerService = new ReaderService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");  
		request.setCharacterEncoding("utf-8");
		List<Reader> lisReaders = new ArrayList<Reader>();
		String action = request.getParameter("action");
		

		Reader reader = new Reader();
		lisReaders = readerService.findAll();

		switch (action) {
		case "LIST":
			request.setAttribute("lisReaders", lisReaders);
			request.setAttribute("view","/views/reader/reader.jsp");
			request.getRequestDispatcher(request.getContextPath() + "/views/layout.jsp").forward(request,
					response);
			break;
		case "ADD":
			request.getRequestDispatcher(request.getContextPath() + "/views/books/addBooks.jsp").forward(request,
					response);
			break;
		case "EDIT":
			Long id = Long.parseLong(request.getParameter("id").toString());
			reader = readerService.findById(id);
			request.setAttribute("reader", reader);
			request.getRequestDispatcher(request.getContextPath() + "/views/books/update.jsp").forward(request,
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
