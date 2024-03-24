package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.demo.entities.Area;
import com.demo.entities.Book;
import com.demo.entities.Category;
import com.demo.entities.Role;
import com.demo.repository.AreaRepository;
import com.demo.repository.CategoryRepository;
import com.demo.repository.RoleRepository;

@Service
public class AreaServiceImpl implements AreaService{

	@Autowired
	private AreaRepository areaRepository;
	@Override
	public Iterable<Area> findAll() {
		return areaRepository.findAll();
	}
	@Override
	public boolean delete(int id) {
		try {
			areaRepository.delete(areaRepository.findById(id).get());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean save(Area area) {
		try {
			areaRepository.save(area);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Area findByIdRole(int id) {
		// TODO Auto-generated method stub
		return areaRepository.findById(id).get();
	}

	@Override
	public List<Area> listAll() {
		// TODO Auto-generated method stub
		return areaRepository.findAllbyIdDesc();
	}
	/// Thái 
	@Override
	@Async
	public List<Area> findTop6Area() {
		return  areaRepository.findTop6Area();
	}

	@Override
	@Async
	public int quantityHotelForArea(int id) {
		return areaRepository.quantityHotelForArea(id);
	}

	@Override
	@Async
	public Area findAreaByName(String name) {
		return areaRepository.search(name);
	}
	@Override
	public Area getAreaById(int id) {
		return areaRepository.getAreaById(id);
	}
}
