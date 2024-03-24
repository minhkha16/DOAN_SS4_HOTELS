package com.demo.controllers.user;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.entities.Area;
import com.demo.entities.Hotel;
import com.demo.services.AreaService;
import com.demo.services.HotelService;
import com.demo.services.RoomService;


@Controller
@RequestMapping("")
public class HomeController {
	@Autowired
	private AreaService areaService;
	@Autowired
	private HotelService hotelService;
	@Autowired
	private RoomService roomService;
	@GetMapping(value = { "home", "/", "" })
	private String index(ModelMap modelMap) {
		
		Map<Area,Integer> areas =  new LinkedHashMap<>();
		for (Area area : areaService.findTop6Area()) {
			areas.put(area, areaService.quantityHotelForArea(area.getId())) ;
		}
//		for (Map.Entry<Area, Integer> entry : areas.entrySet()) {
//		    System.out.println("Key: " + entry.getKey().getId() + ", Value: " + entry.getValue());
//		}
		modelMap.put("areas", areas);
		modelMap.put("full_areas", areaService.findAll());
		modelMap.put("hotels", hotelService.findAllHome());
		
		
		return "home/index";
	}
	
	@GetMapping(value = { "payment"})
	private String payment() {
		return "home/payment";
	}
	
	@GetMapping(value = { "contact"})
	private String contact() {
		return "home/contact";
	}
	
	@GetMapping(value = { "news"})
	private String news() {
		return "home/news";
	}
	
	@GetMapping(value = { "details"})
	private String details() {
		return "home/details";
	}
	
	@GetMapping(value = { "addHotel"})
	private String add_hotel() {
		return "home/addHotel";
	}
	
	@GetMapping(value = { "invoice"})
	private String invoice() {
		return "home/invoice";
	}
	
	@GetMapping(value = { "404"})
	private String eroor() {
		return "home/404";
	}
}
