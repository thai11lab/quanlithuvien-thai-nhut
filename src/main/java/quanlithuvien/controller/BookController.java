package quanlithuvien.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import quanlithuvien.entity.Book;
import quanlithuvien.entity.Category;
import quanlithuvien.service.BookService;
import quanlithuvien.service.CategoryService;

@WebServlet(urlPatterns = { "/books","/search" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 50, // 50MB
maxRequestSize = 1024 * 1024 * 50) 
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService;
	private CategoryService categoryService;

	public BookController() {
//		super();
//		// TODO Auto-generated constructor stub
//		bookService =
//		categoryService = ;
		bookService = new BookService();;
		categoryService = new CategoryService();
	}

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");  
		request.setCharacterEncoding("utf-8");
		HttpSession httpSession = request.getSession();
		String errorCode = httpSession.getAttribute("messageErrorCode")==null ? "":(String) httpSession.getAttribute("messageErrorCode");
		String errorName = httpSession.getAttribute("messageErrorName")==null ? "":(String) httpSession.getAttribute("messageErrorName");
		
//		if (!errorCode.isEmpty()) {
//			
//		}
		List<Book> listBook = new ArrayList<Book>();
		String action = request.getParameter("action");
		List<Category> lisCategories = categoryService.findAll();
		Book book = new Book();
		listBook = bookService.findAll();
	
		switch (action) {
		case "LIST":
			httpSession.removeAttribute("messageErrorCode");
			httpSession.removeAttribute("messageErrorName");
			request.setAttribute("listBook", listBook);
			request.getRequestDispatcher(request.getContextPath() + "/views/books/books.jsp").forward(request,
					response);
			break;
		case "ADD":
//			if (lisCategories == null) {
//				response.sendRedirect("");
//			}
			request.setAttribute("existCode",errorCode);
			request.setAttribute("existName", errorName);
			request.setAttribute("listCT", lisCategories);
			request.getRequestDispatcher(request.getContextPath() + "/views/books/addBooks.jsp").forward(request,
					response);
			break;
		case "EDIT":
			request.setAttribute("existCode",errorCode);
			request.setAttribute("existName", errorName);
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
//		case "SEARCH":
//			request.setAttribute("listBook", listBook);
//			request.getRequestDispatcher(request.getContextPath() + "/views/books/books.jsp").forward(request,response);
//			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");  
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action").toString();
		HttpSession httpSession = request.getSession();
		
		if (action.equals("SEARCH")) {		
			String key = request.getParameter("key").toString();
			List<Book> listBook = bookService.findBySearch(key);
			request.setAttribute("listBook", listBook);
			request.getRequestDispatcher(request.getContextPath()+"/views/books/books.jsp").forward(request, response);
		}else {
			String id = request.getParameter("id").toString();
			String name = request.getParameter("name").toString();
			String code = request.getParameter("code").toString();
			String company = request.getParameter("company").toString();
			int totalBook = Integer.parseInt(request.getParameter("totalBook").toString());
			Long categoryID = Long.parseLong(request.getParameter("category").toString());
			System.out.println(request.getParts());
			Part part = request.getPart("file");
			String fileName;
			
			if (!id.equals("")) {
				Long idU=Long.parseLong(id);
				fileName = uploadFile(part,idU);
			}else {
				fileName = uploadFile(part,null);
			}
			
			
		
			Category category = categoryService.findById(categoryID);
			
			Book book = new Book();
			book.setName(name);
			book.setCode(code);
			book.setCompany(company);
			book.setTotalBook(totalBook);
			book.setCategory(category);
			book.setImg_url(fileName);
			List<Book> bookDuplicateName = bookService.checkUseName(book);
			List<Book> bookDuplicateCode = bookService.checkUseCode(book);
			
			if ((bookDuplicateName.size()>0 || bookDuplicateCode.size()>0)) {
				if (bookDuplicateName.size()>0) {
					httpSession.setAttribute("messageErrorName","Đã tồn tại tên sách");
				}
				if (bookDuplicateCode.size()>0) {
					httpSession.setAttribute("messageErrorCode","Đã tồn tại mã sách");
				}
				if (id !=null && !id.equals("")) {
					
					if (bookDuplicateCode.size() <= 1 && bookDuplicateName.size() <= 1) {
						response.sendRedirect("books?action=EDIT&id="+id);
					}
					
				}else {
				
				
					response.sendRedirect("books?action=ADD");
					
				}
			}else {
				if (id != null && !(id.equals(""))) {
					Long idUpdate = Long.parseLong(id);
					bookService.update(book, idUpdate);
				} else {
					bookService.save(book);
				}
				response.sendRedirect("/books?action=LIST");
			}		
		}		
	}

	public String uploadFile(Part part,Long id) {
		String fileName = part.getSubmittedFileName().toString();
		
		File folderUpload = new File(getServletContext().getRealPath("/img"));
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}
		try {
			if (fileName == null && id != null) {
				Book book = bookService.findById(id);
				fileName = book.getImg_url();
			}else {
				String mainUrl = folderUpload.toString()+"\\"+fileName;
				part.write(mainUrl);
			}		
			return fileName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
