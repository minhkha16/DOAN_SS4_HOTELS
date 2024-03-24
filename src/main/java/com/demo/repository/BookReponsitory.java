package com.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.entities.Book;
import com.demo.entities.BookRoom;
import com.demo.entities.Hotel;

@Repository
public interface BookReponsitory extends CrudRepository<Book, Integer> {
	
	@Query("from Book order by id desc")
	public List<Book> listAll();
	@Query("select count(id) from Book")
	public Long countbooking();
	
	@Query("select IFNULL(sum(book.total), 0) from BookRoom where book.status = :status and datePrice.room.hotel.id = :idHotel")
	public double findByTotal(@Param("status") boolean status, @Param("idHotel") int idHotel);
	
	@Query("select IFNULL(sum(book.total), 0) from BookRoom where book.status = :status and datePrice.room.hotel.account.id = :idAcc and datePrice.room.hotel.status = :sttHotel")
	public double sumTotalByAllHotel(@Param("status") boolean status, @Param("idAcc") int idAcc, @Param("sttHotel") int sttHotel);
	
	@Query("from BookRoom where book.created >= :from and book.created <= :to")
	public List<BookRoom> takeByDate(@Param("from") Date from, @Param("to") Date to);
	
	@Query("from Book where account.role.id = :id")
	public List<Book> findAllByIdRoleAccount(@Param("id") int id);
	
	@Query("select IFNULL(sum(book.total), 0) from BookRoom where book.created >= :from and book.created <= :to and datePrice.room.hotel.id = :idHotel and book.status = :status")
	public double takeByDate2(@Param("from") Date from, @Param("to") Date to, @Param("idHotel") int idHotel, @Param("status") boolean status);
	
	@Query("from BookRoom where book.created >= :from and book.created <= :to and datePrice.room.id = :idRoom")
	public List<BookRoom> takeByDate3(@Param("from") Date from, @Param("to") Date to, @Param("idRoom") int idRoom);
	
	@Query("from BookRoom where book.created >= :from and book.created <= :to and datePrice.room.hotel.id = :idHotel")
	public List<BookRoom> takeByDateByIdHotel(@Param("from") Date from, @Param("to") Date to, @Param("idHotel") int idHotel);
	
	@Query("from Book where id = :id")
	public Book takeById(@Param("id") int id);
	
	@Query("from Book where codeBook like %:keyword%")
	public List<Book> findByCode(@Param("keyword") String code);
	
	@Query("from BookRoom where book.codeBook like %:keyword% and datePrice.room.id = :idRoom")
	public List<BookRoom> findByCodeInBook(@Param("keyword") String code, @Param("idRoom") int idRoom);
	
	@Query("from Book where status = :status")
	public List<Book> findByStatus(@Param("status") boolean status);
	
	@Query("select IFNULL(sum(book.total), 0) from BookRoom where month(book.created) = :mth and year(book.created) = :year and datePrice.room.hotel.id = :idHotel and book.status = :status")
	public long findByMonth(@PathVariable("mth") int mth, @PathVariable("year") int year, @Param("idHotel") int idHotel, @Param("status") boolean status);
	
	@Query("from BookRoom where month(book.created) = :mth and year(book.created) = :year and datePrice.room.hotel.id = :idHotel")
	public List<BookRoom> findByMonthList(@PathVariable("mth") int mth, @PathVariable("year") int year, @Param("idHotel") int idHotel);
	
	@Query("from Book where account.id = :id")
	public List<Book> findAllByIdAccount(@Param("id") int id);
	// Thái 
	@Query("from Book where date <  :from or date > :to")
	public List<Book> findBookByDate(@Param("from") Date from, @Param("to") Date to);
	@Query("from Book where id =:id_book")
	public Book findBookByIdBook(@Param("id_book") int id_book);
	@Query("from Book where UPPER(codeBook) = UPPER(:codebook)")
	public Book findBookByCodeBook(@Param("codebook") String codebook);

}
