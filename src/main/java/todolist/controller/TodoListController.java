package todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.extern.slf4j.Slf4j;
import todolist.form.TodoListForm;
import todolist.model.Item;
import todolist.model.User;
import todolist.service.ItemService;
import todolist.service.UserService;

@Controller
@Slf4j
public class TodoListController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/todo")
	public String getTodoList(Model model) {
		
		List<Item> itemList = itemService.getAllItems();
		model.addAttribute(itemList);
		
		log.info(itemList.toString());
		
		return "todo/index";
	}
	
	@GetMapping("/todo/entry")
	public String getTodoEntry(Model model, @ModelAttribute TodoListForm todoListForm) {
		
		List<User> userList = userService.getUsers();
		model.addAttribute("userList", userList);
		
		log.info(userList.toString());
		
		return "todo/entry";
	}
}