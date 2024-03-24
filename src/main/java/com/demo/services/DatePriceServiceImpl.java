package com.demo.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.demo.entities.Area;
import com.demo.entities.Book;
import com.demo.entities.Category;
import com.demo.entities.DatePrice;
import com.demo.entities.Hotel;
import com.demo.entities.Room;
import com.demo.repository.DatePriceReponsitory;
import com.demo.services.AreaService;
import com.demo.services.DatePriceService;

@Service("datePriceService")
public class DatePriceServiceImpl implements DatePriceService{
	@Autowired
	private DatePriceReponsitory datePriceReponsitory;

	@Override
	public Iterable<DatePrice> findAll() {
		return datePriceReponsitory.findAll();
	}

	@Override
	public boolean save(DatePrice dateprice) {
		try {
			datePriceReponsitory.save(dateprice);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean delete(DatePrice dateprice) {
		try {
			datePriceReponsitory.delete(dateprice);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public DatePrice find(int id) {
		return datePriceReponsitory.findById(id).get();
	}

	@Override
	public DatePrice edit(int idRoom) {
		return datePriceReponsitory.editDatePrice(idRoom);
	}

	@Override
	public List<DatePrice> finbydesc() {
		
		return datePriceReponsitory.findbydesc();
	}
	@Override
	public List<DatePrice> searchByPrice(double price1, double price2) {
		return datePriceReponsitory.searchByPrice(price1, price2);
		
		
	}
	
	@Override
	public List<DatePrice> searchByDate(int date1, int date2) {
		return datePriceReponsitory.searchBydate(date1, date2);
		
	}
	// Thái
	@Override
	@Async
	public DatePrice findDatePriceByIdRoomAndQuantityday(int id_room,int quantityDate) {
		return datePriceReponsitory.findDatePriceByIdRoomAndQuantityday(id_room,quantityDate);
	}

	@Override
	@Async
	public DatePrice findDatePriceByIdRoom(int id_room) {
		return datePriceReponsitory.findDatePriceByIdRoom(id_room);
	}
	@Override
	@Async
	public Date getEndDate(Date from, int quantityDay) {
		 Calendar cal = Calendar.getInstance();
	        cal.setTime(from);
	        cal.add(Calendar.DATE, quantityDay);
		return cal.getTime();
	}



}
