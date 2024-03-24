package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Category;
import com.demo.entities.Role;
import com.demo.repository.CategoryRepository;
import com.demo.repository.RoleRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	


	@Override
	public boolean delete(int id) {
		try {
			categoryRepository.delete(categoryRepository.findById(id).get());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean save(Category category) {
		try {
			categoryRepository.save(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Category findByIdRole(int id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id).get();
	}

	@Override
	public List<Category> listAll() {
		// TODO Auto-generated method stub
		return categoryRepository.findAllbyIdDesc();
	}

}
