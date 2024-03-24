package com.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.entities.BookRoom;

@Repository
public interface BookRoomReponsitory extends CrudRepository<BookRoom, Integer> {
	@Query("from BookRoom where book.id = :id")
	public List<BookRoom> findByIdBookRoom(@Param("id") int id);
	
	@Query("select IFNULL(sum(book.total), 0) from BookRoom where datePrice.room.hotel.id = :idHotel")
	public double findSumHotel(@PathVariable("idHotel") int idHotel);

	@Query("from BookRoom where datePrice.room.hotel.id = :id")
	public List<BookRoom> findByIdHotel(@Param("id") int id);
	
	@Query("select IFNULL(sum(book.total), 0) from BookRoom where month(book.created) = :mth and year(book.created) = :year")
	public long findByMonth(@PathVariable("mth") int mth, @PathVariable("year") int year);
	
	@Query("from BookRoom where month(book.created) = :mth and book.status = :status and year(book.created) = :year")
	public List<BookRoom> findByMonthList(@PathVariable("mth") int mth, @Param("status") boolean status, @PathVariable("year") int year);
	
	@Query("from BookRoom where datePrice.room.hotel.id = :id")
	public List<BookRoom> findByIdHotelStatistic(@Param("id") int id);
	
	@Query("from BookRoom where datePrice.room.id = :id")
	public List<BookRoom> detailsBookByRoom(@Param("id") int id);
	
	// Thái
	@Query("from BookRoom where (datePrice.room.id = :id_room and book.date < :from) or (datePrice.room.id = :id_room and book.date > :to)")
	public List<BookRoom> findBookRoomBy(@Param("id_room") int id_room,@Param("from") Date from, @Param("to") Date to);
	@Query("from BookRoom where datePrice.room.id =:id_room")
	public List<BookRoom> findBookRoomByIdRoom(@Param("id_room") int id_room);
	@Query("from BookRoom where (datePrice.room.id = :id_room and book.date >= :from) or (datePrice.room.id = :id_room and book.date <= :to)")
	public List<BookRoom> findBookRoomCheckAvaible(@Param("id_room") int id_room,@Param("from") Date from, @Param("to") Date to);
	@Query("from BookRoom where book.account.id =:id_account and book.status = true order by id desc")
	public List<BookRoom> findBookRoomByIdAccount(@Param("id_account") int id_account);
	@Query("select IFNULL(count(id),0) from BookRoom where book.account.id =:id_account")
	public int countBookRoomByIdAccount(@Param("id_account") int id_account);
	@Query("from BookRoom where datePrice.id =:id_dateprice and book.id = :id_book")
	public BookRoom findBookRoomByIdDatePriceAndIdBook(@Param("id_dateprice") int id_dateprice,@Param("id_book") int id_book);

}
