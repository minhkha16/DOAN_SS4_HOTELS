package com.demo.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.demo.entities.Account;


public interface AccountService extends UserDetailsService{
	public List<Account> listHotel();
	public List<Account> searchByEmailHotel(String email);
	public List<Account> searchByEmailUser(String email);
	public List<Account> listUser();
	public boolean delete (int id);
	public boolean save(Account account);
	public Account findByIdAccount(int id);
	public Iterable<Account> findAll();
	public Long countAccountUser();
	public Long countAccountHotel();
	
	
	////// Thái 
	public Account getAccountByIdAccount(int id_account);

}
