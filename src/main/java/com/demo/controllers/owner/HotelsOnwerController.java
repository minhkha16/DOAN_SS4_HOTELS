package com.demo.controllers.owner;

import java.io.Console;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Account;
import com.demo.entities.Category;
import com.demo.entities.Hotel;
import com.demo.entities.Role;
import com.demo.helpers.FileHelper;
import com.demo.helpers.GeneraionFileName;
import com.demo.services.AccountService;
import com.demo.services.AreaService;
import com.demo.services.CategoryService;
import com.demo.services.HotelService;
import com.demo.services.RoleService;
import com.demo.services.RoomService;
import com.demo.validators.HotelValidator;

import jakarta.validation.Valid;
@Controller
@RequestMapping("owner/hotels")
public class HotelsOnwerController {
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private HotelValidator hotelValidator;
	
	@RequestMapping(value = { "", "index" }, method = RequestMethod.GET)
	public String index(ModelMap modelmap, Authentication authentication) {
		modelmap.put("hotels", hotelService.findAllByIdRoleAccount(Integer.parseInt(authentication.getName()), 1));
		return "owner/hotels/index";
	}
	
	@RequestMapping(value = {"created" }, method = RequestMethod.GET)
	public String created(ModelMap modelMap) {
		Hotel hotel = new Hotel();
		modelMap.put("hotel", hotel);
		modelMap.put("categories", categoryService.listAll());
		modelMap.put("areas", areaService.listAll());
		return "owner/hotels/created";
	}
	
	@RequestMapping(value = {"created" }, method = RequestMethod.POST)
	private String created(ModelMap model, @ModelAttribute("hotel") Hotel hotel,
			@ModelAttribute("role") Role role,@RequestParam("file") MultipartFile file,RedirectAttributes attr, Authentication authentication,
			@ModelAttribute("hotel") @Valid Hotel empl,
			BindingResult bindingResult) {
		try {
			
			if (bindingResult.hasErrors()) {
				model.put("categories", categoryService.listAll());
				model.put("accounts", accountService.findAll());
				model.put("areas", areaService.findAll());
				return "owner/hotels/created";
			}
			hotelValidator.validate(empl, bindingResult);	
			if (file != null && file.getSize() > 0) {
				File folder = new File(new ClassPathResource(".").getFile().getPath() + "/static/images");

				String fileName = GeneraionFileName.changeFileName(file.getOriginalFilename());

				Path path = Paths.get(folder.getAbsolutePath() + File.separator + fileName);

				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

				hotel.setImage(fileName);
				
				hotel.setCreated(new Date());
				hotel.setStatus(1);
				
				Role roles = new Role();
				roles.setId(2);
				
				Account acc = new Account();
				acc.setRole(roles);
				acc.setId(Integer.parseInt(authentication.getName()));
				hotel.setAccount(acc);
				
				if (hotelService.save(hotel)) {
					attr.addFlashAttribute("msg", 1);
				} else {
					attr.addFlashAttribute("msg", 2);
					return "redirect:/owner/hotels/index";
				}
			} else {
				attr.addFlashAttribute("msg", 2);
				return "redirect:/owner/hotels/index";
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/owner/hotels/index";
	}
	
	@RequestMapping(value = {"delete/{id}" }, method = RequestMethod.GET)
	private String delete(@PathVariable int id, RedirectAttributes attr) {
		Hotel ht = hotelService.find(id);
		try {
			ht.setStatus(2);
			if (hotelService.save(ht)) {
				attr.addFlashAttribute("msg", 1);
				return "redirect:/owner/hotels/index";
			} else {
				attr.addFlashAttribute("msg", 2);
				return "redirect:/owner/hotels/index";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/owner/hotels/index";
	}
	
	@RequestMapping(value = {"edit/{id}" }, method = RequestMethod.GET)
	private String editGET(ModelMap model, @PathVariable int id) {
		Hotel hotel = hotelService.find(id);
		model.put("hotel", hotel);
		model.put("categories", categoryService.listAll());
		model.put("accounts", accountService.findAll());
		model.put("areas", areaService.findAll());
		return "owner/hotels/edit";
	}

	@RequestMapping(value = {"edit" }, method = RequestMethod.POST)
	private String editPost(ModelMap model,@ModelAttribute("hotel") Hotel hotel, RedirectAttributes attr,@RequestParam("file") MultipartFile file
			,@Valid Hotel empl, BindingResult bindingResult,@RequestParam("idCreated") String idCreated,Authentication authentication) {
		try {
			SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
			if (file != null && file.getSize() > 0) {
				File folder = new File(new ClassPathResource(".").getFile().getPath() + "/static/images");

				String fileName = GeneraionFileName.changeFileName(file.getOriginalFilename());

				Path path = Paths.get(folder.getAbsolutePath() + File.separator + fileName);

				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

				hotel.setImage(fileName);
			}
			Role roles = new Role();
			roles.setId(2);
			
			Account acc = new Account();
			acc.setRole(roles);
			acc.setId(Integer.parseInt(authentication.getName()));
			hotel.setAccount(acc);
			hotel.setStatus(1);
			hotel.setUpdated(new Date());
			hotel.setCreated(simp.parse(idCreated));
				
			hotelValidator.validate(hotel, bindingResult);	
			if (bindingResult.hasErrors()) {
				model.put("categories", categoryService.listAll());
				model.put("accounts", accountService.findAll());
				model.put("areas", areaService.findAll());
				return "owner/hotels/edit";
			} 
			
			hotelService.save(hotel);
			attr.addFlashAttribute("msg", 1);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			attr.addFlashAttribute("msg", 2);
			return "redirect:/owner/hotels/index";
		}
		return "redirect:/owner/hotels/index";
	}
	

	
	@RequestMapping(value = {"details/{id}" }, method = RequestMethod.GET)
	private String details(ModelMap model, @PathVariable int id, Authentication authentication) {
		model.put("rooms", roomService.findByIdRoomHotel(id));
		model.put("hotel", hotelService.listAll());
		return "owner/hotels/details_room";
	}
	
	@RequestMapping(value = {"findByName" }, method = RequestMethod.GET)
	private String findByName(ModelMap model, String keyword) {
		if(keyword == null || keyword == "") {
			model.put("hotels", hotelService.listAll());
			return "owner/hotels/index";
		} else {
			model.put("hotels", hotelService.findByName(keyword));
		}
		return "owner/hotels/index";
	}
}
