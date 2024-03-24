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

import com.demo.entities.Category;
import com.demo.entities.Role;
import com.demo.services.CategoryService;
@Controller
@RequestMapping("admin/category")
public class CategoryAdminController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = { "", "index" }, method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("check", 1);
		modelMap.put("categories", categoryService.listAll());
		modelMap.put("a", new Category());
		return "admin/category/index";
	}
	
	@RequestMapping(value = {"created" }, method = RequestMethod.GET)
	public String created(ModelMap modelMap) {
		modelMap.put("category", new Category());
		return "admin/category/created";
	}
	@RequestMapping(value = {"edit/{id}" }, method = RequestMethod.GET)
	public String edit(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.put("check", 2);
		modelMap.put("a", categoryService.findByIdRole(id));
		modelMap.put("categories", categoryService.listAll());
		return "admin/category/index";
	}
	@RequestMapping(value = {"delete/{id}" }, method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		if(categoryService.delete(id)) {
			redirectAttributes.addFlashAttribute("msg",3);
			return "redirect:/admin/category/index";
		}
		return "redirect:/admin/category/index";
	}
	
	@RequestMapping(value = {"created" }, method = RequestMethod.POST)
	public String created(@ModelAttribute("a") Category category, RedirectAttributes redirectAttributes) {
		category.setCreated(new Date());
		if(categoryService.save(category)) {
			redirectAttributes.addFlashAttribute("msg",1);
			return "redirect:/admin/category/index";
		}
		return "redirect:/admin/category/created";
	}
	@RequestMapping(value = {"edit" }, method = RequestMethod.POST)
	public String edit(@ModelAttribute("a") Category category, RedirectAttributes redirectAttributes) {
		category.setUpdated(new Date());
		if(categoryService.save(category)) {
			redirectAttributes.addFlashAttribute("msg",2);
			return "redirect:/admin/category/index";
		}
		return "redirect:/admin/category/edit/"+category.getId();
	}
	
}
