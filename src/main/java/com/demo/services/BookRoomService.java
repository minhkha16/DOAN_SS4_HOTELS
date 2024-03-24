package com.demo.services;


import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.demo.entities.Book;
import com.demo.entities.BookRoom;
import com.demo.entities.Hotel;
import com.demo.entities.Room;


public interface BookRoomService {
	public Iterable<BookRoom> findAll();

	public BookRoom find(int id);
	
	public List<BookRoom> findByIdBookRoom(int id);

	public boolean save(Book book);

	public boolean delete(Book book);
	
	public double SumHotel(int id);
	
	public List<BookRoom> findByIdRoleHotel(int id);
	
	public List<BookRoom> findByIdIdHotelStatistic(int id);
	
	public List<BookRoom> detailsBookByRoom(int id);
	// Thái
	public List<BookRoom> findBookRoomBy(int id_room,Date from,Date to);
	public List<BookRoom> findBookRoomByIdRoom(int id_room);
	public List<BookRoom> findBookRoomCheckAvaible(int id_room,Date from,Date to);
	public boolean save(BookRoom bookRoom);
	public List<BookRoom> findBookRoomByIdAccount(int id_account);
	public int countBookRoomByIdAccount(int id_account);
	public BookRoom findBookRoomByIdDatePriceAndIdBook(int id_dateprice,int id_book);

}
