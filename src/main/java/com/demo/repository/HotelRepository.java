package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.entities.Account;
import com.demo.entities.Area;
import com.demo.entities.Category;
import com.demo.entities.Hotel;
import com.demo.entities.Role;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Integer> {

	@Query("from Hotel order by account.id desc")
	public List<Hotel> findAllbyIdDesc();
	@Query("from Hotel where name like %:keyword% order by account.id desc")
	public List<Hotel> search(@Param("keyword") String keyword);
	
	//owner
	@Query("from Hotel where name like %:keyword%")
	public List<Hotel> findByName(@Param("keyword") String name);
	
	@Query("select IFNULL(sum(book.total),0) from BookRoom where datePrice.room.hotel.id = :idHotel and book.status = true")
	public double findSumHotel(@PathVariable("idHotel") int idHotel);
	
	@Query("select IFNULL(sum(sum(book.total)),0) from BookRoom where datePrice.room.hotel.id = :idHotel")
	public int totalByHotel(@PathVariable("idHotel") int idHotel);
	
	@Query("from Hotel where account.id = :id and status = :status")
	public List<Hotel> findAllAccount(@Param("id") int id, @Param("status") int status);
	
	@Query("select count(id) from Hotel where status =1")
	public Long countHotel();
	
	@Query("select IFNULL(count(book.id),0) from BookRoom where datePrice.room.hotel.id = :idHotel and book.status = true")
	public int countPaid(@PathVariable("idHotel") int idHotel);
	
	@Query("select IFNULL(count(book.id),0) from BookRoom where datePrice.room.hotel.id = :idHotel and book.status = false")
	public int countCancel(@PathVariable("idHotel") int idHotel);
	
	// Thái
	@Query("from Hotel where area.id = :id_area and status = 1")
	public List<Hotel> findHotelByArea(@Param("id_area") int id_area);
	@Query("from Hotel where where id = :id_hotel and status = 1")
	public Hotel findHotelByIdHotel(@Param("id_hotel") int id_hotel);
	@Query("from Hotel where where name like %:name% and area.id = :id_area and status = 1")
	public List<Hotel> findHotelByNameAndIdArea(@Param("name") String name,@Param("id_area") int id_area);
	@Query("select IFNULL(avg(star),0.0) from Evaluate where hotel.id = :id_hotel and hotel.status = 1 and status=true")
	public double getAverageStarHotel(@Param("id_hotel") int id_hotel);
	@Query("select IFNULL(count(id),0.0) from Evaluate where hotel.id = :id_hotel and hotel.status = 1 and status=true")
	public int getCountEvaluateHotel(@Param("id_hotel") int id_hotel);
	@Query("from Hotel")
	public List<Hotel> findAllHome();

}
