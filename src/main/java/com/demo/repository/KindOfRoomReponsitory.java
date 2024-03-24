package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Account;
import com.demo.entities.Area;
import com.demo.entities.Category;
import com.demo.entities.Hotel;
import com.demo.entities.Kindofroom;


@Repository
public interface KindOfRoomReponsitory extends CrudRepository<Kindofroom, Integer> {
	@Query("from Kindofroom where room.id = :id_room")
	public Kindofroom findbyIdroom(@Param("id_room") int id_room);
	
	@Query("from Kindofroom where room.id = :idRoom")
	public Kindofroom editKindofroom(@Param("idRoom") int idRoom);
	@Query("from Kindofroom where room.id = :idRoom")
	public List<Kindofroom> roomDetail(@Param("idRoom") int idRoom);
	
	// Thai
	@Query("from Kindofroom where room.id = :id_room")
	public Kindofroom findKindofroomByIdRoom(@Param("id_room") int id_room);
	

}
