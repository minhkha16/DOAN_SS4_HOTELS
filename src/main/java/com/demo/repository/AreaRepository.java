package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Account;
import com.demo.entities.Area;
import com.demo.entities.Category;
import com.demo.entities.Role;

@Repository
public interface AreaRepository extends CrudRepository<Area, Integer> {

	@Query("from Area order by id desc")
	public List<Area> findAllbyIdDesc();
	
	
	/// Thái
	@Query("from Area order by id asc limit 5")
	public List<Area> findTop6Area();
	@Query("from Area where id =:id")
	public Area getAreaById(@Param("id") int id);
	
	@Query("select count(area.id) from Hotel where area.id = :id")
	public int quantityHotelForArea(@Param("id") int id);
	@Query("from Area where name like %:keyword%")
	public Area search (@Param("keyword") String keyword);

}
