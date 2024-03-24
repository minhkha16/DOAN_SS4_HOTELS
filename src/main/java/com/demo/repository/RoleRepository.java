package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Account;
import com.demo.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

	@Query("from Role order by id desc")
	public List<Role> findAllbyIdDesc();
}
