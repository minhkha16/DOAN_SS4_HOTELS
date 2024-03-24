package com.demo.controllers.owner;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.entities.Book;
import com.demo.services.AccountService;
import com.demo.services.BookRoomService;
import com.demo.services.BookService;
import com.demo.services.HotelService;
import com.demo.services.KindOfRoomService;
import com.demo.services.RoomService;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("owner/statistics")
public class StatisticOwnerController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired 
	private BookRoomService bookRoomService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private HotelService hotelService;
	
	
	@Autowired
	private KindOfRoomService kindOfRoomService;
	
	@RequestMapping(value = { "index" }, method = RequestMethod.GET)
	public String index(ModelMap modelMap, Authentication authentication) {
		modelMap.put("hotels", hotelService.findAllByIdRoleAccount(Integer.parseInt(authentication.getName()), 1));
		modelMap.put("total", bookService.sumTotalByAllHotel(true, Integer.parseInt(authentication.getName()), 1));
		return "owner/statistics/index";
	}
	
	@RequestMapping(value = { "statistic_hotel/{id}" }, method = RequestMethod.GET)
	public String room_revenue(ModelMap modelMap, @PathVariable("id") int id) {
		var st = bookRoomService.findByIdIdHotelStatistic(id);
		modelMap.put("sum", bookService.sumTotal(true, id));
		modelMap.put("bookRooms", st);
		modelMap.put("idHotel", id);
		return "owner/statistics/statistic_hotel";
	}
	
	@RequestMapping(value = { "month" }, method = RequestMethod.GET)
	public String month(ModelMap modelMap, @RequestParam("month") int month, @RequestParam("year") String year,Authentication authentication,
			@RequestParam("idHotel") int idHotel) {
		var st = bookService.findByMonth(month, Integer.parseInt(year), idHotel);
		modelMap.put("sum", bookService.sumMonth(month, Integer.parseInt(year), idHotel, true));
		modelMap.put("bookRooms", st);
		modelMap.put("idHotel", idHotel);
		return "owner/statistics/statistic_hotel";
	}
	
	@RequestMapping(value = { "chart" }, method = RequestMethod.GET)
	public String chart(ModelMap modelMap) {
		
		return "owner/statistics/chart";
	}
	
	@GetMapping(value = { "filterByDate" })
	private String filterByDate(@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "to", required = false) String to, ModelMap model, @RequestParam("idHotel") int idHotel) {
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
			
			model.put("sum", bookService.takeByDate2(f,t, idHotel,true));
			model.put("bookRooms", bookService.takeByDateByIdHotel(f,t, idHotel));
			model.put("idHotel", idHotel);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "owner/statistics/statistic_hotel";
	}
}
