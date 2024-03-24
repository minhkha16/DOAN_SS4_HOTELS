package com.demo.services;

import java.util.List;

import com.demo.entities.Category;


public interface CategoryService {
	public List<Category> listAll();
	public boolean delete (int id);
	public boolean save(Category category);
	public Category findByIdRole(int id);
}
