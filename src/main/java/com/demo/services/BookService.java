package com.demo.services;


import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.demo.entities.Book;
import com.demo.entities.BookRoom;
import com.demo.entities.Hotel;
import com.demo.entities.Room;


public interface BookService {
	public Iterable<Book> findAll();

	public Book find(int id);
	
	public double sumTotal(boolean status, int id);
	
	public double sumTotalByAllHotel(boolean status, int idAcc, int sttHotel);
	
	public long sumMonth(int month, int year, int idHotel, boolean status);
	
	public boolean save(Book book);

	public boolean delete(Book book);
	
	public List<BookRoom> findByMonth(int month, int year, int idHotel);
	
	public List<BookRoom> takeByDate(Date from, Date to);
	
	public double takeByDate2(Date from, Date to, int idHotel, boolean status);
	
	public List<BookRoom> takeByDate3(Date from, Date to, int idRoom);
	
	public List<BookRoom> takeByDateByIdHotel(Date from, Date to, int idHotel);
	
	public Book takeById(int id);
	
	public List<Book> findByStatus(boolean status);
	
	public List<Book> findAllByIdRoleAccount(int id);
	
	public List<Book> findByCode(String code);
	
	public List<BookRoom> findByCodeInBook(String code, int idRoom);
	
	public List<Book> listAll();
	public Long countBooking();
	
	public List<Book> findAllByIdAccount(int id);
	// Thái
	public List<Book> findBookByDate(Date from, Date to);
	public Book findBookByIdBook(int id_book);
	public Book findBookByCodeBook(String codebook);
	

}
