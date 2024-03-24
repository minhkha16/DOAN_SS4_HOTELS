package com.demo.controllers.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Area;
import com.demo.entities.Category;
import com.demo.entities.Role;
import com.demo.services.AreaService;
import com.demo.services.CategoryService;
@Controller
@RequestMapping("admin/area")
public class AreaAdminController {
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value = { "", "index" }, method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("areas", areaService.listAll());
		modelMap.put("a", new Area());
		modelMap.put("check", 1);
		return "admin/area/index";
	}
	
	@RequestMapping(value = {"edit/{id}" }, method = RequestMethod.GET)
	public String edit(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.put("areas", areaService.listAll());
		modelMap.put("a", areaService.findByIdRole(id));
		modelMap.put("check", 2);
		return "admin/area/index";
	}
	@RequestMapping(value = {"delete/{id}" }, method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		if(areaService.delete(id)) {
			redirectAttributes.addFlashAttribute("msg",3);
			return "redirect:/admin/area/index";
		}
		return "redirect:/admin/area/index";
	}
	
	@RequestMapping(value = {"created" }, method = RequestMethod.POST)
	public String created(@ModelAttribute("a") Area area, RedirectAttributes redirectAttributes) {
		if(areaService.save(area)) {
			redirectAttributes.addFlashAttribute("msg",1);
			return "redirect:/admin/area/index";
		}
		return "redirect:/admin/area/index";
	}
	@RequestMapping(value = {"edit" }, method = RequestMethod.POST)
	public String edit(@ModelAttribute("a") Area area, RedirectAttributes redirectAttributes) {
		
		if(areaService.save(area)) {
			redirectAttributes.addFlashAttribute("msg",2);
			return "redirect:/admin/area/index";
		}
		return "redirect:/admin/area/edit/"+area.getId();
	}
	
}
