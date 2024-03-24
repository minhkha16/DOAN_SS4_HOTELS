package com.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.demo.entities.Book;
import com.demo.entities.BookRoom;
import com.demo.entities.Hotel;
import com.demo.entities.Room;
import com.demo.repository.BookReponsitory;
import com.demo.services.BookService;


@Service("bookService")
public class BookServiceImpl implements BookService{
	@Autowired
	private BookReponsitory bookReponsitory;
	@Override
	public Iterable<Book> findAll() {
		return bookReponsitory.findAll();
	}
	
	@Override
	public List<Book> findByCode(String code) {
		return bookReponsitory.findByCode(code);
	}
	


	@Override
	public Book find(int id) {
		return bookReponsitory.findById(id).get();
	}

	@Override
	public boolean save(Book book) {
		try {
			bookReponsitory.save(book);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Book book) {
		try {
			bookReponsitory.delete(book);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public double sumTotal(boolean status, int id) {
		try {
			return bookReponsitory.findByTotal(status, id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public List<Book> findAllByIdAccount(int id) {
		return bookReponsitory.findAllByIdAccount(id);
	}

	@Override
	public List<BookRoom> takeByDate(Date from, Date to) {
		try {
			return bookReponsitory.takeByDate(from, to);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<BookRoom> findByMonth(int month, int year, int idHotel) {
		return bookReponsitory.findByMonthList(month, year, idHotel);
	}

	@Override
	public double takeByDate2(Date from, Date to, int idHotel, boolean status) {
		try {
			return bookReponsitory.takeByDate2(from, to, idHotel, status);
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public Book takeById(int id) {
		return bookReponsitory.takeById(id);
	}
	
	@Override
	public List<Book> findByStatus(boolean status) {
		return bookReponsitory.findByStatus(status);
	}

	@Override
	public long sumMonth(int month, int year, int idHotel, boolean status) {
		try {
			return bookReponsitory.findByMonth(month, year, idHotel, status);
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}


	@Override
	public List<Book> findAllByIdRoleAccount(int id) {
		return bookReponsitory.findAllByIdRoleAccount(id);
	}

	@Override
	public List<Book> listAll() {
		
		return bookReponsitory.listAll();
	}

	@Override
	public Long countBooking() {
		// TODO Auto-generated method stub
		return bookReponsitory.countbooking();
	}

	@Override
	public List<BookRoom> findByCodeInBook(String code, int idRoom) {
		return bookReponsitory.findByCodeInBook(code, idRoom);
	}

	@Override
	public List<BookRoom> takeByDate3(Date from, Date to, int idRoom) {
		try {
			return bookReponsitory.takeByDate3(from, to, idRoom);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public double sumTotalByAllHotel(boolean status, int idAcc, int sttHotel) {
		try {
			return bookReponsitory.sumTotalByAllHotel(status, idAcc, sttHotel);
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public List<BookRoom> takeByDateByIdHotel(Date from, Date to, int idHotel) {
		try {
			return bookReponsitory.takeByDateByIdHotel(from, to, idHotel);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	// Thái
	@Override
	@Async
	public List<Book> findBookByDate(Date from, Date to) {
		return bookReponsitory.findBookByDate(from,to);
	}
	@Override
	@Async
	public Book findBookByIdBook(int id_book) {
		return bookReponsitory.findBookByIdBook(id_book);
	}
	@Override
	@Async
	public Book findBookByCodeBook(String codebook) {
		return bookReponsitory.findBookByCodeBook(codebook);
	}

}
