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

import com.demo.entities.Role;
import com.demo.services.RoleService;

import jakarta.validation.Valid;
@Controller
@RequestMapping("admin/role")
public class RoleAdminController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = { "", "index" }, method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("roles", roleService.listAll());
		modelMap.put("a", new Role());
		modelMap.put("check", 1);
		return "admin/role/index";
	}
	@RequestMapping(value = {"created" }, method = RequestMethod.GET)
	public String created(ModelMap modelMap) {
		modelMap.put("role", new Role());
		return "admin/role/created";
	}
	@RequestMapping(value = {"edit/{id}" }, method = RequestMethod.GET)
	public String edit(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.put("roles", roleService.listAll());
		modelMap.put("a", roleService.findByIdRole(id));
		modelMap.put("check", 2);
		return "admin/role/index";
	}
	@RequestMapping(value = {"delete/{id}" }, method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		if(roleService.delete(id)) {
			redirectAttributes.addFlashAttribute("msg",3);
			return "redirect:/admin/role/index";
		}
		return "redirect:/admin/role/index";
	}
	
	@RequestMapping(value = {"created" }, method = RequestMethod.POST)
	public String created(@ModelAttribute("role") Role role , RedirectAttributes redirectAttributes) {
		role.setCreated(new Date());
		if(roleService.save(role)) {
			redirectAttributes.addFlashAttribute("msg",1);
			return "redirect:/admin/role/index";
		}
		return "redirect:/admin/role/created";
	}
	@RequestMapping(value = {"edit" }, method = RequestMethod.POST)
	public String edit(@ModelAttribute("role") Role role, RedirectAttributes redirectAttributes) {
		role.setUpdated(new Date());
		if(roleService.save(role)) {
			redirectAttributes.addFlashAttribute("msg",2);
			return "redirect:/admin/role/index";
		}
		return "redirect:/admin/role/edit/"+role.getId();
	}
}
