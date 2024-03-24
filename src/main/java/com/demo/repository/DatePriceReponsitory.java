package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Account;
import com.demo.entities.Area;
import com.demo.entities.Category;
import com.demo.entities.DatePrice;
import com.demo.entities.Hotel;
import com.demo.entities.Room;


@Repository
public interface DatePriceReponsitory extends CrudRepository<DatePrice, Integer> {
	@Query("from DatePrice where room.id = :idRoom")
	public DatePrice editDatePrice(@Param("idRoom") int idRoom);
	@Query("from DatePrice where price <= :price2 and price>= :price1")
	public List<DatePrice> searchByPrice(@Param("price1") double price1, @Param("price2") double price2);
	@Query("from DatePrice where quantityDate <= :date2 and quantityDate>= :date1")
	public List<DatePrice> searchBydate(@Param("date1") int date1, @Param("date2") int date2);
	@Query("from DatePrice order by id desc")
	public List<DatePrice> findbydesc();
	
	// THái
	@Query("from DatePrice where room.id = :id_room and quantityDate= :quantityDate")
	public DatePrice findDatePriceByIdRoomAndQuantityday(@Param("id_room") int id_room,@Param("quantityDate") int quantityDate);
	@Query("from DatePrice where room.id = :id_room and quantityDate= 1")
	public DatePrice findDatePriceByIdRoom(@Param("id_room") int id_room);


}
