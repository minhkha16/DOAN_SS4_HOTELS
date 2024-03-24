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
import com.demo.services.HotelService;
import com.demo.services.RoleService;

import jakarta.persistence.Access;
@Controller
@RequestMapping({"admin/chart"})
public class ChartsAdminController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private HotelService hotelService;
	
	
	
	@RequestMapping(value = {"index" }, method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("booking", bookService.countBooking());
		modelMap.put("account_user", accountService.countAccountUser());
		modelMap.put("account_hotel", accountService.countAccountHotel());
		modelMap.put("hotel", hotelService.countHotel());
		return "admin/charts/index";
	}
	
}
