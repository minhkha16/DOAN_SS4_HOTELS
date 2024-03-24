package com.demo.services;

import java.util.List;

import com.demo.entities.Area;

public interface AreaService {
	public List<Area> listAll();
	public boolean delete (int id);
	public boolean save(Area area);
	public Area findByIdRole(int id);
	public Iterable<Area> findAll();
	
	
	/// Thái
	public List<Area> findTop6Area();
	public int quantityHotelForArea(int id);
	public Area findAreaByName(String name);
	// public Iterable<Area> findAll();
	public Area getAreaById(int id);

}
