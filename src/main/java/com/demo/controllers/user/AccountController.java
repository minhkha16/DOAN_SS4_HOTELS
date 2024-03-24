package com.demo.controllers.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Account;
import com.demo.entities.Area;
import com.demo.entities.Book;
import com.demo.entities.BookRoom;
import com.demo.entities.Hotel;
import com.demo.entities.Role;
import com.demo.entities.Room;
import com.demo.paypal.PayPalConfig;
import com.demo.paypal.PayPalResult;
import com.demo.paypal.PayPalSucess;
import com.demo.services.AccountService;
import com.demo.services.BookRoomService;
import com.demo.services.BookService;
import com.demo.services.DatePriceService;
import com.demo.services.HotelService;
import com.demo.services.MailService;
import com.demo.services.RoomService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("account")
public class AccountController {
	@Autowired
	private Environment environment;
	@Autowired
	private RoomService roomService;
	@Autowired
	private HotelService hotelService;
	@Autowired
	private DatePriceService datePriceService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BookRoomService bookRoomService;
	@Autowired
	private BookService bookService;
	@Autowired
	private MailService mailService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@RequestMapping(value = { "register" }, method = RequestMethod.POST)
	public String created(ModelMap modelMap, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("address") String address,
			@RequestParam("username") String username, @RequestParam("fullname") String fullanme,
			@RequestParam("birthday") String birthday) {
		Account account = new Account();
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date birth = simpleDateFormat.parse(birthday);
			account.setBirthday(birth);
		} catch (Exception e) {
			// TODO: handle exception
		}

		account.setAddress(address);

		account.setEmail(email);
		account.setFullname(fullanme);
		account.setUsername(username);
		account.setCreated(new Date());
		account.setPassword(encoder.encode(password));
		account.setStatus(1);
		Role role = new Role();
		role.setId(3);
		account.setRole(role);

		if (accountService.save(account)) {
			return "redirect:/home";
		}
		return "redirect:/home";
	}

	@GetMapping(value = { "booking" })
	private String booking(ModelMap modelMap, @RequestParam("page") String page, Authentication authentication) {
		Map<BookRoom, Integer> bookrooms_db = new LinkedHashMap<>();
		List<BookRoom> bookrooms_dbs = bookRoomService
				.findBookRoomByIdAccount(Integer.parseInt(authentication.getName())); // id_account
		int page_use = Integer.parseInt(page);
		int check_for_list_page = 0;
		int[] list_stt_item = new int[0];
		int number_item_in_one_page = 3;
		for (int i = 1 + number_item_in_one_page * (page_use - 1); i <= number_item_in_one_page
				+ number_item_in_one_page * (page_use - 1); i++) {
			list_stt_item = Arrays.copyOf(list_stt_item, list_stt_item.length + 1);
			list_stt_item[list_stt_item.length - 1] = i;
		}
		if (bookrooms_dbs.size() % number_item_in_one_page == 0) {
			check_for_list_page = bookrooms_dbs.size() / number_item_in_one_page;
		} else {
			check_for_list_page = (bookrooms_dbs.size() / number_item_in_one_page) + 1;
		}
		int[] list_page = new int[0];
		for (int i = 1; i <= check_for_list_page; i++) {
			list_page = Arrays.copyOf(list_page, list_page.length + 1);
			list_page[list_page.length - 1] = i;
		}
		int a = 1;
		for (BookRoom bs : bookrooms_dbs) {
			bookrooms_db.put(bs, a);
			a++;
		}
		Map<BookRoom, Integer> bookrooms = new LinkedHashMap<>();
		for (Map.Entry<BookRoom, Integer> entry : bookrooms_db.entrySet()) {
			for (int number_item : list_stt_item) {
				if (number_item == entry.getValue()) {
					bookrooms.put(entry.getKey(), entry.getValue());
				}
			}
		}
		modelMap.put("account", accountService.getAccountByIdAccount(Integer.parseInt(authentication.getName())));
		modelMap.put("bookrooms", bookrooms);
		modelMap.put("list_page", list_page);
		modelMap.put("page_current", page_use);
		return "account/booking";
	}

	@GetMapping(value = { "profile" })
	private String profile(ModelMap modelMap, Authentication authentication) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Account account = accountService.getAccountByIdAccount(Integer.parseInt(authentication.getName()));
		modelMap.put("account", account);
		return "account/profile";
	}

	@PostMapping(value = { "update" })
	private String update(ModelMap modelMap, @RequestParam("fullname") String fullname,
			@RequestParam("birthday") String birthday, @RequestParam("address") String address) throws ParseException {
		Account account = accountService.getAccountByIdAccount(4);
		account.setFullname(fullname);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		account.setBirthday(formatter.parse(birthday));
		account.setAddress(address);
		if (accountService.save(account)) {
			return "redirect:/account/profile";
		}
		return "redirect:/account/profile";
	}

	@GetMapping(value = { "bookings" })
	private String bookings(ModelMap modelMap, HttpSession session, Authentication authentication) {
		modelMap.put("account", accountService.findByIdAccount(Integer.parseInt(authentication.getName())));

		modelMap.put("check_pay", session.getAttribute("check_pay"));
		modelMap.put("price", session.getAttribute("price"));
		modelMap.put("room", session.getAttribute("room"));
		modelMap.put("from", session.getAttribute("from"));
		modelMap.put("to", session.getAttribute("to"));
		modelMap.put("quantityday", session.getAttribute("quantityday"));
		modelMap.put("quantity_room", session.getAttribute("quantity_room"));
		modelMap.put("grand_total", session.getAttribute("grand_total"));
		modelMap.put("posturl", environment.getProperty("paypal.posturl"));
		modelMap.put("returnurl", environment.getProperty("paypal.returnurl"));
		modelMap.put("business", environment.getProperty("paypal.business"));
		modelMap.put("id_bookroom", session.getAttribute("id_bookroom"));
		return "account/payment";
	}

	@PostMapping(value = { "saveinfo" })
	@ResponseBody
	private String saveinfo(HttpSession session, @RequestParam("fullnamesave") String fullnamesave,
			@RequestParam("cccdsave") String cccdsave, @RequestParam("emailsave") String emailsave,
			@RequestParam("phonesave") String phonesave) {
		session.removeAttribute("fullnamesave");
		session.removeAttribute("cccd");
		session.removeAttribute("emailsave");
		session.removeAttribute("phonesave");

		session.setAttribute("fullnamesave", fullnamesave);
		session.setAttribute("cccdsave", cccdsave);
		session.setAttribute("emailsave", emailsave);
		session.setAttribute("phonesave", phonesave);

		System.out.print("fullnamesave:" + fullnamesave);
		System.out.print("cccdsave:" + cccdsave);
		System.out.print("emailsave:" + emailsave);
		System.out.print("phonesave:" + phonesave);
		return "1";
	}

	@PostMapping(value = { "bookings" })
	private String book(ModelMap modelMap, RedirectAttributes redirectAttributes, @RequestParam("report") String report,
			@RequestParam("bookdates") String bookdates, @RequestParam("quantity_room") int quantity_room,
			@RequestParam("grand_total") String grand_total, @RequestParam("quantityday") int quantityday,
			HttpSession session) {
		System.out.print("report: " + report);
		System.out.print("bookdates: " + bookdates);
		System.out.print("quantity_room: " + quantity_room);
		System.out.print("grand_total: " + grand_total);
		////////////// infor book
		String[] id_price = report.split("-");
		int id_room = Integer.parseInt(id_price[0]);
		String price = id_price[1];
		String[] dates = bookdates.split("-");
		String from = dates[0];
		String to = dates[1];
		Room room = roomService.findRoomByIdRoom(id_room);
		Hotel hotel = hotelService.findHotelByIdHotel(room.getHotel().getId());
		session.removeAttribute("typeroom");
		session.removeAttribute("price");
		session.removeAttribute("room");
		session.removeAttribute("from");
		session.removeAttribute("to");
		session.removeAttribute("quantityday");
		session.removeAttribute("quantity_room");
		session.removeAttribute("grand_total");
		session.removeAttribute("check_pay");
		session.removeAttribute("codebook");
		session.removeAttribute("id_dateprice");
		session.setAttribute("price", price);
		session.setAttribute("typeroom", room.getContent());
		session.setAttribute("room", roomService.findRoomByIdRoom(id_room));
		session.setAttribute("from", from);
		session.setAttribute("to", to);
		session.setAttribute("quantityday", quantityday);
		session.setAttribute("quantity_room", quantity_room);
		session.setAttribute("grand_total", grand_total);
		session.setAttribute("check_pay", 0);
		return "redirect:/account/bookings";
	}

	@GetMapping(value = { "success" })
	public String success(ModelMap modelMap, @RequestParam("PayerID") String payerID, HttpServletRequest request,
			HttpSession session, RedirectAttributes redirectAttributes, Authentication authentication)
			throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		PayPalConfig payPalConfig = new PayPalConfig();
		payPalConfig.setAuthToken(environment.getProperty("paypal.authtoken"));
		payPalConfig.setBusiness(environment.getProperty("paypal.business"));
		payPalConfig.setPosturl(environment.getProperty("paypal.posturl"));
		payPalConfig.setReturnurl(environment.getProperty("paypal.returnurl"));
		PayPalSucess payPalSucess = new PayPalSucess();
		PayPalResult result = payPalSucess.getPayPal(request, payPalConfig);

		if (result == null) {
			System.out.println("Failed");
		} else {
			session.setAttribute("check_pay", 1);

			Book book = new Book();
			Random random = new Random();
			String randomString = String.format("%08x", random.nextInt());
			System.out.println("Chuỗi ngẫu nhiên: " + randomString);
			String code = "ESBOOK-" + randomString;
			book.setCodeBook(code);
			book.setTotal(Integer.parseInt(session.getAttribute("grand_total").toString()));
			book.setDate(simpleDateFormat.parse(session.getAttribute("from").toString()));
			book.setStatus(true);
			book.setCreated(new Date());
			////////////// đây xíu sửa
			Account acc = accountService.getAccountByIdAccount(Integer.parseInt(authentication.getName()));
			book.setAccount(acc);

			Room room = (Room) session.getAttribute("room");
			BookRoom bookroom = new BookRoom();

			bookroom.setDatePrice(datePriceService.findDatePriceByIdRoomAndQuantityday(room.getId(),
					Integer.parseInt(session.getAttribute("quantityday").toString())));
			bookroom.setQuantity(Integer.parseInt(session.getAttribute("quantity_room").toString()));
			bookroom.setPrice(Double.valueOf(session.getAttribute("price").toString()));
			bookroom.setCreated(new Date());
			String note = "Người book:" + session.getAttribute("fullnamesave").toString() + "-cccd:"
					+ session.getAttribute("cccdsave").toString() + "-email:"
					+ session.getAttribute("emailsave").toString() + "-phone:"
					+ session.getAttribute("phonesave").toString();
			book.setNote(note);
			if (bookService.save(book)) {
				bookroom.setBook(book);
				if (bookRoomService.save(bookroom)) {
					String content = "Tên: " + session.getAttribute("fullnamesave");
					content += "<br>Phone: " + session.getAttribute("phonesave").toString();
					content += "<br>CodeBook: " + code;
					content += "<br>From: " + session.getAttribute("from");
					content += "<br>To: " + session.getAttribute("to");
					String email = environment.getProperty("spring.mail.username");
					if (mailService.send(email, session.getAttribute("emailsave").toString(),
							"Bạn đã book phòng thành công", content)) {
						session.setAttribute("codebook", code);
						session.setAttribute("id_dateprice",
								datePriceService
										.findDatePriceByIdRoomAndQuantityday(room.getId(),
												Integer.parseInt(session.getAttribute("quantityday").toString()))
										.getId());
						session.setAttribute("id_bookroom",
								bookRoomService.findBookRoomByIdDatePriceAndIdBook(
										Integer.parseInt(session.getAttribute("id_dateprice").toString()),
										bookService.findBookByCodeBook(code).getId()).getId());
						return "redirect:/account/bookings";
					}

				}
			}

		}
		return "redirect:/account/bookings";
	}

	@GetMapping(value = { "book_detail" })
	private String invoice(ModelMap modelMap, HttpSession session, @RequestParam("id_bookroom") String id_bookroom) {
		BookRoom bookRoom = bookRoomService.find(Integer.parseInt(id_bookroom));
		modelMap.put("bookroom", bookRoom);
		modelMap.put("admin", accountService.getAccountByIdAccount(1));
		modelMap.put("from", bookRoom.getBook().getDate());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(bookRoom.getBook().getDate());
		calendar.add(Calendar.DATE, bookRoom.getDatePrice().getQuantityDate());
		Date to = calendar.getTime();
		modelMap.put("to", to);
		return "account/invoice";
	}

	@GetMapping(value = { "cancel" })
	private String cancel(ModelMap modelMap, @RequestParam("id_bookroom") String id_bookroom) {
		BookRoom bookRoom = bookRoomService.find(Integer.parseInt(id_bookroom));
		Book book = bookService.findBookByIdBook(bookRoom.getBook().getId());
		book.setStatus(false);
		if (bookService.save(book)) {
			return "redirect:/account/booking?page=1";
		}
		return "redirect:/account/booking?page=1";
	}

	@GetMapping(value = { "changepass" })
	private String changepass() {
		return "account/change_Password";
	}
}
