package com.demo.controllers.owner;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Book;
import com.demo.entities.BookRoom;
import com.demo.services.AccountService;
import com.demo.services.BookRoomService;
import com.demo.services.BookService;
import com.demo.services.HotelService;
@Controller
@RequestMapping("owner/book_room")
public class BookRoomOwnerController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private BookRoomService bookroomService;
	
	@Autowired
	private AccountService hotelService;
	
	@RequestMapping(value = {"details/{id}" }, method = RequestMethod.GET)
	private String details(ModelMap model, @PathVariable int id) {
		model.put("bookrooms", bookroomService.findByIdBookRoom(id));
		return "owner/book/details";
	}
	
	@RequestMapping(value = {"detailsBookByRoom/{id}" }, method = RequestMethod.GET)
	private String detailsBookByHotel(ModelMap model, @PathVariable int id) {
		model.put("bookRooms", bookroomService.detailsBookByRoom(id));
		model.put("idRoom", id);
		return "owner/room/details_Book";
	}
}
