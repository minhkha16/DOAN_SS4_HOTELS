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
import com.demo.entities.Image;

@Repository
public interface ImageReponsitory extends CrudRepository<Image, Integer> {
	@Query("from Image where room.id = :idRoom")
	public List<Image> editImage(@Param("idRoom") int idRoom);
	@Query("select IFNULL(count(id),0) from Image where room.id = :idRoom")
	public int countImageByIdRoom(@Param("idRoom") int idRoom);
	@Query("from Image where room.id = :idRoom order by id asc limit 1")
	public Image find1ImageByIdRoom(@Param("idRoom") int idRoom);
}
