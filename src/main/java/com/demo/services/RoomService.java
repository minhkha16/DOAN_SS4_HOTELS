package com.demo.services;

import java.util.Date;
import java.util.List;

import com.demo.entities.Area;
import com.demo.entities.Hotel;
import com.demo.entities.Room;

public interface RoomService {
	public List<Room> listAll();
	public boolean delete (int id);
	public boolean edit(Room room);
	public Room findByIdRoom(int id);
	public List<Room> searchByPrice(int price1, int price2);
	public List<Room> searchByDate(int date1, int date2);
	
	//owner
	public Iterable<Room> findAll();

	public Room find(int id);
	
	public Room find2(int idRoom);

	public boolean save(Room room);
	
	public Room created(Room room);
	
	public boolean delete(Room room);
	
	public List<Room> findAllByIdRoleAccount(int id);
	
	public List<Room> takeByDate(Date from, Date to);
	
	public int sum_fixed();

	public int sumDetails_fixed(int id);
	
	public List<Room> listById(int id);
	
	public Room saveId(Room room);
	
	public List<Room> findByContent(String content);
	
	public List<Room> findByIdRoomHotel(int id);
	// Thái
	public List<Room> findRoomByIdHotel(int id_hotel);
	public int findMinPriceHotel(int id_room);
	public int findMinPriceHotelByIdHotel(int id_hotel);
	public List<Room> getListRoomByIdHotel(int id_hotel);
	public int checkAvailableByDate(int id_room,Date from,Date to);
	public int countQuantityRoomByIdHotel(int id_hotel);
	public Room findRoomByIdRoom(int id_room);
	public Room findFirstRoom();

}
