package com.demo.services;

import java.util.List;

import com.demo.entities.Area;
import com.demo.entities.Hotel;

public interface HotelService {
	public List<Hotel> listAll();
	public boolean delete (int id);
	public boolean edit(Hotel hotel);
	public Hotel findByIdHotel(int id);
	public List<Hotel> search(String keyword);
	
	
	//owner
	public Iterable<Hotel> findAll();

	public Hotel find(int id);

	public boolean save(Hotel hotel);

	public boolean delete(Hotel hotel);

	public List<Hotel> findByName(String keyword);
	
	public double sumTotalByHotel(int id);
	
	public int countPaid(int id);
	
	public int countCancel(int id);
	
	public List<Hotel> findAllByIdRoleAccount(int id, int status);
	
	public Long countHotel();
	
	
	// Thái
	public List<Hotel> findHotelByArea(int id_area);
	public double getAverageStarHotel(int id_hotel);
	public int getCountEvaluateHotel(int id_hotel);
	public List<Hotel> findHotelByNameAndIdArea(String name,int id_area);
	public Hotel findHotelByIdHotel(int id_hotel);
	public List<Hotel> findAllHome();

}
