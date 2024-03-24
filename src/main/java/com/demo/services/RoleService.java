package com.demo.services;

import java.util.List;

import com.demo.entities.Role;

public interface RoleService {
	public List<Role> listAll();
	public boolean delete (int id);
	public boolean save(Role role);
	public Role findByIdRole(int id);
}
