package com.ekart.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekart.exception.CategoryException;
import com.ekart.model.Category;
import com.ekart.model.CurrentAdminSession;
import com.ekart.model.Product;
import com.ekart.repository.AdminSessionRepo;
import com.ekart.repository.CategoryRepo;
import com.ekart.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo cr;
	
	@Autowired
	private AdminSessionRepo adsrepo;

	@Override
	public Category addCategory(Category category, String key) throws CategoryException {

		List<CurrentAdminSession> list=adsrepo.findByUuid(key);
		
		if(list.size()==0)
			throw new CategoryException("you don't have authority to add category");
		
		List<Category> cat=cr.findByName(category.getName());
		
		if(cat.size()!=0) {
			throw new CategoryException("category already registered with this name id is "+cat.get(0).getCategoryId());
		}
		
		return cr.save(category);
	}

	@Override
	public Category viewCategory(int categoryId) throws CategoryException {
		Optional<Category> c=cr.findById(categoryId);
		if(c.isPresent()) {
			return c.get();
		}
		throw new CategoryException("category not found with id "+categoryId);
	}

	@Override
	public Category deleteCategory(int categoryId, String key) throws CategoryException {

		List<CurrentAdminSession> list=adsrepo.findByUuid(key);
		
		if(list.size()==0)
			throw new CategoryException("you don't have authority to delete category");
		
		Optional<Category> c=cr.findById(categoryId);
		if(c.isPresent()) {
			cr.delete(c.get());
			return c.get();
		}
		throw new CategoryException("category not found with id "+categoryId);
	}

	@Override
	public List<Category> allCategory() throws CategoryException {
		List<Category>list=cr.findAll();
		if(list.size()==0) {
			throw new CategoryException("list empty");
		}
		return list;
	}

	@Override
	public List<Product> productByCategory(int categoryId) throws CategoryException {
		Optional<Category> c=cr.findById(categoryId);
		if(c.isPresent()) {
			return c.get().getProducts();
		}
		throw new CategoryException("category not found with id "+categoryId);
	}

}
