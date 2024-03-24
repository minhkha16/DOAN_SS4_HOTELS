package com.demo.controllers.admin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Area;
import com.demo.entities.DatePrice;
import com.demo.entities.Image;
import com.demo.entities.Kindofroom;
import com.demo.entities.Role;
import com.demo.entities.Room;
import com.demo.helpers.FileHelper;
import com.demo.services.AccountService;
import com.demo.services.DatePriceService;
import com.demo.services.HotelService;
import com.demo.services.ImageService;
import com.demo.services.KindOfRoomService;

import com.demo.services.RoomService;

@Controller
@RequestMapping({ "admin/room" })
public class RoomAdminController {
	@Autowired
	private RoomService roomService;

	@Autowired
	private DatePriceService datePriceService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private KindOfRoomService kindOfRoomService;
	@Autowired
	private HotelService hotelService;

	@RequestMapping(value = { "index" }, method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("dateprices", datePriceService.finbydesc());
		return "admin/room/index";
	}
	
	@RequestMapping(value = { "detail/{id}" }, method = RequestMethod.GET)
	public String detail(ModelMap modelMap, @PathVariable("id")int id) {
		modelMap.put("imgs", imageService.edit(id));
		modelMap.put("kindofroom", kindOfRoomService.findKindofroomByIdRoom(id));
		return "admin/room/detail";
	}

	// search
	@RequestMapping(value = { "index" }, method = RequestMethod.POST)
	public String index(ModelMap modelMap, @RequestParam("price1") double price1, @RequestParam("price2") double price2,
			@RequestParam("search") int search, @RequestParam("date1") int date1, @RequestParam("date2") int date2) {
		if (search == 1) {
			modelMap.put("dateprices", datePriceService.searchByPrice(price1, price2));

		} else {
			modelMap.put("dateprices", datePriceService.searchByDate(date1, date2));
		}
		return "admin/room/index";
	}

	

	@RequestMapping(value = { "created" }, method = RequestMethod.GET)
	public String created(ModelMap modelMap) {
		return "admin/account/created";
	}

	@RequestMapping(value = { "edit/{id}" }, method = RequestMethod.GET)
	public String edit(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.put("imgs", imageService.edit(id));
		modelMap.put("room", roomService.findByIdRoom(id));
		modelMap.put("hotels", hotelService.listAll());
		modelMap.put("dateprice", datePriceService.edit(id));
		modelMap.put("kindofroom", kindOfRoomService.findbyidroom(id));
		return "admin/room/edit";
	}
	@RequestMapping(value = { "delete/{id}" }, method = RequestMethod.GET )
	public String delete(ModelMap modelMap, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		Room room = roomService.find(id);
		room.setStatus(2);
		roomService.save(room);
		redirectAttributes.addFlashAttribute("msg", 3);
		return "redirect:/admin/room/index";
	}

	@RequestMapping(value = { "edit" }, method = RequestMethod.POST)
	public String edit(@ModelAttribute("room") Room room, @ModelAttribute("dateprice") DatePrice datePrice,
			@ModelAttribute("kindofroom") Kindofroom kindofroom, RedirectAttributes redirectAttributes,
			@RequestParam("files") MultipartFile[] files, @RequestParam("id_image") String[] id_images,
			@RequestParam("id_dateprice") String id_dateprice, @RequestParam("id_kindofroom") String id_kindofroom) {
		try {
			if (!files[0].getOriginalFilename().isEmpty() && files.length > 0) {
				for (String id_image : id_images) {
					imageService.delete(Integer.parseInt(id_image));
				}
				for (MultipartFile file : files) {
					System.out.println("name: " + file.getOriginalFilename());
					// Upload file
					String fileName = FileHelper.generateFileName(file.getOriginalFilename());
					File imagesFolder = new ClassPathResource("static/images").getFile();
					Path path = Paths.get(imagesFolder.getAbsolutePath() + File.separator + fileName);
					Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
					Image img = new Image();
					img.setRoom(room);
					img.setCreated(new Date());
					img.setName(fileName);
					imageService.save(img);

				}
				room.setUpdated(new Date());
				if (roomService.edit(room)) {
					datePrice.setRoom(room);
					datePrice.setId(Integer.parseInt(id_dateprice));
					datePrice.setUpdated(new Date());
					if (datePriceService.save(datePrice)) {
						kindofroom.setId(Integer.parseInt(id_kindofroom));
						kindofroom.setRoom(room);
						kindofroom.setUpdated(new Date());
						if (kindOfRoomService.save(kindofroom)) {
							redirectAttributes.addFlashAttribute("msg", 2);
							return "redirect:/admin/room/index";
						}
					}
				}
			} else {
				room.setUpdated(new Date());
				if (roomService.edit(room)) {
					datePrice.setRoom(room);
					datePrice.setId(Integer.parseInt(id_dateprice));
					datePrice.setUpdated(new Date());
					if (datePriceService.save(datePrice)) {
						kindofroom.setId(Integer.parseInt(id_kindofroom));
						kindofroom.setRoom(room);
						kindofroom.setUpdated(new Date());
						if (kindOfRoomService.save(kindofroom)) {
							redirectAttributes.addFlashAttribute("msg", 2);
							return "redirect:/admin/room/index";
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/room/edit/" + room.getId();
	}
}
