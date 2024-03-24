package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Area;
import com.demo.entities.Category;
import com.demo.entities.Hotel;
import com.demo.entities.Kindofroom;
import com.demo.repository.KindOfRoomReponsitory;
import com.demo.services.AreaService;
import com.demo.services.KindOfRoomService;

@Service("kindofRoomService")
public class KindOfRoomServiceImpl implements KindOfRoomService{
	@Autowired
	private KindOfRoomReponsitory kindOfRoomReponsitory;

	@Override
	public Iterable<Kindofroom> findAll() {
		return kindOfRoomReponsitory.findAll();
	}
	
	@Override
	public boolean save(Kindofroom kindor) {
		try {
			kindOfRoomReponsitory.save(kindor);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean delete(Kindofroom kindor) {
		try {
			kindOfRoomReponsitory.delete(kindor);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Kindofroom saveId(Kindofroom kindor) {
		try {
			return kindOfRoomReponsitory.save(kindor);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Kindofroom find(int id) {
		return kindOfRoomReponsitory.findById(id).get();
	}
	
	@Override
	public Kindofroom findbyidroom(int id_room) {
		return kindOfRoomReponsitory.findbyIdroom(id_room);
	}

	@Override
	public Kindofroom edit(int idRoom) {
		return kindOfRoomReponsitory.editKindofroom(idRoom);
	}

	@Override
	public Kindofroom findKindofroomByIdRoom(int id_room) {
		return kindOfRoomReponsitory.findKindofroomByIdRoom(id_room);
	}
}
