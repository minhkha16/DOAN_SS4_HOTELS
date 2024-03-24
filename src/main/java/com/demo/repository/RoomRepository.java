package com.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.entities.Account;
import com.demo.entities.Area;
import com.demo.entities.Category;
import com.demo.entities.Hotel;
import com.demo.entities.Role;
import com.demo.entities.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

	@Query("from Room order by hotel.id desc")
	public List<Room> findAllbyIdDesc();
	
	@Query("from Room where id =:id")
	public List<Room> listById(@Param("id")int id);
	
	@Query("from Room where hotel.id = :id")
	public List<Room> findByIdRoom(@PathVariable("id") int id);
	
	@Query("from Room where content like %:content%")
	public List<Room> findByName(@Param("content") String content);
	
	@Query("from Room where id = :id")
	public Room findByIdRoom2(@PathVariable("id") int id);
	
	@Query("from Room where created >= :from and created <= :to")
	public List<Room> takeByDate(@Param("from") Date from, @Param("to") Date to);
	
	@Query("select sum(fixedQuantity) from Room")
	public int findByfixed_quantity();
	
	@Query("select sum(fixedQuantity) from Room where hotel.id = :id")
	public int findById_fixedRoom(@PathVariable("id") int id);
	
	@Query("from Room where hotel.account.id = :id")
	public List<Room> findAllByIdRoleAccount(@Param("id") int id);
	
	@Query("from Room where hotel.id = :id")
	public List<Room> findByIdDeltaiHotel(@Param("id") int id);
	// Thái
	@Query("from Room where hotel.id = :id_hotel")
	public List<Room> findRoomByIdHotel(@Param("id_hotel") int id_hotel);
	@Query("from Room where id = :id_room")
	public Room findRoomByIdRoom(@Param("id_room") int id_room);
	@Query("select IFNULL(min(price),1000) from DatePrice where room.id = :id_room")
	public int findMinPriceHotel(@Param("id_room") int id_room);
	@Query("from Room where hotel.id = :id_hotel")
	public List<Room> getListRoomByIdHotel(@Param("id_hotel") int id_hotel);
	@Query("select IFNULL(count(id),0) from Room where hotel.id = :id_hotel")
	public int countQuantityRoomByIdHotel(@Param("id_hotel") int id_hotel);
	@Query("from Room order by id asc limit 1")
	public Room findFirstRoom();

}
