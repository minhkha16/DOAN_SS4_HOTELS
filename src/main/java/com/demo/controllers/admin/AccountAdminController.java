package com.demo.controllers.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Account;
import com.demo.entities.Area;

import com.demo.services.AccountService;
import com.demo.services.RoleService;
import com.demo.validators.AccountValidator;

import jakarta.validation.Valid;
@Controller
@RequestMapping({"admin/account"})
public class AccountAdminController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private AccountValidator accountValidator;
	
	@RequestMapping(value = {"listhotel" }, method = RequestMethod.GET)
	public String listhotel(ModelMap modelMap) {
		modelMap.put("accounts", accountService.listHotel());
		modelMap.put("check", 1);
		return "admin/account/listaccount";
	}
	@RequestMapping(value = {"listhotel" }, method = RequestMethod.POST)
	public String listhotel(ModelMap modelMap, @RequestParam("email") String email) {
		modelMap.put("accounts", accountService.searchByEmailHotel(email));
		modelMap.put("check", 1);
		return "admin/account/listaccount";
	}
	
	@RequestMapping(value = {"login" }, method = RequestMethod.GET)
	public String login(ModelMap modelMap) {
		return "admin/account/login";
	}
	@RequestMapping(value = {"listuser" }, method = RequestMethod.GET)
	public String listuser(ModelMap modelMap) {
		modelMap.put("check", 2);
		modelMap.put("accounts", accountService.listUser());
		return "admin/account/listaccount";
	}
	
	@RequestMapping(value = {"listuser" }, method = RequestMethod.POST)
	public String listuser(ModelMap modelMap, @RequestParam("email") String email) {
		modelMap.put("accounts", accountService.searchByEmailUser(email));
		modelMap.put("check", 2);
		return "admin/account/listaccount";
	}
	
	@RequestMapping(value = {"created" }, method = RequestMethod.GET)
	public String created(ModelMap modelMap) {
		modelMap.put("account", new Account());
		modelMap.put("roles", roleService.listAll());
		return "admin/account/created";
	}
	
	@RequestMapping(value = {"edit/{id}" }, method = RequestMethod.GET)
	public String edit(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.put("account", accountService.findByIdAccount(id));
		modelMap.put("roles", roleService.listAll());
		return "admin/account/edit";
	}
	@RequestMapping(value = {"created" }, method = RequestMethod.POST)
	public String created(ModelMap modelMap, @ModelAttribute("account") @Valid Account account, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		accountValidator.validate(account, bindingResult);	
		
		if (bindingResult.hasErrors()) {
			modelMap.put("roles", roleService.listAll());
			return "admin/account/created";
		} 
		account.setCreated(new Date());
		account.setPassword(encoder.encode(account.getPassword()));
		account.setStatus(1);
		if(accountService.save(account)) {
			redirectAttributes.addFlashAttribute("msg",1);
			return "redirect:/admin/account/created";
		}
		redirectAttributes.addFlashAttribute("msg",2);
		return "redirect:/admin/account/created";
	}
	
	@RequestMapping(value = {"edit" }, method = RequestMethod.POST)
	public String edit(ModelMap modelMap, @ModelAttribute("account")Account account) {
		account.setUpdateted(new Date());
		account.setStatus(1);
		if(!account.getPassword().equals("")) {
			account.setPassword(encoder.encode(account.getPassword()));
			
		}else {
			var pas = accountService.findByIdAccount(account.getId());
			account.setPassword(pas.getPassword());
		}
		if(accountService.save(account)) {
			
		}
		
		
		return "redirect:/admin/account/created";
	}
}
