package com.demo.controllers.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.entities.Account;
import com.demo.entities.Area;

import com.demo.services.AccountService;
import com.demo.services.BookService;
import com.demo.services.EvaluateService;
import com.demo.services.HotelService;
import com.demo.services.RoleService;

import jakarta.persistence.Access;
@Controller
@RequestMapping({"admin/evaluete"})
public class EvalueteAdminController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private EvaluateService evaluateService;
	
	
	
	@RequestMapping(value = {"index" }, method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("evaluetes", evaluateService.listAll());
		return "admin/evaluete/index";
	}
	@RequestMapping(value = {"edit/{id}" }, method = RequestMethod.GET)
	public String edit(ModelMap modelMap, @PathVariable("id")int id) {
		var eva = evaluateService.findById(id);
		eva.setStatus(true);
		evaluateService.save(eva);
		return "redirect:/admin/evaluete/index";
	}
	
}
