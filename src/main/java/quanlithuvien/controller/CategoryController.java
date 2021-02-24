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

@WebServlet(urlPatterns = { "/category" })
public class CategoryController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	private CategoryService categoryService;

	public CategoryController() {
		categoryService = new CategoryService();
	}

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");  
		request.setCharacterEncoding("utf-8");
		List<Category> listCategories = new ArrayList<Category>();
		String action = request.getParameter("action");
		

		Category category = new Category();
		listCategories = categoryService.findAll();

		switch (action) {
		case "LIST":
			request.setAttribute("listCategories", listCategories);
			request.setAttribute("view","/views/category/category.jsp");
			request.getRequestDispatcher(request.getContextPath() + "/views/layout.jsp").forward(request,
					response);
			break;
		case "ADD":	
			request.setAttribute("view","/views/category/addCategory.jsp");
			request.getRequestDispatcher(request.getContextPath() + "/views/layout.jsp").forward(request,
					response);
			break;
		case "EDIT":
			Long id = Long.parseLong(request.getParameter("id").toString());
			category = categoryService.findById(id);
			request.setAttribute("categoryUpdate", category);
			request.setAttribute("view","/views/category/update.jsp");
			request.getRequestDispatcher(request.getContextPath() + "/views/layout.jsp").forward(request,
					response);
			break;
		case "DELETE":
			Long id1 = Long.parseLong(request.getParameter("id").toString());
			categoryService.deleteById(id1);
			response.sendRedirect("/category?action=LIST");
			break;

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
		
		
		if (action.equals("SEARCH")) {		
			String key = request.getParameter("key").toString();
			List<Category> lisCategories = categoryService.findBySearch(key);
			request.setAttribute("listCategories", lisCategories);
			request.setAttribute("view","/views/category/category.jsp");
			request.getRequestDispatcher(request.getContextPath()+"/views/layout.jsp").forward(request, response);
		}else {
			String id = request.getParameter("id").toString();
			String name = request.getParameter("name").toString();
			String code = request.getParameter("code").toString();
			Category category = new Category();
			category.setName(name);
			category.setCode(code);
			if (id != null && !(id.equals(""))) {
				Long idUpdate = Long.parseLong(id);
				categoryService.update(category, idUpdate);
			} else {
				categoryService.save(category);
			}
			response.sendRedirect("/category?action=LIST");
		}		
	}
}
