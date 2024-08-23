package todolist.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/todo")
	public String getTodoList(Model model) {
		
		List<Item> itemList = itemService.getAllItems();
		model.addAttribute("itemList", itemList);
		
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
	
	@PostMapping("/todo/entry")
	public String postTodoEntry(Model model, @ModelAttribute TodoListForm todoListForm) {
		
		Item item = modelMapper.map(todoListForm, Item.class);
		
		itemService.entryItem(item);
		
		return "redirect:/todo";
	}
	
	@GetMapping("/todo/edit/{id}")
	public String getTodoEdit(Model model, TodoListForm todoListForm, @PathVariable("id") Integer id) {
		
		List<User> userList = userService.getUsers();
		
		model.addAttribute("userList", userList);
		
		log.info(userList.toString());
		
		Item item = itemService.getItemOne(id);
		
		todoListForm = modelMapper.map(item, TodoListForm.class);
		
		model.addAttribute("todoListForm", todoListForm);
		
		log.info(todoListForm.toString());
		
		return "todo/edit";
	}
	
	@PostMapping("/todo/edit/{id}")
	public String postTodoEdit(Model model, TodoListForm todoListForm, @PathVariable("id") Integer id) {
		
		Item item = itemService.getItemOne(id);
		
		if (item != null) {
			item.setItemName(todoListForm.getItemName());
			item.setUserId(todoListForm.getUserId());
			item.setExpireDate(todoListForm.getExpireDate());
			item.setIsFinished(todoListForm.getIsFinished());
			
			itemService.editItem(item);
		}
		
		return "redirect:/todo";
	}
	
	@GetMapping("/todo/delete/{id}")
	public String getTodoDelete(Model model, TodoListForm todoListForm, @PathVariable("id") Integer id) {
		
		List<User> userList = userService.getUsers();
		
		model.addAttribute("userList", userList);
		
		log.info(userList.toString());
		
		Item item = itemService.getItemOne(id);
		
		todoListForm = modelMapper.map(item, TodoListForm.class);
		
		model.addAttribute("todoListForm", todoListForm);
		
		log.info(todoListForm.toString());
		
		return "todo/delete";
	}
	
	@PostMapping("/todo/delete/{id}")
	public String postTodoDelete(Model model, TodoListForm todoListForm, @PathVariable("id") Integer id) {
		
		Item item = itemService.getItemOne(id);
		
		if (item != null) {
			itemService.deleteItem(item);
		}
		
		return "redirect:/todo";
	}
}