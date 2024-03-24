package com.demo.controllers.owner;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.entities.Account;
import com.demo.entities.DatePrice;
import com.demo.entities.Image;
import com.demo.entities.Kindofroom;
import com.demo.entities.Role;
import com.demo.entities.Room;
import com.demo.helpers.FileHelper;
import com.demo.services.AccountService;

import jakarta.validation.Valid;
@Controller
@RequestMapping("owner/account")
public class AccountOwnerController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@RequestMapping(value = {"profile" }, method = RequestMethod.GET)
	public String profile(ModelMap modelMap, Authentication authentication) {
		Account acc = accountService.findByIdAccount(Integer.parseInt(authentication.getName()));
		
		modelMap.put("account", acc);
		return "owner/account/edit";
	}
	
	@RequestMapping(value = {"profile" }, method = RequestMethod.POST)
	public String profile(ModelMap modelMap, @ModelAttribute("account") Account account,RedirectAttributes attr, Authentication authentication) {
		try {
			Role roles = new Role();
			roles.setId(2);
			account.setRole(roles);
			account.setStatus(1);
			account.setUpdateted(new Date());
			account.setId(Integer.parseInt(authentication.getName()));
			if (!account.getPassword().equals("")) {
				account.setPassword(encoder.encode(account.getPassword()));
			} else {
				var acc = accountService.findByIdAccount(Integer.parseInt(authentication.getName()));
				account.setPassword(acc.getPassword());
			}
			if (accountService.save(account)) {
				attr.addFlashAttribute("msg", 1);
			}else {
				attr.addFlashAttribute("msg", 2);
				return "redirect:/owner/hotels/index";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/owner/hotels/index";
	}
}
