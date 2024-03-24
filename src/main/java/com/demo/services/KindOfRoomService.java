package com.demo.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.demo.entities.Area;
import com.demo.entities.Book;
import com.demo.entities.Category;
import com.demo.entities.Hotel;
import com.demo.entities.Kindofroom;

public interface KindOfRoomService {
	public Iterable<Kindofroom> findAll();
	public boolean save(Kindofroom kindor);
	public Kindofroom saveId(Kindofroom kindor);
	public Kindofroom find(int id);
	public boolean delete(Kindofroom kindor);
	public Kindofroom findbyidroom(int id_room);
	
	//owner
	public Kindofroom edit(int idRoom);
	// Thai
	public Kindofroom findKindofroomByIdRoom(int id_room);
}
