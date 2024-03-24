package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Role;
import com.demo.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Role> listAll() {
		
		return roleRepository.findAllbyIdDesc();
	}

	@Override
	public boolean delete(int id) {
		try {
			roleRepository.delete(roleRepository.findById(id).get());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean save(Role role) {
		try {
			roleRepository.save(role);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Role findByIdRole(int id) {
		// TODO Auto-generated method stub
		return roleRepository.findById(id).get();
	}

}
