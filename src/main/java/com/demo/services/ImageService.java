package com.demo.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.demo.entities.Area;
import com.demo.entities.Category;
import com.demo.entities.Hotel;
import com.demo.entities.Image;

public interface ImageService {
	public Iterable<Image> findAll();

	public Image find(int id);

	public boolean save(Image img);
	
	public List<Image> edit(int idRoom);

	public boolean delete(int id);
	
	public int countImageByIdRoom(int idRoom);
	
	public Image find1ImageByIdRoom(int idRoom);
	
}
