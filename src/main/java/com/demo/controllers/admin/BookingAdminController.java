package com.demo.controllers.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Category;
import com.demo.entities.Role;
import com.demo.services.BookRoomService;
import com.demo.services.BookService;
import com.demo.services.CategoryService;
@Controller
@RequestMapping("admin/booking")
public class BookingAdminController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BookService bookService;
	@Autowired
	private BookRoomService bookRoomService;
	
	@RequestMapping(value = { "", "index" }, method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		
		modelMap.put("bookings", bookService.listAll());
		
		return "admin/booking/index";
	}
	@RequestMapping(value = { "index" }, method = RequestMethod.POST)
	public String index(ModelMap modelMap, @RequestParam("code") String code) {
		
		modelMap.put("bookings", bookService.findByCode(code));
		
		return "admin/booking/index";
	}
	@RequestMapping(value = { "detail/{id}" }, method = RequestMethod.GET)
	public String detail(ModelMap modelMap, @PathVariable("id") int id) {
		
		modelMap.put("bookingRooms", bookRoomService.findByIdBookRoom(id));
		
		return "admin/booking/detail";
	}
	
	
	
}
