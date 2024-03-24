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
public interface AccountRepository extends CrudRepository<Account, Integer> {

	@Query("from Account where role.name = :role order by id desc")
	public List<Account> findAllbyIdDesc(@Param("role") String role);

	@Query("from Account where username = :username")
	public Account findByUsername(@Param("username") String username);

	@Query("from Account where role.name = :role and email like %:email%")
	public List<Account> findAllbyIdDescByEmail(@Param("role") String role, @Param("email") String email);

	@Query("select count(id) from Account where role.name = :role")
	public Long countAccount(@Param("role") String role);

	///// Tháiiii
	@Query("from Account where id =:id_account")
	public Account getAccountByIdAccount(@Param("id_account") int id_account);
	

}
