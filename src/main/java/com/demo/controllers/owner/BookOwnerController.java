package com.demo.controllers.owner;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Account;
import com.demo.entities.Book;
import com.demo.services.AccountService;
import com.demo.services.BookRoomService;
import com.demo.services.BookService;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("owner/book")
public class BookOwnerController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired 
	private BookRoomService bookRoomService;
	
	@RequestMapping(value = { "index" }, method = RequestMethod.GET)
	public String index(ModelMap modelMap,Authentication authentication) {
		modelMap.put("books", bookService.findAllByIdAccount(Integer.parseInt(authentication.getName())));
		return "owner/book/index";
	}
	
	@GetMapping(value = { "filterByDate" })
	private String filterByDate(@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "to", required = false) String to, ModelMap model, @RequestParam("idRoom") int id) {

			try {
				SimpleDateFormat simp = new SimpleDateFormat("dd-MM-yyyy");
				if (from == null || from == "") {
					from = simp.format(new Date());
				}
				if (to == null || to == "") {
					to = simp.format(new Date());
				}
				Date f = simp.parse(from);
				Date t = simp.parse(to);

				model.put("from", f);

				model.put("to", t);
				model.put("bookRooms", bookService.takeByDate3(f,t,id));
				model.put("idRoom", id);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		
		return "owner/room/details_Book";
	}
	
	@RequestMapping(value = {"findByCode" }, method = RequestMethod.GET)
	private String findByName(ModelMap model, @RequestParam("code") String code,Authentication authentication, @RequestParam("idRoom") int id) {
		model.put("bookRooms", bookService.findByCodeInBook(code, id));
		model.put("idRoom", id);
		return "owner/room/details_Book";
	}
}
