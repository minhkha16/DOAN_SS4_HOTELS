package com.demo.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.demo.entities.Area;
import com.demo.entities.Category;
import com.demo.entities.Hotel;
import com.demo.entities.Image;
import com.demo.repository.ImageReponsitory;
import com.demo.services.AreaService;
import com.demo.services.ImageService;

@Service("imageService")
public class ImageServiceImpl implements ImageService{
	@Autowired
	private ImageReponsitory imageReponsitory;

	@Override
	public Iterable<Image> findAll() {
		return imageReponsitory.findAll();
	}

	@Override
	public boolean save(Image img) {
		try {
			imageReponsitory.save(img);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Image> edit(int idRoom) {
		return imageReponsitory.editImage(idRoom);
	}

	@Override
	public boolean delete(int id) {
		
		try {
			imageReponsitory.delete(imageReponsitory.findById(id).get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Image find(int id) {
		return imageReponsitory.findById(id).get();
	}

	@Override
	public int countImageByIdRoom(int idRoom) {
		return imageReponsitory.countImageByIdRoom(idRoom);
	}

	@Override
	@Async
	public Image find1ImageByIdRoom(int idRoom) {
		return imageReponsitory.find1ImageByIdRoom(idRoom);
	}
	
}
