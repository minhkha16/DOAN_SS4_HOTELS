package com.demo.controllers.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Account;
import com.demo.entities.Area;
import com.demo.entities.Book;
import com.demo.entities.BookRoom;
import com.demo.entities.Evaluate;
import com.demo.entities.Hotel;
import com.demo.entities.Image;
import com.demo.entities.Room;
import com.demo.services.AreaService;
import com.demo.services.BookRoomService;
import com.demo.services.BookService;
import com.demo.services.DatePriceService;
import com.demo.services.EvaluateService;
import com.demo.services.HotelService;
import com.demo.services.ImageService;
import com.demo.services.RoomService;

@Controller
@RequestMapping("")
public class HotelController {
	@Autowired
	private AreaService areaService;
	@Autowired
	private HotelService hotelService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private DatePriceService datePriceService;
	@Autowired
	private BookService bookService;
	@Autowired
	private BookRoomService bookRoomService;
	@Autowired
	private EvaluateService evaluateService;
	@Autowired
	private ImageService imageService;

	@GetMapping(value = { "hotel" })
	private String index(ModelMap modelMap, @RequestParam("id_area") int id_area, @RequestParam("page") String page) {
		List<Hotel> hotels_db = hotelService.findHotelByArea(id_area);
		int page_use = Integer.parseInt(page);
		int check_for_list_page = 0;
		int[] list_stt_item = new int[0];
		int number_item_in_one_page = 3;
		for (int i = 1 + number_item_in_one_page * (page_use - 1); i <= number_item_in_one_page
				+ number_item_in_one_page * (page_use - 1); i++) {
			list_stt_item = Arrays.copyOf(list_stt_item, list_stt_item.length + 1);
			list_stt_item[list_stt_item.length - 1] = i;
		}
		if (hotels_db.size() % number_item_in_one_page == 0) {
			check_for_list_page = hotels_db.size() / number_item_in_one_page;
		} else {
			check_for_list_page = (hotels_db.size() / number_item_in_one_page) + 1;
		}
		int[] list_page = new int[0];
		for (int i = 1; i <= check_for_list_page; i++) {
			list_page = Arrays.copyOf(list_page, list_page.length + 1);
			list_page[list_page.length - 1] = i;
		}
		List<Hotel> hotels = new ArrayList<>();
		int a = 1;
		for (Hotel hotel : hotels_db) {
			for (int number_item : list_stt_item) {
				if (number_item == a) {
					hotels.add(hotel);
				}
			}
			a++;
		}
		modelMap.put("page_current", page_use);
		modelMap.put("list_page", list_page);
		modelMap.put("id_area", areaService.getAreaById(id_area).getId());
		modelMap.put("hotels", hotels);
		modelMap.put("full_areas", areaService.findAll());
		modelMap.put("name_area", areaService.getAreaById(id_area).getName());
		modelMap.put("case", 1);
		return "home/hotel";
	}

	@GetMapping(value = { "hotel/detail" })
	private String details(ModelMap modelMap, @RequestParam("id_hotel") int id_hotel) {
		List<Hotel> hotels = hotelService.findHotelByArea(hotelService.findHotelByIdHotel(id_hotel).getArea().getId());
		for (int i = 0; i < hotels.size(); i++) {
		    if (hotels.get(i).equals(hotelService.findHotelByIdHotel(id_hotel))) {
		        hotels.remove(i);
		        break; 
		    }
		}
		int count = 0;
		if(hotels.size()>3) {
			for (int i = hotels.size() - 1; i >= 0; i--) {
			    hotels.remove(i);
			    count++;
			    if (count == 3) {
			        break;
			    }
			}
		}
		//[{'src': '/assets/images/gal/1.jpg'}, {'src': '/assets/images/gal/2.jpg'},{'src': '/assets/images/gal/3.jpg'}]
		
		Map<Room,String> result =  new LinkedHashMap<>();
		
		for(Room room : roomService.findRoomByIdHotel(id_hotel)) {
			String start = "[";
			String end = "]";
			String ghep = "";
			List<Image> images = imageService.edit(room.getId());
			for(Image image : images ) {
				ghep +="{'src': '/images/"+image.getName()+"'},"; 
			}
			String ghep_full = start + ghep + end;
			result.put(room, ghep_full);
		}
		
		modelMap.put("id_area", areaService.getAreaById(hotelService.findHotelByIdHotel(id_hotel).getArea().getId()).getId());
		modelMap.put("hotels", hotels);
		modelMap.put("hotel", hotelService.findHotelByIdHotel(id_hotel));
		modelMap.put("rooms", result);
		modelMap.put("name_area",
				areaService.getAreaById(hotelService.findHotelByIdHotel(id_hotel).getArea().getId()).getName());
		modelMap.put("evaluates", evaluateService.getAllEvaluateByIdHotel(id_hotel));

		return "home/details";
	}

	@PostMapping(value = { "/checkRooomAvaiable" })
	@ResponseBody
	private String checkRooomAvaiable(@RequestParam("id_room") int id_room, @RequestParam("date") String date)
			throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String[] dates = date.split("-");
		Date from = simpleDateFormat.parse(dates[0].trim());
		Date to = simpleDateFormat.parse(dates[1].trim());
		List<BookRoom> bookRooms_checkAvaiable = new ArrayList<>();
		List<BookRoom> bookRooms = bookRoomService.findBookRoomCheckAvaible(id_room, from, to);
		int rooms_avaiable = roomService.findRoomByIdRoom(id_room).getFixedQuantity();
		for (BookRoom bookroom : bookRooms) {
			System.out.print("idbookroom:" + bookroom.getId());
			System.out.print("voo rooi ne");
			int quantity_date = datePriceService.findDatePriceByIdRoom(id_room).getQuantityDate(); // 2
			Date date_in_book_db_start = bookService.findBookByIdBook(bookroom.getBook().getId()).getDate(); // 23
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date_in_book_db_start);
			calendar.add(Calendar.DATE, quantity_date);
			Date date_in_book_db_end = calendar.getTime(); // 25
			if ((from.compareTo(date_in_book_db_end) < 0 && to.compareTo(date_in_book_db_end) >= 0)
					|| (from.compareTo(date_in_book_db_start) >= 0 && to.compareTo(date_in_book_db_end) <= 0)
					|| from.compareTo(date_in_book_db_start) < 0 && to.compareTo(date_in_book_db_start) > 0) {
				rooms_avaiable = rooms_avaiable - bookroom.getQuantity();
			} else {
				System.out.print("không add");
			}
		}

		HashSet<BookRoom> set = new HashSet<>(bookRooms_checkAvaiable);
		List<BookRoom> newList_bookRooms_checkAvaiable = new ArrayList<>(set);
		System.out.print("Đây: " + (rooms_avaiable - newList_bookRooms_checkAvaiable.size()));
		return String.valueOf(rooms_avaiable - newList_bookRooms_checkAvaiable.size());
	}

	@GetMapping(value = "hotels")
	public String hotels(ModelMap modelMap, @RequestParam("area") String area,
			@RequestParam("main-input-search") String date, @RequestParam("page") String page,
			RedirectAttributes redirectAttributes) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String[] dates = date.split("-");
		Date from = simpleDateFormat.parse(dates[0].trim());
		Date to = simpleDateFormat.parse(dates[1].trim());
		List<Hotel> hotels_db = hotelService.findHotelByArea(areaService.findAreaByName(area).getId());
		
		List<Hotel> hotels_use = new ArrayList<>();
		for (Hotel h : hotels_db) {
			List<Room> rooms = roomService.getListRoomByIdHotel(h.getId());
			for (Room r : rooms) {
				System.out.print("Chạy 1 lần");
				System.out.print("id_room: " + r.getId());
				if (bookRoomService.findBookRoomByIdRoom(r.getId()).isEmpty()) {
					hotels_use.add(h);
				} else {
					List<BookRoom> bookRooms = bookRoomService.findBookRoomBy(r.getId(), from, to);
					for (BookRoom bookroom : bookRooms) {
						System.out.print("idbookroom:" + bookroom.getId());
						int quantity_date = datePriceService.findDatePriceByIdRoom(r.getId()).getQuantityDate();
						Date date_in_book_db_start = bookService.findBookByIdBook(bookroom.getBook().getId()).getDate();
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date_in_book_db_start);
						calendar.add(Calendar.DATE, quantity_date);
						Date date_in_book_db_end = calendar.getTime();
						System.out.print("date_in_book_db_end: " + date_in_book_db_end);
						System.out.print("tới đây rồi");
						if ((from.compareTo(date_in_book_db_end) > 0 && to.compareTo(date_in_book_db_end) < 0)
								|| (from.compareTo(date_in_book_db_start) < 0
										&& to.compareTo(date_in_book_db_start) <= 0)
								|| (from.compareTo(date_in_book_db_end) >= 0
										&& to.compareTo(date_in_book_db_end) > 0)) {
							hotels_use.add(h);
							System.out.print("đã add");
						} else {
							System.out.print("không add");
						}
					}
				}
			}
		}
		HashSet<Hotel> set = new HashSet<>(hotels_use);
		List<Hotel> newListHotel = new ArrayList<>(set);
		int page_use = Integer.parseInt(page);
		int check_for_list_page = 0;
		int[] list_stt_item = new int[0];
		int number_item_in_one_page = 2;
		for (int i = 1 + number_item_in_one_page * (page_use - 1); i <= number_item_in_one_page
				+ number_item_in_one_page * (page_use - 1); i++) {
			list_stt_item = Arrays.copyOf(list_stt_item, list_stt_item.length + 1);
			list_stt_item[list_stt_item.length - 1] = i;
		}
		if (newListHotel.size() % number_item_in_one_page == 0) {
			check_for_list_page = newListHotel.size() / number_item_in_one_page;
		} else {
			check_for_list_page = (newListHotel.size() / number_item_in_one_page) + 1;
		}
		int[] list_page = new int[0];
		for (int i = 1; i <= check_for_list_page; i++) {
			list_page = Arrays.copyOf(list_page, list_page.length + 1);
			list_page[list_page.length - 1] = i;
		}
		List<Hotel> hotels = new ArrayList<>();
		int a = 1;
		for (Hotel hot : newListHotel) {
			for (int number_item : list_stt_item) {
				if (number_item == a) {
					hotels.add(hot);
				}
			}
			a++;
		}
		modelMap.put("page_current", page_use);
		modelMap.put("list_page", list_page);
		modelMap.put("full_areas", areaService.findAll());
		modelMap.put("hotels", hotels);
		modelMap.put("name_area", area);
		modelMap.put("id_area", areaService.findAreaByName(area).getId());
		modelMap.put("case", 2);
		modelMap.put("main_input_search", date);
		return "home/hotel";
	}

	@GetMapping(value = "searchHotel")
	public String searchHotel(ModelMap modelMap, @RequestParam("area") String area, @RequestParam("dates") String date,
			@RequestParam("hotelname") String hotelname, @RequestParam("pricerange") String pricerange,
			RedirectAttributes redirectAttributes, @RequestParam("page") String page) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String[] dates = date.split("-");
		Date from = simpleDateFormat.parse(dates[0].trim());
		Date to = simpleDateFormat.parse(dates[1].trim());
		String[] priceranges = pricerange.split(";");
		int price_from = Integer.parseInt(priceranges[0].trim());
		int price_to = Integer.parseInt(priceranges[1].trim());
		
		List<Hotel> hotels_use = new ArrayList<>();
		System.out.print("hotelname"+hotelname);
			
		if (!hotelname.equals("")) {
			System.out.print("1234");
			List<Hotel> hotels_db = hotelService.findHotelByNameAndIdArea(hotelname,
					areaService.findAreaByName(area).getId());
			for (Hotel h : hotels_db) {
				List<Room> rooms = roomService.getListRoomByIdHotel(h.getId());
				for (Room r : rooms) {
					if (bookRoomService.findBookRoomByIdRoom(r.getId()).isEmpty()) {
						int price_room = datePriceService.findDatePriceByIdRoom(r.getId()).getPrice();
						if (price_room >= price_from && price_room <= price_to) {
							hotels_use.add(h);
						}

					} else {
						List<BookRoom> bookRooms = bookRoomService.findBookRoomBy(r.getId(), from, to);
						for (BookRoom bookroom : bookRooms) {
							System.out.print("idbookroom:" + bookroom.getId());
							int price_room = datePriceService.findDatePriceByIdRoom(r.getId()).getPrice();
							if (price_room >= price_from && price_room <= price_to) {

								int quantity_date = datePriceService.findDatePriceByIdRoom(r.getId()).getQuantityDate();
								Date date_in_book_db_start = bookService.findBookByIdBook(bookroom.getBook().getId())
										.getDate();
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(date_in_book_db_start);
								calendar.add(Calendar.DATE, quantity_date);
								Date date_in_book_db_end = calendar.getTime();
								System.out.print("date_in_book_db_end: " + date_in_book_db_end);
								System.out.print("tới đây rồi");
								if ((from.compareTo(date_in_book_db_end) > 0 && to.compareTo(date_in_book_db_end) < 0)
										|| (from.compareTo(date_in_book_db_start) < 0
												&& to.compareTo(date_in_book_db_start) < 0)
										|| (from.compareTo(date_in_book_db_end) >= 0
												&& to.compareTo(date_in_book_db_end) > 0)) {
									hotels_use.add(h);
									System.out.print("đã add");
								} else {
									System.out.print("không add");
								}
							}
						}
					}
				}
			}
		} else {
			System.out.print("abcd");
			List<Hotel> hotels = hotelService.findHotelByArea(areaService.findAreaByName(area).getId());
			for (Hotel h : hotels) {
				List<Room> rooms = roomService.getListRoomByIdHotel(h.getId());
				for (Room r : rooms) {
					System.out.print("Chạy 1 lần");
					System.out.print("id_room: " + r.getId());
					if (bookRoomService.findBookRoomByIdRoom(r.getId()).isEmpty()) {
						System.out.print("Xao chìn");
						hotels_use.add(h);
					} else {
						List<BookRoom> bookRooms = bookRoomService.findBookRoomBy(r.getId(), from, to);
						for (BookRoom bookroom : bookRooms) {
							System.out.print("idbookroom:" + bookroom.getId());
							int price_room = datePriceService.findDatePriceByIdRoom(r.getId()).getPrice();
							if (price_room >= price_from && price_room <= price_to) {
								int quantity_date = datePriceService.findDatePriceByIdRoom(r.getId()).getQuantityDate();
								Date date_in_book_db_start = bookService.findBookByIdBook(bookroom.getBook().getId())
										.getDate();
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(date_in_book_db_start);
								calendar.add(Calendar.DATE, quantity_date);
								Date date_in_book_db_end = calendar.getTime();
								System.out.print("date_in_book_db_end: " + date_in_book_db_end);
								System.out.print("tới đây rồi");
								if ((from.compareTo(date_in_book_db_end) > 0 && to.compareTo(date_in_book_db_end) < 0)
										|| (from.compareTo(date_in_book_db_start) < 0
												&& to.compareTo(date_in_book_db_start) < 0)
										|| (from.compareTo(date_in_book_db_end) >= 0
												&& to.compareTo(date_in_book_db_end) > 0)) {
									hotels_use.add(h);
									System.out.print("đã add");
								} else {
									System.out.print("không add");
								}
							}

						}
					}
				}
			}
		}
		HashSet<Hotel> set = new HashSet<>(hotels_use);
		List<Hotel> newListHotel = new ArrayList<>(set);
		int page_use = Integer.parseInt(page);
		int check_for_list_page = 0;
		int[] list_stt_item = new int[0];
		int number_item_in_one_page = 2;
		for (int i = 1 + number_item_in_one_page * (page_use - 1); i <= number_item_in_one_page
				+ number_item_in_one_page * (page_use - 1); i++) {
			list_stt_item = Arrays.copyOf(list_stt_item, list_stt_item.length + 1);
			list_stt_item[list_stt_item.length - 1] = i;
		}
		if (newListHotel.size() % number_item_in_one_page == 0) {
			check_for_list_page = newListHotel.size() / number_item_in_one_page;
		} else {
			check_for_list_page = (newListHotel.size() / number_item_in_one_page) + 1;
		}
		int[] list_page = new int[0];
		for (int i = 1; i <= check_for_list_page; i++) {
			list_page = Arrays.copyOf(list_page, list_page.length + 1);
			list_page[list_page.length - 1] = i;
		}
		List<Hotel> hotels = new ArrayList<>();
		int a = 1;
		for (Hotel hot : newListHotel) {
			for (int number_item : list_stt_item) {
				if (number_item == a) {
					hotels.add(hot);
				}
			}
			a++;
		}
		modelMap.put("id_area", areaService.findAreaByName(area).getId());
		modelMap.put("page_current", page_use);
		modelMap.put("list_page", list_page);
		modelMap.put("full_areas", areaService.findAll());
		modelMap.put("hotels", hotels);
		modelMap.put("name_area", area);
		modelMap.put("dates",date);
		modelMap.put("hotelname",hotelname);
		modelMap.put("pricerange",pricerange);
		modelMap.put("case",3);
		return "home/hotel";
	}

	@PostMapping(value = { "evaluate" })
	private String evaluate(@RequestParam("rg_total") String rg_total, @RequestParam("hotel_id") String hotel_id,
			@RequestParam("content") String content, RedirectAttributes redirectAttributes) {
		Evaluate evaluate = new Evaluate();
		Hotel hotel = new Hotel();
		hotel.setId(Integer.parseInt(hotel_id));
		Account acc = new Account();
		acc.setId(4);
		evaluate.setContent(content);
		System.out.print("rg_total" + rg_total);
		String totals[] = rg_total.split("");
		String a = totals[0];
		String b = totals[1];
		System.out.print(a);
		System.out.print(b);
		evaluate.setStar(Integer.parseInt(a));
		evaluate.setStatus(false);
		evaluate.setAccount(acc);
		evaluate.setHotel(hotel);
		if (evaluateService.save(evaluate)) {
			return "redirect:hotel/detail?id_hotel=" + hotel_id + "";
		} else {
			return "redirect:hotel/detail?id_hotel=" + hotel_id + "";
		}
	}

}
