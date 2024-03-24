package com.demo.services;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.demo.entities.Area;
import com.demo.entities.Book;
import com.demo.entities.BookRoom;
import com.demo.entities.Category;
import com.demo.entities.Hotel;
import com.demo.repository.BookRoomReponsitory;
import com.demo.services.BookRoomService;

@Service("bookRoomService")
public class BookRoomServiceImpl implements BookRoomService{
	@Autowired
	private BookRoomReponsitory bookRoomReponsitory;

	@Override
	public Iterable<BookRoom> findAll() {
		return bookRoomReponsitory.findAll();
	}

	@Override
	public BookRoom find(int id) {
		return bookRoomReponsitory.findById(id).get();
	}

	@Override
	public List<BookRoom> findByIdBookRoom(int id) {
		return bookRoomReponsitory.findByIdBookRoom(id);
	}
	
	@Override
	public boolean save(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double SumHotel(int id) {
		try {
			return bookRoomReponsitory.findSumHotel(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<BookRoom> findByIdRoleHotel(int id) {
		return bookRoomReponsitory.findByIdHotel(id);
	}

	@Override
	public List<BookRoom> findByIdIdHotelStatistic(int id) {
		return bookRoomReponsitory.findByIdHotelStatistic(id);
	}
	// Thái
	@Override
	@Async
	public List<BookRoom> findBookRoomBy(int id_room,Date from,Date to) {
		return bookRoomReponsitory.findBookRoomBy(id_room,from,to);
	}

	@Override
	@Async
	public List<BookRoom> findBookRoomByIdRoom(int id_room) {
		return bookRoomReponsitory.findBookRoomByIdRoom(id_room);
	}

	@Override
	@Async
	public List<BookRoom> findBookRoomCheckAvaible(int id_room, Date from, Date to) {
		return bookRoomReponsitory.findBookRoomCheckAvaible(id_room, from, to);
	}
	@Override
	@Async
	public boolean save(BookRoom bookRoom) {
		try {
			bookRoomReponsitory.save(bookRoom);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	@Async
	public int countBookRoomByIdAccount(int id_account) {
		return bookRoomReponsitory.countBookRoomByIdAccount(id_account);
	}
	
	@Override
	@Async
	public List<BookRoom> detailsBookByRoom(int id) {
		return bookRoomReponsitory.detailsBookByRoom(id);
	}

	@Override
	@Async
	public List<BookRoom> findBookRoomByIdAccount(int id_account) {
		return bookRoomReponsitory.findBookRoomByIdAccount(id_account);
	}
	@Override
	@Async
	public BookRoom findBookRoomByIdDatePriceAndIdBook(int id_dateprice, int id_book) {
		return bookRoomReponsitory.findBookRoomByIdDatePriceAndIdBook(id_dateprice, id_book);
	}

	
	
}
