package com.demo.controllers.admin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.demo.entities.Hotel;
import com.demo.entities.Role;
import com.demo.helpers.FileHelper;
import com.demo.services.AccountService;
import com.demo.services.AreaService;
import com.demo.services.CategoryService;
import com.demo.services.HotelService;
@Controller
@RequestMapping({"admin/hotel"})
public class HotelAdminController {
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value = {"index" }, method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("hotels", hotelService.listAll());
		return "admin/hotel/index";
	}
	@RequestMapping(value = {"index" }, method = RequestMethod.POST)
	public String index(ModelMap modelMap, @RequestParam("keyword") String keyword) {
		modelMap.put("hotels", hotelService.search(keyword));
		return "admin/hotel/index";
	}
	
	@RequestMapping(value = {"edit/{id}" }, method = RequestMethod.GET)
	public String edit(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.put("hotel", hotelService.findByIdHotel(id));
		modelMap.put("areas", areaService.listAll());
		modelMap.put("categories", categoryService.listAll());
		return "admin/hotel/edit";
	}
	@RequestMapping(value = {"delete/{id}" }, method = RequestMethod.GET)
	public String delete(ModelMap modelMap, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		Hotel hotel = hotelService.find(id);
		hotel.setStatus(2);
		hotelService.save(hotel);
		redirectAttributes.addFlashAttribute("msg",3);
		return "redirect:/admin/hotel/index";
	}
	
	@RequestMapping(value = {"edit" }, method = RequestMethod.POST)
	public String edit(@ModelAttribute("hotel") Hotel hotel, RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file ) {
		
		try {
			hotel.setUpdated(new Date());
			var check = hotelService.findByIdHotel(hotel.getId());
			if(!file.isEmpty()) {
				String fileName = FileHelper.generateFileName(file.getOriginalFilename());
				File imagesFolder = new ClassPathResource("static/images").getFile();
				Path path = Paths.get(imagesFolder.getAbsolutePath() + File.separator + fileName);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				hotel.setImage(fileName);
			}
			// Upload File
			if(hotelService.edit(hotel)) {
				redirectAttributes.addFlashAttribute("msg",2);
				return "redirect:/admin/hotel/index";
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return "redirect:/admin/hotel/edit/"+hotel.getId();
	}
}
