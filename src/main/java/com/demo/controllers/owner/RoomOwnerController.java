package com.demo.controllers.owner;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Convenient;
import com.demo.entities.DatePrice;
import com.demo.entities.Hotel;
import com.demo.entities.Image;
import com.demo.entities.Kindofroom;
import com.demo.entities.Room;
import com.demo.helpers.FileHelper;
import com.demo.helpers.GeneraionFileName;
import com.demo.services.DatePriceService;
import com.demo.services.EvaluateService;
import com.demo.services.HotelService;
import com.demo.services.ImageService;
import com.demo.services.KindOfRoomService;
import com.demo.services.RoomService;
import com.demo.validators.DatePriceValidator;
import com.demo.validators.KindOfRoomValidator;
import com.demo.validators.RoomValidator;

import jakarta.validation.Valid;
@Controller
@RequestMapping("owner/room")
public class RoomOwnerController {
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private EvaluateService evaluateService;
	
	@Autowired
	private DatePriceService datePriceService;
	
	@Autowired
	private KindOfRoomService kindOfRoomService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private RoomValidator roomValidator;
	
	@Autowired
	private DatePriceValidator datePriceValidator;
	
	@Autowired 
	private KindOfRoomValidator kindOfRoomValidator;
	
	@RequestMapping(value = {"index" }, method = RequestMethod.GET)
	public String index(ModelMap model,Authentication authentication) {
		model.put("rooms", roomService.findAllByIdRoleAccount(Integer.parseInt(authentication.getName())));
		return "owner/room/index";
	}
	
	@GetMapping(value = { "filterByDate" })
	private String filterByDate(@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "to", required = false) String to, ModelMap model,Authentication authentication) {
		try {
			SimpleDateFormat simp = new SimpleDateFormat("dd-MM-yyyy");
			if (from == null || from == "") {
				model.put("rooms", roomService.listAll());
				return "owner/room/index";
			}
			if (to == null || to == "") {
				model.put("rooms", roomService.listAll());
				return "owner/room/index";
			}
			Date f = simp.parse(from);
			Date t = simp.parse(to);

			model.put("from", f);

			model.put("to", t);
			model.put("rooms", roomService.takeByDate(f,t));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "owner/room/index";
	}
	
	@RequestMapping(value = {"evaluate" }, method = RequestMethod.GET)
	public String evaluate(ModelMap modelMap,Authentication authentication) {
		modelMap.put("evaluates", evaluateService.findAllByIdDesc(1, Integer.parseInt(authentication.getName())));
		return "owner/room/evaluate";
	}
	
	
	@RequestMapping(value = {"created" }, method = RequestMethod.GET)
	public String created(ModelMap modelMap, Authentication authentication) {
		Room room = new Room();
		Image img = new Image();
		DatePrice dateprice = new DatePrice();
		Kindofroom kindor = new Kindofroom();
		modelMap.put("kindor", kindor);
		modelMap.put("dateprice", dateprice);
		modelMap.put("room", room);
		modelMap.put("img", img);
		modelMap.put("hotels", hotelService.findAllByIdRoleAccount(Integer.parseInt(authentication.getName()), 1));
		return "owner/room/created";
	}
	
	@RequestMapping(value = {"created" }, method = RequestMethod.POST)
	public String created(ModelMap modelMap,@RequestParam("files") MultipartFile[] files,
			 @ModelAttribute("dateprice") DatePrice dateprice, @ModelAttribute("kindor") Kindofroom kindor, RedirectAttributes attr,
			 @ModelAttribute("room")  Room room, DatePrice emp, BindingResult bindingResult,Authentication authentication) {
		try {
//			if (bindingResult.hasErrors()) {
//				modelMap.put("kindor", kindor);
//				modelMap.put("dateprice", dateprice);
//				modelMap.put("room", room);
//				modelMap.put("hotels", hotelService.findAllByIdRoleAccount(Integer.parseInt(authentication.getName())));
//				return "owner/room/created";
//			}	
//			roomValidator.validate(room, bindingResult);
//			datePriceValidator.validate(emp, bindingResult);
			
			if (files != null && files.length > 1) {
				room.setCreated(new Date());
				var roomId = roomService.saveId(room);
					for (MultipartFile file : files) {
						System.out.println("name: " + file.getOriginalFilename());
						// Upload file
						String fileName = FileHelper.generateFileName(file.getOriginalFilename());
						File imagesFolder = new ClassPathResource("static/images").getFile();
						Path path = Paths.get(imagesFolder.getAbsolutePath() + File.separator + fileName);
						Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
						Image img = new Image();
						img.setRoom(roomId);
						img.setCreated(new Date());
						img.setName(fileName);
						
						if (imageService.save(img)) {
							dateprice.setCreated(new Date());
							dateprice.setRoom(roomId);
							if (datePriceService.save(dateprice)) {
								kindor.setCreated(new Date());
								kindor.setRoom(roomId);
								kindOfRoomService.save(kindor);
							}
						}
				}
			} else {
				attr.addFlashAttribute("msg", 2);
				return "redirect:/owner/room/index";
			}
				
				
			attr.addFlashAttribute("msg", 1);
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		return "redirect:/owner/room/index";
	}
	
	@RequestMapping(value = {"edit/{id}" }, method = RequestMethod.GET)
	public String edit(ModelMap modelMap, @PathVariable("id") int id, Authentication authentication) {
		Room room = roomService.find(id);
		List<Image> img = imageService.edit(room.getId());
		Kindofroom kindor = kindOfRoomService.edit(room.getId());
		DatePrice dateprice = datePriceService.edit(room.getId());
		modelMap.put("kindor", kindor);
		modelMap.put("dateprice", dateprice);
		modelMap.put("room", room);
		modelMap.put("imgs", img);
		modelMap.put("hotels", hotelService.findAllByIdRoleAccount(Integer.parseInt(authentication.getName()), 1));
		return "owner/room/edit";
	}
	
	@RequestMapping(value = {"edit" }, method = RequestMethod.POST)
	public String edit(ModelMap modelMap, @ModelAttribute("room") Room room,@ModelAttribute("dateprice") DatePrice dateprice,
			@ModelAttribute("kindor") Kindofroom kindor,@RequestParam("files") MultipartFile[] files, @RequestParam("idImg") String[] idImg,
			@RequestParam("tgian") String[] tgian, RedirectAttributes attr, @RequestParam("id_Date") String id_Date, @RequestParam("id_Kindor") String id_Kindor,
			@RequestParam("idCreatedRoom") String idCreatedRoom,@RequestParam("idCreatedDate") String idCreatedDate,@RequestParam("idCreatedKind") String idCreatedKind) {
		try {
			SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
			room.setCreated(simp.parse(idCreatedRoom));
			room.setUpdated(new Date());
			var roomId = roomService.saveId(room);
			var a = 0;
			if (!files[0].getOriginalFilename().isEmpty() && files.length > 0) {
				for (String i : idImg) {
					if(imageService.delete(Integer.parseInt(i))) {
						System.out.print("ok");
					}
				}
				for (MultipartFile file : files) {
					String fileName = FileHelper.generateFileName(file.getOriginalFilename());
					File imagesFolder = new ClassPathResource("static/images").getFile();
					Path path = Paths.get(imagesFolder.getAbsolutePath() + File.separator + fileName);
					Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
					Image img = new Image();
					img.setRoom(roomId);
					img.setUpdated(new Date());
					img.setCreated(simp.parse(tgian[a]));
					img.setName(fileName);
					imageService.save(img);
				}
				dateprice.setCreated(simp.parse(idCreatedDate));
				dateprice.setUpdated(new Date());
				dateprice.setId(Integer.parseInt(id_Date));
				dateprice.setRoom(roomId);
				
				kindor.setCreated(simp.parse(idCreatedKind));
				kindor.setUpdated(new Date());
				kindor.setId(Integer.parseInt(id_Kindor));
				kindor.setRoom(roomId);
				
			} else {
				dateprice.setCreated(simp.parse(idCreatedDate));
				dateprice.setUpdated(new Date());
				dateprice.setId(Integer.parseInt(id_Date));
				dateprice.setRoom(roomId);
				datePriceService.save(dateprice);
				
				kindor.setCreated(simp.parse(idCreatedKind));
				kindor.setUpdated(new Date());
				kindor.setId(Integer.parseInt(id_Kindor));
				kindor.setRoom(roomId);
				kindOfRoomService.save(kindor);
			}
			attr.addFlashAttribute("msg", 1);
			return "redirect:/owner/room/index";	
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			attr.addFlashAttribute("msg", 2);
		}
		return "redirect:/owner/room/index";
	}
	
	@RequestMapping(value = {"delete/{id}" }, method = RequestMethod.GET)
	private String delete(@PathVariable int id, RedirectAttributes attr) {
		Room room = roomService.find(id);
		try {
			if (roomService.delete(room)) {
				return "redirect:/owner/room/index";
			} else {
				return "redirect:/owner/room/index";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/owner/room/index";
	}
	
	@RequestMapping(value = {"details_KindOfRoom/{id}" }, method = RequestMethod.GET)
	private String details_KindOfRoom(@PathVariable int id, RedirectAttributes attr, ModelMap modelMap) {
		modelMap.put("rooms", roomService.listById(id));
		
		return "owner/room/details_KindOfRoom";
	}
	
	@RequestMapping(value = {"findByContent" }, method = RequestMethod.GET)
	private String findByName(ModelMap model, String content, Authentication authentication) {
		if(content == null || content == "") {
			model.put("rooms", roomService.listAll());
			return "owner/room/index";
		} else {
			model.put("rooms", roomService.findByContent(content));
		}
		return "owner/room/index";
	}
}
