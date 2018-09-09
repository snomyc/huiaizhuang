package com.snomyc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snomyc.sys.bean.User;
import com.snomyc.sys.service.UserService;
import springfox.documentation.annotations.ApiIgnore;
import java.util.List;

@ApiIgnore
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/list";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "user/list";
	}
	
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "user/userAdd";
	}
	
	@RequestMapping("/add")
	public String add(User user) {
		userService.save(user);
		return "redirect:/list";
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(Model model, String id) {
		User user = userService.getById(id);
		model.addAttribute("user", user);
		return "user/userEdit";
	}
	
	@RequestMapping("/edit")
	public String edit(User user) {
		userService.save(user);
		return "redirect:/list";
	}
	
	@RequestMapping("/delete")
	public String delete(String id) {
		userService.delete(id);
		return "redirect:/list";
	}
}
