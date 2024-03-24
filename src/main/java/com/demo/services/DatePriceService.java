package com.demo.services;

import java.util.Date;
import java.util.List;

import com.demo.entities.Area;
import com.demo.entities.Category;
import com.demo.entities.DatePrice;
import com.demo.entities.Hotel;
import com.demo.entities.Kindofroom;
import com.demo.entities.Room;


public interface DatePriceService {
	public Iterable<DatePrice> findAll();
	
	public boolean save(DatePrice dateprice);
	
	public DatePrice find(int id);
	
	public boolean delete(DatePrice dateprice);
	
	public DatePrice edit(int idRoom);
	
	public List<DatePrice> finbydesc();
	
	public List<DatePrice> searchByPrice(double price1, double price2);
	public List<DatePrice> searchByDate(int date1, int date2);
	
	// Thái
	public DatePrice findDatePriceByIdRoomAndQuantityday( int id_room,int quantityDate);
	public DatePrice findDatePriceByIdRoom(int id_room);
	public Date getEndDate(Date from,int quantityDay);

}
