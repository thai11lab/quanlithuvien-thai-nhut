package quanlithuvien.service;

import java.util.List;

import quanlithuvien.entity.Book;
import quanlithuvien.entity.Category;
import quanlithuvien.repository.BookRepository;
import quanlithuvien.repository.CategoryRepository;

public class CategoryService {
	
	private CategoryRepository categoryRepository;
	
	public CategoryService() {
		// TODO Auto-generated constructor stub
		categoryRepository = new CategoryRepository();
	}
	
	public List<Category> findAll() {
		List<Category> listCategory = categoryRepository.findAll();
		return listCategory;
	}
	
	public Category findById(Long id) {
		Category category = categoryRepository.findById(id);
		return category;
	}
//	public void save(Category category) {
//		categoryRepository.save(category);
//	}

	public void deleteById(Long id1) {
		categoryRepository.deleteById(id1);
		
	}

	public List<Category> findBySearch(String key) {
		// TODO Auto-generated method stub
		return categoryRepository.findBySearch(key);
	}

	public void update(Category category, Long idUpdate) {
		// TODO Auto-generated method stub
		categoryRepository.update(category,idUpdate);
	}

	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryRepository.save(category);
	}
}
