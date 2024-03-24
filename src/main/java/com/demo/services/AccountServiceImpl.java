package com.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.entities.Account;
import com.demo.entities.Area;
import com.demo.entities.Category;
import com.demo.entities.Role;
import com.demo.repository.AccountRepository;
import com.demo.repository.AreaRepository;
import com.demo.repository.CategoryRepository;
import com.demo.repository.RoleRepository;

@Service("accountService")
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		if(account == null) {
			throw new UsernameNotFoundException("username not found");
		} else {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			
				authorities.add(new SimpleGrantedAuthority(account.getRole().getName()));
			
			return new User(account.getId().toString(), account.getPassword(), authorities);
		}
		
		
	}


	@Override
	public boolean delete(int id) {
		try {
			accountRepository.delete(accountRepository.findById(id).get());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean save(Account acount) {
		try {
			accountRepository.save(acount);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Account findByIdAccount(int id) {
		// TODO Auto-generated method stub
		return accountRepository.findById(id).get();
	}

	@Override
	public List<Account> listHotel() {
		// TODO Auto-generated method stub
		return accountRepository.findAllbyIdDesc("ROLE_HOTEL");
	}
	@Override
	public List<Account> listUser() {
		// TODO Auto-generated method stub
		return accountRepository.findAllbyIdDesc("ROLE_USER");
	}


	@Override
	public Iterable<Account> findAll() {
		return accountRepository.findAll();
	}


	@Override
	public List<Account> searchByEmailHotel(String email) {
		// TODO Auto-generated method stub
		return accountRepository.findAllbyIdDescByEmail("ROLE_HOTEL", email);
	}


	@Override
	public List<Account> searchByEmailUser(String email) {
		// TODO Auto-generated method stub
		return accountRepository.findAllbyIdDescByEmail("ROLE_USER", email);
	}


	@Override
	public Long countAccountUser() {
		// TODO Auto-generated method stub
		return accountRepository.countAccount("ROLE_USER");
	}


	@Override
	public Long countAccountHotel() {
		// TODO Auto-generated method stub
		return accountRepository.countAccount("ROLE_HOTEL");
	}
	/// Thái
	@Override
	@Async
	public Account getAccountByIdAccount(int id_account){
		return accountRepository.getAccountByIdAccount(id_account);
	}


}
