package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Account;
import com.demo.entities.Area;
import com.demo.entities.Category;
import com.demo.entities.Evaluate;
import com.demo.entities.Hotel;

@Repository
public interface EvalueteReponsitory extends CrudRepository<Evaluate, Integer> {
	@Query("from Evaluate where hotel.id = :id_hotel and status = true")
	public List<Evaluate> getAllEvaluateByIdHotel(@Param("id_hotel") int id_hotel);
	@Query("select IFNULL(count(id),0) from Evaluate where account.id =:id_account")
	public int countEvaluateByIdAccount(@Param("id_account") int id_account);
	
	@Query("from Evaluate where hotel.status = :status and hotel.account.id = :idAcc order by id desc ")
	public List<Evaluate> findAllByIdDesc(int status, int idAcc);
	
	@Query("from Evaluate order by id desc")
	public List<Evaluate> listAll();
}
